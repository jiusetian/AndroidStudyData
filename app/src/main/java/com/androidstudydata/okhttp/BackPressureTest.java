package com.androidstudydata.okhttp;

import android.util.Log;

import com.androidstudydata.LogUtils;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Author：Alex
 * Date：2020/1/8
 * Note：背压相关测试
 */
public class BackPressureTest {

    public static final String TAG = "tag";

    /**
     * 当发射超过128时并且发射完以后会提示缓存池已经满了：MissingBackpressureException: Queue is full?!
     */
    public void backMissing() {
        // 创建被观察者Flowable
        Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(FlowableEmitter<Integer> emitter) throws Exception {
                // 发送 129个事件
                for (int i = 0; i < 211; i++) {
                    Log.d(TAG, "发送了事件" + i);
                    emitter.onNext(i);
                }
                emitter.onComplete();
            }
        }, BackpressureStrategy.MISSING) // 设置背压模式 = BackpressureStrategy.MISSING
                .subscribeOn(Schedulers.io()) // 设置被观察者在io线程中进行
                .observeOn(AndroidSchedulers.mainThread()) // 设置观察者在主线程中进行
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        Log.d(TAG, "onSubscribe");
                        s.request(10);
                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.d(TAG, "接收到了事件" + integer);
                    }

                    @Override
                    public void onError(Throwable t) {
                        Log.w(TAG, "onError: ", t);
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete");
                    }
                });

    }

    /**
     * 利用背压策略missing解决问题
     * 如果发射超过了128，但下游还没开始接收，则上游会阻塞不发射数据，直到下游开始接收数据并受到96的时候，上游才清理掉96个数据并且又开始发射数据，但当缓存池满了以后，
     * 上游又会阻塞等待，等到下游接收数据并且缓存池只剩下32个数据未消费的时候才开始清理缓存池
     */
    public void backMissingUse() {
        Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(FlowableEmitter<Integer> e) throws Exception {
                int i = 0;
                while (true) {
                    if (e.requested() == 0) continue;//此处添加代码，让flowable按需发送数据
                    System.out.println("发射---->" + i);
                    i++;
                    e.onNext(i);
                }
            }
        }, BackpressureStrategy.MISSING)
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.newThread())
                .subscribe(new Subscriber<Integer>() {
                    private Subscription mSubscription;

                    @Override
                    public void onSubscribe(Subscription s) {
                        s.request(1);            //设置初始请求数据量为1
                        mSubscription = s;
                    }

                    @Override
                    public void onNext(Integer integer) {
                        try {
                            Thread.sleep(100);
                            System.out.println("接收------>" + integer);
                            mSubscription.request(1);//每接收到一条数据增加一条请求量
                        } catch (InterruptedException ignore) {
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    /**
     * 背压策略Drop
     * 在Drop的策略下，当上游发射数据的速度是下游接收数据的三倍，上游发射到128的时候，缓存池已经满了， 此时下游才接收到43，但是此时并不马上清理缓存池，而上游继续
     * 发射数据，但是此时发射的数据是被抛弃的， 下游继续接收数据，当接收到96的时候，开始清理缓存池，此时上游数据已经发射到284了，此时将清理掉前面96个数据，284以后
     * 的数据继续保存到缓存池，当下游接收到128的时候，上游已经发射到378了，但是下游已经接收不到129这个数据了，而是接收到284这个数据，因为从129到283这个阶段的数据
     * 都被放弃了，因为这个阶段缓存池虽然满了，但不是立即清理，而是等到下游接收到96的时候，才清理缓存池
     */
    public void backDrop() {

        Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(FlowableEmitter<Integer> e) throws Exception {
                String threadName = Thread.currentThread().getName();
                System.out.println(threadName + "开始发射数据" + System.currentTimeMillis());
                for (int i = 1; i <= 500; i++) {
                    System.out.println(threadName + "发射---->" + i);
                    e.onNext(i);
                    LogUtils.d("当前可请求值=" + e.requested());
                    try {
                        Thread.sleep(100);//每隔100毫秒发射一次数据
                    } catch (Exception ex) {
                        e.onError(ex);
                    }
                }
                System.out.println(threadName + "发射数据结束" + System.currentTimeMillis());
                e.onComplete();
            }
        }, BackpressureStrategy.DROP)
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.newThread())
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        s.request(Long.MAX_VALUE);            //注意此处，暂时先这么设置
                    }

                    @Override
                    public void onNext(Integer integer) {
                        try {
                            Thread.sleep(300);//每隔300毫秒接收一次数据
                        } catch (InterruptedException ignore) {
                        }
                        System.out.println(Thread.currentThread().getName() + "接收---------->" + integer);
                    }

                    @Override
                    public void onError(Throwable t) {
                        t.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                        System.out.println(Thread.currentThread().getName() + "接收----> 完成");
                    }
                });
    }

    /**
     * 背压策略LATEST测试
     * 出现当缓存区大小存满（默认缓存区大小 = 128）、被观察者仍然继续发送下1个事件时，只保存最后的事件，超过缓存区大小（128）的事件丢弃
     * 比如500个数据，缓存里会保存129个事件，保存1-128和500
     */
    public void backLatest() {

        Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(FlowableEmitter<Integer> e) throws Exception {
                String threadName = Thread.currentThread().getName();
                System.out.println(threadName + "开始发射数据" + System.currentTimeMillis());
                for (int i = 1; i <= 500; i++) {
                    System.out.println(threadName + "发射---->" + i);
                    e.onNext(i);
                    try {
                        Thread.sleep(10);
                    } catch (Exception ignore) {
                    }
                }
                System.out.println(threadName + "发射数据结束" + System.currentTimeMillis());
                e.onComplete();

            }
        }, BackpressureStrategy.LATEST)
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.newThread())
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        s.request(Long.MAX_VALUE);            //注意此处，暂时先这么设置
                    }

                    @Override
                    public void onNext(Integer integer) {
                        try {
                            Thread.sleep(30);
                        } catch (InterruptedException ignore) {
                        }
                        System.out.println(Thread.currentThread().getName() + "接收---------->" + integer);
                    }

                    @Override
                    public void onError(Throwable t) {
                        t.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                        System.out.println(Thread.currentThread().getName() + "接收----> 完成");
                    }
                });
    }

    /**
     * 背压策略buffer的测试
     * <p>
     * BufferAsyncEmitter内部维护了一个缓存池SpscLinkedArrayQueue，其大小不限，此策略下，如果Flowable默认的异步缓存池满了，会通过此缓存池暂存数据
     * 它与Observable的异步缓存池一样，可以无限制向里添加数据，不会抛出MissingBackpressureException异常，但会导致OOM
     */
    public void backBuffer() {

        // 创建被观察者Flowable
        Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(FlowableEmitter<Integer> emitter) throws Exception {

                // 发送 129个事件
                for (int i = 1; i < 130; i++) {
                    Log.d(TAG, "发送了事件" + i);
                    emitter.onNext(i);
                }
                emitter.onComplete();
            }
        }, BackpressureStrategy.BUFFER) // 设置背压模式 = BackpressureStrategy.BUFFER
                .subscribeOn(Schedulers.io()) // 设置被观察者在io线程中进行
                .observeOn(AndroidSchedulers.mainThread()) // 设置观察者在主线程中进行
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        Log.d(TAG, "onSubscribe");
                        s.request(10);
                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.d(TAG, "接收到了事件" + integer);
                    }

                    @Override
                    public void onError(Throwable t) {
                        Log.w(TAG, "onError: ", t);
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete");
                    }
                });

    }


    /**
     * 背压策略ERROR测试
     * 如果放入Flowable的异步缓存池中的数据超限了，则会抛出MissingBackpressureException异常
     */
    public void backError() {

        Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(FlowableEmitter<Integer> e) throws Exception {
                for (int i = 1; i <= 129; i++) {
                    e.onNext(i);
                    Log.d("tag", "subscribe: 执行发射");
                }
                e.onComplete();
            }
        }, BackpressureStrategy.ERROR)
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.newThread())
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        s.request(Long.MAX_VALUE);            //注意此处，暂时先这么设置
                    }

                    @Override
                    public void onNext(Integer integer) {
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException ignore) {
                        }
                        System.out.println(integer);
                    }

                    @Override
                    public void onError(Throwable t) {
                        t.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("接收----> 完成");
                    }
                });
    }

    /**
     * 背压测试
     */
    public void backPressure() {

        Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(FlowableEmitter<Integer> e) throws Exception {
                System.out.println("发射----> 1");
                e.onNext(1);
                System.out.println("发射----> 2");
                e.onNext(2);
                System.out.println("发射----> 3");
                e.onNext(3);
                System.out.println("发射----> 完成");
                e.onComplete();
            }
        }, BackpressureStrategy.BUFFER) //create方法中多了一个BackpressureStrategy类型的参数
                .subscribeOn(Schedulers.newThread())//为上下游分别指定各自的线程
                .observeOn(Schedulers.newThread())
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onSubscribe(Subscription s) {   //onSubscribe回调的参数不是Disposable而是Subscription
                        s.request(Long.MAX_VALUE);            //注意此处，暂时先这么设置
                    }

                    @Override
                    public void onNext(Integer integer) {
                        System.out.println("接收----> " + integer);
                    }

                    @Override
                    public void onError(Throwable t) {
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("接收----> 完成");
                    }
                });
    }

}
