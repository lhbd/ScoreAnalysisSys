package com.gdin.analyse.present;

import com.gdin.analyse.model.teacher.ClassResultModel;
import com.gdin.analyse.model.teacher.ClassResultModelImpl;
import com.gdin.analyse.view.ClassResultView;

import java.util.ArrayList;

public class ClassResultPresent {

    ClassResultView view ;
    ClassResultModel model;

    public ClassResultPresent(ClassResultView view) {
        this.view = view;
        model = new ClassResultModelImpl();
    }
    public ArrayList<Integer> getRankData(){
        return model.getRankData();
    }
}
