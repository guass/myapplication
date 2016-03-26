package com.guass.navapp.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.guass.navapp.utils.BitmapHelper;
import com.lidroid.xutils.BitmapUtils;

import java.util.List;

/**
 * Created by guass on 2016/3/25.
 */
public abstract  class BaseHolder <T> extends RecyclerView.ViewHolder  {
    protected BitmapUtils bitmapUtils;
    protected List<T> datas_holder;

    public List<T> getDatas()
    {
        return datas_holder;
    }

    public BaseHolder(View itemView)
    {
        super(itemView);
        bitmapUtils = BitmapHelper.getBitmapUtils();
        intView();
    }

    public void setDatas(List<T> datas, int position)
    {
            datas_holder = datas;
            refreshView(position);
    }

    public abstract void intView();
    public abstract void refreshView(int position);
}
