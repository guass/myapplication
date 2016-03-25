package com.guass.navapp.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.guass.navapp.R;
import com.guass.navapp.bean.AppInfo;
import com.guass.navapp.http.HttpHelper;
import com.guass.navapp.utils.BitmapHelper;
import com.guass.navapp.utils.Utils;
import com.lidroid.xutils.BitmapUtils;

import java.util.List;

/**
 * Created by guass on 2016/3/25.
 */
public class HomeAdapter extends RecyclerView.Adapter <HomeAdapter.HomeViewHoler>{
    private  BitmapUtils bitmapUtils;
    private List<AppInfo> datas;
    private Context mContext;
    private LayoutInflater mLayoutInflater;


    public HomeAdapter(Context context, List<AppInfo> datas)
    {
        mContext = context;
        this.datas = datas;
        bitmapUtils = BitmapHelper.getBitmapUtils();
        mLayoutInflater = LayoutInflater.from(context);
    }

    public static class HomeViewHoler extends RecyclerView.ViewHolder{
        ImageView item_icon;
        TextView item_title;
        RatingBar item_rating;
        TextView item_size;
        TextView item_bottom;
        CardView item_card;


        public HomeViewHoler(View itemView) {
            super(itemView);
            item_icon = (ImageView) itemView.findViewById(R.id.home_item_icon);
            item_title = (TextView) itemView.findViewById(R.id.home_item_title);
            item_rating = (RatingBar) itemView.findViewById(R.id.home_item_rating);
            item_size = (TextView) itemView.findViewById(R.id.home_item_size);
            item_bottom = (TextView) itemView.findViewById(R.id.home_item_bottom);
            item_card = (CardView) itemView.findViewById(R.id.home_cv_item);
        }
    }


    @Override
    public HomeViewHoler onCreateViewHolder(ViewGroup parent, int viewType) {
        return new HomeViewHoler(mLayoutInflater.inflate(R.layout.home_item_view,parent,false));
    }

    @Override
    public void onBindViewHolder(HomeViewHoler holder, final int position) {
        AppInfo appInfo = getItem(position);
        holder.item_title.setText(appInfo.getName());
        holder.item_size.setText(android.text.format.Formatter.formatFileSize(Utils.getContext(),appInfo.getSize()));
        holder.item_bottom.setText(appInfo.getDes());
        holder.item_rating.setRating((float) appInfo.getStars());

        if(bitmapUtils != null)
        {
            bitmapUtils.display(holder.item_icon, HttpHelper.URL+"image?name=" + appInfo.getIconUrl());
        }
        holder.item_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext,"position :" + position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public AppInfo getItem(int position)
    {
        return datas.get(position);
    }
}
