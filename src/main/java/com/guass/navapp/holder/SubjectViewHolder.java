package com.guass.navapp.holder;

import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.guass.navapp.R;
import com.guass.navapp.base.BaseHolder;
import com.guass.navapp.bean.SubjectInfo;
import com.guass.navapp.http.HttpHelper;
import com.guass.navapp.utils.Utils;

/**
 * Created by guass on 2016/3/25.
 */
public class SubjectViewHolder extends BaseHolder<SubjectInfo> {
    CardView mCardView;
    ImageView mImageView;
    TextView mTextView;

    public SubjectViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void intView()
    {
        mCardView = (CardView) itemView.findViewById(R.id.sub_cv_item);
        mImageView = (ImageView) itemView.findViewById(R.id.sub_iv_pic);
        mTextView = (TextView) itemView.findViewById(R.id.sub_tv_text);
    }

    @Override
    public void refreshView(final int position)
    {
        Log.e("jiguang", "refreshView: datas_holer === " + datas_holder );
        mTextView.setText(datas_holder.get(position).getDes());
        bitmapUtils.display(mImageView,  HttpHelper.URL+"image?name=" + datas_holder.get(position).getUrl());
        mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Utils.getContext(),"subject position =" + position,Toast.LENGTH_SHORT).show();;
            }
        });
    }


}
