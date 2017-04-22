package com.gdin.analyse.adapter;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.gdin.analyse.R;
import com.gdin.analyse.entity.CourseScoreDetailEntity;
import com.gdin.analyse.entity.ModuleScoreDetailEntity;

import java.util.List;

public class ScoreDetailAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity,BaseViewHolder> {

    public static final int COURSE_SCORE = 0;
    public static final int MODULE_SCORE = 1;

    public ScoreDetailAdapter(List<MultiItemEntity> data) {
        super(data);
        addItemType(ScoreDetailAdapter.COURSE_SCORE, R.layout.courser_score_detail_layout);
        addItemType(ScoreDetailAdapter.MODULE_SCORE,R.layout.module_score_detail_layout);
    }

    @Override
    protected void convert(BaseViewHolder holder, MultiItemEntity item) {
        switch (holder.getItemViewType()){
            case COURSE_SCORE:
                holder.setText(R.id.course_name,((CourseScoreDetailEntity)item).getcName());
                holder.setText(R.id.course_score,((CourseScoreDetailEntity)item).getcScore()+"");
                break;
            case MODULE_SCORE:
                holder.setText(R.id.module_name,((ModuleScoreDetailEntity)item).getmName());
                holder.setText(R.id.module_score,((ModuleScoreDetailEntity)item).getmScore()+"");
                break;
        }

    }
}
