package com.androidstudydata.rxjava;

import com.androidstudydata.LogUtils;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;

/**
 * Author：Alex
 * Date：2019/7/4
 * Note：
 */
public class RxJavaTest {

    public void exceptionTest(){
        Observable.just(2,3,4,5)
                .map(new Function<Integer, String>() {
                    @Override
                    public String apply(Integer integer) throws Exception {
                        //throw new RuntimeException("异常了"); //如果这里抛出异常了，就会阻断程序的继续执行
                        return integer+"";
                    }
                }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {
                LogUtils.d("值="+s);
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
