package com.gdin.analyse.tools;

import android.app.Application;
import android.content.Context;

/**
 * Created by Administrator on 2017/4/10.
 */

public class CustomApplication extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        //获取Context
        context = getApplicationContext();
    }

    //返回
    public static Context getContext(){
        return context;
    }
}
