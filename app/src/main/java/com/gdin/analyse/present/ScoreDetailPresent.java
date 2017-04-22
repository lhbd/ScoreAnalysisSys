package com.gdin.analyse.present;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.gdin.analyse.entity.CourseScoreDetailEntity;
import com.gdin.analyse.model.scoredetail.ScoreDetailModel;
import com.gdin.analyse.model.scoredetail.ScoreDetailModelImpl;
import com.gdin.analyse.view.ScoreDetailView;

import java.util.List;

public class ScoreDetailPresent {

    ScoreDetailView scoreDetailView;
    ScoreDetailModel model;

    public ScoreDetailPresent(ScoreDetailView scoreDetailView) {
        this.scoreDetailView = scoreDetailView;
        model = new ScoreDetailModelImpl();
    }

    public List<MultiItemEntity> getData(){
        return model.initData();
    }


}
