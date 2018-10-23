package com.androidstudydata;

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
 * getScrollX的值总是等于内容view左边缘和父view左边缘的距离,而且在父view的右边是正数，左边是负值
 *
 * 2.scrollTo是内容view移动的绝对位置，参数值是父view坐标系的坐标值，只是正数值相反
 *
 * 3.几个重要的方法解析
 *   -- getX和getLeft，getX返回的是view移动后相对于父view的位置，getLeft返回view初始位置，以后不会改变
 *   -- scrollTo和对view进行属性动画translation后，对getX的不同影响，发现scrollTo以后对getX的值没有影响，但是
 *   translation以后会有影响
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
        tv.post(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "onCreate: 改变前="+tv.getTop()+"///"+tv.getY());
            }
        });

        //containerView.scrollTo(100, 0);
        Log.d(TAG, "onCreate: tv的scroll值="+tv.getScrollX());
        containerView.scrollBy(-200,0);
        //ObjectAnimator.ofFloat(tv,"translationX",100).setDuration(1000).start(); //属性动画
        tv.post(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "onCreate: 改变后="+tv.getTop()+"///"+tv.getTranslationX());
            }
        });
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: 点击时="+tv.getTranslationX()+"getLeft的值="+tv.getLeft()
                        +",containerView的scrollX值="+containerView.getScrollX());
            }
        });

        Log.d(TAG, "onCreate: containerView的scrollX值=" + containerView.getScrollX());

    }
}
