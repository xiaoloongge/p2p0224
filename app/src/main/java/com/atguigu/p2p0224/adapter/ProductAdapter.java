package com.atguigu.p2p0224.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.atguigu.p2p0224.R;
import com.atguigu.p2p0224.bean.ProductBean;
import com.atguigu.p2p0224.view.ProgressView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/6/24.
 */

public class ProductAdapter extends BaseAdapter {

    private Context context;
    //可以把集合里的具体数据类型抽取为泛型
    private List<ProductBean.DataBean> list;

    public ProductAdapter(Context context, List<ProductBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }


    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;

        if (convertView == null) {
            //返回一个布局 （抽取：可以把加载布局方法抽取到viewHolder中）
            convertView = View.inflate(context, R.layout.product_item, null);
            //创建一个holder（抽取：让子类去实现创建viewHolder,父类就可以创建抽象方法）
            viewHolder = new ViewHolder(convertView);
            //设置tag （抽取 ：可以把设置tag放到viewHolder中，因为convertView已经放到viewholder中）
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        //获取并设置数据 （抽取：把设置数据方法放在viewholder中，
        // 因为convertView在viewholder中初始化，那么绑定数据放在viewholder中比较合适）
        ProductBean.DataBean dataBean = list.get(position);
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

        /*
        *
        * 第一步 初始化view（因为每个holder中的布局都不一样，所以具体初始化view由子类来实现）
        *
        * 第二步 设置tag
        *
        * 第三步 设置数据
        *
        * */

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
