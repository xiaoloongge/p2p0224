package com.atguigu.p2p0224.common;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

/**
 * Created by Administrator on 2017/6/20.
 */

public class MyApplication extends Application {

    public static Context getContext() {
        return context;
    }
    private static Context context;
    private static Handler handler;
    private static int pid;

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化上下文
        context = this;
        //初始化crashHandler
        //CrashHandler.getInstance().init(this);

        handler = new Handler();
        pid = android.os.Process.myPid();


    }

    public static int getPid(){
        return pid;
    }

    public static Handler getHandler(){
        return handler;
    }
}
