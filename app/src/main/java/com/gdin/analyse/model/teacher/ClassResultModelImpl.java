package com.gdin.analyse.model.teacher;

import com.gdin.analyse.entity.ClassResultEntity;
import com.gdin.analyse.entity.ExamDataEntity;
import com.gdin.analyse.entity.HttpResult;
import com.gdin.analyse.info.HttpMethods;
import com.gdin.analyse.subscribers.cSubscriber;
import com.gdin.analyse.tools.CustomApplication;

import java.util.ArrayList;
import java.util.List;

public class ClassResultModelImpl implements ClassResultModel {
    @Override
    public ArrayList<ClassResultScoreEntity> getScoreData() {

        final ArrayList<ClassResultScoreEntity> data = new ArrayList<>();
        HttpMethods.getInstance().getClassScore(new cSubscriber<HttpResult<ClassResultEntity>>() {
            @Override
            public void onComplete() {

            }

            @Override
            public void onNext(HttpResult<ClassResultEntity> result, int i) {
               data.addAll(initData(result.getData()));

            }
        },new ClassResultEntity(CustomApplication.getSchoolId(),CustomApplication.getGradeId(),
                CustomApplication.getClassId(),CustomApplication.getExamName()));
        return data;
    }

    private List<ClassResultScoreEntity> initData(ClassResultEntity entity){
        ArrayList<ClassResultScoreEntity> data = new ArrayList<>();
//        Context context = CustomApplication.getContext();
//        String grade = context.getString(R.string.score_grade);
//        String cla = context.getString(R.string.score_class);
//        String max = context.getString(R.string.score_max);
//        String avg = context.getString(R.string.score_avg);
//
//        data.add(new ClassResultScoreEntity(context.getString(R.string.score_detail),"","",""));
//        data.add(new ClassResultScoreEntity(grade,"","",""));
//        data.add(new ClassResultScoreEntity(max,entity.getSumBest()+"",avg,entity.getSumAvg()+""));
//        data.add(new ClassResultScoreEntity(cla,"","",""));
//        data.add(new ClassResultScoreEntity(max,entity.getSumBest()+"",avg,entity.getSumAvg()+""));
//
//        data.add(new ClassResultScoreEntity(context.getString(R.string.score_ch),"","",""));
//        data.add(new ClassResultScoreEntity(grade,"","",""));
//        data.add(new ClassResultScoreEntity(max,entity.getChBest()+"",avg,entity.getChAvg()+""));
//        data.add(new ClassResultScoreEntity(cla,"","",""));
//        data.add(new ClassResultScoreEntity(max,entity.getChBest()+"",avg,entity.getChAvg()+""));
//
//        data.add(new ClassResultScoreEntity(context.getString(R.string.score_math),"","",""));
//        data.add(new ClassResultScoreEntity(grade,"","",""));
//        data.add(new ClassResultScoreEntity(max,entity.getMathBest()+"",avg,entity.getMathAvg()+""));
//        data.add(new ClassResultScoreEntity(cla,"","",""));
//        data.add(new ClassResultScoreEntity(max,entity.getMathBest()+"",avg,entity.getMathAvg()+""));
//
//        data.add(new ClassResultScoreEntity(context.getString(R.string.score_eh),"","",""));
//        data.add(new ClassResultScoreEntity(grade,"","",""));
//        data.add(new ClassResultScoreEntity(max,entity.getEnBest()+"",avg,entity.getEnAvg()+""));
//        data.add(new ClassResultScoreEntity(cla,"","",""));
//        data.add(new ClassResultScoreEntity(max,entity.getEnBest()+"",avg,entity.getEnAvg()+""));
        return data;
    }
    @Override
    public List<ExamDataEntity> getExamData() {
        final List<ExamDataEntity> data = new ArrayList<>();
        HttpMethods.getInstance().getExam(new cSubscriber<HttpResult<List<ExamDataEntity>>>() {
            @Override
            public void onComplete() {

            }

            @Override
            public void onNext(HttpResult<List<ExamDataEntity>> result, int i) {
                data.addAll(result.getData());
            }
        },new ExamDataEntity(CustomApplication.getSchoolId(),CustomApplication.getGradeId(),CustomApplication.getClassId()));
        return data;
    }

    @Override
    public ArrayList<Integer> getRankData() {
        ArrayList<Integer> data = new ArrayList<>();
        data.add(3);
        data.add(15);
        data.add(21);
        return data;
    }
}
