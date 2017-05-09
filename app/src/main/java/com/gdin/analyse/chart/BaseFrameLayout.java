package com.gdin.analyse.chart;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;


public abstract class BaseFrameLayout extends FrameLayout {
    public BaseFrameLayout(Context context) {
        super(context);
        initView();
        initListener();
    }

    public BaseFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
        initListener();
    }

    public BaseFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    protected abstract void initView();
    protected abstract void initListener();

}
