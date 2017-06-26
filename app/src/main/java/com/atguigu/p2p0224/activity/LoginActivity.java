package com.atguigu.p2p0224.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.atguigu.p2p0224.R;
import com.atguigu.p2p0224.base.BaseActivity;
import com.atguigu.p2p0224.common.AppNetConfig;
import com.atguigu.p2p0224.utils.HttpUtils;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LoginActivity extends BaseActivity {

    @Bind(R.id.base_title)
    TextView baseTitle;
    @Bind(R.id.base_back)
    ImageView baseBack;
    @Bind(R.id.base_setting)
    ImageView baseSetting;
    @Bind(R.id.tv_login_number)
    TextView tvLoginNumber;
    @Bind(R.id.et_login_number)
    EditText etLoginNumber;
    @Bind(R.id.rl_login)
    RelativeLayout rlLogin;
    @Bind(R.id.tv_login_pwd)
    TextView tvLoginPwd;
    @Bind(R.id.et_login_pwd)
    EditText etLoginPwd;
    @Bind(R.id.btn_login)
    Button btnLogin;
    @Bind(R.id.regitster_tv)
    TextView regitsterTv;

    @Override
    public void initTitle() {
        super.initTitle();
        baseTitle.setText("登录");
    }

    @Override
    public void initListener() {

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //获取输入的账号和密码
                String number = etLoginNumber.getText().toString().trim();
                String pwd = etLoginPwd.getText().toString().trim();
                //校验
                if (TextUtils.isEmpty(number)){
                    showToast("账号不能为空");
                    return;
                }

                if (TextUtils.isEmpty(pwd)){
//                    if (pwd.length()>6){
//                        if ()//有没有非法字符
//                    }
                    showToast("密码不能为空");
                    return;
                }

                //登录
                Map<String, String> map = new HashMap<String, String>();
                map.put("phone",number);
                map.put("password",pwd);
                HttpUtils.getInstance().post(AppNetConfig.LOGIN, map, new HttpUtils.OnHttpClientListener() {
                    @Override
                    public void onSuccess(String json) {
                        Log.d("json", "onSuccess: "+json);
                    }

                    @Override
                    public void onFailure(String message) {
                        Log.d("json", "onFailure: "+message);
                    }
                });


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
        return R.layout.activity_login;
    }

}
