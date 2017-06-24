package com.atguigu.p2p0224.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.atguigu.p2p0224.R;
import com.atguigu.p2p0224.adapter.ImpAdapter1;
import com.atguigu.p2p0224.adapter.ImpAdapter2;
import com.atguigu.p2p0224.adapter.ImpAdapter3;
import com.atguigu.p2p0224.adapter.ProductAdapter;
import com.atguigu.p2p0224.base.BaseFragment;
import com.atguigu.p2p0224.bean.ProductBean;
import com.atguigu.p2p0224.common.AppNetConfig;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/6/24.
 */

public class InvestAllFragment extends BaseFragment {

    @Bind(R.id.lv)
    ListView lv;

    @Override
    public String getChildUrl() {
        return AppNetConfig.PRODUCT;
    }

    @Override
    public void setContent(String json) {
        super.setContent(json);


        ProductBean productBean = JSON.parseObject(json, ProductBean.class);

        lv.setAdapter(new ImpAdapter3(getActivity(),productBean.getData()));

    }

    @Override
    public void initTitle() {

    }

    @Override
    public void initData() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_all;
    }

}
