package com.androidstudydata;

import android.support.multidex.MultiDexApplication;

import java.util.HashMap;
import java.util.Map;

/**
 * 我的
 */
public class MyApp extends MultiDexApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        //startService(new Intent(this, ServiceTest.class));
        Map<String,String> map=new HashMap<String,String>();
        map.put("hello","hhh");
        map.put("how are you?","hhhhhhh");
        System.out.println(map.get("hello"));
        System.out.println(map.get("how are you?"));
    }
}
