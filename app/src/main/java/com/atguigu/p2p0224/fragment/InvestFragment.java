package com.atguigu.p2p0224.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.atguigu.p2p0224.R;
import com.atguigu.p2p0224.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/6/20.
 */

public class InvestFragment extends BaseFragment implements View.OnClickListener {


    @Bind(R.id.tv_invest_all)
    TextView tvInvestAll;
    @Bind(R.id.tv_invest_recommend)
    TextView tvInvestRecommend;
    @Bind(R.id.tv_invest_hot)
    TextView tvInvestHot;
    @Bind(R.id.vp_invest)
    ViewPager vpInvest;

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

    }

    private List<BaseFragment> listFragment;
    @Override
    protected void initData() {
       initViewPager();
        //设置默认选中的TV
        setSelectTv(tvInvestAll);
    }


    /*
    * 设置选中的TextView
    * */
    private void setSelectTv(View view) {
        setDefaultAll(tvInvestHot);
        setDefaultAll(tvInvestRecommend);
        setDefaultAll(tvInvestAll);
        TextView tv = (TextView) view;
        tv.setTextColor(Color.YELLOW);
        tv.setBackgroundColor(Color.BLUE);
    }

    /*
    *恢复默认textview的属性
    * */
    private void setDefaultAll(View view) {
        TextView tv = (TextView) view;
        tv.setTextColor(Color.BLACK);
        tv.setBackgroundColor(Color.WHITE);
    }

    @Override
    public void initListener() {
        super.initListener();

        //vp监听
        vpInvest.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                selectTv(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        //textView监听
        tvInvestAll.setOnClickListener(this);
        tvInvestRecommend.setOnClickListener(this);
        tvInvestHot.setOnClickListener(this);
    }

    /*
    * 根据position切换不同的标题
    * */
    private void selectTv(int position) {
        switch (position){
            case 0:
                setSelectTv(tvInvestAll);
                break;
            case 1:
                setSelectTv(tvInvestRecommend);
                break;
            case 2:
                setSelectTv(tvInvestHot);
                break;
        }
    }

    /*
        * 初始化fragmentAdapter
        * */
    private void initViewPager() {
        listFragment = new ArrayList<>();
        listFragment.add(new InvestAllFragment());
        listFragment.add(new InvesthotFragment());
        listFragment.add(new InvestReFragment());
        vpInvest.setAdapter(new MyAdapter(getFragmentManager()));
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_invest;
    }


    /*
    * Kotlin
    * */
    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.tv_invest_all:
                vpInvest.setCurrentItem(0);
                break;
            case R.id.tv_invest_recommend:
                vpInvest.setCurrentItem(1);
                break;
            case R.id.tv_invest_hot:
                vpInvest.setCurrentItem(2);
                break;
        }

    }


    /*
    *
    * FragmentPagerAdapter
    * FragmentStatePagerAdapter
    * 面试题这两个有什么区别？
    * * FragmentPagerAdapter:该类内的每一个生成的 Fragment 都将保存在内存之中，
    * 因此适用于那些`相对静态的页，
    * 数量也比较少`的那种；如果需要处理有很多页，并且数据动态性较大、占用内存较多的情况，
    * 应该使用FragmentStatePagerAdapter

    * FragmentStatePagerAdapter:该 PagerAdapter 的实现将只保留当前页面，
    * 当页面离开视线后，就会被消除，释放其资源；而在页面需要显示时，
    * 生成新的页面(就像 ListView 的实现一样)。这么实现的好处就是当拥有大量的页面时，
    * 不必在内存中占用大量的内存。
    *
    * */
    class MyAdapter extends FragmentPagerAdapter{

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return listFragment.get(position);
        }

        @Override
        public int getCount() {
            return listFragment.size();
        }
    }

}
