package com.guass.navapp.activity;

import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.TextView;

import com.guass.navapp.R;
import com.guass.navapp.base.BaseActivity;
import com.guass.navapp.bean.AppInfo;
import com.guass.navapp.holder.DetailAppInfoHolder;
import com.guass.navapp.holder.DetailScreenHolder;
import com.guass.navapp.protocol.DetailProtocol;
import com.guass.navapp.protocol.HomeProtocol;
import com.guass.navapp.utils.Utils;
import com.guass.navapp.view.LoadingPage;

import java.util.List;

/**
 * Created by guass on 2016/4/3.
 */
public class DetailActivity extends BaseActivity {

    private AppInfo datas;
    private String packageName;

    private FrameLayout detail_bottom;
    private FrameLayout detail_info;
    private FrameLayout detail_safe;
    private FrameLayout detail_des;
    private HorizontalScrollView detail_screen;


    @Override
    public void initView() {




        Intent intent = getIntent();
        packageName = intent.getStringExtra("packageName");
        super.initView();


        LoadingPage loadingPage = new LoadingPage(this) {
            @Override
            public View creatSuccessView()
            {
                return DetailActivity.this.creatSuccessView();
            }

            @Override
            public LoadResult load()
            {
                return DetailActivity.this.load();
            }
        };

        setContentView(loadingPage);

    }



    private LoadingPage.LoadResult load()
    {
        DetailProtocol detailProtocol = new DetailProtocol(packageName);
        datas = detailProtocol.load(0);

        return checkData(datas);
    }

    private View creatSuccessView()
    {


        View view = Utils.inflate(R.layout.activity_detail);

        detail_bottom = (FrameLayout) view.findViewById(R.id.detail_bottom_layout);
        detail_info = (FrameLayout) view.findViewById(R.id.detail_info);
        detail_safe = (FrameLayout) view.findViewById(R.id.detail_safe);
        detail_des = (FrameLayout) view.findViewById(R.id.detail_des);
        detail_screen = (HorizontalScrollView) view.findViewById(R.id.detail_screen);

        //~~
        DetailAppInfoHolder detailAppInfoHolder = new DetailAppInfoHolder(datas);
        View v = detailAppInfoHolder.getConvertView();
        detail_info.addView(v);

        //~~
        DetailScreenHolder detailScreenHolder = new DetailScreenHolder(datas);
        View viewScreen = detailScreenHolder.getConvertView();
        detail_screen.addView(viewScreen);

        return view;
    }


    protected LoadingPage.LoadResult checkData(AppInfo load)
    {
        if(load == null)
        {
            return LoadingPage.LoadResult.error;
        }
        else
        {

                return LoadingPage.LoadResult.success;

        }
    }

}
