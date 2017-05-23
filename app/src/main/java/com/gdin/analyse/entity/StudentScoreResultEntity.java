package com.gdin.analyse.entity;

public class StudentScoreResultEntity {
    private int schoolId;
    private int gradeId;
    private int classId;
    private int stuNum;
    private String stuName;
    private int chScore;
    private int mathScore;
    private int enScore;
    private int sumScore;

    public StudentScoreResultEntity(int stuNum, String stuName, int chScore, int mathScore, int enScore, int sumScore) {
        this.stuNum = stuNum;
        this.stuName = stuName;
        this.chScore = chScore;
        this.mathScore = mathScore;
        this.enScore = enScore;
        this.sumScore = sumScore;
    }

    public int getSchoolId() {
        return schoolId;
    }

    public int getGradeId() {
        return gradeId;
    }

    public int getClassId() {
        return classId;
    }

    public int getStuNum() {
        return stuNum;
    }

    public String getStuName() {
        return stuName;
    }

    public int getChScore() {
        return chScore;
    }

    public int getMathScore() {
        return mathScore;
    }

    public int getEnScore() {
        return enScore;
    }

    public int getSumScore() {
        return sumScore;
    }

    public void setSchoolId(int schoolId) {
        this.schoolId = schoolId;
    }

    public void setGradeId(int gradeId) {
        this.gradeId = gradeId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public void setStuNum(int stuNum) {
        this.stuNum = stuNum;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public void setChScore(int chScore) {
        this.chScore = chScore;
    }

    public void setMathScore(int mathScore) {
        this.mathScore = mathScore;
    }

    public void setEnScore(int enScore) {
        this.enScore = enScore;
    }

    public void setSumScore(int sumScore) {
        this.sumScore = sumScore;
    }
}
