package com.androidstudydata;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * view的scroll相关测试，得到的结论
 * 1.scrollTo（x,y）参数x大于0时，内容view从右向左移动x距离，此时父view的getScrollX的值也是大于0
 * getScrollX的值总是等于内容view左边缘和父view左边缘的距离
 *
 * 2.scrollTo是内容view移动的绝对位置，参数值是父view坐标系的坐标值，只是正数值相反
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
        containerView.scrollTo(100, 0);
        containerView.scrollBy(-200,0);
        Log.d(TAG, "onCreate: scrollX的值=" + containerView.getScrollX());

    }
}
