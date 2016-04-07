package com.guass.navapp.holder;

import android.animation.ValueAnimator;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.guass.navapp.R;
import com.guass.navapp.bean.AppInfo;
import com.guass.navapp.http.HttpHelper;
import com.guass.navapp.utils.BitmapHelper;
import com.guass.navapp.utils.Utils;
import com.lidroid.xutils.BitmapUtils;

/**
 * Created by guass on 2016/4/6.
 */
public class DetailSafeHolder extends DetailBaseHolder {

    private RelativeLayout safeLayout;
    private LinearLayout mLinearLayout;
    private ImageView safe_arrow;

    private ImageView[] iv;
    private ImageView[] desIv;
    private TextView[] desTv;
    private LinearLayout[] mLinearLayout_detail;
    private boolean isHide = false;

    public DetailSafeHolder(AppInfo datas) {
        super(datas);
    }

    @Override
    public void intView() {
        mView = Utils.inflate(R.layout.detail_safe);

        safeLayout = (RelativeLayout) mView.findViewById(R.id.safe_layout);
        mLinearLayout = (LinearLayout) mView.findViewById(R.id.safe_content);
        safe_arrow = (ImageView) mView.findViewById(R.id.safe_arrow);
        safe_arrow.setBackgroundResource(R.mipmap.arrow_up);
        safeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int startHeight;
                int endHeight;
                if(!isHide)
                {
                    startHeight = getMeasureHeight();
                    endHeight = 0;
                    safe_arrow.setBackgroundResource(R.mipmap.arrow_down);
         //           mLinearLayout.setVisibility(View.GONE);
                    isHide = true;
                }
                else
                {
                    startHeight = 0;
                    endHeight = getMeasureHeight();
                    safe_arrow.setBackgroundResource(R.mipmap.arrow_up);
           //         mLinearLayout.setVisibility(View.VISIBLE);
                    isHide = false;
                }

                final RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) mLinearLayout.getLayoutParams();
                ValueAnimator va = ValueAnimator.ofInt(startHeight,endHeight);
                va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        int animatedValue = (int) animation.getAnimatedValue();
                    //    mLinearLayout.getLayoutParams().height = animatedValue;

                        layoutParams.height = animatedValue;
                        mLinearLayout.setLayoutParams(layoutParams);
                    }
                });
                va.setDuration(300);
                va.start();
            }
        });

        iv = new ImageView[4];
        iv[0] = (ImageView) mView.findViewById(R.id.iv_1);
        iv[1] = (ImageView) mView.findViewById(R.id.iv_2);
        iv[2] = (ImageView) mView.findViewById(R.id.iv_3);
        iv[3] = (ImageView) mView.findViewById(R.id.iv_4);

        desIv = new ImageView[4];
        desIv[0] = (ImageView) mView.findViewById(R.id.des_iv_1);
        desIv[1] = (ImageView) mView.findViewById(R.id.des_iv_2);
        desIv[2] = (ImageView) mView.findViewById(R.id.des_iv_3);
        desIv[3] = (ImageView) mView.findViewById(R.id.des_iv_4);

        desTv = new TextView[4];
        desTv[0] = (TextView) mView.findViewById(R.id.des_tv_1);
        desTv[1] = (TextView) mView.findViewById(R.id.des_tv_2);
        desTv[2] = (TextView) mView.findViewById(R.id.des_tv_3);
        desTv[3] = (TextView) mView.findViewById(R.id.des_tv_4);

        mLinearLayout_detail = new LinearLayout[4];
        mLinearLayout_detail[0] = (LinearLayout) mView.findViewById(R.id.des_layout_1);
        mLinearLayout_detail[1] = (LinearLayout) mView.findViewById(R.id.des_layout_2);
        mLinearLayout_detail[2] = (LinearLayout) mView.findViewById(R.id.des_layout_3);
        mLinearLayout_detail[3] = (LinearLayout) mView.findViewById(R.id.des_layout_4);

    }

    @Override
    public void refreshView() {
        BitmapUtils bitmapUtils = BitmapHelper.getBitmapUtils();

        for (int i = 0; i < 4; i++)
        {

            if(i < datas.getSafeDes().size() && i < datas.getSafeDesColor().size()&& i < datas.getSafeDesUrl().size() && i < datas.getSafeUrl().size())
            {
                iv[i].setVisibility(View.VISIBLE);
                desIv[i].setVisibility(View.VISIBLE);
                desTv[i].setVisibility(View.VISIBLE);
                bitmapUtils.display(iv[i], HttpHelper.URL + "image?name=" + datas.getSafeUrl().get(i));
                bitmapUtils.display(desIv[i], HttpHelper.URL + "image?name=" + datas.getSafeDesUrl().get(i));
                desTv[i].setText(datas.getSafeDes().get(i));

                int color;
                int colorType = datas.getSafeDesColor().get(i);
                if (colorType >= 1 && colorType <= 3) {
                    color = Color.rgb(255, 153, 0); // 00 00 00
                } else if (colorType == 4) {
                    color = Color.rgb(0, 177, 62);
                } else {
                    color = Color.rgb(122, 122, 122);
                }
                desTv[i].setTextColor(color);
            }
            else
            {
                iv[i].setVisibility(View.GONE);
                desIv[i].setVisibility(View.GONE);
                desTv[i].setVisibility(View.GONE);
                mLinearLayout_detail[i].setVisibility(View.GONE);
            }
        }
    }

    public int getMeasureHeight()
    {
        int measuredWidth = mLinearLayout.getMeasuredWidth();
    //    mLinearLayout.getLayoutParams().height = ViewGroup.LayoutParams.WRAP_CONTENT;

        int measureSpecWidth = View.MeasureSpec.makeMeasureSpec(measuredWidth,View.MeasureSpec.EXACTLY);
        int measureSpecHeight = View.MeasureSpec.makeMeasureSpec(1000, View.MeasureSpec.AT_MOST);
        mLinearLayout.measure(measureSpecWidth,measureSpecHeight);
        return mLinearLayout.getMeasuredHeight();
    }

    @Override
    public View getConvertView() {
        return mView;
    }
}
