package com.androidstudydata;

import android.animation.FloatEvaluator;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.androidstudydata.propertyanima.AnimaActivity;
import com.androidstudydata.view.ConstraintLayoutActivity;
import com.androidstudydata.view.CoordinatorLayoutActivity;

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

                ObjectAnimator animator = ObjectAnimator.ofFloat(view, "translationX", 100);
                animator.setInterpolator(new LinearInterpolator());
                animator.setEvaluator(new FloatEvaluator());
                animator.setDuration(100);
                animator.start();

            }
        });

        //动画
        findViewById(R.id.anima).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAct(AnimaActivity.class);
            }
        });


    }

    private void startAct(Class startClass) {
        startActivity(new Intent(MainActivity.this, startClass));


    }
}
