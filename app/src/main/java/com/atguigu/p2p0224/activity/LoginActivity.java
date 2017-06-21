package com.atguigu.p2p0224.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.atguigu.p2p0224.R;
import com.atguigu.p2p0224.common.AppManager;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        AppManager.getInstance().addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getInstance().removeActivity(this);
    }
}
