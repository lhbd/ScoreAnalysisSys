package com.gdin.analyse.present;

import com.gdin.analyse.model.register.RegisterModel;
import com.gdin.analyse.model.register.RegisterModelImpl;
import com.gdin.analyse.view.RegisterView;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterPresent {
    RegisterView registerView;
    RegisterModel registerModel;
    List<String> schools = new ArrayList<>();


    public RegisterPresent(RegisterView registerView) {
        this.registerView = registerView;
        registerModel = new RegisterModelImpl();
    }

    public void initData(){
        registerModel.initData();
    }
    public List<String> getSchoolData(){
        schools = registerModel.getSchoolData();
        return schools;
    }
    //实现completeTextView的任意匹配
    public boolean inputEffective(String str){
        Pattern p = Pattern.compile("\\w+");
        Matcher m = p.matcher(str);
        return m.matches() && schools.contains(str);
    }

    public List<String> getGradeData() {
        return registerModel.getGradeName();
    }

    public List<String> getClassData() {
        return registerModel.getClassName();
    }

    public int getSchoolId(int pos){
        return registerModel.getSchoolId(pos);
    }
    public int getGradeId(int pos){
        return registerModel.getGradeId(pos);
    }
    public int getClassId(int pos){
        return registerModel.getClassId(pos);
    }

}
