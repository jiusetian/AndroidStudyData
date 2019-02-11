package com.androidstudydata.matrix;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.androidstudydata.LogUtils;
import com.androidstudydata.R;

/**
 * Created by Administrator on 2019/1/9.
 */

public class MatrixSetPolyToPoly extends View {

    private Bitmap mBitmap;             // 要绘制的图片
    private Matrix mPolyMatrix;         // 测试setPolyToPoly用的Matrix
    private Paint paint;

    public MatrixSetPolyToPoly(Context context, AttributeSet attrs) {
        super(context, attrs);
//        mPolyMatrix = new Matrix();
//        paint=new Paint();
        initBitmapAndMatrix();
    }

    private void initBitmapAndMatrix() {

        paint = new Paint();
        LogUtils.d("执行了初始化");
        mBitmap = BitmapFactory.decodeResource(getResources(),
                R.mipmap.polytest);

        mPolyMatrix = new Matrix();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //canvas.drawBitmap(mBitmap,0,100,paint);

        setLayerType(View.LAYER_TYPE_SOFTWARE, null);//关闭硬件加速
        paint.setColor(Color.RED);

        //one(canvas);

        //two(canvas);
        //three(canvas);
        fours(canvas);

    }


    private void one(Canvas canvas) {

        //先画一个蓝色的矩形
        paint.setColor(Color.BLUE);
        canvas.drawRect(0,0,400,400,paint);

        //相当于这个正方形以源坐标和目标坐标的变化为参考，即当源坐标到目标坐标的x和y值变化多少，正方形也跟着这个值变化
        mPolyMatrix.setPolyToPoly(new float[]{100, 100}, 0, new float[]{200, 200}, 0, 1);
        canvas.setMatrix(mPolyMatrix);
        paint.setColor(Color.RED);
        //通过了setPolyToPoly的改变，再画一个红色矩形看有什么变化
        canvas.drawRect(0, 0, 400, 400, paint);

    }

    private void two(Canvas canvas){
        //先画一个蓝色的矩形
        paint.setColor(Color.BLUE);
        canvas.drawRect(0,0,200,200,paint);

        mPolyMatrix.setPolyToPoly(new float[]{200,200,200,400}, 0, new float[]{200,200,300,400}, 0, 2);

        canvas.setMatrix(mPolyMatrix);
        paint.setColor(Color.RED);
        //通过了setPolyToPoly的改变，再画一个红色矩形看有什么变化
        canvas.drawRect(0, 200, 200, 400, paint);
    }

    private void three(Canvas canvas){
        //先画一个蓝色的矩形
        paint.setColor(Color.BLUE);
        canvas.drawRect(0,0,200,200,paint);

        mPolyMatrix.setPolyToPoly(new float[]{0,200,200,200,200,400}, 0, new float[]{0,200,200,200,300,400}, 0, 3);
        canvas.setMatrix(mPolyMatrix);
        paint.setColor(Color.RED);
        //通过了setPolyToPoly的改变，再画一个红色矩形看有什么变化
        canvas.drawRect(0, 200, 200, 400, paint);
    }

    private void fours(Canvas canvas){
        //先画一个蓝色的矩形
        paint.setColor(Color.BLUE);
        canvas.drawRect(0,0,200,200,paint);

        mPolyMatrix.setPolyToPoly(new float[]{0,200,200,200,200,400,0,400}, 0, new float[]{0,200,200,200,300,300,0,400}, 0, 4);
        canvas.setMatrix(mPolyMatrix);
        paint.setColor(Color.RED);
        //通过了setPolyToPoly的改变，再画一个红色矩形看有什么变化
        canvas.drawRect(0, 200, 200, 400, paint);
    }


    private void four(Canvas canvas) {

        float[] src = {0, 0,                                    // 左上
                mBitmap.getWidth(), 0,                          // 右上
                mBitmap.getWidth(), mBitmap.getHeight(),        // 右下
                0, mBitmap.getHeight()};                        // 左下

        float[] dst = {0, 0,                                    // 左上
                mBitmap.getWidth(), 40,                        // 右上
                mBitmap.getWidth(), mBitmap.getHeight() - 20,  // 右下
                0, mBitmap.getHeight()};                        // 左下

        // 核心要点
        mPolyMatrix.setPolyToPoly(src, 0, dst, 0, 4); // src.length >> 1 为位移运算 相当于处以2

        // 此处为了更好的显示对图片进行了等比缩放和平移(图片本身有点大)
//        mPolyMatrix.postScale(0.26f, 0.26f);
        mPolyMatrix.postTranslate(0, 200);

        // 根据Matrix绘制一个变换后的图片
        canvas.drawBitmap(mBitmap, mPolyMatrix, null);
    }
}



















