package com.atguigu.p2p0224.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.atguigu.p2p0224.R;
import com.atguigu.p2p0224.activity.IconSettingsActivity;
import com.atguigu.p2p0224.activity.MainActivity;
import com.atguigu.p2p0224.base.BaseFragment;
import com.atguigu.p2p0224.common.AppNetConfig;
import com.atguigu.p2p0224.utils.BitmapUtils;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import butterknife.Bind;
import jp.wasabeef.picasso.transformations.CropCircleTransformation;

/**
 * Created by Administrator on 2017/6/20.
 */

public class PropertyFragment extends BaseFragment {


    @Bind(R.id.tv_settings)
    TextView tvSettings;
    @Bind(R.id.iv_me_icon)
    ImageView ivMeIcon;
    @Bind(R.id.rl_me_icon)
    RelativeLayout rlMeIcon;
    @Bind(R.id.tv_me_name)
    TextView tvMeName;
    @Bind(R.id.rl_me)
    RelativeLayout rlMe;
    @Bind(R.id.recharge)
    ImageView recharge;
    @Bind(R.id.withdraw)
    ImageView withdraw;
    @Bind(R.id.ll_touzi)
    TextView llTouzi;
    @Bind(R.id.ll_touzi_zhiguan)
    TextView llTouziZhiguan;
    @Bind(R.id.ll_zichan)
    TextView llZichan;
    private MainActivity mainActivity;

    @Override
    public String getChildUrl() {
        return "";
    }

    @Override
    public void setContent(String json) {

    }

    @Override
    public void initTitle() {

    }

    @Override
    public void initListener() {
        super.initListener();

        tvSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //起动设置界面
                startActivity(new Intent(getActivity(),IconSettingsActivity.class));
            }
        });
    }

    @Override
    public void initData() {
//        Picasso.with(getActivity())
//                .load(AppNetConfig.BASE_URL+"images/tx.png")
//
//                .transform(new Transformation() {
//                    @Override
//                    public Bitmap transform(Bitmap bitmap) {
//
//                        return BitmapUtils.getBitmap(bitmap);
//                    }
//
//                    @Override
//                    public String key() {
//                        return "CropCircleTransformation()";
//                    }
//                })
//                .into(ivMeIcon);

        mainActivity = (MainActivity)getActivity();



        try {
            String value = mainActivity.getUser().getName();
            String name = new String(value.getBytes("UTF-8"));
            tvMeName.setText(name);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }


    /*
    *
    * 当页面再次显示UI的时候 会调用此方法
    * */
    @Override
    public void onResume() {
        super.onResume();

        String image =mainActivity.getImage();

        /*
        * 判断加载网络图片还是本地图片
        * */
        if (TextUtils.isEmpty(image)){
            //加载头像
            Picasso.with(getActivity())
                    .load(AppNetConfig.BASE_URL+"images/tx.png")

                    .transform(new CropCircleTransformation())
                    .into(ivMeIcon);
        }else{
            //加载头像
            Picasso.with(getActivity())
                    .load(new File(image))
                    .transform(new CropCircleTransformation())
                    .into(ivMeIcon);
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_property;
    }


}
