package com.atguigu.p2p0224.view;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2017/6/26.
 */

public class TestDemo extends ViewGroup {

    public TestDemo(Context context) {
        super(context);
    }

    public TestDemo(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

       // canvas.drawCircle(cx,cy,radius,paint);

        //canvas.drawArc();
    }

    /*
                * 用来布局子类的位置
                *
                * */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }
}
