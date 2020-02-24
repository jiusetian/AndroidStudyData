package com.lib_java.dynamicProxy;

import com.lib_java.JavaUtil;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * Created by XR_liu on 2018/11/12.
 */
public class DynamicProxyTest {

    public void test() {

        Buyer buyer = new Buyer();
        InvocationHandler invocationHandler = new BuyerInvocationHandler<Buy>(buyer);
        JavaUtil.generateClassFile(Buy.class,"$Proxy0");
        //获取动态代理对象
        Buy buyerProxy = (Buy) Proxy.newProxyInstance(Buy.class.getClassLoader(), new Class<?>[]{Buy.class}, invocationHandler);
        buyerProxy.buyHouse(); //调用方法

    }

}
