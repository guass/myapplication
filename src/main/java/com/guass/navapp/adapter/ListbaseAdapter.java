package com.guass.navapp.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.guass.navapp.base.BaseAdapter;
import com.guass.navapp.base.BaseHolder;

import java.util.List;

/**
 * Created by guass on 2016/3/26.
 */
public abstract class ListbaseAdapter <T extends  BaseHolder> extends BaseAdapter {

    public ListbaseAdapter(Context context, List datas) {
        super(context, datas);
    }

    @Override
    public void bindViewHoler(BaseHolder holder, int position) {
        holder.setDatas(datas,position);
    }

    @Override
    public BaseHolder createViewHoler(ViewGroup parent, int viewType) {
        return createRealViewHolder(parent, viewType);
    }

    public  abstract T createRealViewHolder(ViewGroup parent, int viewType);
}
