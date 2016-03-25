package com.guass.navapp.fragment;

import android.graphics.Color;
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

import com.guass.navapp.adapter.SubjectAdapter;
import com.guass.navapp.base.BaseFragment;

import com.guass.navapp.bean.SubjectInfo;
import com.guass.navapp.protocol.SubjectProtocol;
import com.guass.navapp.utils.Utils;
import com.guass.navapp.view.LoadingPage;

import java.util.List;

/**
 * Created by guass on 2016/3/14.
 */
public class SubjectFragment extends BaseFragment {
//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        TextView textView = new TextView(Utils.getContext());
//        textView.setText("Rank");
//        return textView;
//    }
    private List<SubjectInfo> datas;

    @Override
    public View creatViewSuccess() {

        RecyclerView recyclerView = new RecyclerView(Utils.getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Utils.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(new SubjectAdapter(Utils.getContext(),datas));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        return recyclerView;
    }

    @Override
    public LoadingPage.LoadResult load() {
        SubjectProtocol subjectProtocol = new SubjectProtocol();
        datas = subjectProtocol.load(0);
        return checkData(datas);
    }





    private LoadingPage.LoadResult checkData(List<SubjectInfo> load)
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
