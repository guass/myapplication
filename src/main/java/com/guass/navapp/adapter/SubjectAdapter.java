package com.guass.navapp.adapter;
import android.content.Context;
import android.view.ViewGroup;
import com.guass.navapp.R;
import com.guass.navapp.base.BaseAdapter;
import com.guass.navapp.bean.SubjectInfo;
import com.guass.navapp.holder.SubjectViewHolder;
import java.util.List;

/**
 * Created by guass on 2016/3/24.
 */
public class SubjectAdapter extends BaseAdapter<SubjectInfo, SubjectViewHolder> {

    private List<SubjectInfo> datas;

    public SubjectAdapter(Context context, List<SubjectInfo> datas)
    {
        super(context,datas);
        this.datas = datas;
    }

    @Override
    public void bindViewHoler(SubjectViewHolder holder,  int position)
    {

        holder.setDatas(datas,position);
    }

    @Override
    public SubjectViewHolder createViewHoler(ViewGroup parent, int viewType)
    {
        return new SubjectViewHolder(mLayoutInflater.inflate(R.layout.subject_item_view,parent,false));

    }

}
