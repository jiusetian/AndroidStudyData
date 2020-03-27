package com.androidstudydata.genericity;

import com.easysocket.utils.LogUtil;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author：枪花
 * Date：2020/3/18
 * Note：Type相关测试
 */
public class TypeTest {


    /**
     * TypeVariable相关测试
     */
    public void typeVariableTest() {
        Type[] types = VariableTest.class.getTypeParameters();
        LogUtil.d("第一个泛型上边界=" + ((TypeVariable) types[0]).getBounds()[0] + "----" + ((TypeVariable) types[0]).getBounds()[1]);
        for (Type type : types) {
            TypeVariable t = (TypeVariable) type;
            System.out.println(t.getGenericDeclaration());
            int size = t.getBounds().length;
            System.out.println("大小" + size + "====" + t.getBounds()[--size]);
            System.out.println(t.getName() + "\n-------------分割线-------------");
        }
    }

    /**
     *
     */
    public void typeParameterTest() {
        Type type = ParameterTest.class.getGenericSuperclass();
        Type[] params = ((ParameterizedType) type).getActualTypeArguments();
        LogUtil.d("类型为:" + params[0] + "----" + params[1]);
    }

    /**
     *
     */
    public void typeParameterTest1() {
        //一定要加后面的{}，这样test1是继承于ParameterTest1的匿名内部类的实例，下面才能通过getGenericSuperclass获取到泛型类型
        //ParameterTest test=new ParameterTest<Double,Float>(){};
        ParameterTest test=new ParameterTest();
        LogUtil.d("t的类型="+test.getClass());
        Type parameterizedType = test.getClass().getGenericSuperclass();
        //Type type = test1.getClass().getGenericSuperclass();
        Type[] params = ((ParameterizedType) parameterizedType).getActualTypeArguments();
        LogUtil.d("类型为:" + params[0] + "----" + params[1]);
        //为什么没有泛型的类型检查呢？因为new ArrayList<String>()只是开辟了一个内存空间，而list2的引用是ArrayList，编译器的泛型类型检查是对
        //类型引用的检查，所以不会对list2引用限定添加String的检查
//        ArrayList<String> list1 = new ArrayList();
//        ArrayList list2 = new ArrayList<String>(); //第二种 情况
//        list2.add(333);
    }

    /**
     * 测试多级泛型，比如A<B<T>>
     */
    public void typeParameterTest3() {
        Map<String,List<String>> map=new HashMap<String,List<String>>(){};
        Type type=map.getClass().getGenericSuperclass();
        Type[] params=ParameterizedType.class.cast(type).getActualTypeArguments();
        LogUtil.d("类型="+params[0]+"----"+params[1]);
        LogUtil.d("是否为泛型类型="+(params[0] instanceof TypeVariable)+"-----"+(params[1] instanceof TypeVariable));
        ParameterizedType type1= (ParameterizedType) params[1];
        Type[] params2=ParameterizedType.class.cast(type1).getActualTypeArguments();
        LogUtil.d("二级泛型="+params2[0]);
    }


    public void typeParameterTest4(){
        ParameterizedType type= (ParameterizedType) ParameterTest1.class.getGenericSuperclass();
        Type[] params=type.getActualTypeArguments();
        LogUtil.d("类型="+params[0]+"----"+params[1]);
        LogUtil.d("什么类型="+(params[0] instanceof TypeVariable));
    }


    //定义一个带有泛型的类
    class VariableTest<K extends Integer & Map, V> {
    }

    /**
     * 1.在编译时就已经确定了父类的泛型类型，此时父类的泛型是不用擦除的，因为编译期已经确认了泛型类型，运行时的任何对象都会共用这个泛型类型
     * 所以class对象中就能保存这个泛型不变，假如是运行时确定的泛型类型，因为运行时可能确认不同的泛型类型，而一个class对象只能确定一个泛型类型，
     * 所以只能将不同的泛型作擦除处理，统一为一个类型
     *
     * 2.为什么泛型需要在当前类的父类中获取，因为当前类的泛型不管是编译时确定还是在运行时确定的，都会进行擦除，因为当前类的所有不同泛型的对象需要
     * 共用一个class对象，要想获得子类的泛型，可以通过将子类的泛型传递给父类，而获得编译时确定的子类泛型
     */
    class ParameterTest<K,E> extends SuperType<String, Number> {
    }

    class ParameterTest1<T, V> extends SuperType<T, V> {

    }

    public static class SuperType<T, V> {
    }
}























