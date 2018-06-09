package com.jachat.translateor.utils;

import com.orhanobut.logger.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by plcgo on 2017/2/9.
 */

public class FileUtils {


    //将byte数组写入文件
    public static void bytesToFile(String path, byte[] content) {

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(path);
            fos.write(content);
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String readFile(File file) {
        String str = "";
        FileInputStream in = null;
        try {
            if(!file.exists()){
                file.createNewFile();
            }
            in = new FileInputStream(file);
            // size  为字串的长度 ，这里一次性读完
            int size = in.available();
            byte[] buffer = new byte[size];
            in.read(buffer);
            str = new String(buffer);
        } catch (IOException e) {
            Logger.e("读文件异常");
            e.printStackTrace();
            return "";
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return str;
    }

    public static boolean writeFile(File file, String content) {
        FileOutputStream fos = null;
        Logger.e(file.getAbsolutePath() + "   " + file.getParentFile());
//        if (!file.getParentFile().exists()) {
//            boolean result = file.getParentFile().mkdirs();
//            if (!result) {
//                Logger.e("创建失败");
//            }
//        }
        boolean isSucceed = false;
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            fos = new FileOutputStream(file);
            Logger.e("content = " + content);
            fos.write(content.getBytes());
            Logger.e("写入成功");
            isSucceed = true;
        } catch (IOException e) {
            Logger.e("文件创建异常");
            e.printStackTrace();
            isSucceed = false;
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return isSucceed;
    }

    /**
          * 删除单个文件
          *
          * @param fileName 要删除的文件的文件名
          * @return 单个文件删除成功返回true，否则返回false
          */
    public static boolean deleteFile(String fileName) {
        File file = new File(fileName);
        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                Logger.e("删除单个文件" + fileName + "成功！");
                return true;
            } else {
                Logger.e("删除单个文件" + fileName + "失败！");
                return false;
            }
        } else {
            Logger.e("删除单个文件失败：" + fileName + "不存在！");
            return false;
        }
    }
}
