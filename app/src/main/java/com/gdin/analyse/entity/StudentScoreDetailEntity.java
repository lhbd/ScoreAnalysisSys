package com.gdin.analyse.entity;

public class StudentScoreDetailEntity{
    private int sWholeScore;
    private int sChScore;
    private int sMathScore;
    private int sEnScore;

    public StudentScoreDetailEntity(int sWholeScore, int sChScore, int sMathScore, int sEnScore) {
        this.sWholeScore = sWholeScore;
        this.sChScore = sChScore;
        this.sMathScore = sMathScore;
        this.sEnScore = sEnScore;
    }

    public int getsWholeScore() {
        return sWholeScore;
    }

    public int getsChScore() {
        return sChScore;
    }

    public int getsMathScore() {
        return sMathScore;
    }

    public int getsEnScore() {
        return sEnScore;
    }

    public void setsWholeScore(int sWholeScore) {
        this.sWholeScore = sWholeScore;
    }

    public void setsChScore(int sChScore) {
        this.sChScore = sChScore;
    }

    public void setsMathScore(int sMathScore) {
        this.sMathScore = sMathScore;
    }

    public void setsEnScore(int sEnScore) {
        this.sEnScore = sEnScore;
    }
}
