package com.guass.navapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.guass.navapp.base.BaseFragment;
import com.guass.navapp.utils.Utils;
import com.guass.navapp.view.LoadingPage;

/**
 * Created by guass on 2016/3/14.
 */
public class CategrayFragment extends BaseFragment {
//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        TextView textView = new TextView(Utils.getContext());
//        textView.setText("CategrayFragment");
//        return textView;
//    }

    @Override
    public View creatViewSuccess() {
        return null;
    }

    @Override
    public LoadingPage.LoadResult load() {
        return LoadingPage.LoadResult.error;
    }
}
