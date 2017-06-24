package com.atguigu.p2p0224.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.atguigu.p2p0224.R;
import com.atguigu.p2p0224.adapter.base.ProductAdapter1;
import com.atguigu.p2p0224.bean.ProductBean;
import com.atguigu.p2p0224.view.ProgressView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/6/24.
 */

public class ImpAdapter1 extends ProductAdapter1<ProductBean.DataBean> {

    public ImpAdapter1(Context context, List<ProductBean.DataBean> list) {
        super(context, list);
    }

    @Override
    public View getChildView(int position,
                             View convertView, ViewGroup parent,
                             ProductBean.DataBean dataBean) {

        ViewHolder viewHolder = null;

        if (convertView == null) {
            convertView = View.inflate(context, R.layout.product_item, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.pName.setText(dataBean.getName());

        return convertView;
    }

    class ViewHolder {
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

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
