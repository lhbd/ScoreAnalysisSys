package com.gdin.analyse.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.gdin.analyse.R;
import com.gdin.analyse.view.TMainView;

public class TMainActivity extends BaseAppCompatActivity implements TMainView {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getToolbarTitle().setText(R.string.login_label_signin);

    }

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    protected void bindView() {

    }
}
