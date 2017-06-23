package com.atguigu.p2p0224.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
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
    private int strokeWidth = UIUtils.dp2px(20);
    private int height;
    private int width;

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
        paint.setColor(paintColor);
        paint.setStrokeWidth(strokeWidth);
        paint.setAntiAlias(true);
        /*
        * 三种样式
        * stroke 描边
        * fill 填充
        * stroke and fill 即填充又描边
        *
        * */
        paint.setStyle(Paint.Style.STROKE);

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
        int cx = width / 2;
        int cy = height / 2;
        int radius = cx - strokeWidth / 2;
        canvas.drawCircle(cx,cy,radius,paint);
    }
}
