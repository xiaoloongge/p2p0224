package com.atguigu.p2p0224.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alipay.sdk.encrypt.MD5;
import com.atguigu.p2p0224.R;
import com.atguigu.p2p0224.base.BaseActivity;
import com.atguigu.p2p0224.common.AppNetConfig;
import com.atguigu.p2p0224.utils.HttpUtils;
import com.atguigu.p2p0224.utils.Md5;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RegisterActivity extends BaseActivity {


    @Bind(R.id.base_title)
    TextView baseTitle;
    @Bind(R.id.base_back)
    ImageView baseBack;
    @Bind(R.id.base_setting)
    ImageView baseSetting;
    @Bind(R.id.et_register_number)
    EditText etRegisterNumber;
    @Bind(R.id.et_register_name)
    EditText etRegisterName;
    @Bind(R.id.et_register_pwd)
    EditText etRegisterPwd;
    @Bind(R.id.et_register_pwdagain)
    EditText etRegisterPwdagain;
    @Bind(R.id.btn_register)
    Button btnRegister;

    @Override
    public void initTitle() {
        super.initTitle();
        baseTitle.setText("注册");
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

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //获取输入的数据
                String name = etRegisterName.getText().toString().trim();
                String number = etRegisterNumber.getText().toString().trim();
                String pwd = etRegisterPwd.getText().toString().trim();
                String pwdAgain = etRegisterPwdagain.getText().toString().trim();

                if (TextUtils.isEmpty(number)){
                    showToast("手机号不能为空");
                    return;
                }
                if (TextUtils.isEmpty(name)){
                    showToast("用户名不能为空");
                    return;
                }
                if (!TextUtils.isEmpty(pwd)){
                    if (!pwd.equals(pwdAgain)){
                        showToast("两次密码不一致");
                        return;
                    }
                }else{
                    showToast("密码不能为空");
                    return;
                }

                //连网
                Map<String, String> map = new HashMap<String, String>();
                map.put("name",name);
                map.put("phone",number);
                map.put("password", Md5.Md5_16(pwd));

                HttpUtils.getInstance().post(AppNetConfig.REGISTER, map, new HttpUtils.OnHttpClientListener() {
                    @Override
                    public void onSuccess(String json) {
                        Log.d("register", "onSuccess: "+json);
                        try {
                            JSONObject obj = new JSONObject(json);
                            boolean isExist = obj.getBoolean("isExist");
                            if (isExist){
                                showToast("用户已存在");
                            }else{
                                showToast("注册成功");
                                finish();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(String message) {
                        Log.d("register", "onFailure: "+message);
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
        return R.layout.activity_register;
    }


}
