package com.androidstudydata.propertyanima;

import android.animation.TypeEvaluator;

/**
 * Created by XR_liu on 2018/11/14.
 */
public class PointEvaluator implements TypeEvaluator<Point> {

    @Override
    public Point evaluate(float fraction, Point startValue, Point endValue) {

        float x = startValue.getX() + (endValue.getX() - startValue.getX()) * fraction;
        float y = startValue.getY() + (endValue.getY() - startValue.getY()) * fraction;
        Point point = new Point(x, y);
        return point;
    }
}
