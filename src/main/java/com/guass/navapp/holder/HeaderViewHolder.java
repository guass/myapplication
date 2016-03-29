package com.guass.navapp.holder;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.guass.navapp.R;
import com.guass.navapp.base.BaseHolder;
import com.guass.navapp.http.HttpHelper;
import com.guass.navapp.utils.Utils;

import java.util.List;

/**
 * Created by guass on 2016/3/29.
 */
public class HeaderViewHolder extends BaseHolder<String> {
//    ImageView mImageView;
    ViewPager mViewPager;

    private List<String> pictrueUrl;



    public HeaderViewHolder(View itemView) {
        super(itemView);
    }



    public List<String> getPictrueUrl() {
        return pictrueUrl;
    }

    public void setPictrueUrl(List<String> pictrueUrl) {
        Log.d("jiguang", "setPictrueUrl: " + pictrueUrl);
        this.pictrueUrl = pictrueUrl;
    }

    @Override
    public void intView() {
    //    mImageView = (ImageView) itemView.findViewById(R.id.home_header_iv);
        mViewPager = (ViewPager) itemView.findViewById(R.id.home_header_vp);
    }

    @Override
    public void refreshView(int position) {
            mViewPager.setAdapter(new HomeHeaderAdapter());
    }

    class HomeHeaderAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return pictrueUrl.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
          //  super.destroyItem(container, position, object);
            container.removeView((ImageView)object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = new ImageView(Utils.getContext());
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            bitmapUtils.display(imageView, HttpHelper.URL + "image?name=" + pictrueUrl.get(position));

            container.addView(imageView, ViewPager.LayoutParams.MATCH_PARENT);

            return imageView;
        }
    }
}
