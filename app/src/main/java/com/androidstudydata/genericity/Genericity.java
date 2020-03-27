package com.androidstudydata.genericity;

import com.androidstudydata.LogUtils;
import com.androidstudydata.Utils;
import com.easysocket.utils.LogUtil;

import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by XR_liu on 2018/11/25.
 * 泛型相关测试
 */
public class Genericity<T> extends IType<Erasure>{

    private T t; //泛型


    public Type getType() { //获取需要解析的泛型T类型
        LogUtils.d("类型="+getClass().getSimpleName());
        return Utils.findNeedClass(getClass());
    }

    public Type getRawType() { //获取需要解析的泛型T raw类型
        return Utils.findRawType(getClass());
    }

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
        //只要添加Number的子类类型即可
        list.add(3);

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

    private T[] mt;
    private List<String>[] listArray;
    private Map<String,Integer> map2;
    private List<Map<String,Integer>> list2;
    /**
     * 泛型数组
     */
    public void shuzufanxing(){
        try {
            Field listField=Genericity.class.getDeclaredField("listArray");
            Field map2Field=Genericity.class.getDeclaredField("map2");
            Field list2Field=Genericity.class.getDeclaredField("list2");

            ParameterizedType typeList2= (ParameterizedType) list2Field.getGenericType();
            Type[] list2Field2=typeList2.getActualTypeArguments();
            LogUtils.d("方法000="+list2Field2[0]);

            ParameterizedType typeMap2= (ParameterizedType) map2Field.getGenericType();
            Type[] typeMap2s=typeMap2.getActualTypeArguments();
            LogUtils.d("方法="+typeMap2s[0]+"???"+typeMap2s[1]);


            Type listType=listField.getGenericType();
            GenericArrayType genericArrayType= (GenericArrayType) listType;
            LogUtils.d("泛型="+genericArrayType.getGenericComponentType());
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    /**
     * getClass获取的是运行时类的class对象
     */
    public void classTypeTest(){
        List<Father> list=new ArrayList<>();
        Son son1=new Son();
        Son son2=new Son();
        list.add(son1);
        list.add(son2);
        for (Father father:list){
            LogUtil.d("类型00"+father.getClass().getName());
        }
        LogUtil.d("类型="+list.get(0).getClass().getName());
        LogUtil.d("类型2"+Son.class.getName());
    }

    /**
     * 1.getGenericSuperclass()获取直接父类的Type，如果有泛型则包含泛型
     */
    public void typeTest(){
        Sub sub=new Sub();
        LogUtil.d("类型="+sub.getClass().getGenericSuperclass());
    }

    class Father<T>{
    }

    class Son<T> extends Father<T>{
    }

    class Sub extends Son<String>{

    }

}
