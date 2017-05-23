package com.gdin.analyse.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2017/5/23.
 */

public class StudentRollSortRequestEntity implements Parcelable{
    private int schoolId;
    private int gradeId;
    private int classId;
    private int limitNum;
    private String examName;
    private String orderBy;
    private String sortBy;

    public StudentRollSortRequestEntity(int schoolId, int gradeId, int classId, int limitNum, String examName, String orderBy, String sortBy) {
        this.schoolId = schoolId;
        this.gradeId = gradeId;
        this.classId = classId;
        this.limitNum = limitNum;
        this.examName = examName;
        this.orderBy = orderBy;
        this.sortBy = sortBy;
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

    public void setLimitNum(int limitNum) {
        this.limitNum = limitNum;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
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

    public int getLimitNum() {
        return limitNum;
    }

    public String getExamName() {
        return examName;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public String getSortBy() {
        return sortBy;
    }

    protected StudentRollSortRequestEntity(Parcel in) {
        schoolId = in.readInt();
        gradeId = in.readInt();
        classId = in.readInt();
        limitNum = in.readInt();
        examName = in.readString();
        orderBy = in.readString();
        sortBy = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(schoolId);
        dest.writeInt(gradeId);
        dest.writeInt(classId);
        dest.writeInt(limitNum);
        dest.writeString(examName);
        dest.writeString(orderBy);
        dest.writeString(sortBy);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<StudentRollSortRequestEntity> CREATOR = new Creator<StudentRollSortRequestEntity>() {
        @Override
        public StudentRollSortRequestEntity createFromParcel(Parcel in) {
            return new StudentRollSortRequestEntity(in);
        }

        @Override
        public StudentRollSortRequestEntity[] newArray(int size) {
            return new StudentRollSortRequestEntity[size];
        }
    };
}
