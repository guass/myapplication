package com.guass.navapp.adapter;

import android.content.Context;
import android.view.ViewGroup;
import com.guass.navapp.R;
import com.guass.navapp.base.BaseAdapter;
import com.guass.navapp.bean.AppInfo;
import com.guass.navapp.holder.HomeViewHolder;

import java.util.List;

/**
 * Created by guass on 2016/3/26.
 */
public class HomeAdapter extends BaseAdapter<AppInfo , HomeViewHolder> {
    private List<AppInfo> datas;

    public HomeAdapter(Context context, List<AppInfo> datas) {
        super(context, datas);
        this.datas = datas;
    }

    @Override
    public void bindViewHoler(HomeViewHolder holder, int position) {
        holder.setDatas(datas,position);
    }


    @Override
    public  HomeViewHolder createViewHoler(ViewGroup parent, int viewType)
    {
        return new HomeViewHolder(mLayoutInflater.inflate(R.layout.home_item_view,parent,false));
    }
}
