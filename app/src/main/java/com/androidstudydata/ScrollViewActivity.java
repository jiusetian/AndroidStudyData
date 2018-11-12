package com.androidstudydata;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * view的scroll相关测试，得到的结论
 * 1.scrollTo（x,y）参数x大于0时，内容view从右向左移动x距离，而且不管之前内容view在哪个位置，此时都会移动到相对于
 * 父view的x坐标位置，此时父view的getScrollX的值也是大于0。
 * getScrollX的值总是等于内容view左边缘和父view左边缘的距离,而且在父view的右边是负数，左边是正数
 *
 * 2.scrollTo是内容view移动的绝对位置，参数值是父view坐标系的坐标值，只是正数值相反
 *
 * 3.几个重要的方法解析
 *   -- getX和getLeft，getX返回的是view移动后相对于父view的位置，getLeft返回view初始位置，以后不会改变
 *   -- scrollTo和对view进行属性动画translation后，对getX的不同影响，发现scrollTo以后对getX的值没有影响，但是
 *   translation以后会有影响
 *   -- getX返回值为getLeft()+getTranslationX()，当setTranslationX()时getLeft()不变，getX()变
 *
 *  4.事实证明，getTranslationX的值只跟view设置了translation才有关系，跟view设置了scroll没关系，同样getScroll的值也是
 *
 *  5.getScroll获取view相对于初始位置的滑动的偏移量，而且原始位置的右边是负数，左边是正数
 */
public class ScrollViewActivity extends AppCompatActivity {
    private String TAG = "tag";
    TextView tv;
    LinearLayout containerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_view);

        containerView = findViewById(R.id.container);
        tv = findViewById(R.id.scroll_view);
        final int[] loca=new int[2];
//        tv.post(new Runnable() {
//            @Override
//            public void run() {
//                Log.d(TAG, "onCreate: 改变前="+tv.getTop()+"///"+tv.getY());
//                tv.getLocationInWindow(loca);
//                Log.d(TAG, "onCreate: 改变前tv的位置="+loca[0]+"/////"+loca[1]);
//            }
//        });
//
//        tv.post(new Runnable() {
//            @Override
//            public void run() {
//                Log.d(TAG, "onCreate: 改变后="+tv.getTop()+"///"+tv.getTranslationX());
//
//            }
//        });


        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                containerView.scrollTo(100, 0);
//                containerView.scrollBy(-200,0);
                ObjectAnimator.ofFloat(tv,"translationX",100).setDuration(1000).start(); //属性动画

            }
        });

        //获取位置
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv.getLocationInWindow(loca);
                Log.d(TAG, "onCreate: 当前tv的位置="+loca[0]+"/////"+loca[1]);
                containerView.getLocationInWindow(loca);
                Log.d(TAG, "onCreate: 当前父容器的位置="+loca[0]+"/////"+loca[1]);

                Log.d(TAG, "onClick: 点击时tv的getTranslationX="+tv.getTranslationX()+"tv的getLeft的值="+tv.getLeft()
                        +",containerView的scrollX值="+containerView.getScrollX()
                +",当前tv的getX值="+tv.getX());
            }
        });

    }
}
