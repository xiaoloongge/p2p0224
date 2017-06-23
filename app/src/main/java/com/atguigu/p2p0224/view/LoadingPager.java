package com.atguigu.p2p0224.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
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
    private static final int STATE_ERROR= 1;
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

    private void showSafePager() {
        //保证在主线程中运行
        UIUtils.runOnUIThread(new Runnable(){
            @Override
            public void run() {
                showPager();
            }
        });
    }

    private void showPager() {

        errorView.setVisibility(currentState == STATE_ERROR?View.VISIBLE : View.GONE);
        loadingView.setVisibility(currentState == STATE_LOADING?View.VISIBLE : View.GONE);

        if (successView == null){
            successView = UIUtils.inflate(getLayoutid());
            this.addView(successView);
        }
        successView.setVisibility(currentState == STATE_SUCCESS?View.VISIBLE : View.GONE);

    }

    public abstract int getLayoutid();

    /*
    *
    * 连网操作
    *
    * 连网成功  改变状态 success
    * 连网失败  改变状态  error
    *
    * */
    public void loadNet(){
        String url = getUrl();

        HttpUtils.getInstance().get(url, new HttpUtils.OnHttpClientListener() {
            @Override
            public void onSuccess(String json) {
                currentState = STATE_SUCCESS;
                setResult(successView,json);
                showSafePager();
            }

            @Override
            public void onFailure(String message) {
                currentState = STATE_ERROR;
                showSafePager();
            }
        });
    }

    protected abstract void setResult(View successView, String json);

    /*
    * 获取URL地址
    * */
    protected abstract String getUrl();
}
