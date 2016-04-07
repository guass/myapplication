package com.guass.navapp.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.guass.navapp.R;

import com.guass.navapp.bean.AppInfo;
import com.guass.navapp.http.HttpHelper;
import com.guass.navapp.utils.BitmapHelper;
import com.guass.navapp.utils.Utils;
import com.lidroid.xutils.BitmapUtils;



/**
 * Created by guass on 2016/4/4.
 */
public class DetailAppInfoHolder  extends DetailBaseHolder{


    private ImageView mImageView;
    private RatingBar mRatingBar;

    private TextView detail_item_title;
    private TextView detail_item_download;
    private TextView detail_item_version;
    private TextView detail_item_date;
    private TextView detail_item_size;

    public DetailAppInfoHolder(AppInfo datas) {
        super(datas);
    }

    @Override
    public void intView()
    {
        mView = Utils.inflate(R.layout.detail_app_info);
        mImageView = (ImageView) mView.findViewById(R.id.item_icon);
        mRatingBar = (RatingBar) mView.findViewById(R.id.item_rating);
        detail_item_title = (TextView) mView.findViewById(R.id.item_title);
        detail_item_download = (TextView) mView.findViewById(R.id.item_download);
        detail_item_version = (TextView) mView.findViewById(R.id.item_version);
        detail_item_date = (TextView) mView.findViewById(R.id.item_date);
        detail_item_size = (TextView) mView.findViewById(R.id.item_size);
    }

    @Override
    public void refreshView() {
        BitmapUtils bitmapUtils = BitmapHelper.getBitmapUtils();
        bitmapUtils.display(mImageView, HttpHelper.URL+"image?name=" + datas.getIconUrl());
        mRatingBar.setRating((float) datas.getStars());
        detail_item_title.setText(datas.getName());
        detail_item_download.setText(datas.getDownLoadNum());
        detail_item_version.setText(datas.getVersion());
        detail_item_date.setText(datas.getDate());

        detail_item_size.setText(android.text.format.Formatter.formatFileSize(Utils.getContext(),datas.getSize()));
    }

    @Override
    public View getConvertView() {
        return mView;
    }

}
