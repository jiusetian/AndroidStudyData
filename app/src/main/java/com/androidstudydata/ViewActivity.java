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
                ((TrackView)findViewById(R.id.track_view)).reset();
            }
        });

        ((WaveView)findViewById(R.id.wave_view)).startAnim();
    }
}
