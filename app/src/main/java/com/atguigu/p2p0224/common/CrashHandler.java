package com.atguigu.p2p0224.common;

import android.content.Context;
import android.os.Build;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import com.atguigu.p2p0224.utils.UIUtils;

/**
 * Created by Administrator on 2017/6/21.
 */

public class CrashHandler implements Thread.UncaughtExceptionHandler {

    private CrashHandler(){};

    private  static CrashHandler crashHandler = new CrashHandler();

    public static CrashHandler getInstance(){
        return crashHandler;
    }

    private Context context;
    public void init(Context context){
        //告诉系统 崩溃的操作 由我来执行
        Thread.setDefaultUncaughtExceptionHandler(this);
        this.context = context;
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {

        Log.d("crash", "uncaughtException: "+e.getMessage());
        /*
        * 第一 提醒用户
        * 第二 收集异常
        * 第三 退出应用
        *
        *
        * */

//        ThreadManager.getInstance().getThread().execute(new Runnable() {
//            @Override
//            public void run() {
//
//            }
//        });
        new Thread(){
            @Override
            public void run() {
                Looper.prepare();
                //执行在主线程中的代码
                Toast.makeText(context, "系统崩溃了哥很伤心！！！", Toast.LENGTH_SHORT).show();
                Looper.loop();
            }
        }.start();


        collection(e);

        AppManager.getInstance().removeAll();
        //杀死当前的进程
        android.os.Process.killProcess(android.os.Process.myPid());
        //参数 除了0以外都表示非正常退出
        System.exit(0);//退出虚拟机
    }


    /*
    * 收集异常信息
    * */
    private void collection(Throwable e) {

        String version = Build.BOARD;

        //发送给服务器

    }
}
