package com.atguigu.p2p0224.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.atguigu.p2p0224.view.LoadingPager;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/6/21.
 */

public abstract class BaseFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

//        if (getLayoutId() == 0){
//            TextView view = new TextView(getActivity());
//            view.setText("你呀小白啊");
//            return view;
//        }
//
//        View view = View.inflate(
//                getActivity(),
//                getLayoutId(),
//                null
//        );
//        ButterKnife.bind(this,view);
//
//        initView();
//        initTitle();
//        initData();
//        initListener();

        LoadingPager loadingPager = new LoadingPager(getActivity()) {
            @Override
            public int getLayoutid() {
                return BaseFragment.this.getLayoutId();
            }

            @Override
            protected void setResult(View successView, String json) {

            }

            @Override
            protected String getUrl() {
                return null;
            }
        };

        return loadingPager;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //ButterKnife.unbind(this);
    }

    protected abstract void initTitle();

    /*
    * 重写
    * */
    private void initListener() {

    }

    protected abstract void initData();

    /*
    * 可以重写
    *
    * */
    private void initView() {

    }

    public abstract int getLayoutId();
}
