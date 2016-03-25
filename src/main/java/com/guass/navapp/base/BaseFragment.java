package com.guass.navapp.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.guass.navapp.utils.Utils;
import com.guass.navapp.view.LoadingPage;


/**
 * Created by guass on 2016/3/14.
 */
public abstract class BaseFragment extends Fragment {
    private LoadingPage loadingPage;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

            loadingPage =  new LoadingPage(Utils.getContext()) {
            @Override
            public View creatSuccessView() {
                Log.i("jiguang","creatSuccessView creatSuccessView creatSuccessView!!!");
                return BaseFragment.this.creatViewSuccess();
            }

            @Override
            public LoadResult load() {
                Log.i("jiguang","load load load!!!");
                return BaseFragment.this.load();
            }
        };
        return loadingPage;
    }

    public abstract View creatViewSuccess();

    public abstract LoadingPage.LoadResult load();
}