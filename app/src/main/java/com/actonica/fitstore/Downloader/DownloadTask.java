package com.actonica.fitstore.Downloader;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.PowerManager;
import android.util.Log;

import com.actonica.fitstore.Helpers.FilesPathResolver;
import com.actonica.fitstore.Helpers.UrlResolver;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by ilgar on 03.07.2016.
 */
public class DownloadTask extends AsyncTask<Void, Integer, Boolean> {

    private int programId;
    private String zip_name;
    private Context context;
    private IDownloader callback;



    public DownloadTask(int programId, String zip_name, Context ctx, IDownloader dwnldr) {
        this.programId = programId;
        this.zip_name = zip_name;
        this.context = ctx;
        this.callback = dwnldr;
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        InputStream input = null;
        OutputStream output = null;
        HttpURLConnection connection = null;
        try {
            URL url = new URL(UrlResolver.getProgramDownloadUrl(zip_name));
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();

            // expect HTTP 200 OK, so we don't mistakenly save error report
            // instead of the file
            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                return false;
            }

            // this will be useful to display download percentage
            // might be -1: server did not report the length
            int fileLength = connection.getContentLength();

            // download the file
            input = connection.getInputStream();
            String appDataPath = FilesPathResolver.getProgramFilesPath(programId, context);
            File myFile = new File(appDataPath, zip_name);
            output = new FileOutputStream(myFile);

            byte data[] = new byte[4096];
            long total = 0;
            int count;
            int progress = 0;
            while ((count = input.read(data)) != -1) {
                // allow canceling with back button
                if (isCancelled()) {
                    input.close();
                    return null;
                }
                total += count;
                // publishing the progress....
                if (fileLength > 0) { // only if total length is known
                    int cur_progress = (int)(total * 100 / fileLength);
                    if (progress != cur_progress) {
                        progress = cur_progress;
                        publishProgress(progress);
                    }
                }
                output.write(data, 0, count);
            }
        } catch (Exception e) {
            Log.e(this.getClass().getName(), e.toString());
            return false;
        } finally {
            try {
                if (output != null)
                    output.close();
                if (input != null)
                    input.close();
            } catch (IOException ignored) {
            }

            if (connection != null)
                connection.disconnect();
        }
        return true;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        this.callback.updateDownloadProgress(programId, values[0]);
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(Boolean status) {
        this.callback.setDownloadFinished(programId, status);
        super.onPostExecute(status);
    }
}