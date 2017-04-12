package com.gdin.analyse.model.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;

import com.gdin.analyse.entity.LoginDataEntity;
import com.gdin.analyse.tools.CustomApplication;

import static android.content.Context.MODE_PRIVATE;

public class LoginModelImpl implements LoginModel {

    private SharedPreferences sp;

    @Override
    public void initSharedPreferences() {
        sp = CustomApplication.getContext().getSharedPreferences("loginUser", MODE_PRIVATE); //context类中使用getSharedPreferences，第一个参数为文件名
    }

    @Override
    public String getString(String key, @Nullable String defValue) {
        return sp.getString(key,defValue);
    }

    @Override
    public int getInt(String key, int defValue) {
        return sp.getInt(key,defValue);
    }

    @Override
    public boolean isChecked(String value) {
        return sp.getBoolean(value, false);
    }

    @Override
    public void saveRegister(StringBuffer buffer, Intent data) {
        sp.edit().putString("userMessage", buffer.toString()).apply();
        sp.edit().putInt("loginSchoolId",data.getIntExtra("loginSchoolId",0)).apply();
        sp.edit().putInt("loginGradeId",data.getIntExtra("loginGradeId",0)).apply();
        sp.edit().putInt("loginClassId",data.getIntExtra("loginClassId",0)).apply();
        sp.edit().putString("loginType",data.getStringExtra("loginType")).apply();
    }

    @Override
    public void saveUser(String user,String pwd) {
        sp.edit().putString("user", user).apply();
        sp.edit().putString("pwd", pwd).apply();
    }

    @Override
    public void updateCheckState(String value, boolean isChecked) {
        sp.edit().putBoolean(value, isChecked).apply();

    }

    @Override
    public boolean checkLoginMessage(LoginDataEntity entity) {

        return false;
    }
}
