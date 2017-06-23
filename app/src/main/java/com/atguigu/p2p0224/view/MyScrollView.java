package com.atguigu.p2p0224.view;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ScrollView;

/**
 * Created by Administrator on 2017/6/23.
 */

public class MyScrollView extends ScrollView {

    private View childView;
    private int lastY;

    public MyScrollView(Context context) {
        super(context);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    /*
    * 面试题：
    * getY()和getRawY()的区别
    * getY :指的是当前布局到父布局之间y轴的距离
    * getRawY : 指的是当前布局到屏幕之间Y轴的距离
    *
    * */

    /*
    * rect 和 rectf的区别
    * rect存int
    * rectf存float
    *
    * */
    private Rect rect = new Rect();
    //用来标记动画是否执行完成
    private boolean isAnStart = false;
    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        if (isAnStart && getChildCount()==0){
            return super.onTouchEvent(ev);
        }

        int eventY = (int) ev.getY();
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                lastY = eventY;
                break;
            case MotionEvent.ACTION_MOVE:
                //获取偏移量
                int dy = eventY - lastY;
                //存放子view原始位置
                if (rect.isEmpty()){
                    rect.set(childView.getLeft(),childView.getTop(),
                            childView.getRight(),childView.getBottom());
                }
                //改变子布局的位置
                childView.layout(
                        childView.getLeft(),
                        childView.getTop()+dy,
                        childView.getRight(),
                        childView.getBottom()+dy
                );

                lastY = eventY;
                break;
            case MotionEvent.ACTION_UP:
               // if (!rect.isEmpty()){
                    int translateY = childView.getTop()-rect.top;
                    TranslateAnimation animation
                            = new TranslateAnimation(0,0,0,-translateY);
                    animation.setDuration(200);
                    animation.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                            isAnStart = true;
                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {

                            isAnStart = false;

                            childView.layout(rect.left,rect.top,rect.right,rect.bottom);

                            //清除原有的初始标记
                            rect.setEmpty();

                            //childView.clearAnimation();
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });
                    childView.startAnimation(animation);
                //}
                break;
        }
        return true;
    }

    /*
        * 布局加载完成以后回调的方法
        * */
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        //判断子view至少有一个
        if (getChildCount()>0){
            //获取第一个子view
            childView = getChildAt(0);
        }
    }
}
