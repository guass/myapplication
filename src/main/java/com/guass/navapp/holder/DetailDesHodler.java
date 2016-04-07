package com.guass.navapp.holder;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.util.Log;
import android.view.View;
import android.view.ViewParent;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.guass.navapp.R;
import com.guass.navapp.bean.AppInfo;
import com.guass.navapp.utils.Utils;

/**
 * Created by guass on 2016/4/7.
 */
public class DetailDesHodler extends DetailBaseHolder {
    private TextView mTextView;
    private ImageView mImageView;
    private TextView mAuthor;

    private boolean isHide = false;
    public DetailDesHodler(AppInfo datas) {
        super(datas);
    }

    @Override
    public void intView() {
        mView = Utils.inflate(R.layout.detail_des);
        mTextView = (TextView) mView.findViewById(R.id.des_content);
        mImageView = (ImageView) mView.findViewById(R.id.des_arrow);
        mAuthor = (TextView) mView.findViewById(R.id.des_author);

        mImageView.setBackgroundResource(R.mipmap.arrow_up);
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int startHeight = 0;
                int endHeight = 0;
                if(isHide)
                {
                    startHeight = getMeasureHeight5();
                    endHeight = getMeasureHeightReal();
                    mImageView.setBackgroundResource(R.mipmap.arrow_up);
                    isHide = false;
                }
                else
                {
                    startHeight = getMeasureHeightReal();
                    endHeight = getMeasureHeight5();
                    mImageView.setBackgroundResource(R.mipmap.arrow_down);
                    isHide = true;
                }

                ValueAnimator va = ValueAnimator.ofInt(startHeight, endHeight);
                va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) mTextView.getLayoutParams();
                        layoutParams.height = (int) animation.getAnimatedValue();

                        mTextView.setLayoutParams(layoutParams);
                    }
                });

                va.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        ScrollView sv = getScrollView(mTextView);
                        sv.scrollTo(0,sv.getMeasuredHeight());
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });
                va.setDuration(300);
                va.start();
            }
        });
    }


    @Override
    public void refreshView()
    {
         mTextView.setText(datas.getDes());
        mAuthor.setText("作者:" + datas.getAuthor());

    }

    public int getMeasureHeightReal()
    {
        mTextView.setMaxLines(1000);
        int measuredWidth = mTextView.getMeasuredWidth();
        int measureSpecWidth = View.MeasureSpec.makeMeasureSpec(measuredWidth, View.MeasureSpec.EXACTLY);
        int mesureSpecHeight = View.MeasureSpec.makeMeasureSpec(1000, View.MeasureSpec.AT_MOST);

        mTextView.measure(measureSpecWidth,mesureSpecHeight);

        return mTextView.getMeasuredHeight();
    }

    public int getMeasureHeight5()
    {

        mTextView.setMaxLines(5);
        int measureWidth = mTextView.getMeasuredWidth();

        int measureSpecWidth = View.MeasureSpec.makeMeasureSpec(measureWidth, View.MeasureSpec.EXACTLY);
        int mesureSpecHeight = View.MeasureSpec.makeMeasureSpec(1000, View.MeasureSpec.AT_MOST);

        mTextView.measure(measureSpecWidth,mesureSpecHeight);

        return mTextView.getMeasuredHeight();
    }

    public ScrollView getScrollView(View v)
    {
        ViewParent parent = v.getParent();

        if(parent instanceof ScrollView)
        {
            return (ScrollView) parent;
        }

        return  getScrollView((View) parent);
    }

    @Override
    public View getConvertView() {
        return mView;
    }
}
