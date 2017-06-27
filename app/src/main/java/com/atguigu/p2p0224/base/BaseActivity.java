package com.atguigu.p2p0224.base;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.atguigu.p2p0224.bean.LoginBean;
import com.atguigu.p2p0224.common.AppManager;

import java.io.UnsupportedEncodingException;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/6/26.
 */

public abstract class BaseActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);

        AppManager.getInstance().addActivity(this);

        //初始化控件
        initView();
        //初始化数据
        initData();
        //事件监听
        initListener();
        //初始化标题
        initTitle();
    }

    public abstract void initListener();

    public abstract void initData();

    public abstract void initView();

    public abstract int getLayoutId();

    public void initTitle(){

    }

    /*
        *
        * 初始化对象
        * */
    public <T> T instance(int id) {
        return (T) findViewById(id);
    }

    /*
    *
    * 弹出吐司
    * */
    public void showToast(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }


    /*
    * 存储用户信息
    * */
    private String spName = "loginbean";
    public void saveUser(LoginBean bean){
        SharedPreferences sp = getSharedPreferences(spName, MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
//        try {
//            //String name = new String(bean.getName().getBytes(), "G");
//
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
        edit.putString("name",bean.getName());
        edit.putString("imageurl",bean.getImageurl());
        edit.putString("iscredit",bean.getIscredit());
        edit.putString("phone",bean.getPhone());
        edit.commit();

    }
    /*
    * 获取用户信息
    * */
    public LoginBean getUser(){
        SharedPreferences sp = getSharedPreferences(spName, MODE_PRIVATE);
        LoginBean bean = new LoginBean();
        bean.setName(sp.getString("name","admin"));
        bean.setImageurl(sp.getString("imageurl",""));
        bean.setIscredit(sp.getString("iscredit",""));
        bean.setPhone(sp.getString("phone",""));
        return bean;
    }

    /*
    * 保存图片地址
    * */
    public void saveImage(String image){
        SharedPreferences sp = getSharedPreferences(spName, MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putString("imageurl",image);
        edit.putBoolean("isFile",true);
        edit.commit();
    }
    /*
    * 获取图片
    * */
    public String getImage(){
        SharedPreferences sp = getSharedPreferences(spName, MODE_PRIVATE);
        boolean isFile = sp.getBoolean("isFile", false);
        if (isFile){
            return sp.getString("imageurl","");
        }else{
            return "";
        }
    }

    /*
    * 清除sp
    * */
    public void clearSp(){
        //清除Sp文件（清除的是sp内容）
        SharedPreferences sp = getSharedPreferences(spName, MODE_PRIVATE);
        sp.edit().clear().commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);

    }
}
