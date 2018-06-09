package com.jachat.translateor.mvp.presenter;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

import com.jachat.translateor.config.ConfConstant;
import com.jachat.translateor.config.Constant;
import com.jachat.translateor.mvp.contract.MainContract;
import com.jachat.translateor.mvp.model.EventObject;
import com.jachat.translateor.mvp.model.Info;
import com.jachat.translateor.mvp.model.ResInfoResponse;
import com.jachat.translateor.utils.FileUtils;
import com.jachat.translateor.utils.JsonUtils;
import com.jachat.translateor.utils.SystemUtils;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.util.List;

/**
 * Created by jachat01 on 2017/12/20.
 */

public class MainPresenter extends MainContract.Presenter{

    private Context mContext;
    private MainContract.View mView;

    public MainPresenter(Context context, MainContract.View view) {
        mContext = context;
        mView = view;
    }

    @Override
    public void fileOpe(List<ResInfoResponse.ItemsBean> items) {
        if (items != null) {
            for (int i = 0; i < items.size(); i++) {
                if ("save".equals(items.get(i).action)) {//下载资源文件
                    String str = FileUtils.readFile(new File(ConfConstant.PATH_CONFIRM));
                    //Logger.e("info = " + str);
                    int version = 0;
                    if (!"".equals(str)) {
                        Info info = JsonUtils.fromJson(str, Info.class);
                        version = info.version;
                    }
                    if (items.get(i).verno > version) {
                        Intent intent = new Intent();
                        intent.setAction("com.jachat.action.SAVEFILE");
                        intent.putExtra("url", items.get(i).path);
                        intent.putExtra("dir", items.get(i).clientpath);
                        //intent.putExtra("file", "jachat.apk");
                        mContext.sendBroadcast(intent);
                        Logger.e("发送广播成功");
                        //mView.onUpgradeResSucceed(items.get(i).verno);
                    }
                } else if ("updateapp".equals(items.get(i).action)) {//检测版本更新
                    if (items.get(i).verno > SystemUtils.getVersionCode(mContext)) {
                        Intent intent = new Intent();
                        intent.setAction("com.jachat.action.UPGRADE");
                        intent.putExtra("url", items.get(i).path);
                        intent.putExtra("apkName", "translator.apk");
                        intent.putExtra("packageName", "com.jachat.translateor");
                        intent.putExtra("launcherActivity", "com.jachat.translateor.MainActivity");
                        mContext.sendBroadcast(intent);
                        Logger.e("发送升级广播成功");
                        EventBus.getDefault().post(new EventObject(Constant.EVENT_UPDATE_APP, 0));
                    }
                } else if ("delete".equals(items.get(i).action)) {//删除资源文件
                    Intent intent = new Intent();
                    intent.setAction("com.jachat.action.DELETE");
                    intent.putExtra("path", items.get(i).clientpath);
                    mContext.sendBroadcast(intent);
                    Logger.e("发送删除广播成功");
                } else if ("updatesys".equals(items.get(i).action)) {
                    Intent intentUpSys = new Intent(Intent.ACTION_MAIN);
                    intentUpSys.addCategory(Intent.CATEGORY_LAUNCHER);
                    ComponentName cn = new ComponentName("com.jachat.systemupdate", "com.jachat.systemupdate.MainActivity");
                    intentUpSys.setComponent(cn);
                    mContext.startActivity(intentUpSys);
                }
            }
        }
    }


}
