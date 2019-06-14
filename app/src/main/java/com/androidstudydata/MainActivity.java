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
import com.androidstudydata.debug.Debug;
import com.androidstudydata.genericity.Erasure;
import com.androidstudydata.handler.HandlerDemo;
import com.androidstudydata.json.JsonUtil;
import com.androidstudydata.json.MyData;
import com.androidstudydata.kotlin.Lanbuda;
import com.androidstudydata.map.MapTest;
import com.androidstudydata.matrix.MatrixTest;
import com.androidstudydata.memory.MemoryActivity;
import com.androidstudydata.propertyanima.AnimaActivity;
import com.androidstudydata.reflect.ReflectDemo;
import com.androidstudydata.thread.InterruputSleepThread;
import com.androidstudydata.view.ConstraintLayoutActivity;
import com.androidstudydata.view.CoordinatorLayoutActivity;
import com.google.gson.JsonSyntaxException;
import com.lib_java.compileAnnotation.BindView;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.bindView)
    Button BindBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //android.os.Debug.startMethodTracing(this.getExternalFilesDir(Environment.MEDIA_MOUNTED).getPath());
        //LogUtils.d("路径="+this.getExternalFilesDir(Environment.MEDIA_MOUNTED).getPath());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewInjector.injectView(this);
        //ViewServer.get(this).addWindow(this);
//        Fragment blankFragment=new BlankFragment();
//        //获取FragmentManager
//        FragmentManager fm = getFragmentManager();
//        //获取FragmentTransaction
//        FragmentTransaction ft = fm.beginTransaction();
//        //进行添加操作
//        ft.add(R.id.fragment_container,blankFragment);
//        //提交
//        ft.commit();

        //view时间分发
        findViewById(R.id.dispatch_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAct(EventDispatchActivity.class);
            }
        });

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


        final InterruputSleepThread[] thread = {null};
        //线程操作
        findViewById(R.id.thead).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //CaculateSync.main();
                //new CaculateSync().test();
                if (thread[0] == null) {
                    thread[0] = new InterruputSleepThread();
                    thread[0].startThead();
                } else {
                    thread[0].interruput();
                }
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
                LogUtils.d("泛型的类型=" + clazz.getSimpleName());
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

        //消息机制
        findViewById(R.id.handler).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //new ThreadLocalDemo().test();
//                new HandlerDemo().sendMsg();
                //     new HandlerDemo().post();
//                new HandlerDemo().createHandler3();
                new HandlerDemo().handler3Post();
            }
        });

        //测试view的位置
        findViewById(R.id.view_loca).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAct(ViewLocaActivity.class);
            }
        });

        //debug调试
        findViewById(R.id.debug).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Debug().startDebug();
            }
        });

        //View测试
        findViewById(R.id.view_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAct(ViewActivity.class);
            }
        });

        //matrix测试
        findViewById(R.id.matrix_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MatrixTest matrixTest = new MatrixTest();
                //matrixTest.getValues();
                //matrixTest.setContact();
                // matrixTest.mapPoints3();
                //matrixTest.mapRadius();
                matrixTest.mapRect();
            }
        });

        //内存分析测试
        findViewById(R.id.memory_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAct(MemoryActivity.class);
            }
        });

        //json测试
        findViewById(R.id.json_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    MyData myData = (MyData) JsonUtil.jsonStringToObject("{\"ID\":1,\"Name\":\"hpy\",\"Age\":18}", MyData.class);
                    LogUtils.d("ID是" + myData.getID());
                } catch (JsonSyntaxException e) {
                    LogUtils.d("有异常");
                    e.printStackTrace();
                }
            }
        });

        //map测试
        findViewById(R.id.map_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MapTest().test();
            }
        });

        //kotlin测试
        findViewById(R.id.kotlin_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ReturnAndSkip returnAndSkip=new ReturnAndSkip();
//                returnAndSkip.label();

//                DelegateClassKt delegateClassKt=new DelegateClassKt();
//                delegateClassKt.main2();
                //DelegateClassKt.main2();
                Lanbuda lanbuda=new Lanbuda();
                lanbuda.mainLbd();
            }
        });

        //android.os.Debug.stopMethodTracing();
    }

    private void startAct(Class startClass) {

        startActivity(new Intent(MainActivity.this, startClass));
    }

    @Override
    protected void onResume() {
        super.onResume();
        // ViewServer.get(this).setFocusedWindow(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // ViewServer.get(this).removeWindow(this);
    }
}
