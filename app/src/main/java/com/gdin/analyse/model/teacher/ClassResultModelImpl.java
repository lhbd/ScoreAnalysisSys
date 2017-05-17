package com.gdin.analyse.model.teacher;

import com.gdin.analyse.entity.ExamDataEntity;
import com.gdin.analyse.entity.HttpResult;
import com.gdin.analyse.info.HttpMethods;
import com.gdin.analyse.subscribers.cSubscriber;
import com.gdin.analyse.tools.CustomApplication;

import java.util.ArrayList;
import java.util.List;

public class ClassResultModelImpl implements ClassResultModel {
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
