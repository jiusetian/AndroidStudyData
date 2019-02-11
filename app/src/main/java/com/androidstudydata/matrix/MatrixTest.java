package com.androidstudydata.matrix;

import android.graphics.Matrix;
import android.graphics.RectF;

import com.androidstudydata.LogUtils;

import java.util.Arrays;

/**
 * Created by Administrator on 2019/1/10.
 */

public class MatrixTest {

    public void getValues() {
        Matrix matrix = new Matrix();
        matrix.preScale(2, 2);
        matrix.preTranslate(100, 100);
        float[] floats = new float[9];
        matrix.getValues(floats);
        LogUtils.d("获取的矩阵数据为=" + Arrays.toString(floats));
    }


    public void setContact() {
        Matrix matrix = new Matrix();
        Matrix matrix1 = new Matrix();
        Matrix matrix2 = new Matrix();

        matrix1.setScale(2, 3);
        matrix2.setTranslate(100, 200);

        LogUtils.d("1的数据=" + matrix1.toShortString());
        LogUtils.d("2的数据=" + matrix2.toShortString());
        matrix.setConcat(matrix1, matrix2);

        LogUtils.d("0的数据=" + matrix.toShortString());
        matrix1.preConcat(matrix2);
        LogUtils.d("1数据=" + matrix1.toShortString());
    }

    public void mapPoints1() {

        // 初始数据为三个点 (0, 0) (80, 100) (400, 300)
        float[] pts = new float[]{0, 0, 100, 200, 300, 400};

        // 构造一个matrix，x坐标缩放0.5
        Matrix matrix = new Matrix();
        matrix.setScale(0.5f, 1f);

        // 输出pts计算之前数据
        LogUtils.d("before: " + Arrays.toString(pts));

        // 调用map方法计算
        matrix.mapPoints(pts);

        // 输出pts计算之后数据
        LogUtils.d("after : " + Arrays.toString(pts));
    }

    public void mapPoints2() {

        float[] src = new float[]{0, 0, 100, 200, 300, 400};
        float[] dst=new float[6];
        // 构造一个matrix，x坐标缩放0.5
        Matrix matrix = new Matrix();
        matrix.setScale(0.5f, 1f);

        LogUtils.d("计算前源数据：" + Arrays.toString(src));
        LogUtils.d("计算前目标数据："+Arrays.toString(dst));
        // 调用map方法计算
        matrix.mapPoints(dst,src);

        LogUtils.d("计算后源数据：" + Arrays.toString(src));
        LogUtils.d("计算后目标数据："+Arrays.toString(dst));
    }

    public void mapPoints3(){

        float[] src = new float[]{10, 50, 100, 200, 300, 400};
        float[] dst=new float[6];
        // 构造一个matrix，x坐标缩放0.5
        Matrix matrix = new Matrix();
        matrix.setScale(0.5f, 1f);

        LogUtils.d("计算前源数据：" + Arrays.toString(src));
        LogUtils.d("计算前目标数据："+Arrays.toString(dst));
        // 调用map方法计算
        matrix.mapPoints(dst,0,src,2,2);
        LogUtils.d("计算后源数据：" + Arrays.toString(src));
        LogUtils.d("计算后目标数据："+Arrays.toString(dst));
    }

    public void mapRadius(){

        float radius=100;
        float result;

        Matrix matrix = new Matrix();
        matrix.setScale(0.5f, 1f);
        result=matrix.mapRadius(radius);

        LogUtils.d("计算后的半径="+result);
    }

    public void mapRect(){

        RectF rectF=new RectF(100,100,500,500);

        Matrix matrix = new Matrix();
        matrix.setScale(0.5f, 1f);

        boolean b=matrix.mapRect(rectF);

        LogUtils.d("变换后是否为矩形="+b+"数据为："+rectF.toShortString());
    }
}


























