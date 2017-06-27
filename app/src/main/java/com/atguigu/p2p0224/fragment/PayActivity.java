package com.atguigu.p2p0224.fragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.atguigu.p2p0224.R;
import com.atguigu.p2p0224.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PayActivity extends BaseActivity {


    @Bind(R.id.base_title)
    TextView baseTitle;
    @Bind(R.id.base_back)
    ImageView baseBack;
    @Bind(R.id.base_setting)
    ImageView baseSetting;
    @Bind(R.id.chongzhi_text)
    TextView chongzhiText;
    @Bind(R.id.view)
    View view;
    @Bind(R.id.et_chongzhi)
    EditText etChongzhi;
    @Bind(R.id.chongzhi_text2)
    TextView chongzhiText2;
    @Bind(R.id.yue_tv)
    TextView yueTv;
    @Bind(R.id.btn_chongzhi)
    Button btnChongzhi;


    /*
    * 面试题 ：
    * 模糊查询是在服务器查找的
    * 价格排序是在服务器排序的 (分清楚本地 还是 网络)
    *
    * */

    @Override
    public void initTitle() {
        super.initTitle();
        baseTitle.setText("充值");
        baseBack.setVisibility(View.VISIBLE);
    }

    @Override
    public void initListener() {
        baseBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //给et添加监听事件
        etChongzhi.addTextChangedListener(new TextWatcher() {
            //当文字发生前发生改变回调此方法
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            //当文字发生改变回调此方法
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
            //当文字发生改变后回调此方法
            @Override
            public void afterTextChanged(Editable s) {
                //校验  非0
//                try {
//                    double number = Double.parseDouble(s.toString());
//                }catch (Exception e){
//                    showToast("不能输入非法字符");
//                }

                if (TextUtils.isEmpty(s)){
                    btnChongzhi.setClickable(false);
                    btnChongzhi.setBackgroundResource(R.drawable.btn_02);
                }else{
                    btnChongzhi.setClickable(true);
                    btnChongzhi.setBackgroundResource(R.drawable.btn_01);
                }
            }
        });

        //充值button监听
        btnChongzhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取充值金额
                String number = etChongzhi.getText().toString().trim();
                double money = Double.parseDouble(number);

                //调起支付宝
                
            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_pay;
    }

}
