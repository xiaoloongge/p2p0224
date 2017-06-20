package com.atguigu.p2p0224.utils;

import android.content.Context;
import android.view.View;

import com.atguigu.p2p0224.common.MyApplication;

/**
 * Created by Administrator on 2017/6/20.
 */

public class UIUtils {


    /*
    * 加载布局
    * */
    public static View inflate(int id){
        return View.inflate(getContext(),id,null);
    }


    /*
    * 返回一个上下文
    * */
    private static Context getContext() {

        return MyApplication.getContext();
    }


}
