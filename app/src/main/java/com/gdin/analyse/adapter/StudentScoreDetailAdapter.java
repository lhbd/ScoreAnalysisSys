package com.gdin.analyse.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.gdin.analyse.entity.StudentScoreDetailEntity;

import java.util.List;

public class StudentScoreDetailAdapter extends BaseQuickAdapter<StudentScoreDetailEntity,BaseViewHolder> {

    public StudentScoreDetailAdapter(int layoutResId, List<StudentScoreDetailEntity> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, StudentScoreDetailEntity item) {

    }
}
