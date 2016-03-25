package com.guass.navapp.fragment;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.guass.navapp.R;
import com.guass.navapp.adapter.HomeAdapter;
import com.guass.navapp.base.BaseFragment;
import com.guass.navapp.bean.AppInfo;
import com.guass.navapp.http.HttpHelper;
import com.guass.navapp.protocol.HomeProtocol;
import com.guass.navapp.utils.BitmapHelper;
import com.guass.navapp.utils.Utils;
import com.guass.navapp.view.LoadingPage;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.bitmap.PauseOnScrollListener;

import java.util.List;

/**
 * Created by guass on 2016/3/14.
 */
public class HomeFragment extends BaseFragment {


    private List<AppInfo> datas;
    private BitmapUtils bitmapUtils;

    @Override
    public View creatViewSuccess() {
//        ListView listView = new ListView(Utils.getContext());
//        listView.setAdapter(new HomeAdapter());
//        bitmapUtils = BitmapHelper.getBitmapUtils();
//        listView.setOnScrollListener(new PauseOnScrollListener(bitmapUtils,false,true));
//
//        bitmapUtils.configDefaultLoadingImage(R.mipmap.ic_default);
//        bitmapUtils.configDefaultLoadFailedImage(R.mipmap.ic_default);
        RecyclerView recyclerView = new RecyclerView(Utils.getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Utils.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(new HomeAdapter(Utils.getContext(),datas));
        recyclerView.setItemAnimator(new DefaultItemAnimator());


        return recyclerView;
    }

    class HomeAdapter2 extends BaseAdapter{

        @Override
        public int getCount() {
     //       return datas.size();
            return datas.size();
        }

        @Override
        public AppInfo getItem(int position) {
            return datas.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            HomeViewHolder homeViewHolder;
            if(convertView == null)
            {
                convertView = View.inflate(Utils.getContext(),R.layout.item_app,null);
                homeViewHolder = new HomeViewHolder();
                homeViewHolder.item_icon  = (ImageView) convertView.findViewById(R.id.item_icon);
                homeViewHolder.item_title = (TextView) convertView.findViewById(R.id.item_title);
                homeViewHolder.item_rating = (RatingBar) convertView.findViewById(R.id.item_rating);
                homeViewHolder.item_size = (TextView) convertView.findViewById(R.id.item_size);
                homeViewHolder.item_bottom = (TextView) convertView.findViewById(R.id.item_bottom);

                convertView.setTag(homeViewHolder);
            }
            else
            {
                    homeViewHolder = (HomeViewHolder) convertView.getTag();
            }

            AppInfo appInfo = getItem(position);
            homeViewHolder.item_title.setText(appInfo.getName());
            homeViewHolder.item_size.setText(android.text.format.Formatter.formatFileSize(Utils.getContext(),appInfo.getSize()));
            homeViewHolder.item_bottom.setText(appInfo.getDes());
            homeViewHolder.item_rating.setRating((float) appInfo.getStars());


         //   BitmapUtils bitmapUtils = new BitmapUtils(Utils.getContext(), FileUtils.getIconDir().getAbsolutePath(),0.5f);
    //        BitmapUtils bitmapUtils = BitmapHelper.getBitmapUtils();
            if(bitmapUtils != null)
            {
                bitmapUtils.display(homeViewHolder.item_icon, HttpHelper.URL+"image?name=" + appInfo.getIconUrl());
            }

            return convertView;
        }
    }


    static  class HomeViewHolder{
          ImageView item_icon;
          TextView item_title;
          RatingBar item_rating;
          TextView item_size;
        TextView item_bottom;

    }


    @Override
    public LoadingPage.LoadResult load() {

        HomeProtocol homeProtocol = new HomeProtocol();
        datas = homeProtocol.load(0);

        return checkData(datas);
    }

    private LoadingPage.LoadResult checkData(List<AppInfo> load)
    {
        if(load == null)
        {
            return LoadingPage.LoadResult.error;
        }
        else
        {
            if(load.size() == 0)
            {
                return LoadingPage.LoadResult.empty;
            }
            else
            {
                return LoadingPage.LoadResult.success;
            }
        }
    }


}
