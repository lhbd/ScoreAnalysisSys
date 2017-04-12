package com.gdin.analyse.info;

import com.gdin.analyse.entity.HttpResult;
import com.gdin.analyse.entity.LoginDataEntity;
import com.gdin.analyse.entity.SchoolEntity;

import java.util.List;

import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

public interface HttpInfo {

    @POST("user/info/getSchoolInfo")
    Observable<HttpResult<List<SchoolEntity>>> getSchoolData();
    @POST("user/info/login")
    Observable<HttpResult<List<LoginDataEntity>>> checkLogin(@Body LoginDataEntity entity);
}
