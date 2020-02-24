package com.androidstudydata.propertyanima;

import android.animation.FloatEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.FrameLayout;

import com.androidstudydata.LogUtils;
import com.androidstudydata.Utils;

/**
 * Created by XR_liu on 2018/11/14.
 */
public class AnimaTest {

    public void objectAnimaTest(View targetView){
        ObjectAnimator animator = ObjectAnimator.ofFloat(targetView, "translationX", 100);

        animator.setInterpolator(new LinearInterpolator());
        animator.setEvaluator(new FloatEvaluator());
        animator.setDuration(100);
        animator.start();
    }

    public void valueAnimaTest() {

        ValueAnimator animator = ValueAnimator.ofFloat(0, 10);
        //设置动画时长
        animator.setDuration(300);
        //设置动画执行延迟时间
        animator.setStartDelay(100);
        //设置动画重复次数
        animator.setRepeatCount(0);
        //设置动画重复模式
        animator.setRepeatMode(ValueAnimator.RESTART);

        //设置动画监听器，值的改变都会回调
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float currentValue = (float) animation.getAnimatedValue();
                LogUtils.d("当前动画值=" + currentValue);

                //改变view的属性值
//                View view = new View();
//                view.setProperty(currentValue);
//                view.requestLayout();
            }
        });

        //开始动画
        animator.start();



    }

    //ValueAnimator的ofObject方法的使用
    public void ofObjectBtn(Context context, final Button button) {

        //起初点
        Point startPoint = new Point(0, 0);
        //终点
        Point endPoint = new Point(Utils.getScreenWidth(context)-220, Utils.getScreenHeight(context)-200);

        ValueAnimator animator=ValueAnimator.ofObject(new PointEvaluator(),startPoint,endPoint);
        animator.setDuration(5000);
        animator.setStartDelay(300);

        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {

                Point currentPoint= (Point) animation.getAnimatedValue();
                LogUtils.d("变化的值="+currentPoint.getX()+"///"+currentPoint.getY());
                FrameLayout.LayoutParams layoutParams= new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                //改变btn的属性
                layoutParams.topMargin= (int) currentPoint.getY();
                layoutParams.leftMargin= (int) currentPoint.getX();
                button.setLayoutParams(layoutParams);
                button.requestLayout();
            }
        });

        animator.start();


    }
}
