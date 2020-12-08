package com.androidstudydata.adaptive;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;

import com.androidstudydata.LogUtils;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

/**
 * Author：Mapogo
 * Date：2020/11/29
 * Note：
 */
public class Android10 {

    /**
     * 写文件到download目录中
     *
     * @param context
     */
    public static void writeDownloadFile(Context context) {

        ContentResolver resolver = context.getContentResolver();
        ContentValues values = new ContentValues();
        values.put(MediaStore.Downloads.DISPLAY_NAME, "FILENAME");
        //设置文件类型（有哪些类型网上很容易查到，如果不设置的话，就是默认没有扩展名的文件）
        values.put(MediaStore.Downloads.MIME_TYPE, "text/plain");
        //这个方法只可在Android10的手机上执行，设置路径
        values.put(MediaStore.Downloads.RELATIVE_PATH, "Download/mypath");
        Uri external = MediaStore.Downloads.EXTERNAL_CONTENT_URI;
        // 写入文件
        Uri insertUri = resolver.insert(external, values);

        // io写入
        OutputStream os = null;
        String test = "aaaaaaaaaa";
        try {
            os = resolver.openOutputStream(insertUri);
            if (os == null) {
                return;
            }
            byte[] buffer = test.getBytes();
            os.write(buffer, 0, buffer.length);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (os != null) {
                    os.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public static void apkWrite(Context context) {
        LogUtils.d("测试apply code changes3333");
        // Android 10 要使用getExternalFilesDir获取外部存储的私有目录，不能使用getExternalStorageDirectory
        // 外部存储的根目录中创建文件
        String apkFilePath = context.getExternalFilesDir("apk").getAbsolutePath();
        LogUtils.d("路径=" + apkFilePath);
        File newFile = new File(apkFilePath + File.separator + "temp.apk");
        OutputStream os = null;
        try {
            os = new FileOutputStream(newFile);
            if (os != null) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    os.write("file is created".getBytes(StandardCharsets.UTF_8));
                }
                os.flush();
            }
        } catch (IOException e) {
        } finally {
            try {
                if (os != null) {
                    os.close();
                }
            } catch (IOException e1) {

            }
        }
    }

    //这里的fileName指文件名，不包含路径
    //relativePath 包含某个媒体下的子路径
    private static Uri insertFileIntoMediaStore(Context context, String fileName,
                                                String fileType, String relativePath) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
            return null;
        }
        ContentResolver resolver = context.getContentResolver();
        //设置文件参数到ContentValues中
        ContentValues values = new ContentValues();
        //设置文件名
        values.put(MediaStore.Downloads.DISPLAY_NAME, fileName);
        //设置文件描述，这里以文件名为例子
        //values.put(MediaStore.Downloads.DESCRIPTION, fileName);
        //设置文件类型
        values.put(MediaStore.Downloads.MIME_TYPE, "application/vnd.android.package-archive");
        //注意RELATIVE_PATH需要targetVersion=29
        //故该方法只可在Android10的手机上执行
        values.put(MediaStore.Downloads.RELATIVE_PATH, relativePath);
        //EXTERNAL_CONTENT_URI代表外部存储器
        Uri external = MediaStore.Downloads.EXTERNAL_CONTENT_URI;
        //insertUri表示文件保存的uri路径
        Uri insertUri = resolver.insert(external, values);
        return insertUri;
    }

    /**
     * 将一个源文件内容写入到目标文件
     *
     * @param context
     * @param insertUri  目标写入文件uri
     * @param sourcePath 源文件路径
     */
    private static void saveFile(Context context, Uri insertUri, String sourcePath) {
        if (insertUri == null) {
            return;
        }
        String mFilePath = insertUri.toString();
        InputStream is = null;
        OutputStream os = null;
        try {
            os = context.getContentResolver().openOutputStream(insertUri);
            if (os == null) {
                return;
            }
            int read;
            File sourceFile = new File(sourcePath);
            if (sourceFile.exists()) { // 文件存在时
                // 将源文件读入输入流
                is = new FileInputStream(sourceFile); // 读入原文件
                byte[] buffer = new byte[1024];
                while ((read = is.read(buffer)) != -1) {
                    // 将读到的输入流写到目标文件中
                    os.write(buffer, 0, read);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
                if (os != null) {
                    os.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 使用MediaStore读取公共目录下的文件
     *
     * @param context
     * @param uri
     * @return
     */
    private static Bitmap readUriToBitmap(Context context, Uri uri) {
        ParcelFileDescriptor parcelFileDescriptor = null;
        FileDescriptor fileDescriptor = null;
        Bitmap tagBitmap = null;
        try {
            parcelFileDescriptor = context.getContentResolver().openFileDescriptor(uri, "r");
            if (parcelFileDescriptor != null && parcelFileDescriptor.getFileDescriptor() != null) {
                fileDescriptor = parcelFileDescriptor.getFileDescriptor();
                //转换uri为bitmap类型
                tagBitmap = BitmapFactory.decodeFileDescriptor(fileDescriptor);
                // 你可以做的~~
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (parcelFileDescriptor != null) {
                    parcelFileDescriptor.close();
                }
            } catch (IOException e) {
            }
        }
        return tagBitmap;
    }

    /**
     * 删除文件
     *
     * @param context
     * @param fileUri
     */
    public static void deleteFile(Context context, Uri fileUri) {
        context.getContentResolver().delete(fileUri, null, null);
    }


}
