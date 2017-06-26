package com.atguigu.p2p0224.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;

/**
 * Created by Administrator on 2017/6/26.
 */

public class BitmapUtils {

    /*
    *
    * Bitmap.Config ARGB_4444：每个像素占四位，即A=4，R=4，G=4，B=4，那么一个像素点占4+4+4+4=16位

    Bitmap.Config ARGB_8888：每个像素占四位，即A=8，R=8，G=8，B=8，那么一个像素点占8+8+8+8=32位

    Bitmap.Config RGB_565：每个像素占四位，即R=5，G=6，B=5，没有透明度，那么一个像素点占5+6+5=16位

    Bitmap.Config ALPHA_8：每个像素占四位，只有透明度，没有颜色。

    一般情况下我们都是使用的ARGB_8888，由此可知它是最占内存的，因为一个像素占32位，8位=1字节，
    所以一个像素占4字节的内存。假设有一张480x800的图片，如果格式为ARGB_8888，那么将会占用1500KB的内存。
    *
    * */
    public static Bitmap getBitmap(Bitmap source){

        //获取最小的值（宽或高）
        int size = Math.min(source.getWidth(), source.getHeight());
        //创建一个正方形的图片
        Bitmap bitmap = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
        //创建一个画布（图片是画布的背景）
        Canvas canvas = new Canvas(bitmap);
        //创建一个画笔
        Paint paint = new Paint();


        //BitmapShader的作用是使用特定的图片来作为纹理来使用
        /*
        CLMP 如果需要填充的内容大小超过了bitmap size 就选bitmap 边界的颜色进行扩展

        REPEAT重复，不断的重复bitmap去填满，如果绘制的区域大于纹理图片的话，
              纹理图片会在这片区域不断重复

        MIRROR镜像的去填满。如果绘制的区域大于纹理图片的话，纹理图片会以镜像的形式重复出现

        */
        BitmapShader shader =
                new BitmapShader(source, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP);


//        int width = (source.getWidth() - size) / 2;
//        int height = (source.getHeight() - size) / 2;

        int width = (source.getWidth() - size) / 2;
        //向下移动到图片的中间
        int height = (source.getHeight() - size) / 2;
        //图片缩放
        if (width != 0 || height != 0) {
            // source isn't square, move viewport to center
            Matrix matrix = new Matrix();
            //向下是正向上是负
            //像素点相对画布向右width+20,向下移动height+20
            matrix.setTranslate(width+20, height+20);
            //因为图片会被拉伸，所以我们会设置matrix这样图片放大后就不会感觉像是被拉伸了
            shader.setLocalMatrix(matrix);
        }
//        if (width != 0 || height != 0) {
//            // source isn't square, move viewport to center
//            Matrix matrix = new Matrix();
//            matrix.setTranslate(-width, -height);
//            shader.setLocalMatrix(matrix);
//        }
        paint.setShader(shader);
        paint.setAntiAlias(true);//抗锯齿

        //画圆
        float r = size / 2f;
        canvas.drawCircle(r, r, r, paint);

        source.recycle();

        return bitmap;
    }
}
