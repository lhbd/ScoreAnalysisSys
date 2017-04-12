package com.gdin.analyse.model.register;

import java.util.List;

public interface RegisterModel {

    void initData();
    List<String> getSchoolData();
    List<String> getGradeName();
    List<String> getClassName();

    int getSchoolId(int pos);
    int getGradeId(int pos);
    int getClassId(int pos);
}
