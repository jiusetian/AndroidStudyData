package com.androidstudydata.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
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
    private int count = 6;
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
        radarPaint.setColor(Color.BLACK);
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
        drawPolygon(canvas);

        // 画线
        drawLines(canvas);

        canvas.restore();
    }

    // 画线
    private void drawLines(Canvas canvas) {
        Path path1 = new Path();
        Path path2 = new Path();
        Path path3 = new Path();
        Path[] paths = {path1, path2, path3};

        for (int i = 0; i < count; i++) {
            // 计算弧度
            float rad = (float) (60 * i * (Math.PI / 180));
            // x坐标和y坐标
            float x = (float) (Math.cos(rad) * radius);
            float y = (float) (Math.sin(rad) * radius);

            if (i == 0 || i == 1 || i == 2) {
                paths[i].moveTo(x, y);
            } else if (i == 3 || i == 4 || i == 5) {
                paths[i-3].lineTo(x, y);
                canvas.drawPath(paths[i-3], radarPaint);
            }
        }
    }

    // 画多边形
    private void drawPolygon(Canvas canvas) {
        // 路径
        Path polyPath = new Path();
        // 画六个多边形
        for (int i = 0; i < count; i++) {
            // 重置
            polyPath.reset();
            // 一个多边形有6个点
            for (int j = 0; j < 6; j++) {
                // 计算弧度
                float rad = (float) (60 * j * (Math.PI / 180));
                // x坐标和y坐标
                float x = (float) (Math.cos(rad) * (radius * (count - i) / 6));
                float y = (float) (Math.sin(rad) * (radius * (count - i) / 6));
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
