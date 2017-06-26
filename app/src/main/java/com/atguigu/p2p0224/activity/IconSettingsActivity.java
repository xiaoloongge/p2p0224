package com.atguigu.p2p0224.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.atguigu.p2p0224.R;
import com.atguigu.p2p0224.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

public class IconSettingsActivity extends BaseActivity {


    @Bind(R.id.base_title)
    TextView baseTitle;
    @Bind(R.id.base_back)
    ImageView baseBack;
    @Bind(R.id.base_setting)
    ImageView baseSetting;
    @Bind(R.id.iv_user_icon)
    ImageView ivUserIcon;
    @Bind(R.id.tv_user_change)
    TextView tvUserChange;
    @Bind(R.id.btn_user_logout)
    Button btnUserLogout;

    @Override
    public void initTitle() {
        super.initTitle();
        baseTitle.setText("头像设置");
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
    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_icon_settings;
    }


}
