package com.androidstudydata.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Author：Mapogo
 * Date：2020/12/8
 * Note：画雷达
 */
public class RedarView extends View {

    //中心X
    private int centerX;
    //中心Y
    private int centerY;
    //雷达区画笔
    private Paint radarPaint;
    // 多边形个数
    private int polygonCount = 50;
    // 多边形的边数
    private int polygonSides = 10;
    // 圆形半径
    private float radius;


    public RedarView(Context context) {
        super(context);
        init();
    }

    public RedarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RedarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        radarPaint = new Paint();
        radarPaint.setColor(Color.RED);
        radarPaint.setAntiAlias(true);
        radarPaint.setStrokeWidth(1);
        radarPaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        // 将原点移动到画布中心
        canvas.translate(centerX, centerY);

        // 画多边形
        drawPolygonByMatrix(canvas);

        // 画线
        drawLines(canvas);

        canvas.restore();
    }

    private void drawLines(Canvas canvas) {
        // 路径
        Path polyPath = new Path();
        Matrix matrix = new Matrix();
        // 初始点
        float[] xy = {radius, 0};
        polyPath.moveTo(xy[0], xy[1]);
        polyPath.lineTo(0, 0);
        // 画第一条线
        canvas.drawPath(polyPath, radarPaint);

        for (int j = 1; j < polygonSides; j++) {
            polyPath.reset();
            matrix.reset();
            // 旋转度数
            matrix.setRotate(360f / polygonSides, 0, 0);
            matrix.mapPoints(xy);
            // 旋转后xy的值也发生了改变
            polyPath.moveTo(xy[0], xy[1]);
            polyPath.lineTo(0, 0);
            canvas.drawPath(polyPath, radarPaint);
        }
    }

    // 通过矩阵来计算坐标值，并画多边形
    private void drawPolygonByMatrix(Canvas canvas) {
        // 路径
        Path polyPath = new Path();
        Matrix matrix = new Matrix();
        // 画六个多边形
        for (int i = 0; i < polygonCount; i++) {
            // 重置
            polyPath.reset();
            matrix.reset();
            // 初始点
            float[] xy = {radius * (polygonCount - i) / polygonCount, 0};
            polyPath.moveTo(xy[0], xy[1]);

            for (int j = 1; j < polygonSides; j++) {
                // 旋转度数
                matrix.setRotate(360f / polygonSides, 0, 0);
                matrix.mapPoints(xy);
                // 旋转后xy的值也发生了改变
                polyPath.lineTo(xy[0], xy[1]);
            }
            // 添加完点以后进行绘制
            polyPath.close();
            canvas.drawPath(polyPath, radarPaint);
        }
    }


    // 画多边形
    private void drawPolygon(Canvas canvas) {
        // 路径
        Path polyPath = new Path();
        // 画六个多边形
        for (int i = 0; i < polygonCount; i++) {
            // 重置
            polyPath.reset();
            // 一个多边形有6个点
            for (int j = 0; j < polygonSides; j++) {
                // 计算弧度
                float rad = (float) ((360 / polygonSides) * j * (Math.PI / 180));
                // x坐标和y坐标
                float x = (float) (Math.cos(rad) * (radius * (polygonCount - i) / polygonCount));
                float y = (float) (Math.sin(rad) * (radius * (polygonCount - i) / polygonCount));
                if (j == 0) { // 第一个点
                    polyPath.moveTo(x, y);
                } else {
                    polyPath.lineTo(x, y);
                }
            }
            // 添加完点以后进行绘制
            polyPath.close();
            canvas.drawPath(polyPath, radarPaint);
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        centerX = w >> 1;
        centerY = h >> 1;
        radius = Math.min(w, h) * 0.9f * 0.5f;
    }
}
