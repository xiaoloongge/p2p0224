package com.atguigu.p2p0224.common;

import android.app.Activity;
import android.util.Log;

import java.util.Stack;

/**
 * Created by Administrator on 2017/6/21.
 */

public class AppManager {

    /*
    * 单例
    * 第一步 私有化构造器
    * 第二步 创建实例
    * 第三步 创建一个方法 返回实例
    *
    * */

    private AppManager(){};

    private static AppManager appManager = new AppManager();

    public static AppManager getInstance(){
        return appManager;
    }

    private Stack<Activity> stack = new Stack<>();
    /*
    * 添加activity
    * */
    public void addActivity(Activity activity){
        Log.d("stack", "addActivity: "+activity.getClass().toString());
        //校验
        if (activity != null){
            stack.add(activity);
        }
    }

    /*
    * 删除activity
    *
    * */
    public void removeActivity(Activity activity){

//        Log.d("stack", "removeActivity: "+activity.getClass().toString());
        if (activity != null){

            for (int i = stack.size()-1; i >= 0; i--) {
                Activity currentActivity = stack.get(i);
                //Log.d("stack", "currentActivity: "+currentActivity.getClass().toString());
                if (currentActivity.getClass().equals(activity.getClass())){

                    currentActivity.finish();
                    stack.remove(i);
                }
//                if (currentActivity == activity){
//
//                }
            }
        }
    }

    /*
    * 删除所有的Activity
    *
    * */

    public void removeAll(){

        for (int i = stack.size()-1; i >=0 ; i--) {

            Activity currentActivity = stack.get(i);

            if (currentActivity != null){
                currentActivity.finish();
                stack.remove(i);
            }
        }
    }

}
