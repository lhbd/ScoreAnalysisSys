package com.gdin.analyse.info;

import com.gdin.analyse.entity.ClassResultEntity;
import com.gdin.analyse.entity.ExamDataEntity;
import com.gdin.analyse.entity.HttpResult;
import com.gdin.analyse.entity.LoginDataEntity;
import com.gdin.analyse.entity.ResetPwdEntity;
import com.gdin.analyse.entity.SchoolEntity;
import com.gdin.analyse.entity.StudentDetailRequestEntity;
import com.gdin.analyse.entity.StudentRankResultEntity;
import com.gdin.analyse.entity.StudentRollRequestEntity;
import com.gdin.analyse.entity.StudentRollSortRequestEntity;
import com.gdin.analyse.entity.StudentRollSortResultEntity;
import com.gdin.analyse.entity.StudentScoreResultEntity;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class HttpMethods {
    private final static String BASE_URL = "http://120.25.97.250:8888/";
//    private final static String BASE_URL = "http://120.77.34.81/";

    private static final int DEFAULT_TIMEOUT = 5;

    private Retrofit retrofit;
    private HttpInfo info;

    //构造方法私有
    private HttpMethods() {

        //手动创建一个OkHttpClient,添加日志打印并设置超时时间
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(
                new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .build();

        retrofit = new Retrofit.Builder()
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();
        info = retrofit.create(HttpInfo.class);
    }

    //在访问HttpMethods时创建单例
    private static class SingletonHolder{
        private static final HttpMethods INSTANCE = new HttpMethods();
    }

    //获取单例
    public static HttpMethods getInstance(){
        return SingletonHolder.INSTANCE;
    }

    //获取学校
    public void getSchoolData(Subscriber<HttpResult<List<SchoolEntity>>> subscriber){
        info.getSchoolData()
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
    //检查登录
    public void checkLogin(Subscriber<HttpResult<LoginDataEntity>> subscriber,LoginDataEntity entity){
        info.checkLogin(entity)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
    //重置密码
    public void resetPwd(Subscriber<HttpResult> subscriber,ResetPwdEntity entity){
        info.resetPwd(entity)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
    //获取试卷
    public void getExam(Subscriber<HttpResult<List<ExamDataEntity>>> subscriber, ExamDataEntity entity){
        info.getExam(entity)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
    //获取班级整体得分
    public void getClassScore(Subscriber<HttpResult<ClassResultEntity>> subscriber, ClassResultEntity entity){
        info.getClassScore(entity)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
    //获取学生名单
    public void getStudentRoll(Subscriber<HttpResult<List<StudentScoreResultEntity>>> subscriber, StudentRollRequestEntity entity){
        info.getStudentRoll(entity)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
    //获取筛选后的学生名单
    public void getStudentRollSort(Subscriber<HttpResult<List<StudentRollSortResultEntity>>> subscriber, StudentRollSortRequestEntity entity){
        info.getStudentRollSort(entity)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    //获取学生得分
    public void getStudentScore(Subscriber<HttpResult<StudentScoreResultEntity>> subscriber, StudentDetailRequestEntity entity){
        info.getStudentScore(entity)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
    //获取学生年级/班级排名
    public void getStudentRank(Subscriber<HttpResult<StudentRankResultEntity>> subscriber, StudentDetailRequestEntity entity){
        info.getStudentRank(entity)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
}
