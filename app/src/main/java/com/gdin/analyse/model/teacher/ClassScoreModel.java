package com.gdin.analyse.model.teacher;

import com.gdin.analyse.entity.ClassScoreEntity;

import java.util.ArrayList;
import java.util.Map;

public interface ClassScoreModel {

    ArrayList<ClassScoreEntity> getScoreData();
    Map<String,String> getExamData();
    ArrayList<Integer> getRankData();
}
