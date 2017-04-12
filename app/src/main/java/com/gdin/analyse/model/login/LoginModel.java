package com.gdin.analyse.model.login;

import android.content.Intent;
import android.support.annotation.Nullable;

import com.gdin.analyse.entity.LoginDataEntity;

public interface LoginModel {
      void initSharedPreferences();
      void saveRegister(StringBuffer buffer, Intent data);
      void saveUser(String user,String pwd);
      void updateCheckState(String value, boolean isChecked);
      String getString(String key, @Nullable String defValue);
      int getInt(String key, int defValue);
      boolean isChecked(String value);
      boolean checkLoginMessage(LoginDataEntity entity) ;

}
