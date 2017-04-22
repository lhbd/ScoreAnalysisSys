package com.gdin.analyse.model.teacher;

import com.gdin.analyse.entity.ScoreEntity;

import java.util.ArrayList;
import java.util.List;

public class TModelImpl implements TModel {
    @Override
    public List<ScoreEntity> initData() {
        List<ScoreEntity> data = new ArrayList<>();
        for (int i = 0; i<20;i++){
            data.add(new ScoreEntity(ScoreEntity.SCORE_DATA,"学号 "+i,"姓名 "+i,"成绩 "+i));
        }
        return data;
    }
}
