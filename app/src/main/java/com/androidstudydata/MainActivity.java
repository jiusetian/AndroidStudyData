package com.androidstudydata;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.androidstudydata.view.ConstraintLayoutActivity;
import com.androidstudydata.view.CoordinatorLayoutActivity;
import com.lib_java.dynamicProxy.DynamicProxyTest;

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

        //动态代理
        findViewById(R.id.dynamicLayout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //测试
                DynamicProxyTest test=new DynamicProxyTest();
                test.test();
            }
        });

    }

    private void startAct(Class startClass){
        startActivity(new Intent(MainActivity.this,startClass));
    }
}
