package com.gdin.analyse.entity;


import android.os.Parcel;
import android.os.Parcelable;

public class ExamDataEntity implements Parcelable{
    private int examId;
    private int schoolId;
    private int gradeId;
    private int classId;
    private int examNumber;
    private String examName;

    @Override
    public String toString() {
        return "ExamDataEntity{" +
                "examId=" + examId +
                ", schoolId=" + schoolId +
                ", gradeId=" + gradeId +
                ", classId=" + classId +
                ", examNumber=" + examNumber +
                ", examName='" + examName + '\'' +
                '}';
    }

    public ExamDataEntity(int schoolId, int gradeId, int classId) {
        this.schoolId = schoolId;
        this.gradeId = gradeId;
        this.classId = classId;
    }

    public ExamDataEntity(int examId, int schoolId, int gradeId, int classId, int examNumber, String examName) {
        this.examId = examId;
        this.schoolId = schoolId;
        this.gradeId = gradeId;
        this.classId = classId;
        this.examNumber = examNumber;
        this.examName = examName;
    }

    public void setExamId(int examId) {
        this.examId = examId;
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

    public void setExamNumber(int examNumber) {
        this.examNumber = examNumber;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public int getExamId() {
        return examId;
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

    public int getExamNumber() {
        return examNumber;
    }

    public String getExamName() {
        return examName;
    }

    protected ExamDataEntity(Parcel in) {
        examId = in.readInt();
        schoolId = in.readInt();
        gradeId = in.readInt();
        classId = in.readInt();
        examNumber = in.readInt();
        examName = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(examId);
        dest.writeInt(schoolId);
        dest.writeInt(gradeId);
        dest.writeInt(classId);
        dest.writeInt(examNumber);
        dest.writeString(examName);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ExamDataEntity> CREATOR = new Creator<ExamDataEntity>() {
        @Override
        public ExamDataEntity createFromParcel(Parcel in) {
            return new ExamDataEntity(in);
        }

        @Override
        public ExamDataEntity[] newArray(int size) {
            return new ExamDataEntity[size];
        }
    };
}
