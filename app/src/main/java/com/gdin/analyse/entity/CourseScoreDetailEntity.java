package com.gdin.analyse.entity;

import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.gdin.analyse.adapter.ScoreDetailAdapter;

public class CourseScoreDetailEntity extends AbstractExpandableItem<ModuleScoreDetailEntity> implements MultiItemEntity{
    private String cName;
    private int cScore;

    @Override
    public String toString() {
        return "CourseScoreDetailEntity{" +
                "cScore=" + cScore +
                ", cName='" + cName + '\'' +
                '}';
    }

    public CourseScoreDetailEntity(String cName, int cScore) {
        this.cName = cName;
        this.cScore = cScore;
    }

    public void setcScore(int cScore) {
        this.cScore = cScore;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }


    public String getcName() {
        return cName;
    }



    public int getcScore() {
        return cScore;
    }

    @Override
    public int getLevel() {
        return 0;
    }

    @Override
    public int getItemType() {
        return ScoreDetailAdapter.COURSE_SCORE;

    }
}
