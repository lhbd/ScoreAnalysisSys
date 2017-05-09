package com.gdin.analyse.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.gdin.analyse.R;
import com.gdin.analyse.entity.ExamDataEntity;

import java.util.List;

public class ExamDialogAdapter extends BaseQuickAdapter<ExamDataEntity,BaseViewHolder> {

    public ExamDialogAdapter(int layoutResId, List<ExamDataEntity> data) {
        super(layoutResId, data);
    }

    public ExamDialogAdapter(List<ExamDataEntity> data) {
        super(data);
    }

    public ExamDialogAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder holder, ExamDataEntity item) {
        holder.setText(R.id.exam_dialog_item,item.getExamName());
    }
}
