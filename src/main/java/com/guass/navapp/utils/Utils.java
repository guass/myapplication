package com.guass.navapp.utils;

import android.content.Context;
import android.view.View;

import com.guass.navapp.BaseApplication;

/**
 * Created by guass on 2016/3/14.
 */
public class Utils {
    public static Context getContext()
    {
        return BaseApplication.getContext();
    }

    public static String[] getStringArray(int id)
    {
       return  getContext().getResources().getStringArray(id);
    }

    public static View inflate(int resource)
    {

        return  View.inflate(getContext(),resource,null);
    }
}
