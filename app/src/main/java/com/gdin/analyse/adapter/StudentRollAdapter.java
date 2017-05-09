package com.gdin.analyse.adapter;


import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.gdin.analyse.R;
import com.gdin.analyse.entity.StudentRollEntity;

import java.util.List;

public  class StudentRollAdapter extends BaseQuickAdapter<StudentRollEntity,BaseViewHolder> {

    public onClickForDetailListener listener;
    public void setonClickForDetailListener(onClickForDetailListener listener){
        this.listener = listener;
    }
    public interface onClickForDetailListener{
        void onClickForDetail();
    }

    public StudentRollAdapter(int layoutResId, List<StudentRollEntity> data) {
        super(layoutResId, data);
    }

    public StudentRollAdapter(List<StudentRollEntity> data) {
        super(data);
    }

    public StudentRollAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder holder, StudentRollEntity item) {

        holder.setText(R.id.s_name,item.getName());
        holder.setText(R.id.s_id,item.getId()+"");
        holder.setText(R.id.s_score_value,item.getScore());
        holder.setText(R.id.s_ch_value,item.getCh());
        holder.setText(R.id.s_math_value,item.getMath());
        holder.setText(R.id.s_en_value,item.getEn());
        holder.setText(R.id.s_grade_rank_value,item.getgRank());
        holder.setText(R.id.s_class_rank_value,item.getcRank());

        holder.getView(R.id.show_detail_label).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener==null)
                    return;
                listener.onClickForDetail();
            }
        });
    }
}
