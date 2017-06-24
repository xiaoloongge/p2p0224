package com.atguigu.p2p0224.adapter.base;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.atguigu.p2p0224.R;
import com.atguigu.p2p0224.holder.BaseHolder;
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
public abstract class ProductAdapter3<T>  extends BaseAdapter{

    public Context context;
    public List<T> list;

    public ProductAdapter3(Context context, List<T> list) {
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


       BaseHolder viewHolder = null;

        if (convertView == null) {

            viewHolder = getViewHolder();

        }else{
            viewHolder = (BaseHolder) convertView.getTag();
        }

        viewHolder.setData(list.get(position));

        return viewHolder.getView();
    }

    protected abstract BaseHolder getViewHolder();

}
