package com.atguigu.p2p0224.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.atguigu.p2p0224.R;
import com.atguigu.p2p0224.base.BaseFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/6/20.
 */

public class InvestFragment extends BaseFragment {


    @Bind(R.id.tv)
    TextView tv;

    @Override
    protected String getChildUrl() {
        return "";
    }

    /*
    * json 需要注意 不连网的情况下 json是没有数据的
    *
    * */
    @Override
    protected void setContent(String json) {


    }

    @Override
    protected void initTitle() {

        tv.setText("小龙哥太帅了");
    }

    @Override
    protected void initData() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_invest;
    }

}
