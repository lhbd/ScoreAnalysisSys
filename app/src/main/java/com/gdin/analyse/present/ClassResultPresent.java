package com.gdin.analyse.present;

import com.gdin.analyse.entity.ExamDataEntity;
import com.gdin.analyse.model.teacher.ClassResultModel;
import com.gdin.analyse.model.teacher.ClassResultModelImpl;
import com.gdin.analyse.model.teacher.ClassResultScoreEntity;
import com.gdin.analyse.view.ClassResultView;

import java.util.ArrayList;
import java.util.List;

public class ClassResultPresent {

    ClassResultView view ;
    ClassResultModel model;

    public ClassResultPresent(ClassResultView view) {
        this.view = view;
        model = new ClassResultModelImpl();
    }

    public ArrayList<ClassResultScoreEntity> getScoreData(){
        return model.getScoreData();
    }
    public List<ExamDataEntity> getExamData(){
        return model.getExamData();
    }
    public ArrayList<Integer> getRankData(){
        return model.getRankData();
    }
}
