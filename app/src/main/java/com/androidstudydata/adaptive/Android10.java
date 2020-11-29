package com.androidstudydata.adaptive;

import android.content.Context;
import android.os.Build;

import com.androidstudydata.LogUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

/**
 * Author：Mapogo
 * Date：2020/11/29
 * Note：
 */
public class Android10 {

    public static void apkWrite(Context context) {
        String apkFilePath = context.getExternalFilesDir("apk").getAbsolutePath();
        LogUtils.d("路径="+apkFilePath);
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

}
