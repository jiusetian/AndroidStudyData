package com.androidstudydata.propertyanima;

import android.animation.ValueAnimator;

import com.androidstudydata.java.LogUtils;

/**
 * Created by XR_liu on 2018/11/14.
 */
public class AnimaTest {

    private void valueAnimaTest() {

        ValueAnimator animator = ValueAnimator.ofFloat(0, 100);
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
                float currentValue = animation.getAnimatedFraction();
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
}
