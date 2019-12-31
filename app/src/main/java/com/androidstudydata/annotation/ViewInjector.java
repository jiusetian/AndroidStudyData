package com.androidstudydata.annotation;


import android.app.Activity;
import android.view.View;

import com.lib_java.compileAnnotation.ViewInject;

public class ViewInjector {
    private static final String SUFFIX = "$$ViewInject";

    public static void injectView(Activity activity) {
        //找到自动生成的代理类
        ViewInject proxyActivity = findProxyActivity(activity);
        //
        proxyActivity.inject(activity, activity); //执行注入方法
    }

    //object是持有被注解字段的对象，view是指我们要findViewById那个view对象，也可以是activity对象
    public static void injectView(Object object, View view) {
        ViewInject proxyActivity = findProxyActivity(object);
        proxyActivity.inject(object, view);
    }

    private static ViewInject findProxyActivity(Object activity) {
        try {
            Class clazz = activity.getClass();
            Class injectorClazz = Class.forName(clazz.getName() + SUFFIX);
            return (ViewInject) injectorClazz.newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        throw new RuntimeException(String.format("can not find %s , something when compiler.", activity.getClass().getSimpleName() + SUFFIX));
    }
}
