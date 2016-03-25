package com.guass.navapp.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.guass.navapp.R;
import com.guass.navapp.bean.SubjectInfo;
import com.guass.navapp.http.HttpHelper;
import com.guass.navapp.utils.BitmapHelper;
import com.guass.navapp.utils.Utils;
import com.lidroid.xutils.BitmapUtils;

import java.util.List;

/**
 * Created by guass on 2016/3/24.
 */
public class SubjectAdapter extends RecyclerView.Adapter<SubjectAdapter.SubjectViewHolder> {
    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private List<SubjectInfo> datas;
    private BitmapUtils bitmapUtils;

    String des;
    String url;

    public SubjectAdapter(Context context, List<SubjectInfo> datas)
    {
        mContext = context;

        mLayoutInflater = LayoutInflater.from(context);

        this.datas = datas;
        bitmapUtils = BitmapHelper.getBitmapUtils();

    }

    public  static class SubjectViewHolder extends RecyclerView.ViewHolder{
        CardView mCardView;
        ImageView mImageView;
        TextView mTextView;

        public SubjectViewHolder(View itemView) {
            super(itemView);
            mCardView = (CardView) itemView.findViewById(R.id.sub_cv_item);
            mImageView = (ImageView) itemView.findViewById(R.id.sub_iv_pic);
            mTextView = (TextView) itemView.findViewById(R.id.sub_tv_text);
        }
    }

    @Override
    public SubjectViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SubjectViewHolder(mLayoutInflater.inflate(R.layout.subject_item_view,parent,false));
    }

    @Override
    public void onBindViewHolder(SubjectViewHolder holder, final int position) {
            holder.mTextView.setText(datas.get(position).getDes());
            bitmapUtils.display(holder.mImageView,  HttpHelper.URL+"image?name=" + datas.get(position).getUrl());
            holder.mCardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(Utils.getContext(),"position =" + position,Toast.LENGTH_SHORT).show();;
                }
            });

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }
}
