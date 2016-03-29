package com.guass.navapp.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.guass.navapp.base.BaseHolder;

import java.util.List;

/**
 * Created by guass on 2016/3/29.
 */
public class HomeAdapter extends ListbaseAdapter {

    public HomeAdapter(Context context, List datas) {
        super(context, datas);
    }

    @Override
    protected List onLoad() {
        return null;
    }

    @Override
    public BaseHolder createRealViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public BaseHolder headerViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public boolean isHasHeader() {
        return false;
    }
}
