package com.atguigu.p2p0224.holder;

import android.view.View;
import android.widget.TextView;

import com.atguigu.p2p0224.R;
import com.atguigu.p2p0224.bean.ProductBean;
import com.atguigu.p2p0224.utils.UIUtils;
import com.atguigu.p2p0224.view.ProgressView;

import butterknife.Bind;

/**
 * Created by Administrator on 2017/6/24.
 */

public class ProductHolder extends BaseHolder<ProductBean.DataBean> {

    @Bind(R.id.p_name)
    TextView pName;
    @Bind(R.id.p_money)
    TextView pMoney;
    @Bind(R.id.p_yearlv)
    TextView pYearlv;
    @Bind(R.id.p_suodingdays)
    TextView pSuodingdays;
    @Bind(R.id.p_minzouzi)
    TextView pMinzouzi;
    @Bind(R.id.p_minnum)
    TextView pMinnum;
    @Bind(R.id.p_progresss)
    ProgressView pProgresss;

    @Override
    public View initView() {
        return View.inflate(UIUtils.getContext(), R.layout.product_item, null);
    }

    @Override
    protected void setContent(ProductBean.DataBean dataBean) {

        pName.setText(dataBean.getName());
    }
}
