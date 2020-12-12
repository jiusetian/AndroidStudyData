package com.androidstudydata.bitmap;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.os.Environment;
import android.util.Log;
import android.view.View;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Author：Mapogo
 * Date：2020/12/9
 * Note：
 */
public class PictureCompress {

    /**
     * 质量压缩方法
     * 图片的质量压缩，会改变图片在磁盘中的大小（File文件的大小），不能改变图片加载时在内存中的大小
     * 因为质量压缩不会改变图片的分辨率，而图片在内存中的大小是根据 width*height*(一个像素的所占用的字节数) 计算的
     * 原理是：通过算法扣掉（同化）了 图片中的一些某个点附近相近的像素，达到降低质量减少文件大小的目的
     * 应用场景：图片的上传
     *
     * @param image
     * @return
     */
    public static Bitmap compressImage(Bitmap image) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);// 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 90;
        while (baos.toByteArray().length / 1024 > 100) { // 循环判断如果压缩后图片是否大于100kb,大于继续压缩
            baos.reset(); // 重置baos即清空baos
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);// 这里压缩options%，把压缩后的数据存放到baos中
            options -= 10;// 每次都减少10
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());// 把压缩后的数据baos存放到ByteArrayInputStream中
        // 将图片数据的流转换为Bitmap
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);// 把ByteArrayInputStream数据生成图片
        return bitmap;
    }

    /**
     * 图片按比例大小压缩方法
     * 图片的尺寸压缩是指：按照一定的倍数对图片减少单位尺寸的像素值，可以改变图片在内存中的大小，不改变图片在磁盘中的大小。
     * 原理是：通过减少单位尺寸的像素值，真正意义上的降低像素值。
     * 应用场景：用户头像的缩略图
     *
     * @param srcPath （根据路径获取图片并压缩）
     * @return
     */
    public static Bitmap getimage(String srcPath) {

        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        // 开始读入图片，此时把options.inJustDecodeBounds 设回true了
        newOpts.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeFile(srcPath, newOpts);// 此时返回bm为空
        newOpts.inJustDecodeBounds = false;
        int w = newOpts.outWidth;
        int h = newOpts.outHeight;
        // 现在主流手机比较多是800*480分辨率，所以高和宽我们设置为
        float hh = 800f;// 这里设置高度为800f
        float ww = 480f;// 这里设置宽度为480f
        // 缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
        int be = 1;// be=1表示不缩放
        if (w > h && w > ww) {// 如果宽度大的话根据宽度固定大小缩放
            be = (int) (newOpts.outWidth / ww);
        } else if (w < h && h > hh) {// 如果高度高的话根据宽度固定大小缩放
            be = (int) (newOpts.outHeight / hh);
        }
        if (be <= 0)
            be = 1;
        newOpts.inSampleSize = be;// 设置缩放比例
        // 重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
        bitmap = BitmapFactory.decodeFile(srcPath, newOpts);
        return compressImage(bitmap);// 压缩好比例大小后再进行质量压缩
    }

    /**
     * 图片按比例大小压缩方法
     *
     * @param image （根据Bitmap图片压缩）
     * @return
     */
    public static Bitmap compressScale(Bitmap image) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        // 判断如果图片大于1M,进行压缩避免在生成图片（BitmapFactory.decodeStream）时溢出
        if (baos.toByteArray().length / 1024 > 1024) {
            baos.reset();// 重置baos即清空baos
            image.compress(Bitmap.CompressFormat.JPEG, 80, baos);// 这里压缩50%，把压缩后的数据存放到baos中
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        // 开始读入图片，此时把options.inJustDecodeBounds 设回true了
        newOpts.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);
        newOpts.inJustDecodeBounds = false;
        int w = newOpts.outWidth;
        int h = newOpts.outHeight;
        Log.i("TAG", w + "---------------" + h);
        // 现在主流手机比较多是800*480分辨率，所以高和宽我们设置为
        // float hh = 800f;// 这里设置高度为800f
        // float ww = 480f;// 这里设置宽度为480f
        float hh = 512f;
        float ww = 512f;
        // 缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
        int be = 1;// be=1表示不缩放
        if (w > h && w > ww) {// 如果宽度大的话根据宽度固定大小缩放
            be = (int) (newOpts.outWidth / ww);
        } else if (w < h && h > hh) { // 如果高度高的话根据高度固定大小缩放
            be = (int) (newOpts.outHeight / hh);
        }
        if (be <= 0)
            be = 1;
        newOpts.inSampleSize = be; // 设置缩放比例
        // newOpts.inPreferredConfig = Config.RGB_565;//降低图片从ARGB888到RGB565
        // 重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
        isBm = new ByteArrayInputStream(baos.toByteArray());
        bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);
        return compressImage(bitmap);// 压缩好比例大小后再进行质量压缩
        //return bitmap;
    }

    /**
     * 采样率压缩
     * <p>
     * 采样率压缩是通过设置BitmapFactory.Options.inSampleSize，来减小图片的分辨率，进而减小图片所占用的磁盘空间和内存大小。
     * 设置的inSampleSize会导致压缩的图片的宽高都为1/inSampleSize，整体大小变为原始图片的inSampleSize平方分之一，当然，这些有些注意点：
     * 1、inSampleSize小于等于1会按照1处理
     * 2、inSampleSize只能设置为2的平方，不是2的平方则最终会减小到最近的2的平方数，如设置7会按4进行压缩，设置15会按8进行压缩
     *
     * @param inSampleSize 可以根据需求计算出合理的inSampleSize
     */
    public static void compressSample(int inSampleSize) {
        File sdFile = Environment.getExternalStorageDirectory();
        File originFile = new File(sdFile, "originImg.jpg");
        BitmapFactory.Options options = new BitmapFactory.Options();
        //设置此参数是仅仅读取图片的宽高到options中，不会将整张图片读到内存中，防止oom
        options.inJustDecodeBounds = true;
        Bitmap emptyBitmap = BitmapFactory.decodeFile(originFile.getAbsolutePath(), options);

        options.inJustDecodeBounds = false;
        options.inSampleSize = inSampleSize;
        Bitmap resultBitmap = BitmapFactory.decodeFile(originFile.getAbsolutePath(), options);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        resultBitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
        try {
            FileOutputStream fos = new FileOutputStream(new File(sdFile, "resultImg.jpg"));
            fos.write(bos.toByteArray());
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 缩放压缩
     * 通过减少图片的像素来降低图片的磁盘空间大小和内存大小，可以用于缓存缩略图
     *
     * @param v
     */
    public void compress(View v) {
        File sdFile = Environment.getExternalStorageDirectory();
        File originFile = new File(sdFile, "originImg.jpg");
        Bitmap bitmap = BitmapFactory.decodeFile(originFile.getAbsolutePath());
        //设置缩放比
        int radio = 8;
        Bitmap result = Bitmap.createBitmap(bitmap.getWidth() / radio, bitmap.getHeight() / radio, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(result);
        RectF rectF = new RectF(0, 0, bitmap.getWidth() / radio, bitmap.getHeight() / radio);
        //将原图画在缩放之后的矩形上
        canvas.drawBitmap(bitmap, null, rectF, null);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        result.compress(Bitmap.CompressFormat.JPEG, 100, bos);
        try {
            FileOutputStream fos = new FileOutputStream(new File(sdFile, "sizeCompress.jpg"));
            fos.write(bos.toByteArray());
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
