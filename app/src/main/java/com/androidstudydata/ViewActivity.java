package com.androidstudydata;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.androidstudydata.view.TrackView;
import com.androidstudydata.view.WaveView;

public class ViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        findViewById(R.id.rest).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((TrackView) findViewById(R.id.track_view)).reset();
            }
        });

        ((WaveView) findViewById(R.id.wave_view)).startAnim();

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtils.d("btn被点击了");
            }
        });

        // ViewGroup和子View中，虽然事件先到了ViewGroup，但是默认情况下，还是子View有事件的优先处理
        // 权，比如在ViewGroup和子View都设置了点击事件，点击事件最终还是子View优先处理了
        findViewById(R.id.fl).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtils.d("fl被点击了");
            }
        });

    }
}
