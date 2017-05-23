package com.gdin.analyse.entity;

public class StudentRankResultEntity {
    private int chRank;
    private int mathRank;
    private int enRank;
    private int sumRank;

    public StudentRankResultEntity(int chRank, int mathRank, int enRank, int sumRank) {
        this.chRank = chRank;
        this.mathRank = mathRank;
        this.enRank = enRank;
        this.sumRank = sumRank;
    }

    public void setChRank(int chRank) {
        this.chRank = chRank;
    }

    public void setMathRank(int mathRank) {
        this.mathRank = mathRank;
    }

    public void setEnRank(int enRank) {
        this.enRank = enRank;
    }

    public void setSumRank(int sumRank) {
        this.sumRank = sumRank;
    }

    public int getChRank() {
        return chRank;
    }

    public int getMathRank() {
        return mathRank;
    }

    public int getEnRank() {
        return enRank;
    }

    public int getSumRank() {
        return sumRank;
    }
}
