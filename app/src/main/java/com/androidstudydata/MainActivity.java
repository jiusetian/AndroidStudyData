package com.androidstudydata;

import android.animation.FloatEvaluator;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.androidstudydata.act.TestActivity;
import com.androidstudydata.annotation.runtimeanno.AnnoUtils;
import com.androidstudydata.annotation.runtimeanno.Person;
import com.androidstudydata.commontest.ComTest;
import com.androidstudydata.datastruture.PriorityQueueExample;
import com.androidstudydata.debug.Debug;
import com.androidstudydata.eventbus.EventBusActivity;
import com.androidstudydata.eventbus.MyEvent;
import com.androidstudydata.genericity.Genericity;
import com.androidstudydata.genericity.SubGenericty;
import com.androidstudydata.genericity.TypeTest;
import com.androidstudydata.handler.HandlerDemo;
import com.androidstudydata.json.JsonUtil;
import com.androidstudydata.json.MyData;
import com.androidstudydata.kotlin.KotlinActivity;
import com.androidstudydata.matrix.MatrixTest;
import com.androidstudydata.memory.MemoryActivity;
import com.androidstudydata.okhttp.BackPressureTest;
import com.androidstudydata.propertyanima.AnimaActivity;
import com.androidstudydata.reflect.ReflectDemo;
import com.androidstudydata.rxjava.RxJavaTest;
import com.androidstudydata.service.ServiceTest;
import com.androidstudydata.thread.InterruputSleepThread;
import com.androidstudydata.thread.VolitileTest;
import com.androidstudydata.throwable.MyException;
import com.androidstudydata.view.ConstraintLayoutActivity;
import com.androidstudydata.view.CoordinatorLayoutActivity;
import com.google.gson.JsonSyntaxException;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

//    @BindView(R.id.bindView)
//    Button BindBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //android.os.Debug.startMethodTracing(this.getExternalFilesDir(Environment.MEDIA_MOUNTED).getPath());
        //LogUtils.d("路径="+this.getExternalFilesDir(Environment.MEDIA_MOUNTED).getPath());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EventBus.getDefault().register(this); //注册eventbus
        //ViewInjector.injectView(this);
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
//                if (thread[0] == null) {
//                    thread[0] = new InterruputSleepThread();
//                    thread[0].startThead();
//                } else {
//                    thread[0].interruput();
//                }
                //new TwoThreadCreater().executeOneRun();
                // DeadLock.test();
                //CycleDeadLock.test();
//                InterruputSleepThread thread1=new InterruputSleepThread();
//                thread1.startThead();
//                thread1.interruput();
                //LockTest.lockTest();
                //LockTest.tryLockTest();
                //new SumArrayFor5Threads().startSum();
                //AtomicIntegerTest.IntegerIncreaseTest.test();
                //AtomicIntegerTest.IntegerVolatileTest.test();
                //AtomicIntegerTest.test();

                VolitileTest.test();
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

        //BindBtn.setText("成功绑定了view");

        //泛型
        findViewById(R.id.genericity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Erasure<String> erasure = new Erasure<>("刘兴荣");
//                Class clazz = erasure.getErasure().getClass();
//                LogUtils.d("泛型的类型=" + clazz.getSimpleName());
                Genericity genericity = new SubGenericty<List<String>>();
                //genericity.typeTest();
                TypeTest typeTest = new TypeTest();
                typeTest.typeParameterTest3();
                // genericity.classTypeTest();
                //Genericity genericity=new Genericity();
                //genericity.shuzufanxing();
                //LogUtils.d("泛型=" + genericity.getType() + "////" + genericity.getRawType() + "???" + Utils.findNeedType(genericity.getClass()));
                // LogUtils.d("gson泛型=" + $Gson$Types.newParameterizedTypeWithOwner(null, List.class, String.class));

//                Type rawType=genericity.getRawType(); //返回List
//                Type type=genericity.getType(); //返回List<String>
//                if (String.class.isAssignableFrom(Utils.getClass(type, 0))){
//                    LogUtils.d("对对对0000");
//                }
//                if (List.class.isAssignableFrom(Utils.getClass(rawType, 0))){
//                    LogUtils.d("对对对");
//                }

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

        //handler消息机制
        findViewById(R.id.handler).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //new ThreadLocalDemo().test();
//                new HandlerDemo().sendMsg();
                //     new HandlerDemo().post();
                //new HandlerDemo().createHandler3();
                //new HandlerDemo().handler3Post();
                //startAct(HandlerActivity.class);
                new HandlerDemo().sendMsg();
                // new ThreadSwitch().switchTest();

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
                    //MyData myData = (MyData) JsonUtil.jsonStringToObject("{\"ID\":1,\"Name\":\"hpy\",\"Age\":18}", MyData.class);
//                    BaseReceiver<List<History>> receiver= (BaseReceiver<List<History>>) JsonUtil.jsonStringToObject(JsonUtil.histroy011,BaseReceiver.class);
//                    LogUtils.d("ID是" + receiver.toString());
                    List<MyData> datas = new ArrayList();
                    MyData data = new MyData();
                    data.setName("小龙人");
                    MyData data1 = new MyData();
                    data1.setName("劳心劳肺");
                    datas.add(data);
                    datas.add(data1);
                    LogUtil.INSTANCE.d("字符串=" + JsonUtil.objectToJsonString(datas));

                } catch (JsonSyntaxException e) {
                    LogUtils.d("有异常");
                    e.printStackTrace();
                }
            }
        });

        //map测试
        findViewById(R.id.map_test).setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                //new MapTest().test();
                PriorityQueueExample.test();

            }
        });

        //kotlin集合测试
        findViewById(R.id.kotlin_collection).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //CollectionOperator.INSTANCE.elementOperator();
            }
        });

        //kotlin测试
        findViewById(R.id.kotlin_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 协程
                //CoroutineTest.INSTANCE.createCoroutine();
                // CoroutineTest.INSTANCE.suspendTest();
                //CoroutineTest.INSTANCE.excuteTest();
                //CoroutineTest.INSTANCE.cancelTest();
                //CoroutineTest.INSTANCE.synTest();
                //CoroutineTest.INSTANCE.mutexTest();
               // CoroutineTest.INSTANCE.launchThreadTest();
                //CoroutineTest.INSTANCE.cancleTest2();
                //CoroutineTest.INSTANCE.asyncTest();
                //CoroutineTest.INSTANCE.hangup();
                //CoroutineTest.INSTANCE.coroutineHangup();
                startAct(KotlinActivity.class);

//                ReturnAndSkip returnAndSkip=new ReturnAndSkip();
//                returnAndSkip.label();

//                DelegateClassKt delegateClassKt=new DelegateClassKt();
//                delegateClassKt.main2();
                //DelegateClassKt.main2();
//                Lanbuda lanbuda=new Lanbuda();
////                lanbuda.mainLbd();
//                Xiaohong xiaohong=new Xiaohong(new XiaoMing());
//                xiaohong.buyCoach();
//                xiaohong.buyGucci();

                // new KtMapTest().test(33);
                //new BasicGrammar().logTest("我是","刘兴荣");
                //LogUtil.INSTANCE.d("我是是啦啦啦啦啦啦");
                // BasicGrammar.INSTANCE.mapTest();
//                LogUtils.d("返回值="+BasicGrammar.INSTANCE.returnTest02());
//                //kotlin中的函数参数，可以有如下的方式实现
//                BasicGrammar.INSTANCE.retu02(3, new Function0<Integer>() {
//                    @Override
//                    public Integer invoke() {
//                        return 5;
//                    }
//                });
                //new KtMapTest().arrayLength();
                //LogUtil.INSTANCE.d("测试=" + Lambda.INSTANCE.denghaotest(11, 33));

            }
        });

        // 服务的测试
        findViewById(R.id.service_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(new Intent(MainActivity.this, ServiceTest.class));
            }
        });

        //rxjava测试
        findViewById(R.id.rxjava_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RxJavaTest rxJavaTest = new RxJavaTest();
                //rxJavaTest.checkExceptionTest();
                //rxJavaTest.composeTest();
                //rxJavaTest.distinctUntilChangedTest();
                //rxJavaTest.contactErrorDelayTest();
                //rxJavaTest.filterTest();
                //rxJavaTest.defaultEmpty();
                //RxJavaKtTest.INSTANCE.defaultEmptyTest();
                BackPressureTest backPressureTest = new BackPressureTest();
                backPressureTest.backBuffer();
            }
        });

        //eventbus测试
        findViewById(R.id.eventbus_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, EventBusActivity.class));
            }
        });

        //异常测试
        findViewById(R.id.exception_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyException myException = new MyException();
                myException.checkExceptionTest();
                myException.runtimeExceptionTest();
            }
        });

        //普通测试
        findViewById(R.id.common_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ComTest.splitTest();
                ComTest.weiyiTest();
            }
        });

        //测试activity
        findViewById(R.id.act_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAct(TestActivity.class);
            }
        });


        //数据结构测试
        findViewById(R.id.data_strut).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //PriorityQueueExample.test();

                //LogUtil.d("-2移动后的值="+(-2>>>1));
                int i = 10;
                i -= 4;
                LogUtils.d("等于=" + i);
            }
        });

        //fragment测试
        findViewById(R.id.frg_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAct(FragmentActivity.class);
            }
        });
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

    @Subscribe
    public void onBusEvent(MyEvent myEvent) {
        LogUtils.d("接收到事件");
    }
}
