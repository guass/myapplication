package com.guass.navapp.fragment;

import android.graphics.Rect;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.guass.navapp.R;
import com.guass.navapp.adapter.ListbaseAdapter;
import com.guass.navapp.base.BaseFragment;

import com.guass.navapp.base.BaseHolder;
import com.guass.navapp.bean.SubjectInfo;
import com.guass.navapp.decoration.SpaceItemDecoration;
import com.guass.navapp.holder.SubjectViewHolder;
import com.guass.navapp.protocol.SubjectProtocol;
import com.guass.navapp.utils.Utils;
import com.guass.navapp.view.LoadingPage;

import java.util.List;

/**
 * Created by guass on 2016/3/14.
 */
public class SubjectFragment extends BaseFragment {
//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        TextView textView = new TextView(Utils.getContext());
//        textView.setText("Rank");
//        return textView;
//    }
    private List<SubjectInfo> datas;

    @Override
    public View creatViewSuccess() {

        RecyclerView recyclerView = new RecyclerView(Utils.getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Utils.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setAdapter(new ListbaseAdapter(Utils.getContext(), datas) {
            @Override
            public BaseHolder createRealViewHolder(ViewGroup parent, int viewType) {
                return new SubjectViewHolder(mLayoutInflater.inflate(R.layout.subject_item_view,parent,false));
            }

            @Override
            public BaseHolder headerViewHolder(ViewGroup parent, int viewType) {
                return null;
            }

            @Override
            public boolean isHasHeader() {
                return false;
            }

            @Override
            protected List onLoad() {
                return null;
            }
        });
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        int dimensionPixelSize = Utils.getContext().getResources().getDimensionPixelSize(R.dimen.subject_card_space);
        recyclerView.addItemDecoration(new SpaceItemDecoration(dimensionPixelSize));
        return recyclerView;
    }

    @Override
    public LoadingPage.LoadResult load() {
        SubjectProtocol subjectProtocol = new SubjectProtocol();
        datas = subjectProtocol.load(0);
        return checkData(datas);
    }

    class SpaceItemDecoration extends RecyclerView.ItemDecoration {
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

}
