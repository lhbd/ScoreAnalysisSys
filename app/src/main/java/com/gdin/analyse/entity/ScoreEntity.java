package com.gdin.analyse.entity;

import com.chad.library.adapter.base.entity.MultiItemEntity;

public class ScoreEntity implements MultiItemEntity {
    public static final int SCORE_DATA = 0;
    private int type;
    private String sNumber;
    private String sName;
    private String sScore;

    public ScoreEntity(int type, String sNumber, String sName, String sScore) {
        this.type = type;
        this.sNumber = sNumber;
        this.sName = sName;
        this.sScore = sScore;
    }

    public void setsNumber(String sNumber) {
        this.sNumber = sNumber;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public void setsScore(String sScore) {
        this.sScore = sScore;
    }

    public String getsNumber() {
        return sNumber;
    }

    public String getsName() {
        return sName;
    }

    public String getsScore() {
        return sScore;
    }

    @Override
    public String toString() {
        return "ScoreEntity{" +
                "sNumber='" + sNumber + '\'' +
                ", sName='" + sName + '\'' +
                ", sScore='" + sScore + '\'' +
                '}';
    }

    @Override
    public int getItemType() {
        return type;
    }
}
