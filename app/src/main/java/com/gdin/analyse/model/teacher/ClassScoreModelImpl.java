package com.gdin.analyse.model.teacher;

import android.content.Context;

import com.gdin.analyse.R;
import com.gdin.analyse.entity.ClassScoreEntity;
import com.gdin.analyse.tools.CustomApplication;

import java.util.ArrayList;
import java.util.Map;
import java.util.WeakHashMap;

public class ClassScoreModelImpl implements ClassScoreModel {
    @Override
    public ArrayList<ClassScoreEntity> getScoreData() {

        ArrayList<ClassScoreEntity> data = new ArrayList<>();
        Context context = CustomApplication.getContext();
        String grade = context.getString(R.string.score_grade);
        String cla = context.getString(R.string.score_class);
        String max = context.getString(R.string.score_max);
        String avg = context.getString(R.string.score_avg);
        data.add(new ClassScoreEntity(context.getString(R.string.score_detail),"","",""));
        data.add(new ClassScoreEntity(grade,"","",""));
        data.add(new ClassScoreEntity(max,"92",avg,"74"));
        data.add(new ClassScoreEntity(cla,"","",""));
        data.add(new ClassScoreEntity(max,"92",avg,"74"));

        data.add(new ClassScoreEntity(context.getString(R.string.score_ch),"","",""));
        data.add(new ClassScoreEntity(grade,"","",""));
        data.add(new ClassScoreEntity(max,"92",avg,"74"));
        data.add(new ClassScoreEntity(cla,"","",""));
        data.add(new ClassScoreEntity(max,"92",avg,"74"));

        data.add(new ClassScoreEntity(context.getString(R.string.score_math),"","",""));
        data.add(new ClassScoreEntity(grade,"","",""));
        data.add(new ClassScoreEntity(max,"92",avg,"74"));
        data.add(new ClassScoreEntity(cla,"","",""));
        data.add(new ClassScoreEntity(max,"92",avg,"74"));

        data.add(new ClassScoreEntity(context.getString(R.string.score_eh),"","",""));
        data.add(new ClassScoreEntity(grade,"","",""));
        data.add(new ClassScoreEntity(max,"92",avg,"74"));
        data.add(new ClassScoreEntity(cla,"","",""));
        data.add(new ClassScoreEntity(max,"92",avg,"74"));
        return data;
    }

    @Override
    public Map<String,String> getExamData() {
        Map<String,String> data = new WeakHashMap<>();
        data.put("0","试卷1");
        data.put("1","试卷2");
        data.put("2","试卷3");
        data.put("3","试卷4");
        data.put("4","试卷5");
        data.put("5","试卷6");
        data.put("6","试卷7");
        data.put("7","试卷8");
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
