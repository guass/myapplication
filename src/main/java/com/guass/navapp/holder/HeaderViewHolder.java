package com.guass.navapp.holder;

import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.guass.navapp.R;
import com.guass.navapp.base.BaseHolder;
import com.guass.navapp.http.HttpHelper;
import com.guass.navapp.utils.Utils;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by guass on 2016/3/29.
 */
public class HeaderViewHolder extends BaseHolder<String> {
//    ImageView mImageView;
    ViewPager mViewPager;

    private List<String> pictrueUrl;

    Handler mHandler = new Handler();

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
        mViewPager.setCurrentItem(1000);

        final AutoRunTask autoRunTask = new AutoRunTask();
        autoRunTask.start();

        mViewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                switch (action)
                {
                    case MotionEvent.ACTION_DOWN:
                        autoRunTask.stop();
                        break;
                    case MotionEvent.ACTION_CANCEL:

                    case MotionEvent.ACTION_UP:
                        autoRunTask.start();
                        break;

                }
                return false;
            }
        });
    }

    boolean flag;
    class AutoRunTask implements Runnable{

        @Override
        public void run()
        {
            if(flag)
            {
                mHandler.removeCallbacks(this);
                int currentItem = mViewPager.getCurrentItem();
                currentItem++;
                mViewPager.setCurrentItem(currentItem);
                mHandler.postDelayed(this,3000);
            }

        }

        public void start()
        {
            if(flag)
            {
                return;
            }
            mHandler.removeCallbacks(this);
            flag = true;
            mHandler.postDelayed(this,3000);
        }

        public void stop()
        {
            if(flag)
            {
                flag = false;
                mHandler.removeCallbacks(this);
            }

        }
    }

    class HomeHeaderAdapter extends PagerAdapter{

        LinkedList<ImageView> convertView = new LinkedList<ImageView>();

        @Override
        public int getCount() {
         //   return pictrueUrl.size();
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
          //  super.destroyItem(container, position, object);
            ImageView imageView = (ImageView) object;
            convertView.add(imageView);
            container.removeView(imageView);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            int index = position % pictrueUrl.size();
            ImageView imageView;
            if(convertView.size() > 0)
            {
                imageView = convertView.remove(0);
            }
            else
            {
                imageView = new ImageView(Utils.getContext());
            }

            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            bitmapUtils.display(imageView, HttpHelper.URL + "image?name=" + pictrueUrl.get(index));

            container.addView(imageView, ViewPager.LayoutParams.MATCH_PARENT);

            return imageView;
        }
    }
}
