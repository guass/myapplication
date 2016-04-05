package com.guass.navapp.holder;

import android.view.View;

import com.guass.navapp.bean.AppInfo;

/**
 * Created by guass on 2016/4/5.
 */
public abstract class DetailBaseHolder {

    public View mView;
    public AppInfo datas;

    public DetailBaseHolder(AppInfo datas)
    {
        this.datas = datas;
        intView();
        refreshView();
    }

    public abstract void intView();
    public abstract void refreshView();

    public abstract View getConvertView();

}
