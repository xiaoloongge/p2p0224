package com.atguigu.p2p0224.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.atguigu.p2p0224.R;
import com.atguigu.p2p0224.base.BaseFragment;
import com.atguigu.p2p0224.common.AppNetConfig;

/**
 * Created by Administrator on 2017/6/20.
 */

public class InvestFragment extends BaseFragment {


    @Override
    protected String getChildUrl() {
        return AppNetConfig.INDEX;
    }

    @Override
    protected void setContent(String json) {

    }

    @Override
    protected void initTitle() {

    }

    @Override
    protected void initData() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }
}
