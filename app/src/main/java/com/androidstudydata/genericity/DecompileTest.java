package com.androidstudydata.genericity;

import java.util.HashMap;
import java.util.Map;

public class DecompileTest {
    public void main() {
        System.out.println("best");
        Map<String,String> map=new HashMap<>();
        map.put("testKey","testValue");
        String value=map.get("testKey");
        System.out.println(value);

        Map map2=new HashMap<String,String>();
        map2.put("dd",33);

        Map<String,Son> map1=new HashMap<>();
        Son son=new Son<String>();
        map1.put("son",son);
        Son son1=map1.get("son");

    }


    class Father<K,V>{}

    class Son<T> extends Father<String,Number>{

        T s;

        public T getS(){return s;}
    }
}
