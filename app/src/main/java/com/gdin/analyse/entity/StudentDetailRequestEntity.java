package com.gdin.analyse.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class StudentDetailRequestEntity implements Parcelable {
    private int examId;
    private String querySource;
    private int stuNum;


    public StudentDetailRequestEntity(int examId, int stuNum) {
        this.examId = examId;
        this.stuNum = stuNum;
    }

    public StudentDetailRequestEntity(int examId, String querySource, int stuNum) {
        this.examId = examId;
        this.querySource = querySource;
        this.stuNum = stuNum;
    }

    protected StudentDetailRequestEntity(Parcel in) {
        examId = in.readInt();
        querySource = in.readString();
        stuNum = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(examId);
        dest.writeString(querySource);
        dest.writeInt(stuNum);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<StudentDetailRequestEntity> CREATOR = new Creator<StudentDetailRequestEntity>() {
        @Override
        public StudentDetailRequestEntity createFromParcel(Parcel in) {
            return new StudentDetailRequestEntity(in);
        }

        @Override
        public StudentDetailRequestEntity[] newArray(int size) {
            return new StudentDetailRequestEntity[size];
        }
    };

    public int getExamId() {
        return examId;
    }

    public int getStuNum() {
        return stuNum;
    }

    public String getQuerySource() {
        return querySource;
    }

    public void setQuerySource(String querySource) {
        this.querySource = querySource;
    }

    public void setExamId(int examId) {
        this.examId = examId;
    }

    public void setStuNum(int stuNum) {
        this.stuNum = stuNum;
    }


}
