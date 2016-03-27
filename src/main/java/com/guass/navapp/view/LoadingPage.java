package com.guass.navapp.view;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.guass.navapp.R;
import com.guass.navapp.thread.ThreadManager;
import com.guass.navapp.utils.Utils;

/**
 * Created by guass on 2016/3/14.
 */
public abstract class LoadingPage extends FrameLayout {

    public static final int STATE_UNKOWN = 0;
    public static final int STATE_LOADING = 1;
    public static final int STATE_ERROR = 2;
    public static final int STATE_EMPTY = 3;
    public static final int STATE_SUCCESS = 4;
    public int state = STATE_UNKOWN;

    private View mLoadingView;
    private View mErrorView;
    private View mSuccessView;
    private View mEmptyView;
    private Button mBtn_error;

    public LoadingPage(Context context) {
     //   super(context);
        this(context,null);
    }

    public LoadingPage(Context context, AttributeSet attrs) {
   //     super(context, attrs);
        this(context,null,0);
    }

    public LoadingPage(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();


    }

    public void init()
    {
        checkData();
        mLoadingView = creatLoadingView();
        mEmptyView = creatEmptyView();
        mErrorView = creatErrorView();

        mBtn_error.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mErrorView.setVisibility(View.INVISIBLE);
                init();
            }
        });


        if(mLoadingView != null)
        {
            addView(mLoadingView, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        }

        if(mEmptyView != null)
        {
            addView(mEmptyView, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        }

        if(mErrorView != null)
        {
            addView(mErrorView, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        }

        mEmptyView.setVisibility(View.INVISIBLE);
        mErrorView.setVisibility(View.INVISIBLE);
    }

    public abstract View creatSuccessView();
    public abstract LoadResult load();


    public View creatErrorView()
    {
        View view = Utils.inflate(R.layout.error_page);
        mBtn_error = (Button) view.findViewById(R.id.btn_error_page);
        return  view;
    }

    public View creatLoadingView()
    {
        return Utils.inflate(R.layout.loading_page);
    }

    public View creatEmptyView()
    {
        return  Utils.inflate(R.layout.empty_page);
    }




    public enum LoadResult {
        error(2), empty(3), success(4);

        int value;

        LoadResult(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

    }



    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            Log.i("jiguang","msg msg ==" + msg.what);

            switch (msg.what)
            {
                case STATE_ERROR:
                        mLoadingView.setVisibility(View.INVISIBLE);
                        mErrorView.setVisibility(View.VISIBLE);
                    break;
                case STATE_EMPTY:
                        mLoadingView.setVisibility(View.INVISIBLE);
                        mEmptyView.setVisibility(View.VISIBLE);
                    break;
                case STATE_SUCCESS:
                        mLoadingView.setVisibility(View.INVISIBLE);
                        showSuccessView();
                    break;
            }

        }


    };

    public void showSuccessView()
    {
        mSuccessView = creatSuccessView();
        if(mSuccessView != null)
        {
            addView(mSuccessView, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        }
    }

    public void checkData()
    {

        ThreadManager.getInstance().creatLongPool().execute(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(1000);
                LoadResult loadResult = load();

                if(loadResult != null)
                {
                    int value = loadResult.getValue();
                    Message message = handler.obtainMessage();
                    message.what = value;
                    handler.sendMessage(message);
                }
            }
        });

    }


}
