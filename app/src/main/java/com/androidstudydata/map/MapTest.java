package com.androidstudydata.map;

import com.androidstudydata.LogUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Author：Alex
 * Date：2019/6/11
 * Note：
 */
public class MapTest {

    Map<Info,String> map=new HashMap<>();

    public void test(){
        Info info=new Info("lxr",80);
        map.put(info,"lxrong");
        Info info1=new Info("lxr",80);

        LogUtils.d("测试="+map.get(info1));
        LogUtils.d("测试2="+map.get(info));
    }



    public static class Info{
         private String ip;
         private int port;

         public Info(String ip,int port){
             this.ip=ip;
             this.port=port;
         }
    }
}
