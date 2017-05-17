package com.gdin.analyse.model.register;

import com.gdin.analyse.entity.ClassEntity;
import com.gdin.analyse.entity.GradeEntity;
import com.gdin.analyse.entity.HttpResult;
import com.gdin.analyse.entity.SchoolEntity;
import com.gdin.analyse.info.HttpMethods;
import com.gdin.analyse.subscribers.cSubscriber;

import java.util.ArrayList;
import java.util.List;

public class RegisterModelImpl implements RegisterModel {


    private List<SchoolEntity> schoolEntityList = new ArrayList<>();
    private List<GradeEntity>  gradeEntityList  = new ArrayList<>();
    private List<ClassEntity>  classEntityList  = new ArrayList<>();


    @Override
    public void initData() {
        gradeEntityList.add(new GradeEntity(0,"——班级——"));
        gradeEntityList.add(new GradeEntity(1,"一年级"));
        gradeEntityList.add(new GradeEntity(2,"二年级"));
        gradeEntityList.add(new GradeEntity(3,"三年级"));

        classEntityList.add(new ClassEntity(0,"——年级——"));
        for (int i = 1;i<4;i++){
            classEntityList.add(new ClassEntity(i,i+"班"));
        }
    }


    @Override
    public List<String> getSchoolData() {
        final List<String> list = new ArrayList<>();
        HttpMethods.getInstance().getSchoolData(new cSubscriber<HttpResult<List<SchoolEntity>>>() {
            @Override
            public void onComplete() {

            }

            @Override
            public void onNext(HttpResult<List<SchoolEntity>> result, int i) {
                for (SchoolEntity entity : result.getData()){
                    schoolEntityList.add(entity);
                    list.add(entity.getSchoolName());
                }

            }
        });
        return list;
    }

    @Override
    public List<String> getGradeName() {

        List<String> list = new ArrayList<>();
        for (GradeEntity entity : gradeEntityList){
            list.add(entity.getGradeName());
        }
        return list;
    }

    @Override
    public List<String> getClassName() {
        List<String> list = new ArrayList<>();
        for (ClassEntity entity : classEntityList){
            list.add(entity.getClassName());
        }
        return list;
    }

    @Override
    public int getSchoolId(int pos) {
        return schoolEntityList.get(pos).getSchoolId();
    }

    @Override
    public int getGradeId(int pos) {
        return gradeEntityList.get(pos).getLoginGradeId();
    }

    @Override
    public int getClassId(int pos) {
        return classEntityList.get(pos).getLoginClassId();
    }
}
