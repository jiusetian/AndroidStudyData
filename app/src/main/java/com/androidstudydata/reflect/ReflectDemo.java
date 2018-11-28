package com.androidstudydata.reflect;

import com.androidstudydata.LogUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by XR_liu on 2018/11/26.
 */
public class ReflectDemo {

    public static void test() throws Exception {

//        Class<?> clazz01 = Class.forName("com.androidstudydata.reflect.ReflectDemo$User01");
//
//        Class<?> clazz02 = new User02().getClass();
//
//        Class<?> clazz03 = User03.class;


        Class<?> personClazz = Person.class;

        LogUtils.d("返回指定参数类型、具有public访问权限的构造函数对象");
        //返回指定参数类型、具有public访问权限的构造函数对象
        Constructor cs01 = personClazz.getConstructor(String.class);
        //创建对象
        Person person01 = (Person) cs01.newInstance("锤子");
        person01.setAge(100);
        LogUtils.d("person01=" + person01.toString());

        LogUtils.d("返回指定参数类型、所有声明的（包括private）构造函数对象");

        //返回指定参数类型、所有声明的（包括private）构造函数对象
        Constructor cs02 = personClazz.getDeclaredConstructor(int.class, String.class);
        Person person02 = (Person) cs02.newInstance(25, "二狗");
        LogUtils.d("perosn02=" + person02.toString());

        LogUtils.d("返回所有声明的（包括private）构造函数对象");

        Constructor[] constructors = personClazz.getDeclaredConstructors();
        //查看每个构造方法信息
        for (int i = 0; i < constructors.length; i++) {
            //获取构造函数参数类型
            Class[] classes = constructors[i].getParameterTypes();

            LogUtils.d("构造方法" + i + "信息=" + constructors[i].toString());
            LogUtils.d("参数类型：");
            for (int j = 0; j < classes.length; j++) {
                LogUtils.d(classes[j].getName());
            }
        }

        Class studenClazz = Student.class;

        Student student = (Student) studenClazz.newInstance();
        student.setName("小明");
        Field fieldName = studenClazz.getField("name");
        LogUtils.d("name字段的值=" + fieldName.get(student));

        //重新设置name字段的值
        fieldName.set(student, "锤子");
        LogUtils.d("改变后的name字段的值=" + student.getName());
        LogUtils.d("name字段类型=" + fieldName.getType());
        LogUtils.d("name字段是否为枚举=" + fieldName.isEnumConstant());
        //设置访问性
        fieldName.setAccessible(false);
        LogUtils.d("改变访问属性的name字段=" + fieldName.isAccessible());

        //获取指定名称的(包含private修饰的)字段，不包括继承的字段
        Field field01 = studenClazz.getDeclaredField("score");
        LogUtils.d("指定名称不包含继承包含private修饰的score字段=" + field01);

        //获取指定name名称、具有public修饰的字段，包含继承字段
        Field field02 = studenClazz.getField("name");

        LogUtils.d("指定名称字段public修饰的name，包含继承的=" + field02);

        //获取Class对象所表示的类或接口的所有(包含private修饰的)字段,不包括继承的字段
        Field[] fields = studenClazz.getDeclaredFields();
        for (Field field : fields) {
            LogUtils.d("不包括继承包含private的字段=" + field);
        }

        //获取修饰符为public的字段，包含继承字段
        Field[] fields1 = studenClazz.getFields();
        for (Field field : fields1) {
            LogUtils.d("包含继承public修饰的字段=" + field);
        }

        LogUtils.d("\n\n\n\n");
        //Method相关测试
        Class classStu = Student.class;

        //返回一个指定参数的Method对象
        Method method01 = classStu.getDeclaredMethod("setGrade", String.class);
        LogUtils.d("指定string类型参数的setGrade方法=" + method01);

        //返回所有的不包括继承的包括private成员方法
        Method[] methods = classStu.getDeclaredMethods();
        for (Method method:methods){
            LogUtils.d("返回所有方法，不包括继承但包括private=" +method+"\n");
        }

        //返回包括继承的指定的public成员方法
        Method method02 = classStu.getMethod("setAge", int.class);
        LogUtils.d("返回指定的包括继承的public成员方法=" + method02);

        //返回包括继承的所有public成员方法
        Method[] methods1 = classStu.getMethods();
        for (Method method : methods1) {
            LogUtils.d("返回包括继承的所有public成员方法=" + method+"\n");
        }


    }

    //继承Person
    static class Student extends Person {
        public String grade;
        private int score;


        private String getGrade() {
            return grade;
        }

        public void setGrade(String grade) {
            this.grade = grade;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }
    }


    static class Person {

        //初始化
//        static {
//            LogUtils.d("初始化User类");
//        }

        private int age;
        public String name;

        public Person() {
            super();
        }

        public Person(String name) {
            super();
            this.name = name;
        }

        /**
         * 私有构造
         *
         * @param age
         * @param name
         */
        public Person(int age, String name) {
            super();
            this.age = age;
            this.name = name;

        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }


        @Override
        public String toString() {
            return "Person{" +
                    "age=" + age +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

    static class User01 {

        //初始化
        static {
            LogUtils.d("初始化User01类");
        }
    }

    static class User02 {

        //初始化
        static {
            LogUtils.d("初始化User02类");
        }
    }

    static class User03 {

        //初始化
        static {
            LogUtils.d("初始化User03类");
        }
    }
}
