package com.gdin.analyse.entity;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.gdin.analyse.adapter.ScoreDetailAdapter;

public class ModuleScoreDetailEntity implements MultiItemEntity{
    private String mName;
    private int mScore;

    @Override
    public String toString() {
        return "ModuleScoreDetailEntity{" +
                ", mName='" + mName + '\'' +
                ", mScore=" + mScore +
                '}';
    }

    public ModuleScoreDetailEntity(String mName, int mScore) {
        this.mName = mName;
        this.mScore = mScore;
    }

    @Override
    public int getItemType() {
        return ScoreDetailAdapter.MODULE_SCORE;
    }

    public String getmName() {
        return mName;
    }

    public int getmScore() {
        return mScore;
    }


    public void setmName(String mName) {
        this.mName = mName;
    }

    public void setmScore(int mScore) {
        this.mScore = mScore;
    }
}
