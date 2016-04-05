package com.guass.navapp.decoration;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by guass on 2016/3/30.
 */
public class SpaceItemDecoration extends RecyclerView.ItemDecoration {
    private int space;

    SpaceItemDecoration(int space)
    {
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        if(parent.getChildPosition(view) != 0)
            outRect.top = space;
    }
}
