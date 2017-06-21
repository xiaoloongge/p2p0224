package com.atguigu.p2p0224.activity;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.atguigu.p2p0224.R;
import com.atguigu.p2p0224.common.AppManager;
import com.atguigu.p2p0224.fragment.HomeFragment;
import com.atguigu.p2p0224.fragment.InvestFragment;
import com.atguigu.p2p0224.fragment.MoreFragment;
import com.atguigu.p2p0224.fragment.PropertyFragment;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.main_fl)
    FrameLayout mainFl;
    @Bind(R.id.rb_main)
    RadioButton rbMain;
    @Bind(R.id.rb_invest)
    RadioButton rbInvest;
    @Bind(R.id.rb_propert)
    RadioButton rbPropert;
    @Bind(R.id.rb_more)
    RadioButton rbMore;
    @Bind(R.id.main_rg)
    RadioGroup mainRg;

    private HomeFragment homeFragment;
    private InvestFragment investFragment;
    private MoreFragment moreFragment;
    private PropertyFragment propertyFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        AppManager.getInstance().addActivity(this);

        //初始化控件
        initView();
        //初始化数据
        initData();
        //事件监听
        initListener();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getInstance().removeActivity(this);
    }

    private void initListener() {

        //radioGroup监听
        mainRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {

               switchFrgment(checkedId);
            }
        });
    }

    /*
    * 切换fragment
    *
    * */
    public void switchFrgment(int checkedId){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        hidden(transaction);

        switch (checkedId){
            case R.id.rb_invest:
                if (investFragment == null){
                    investFragment = new InvestFragment();
                    transaction.add(R.id.main_fl,investFragment);
                }else{
                    transaction.show(investFragment);
                }
                break;
            case R.id.rb_main:
                if (homeFragment == null){
                    homeFragment = new HomeFragment();
                    transaction.add(R.id.main_fl,homeFragment);
                }else{
                    transaction.show(homeFragment);
                }
                break;
            case R.id.rb_more:
                if (moreFragment == null){
                    moreFragment = new MoreFragment();
                    transaction.add(R.id.main_fl,moreFragment);
                }else{
                    transaction.show(moreFragment);
                }
                break;
            case R.id.rb_propert:
                if (propertyFragment == null){
                    propertyFragment = new PropertyFragment();
                    transaction.add(R.id.main_fl,propertyFragment);
                }else{
                    transaction.show(propertyFragment);
                }
                break;
        }
        transaction.commit();
    }

    /*
    * 隐藏fragment
    *
    * */
    private void hidden(FragmentTransaction transaction) {
        if (investFragment != null){
            transaction.hide(investFragment);
        }
        if (moreFragment != null){
            transaction.hide(moreFragment);
        }
        if (homeFragment != null){
            transaction.hide(homeFragment);
        }
        if (propertyFragment != null){
            transaction.hide(propertyFragment);
        }

    }

    private void initData() {


    }

    private void initView() {

        switchFrgment(R.id.rb_main);
    }

    /*
    *
    *
    * */
    public <T> T instance(int id) {
        return (T) findViewById(id);
    }

    /*
    * 双击退出
    * */
    private boolean isExit = false;
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){

            if (isExit){
                finish();
            }
            Toast.makeText(this, "臭不要脸不要摸我", Toast.LENGTH_SHORT).show();
            isExit = true;
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    isExit = false;
                }
            },2000);
            return true;
        }
        return super.onKeyUp(keyCode,event);
    }
}
