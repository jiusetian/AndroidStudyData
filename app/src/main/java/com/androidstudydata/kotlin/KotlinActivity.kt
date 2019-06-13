package com.androidstudydata.kotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.androidstudydata.R
import com.androidstudydata.kotlin.aboutClass.Person
import com.androidstudydata.kotlin.aboutClass.Runoob

class KotlinActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin)


    }

    fun personTest() {

        var person: Person = Person()

        //给属性赋值
        person.lastName = "laowang"
        println("名字是：${person.lastName}")

        person.no = 200;
        println("no的大小：${person.no}")
        //因为height的set方法设置为private，所以不能赋值，但是可以访问get
        //person.height=333.3f
        println("高度=${person.height}")
    }

    fun runoobTest() {
        //这里调用的是次构造函数
        var runoob: Runoob = Runoob("菜鸟教程", 1000)
        //调用了扩展函数
        var p: Person = Person();
        p.Print()
    }

    //创建Person类的扩展函数
    fun Person.Print() {
        println("我是person的扩展函数")
        //在扩展函数中可以调用被扩展类的非私有属性
        method();
    }

    //为MutableList 扩展一个交换函数
    fun MutableList<Int>.swap(index1: Int, index2: Int) {
        val temp = this[index1] //this关键字代表接收者对象
        this[index1] = this[index2]
        this[index2] = temp

    }

    /**
     * 泛型通配符测试
     */
    fun fanxing() {

        // list 为 ArrayList<Number> 类型
        val list = arrayListOf<Number>(1, 2, 3) //一个实参类型是Number的Collection
        // array 为 Array<Float> 类型
        val array = arrayOf(1f, 2f)
        //因为这里上限规定了是Number，所以Float是可以的，
        val list1: List<Number> = list.plus(array)


        /*out 是一个上限通配符，Number 是元素的上限，所以Float是可以的，但是Any不行*/
        var l: List<out Number>

        l= ArrayList<Float>()

    }

    // 定义一个支持协变的类
    class Runoob1<out A>(val a: A) {
        fun foo(): A {
            return a
        }
    }

    // 定义一个支持逆变的类
    class Runoob2<in A>(a: A) {
        fun foo(a: A) {
        }
    }

    /**
     * 参数协变测试方法
     */
    fun xiebian(){

        var strCo: Runoob1<String> = Runoob1("a")
        var anyCo: Runoob1<Any> = Runoob1<Any>("b")
        anyCo = strCo
        println(anyCo.foo())   // 输出 a

    }

    /**
     * 参数逆变测试
     */
    fun nibian(){
        var strDCo = Runoob2("a")
        var anyDCo = Runoob2<Any>("b")

        strDCo = anyDCo
    }



    /**
     * 加在Collection的扩展函数，这个函数是带有泛型的函数，参数是一个array集合，元素是一个泛型，out是泛型上限通配符，代表只读，
     *
     * 返回值是一个list集合，同时是一个泛型
     *
     */
    fun <T> Collection<T>.plus(elements: Array<out T>): List<T> {
        return listOf(elements.get(3))
    }


}
