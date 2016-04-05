package com.guass.navapp.holder;

import android.view.View;
import android.widget.ImageView;

import com.guass.navapp.R;
import com.guass.navapp.bean.AppInfo;
import com.guass.navapp.http.HttpHelper;
import com.guass.navapp.utils.BitmapHelper;
import com.guass.navapp.utils.Utils;
import com.lidroid.xutils.BitmapUtils;

import java.util.List;

/**
 * Created by guass on 2016/4/5.
 */
public class DetailScreenHolder  extends DetailBaseHolder{


    private ImageView[]  mImageView;

    public DetailScreenHolder(AppInfo datas) {
        super(datas);
    }

    @Override
    public void intView() {
        mView = Utils.inflate(R.layout.detail_screen);
        mImageView = new ImageView[5];
        mImageView[0] = (ImageView) mView.findViewById(R.id.screen_1);
        mImageView[1] = (ImageView) mView.findViewById(R.id.screen_2);
        mImageView[2] = (ImageView) mView.findViewById(R.id.screen_3);
        mImageView[3] = (ImageView) mView.findViewById(R.id.screen_4);
        mImageView[4] = (ImageView) mView.findViewById(R.id.screen_5);
    }

    @Override
    public void refreshView() {
        List<String> screen = datas.getScreen();

        for (int i = 0; i < 5; i++)
        {
            if(i < screen.size())
            {
                mImageView[i].setVisibility(View.VISIBLE);
                BitmapUtils bitmapUtils = BitmapHelper.getBitmapUtils();
                bitmapUtils.display(mImageView[i], HttpHelper.URL+"image?name=" + screen.get(i));

            }
            else
            {
                mImageView[i].setVisibility(View.GONE);
            }
        }
    }

    @Override
    public View getConvertView() {
        return mView;
    }

}
