package com.gdin.analyse.listener;

import android.text.Editable;
import android.text.TextWatcher;

public abstract class EditWatcherListener implements TextWatcher {
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    @Override
    public void afterTextChanged(Editable s) {
        notifyData();
    }

    public abstract void notifyData();
}
