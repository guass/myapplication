package com.guass.navapp.base;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.guass.navapp.R;
import com.guass.navapp.bean.SubjectInfo;
import com.guass.navapp.utils.BitmapHelper;
import com.lidroid.xutils.BitmapUtils;

import java.util.List;

/**
 * Created by guass on 2016/3/25.
 */
public abstract  class BaseAdapter <T, V extends RecyclerView.ViewHolder>  extends RecyclerView.Adapter  {

    protected List<T> datas;

    protected LayoutInflater mLayoutInflater;
    public List<T> getDatas() {
        return datas;
    }

    public void setDatas(List<T> datas) {
        this.datas = datas;
    }

    public BaseAdapter(Context context, List<T> datas)
    {
        this.datas = datas;
   //     bitmapUtils = BitmapHelper.getBitmapUtils();
        mLayoutInflater = LayoutInflater.from(context);
    }



    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return createViewHoler(parent,viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder,  int position) {
        bindViewHoler((V) holder,position);
    }

    public abstract void bindViewHoler(V holder,  int position);
    public abstract V createViewHoler(ViewGroup parent, int viewType);

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public T getItem(int position)
    {
        return  datas.get(position);
    }
}
