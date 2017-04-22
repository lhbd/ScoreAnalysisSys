package com.gdin.analyse.adapter;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.gdin.analyse.R;
import com.gdin.analyse.entity.ScoreEntity;

import java.util.List;

public class ScoreAdapter extends BaseMultiItemQuickAdapter<ScoreEntity,BaseViewHolder> {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public ScoreAdapter(List<ScoreEntity> data) {
        super(data);
        addItemType(ScoreEntity.SCORE_DATA, R.layout.t_score_layout);
    }

    @Override
    protected void convert(final BaseViewHolder holder, ScoreEntity item) {
        holder.setText(R.id.t_sNumber,item.getsNumber());
        holder.setText(R.id.t_sName,item.getsName());
        holder.setText(R.id.t_sScore,item.getsScore());
    }
}
