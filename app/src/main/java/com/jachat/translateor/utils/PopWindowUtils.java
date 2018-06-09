package com.jachat.translateor.utils;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;

/**
 * Created by Perryn on 2017/7/10.
 */

public class PopWindowUtils {

    private PopWindowUtils showPopup;

    private Context context;

    private LayoutInflater inflater;

    private View popView;

    private PopupWindow popupWindow;

    /**
     * 初始化
     * @param context
     */
    public PopWindowUtils (Context context){
        this.context = context;
        inflater = LayoutInflater.from(context);
        showPopup = this;
    }

    /**
     * 输入布局文件，设置popwindow
     * @param layoutId
     * @return
     */
    public PopWindowUtils showSimplePopupWindows(int layoutId){
        popView = inflater.inflate(layoutId,null);
        popupWindow = new PopupWindow(popView, 204, 80);
        popupWindow.setFocusable(true);
        popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        popupWindow.setOutsideTouchable(false);
        ColorDrawable dw = new ColorDrawable(0x30000000);
        popupWindow.setBackgroundDrawable(dw);
        return showPopup;
    }
    /**
     * 输入布局文件，设置popwindow
     */
    public PopWindowUtils showSimplePopupWindows(View view){
        popView = view;
        popupWindow = new PopupWindow(popView, 204, 80);
        popupWindow.setFocusable(true);
        popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        popupWindow.setOutsideTouchable(false);
        ColorDrawable dw = new ColorDrawable(0x30000000);
        popupWindow.setBackgroundDrawable(dw);
        return showPopup;
    }


    /**
     * 闪光灯设置页面Pop
     *
     * @param view
     * @return
     */
    public PopWindowUtils showCameraFlashPopupWindow(View view) {
        popView = view;
        popupWindow = new PopupWindow(popView, 90, 35);
        popupWindow.setFocusable(true);
        popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        popupWindow.setOutsideTouchable(false);
        ColorDrawable dw = new ColorDrawable(0x20000000);
        popupWindow.setBackgroundDrawable(dw);
        return showPopup;
    }


    /**
     * 输入布局文件，设置popwindow
     */
    public PopWindowUtils showKeyboardPopupWindows(View view){
        popView = view;
        popupWindow = new PopupWindow(popView, 240, 188);
        popupWindow.setFocusable(true);
        popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        popupWindow.setOutsideTouchable(false);
        ColorDrawable dw = new ColorDrawable(0x30000000);
        popupWindow.setBackgroundDrawable(dw);
        return showPopup;
    }


    /**
     * 设置弹窗动画
     * @param animId
     * @return showPopu
     */
    public PopWindowUtils setAnim(int animId){
        if (popupWindow!=null){
            popupWindow.setAnimationStyle(animId);

        }
        return showPopup;
    }

    public boolean isShowing(){
        if(popupWindow != null){
            return popupWindow.isShowing();
        }
        return false;
    }

    /**
     * 将弹窗设置在底部
     * @param parent
     * @return
     */
    public PopWindowUtils atBottom(View parent){
        if (popupWindow!=null){
            popupWindow.showAtLocation(parent, Gravity.BOTTOM| Gravity.CENTER_HORIZONTAL,0,50);
        }
        return showPopup;
    }

    public PopWindowUtils atTop(View parent){
        if (popupWindow!=null){
            popupWindow.showAtLocation(parent, Gravity.TOP| Gravity.CENTER_HORIZONTAL,0,50);
        }
        return showPopup;
    }

    public PopWindowUtils atCenter(View parent){
        if (popupWindow!=null){
            popupWindow.showAtLocation(parent, Gravity.CENTER,0,0);
        }
        return showPopup;
    }

    /**
     * 将弹窗设置在底部
     * @param parent
     * @return
     */
    public PopWindowUtils atBottom1(View parent){
        if (popupWindow!=null){
            popupWindow.showAtLocation(parent, Gravity.BOTTOM| Gravity.CENTER_HORIZONTAL,0,0);
        }
        return showPopup;
    }




    /**
     * 设置悬浮框位置，偏移量
     * @param paren
     * @param x
     * @param y
     * @return
     */
    public PopWindowUtils dropDown(View paren, int x, int y){
        if (popupWindow!=null){
            popupWindow.showAsDropDown(paren,x,y);
        }
        return showPopup;
    }

    /**
     * 设置弹窗的位置以及偏移量
     * @param parent
     * @param gravity
     * @param x
     * @param y
     * @return
     */
    public PopWindowUtils atLocation(View parent, int gravity, int x, int y){
        if (popupWindow!=null){
            popupWindow.showAtLocation(parent,gravity,x,y);
        }
        return showPopup;
    }


    /**
     * 设置布局文件中控件的点击事件
     * @param id
     * @param listener
     * @return
     */
    public PopWindowUtils setClick(int id, View.OnClickListener listener){
        if (popView!=null){
            popView.findViewById(id).setOnClickListener(listener);
        }
        return showPopup;
    }

    /**
     * 关闭弹窗的点击事件
     * @param id
     * @return
     */
    public PopWindowUtils setDismissClick(int id){
        if (popupWindow!=null && popView!=null){
            popView.findViewById(id).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    popupWindow.dismiss();
                }
            });
        }
        return showPopup;
    }

    public void closePopupWindow(){
        if (popupWindow!=null){
            popupWindow.dismiss();
        }
    }

    /**
     * 获取view
     * @return
     */
    public View getView(){
        if (popView!=null){
            return popView;
        }
        return null;
    }
}
