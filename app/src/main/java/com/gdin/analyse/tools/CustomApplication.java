package com.gdin.analyse.tools;

import android.app.Application;
import android.content.Context;

public class CustomApplication extends Application {
    private static Context context;
    private static int tokenId=1;
    private static int examId=1;
    private static String examName = "5月统考";
    private static int schoolId;
    private static int gradeId;
    private static int classId;
    private static String schoolName;
    private static String gradeName;
    private static String className;

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

    public static String getSchoolName() {
        return schoolName;
    }

    public static String getGradeName() {
        return gradeName;
    }

    public static String getClassName() {
        return className;
    }

    public static void setSchoolName(String schoolName) {
        CustomApplication.schoolName = schoolName;
    }

    public static void setGradeName(String gradeName) {
        CustomApplication.gradeName = gradeName;
    }

    public static void setClassName(String className) {
        CustomApplication.className = className;
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
