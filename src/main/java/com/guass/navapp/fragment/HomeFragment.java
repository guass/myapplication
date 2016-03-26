package com.guass.navapp.fragment;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.guass.navapp.adapter.HomeAdapter;
import com.guass.navapp.base.BaseFragment;
import com.guass.navapp.bean.AppInfo;
import com.guass.navapp.protocol.HomeProtocol;
import com.guass.navapp.utils.Utils;
import com.guass.navapp.view.LoadingPage;

import java.util.List;

/**
 * Created by guass on 2016/3/14.
 */
public class HomeFragment extends BaseFragment {


    private List<AppInfo> datas;


    @Override
    public View creatViewSuccess() {
//        ListView listView = new ListView(Utils.getContext());
//        listView.setAdapter(new HomeAdapterBak());
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
