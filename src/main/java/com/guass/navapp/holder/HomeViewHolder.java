package com.guass.navapp.holder;

import android.content.Intent;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.guass.navapp.R;
import com.guass.navapp.activity.DetailActivity;
import com.guass.navapp.base.BaseHolder;
import com.guass.navapp.bean.AppInfo;
import com.guass.navapp.http.HttpHelper;
import com.guass.navapp.utils.Utils;

/**
 * Created by guass on 2016/3/26.
 */
public class HomeViewHolder extends BaseHolder <AppInfo>{

    ImageView item_icon;
    TextView item_title;
    RatingBar item_rating;
    TextView item_size;
    TextView item_bottom;
    CardView item_card;



    public HomeViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void intView() {
        item_icon = (ImageView) itemView.findViewById(R.id.home_item_icon);
        item_title = (TextView) itemView.findViewById(R.id.home_item_title);
        item_rating = (RatingBar) itemView.findViewById(R.id.home_item_rating);
        item_size = (TextView) itemView.findViewById(R.id.home_item_size);
        item_bottom = (TextView) itemView.findViewById(R.id.home_item_bottom);
        item_card = (CardView) itemView.findViewById(R.id.home_cv_item);
    }

    @Override
    public void refreshView(final int position) {
        AppInfo appInfo = datas_holder.get(position);
        item_title.setText(appInfo.getName());
        item_size.setText(android.text.format.Formatter.formatFileSize(Utils.getContext(),appInfo.getSize()));
        item_bottom.setText(appInfo.getDes());
        item_rating.setRating((float) appInfo.getStars());

        if(bitmapUtils != null)
        {
            bitmapUtils.display(item_icon, HttpHelper.URL+"image?name=" + appInfo.getIconUrl());
        }
         item_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
         //       Toast.makeText(Utils.getContext(),"position :" + position, Toast.LENGTH_SHORT).show();
                onInnerClick(position);
            }


         });
    }

    private void onInnerClick(int position)
    {
        Toast.makeText(Utils.getContext(),"position 4444 :" + position, Toast.LENGTH_SHORT).show();
        AppInfo appInfo = getDatas().get(position);
        Intent intent = new Intent(Utils.getContext(), DetailActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Log.d("jiguang", "onInnerClick: packageName == " + appInfo.getPackageName());
        intent.putExtra("packageName",appInfo.getPackageName());
        Utils.getContext().startActivity(intent);
    }
}
