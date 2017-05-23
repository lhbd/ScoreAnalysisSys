package com.gdin.analyse.adapter;


import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.gdin.analyse.R;
import com.gdin.analyse.entity.StudentRollSortResultEntity;

import java.util.List;

public  class StudentRollSortAdapter extends BaseQuickAdapter<StudentRollSortResultEntity,BaseViewHolder> {

    public onClickForDetailListener listener;
    public void setonClickForDetailListener(onClickForDetailListener listener){
        this.listener = listener;
    }
    public interface onClickForDetailListener{
        void onClickForDetail(int pos);
    }

    public StudentRollSortAdapter(int layoutResId, List<StudentRollSortResultEntity> data) {
        super(layoutResId, data);
    }

    public StudentRollSortAdapter(List<StudentRollSortResultEntity> data) {
        super(data);
    }

    public StudentRollSortAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder holder, StudentRollSortResultEntity item) {

        holder.setText(R.id.s_name,item.getStuName());
        holder.setText(R.id.s_id,item.getStuNum()+"");
        holder.setText(R.id.s_score_value,item.getSumScore()+"");
        holder.setText(R.id.s_ch_value,item.getChScore()+"");
        holder.setText(R.id.s_math_value,item.getMathScore()+"");
        holder.setText(R.id.s_en_value,item.getEnScore()+"");
        holder.setText(R.id.s_grade_rank_value,item.getSumRank()+"");
        holder.setText(R.id.s_class_rank_value,item.getSumRank()+"");

        final int pos = holder.getAdapterPosition();
        holder.getView(R.id.show_detail_label).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener==null)
                    return;
                listener.onClickForDetail(pos);
            }
        });
    }
}
