package com.androidstudydata.javamap;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.LruCache;

import com.easysocket.utils.LogUtil;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Author：Alex
 * Date：2019/6/11
 * Note：
 */
public class MapTest {

    Map<Info, String> map = new HashMap<>();
    Map<String,String> map2=new HashMap<>();

    LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>(0,0.75f,true);

    LruCache<String,String> lruCache=new LruCache<>(5);

    /**
     * hashmap的哈希值计算方法
     * @param hashcode
     * @return
     */
    static  int hash(int  hashcode) {
        return  hashcode ^ (hashcode >>> 16);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void test() {

//
        Info info=new Info("lxr",80);

        map.put(info,"lxrong");
//        Info info1=new Info("lxr",80);
//
//        LogUtils.d("测试="+map.get(info1));
//        LogUtils.d("测试2="+map.get(info));
//
//        map2.put("lxr","测试");
//        map2.put(new String("lxr"),"刘兴荣");
//        LogUtil.d("lxr测试=="+map2.get("lxr"));
//        LogUtil.d("刘兴荣测试="+map2.get(new String("lxr")));

        String str="lxr";
        LogUtil.d("散列值1="+str.hashCode());
        //str.replace("l","刘");
        info.port=90;
        str=str+"1";
        //LogUtil.d("改变后="+str);
        LogUtil.d("散列值2="+str.hashCode());
//
//        map.forEach(new BiConsumer<Info, String>() {
//            @Override
//            public void accept(Info info, String s) {
//
//            }
//        });

//        for (int i = 0; i < 10; i++) {
////            try {
////                Thread.sleep(10);
////            } catch (InterruptedException e) {
////                e.printStackTrace();
////            }
//            String key= String.valueOf(System.currentTimeMillis());
//            //linkedHashMap.put(key,i+"");
//            //LogUtil.d("key为="+key);
//            lruCache.put(i+"",i+"");
//
//        }
//        LogUtil.d("大小="+lruCache.size());
    }


    public static class Info {
        private String ip;
        private int port;

        public Info(String ip, int port) {
            this.ip = ip;
            this.port = port;
        }
    }
}
