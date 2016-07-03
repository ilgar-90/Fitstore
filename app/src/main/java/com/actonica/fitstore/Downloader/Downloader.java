package com.actonica.fitstore.Downloader;

import android.content.Context;
import android.os.Environment;
import android.support.v4.os.AsyncTaskCompat;
import android.util.Log;

import com.actonica.fitstore.Helpers.FilesPathResolver;
import com.actonica.fitstore.Models.Program;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * Created by ilgar on 03.07.2016.
 */
public class Downloader implements IDownloader{

    private static volatile Downloader instance;
    private static Context context;

    public static Downloader getInstance(Context mContext) {
        Downloader localInstance = instance;
        if (localInstance == null) {
            synchronized (Downloader.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new Downloader();
                    context = mContext;
                }
            }
        }
        return localInstance;
    }


    private HashMap<Integer, DownloadTaskInfo> downloads = new HashMap<>();

    public void startDownload(Program program){
        final DownloadTask downloadTask = new DownloadTask(program.getId(), program.getZipFile(), context, this);
        AsyncTaskCompat.executeParallel(downloadTask, null);
        DownloadTaskInfo dti = new DownloadTaskInfo(downloadTask, program);
        downloads.put(program.getId(), dti);
    }


    public void cancelDownload(int programId){
        DownloadTask cancelled = downloads.get(programId).getDownloadTask();
        if (cancelled != null){
            cancelled.cancel(true); //TODO remove file
            removeDownloadTask(programId);
        }
    }

    private void removeDownloadTask(int programId){
        downloads.remove(programId);
    }

    public void updateDownloadProgress(int programId, int progress) {
        Log.d("HUI", "HUI Downloading program: " + programId + "; progress: " + progress);
    }

    public void setDownloadFinished(int programId, boolean isSuccess) {
        Log.d("HUI", "HUI FINISHED : "+programId+"; SUCCESS: "+isSuccess);
        if (!isSuccess)
            return;

        DownloadTaskInfo dti = downloads.get(programId);
        Program program = dti.getProgram();
        String zipname = program.getZipFile();
        String path = FilesPathResolver.getProgramFilesPath(programId, context) + "/";

        InputStream is;
        ZipInputStream zis;
        try
        {
            String filename;
            is = new FileInputStream(path + zipname);
            zis = new ZipInputStream(new BufferedInputStream(is));
            ZipEntry ze;
            byte[] buffer = new byte[1024];
            int count;

            while ((ze = zis.getNextEntry()) != null)
            {
                // zapis do souboru
                filename = ze.getName();


                {
                    String FileName = path + filename;
                    File f = new File(FileName.substring(0, FileName.lastIndexOf("/")));
                    if (!f.exists())
                        f.mkdirs();
                }


                FileOutputStream fout = new FileOutputStream(path + filename);
                // cteni zipu a zapis
                while ((count = zis.read(buffer)) != -1)
                {
                    fout.write(buffer, 0, count);
                }

                fout.close();
                zis.closeEntry();
            }

            zis.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

        File zip = new File(path+zipname);
        zip.delete();

        Log.d("HUI", "HUI UNZIPPED!!!");
    }
}
