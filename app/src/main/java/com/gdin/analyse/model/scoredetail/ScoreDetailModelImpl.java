package com.gdin.analyse.model.scoredetail;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.gdin.analyse.entity.CourseScoreDetailEntity;
import com.gdin.analyse.entity.ModuleScoreDetailEntity;

import java.util.ArrayList;
import java.util.List;

public class ScoreDetailModelImpl implements ScoreDetailModel {
    @Override
    public List<MultiItemEntity> initData() {
        String[] arr = new String[]{"语文","数学","英语","生物","地理","政治","历史"};
        String[] module = new String[]{"选择题","填空题","问答题"};
        List<MultiItemEntity> data = new ArrayList<>();
        for (int i = 0;i<7;i++){
            CourseScoreDetailEntity entity = new CourseScoreDetailEntity(arr[i],88);
            for (int j=0;j<3;j++){
                entity.addSubItem(new ModuleScoreDetailEntity(module[j],30));
            }
            data.add(entity);
        }
        return data;
    }
}
