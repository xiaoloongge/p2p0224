package com.atguigu.p2p0224.holder;

import android.view.View;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/6/24.
 */

public abstract class BaseHolder<T> {

    private final View rootView;
    private T t;

    public BaseHolder(){
        rootView = initView();
        ButterKnife.bind(this,rootView);
        rootView.setTag(this);
    }


    public void setData(T t) {
        this.t = t;
        setContent(t);
    }

    public View getView(){
        return rootView;
    }


    /*
    * 创建布局
    *
    * 设置数据
    *
    * */
    protected abstract void setContent(T t);

    public abstract View initView();



}
