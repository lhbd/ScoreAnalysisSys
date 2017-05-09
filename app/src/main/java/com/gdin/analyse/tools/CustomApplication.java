package com.gdin.analyse.tools;

import android.app.Application;
import android.content.Context;

public class CustomApplication extends Application {
    private static Context context;
    private static int tokenId=3;
    private static int examId=12;
    private static String examName = "234";
    private static int schoolId;
    private static int gradeId;
    private static int classId;

    @Override
    public void onCreate() {
        //获取Context
        context = getApplicationContext();
    }

    //返回
    public static Context getContext(){
        return context;
    }

    public static int getTokenId() {
        return tokenId;
    }

    public static int getSchoolId() {
        return schoolId;
    }

    public static int getGradeId() {
        return gradeId;
    }

    public static int getClassId() {
        return classId;
    }

    public static int getExamId() {
        return examId;
    }

    public static String getExamName() {
        return examName;
    }

    public static void setExamName(String examName) {
        CustomApplication.examName = examName;
    }

    public static void setExamId(int examId) {
        CustomApplication.examId = examId;
    }

    public static void setTokenId(int tokenId) {
        CustomApplication.tokenId = tokenId;
    }

    public static void setSchoolId(int schoolId) {
        CustomApplication.schoolId = schoolId;
    }

    public static void setGradeId(int gradeId) {
        CustomApplication.gradeId = gradeId;
    }

    public static void setClassId(int classId) {
        CustomApplication.classId = classId;
    }
}
