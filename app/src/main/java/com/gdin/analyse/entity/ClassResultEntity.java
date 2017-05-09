package com.gdin.analyse.entity;


import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;

public class ClassResultEntity implements Parcelable{

    private int id;
    private int schoolId;
    private int gradeId;
    private int classId;
    private int examId;
    @Nullable
    private String examName;
    private int chBest;
    private int mathBest;
    private int enBest;
    private int sumBest;
    private int chAvg;
    private int mathAvg;
    private int enAvg;
    private int sumAvg;
    private int number;
    private int passNumber;
    private int superiorNumber;

    public ClassResultEntity(int schoolId, int gradeId, int classId, String examName) {
        this.schoolId = schoolId;
        this.gradeId = gradeId;
        this.classId = classId;
        this.examName = examName;
    }

    @Override
    public String toString() {
        return "ClassResultEntity{" +
                "superiorNumber=" + superiorNumber +
                ", passNumber=" + passNumber +
                ", number=" + number +
                ", sumAvg=" + sumAvg +
                ", enAvg=" + enAvg +
                ", mathAvg=" + mathAvg +
                ", chAvg=" + chAvg +
                ", sumBest=" + sumBest +
                ", enBest=" + enBest +
                ", mathBest=" + mathBest +
                ", chBest=" + chBest +
                ", examName='" + examName + '\'' +
                ", examId=" + examId +
                ", classId=" + classId +
                ", gradeId=" + gradeId +
                ", schoolId=" + schoolId +
                ", id=" + id +
                '}';
    }

    public int getId() {
        return id;
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

    public int getExamId() {
        return examId;
    }

    public String getExamName() {
        return examName;
    }

    public int getChBest() {
        return chBest;
    }

    public int getMathBest() {
        return mathBest;
    }

    public int getEnBest() {
        return enBest;
    }

    public int getSumBest() {
        return sumBest;
    }

    public int getChAvg() {
        return chAvg;
    }

    public int getMathAvg() {
        return mathAvg;
    }

    public int getEnAvg() {
        return enAvg;
    }

    public int getSumAvg() {
        return sumAvg;
    }

    public int getNumber() {
        return number;
    }

    public int getPassNumber() {
        return passNumber;
    }

    public int getSuperiorNumber() {
        return superiorNumber;
    }

    public void setId(int id) {
        this.id = id;
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

    public void setExamId(int examId) {
        this.examId = examId;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public void setChBest(int chBest) {
        this.chBest = chBest;
    }

    public void setMathBest(int mathBest) {
        this.mathBest = mathBest;
    }

    public void setEnBest(int enBest) {
        this.enBest = enBest;
    }

    public void setSumBest(int sumBest) {
        this.sumBest = sumBest;
    }

    public void setChAvg(int chAvg) {
        this.chAvg = chAvg;
    }

    public void setMathAvg(int mathAvg) {
        this.mathAvg = mathAvg;
    }

    public void setEnAvg(int enAvg) {
        this.enAvg = enAvg;
    }

    public void setSumAvg(int sumAvg) {
        this.sumAvg = sumAvg;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setPassNumber(int passNumber) {
        this.passNumber = passNumber;
    }

    public void setSuperiorNumber(int superiorNumber) {
        this.superiorNumber = superiorNumber;
    }

    protected ClassResultEntity(Parcel in) {
        id = in.readInt();
        schoolId = in.readInt();
        gradeId = in.readInt();
        classId = in.readInt();
        examId = in.readInt();
        examName = in.readString();
        chBest = in.readInt();
        mathBest = in.readInt();
        enBest = in.readInt();
        sumBest = in.readInt();
        chAvg = in.readInt();
        mathAvg = in.readInt();
        enAvg = in.readInt();
        sumAvg = in.readInt();
        number = in.readInt();
        passNumber = in.readInt();
        superiorNumber = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(schoolId);
        dest.writeInt(gradeId);
        dest.writeInt(classId);
        dest.writeInt(examId);
        dest.writeString(examName);
        dest.writeInt(chBest);
        dest.writeInt(mathBest);
        dest.writeInt(enBest);
        dest.writeInt(sumBest);
        dest.writeInt(chAvg);
        dest.writeInt(mathAvg);
        dest.writeInt(enAvg);
        dest.writeInt(sumAvg);
        dest.writeInt(number);
        dest.writeInt(passNumber);
        dest.writeInt(superiorNumber);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ClassResultEntity> CREATOR = new Creator<ClassResultEntity>() {
        @Override
        public ClassResultEntity createFromParcel(Parcel in) {
            return new ClassResultEntity(in);
        }

        @Override
        public ClassResultEntity[] newArray(int size) {
            return new ClassResultEntity[size];
        }
    };
}
