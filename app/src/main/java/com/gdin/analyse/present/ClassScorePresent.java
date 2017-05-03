package com.gdin.analyse.present;

import com.gdin.analyse.entity.ClassScoreEntity;
import com.gdin.analyse.model.teacher.ClassScoreModel;
import com.gdin.analyse.model.teacher.ClassScoreModelImpl;
import com.gdin.analyse.view.ClassScoreView;

import java.util.ArrayList;
import java.util.Map;

public class ClassScorePresent {

    ClassScoreView view ;
    ClassScoreModel model;

    public ClassScorePresent(ClassScoreView view) {
        this.view = view;
        model = new ClassScoreModelImpl();
    }

    public ArrayList<ClassScoreEntity> getScoreData(){
        return model.getScoreData();
    }
    public Map<String,String> getExamData(){
        return model.getExamData();
    }
    public ArrayList<Integer> getRankData(){
        return model.getRankData();
    }
}
