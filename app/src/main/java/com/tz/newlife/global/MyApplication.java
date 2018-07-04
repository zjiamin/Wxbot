package com.tz.newlife.global;

import android.app.Application;
import android.content.Context;

public class MyApplication extends Application {
    /*我们要告知系统，当程序启动的时候应该初始化MyApplication类，而不是默认的Application
    * 在AndroidManifest.xml文件的<application>标签下进行指定就可以了*/

    private static Context context;

    @Override
    public void onCreate() {
        context = getApplicationContext();
        //重写了父类的onCreate()方法
        //并通过getApplicationContext()得到一个应用程序级别的Context
        super.onCreate();
    }

    public static Context getContext() {
        //又提供了一个静态的getContext()方法将刚才的Context返回
        return context;
    }
}
