package com.gdin.analyse.adapter;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.gdin.analyse.R;
import com.gdin.analyse.entity.UserMessage;

import java.util.List;

public class UserMessageAdapter extends BaseQuickAdapter<UserMessage,BaseViewHolder> {

    public UserMessageAdapter(int layoutResId, List<UserMessage> data) {
        super(layoutResId, data);
    }

    public UserMessageAdapter(List data) {
        super(data);
    }

    public UserMessageAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder holder, UserMessage item) {
        holder.setText(R.id.string_tv,item.getString());
        holder.setText(R.id.value_tv,item.getValue());
        if (holder.getAdapterPosition() == 4){
            holder.getView(R.id.bottom_link).setVisibility(View.INVISIBLE);
        }
    }


}
