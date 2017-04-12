package com.gdin.analyse.info;

import com.gdin.analyse.entity.HttpResult;
import com.gdin.analyse.entity.LoginDataEntity;
import com.gdin.analyse.entity.SchoolEntity;

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
    private final static String BASE_URL = "http://120.77.34.81/";

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


    public void getSchoolData(Subscriber<HttpResult<List<SchoolEntity>>> subscriber){
        info.getSchoolData()
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
    public void checkLogin(Subscriber<HttpResult<List<LoginDataEntity>>> subscriber,LoginDataEntity entity){
        info.checkLogin(entity)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

}
