package com.jachat.translateor;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.SupplicantState;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.BatteryManager;

import com.jachat.translateor.config.Constant;
import com.jachat.translateor.config.KeyCode;
import com.jachat.translateor.mvp.model.ChargeBean;
import com.jachat.translateor.mvp.model.EventObject;
import com.jachat.translateor.utils.IntenetUtil;
import com.jachat.translateor.utils.SPUtils;
import com.jachat.translateor.utils.WifiUtils;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by Perryn on 2017/8/24 0024.
 */

public class MainReceiver extends BroadcastReceiver {

    private boolean isSleep;

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        //Logger.e("action = " + action);

        if (Intent.ACTION_SCREEN_ON.equals(action)) {
            //Logger.e("屏幕打开了");
            isSleep = false;
            EventBus.getDefault().post(new EventObject(Constant.EVENT_SCREEN_ON, null));
        } else if (Intent.ACTION_SCREEN_OFF.equals(action)) {
            //Logger.e("屏幕锁屏了");
            isSleep = true;
            EventBus.getDefault().post(new EventObject(Constant.EVENT_SCREEN_OFF, null));
        } else if (Intent.ACTION_BATTERY_CHANGED.equals(action)) {
            //是否充电状态
            int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
            ChargeBean bean = new ChargeBean();
            if (status == BatteryManager.BATTERY_STATUS_CHARGING || status == BatteryManager.BATTERY_STATUS_FULL) {
                bean.isCharging = true;
            } else {
                bean.isCharging = false;
                if ((boolean) SPUtils.get(context, Constant.SP_BOOT, false)) {
                    SPUtils.put(context, Constant.SP_BOOT, false);
                }
            }
            //电池电量
            int level = intent.getIntExtra("level", 0);
            //Logger.e("level = " + level);
            bean.curElectric = level;
            EventBus.getDefault().post(new EventObject(Constant.EVENT_BATTERY_CHANGED, bean));
        }
        if (isSleep) return;
        //Logger.e("action = " + action);
        switch (action) {
            case ConnectivityManager.CONNECTIVITY_ACTION:
                int state = IntenetUtil.getNetworkState(context);
                Logger.e("network state = " + state);
                EventBus.getDefault().post(new EventObject(Constant.EVENT_NETWORK_STATE, state));
                break;
            case WifiManager.SCAN_RESULTS_AVAILABLE_ACTION://wifi已成功扫描到可用wifi。
                EventBus.getDefault().post(new EventObject(Constant.EVENT_WIFI_SCAN_LIST, null));
                break;
            case WifiManager.RSSI_CHANGED_ACTION:
                WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
                WifiInfo info = wifiManager.getConnectionInfo();
                if (info.getBSSID() != null) {
                    int strength = WifiManager.calculateSignalLevel(info.getRssi(), 5);// 连接信号强度
                    //Logger.e("wifi信号强度 = " + strength);
                    EventBus.getDefault().post(new EventObject(Constant.EVENT_WIFI_SIGNAL_CHANGE, strength));
                }
                break;
            case WifiManager.NETWORK_STATE_CHANGED_ACTION:
                NetworkInfo info1 = intent.getParcelableExtra(WifiManager.EXTRA_NETWORK_INFO);
                //Logger.json(JSON.toJSONString(info1));
                if (info1.getState().equals(NetworkInfo.State.DISCONNECTED)) {
                    Logger.e("连接已断开");
                    EventBus.getDefault().post(new EventObject(Constant.EVENT_WIFI_CONN_DISCONNECT, null));
                } else if (info1.getState().equals(NetworkInfo.State.CONNECTED)) {
                    WifiInfo wifiInfo = WifiUtils.getInstance(context).getWifiManager().getConnectionInfo();
                    //Logger.e("已连接到网络:" + wifiInfo.getSSID());
                    if ("<unknown ssid>".equals(wifiInfo.getSSID())) return;
                    if ("0x".equals(wifiInfo.getSSID())) return;
                    //Logger.json(JSON.toJSONString(wifiInfo));
                    if (SupplicantState.COMPLETED != wifiInfo.getSupplicantState()) return;
                    Logger.e("ssid = " + wifiInfo.getSSID());
                    EventBus.getDefault().post(new EventObject(Constant.EVENT_WIFI_CONNECT_SUCCEED, wifiInfo.getSSID().replaceAll("\"", "")));
                } else {
                    EventBus.getDefault().post(new EventObject(Constant.EVENT_WIFI_CONN_STATE, info1.getDetailedState()));
                }
                break;
            case WifiManager.SUPPLICANT_STATE_CHANGED_ACTION:
                SupplicantState supplicantState = intent.getParcelableExtra(WifiManager.EXTRA_NEW_STATE);
                //Logger.e(supplicantState.name());
                int linkWifiResult = intent.getIntExtra(WifiManager.EXTRA_SUPPLICANT_ERROR, 123);
                if (linkWifiResult == WifiManager.ERROR_AUTHENTICATING) {
                   // Logger.e("密码错误");
                    EventBus.getDefault().post(new EventObject(Constant.EVENT_WIFI_PASSWORD_ERROR, null));
                }
                break;
            case "android.intent.action.SIM_STATE_CHANGED":
                EventBus.getDefault().post(new EventObject(Constant.EVENT_SIM_STATE, null));
                break;
            case "com.jachat.action.MOTHER_TONGUE_D":
                EventBus.getDefault().post(new EventObject(Constant.EVENT_KEY_MOTHER_TONGUE_DOWN, 1));
                break;
            case "com.jachat.action.MOTHER_TONGUE_U":
                EventBus.getDefault().post(new EventObject(Constant.EVENT_KEY_MOTHER_TONGUE_UP, 1));
                break;
            case "com.jachat.action.FOREIGN_LANGUAGE_D":
                EventBus.getDefault().post(new EventObject(Constant.EVENT_KEY_FOREIGN_LANGUAGE_DOWN, 1));
                break;
            case "com.jachat.action.FOREIGN_LANGUAGE_U":
                EventBus.getDefault().post(new EventObject(Constant.EVENT_KEY_FOREIGN_LANGUAGE_UP, 0));
                break;
            case "com.jachat.action.REPLAY_D":
                EventBus.getDefault().post(new EventObject(Constant.EVENT_KEY_CANCEL, 1));
                break;
            case Intent.ACTION_CLOSE_SYSTEM_DIALOGS:
                EventBus.getDefault().post(new EventObject(KeyCode.KEY_HOME,null));
                break;
        }

    }
}
