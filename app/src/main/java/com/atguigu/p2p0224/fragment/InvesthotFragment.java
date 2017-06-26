package com.atguigu.p2p0224.fragment;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.atguigu.p2p0224.R;
import com.atguigu.p2p0224.base.BaseFragment;
import com.atguigu.p2p0224.utils.UIUtils;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/6/24.
 */

public class InvesthotFragment extends BaseFragment {

    @Bind(R.id.id_flowlayout)
    TagFlowLayout idFlowlayout;
    private String[] names = new String[]{"新手福利计划", "财神道90天计划", "硅谷钱包计划", "30天理财计划(加息2%)", "180天理财计划(加息5%)", "月月升理财计划(加息10%)",
            "中情局投资商业经营", "大学老师购买车辆", "屌丝下海经商计划", "美人鱼影视拍摄投资", "Android培训老师自己周转", "养猪场扩大经营",
            "旅游公司扩大规模", "铁路局回款计划", "屌丝迎娶白富美计划"
    };

    @Override
    public String getChildUrl() {
        return null;
    }

    @Override
    public void initTitle() {

    }

    @Override
    public void initData() {

        idFlowlayout.setAdapter(new TagAdapter<String>(names)
        {
            @Override
            public View getView(FlowLayout parent, int position, String s)
            {
                TextView tv = new TextView(getActivity());
                tv.setTextSize(20);
                tv.setTextColor(Color.WHITE);
                //获取shape布局的实例对象
                Drawable drawable = getResources().getDrawable(R.drawable.hot_shape);
                tv.setBackgroundDrawable(drawable);
                //获取textView的背景
                GradientDrawable gd = (GradientDrawable) tv.getBackground();
                //设置背景色
                gd.setColor(UIUtils.getColor());
                tv.setText(s);
                return tv;
            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_hot;
    }

}
