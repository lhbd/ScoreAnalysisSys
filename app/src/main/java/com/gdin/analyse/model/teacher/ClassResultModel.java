package com.gdin.analyse.model.teacher;

import com.gdin.analyse.entity.ExamDataEntity;

import java.util.ArrayList;
import java.util.List;

public interface ClassResultModel {

    ArrayList<ClassResultScoreEntity> getScoreData();
   List<ExamDataEntity> getExamData();
    ArrayList<Integer> getRankData();
}
