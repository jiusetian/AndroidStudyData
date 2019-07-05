package com.androidstudydata.kotlin

/**
 * Author：Alex
 * Date：2019/5/9
 * Note：基础语法
 */
class BasicGrammar {

    //不可变变量，类似Java的final
    val a: Int = 1;
    val b = 1; //系统自动判断为int类型
//    val c: Int      // 如果不在声明时初始化则必须提供变量类型
//    c = 1           // 明确赋值

    //可变变量
    var x = 5        // 系统自动推断变量类型为Int
    //这种运算要在方法内
    //x+=1           // 变量可修改


    //返回值int
    public fun sum(a: Int, b: Int): Int {

        return a + b;
    }

    //没有返回值
    fun printlSum(a: Int, b: Int): Unit {
        print(a + b);
    }

    //可变长参数
    fun vars(vararg v: Int) {
        for (vt in v) {
            print(vt);
        }
    }

    //lambda函数
    fun lanbuda() {
        val sumLambda: (Int, Int) -> Int = { x, y -> x + y }
        println(sumLambda(1, 2))  // 输出 3
    }

    /**
     * $表示一个变量名或变量值
     * $varName 表示变量值
     * ${varName.fun()}表示变量的方法的返回值
     */
    fun stringMode() {
        var a = 1;
        val s1 = "a is $a";
        a = 2;
        val s2 = "${s1.replace("is", "was")},but now is $a";
        print(s2);
    }

    /**
     * null 检查机制
     */
    fun nullCheck(args: Array<String>) {

        //类型后面加？表示可以为null
        var age: String? = "22";
        //字段后加!!表示抛出空指针异常
        val ages = age!!.toInt();
        //不做处理返回null
        val age1 = age?.toInt()
        //age为null就返回-1
        val age2 = age?.toInt() ?: -1;

        if (args.size < 2) {
            print("数组大小至少为2")
            return;
        }

        val x = parseInt(args[0]);
        val y = parseInt(args[1]);

        //必须做非空判断，否则会出错
        if (x != null && y != null) {
            print(x * y);
        }

    }

    //?代表可以返回空
    fun parseInt(str: String): Int? {
        return str?.toInt();
    }

    /**
     * 类型检测和自动类型转换
     */
    fun getStringLength(str: Any): Int? {
        if (str is String) {
            return str.length;
        }
        return null;
    }

    /**
     * 区间函数
     */
    fun rangeFun() {
        print("循环输出");
        for (i in 1..4) print(i); //输出1234

        println("\n--------------------------")

        print("设置步长");
        for (i in 1..4 step 2) print(i); //输出13

        println("\n--------------------------")

        print("使用down to");
        for (i in 4 downTo 1 step 2) print(i); //输出42

        println("\n--------------------------")

        print("使用until")
        for (i in 1 until 4) print(i); //输出123

    }

    /**
     * 使用when表达式
     * 其实是取代了switch操作符
     */

    fun describe(obj: Any): String =
            when (obj) {
                1 -> "one"
                "hello" -> "你好"
                is Long -> "Long"
                !is String -> "no string"
                else -> "unknow"
            }

    /**
     * 使用集合
     */
    fun listUse() {
        //创建一个集合
        val items = listOf("apple", "banana", "llll")
        //迭代
        for (item in items) {
            print(item)
        }
        //使用when
        when {
            "orange" in items -> println("ddd")
            "apple" in items -> println("苹果")
        }

    }

}






























