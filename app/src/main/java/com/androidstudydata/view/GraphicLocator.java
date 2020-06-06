package com.androidstudydata.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.androidstudydata.LogUtil;
import com.androidstudydata.R;

/**
 * Author：Alex
 * Date：2019/9/4
 * Note：
 */
public class GraphicLocator {

    public Bitmap drawLocator(Context context, String planeName) {
        Bitmap bitmap;
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.quad);

        Bitmap btm = Bitmap.createBitmap((int) (bitmap.getWidth() * 1.5), (int) (bitmap.getHeight()*1.5), Bitmap.Config.ARGB_8888); //建立一个空的BItMap
        Canvas canvas = new Canvas(btm);
        Paint photoPaint = new Paint(); //建立画笔
        photoPaint.setDither(true); //获取跟清晰的图像采样
        photoPaint.setFilterBitmap(true);//过滤一些
        Rect src = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());//创建一个指定的新矩形的坐标
        Rect dst = new Rect(btm.getWidth()/2-bitmap.getWidth()/2, btm.getHeight()/2-bitmap.getHeight()/2,
                bitmap.getWidth()+(btm.getWidth()/2-bitmap.getWidth()/2), bitmap.getHeight()+(btm.getHeight()/2-bitmap.getHeight()/2));//创建一个指定的新矩形的坐标
        canvas.drawBitmap(bitmap, src, dst, photoPaint);//将photo 缩放或则扩大到 dst使用的填充区photoPaint
        Paint textPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DEV_KERN_TEXT_FLAG);//设置画笔
        textPaint.setTextSize(getFontSize(context, 16));//字体大小
        textPaint.setTypeface(Typeface.DEFAULT_BOLD);//采用默认的宽度
        textPaint.setColor(Color.WHITE);//采用的颜色
        int strWidth = (int) getStringWidth2(planeName)*2; //字符串宽度
        LogUtil.INSTANCE.d("长度="+btm.getWidth()+"///"+strWidth);
        canvas.drawText(planeName, btm.getWidth() / 2 - strWidth / 2,btm.getHeight()/2-bitmap.getHeight()/2-2, textPaint);//绘制上去字，中间参数为坐标点
        canvas.save(); //保存
        canvas.restore();
        return btm;
    }

    //字符串宽度
    private int getStringWidth(String string) {
        Rect rect = new Rect();
        Paint paint=new Paint();
        paint.getTextBounds(string, 0, string.length(), rect);
        return rect.width();
    }

    private float getStringWidth2(String string) {
        Paint paint=new Paint();
        return paint.measureText(string);
    }

    /**
     * 根据屏幕分辨率适配字体大小
     *
     * @param context
     * @param textSize
     * @return
     */
    public int getFontSize(Context context, int textSize) {
        DisplayMetrics dm = new
                DisplayMetrics();
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);

        windowManager.getDefaultDisplay().getMetrics(dm);
        int screenHeight
                = dm.heightPixels;
        // screenWidth = screenWidth > screenHeight ?screenWidth :
        // screenHeight;
        int rate = (int) (textSize
                * (float) screenHeight / 1280);
        return rate;
    }

}
