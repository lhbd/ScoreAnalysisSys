package com.gdin.analyse.present;

import android.content.Intent;
import android.support.annotation.Nullable;

import com.gdin.analyse.entity.LoginDataEntity;
import com.gdin.analyse.model.login.LoginModel;
import com.gdin.analyse.model.login.LoginModelImpl;
import com.gdin.analyse.view.LoginView;


public class LoginPresent {
    LoginModel model;
    LoginView view;

    public LoginPresent(LoginView loginView) {
        this.view = loginView;
        model= new LoginModelImpl();
        model.initSharedPreferences();
    }
    public void updateCheckState(String value, boolean isChecked){
        model.updateCheckState(value,isChecked);
    }
    public  void saveRegister(StringBuffer buffer, Intent data){
        model.saveRegister(buffer,data);
    }
    public void saveUser(String user,String pwd){
        model.saveUser(user,pwd);
    }
    public  String getString(String key, @Nullable String defValue){
        return model.getString(key,defValue);
    }
    public int getInt(String key, int defValue){
        return model.getInt(key,defValue);
    }

    public boolean isChecked(String value){
        return model.isChecked(value);
    }
    public boolean checkLoginMessage(LoginDataEntity entity) {
        return model.checkLoginMessage(entity);
    }
}
