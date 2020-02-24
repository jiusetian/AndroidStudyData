package com.lib_java.dynamicProxy;


/**
 * Created by XR_liu on 2018/11/12.
 */
public class Buyer implements Buy {
    String TAG="tag";
    @Override
    public void buyHouse() {
        System.out.print("buyHouse: 买了一百套房子");
    }
}
