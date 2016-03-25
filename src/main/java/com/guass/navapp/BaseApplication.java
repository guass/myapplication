package com.guass.navapp;

import android.app.Application;
import android.content.Context;
import android.widget.BaseAdapter;

/**
 * Created by guass on 2016/3/14.
 */
public class BaseApplication extends Application {
    public  static BaseApplication mBaseApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        mBaseApplication = this;
    }

    public static Context getContext()
    {
        return mBaseApplication;
    }
}
