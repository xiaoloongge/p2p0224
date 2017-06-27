package com.atguigu.p2p0224.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
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

import java.io.File;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.model.PhotoInfo;
import jp.wasabeef.picasso.transformations.CropCircleTransformation;

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

        //更换头像
        tvUserChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(IconSettingsActivity.this)
                        .setItems(new String[]{"相机","相册"}, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                               if (which == 0){
                                   showCamera();
                               }else{
                                   showPhoto();
                               }
                            }
                        }).show();
            }

        });
    }

    /*
    * 调用相册
    * */
    private void showPhoto() {
        GalleryFinal.openGallerySingle(02, new GalleryFinal.OnHanlderResultCallback() {
            @Override
            public void onHanlderSuccess(int reqeustCode, List<PhotoInfo> resultList) {

                Log.d("image", "onHanlderSuccess: "
                        +resultList.get(0).getPhotoPath());
                makeImage(resultList.get(0).getPhotoPath());

            }

            @Override
            public void onHanlderFailure(int requestCode, String errorMsg) {

            }
        });
        //调起本地相册
//        Intent picture = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//        startActivityForResult(picture, 0);
    }

    private void makeImage(String photoPath) {

        //展示图片
        Picasso.with(this)
                .load(new File(photoPath))
                .transform(new CropCircleTransformation())
                .into(ivUserIcon);
        //上传图片

        //保存到Sp中
        saveImage(photoPath);
    }

    /*
    * 调用相机
    * */
    private void showCamera() {
        GalleryFinal.openCamera(01, new GalleryFinal.OnHanlderResultCallback() {
            @Override
            public void onHanlderSuccess(int reqeustCode, List<PhotoInfo> resultList) {

                Log.d("image", "onHanlderSuccess: "
                        +resultList.get(0).getPhotoPath());
                makeImage(resultList.get(0).getPhotoPath());
            }

            @Override
            public void onHanlderFailure(int requestCode, String errorMsg) {

                Log.d("image", "onHanlderFailure: "+errorMsg);

            }
        });
        //系统调用相机
//        Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        startActivityForResult(camera, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

//        if (requestCode == 0){
//            //拍照
//            Bundle bundle = data.getExtras();
//            // 获取相机返回的数据，并转换为图片格式
//            Bitmap bitmap = (Bitmap) bundle.get("data");
//            ivUserIcon.setImageBitmap(bitmap);
//        }

    }

    @Override
    public void initData() {
        String image = getImage();
        if (TextUtils.isEmpty(image)){
            //加载头像
            Picasso.with(IconSettingsActivity.this)
                    .load(AppNetConfig.BASE_URL+"images/tx.png")

                    .transform(new CropCircleTransformation())
                    .into(ivUserIcon);
        }else{
            //加载头像
            Picasso.with(IconSettingsActivity.this)
                    .load(new File(image))

                    .transform(new CropCircleTransformation())
                    .into(ivUserIcon);
        }
    }

    @Override
    public void initView() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_icon_settings;
    }


}
