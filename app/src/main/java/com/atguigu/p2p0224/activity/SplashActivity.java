package com.atguigu.p2p0224.activity;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.atguigu.p2p0224.R;
import com.atguigu.p2p0224.base.BaseActivity;
import com.atguigu.p2p0224.bean.UpdateBean;
import com.atguigu.p2p0224.common.AppManager;
import com.atguigu.p2p0224.common.AppNetConfig;
import com.atguigu.p2p0224.common.ThreadManager;
import com.atguigu.p2p0224.utils.HttpUtils;
import com.atguigu.p2p0224.utils.UIUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.EventListener;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SplashActivity extends BaseActivity {

    @Bind(R.id.iv_welcome_icon)
    ImageView ivWelcomeIcon;
    @Bind(R.id.splash_tv_version)
    TextView splashTvVersion;
    @Bind(R.id.activity_splash)
    RelativeLayout activitySplash;


    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getInstance().removeActivity(this);
    }

    public void initListener() {

    }

    public void initData() {
        //两秒跳转
//        new Timer().schedule(new TimerTask() {
//            @Override
//            public void run() {
//
//            }
//        },2000);

        //设置动画
        AlphaAnimation animation = new AlphaAnimation(0, 1);
        animation.setDuration(500);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //判断是否有网络
                if (isLoadNet()) {
                    //有网络 连网看是否有更新
                    HttpUtils.getInstance().get(AppNetConfig.UPDATE, new HttpUtils.OnHttpClientListener() {
                        @Override
                        public void onSuccess(String json) {
                            //解析数据
                            if (json.indexOf("title") > 0) {
                                //连网失败 :直接进入主界面或登录界面
                                enterActivity();
                            } else {
                                //是否更新
                                final UpdateBean updateBean = JSON.parseObject(json, UpdateBean.class);
                                //或者 判断当前版本号是否小于获取的版本号
                                if (updateBean.getVersion().equals(getVersionCode())) {
                                    //不需要更新
                                    enterActivity();
                                } else {
                                    //需要更新
                                    //通知用户
                                    new AlertDialog.Builder(SplashActivity.this)
                                            .setTitle("更新提示")
                                            .setMessage(updateBean.getDesc())
                                            .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    dialog.dismiss();
                                                    //进入主页或登录页
                                                    enterActivity();
                                                }
                                            })
                                            .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    dialog.dismiss();
                                                    //进行下载
                                                    download(updateBean);
                                                }
                                            })
                                            .show();
                                }
                            }


                        }

                        @Override
                        public void onFailure(String message) {

                        }
                    });
                } else {
                    //没网络 退出应用或者跳到某个界面

                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        ivWelcomeIcon.startAnimation(animation);

    }

    /*
    * 下载
    * */
    private void download(final UpdateBean updateBean) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.show();

                /*
getCacheDir()方法用于获取/data/data/<application package>/cache目录

getFilesDir()方法用于获取/data/data/<application package>/files目录

通过Context.getExternalFilesDir()方法可以获取到 SDCard/Android/data/你的应用的包名/files/ 目录，一般放一些长时间保存的数据

通过Context.getExternalCacheDir()方法可以获取到 SDCard/android/data/你的应用包名/cache/目录，一般存放临时缓存数据
                *
                * */
        final File file;
        //下载到哪里
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            file = getExternalFilesDir("");
        } else {
            file = getFilesDir();
        }
        final File path = new File(file, "update.apk");

        //连网下载
        ThreadManager.getInstance().getThread().execute(new Runnable() {

            private InputStream inputStream;
            private FileOutputStream fos;

            @Override
            public void run() {
                //连网
                try {
                    URL url = new URL(AppNetConfig.BASE_URL+"app_new.apk");
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");//必须是大写
                    connection.setConnectTimeout(5000);
                    connection.setReadTimeout(5000);
                    connection.connect();
                    if (connection.getResponseCode() == 200) {//连网是否成功
                        //给progress设置最大值
                        progressDialog.setMax(connection.getContentLength());
                        inputStream = connection.getInputStream();
                        fos = new FileOutputStream(path);

                        int len;
                        byte[] buffer = new byte[1024];

                        while ((len = inputStream.read(buffer)) != -1) {
                            //设置当前progress的大小
                            progressDialog.incrementProgressBy(len);
                            fos.write(buffer, 0, len);
                        }

                        progressDialog.dismiss();
                        //弹出是否安装
                        Intent install = new Intent(Intent.ACTION_INSTALL_PACKAGE);
                        install.setData(Uri.parse("file:" + path.getAbsolutePath()));
                        startActivity(install);


                    } else {
                        progressDialog.dismiss();
                        showToast("下载失败");
                        enterActivity();
                    }

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {

                    try {
                        if (inputStream != null) {
                            inputStream.close();
                        }
                        if (fos != null) {
                            fos.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }
        });
    }

    /*
    * 判断是否有网络
    * */
    private boolean isLoadNet() {
        boolean connected = false;
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        if (networkInfo != null) {
            connected = networkInfo.isConnected();
        }
        return connected;
    }

    /*
    * 判断是否登录过
    * */
    private boolean isLogin() {
        String name = getUser().getName();
        if (name.equals("admin")) {
            return false;
        } else {
            return true;
        }
    }

    public void initView() {
        //第一个参数是 含有占位字符的字符串 第二个参数是占位字符的值
        splashTvVersion.setText(
                UIUtils.stringFormat(
                        R.string.splash_version,
                        getVersionCode()));
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }

    /*
    * 获取版本号
    * */
    private String getVersionCode() {

        try {
            PackageInfo info = getPackageManager().getPackageInfo(getPackageName(), 0);
            //versionCode应用市场用来区分版本有没有更新
            int versionCode = info.versionCode;
            //versionName是给用户看的
            String versionName = info.versionName;
            return versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return "3";
    }

    /*
    * 判断进入主界面还是登录界面
    * */
    public void enterActivity() {
        //判断账号是否登录过，如果没有登录跳转到登录界面，或者跳转到主界面
        if (isLogin()) {
            startActivity(new Intent(SplashActivity.this, MainActivity.class));

        } else {
            startActivity(new Intent(SplashActivity.this, LoginActivity.class));

        }
        //清除动画
        ivWelcomeIcon.clearAnimation();
        finish();
    }
}
