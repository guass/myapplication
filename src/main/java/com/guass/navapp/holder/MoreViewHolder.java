package com.guass.navapp.holder;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.guass.navapp.R;
import com.guass.navapp.base.BaseHolder;

/**
 * Created by guass on 2016/3/28.
 */
public class MoreViewHolder extends BaseHolder <Integer>{
    public static final int HAS_DATAS = 0;
    public static final int LOAD_ERROR = 1;
    public static final int NO_DATAS = 2;

    public int defaultStatu = NO_DATAS;

    public ProgressBar mProgressBar;
    public TextView mTextView;
    RelativeLayout rl_more;
    RelativeLayout rl_error;

    Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what == LOAD_ERROR)
            {
                rl_error.setVisibility(View.VISIBLE);
            }
            else
            {
                rl_error.setVisibility(View.GONE);
            }
        }

    };

    public MoreViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void intView()
    {
        mProgressBar = (ProgressBar) itemView.findViewById(R.id.progressBar);
        mTextView = (TextView) itemView.findViewById(R.id.text_load_more);
        rl_error = (RelativeLayout) itemView.findViewById(R.id.rl_more_error);
        rl_more = (RelativeLayout) itemView.findViewById(R.id.rl_more_loading);
    }

    @Override
    public void refreshView(int position)
    {
        Log.d("jiguang", "refreshView: more more ore stat" + defaultStatu);
        Log.d("jiguang", "refreshView: more more ore  position " + position);
        Message msg = Message.obtain();
        msg.what = defaultStatu;
        mHandler.sendMessageDelayed(msg, 1000);

    }

    public void setStatu(int status, int position)
    {
        defaultStatu = status;

        refreshView(position);

    }
}
