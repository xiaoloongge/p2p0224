package com.atguigu.p2p0224.view;

import android.content.Context;
import android.icu.lang.UScript;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import com.atguigu.p2p0224.R;
import com.atguigu.p2p0224.utils.HttpUtils;
import com.atguigu.p2p0224.utils.UIUtils;

/**
 * Created by Administrator on 2017/6/23.
 */

public abstract class LoadingPager extends FrameLayout {

    private View errorView;
    private View loadingView;
    private static final int STATE_LOADING = 0;
    private static final int STATE_ERROR = 1;
    private static final int STATE_SUCCESS = 2;

    private int currentState = STATE_LOADING;
    private View successView;

    public LoadingPager(@NonNull Context context) {
        super(context);
        init();
    }

    public LoadingPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    private void init() {
        //添加不同的布局

        errorView = UIUtils.inflate(R.layout.page_error);
        this.addView(errorView);
        loadingView = UIUtils.inflate(R.layout.page_loading);
        this.addView(loadingView);

        showSafePager();
    }

    /*
    * 展示界面必须在主线程
    *
    * */
    private void showSafePager() {
        //保证在主线程中运行
        UIUtils.runOnUIThread(new Runnable() {
            @Override
            public void run() {
                showPager();
            }
        });
    }

    /*
    *
    *
    * 展示界面 根据的是当前的状态
    * */
    private void showPager() {

        errorView.setVisibility(currentState == STATE_ERROR ? View.VISIBLE : View.GONE);
        loadingView.setVisibility(currentState == STATE_LOADING ? View.VISIBLE : View.GONE);

        if (successView == null) {
            successView = getView();
            this.addView(successView);
        }

        successView.setVisibility(currentState == STATE_SUCCESS ? View.VISIBLE : View.GONE);

        //setResult(successView,"");
    }

    public abstract View getView();

    /*
    *
    * 连网操作
    *
    * 连网成功  改变状态 success
    * 连网失败  改变状态  error
    *
    * */
    public void loadNet() {

        String url = getUrl();
        //判断是否加载网络
        if (TextUtils.isEmpty(url)){
            //不加载网络
            currentState = STATE_SUCCESS;
            showSafePager();
            setResult(successView,"");
        }else{
            //加载网络
            HttpUtils.getInstance().get(url, new HttpUtils.OnHttpClientListener() {
                @Override
                public void onSuccess(String json) {
                    Log.d("loadingPager", "onSuccess: "+json);
                    //处理当前获取的JSON串是否是网页
                    if (json.indexOf("title") > 0){
                        //currentState = STATE_ERROR;
                        loadState = LoadState.ERROR;
                        //showSafePager();
                        //设置状态
                        showState();
                    }else{
                        //改变当前状态
                        //currentState = STATE_SUCCESS;
                        loadState = LoadState.SUCCESS;
                        loadState.setJson(json);
                        //setResult(successView, json);
                        //showSafePager();
                        showState();
                    }

                }


                @Override
                public void onFailure(String message) {
                    Log.d("loadingPager", "onSuccess: "+message);
                    loadState = LoadState.ERROR;
                    //showSafePager();
                    showState();
                }
            });
        }

    }

    private void showState() {

        switch (loadState){
            case SUCCESS:
                currentState = STATE_SUCCESS;
                break;
            case ERROR:
                currentState = STATE_ERROR;
                break;
            case LOADING:
                currentState = STATE_LOADING;
                break;
        }

        showSafePager();

        if (currentState == STATE_SUCCESS){
            setResult(successView, loadState.SUCCESS.getJson());
        }

    }

    /*
    * 枚举 放了 成功 失败 加载中的常量
    *
    * */
    private LoadState loadState;
    public enum LoadState{

        SUCCESS(0),ERROR(1),LOADING(2);

        private int state;
        private String json;
        LoadState(int state){
            this.state = state;
        }

        public void setJson(String json){
            this.json = json;
        }

        public String getJson(){
            return json;
        }
    }

    protected abstract void setResult(View successView, String json);

    /*
    * 获取URL地址
    * */
    protected abstract String getUrl();
}
