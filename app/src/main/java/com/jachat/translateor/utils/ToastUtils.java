package com.jachat.translateor.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by plcgo on 2016/6/22.
 */
public class ToastUtils {
    // 声明一个静态的 Toast 成员
    private static Toast toast;

    /**
     * 显示 Toast 的静态方法
     * @param context 上下文对象
     * @param content 要显示的 String 类型内容
     */
    public static void showShortToast(Context context, String content){

        // 判断Toast对象是否为空
        // 如果为空，就创建一个 Toast 对象
        // 如果不为空，就直接改变要显示的内容而不用 new 一个新的 Toast
        if(toast == null){
            toast = Toast.makeText(context.getApplicationContext(), content, Toast.LENGTH_SHORT);
        }
        // 设置要显示的内容
        toast.setText(content);
        // 显示 Toast
        toast.show();
    }

    /**
     * 显示 Toast 的静态方法
     * @param context 上下文对象
     * @param content 要显示的 String 类型内容
     */
    public static void showLongToast(Context context, String content){

        // 判断Toast对象是否为空
        // 如果为空，就创建一个 Toast 对象
        // 如果不为空，就直接改变要显示的内容而不用 new 一个新的 Toast
        if(toast == null){
            toast = Toast.makeText(context.getApplicationContext(), content, Toast.LENGTH_LONG);
        }

        // 设置要显示的内容
        toast.setText(content);

        // 显示 Toast
        toast.show();
    }
}
