package com.gdin.analyse.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2017/5/18.
 */

public class StudentRollRequestEntity implements Parcelable{
    private int classId;
    private String examName;
    private int gradeId;
    private int schoolId;

    public StudentRollRequestEntity(int classId, String examName, int gradeId, int schoolId) {
        this.classId = classId;
        this.examName = examName;
        this.gradeId = gradeId;
        this.schoolId = schoolId;
    }

    protected StudentRollRequestEntity(Parcel in) {
        classId = in.readInt();
        examName = in.readString();
        gradeId = in.readInt();
        schoolId = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(classId);
        dest.writeString(examName);
        dest.writeInt(gradeId);
        dest.writeInt(schoolId);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<StudentRollRequestEntity> CREATOR = new Creator<StudentRollRequestEntity>() {
        @Override
        public StudentRollRequestEntity createFromParcel(Parcel in) {
            return new StudentRollRequestEntity(in);
        }

        @Override
        public StudentRollRequestEntity[] newArray(int size) {
            return new StudentRollRequestEntity[size];
        }
    };

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public void setGradeId(int gradeId) {
        this.gradeId = gradeId;
    }

    public void setSchoolId(int schoolId) {
        this.schoolId = schoolId;
    }

    public int getClassId() {
        return classId;
    }

    public String getExamName() {
        return examName;
    }

    public int getGradeId() {
        return gradeId;
    }

    public int getSchoolId() {
        return schoolId;
    }
}
