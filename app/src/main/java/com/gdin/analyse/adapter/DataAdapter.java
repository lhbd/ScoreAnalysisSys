package com.gdin.analyse.adapter;


import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.gdin.analyse.R;
import com.gdin.analyse.entity.ClassScoreEntity;

import java.util.List;

public  class DataAdapter extends BaseQuickAdapter<ClassScoreEntity,BaseViewHolder> {

    private int size;

    public DataAdapter(int layoutResId, List<ClassScoreEntity> data) {
        super(layoutResId, data);
        size = data.size()-1;
    }

    public DataAdapter(List<ClassScoreEntity> data) {
        super(data);
    }

    public DataAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder holder, ClassScoreEntity item) {
        int pos = holder.getAdapterPosition();
        if (pos == 0 || pos % 5 == 0) {
            holder.setText(R.id.class_score_max_string,item.getMaxString());
            if (pos == 0)
                holder.getView(R.id.class_score_bottom_link).setVisibility(View.INVISIBLE);
        }else{
            holder.itemView.setPadding(100,0,0,0);
            holder.getView(R.id.class_score_bottom_link).setVisibility(View.INVISIBLE);
            if ((pos-pos/5)%2 == 0){
                holder.itemView.setPadding(100,0,0,0);
                holder.setText(R.id.class_score_max_string,item.getMaxString());
                holder.setText(R.id.class_score_max_value,item.getMaxValue());
                holder.setText(R.id.class_score_avg_string,item.getAvgString());
                holder.setText(R.id.class_score_avg_value,item.getAvgValue());
            }else{
                holder.itemView.setPadding(80,0,0,0);
                holder.setText(R.id.class_score_max_string,item.getMaxString());
            }
        }
        if (holder.getAdapterPosition()==size){
            holder.itemView.setPadding(100,0,0,30);
        }

    }
}
