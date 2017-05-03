package com.gdin.analyse.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.gdin.analyse.R;
import com.gdin.analyse.entity.UserMessage;

import java.util.List;

public class ExamDialogAdapter extends BaseQuickAdapter<UserMessage,BaseViewHolder> {

    public ExamDialogAdapter(int layoutResId, List<UserMessage> data) {
        super(layoutResId, data);
    }

    public ExamDialogAdapter(List<UserMessage> data) {
        super(data);
    }

    public ExamDialogAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder holder, UserMessage item) {
        holder.setText(R.id.exam_dialog_item,item.getValue());
    }
}
