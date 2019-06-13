package com.androidstudydata.genericity;

/**
 * Created by XR_liu on 2018/11/25.
 */
public class Erasure<T> {

    T t;

    public Erasure(T t) {
        this.t = t;
    }

    public T getErasure(){
        return t;
    }


}
