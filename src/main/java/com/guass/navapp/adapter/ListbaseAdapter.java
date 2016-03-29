package com.guass.navapp.adapter;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import android.view.ViewGroup;

import com.guass.navapp.R;
import com.guass.navapp.base.BaseAdapter;
import com.guass.navapp.base.BaseHolder;
import com.guass.navapp.holder.MoreViewHolder;
import com.guass.navapp.thread.ThreadManager;
import com.guass.navapp.utils.Utils;

import java.util.List;

/**
 * Created by guass on 2016/3/26.
 */
public abstract class ListbaseAdapter <T extends  BaseHolder> extends BaseAdapter {

    private static final int TYPE_ITEM = 0;
    private static final int TYPE_MORE = 1;
    private static final int TYPE_HEADER = 2;


    public  MoreViewHolder moreHolder;
    public int morePsition;

    Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what == 0x12)
            {
                notifyDataSetChanged();
            }
        }
    };
    public ListbaseAdapter(Context context, List<T> datas) {


        super(context, datas);
    }

    @Override
    public void bindViewHoler(BaseHolder holder, int position) {

        if(!(holder instanceof MoreViewHolder))
        {
            holder.setDatas(datas,position);
        }

    }

    @Override
    public BaseHolder createViewHoler(ViewGroup parent, int viewType)
    {
        if(viewType == TYPE_HEADER)
        {
            return headerViewHolder(parent, viewType);
        }

        if(viewType == TYPE_MORE)
        {

            if(moreHolder == null)
            {
                moreHolder = new MoreViewHolder(mLayoutInflater.inflate(R.layout.load_more,parent,false));

            }
            loadMore(morePsition);
            return moreHolder;
        }

            return createRealViewHolder(parent, viewType);
    }

    public void loadMore(final int posioion)
    {
        ThreadManager.getInstance().creatLongPool().execute(new Runnable() {
            @Override
            public void run()
            {
            //    SystemClock.sleep(1000);
                Log.d("jiguang", "run: bengin more");
                List<T>  newDatas = onLoad();
                if (newDatas == null)
                {
                     // connect server error
                    moreHolder.setStatu(MoreViewHolder.LOAD_ERROR, posioion);

                }
                else if(newDatas.size() == 0)
                {
                        // no more else datas
                    moreHolder.setStatu(MoreViewHolder.NO_DATAS, posioion);
                }
                else
                {
                    //load success
                    moreHolder.setStatu(MoreViewHolder.HAS_DATAS, posioion);
                    datas.addAll(newDatas);


                    Message msg = Message.obtain();
                    msg.what = 0x12;
                    mHandler.sendMessageDelayed(msg,1000);
                }

            }
        });
    }

    /**
     * loadMore datas
     */
    protected abstract List<T> onLoad();


    @Override
    public int getItemCount() {
        return datas.size() + 1;
    }

    @Override
    public int getItemViewType(int position)
    {
        if(isHasHeader() && position == 0)
        {
            return TYPE_HEADER;
        }

        if (position + 1 == getItemCount())
        {
            morePsition = position;
            return TYPE_MORE;
        } else {
            return TYPE_ITEM;
        }
    }

    public  abstract T createRealViewHolder(ViewGroup parent, int viewType);
    public  abstract T headerViewHolder(ViewGroup parent, int viewType);
    public  abstract boolean isHasHeader();
}
