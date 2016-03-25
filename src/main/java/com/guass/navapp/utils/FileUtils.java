package com.guass.navapp.utils;

import android.content.Context;
import android.os.Environment;

import java.io.File;

/**
 * Created by guass on 2016/3/20.
 */
public class FileUtils {
    public static final String CACHE = "cache";
    public static final String ICON = "icon";
    public static final String ROOT = "GuassAppStore";

    public static File getIconDir()
    {
        return getDir(ICON);
    }

    public static File getCacheDir()
    {
        return getDir(CACHE);
    }

    public static File getDir(String dir)
    {

        StringBuilder path = new StringBuilder();
        if(!isSDAvalible())
        {

            path.append(Utils.getContext().getCacheDir()).append(File.separator).append(ROOT).append(File.separator).append(dir);

        }
        else
        {
            path.append(Environment.getExternalStorageDirectory().getAbsolutePath());
            path.append(File.separator);
            path.append(ROOT).append(File.separator).append(dir);
        }


        File file = new File(path.toString());
        if(!file.exists() || !file.isDirectory())
        {
            file.mkdirs();
        }
        return file;
    }

    private static boolean isSDAvalible()
    {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }
}
