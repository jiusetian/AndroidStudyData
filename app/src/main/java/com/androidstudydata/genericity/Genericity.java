package com.androidstudydata.genericity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by XR_liu on 2018/11/25.
 * 泛型相关测试
 */
public class Genericity {

    /**
     * 上限通配符
     */
    private void shagnxian() {
        //上限通配符，就是说list里面的元素应该是Number的子类，这只是个范围，所以我们无法得知集合里面的元素到底是哪种类型，并且也不知道它的下限是什么，
        //所以就无法添加元素了，因为添加什么都有可能是不安全的，这个跟下限通配符不一样，下限我们知道元素类型的下限是什么，所以我们只要添加它下限类型的子类，
        //就是安全的
        List<? extends Number> list = new ArrayList<>();

        /*如果类型是Object类型，那么所有它的子类都是安全的，因为只要继承了Object类的子类就是安全的，
        向上转型安全，因为只要你继承了我，你就拥有了我的名分*/
        List<Object> list1=new ArrayList<>();
        Double dd=333d;
        Number n=33;
        list1.add(n);
        list1.add(dd);


        Number first = list.get(0); // OK

        Double aDouble = 333d;
        //list.add(aDouble);

        list.add(null); // OK
        Number number = 1;
        //list.add(number); // 错误: 不兼容的类型: Number无法转换为CAP#1
        list.clear(); // OK
        list.remove(0); // OK
    }

    /**
     * 下限通配符
     */
    private void xiaxian(){
        //下限通配符，代表集合里面只能保存Number类的父类包括Number，但是我们还是无法确定集合里面保存的到底是哪个类型
        //所以get出来一个元素以后， 根据向上转型安全，向下转型不安全的原则，所以我们无法将取出来的元素向下转型
        //但我们添加元素的时候，可以使用Number以下的类型，因为是向上转型
        List<? super Number> list = new ArrayList<>();

        //下面是正确的，因为类型作了下限规定，只要是Number上限就可以匹配
        list=new ArrayList<Object>();
        //下面是不正确的，因为Double是Number的下面的类，超过了Number为下限的规定
        //list=new ArrayList<Double>();

        Object o=list.get(2); //ok的
        //Double添加进去是向上转型
        Double d=444d;
        list.add(d);

        //取出来的元素在我们无法预知类型的情况下，我们只知道是Number的父类，所以是数据不安全的
        //Number first = list.get(0); // 错误: 不兼容的类型: CAP#1无法转换为Number
        list.add(null); // OK
        Number number = 1;
        list.add(number); // OK
        list.clear(); // OK
        list.remove(0); // OK
    }

}
