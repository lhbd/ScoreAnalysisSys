package com.gdin.analyse.entity;


import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;

public class ClassResultEntity implements Parcelable{

    private double id;
    private double schoolId;
    private double gradeId;
    private double classId;
    private double examId;
    @Nullable
    private String examName;
    private double chBest;
    private double mathBest;
    private double enBest;
    private double sumBest;
    private double chAvg;
    private double mathAvg;
    private double enAvg;
    private double sumAvg;
    private double number;
    private double passNumber;
    private double superiorNumber;

    public ClassResultEntity(double schoolId, double gradeId, double classId, String examName) {
        this.schoolId = schoolId;
        this.gradeId = gradeId;
        this.classId = classId;
        this.examName = examName;
    }

    protected ClassResultEntity(Parcel in) {
        id = in.readDouble();
        schoolId = in.readDouble();
        gradeId = in.readDouble();
        classId = in.readDouble();
        examId = in.readDouble();
        examName = in.readString();
        chBest = in.readDouble();
        mathBest = in.readDouble();
        enBest = in.readDouble();
        sumBest = in.readDouble();
        chAvg = in.readDouble();
        mathAvg = in.readDouble();
        enAvg = in.readDouble();
        sumAvg = in.readDouble();
        number = in.readDouble();
        passNumber = in.readDouble();
        superiorNumber = in.readDouble();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(id);
        dest.writeDouble(schoolId);
        dest.writeDouble(gradeId);
        dest.writeDouble(classId);
        dest.writeDouble(examId);
        dest.writeString(examName);
        dest.writeDouble(chBest);
        dest.writeDouble(mathBest);
        dest.writeDouble(enBest);
        dest.writeDouble(sumBest);
        dest.writeDouble(chAvg);
        dest.writeDouble(mathAvg);
        dest.writeDouble(enAvg);
        dest.writeDouble(sumAvg);
        dest.writeDouble(number);
        dest.writeDouble(passNumber);
        dest.writeDouble(superiorNumber);
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

    public double getId() {
        return id;
    }

    public double getSchoolId() {
        return schoolId;
    }

    public double getGradeId() {
        return gradeId;
    }

    public double getClassId() {
        return classId;
    }

    public double getExamId() {
        return examId;
    }

    public String getExamName() {
        return examName;
    }

    public double getChBest() {
        return chBest;
    }

    public double getMathBest() {
        return mathBest;
    }

    public double getEnBest() {
        return enBest;
    }

    public double getSumBest() {
        return sumBest;
    }

    public double getChAvg() {
        return chAvg;
    }

    public double getMathAvg() {
        return mathAvg;
    }

    public double getEnAvg() {
        return enAvg;
    }

    public double getSumAvg() {
        return sumAvg;
    }

    public double getNumber() {
        return number;
    }

    public double getPassNumber() {
        return passNumber;
    }

    public double getSuperiorNumber() {
        return superiorNumber;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSchoolId(double schoolId) {
        this.schoolId = schoolId;
    }

    public void setGradeId(double gradeId) {
        this.gradeId = gradeId;
    }

    public void setClassId(double classId) {
        this.classId = classId;
    }

    public void setExamId(double examId) {
        this.examId = examId;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public void setChBest(double chBest) {
        this.chBest = chBest;
    }

    public void setMathBest(double mathBest) {
        this.mathBest = mathBest;
    }

    public void setEnBest(double enBest) {
        this.enBest = enBest;
    }

    public void setSumBest(double sumBest) {
        this.sumBest = sumBest;
    }

    public void setChAvg(double chAvg) {
        this.chAvg = chAvg;
    }

    public void setMathAvg(double mathAvg) {
        this.mathAvg = mathAvg;
    }

    public void setEnAvg(double enAvg) {
        this.enAvg = enAvg;
    }

    public void setSumAvg(double sumAvg) {
        this.sumAvg = sumAvg;
    }

    public void setNumber(double number) {
        this.number = number;
    }

    public void setPassNumber(double passNumber) {
        this.passNumber = passNumber;
    }

    public void setSuperiorNumber(double superiorNumber) {
        this.superiorNumber = superiorNumber;
    }

}
