package com.actonica.fitstore.Helpers;

import android.content.Context;
import android.os.Environment;

import java.io.File;

/**
 * Created by ilgar on 03.07.2016.
 */
public class FilesPathResolver {

    public static String getProgramFilesPath(int programId, Context context){
        String path = String.format("%s/programs/%s", /*context.getApplicationInfo().dataDir*/ Environment.getExternalStorageDirectory().getAbsoluteFile(), Integer.toString(programId));
        new File(path).mkdirs();
        return path;
    }

}
