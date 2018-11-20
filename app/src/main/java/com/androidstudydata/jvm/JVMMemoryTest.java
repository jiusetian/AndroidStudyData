package com.androidstudydata.jvm;

import com.androidstudydata.LogUtils;

/**
 * Created by XR_liu on 2018/11/20.
 * jvm内存分配演示代码
 */
public class JVMMemoryTest {

    /**
     * jvm自动寻找main方法，执行main方法，此时虚拟机栈中有一个代表main方法的栈帧入栈，执行完毕后出栈
     */
    public static void main() {

        /**
         * 1.student是对象的引用，所有会保存在栈帧的局部变量里
         * 2.创建Student的时候，首先进行类的加载工作，类只会加载一次，将类的类型信息数据加载到jvm的方法区中，如果之前加载过了，那么就不会再加载。
         * 3.类的加载其实就是将.class文件加载进虚拟机内存中，在加载的时候，在java堆中生成对应的Class对象，
         * 最后生成一个Student对象在堆中
         *
         * 4.new 关键字后面是类的构造函数，构造函数也是类的静态方法， 所以会促成类的加载，如果类是第一次加载，那么在加载的过程会执行静态代码块，所有加载流程已经完成了，
         * 才真正执行构造方法，所以new一个对象的时候，类的静态代码块会执行在先，然后才会执行到构造方法。
         */
        Student student=new Student(18,"tom","007");

        //再次new student类的时候，此时因为类已经加载过了，所以不会再次加载， 所以就不会执行类的静态代码块了
        Student student1=new Student(180,"tom","007");

        Class stu=Student.class;
        try {
            stu.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        /**
         * 声明定义一个int类型的变量a，因为a是基本数据类型，所以在栈中直接分配一个内存保存这个变量
         */
        int a=9;

        int b=10;

        /**
         * 执行study方法，在栈中加入一个栈帧，执行完毕后这个栈帧将出栈
         * 在study方法中，有两个int类型的局部变量，是保存在栈帧的局部变量内存区中的
         */
        student.study(a,b);

    }


    //静态内部类
    public static class Student extends Person implements IStudyable {

        private static int cnt = 5;

        static {
            cnt++;
            LogUtils.d("执行了student的静态代码块");
        }

        private String sid;

        public Student(){
            super();
        }

        public Student(int age, String name, String sid) {
            super(age, name);
            LogUtils.d("执行了构造方法");
            this.sid = sid;

        }

        public void run() {

            System.out.println("run()...");

        }

        public int study(int a, int b) {

            int c = 10;

            int d = 20;

            return a + b * c - d;

        }

        public static int getCnt() {

            return cnt;

        }
    }

    //父类
    static class Person {

        private String name;

        private int age;


        public Person(){

        }
        public Person(int age, String name) {

            this.age = age;

            this.name = name;

        }

        public void run() {


        }

    }

    //接口
    interface IStudyable {

        public int study(int a, int b);

    }



}
