package com.gdin.analyse.info;

import com.gdin.analyse.entity.ClassResultEntity;
import com.gdin.analyse.entity.ExamDataEntity;
import com.gdin.analyse.entity.HttpResult;
import com.gdin.analyse.entity.LoginDataEntity;
import com.gdin.analyse.entity.StudentRankResultEntity;
import com.gdin.analyse.entity.StudentRollRequestEntity;
import com.gdin.analyse.entity.StudentDetailRequestEntity;
import com.gdin.analyse.entity.ResetPwdEntity;
import com.gdin.analyse.entity.SchoolEntity;
import com.gdin.analyse.entity.StudentRollSortRequestEntity;
import com.gdin.analyse.entity.StudentRollSortResultEntity;
import com.gdin.analyse.entity.StudentScoreResultEntity;

import java.util.List;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Observable;

public interface HttpInfo {

    @GET("user/info/getSchoolInfo")
    Observable<HttpResult<List<SchoolEntity>>> getSchoolData();
    @POST("user/info/login")
    Observable<HttpResult<LoginDataEntity>> checkLogin(@Body LoginDataEntity entity);
    @POST("user/info/alterPwd")
    Observable<HttpResult> resetPwd(@Body ResetPwdEntity entity);
    @POST("exam/getExamInfo")
    Observable<HttpResult<List<ExamDataEntity>>> getExam(@Body ExamDataEntity entity);
    @POST("score/getAnaInfo")
    Observable<HttpResult<ClassResultEntity>> getClassScore(@Body ClassResultEntity entity);
    @POST("score/getAllScoreInfo")
    Observable<HttpResult<List<StudentScoreResultEntity>>> getStudentRoll(@Body StudentRollRequestEntity entity);
    @POST("score/getIndivScoreInfo")
    Observable<HttpResult<StudentScoreResultEntity>> getStudentScore(@Body StudentDetailRequestEntity entity);
    @POST("rank/getIndivRankinfo")
    Observable<HttpResult<StudentRankResultEntity>> getStudentRank(@Body StudentDetailRequestEntity entity);
    @POST("rank/getSpecifyRank")
    Observable<HttpResult<List<StudentRollSortResultEntity>>> getStudentRollSort(@Body StudentRollSortRequestEntity entity);
}
