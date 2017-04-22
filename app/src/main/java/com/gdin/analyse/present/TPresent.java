package com.gdin.analyse.present;

import com.gdin.analyse.entity.ScoreEntity;
import com.gdin.analyse.model.teacher.TModel;
import com.gdin.analyse.model.teacher.TModelImpl;
import com.gdin.analyse.view.TMainView;

import java.util.List;

public class TPresent {
    TMainView tMainView;
    TModel tModel;

    public TPresent(TMainView tMainView) {
        this.tMainView = tMainView;
        tModel = new TModelImpl();
    }
    public List<ScoreEntity> initData(){
        return tModel.initData();
    }
}
