package com.androidstudydata.rxjava;

import android.annotation.SuppressLint;

import com.androidstudydata.LogUtil;
import com.androidstudydata.LogUtils;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import okio.ByteString;

import static io.reactivex.Observable.just;

/**
 * Author：Alex
 * Date：2019/7/4
 * Note：
 */
public class RxJavaTest {


    @SuppressLint("CheckResult")
    public void defaultEmpty(){

        just(3, 4, 5, 6)
                .filter(new Predicate<Integer>() {
                    @Override
                    public boolean test(Integer integer) throws Exception {
                        return true;
                    }
                })
                .map(new Function<Integer, Integer>() {
                    @Override
                    public Integer apply(Integer integer) throws Exception {

                        return integer;
                    }
                })
                .defaultIfEmpty(88)
                .flatMap(new Function<Integer, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(Integer integer) throws Exception {
                        LogUtil.INSTANCE.d("执行了flatmap="+integer);
                        return just(53,4,646,4);
                    }
                })
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        LogUtil.INSTANCE.d("执行");
                    }
                });

    }


    /**
     * 当filter的条件都返回false的时候，后面是不会接收到前面发送的数据的,所以说filter返回false的时候有拦截执行的作用，就是后的相关就不会再执行了
     */
    @SuppressLint("CheckResult")
    public void filterTest() {
        just(3, 4, 5, 6)
                .filter(new Predicate<Integer>() {
                    @Override
                    public boolean test(Integer integer) throws Exception {
                        return false;
                    }
                })
                .flatMap(new Function<Integer, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(Integer integer) throws Exception {
                        LogUtil.INSTANCE.d("执行了flatmap");
                        return Observable.just(53,4,646,4);
                    }
                })
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        LogUtil.INSTANCE.d("执行");
                    }
                });
//                .map(new Function<Integer, String>() {
//                    @Override
//                    public String apply(Integer integer) throws Exception {
//                        LogUtil.INSTANCE.d("执行了map方法");
//                        return integer + "";
//                    }
//                })

    }

    public void contactErrorDelayTest() {
        Observable.concatArrayDelayError(
                Observable.create(new ObservableOnSubscribe<Integer>() {
                    @Override
                    public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {

                        emitter.onNext(1);
                        emitter.onNext(2);
                        emitter.onNext(3);
                        // 发送Error事件，因为使用了concatDelayError，所以第2个Observable将会发送事件，等发送完毕后，再发送错误事件
                        //emitter.onError(new NullPointerException());
                        emitter.onNext(03);
                        emitter.onComplete();
                    }
                }),
                just(4, 5, 6))
                .take(3)
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(Integer value) {
                        LogUtils.d("接收到了事件" + value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.d("对Error事件作出响应");
                    }

                    @Override
                    public void onComplete() {
                        LogUtils.d("对Complete事件作出响应");
                    }
                });
    }

    @SuppressLint("CheckResult")
    public void distinctUntilChangedTest() {
        just("lxr1", "lxr2", "lxr2")
                .distinctUntilChanged(new Function<String, String>() {
                    @Override
                    public String apply(String s) throws Exception {
                        return ByteString.of(s.getBytes()).md5().hex();
                    }
                })
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        LogUtils.d("接收的数据=" + s);
                    }
                });
    }

    /**
     * compose操作符测试
     * 在执行源observable发射之前会执行compose中的apply方法
     */
    @SuppressLint("CheckResult")
    public void composeTest() {
        //Observable<T>是一个泛型，代表他的实例对象是带有泛型参数的，泛型参数的类型就是create方法返回observable的发射的数据的类型
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                LogUtils.d("执行了发射");
                emitter.onNext(4);
            }
        }).compose(new ObservableTransformer<Integer, String>() {
            @Override
            public ObservableSource<String> apply(Observable<Integer> upstream) {
                LogUtils.d("执行了compose的apply方法");
                return upstream.map(new Function<Integer, String>() {
                    @Override
                    public String apply(Integer integer) throws Exception {
                        return integer + "字符串";
                    }
                });
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                LogUtils.d("接收到=" + s);
            }
        });
    }

    /**
     * 异常测试
     */
    public void exceptionTest() {
        just(2, 3, 4, 5)
                .map(new Function<Integer, String>() {
                    @Override
                    public String apply(Integer integer) throws Exception {
                        //throw new RuntimeException("异常了"); //如果这里抛出异常了，就会阻断程序的继续执行
                        return integer + "";
                    }
                }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {
                LogUtils.d("值=" + s);
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onComplete() {

            }
        });
    }
}
