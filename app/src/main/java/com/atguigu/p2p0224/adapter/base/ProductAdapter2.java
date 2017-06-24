package com.atguigu.p2p0224.adapter.base;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.atguigu.p2p0224.R;
import com.atguigu.p2p0224.adapter.ProductAdapter;
import com.atguigu.p2p0224.bean.ProductBean;
import com.atguigu.p2p0224.view.ProgressView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/6/24.
 */


/*
*
* 第一种抽取方法 只是在getView中添加了一个抽象方法
*  注意：
*       返回view
*
* */
public abstract class ProductAdapter2<T>  extends BaseAdapter{

    public Context context;
    public List<T> list;

    public ProductAdapter2(Context context, List<T> list) {
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


         /*
        *
        * 优化三步
        * 第一步 抽出initView
        * 第二步 抽出setTag
        * 第三步 抽出设置数据
        *
        * */


       ViewHolder viewHolder = null;

        if (convertView == null) {
            convertView = initView();
            viewHolder = new ViewHolder(convertView);
            //convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        //ProductBean.DataBean dataBean = list.get(position);

        //viewHolder.pName.setText(dataBean.getName());

        setData(list.get(position));
        return convertView;
    }

    protected abstract void setData(T t);

    protected abstract View initView();

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
            view.setTag(this);
        }
    }
}
