package com.jachat.translateor;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.jachat.translateor.config.Constant;
import com.jachat.translateor.mvp.model.EventObject;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by Perryn on 2017/7/28 0028.
 */

public class BootReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("com.jachat.action.SAVEFILE_OK")) {
            Logger.e("收到升级资源的广播");
            EventBus.getDefault().post(new EventObject(Constant.EVENT_UPGRADE_RES, null));
        }else if(intent.getAction().equals("com.jachat.action.UPDATE_APP")){
            int flag = intent.getIntExtra("flag", -1);
            if(flag == 5){
                EventBus.getDefault().post(new EventObject(Constant.EVENT_UPDATE_APP_PROGRESS, intent.getIntExtra("progress", 0)));
            }else{
                EventBus.getDefault().post(new EventObject(Constant.EVENT_UPDATE_APP, flag));
            }
        }
    }
}
