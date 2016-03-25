package com.guass.navapp.utils;

import android.util.Log;

import com.lidroid.xutils.BitmapUtils;

/**
 * Created by guass on 2016/3/23.
 */
public class BitmapHelper {
    public static BitmapUtils bitmapUtils;

    public static BitmapUtils getBitmapUtils()
    {

        if(bitmapUtils == null)
        {
            bitmapUtils = new BitmapUtils(Utils.getContext(), FileUtils.getIconDir().getAbsolutePath(), 0.3f); //take 30% mem
        }
        return  bitmapUtils;
    }
}
