package com.atguigu.p2p0224.utils;

import android.content.Context;
import android.view.View;

import com.atguigu.p2p0224.common.MyApplication;

import static android.R.attr.key;

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


    /*
    * 格式化字符串 - 占位字符
    * */
    public static String stringFormat(int id,String value){
        String versionName = String.format(getString(id), value);
        return versionName;
    }

    /*
    * 从string文件获取字符串
    * */
    public static String getString(int id){
        return getContext().getResources().getString(id);
    }


}
