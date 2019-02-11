package com.androidstudydata;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewTreeObserver;
import android.widget.Button;

/**
 * 1.getLocationOnScreen:高度包括了状态栏
 */
public class ViewLocaActivity extends AppCompatActivity {

    Button button;
    Button button02;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_loca);

        button=findViewById(R.id.btn);
        button02=findViewById(R.id.btn02);
        //可以监听view的位置
        button.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int[] loca=new int[2];
                button.getLocationOnScreen(loca);
                LogUtils.d("按钮的位置="+loca[0]+"////"+loca[1]);
            }
        });

        button02.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int[] loca=new int[2];
                button02.getLocationInWindow(loca);
                LogUtils.d("按钮02的位置="+loca[0]+"////"+loca[1]);
            }
        });
    }
}
