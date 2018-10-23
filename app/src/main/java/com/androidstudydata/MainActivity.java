package com.androidstudydata;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.contraitlayout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startAct(ConstraintLayoutActivity.class);
            }
        });

        findViewById(R.id.coordinatorlayout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startAct(CoordinatorLayoutActivity.class);
            }
        });

        findViewById(R.id.scroll_layout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAct(ScrollViewActivity.class);
            }
        });


    }

    private void startAct(Class startClass){
        startActivity(new Intent(MainActivity.this,startClass));
    }
}
