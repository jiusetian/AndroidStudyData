package com.androidstudydata;

import android.animation.FloatEvaluator;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;

import com.androidstudydata.annotation.ViewInjector;
import com.androidstudydata.annotation.runtimeanno.AnnoUtils;
import com.androidstudydata.annotation.runtimeanno.Person;
import com.androidstudydata.propertyanima.AnimaActivity;
import com.androidstudydata.reflect.ReflectDemo;
import com.androidstudydata.thread.CaculateSync;
import com.androidstudydata.view.ConstraintLayoutActivity;
import com.androidstudydata.view.CoordinatorLayoutActivity;
import com.genericity.Erasure;
import com.lib_java.compileAnnotation.BindView;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.bindView)
    Button BindBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewInjector.injectView(this);
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

        //线程操作
        findViewById(R.id.thead).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //CaculateSync.main();
                new CaculateSync().test();
            }
        });

        //注解测试
        findViewById(R.id.annotation).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                InheritedTest inheritedTest=new InheritedTest();
//                inheritedTest.test();

                //AnnoElementTest.test();
                LogUtils.d("创建数据的SQL语句=" + AnnoUtils.createTableSql(Person.class));

            }
        });

        BindBtn.setText("成功绑定了view");

        //泛型
        findViewById(R.id.genericity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Erasure<String> erasure = new Erasure<>("刘兴荣");
                Class clazz = erasure.getErasure().getClass();
                LogUtils.d("泛型的类型="+clazz.getSimpleName());
            }
        });

        //反射
        findViewById(R.id.reflect).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    ReflectDemo.test();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


    }

    private void startAct(Class startClass) {
        startActivity(new Intent(MainActivity.this, startClass));


    }
}
