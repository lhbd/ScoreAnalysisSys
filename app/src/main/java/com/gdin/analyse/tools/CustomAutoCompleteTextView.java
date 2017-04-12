package com.gdin.analyse.tools;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.widget.AutoCompleteTextView;

/**
 * Created by Administrator on 2017/3/29.
 */

public class CustomAutoCompleteTextView extends AutoCompleteTextView{
    public CustomAutoCompleteTextView(Context context) {
        super(context);
    }

    public CustomAutoCompleteTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomAutoCompleteTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * enoughToFilter()是判断输入文字列长度是否满足现实候补列表的要求的方法。
     * onFocusChanged()是当控件获得焦点时让其显示候补列表。
     * @return
     */
    @Override
    public boolean enoughToFilter() {
        return true;
    }

    @Override
    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect);
        performFiltering(getText(), KeyEvent.KEYCODE_UNKNOWN);
    }
}
