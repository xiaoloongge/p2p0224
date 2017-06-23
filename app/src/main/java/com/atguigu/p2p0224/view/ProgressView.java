package com.atguigu.p2p0224.view;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.atguigu.p2p0224.utils.UIUtils;

/**
 * Created by Administrator on 2017/6/23.h
 *
 * 自定义控件：
 * 第一步 先考虑继承 view , viewGroup, 控件
 *
 *
 */

/*
* 适配：代码适配（动态设置高度，或者加LinearLayout设置权重）
*
* */

public class ProgressView extends View {

    private Paint paint;
    private int paintColor = Color.BLACK;
    private int strokeWidth = UIUtils.dp2px(10);
    private int height;
    private int width;
    private int sweepAngle = 0;

    public ProgressView(Context context) {
        super(context);

        init();
    }

    public ProgressView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setAntiAlias(true);//去除锯齿
        /*
        * 三种样式
        * stroke 描边
        * fill 填充
        * stroke and fill 即填充又描边
        *
        * */
        paint.setStyle(Paint.Style.STROKE);//设置圆环填充的样式

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        height = getHeight();
        width = getWidth();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        /*
        * 第一 画出三个部分 1 画圆 2 画弧 3 画文字
        *
        * */
        //画圆
        paint.setStrokeWidth(strokeWidth);//画笔的宽度
        paint.setColor(Color.BLACK);//圆环的颜色
        int cx = width / 2;//圆心的x坐标
        int cy = height / 2;//圆心的y坐标
        int radius = cx - strokeWidth / 2;//圆环的半径
        canvas.drawCircle(cx,cy,radius,paint);

        //画弧
        paint.setColor(Color.RED);//弧度的颜色
        RectF rectF = new RectF();
        //圆弧的坐上顶点和右下顶点
        rectF.set(strokeWidth/2,strokeWidth/2,width-strokeWidth/2,height-strokeWidth/2);
        canvas.drawArc(rectF,0,sweepAngle,false,paint);

        //画文字
        paint.setStrokeWidth(0); //设置画笔的宽度
        String str = sweepAngle+"%";
        paint.setTextSize(30);//设置文字的大小
        Rect rect = new Rect();
        paint.getTextBounds(str,0,str.length(),rect);//获取文字的宽高
        int textWidth = rect.width(); //文字的宽
        int textHeight = rect.height();//文字的高
        float x = width / 2 - textWidth / 2; //左下顶点的x坐标
        float y = height / 2 + textHeight / 2;//左下顶点的y坐标
        canvas.drawText(str,x,y,paint);

    }


    /*
    * 面试题：
    * invalidate和postInvalidate的区别是什么
    * invalidate是主线程进行强制重绘
    * postInvalidate是分线程进行强制重绘
    * */
    public void setSweepAngle(final int sweepAngle){
//        for (int i = 0; i < sweepAngle; i++) {
//            this.sweepAngle = i;
//            //强制重绘
//            invalidate();
//            //postInvalidate();
//        }
        ValueAnimator animator = ValueAnimator.ofInt(0,sweepAngle);
        animator.setDuration(5000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {

                int a = (int) animation.getAnimatedValue();

                ProgressView.this.sweepAngle = a;
                invalidate();
            }
        });
        animator.start();

    }
}
