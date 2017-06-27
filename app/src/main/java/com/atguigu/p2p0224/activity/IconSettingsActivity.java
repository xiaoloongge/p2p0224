package com.atguigu.p2p0224.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.atguigu.p2p0224.R;
import com.atguigu.p2p0224.base.BaseActivity;
import com.atguigu.p2p0224.common.AppManager;
import com.atguigu.p2p0224.common.AppNetConfig;
import com.atguigu.p2p0224.utils.BitmapUtils;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

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

        //退出监听
        btnUserLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //退出应用
                /*
                * 跳转到LoginActivity
                * 清除Sp(File db)
                * 清除所有的Activity
                *
                *
                * */

                clearSp();
                AppManager.getInstance().removeAll();
                startActivity(new Intent(IconSettingsActivity.this,LoginActivity.class));

            }
        });
    }

    @Override
    public void initData() {

        //加载头像
        Picasso.with(IconSettingsActivity.this)
                .load(AppNetConfig.BASE_URL+"images/tx.png")

                .transform(new Transformation() {
                    @Override
                    public Bitmap transform(Bitmap bitmap) {

                        return BitmapUtils.getBitmap(bitmap);
                    }

                    @Override
                    public String key() {
                        return "CropCircleTransformation()";
                    }
                })
                .into(ivUserIcon);


    }

    @Override
    public void initView() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_icon_settings;
    }


}
