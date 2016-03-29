package com.guass.navapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.guass.navapp.R;
import com.guass.navapp.adapter.ListbaseAdapter;
import com.guass.navapp.base.BaseFragment;
import com.guass.navapp.base.BaseHolder;
import com.guass.navapp.bean.AppInfo;
import com.guass.navapp.holder.HomeViewHolder;
import com.guass.navapp.protocol.GameProtocol;
import com.guass.navapp.utils.Utils;
import com.guass.navapp.view.LoadingPage;

import java.util.List;

/**
 * Created by guass on 2016/3/14.
 */
public class GameFragment extends BaseFragment {

    private List<AppInfo> datas;

    @Override
    public View creatViewSuccess() {
        RecyclerView recyclerView = new RecyclerView(Utils.getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Utils.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);


        recyclerView.setAdapter(new ListbaseAdapter(Utils.getContext(),datas) {
            @Override
            public BaseHolder createRealViewHolder(ViewGroup parent, int viewType) {
                return new HomeViewHolder(mLayoutInflater.inflate(R.layout.home_item_view,parent,false));
            }

            @Override
            public BaseHolder headerViewHolder(ViewGroup parent, int viewType) {
                return null;
            }

            @Override
            public boolean isHasHeader() {
                return false;
            }

            @Override
            protected List onLoad() {
                GameProtocol gameProtocol = new GameProtocol();
                List<AppInfo> load  = gameProtocol.load(datas.size());
                datas.addAll(load);
                return load;
            }
        });
        recyclerView.setItemAnimator(new DefaultItemAnimator());


        return recyclerView;
    }

    @Override
    public LoadingPage.LoadResult load() {
        GameProtocol gameProtocol = new GameProtocol();
        datas  = gameProtocol.load(0);
        return checkData(datas);
    }
}
