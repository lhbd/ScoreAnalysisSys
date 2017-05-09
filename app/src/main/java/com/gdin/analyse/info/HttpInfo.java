package com.gdin.analyse.info;

import com.gdin.analyse.entity.ClassResultEntity;
import com.gdin.analyse.entity.ExamDataEntity;
import com.gdin.analyse.entity.HttpResult;
import com.gdin.analyse.entity.LoginDataEntity;
import com.gdin.analyse.entity.ResetPwdEntity;
import com.gdin.analyse.entity.SchoolEntity;

import java.util.List;

import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

public interface HttpInfo {

    @POST("user/info/getSchoolInfo")
    Observable<HttpResult<List<SchoolEntity>>> getSchoolData();
    @POST("user/info/login")
    Observable<HttpResult<LoginDataEntity>> checkLogin(@Body LoginDataEntity entity);
    @POST("user/info/alterPwd")
    Observable<HttpResult> resetPwd(@Body ResetPwdEntity entity);
    @POST("exam/getExamInfo")
    Observable<HttpResult<List<ExamDataEntity>>> getExam(@Body ExamDataEntity entity);
    @POST("score/getAnaInfo")
    Observable<HttpResult<ClassResultEntity>> getClassScore(@Body ClassResultEntity entity);
}
