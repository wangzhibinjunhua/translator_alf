package com.jachat.translateor.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.orhanobut.logger.Logger;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by Perryn on 2017/8/19 0019.
 */

public class WifiUtils {

    public static final int WIFICIPHER_WPA = 3;
    public static final int WIFICIPHER_WEP = 2;
    public static final int WIFICIPHER_NOPASS = 1;

    private static volatile WifiUtils instance;
    private WifiManager mWifiManager;
    private WifiUtils(Context context){
        if (mWifiManager == null) {
            mWifiManager = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        }
    }

    /**
     * 单例模式 获取实例方法
     * @return
     */
    public static WifiUtils getInstance(Context context){
        if (instance == null){
            synchronized (WifiUtils.class){
                if (instance == null){
                    instance = new WifiUtils(context);
                }
            }
        }
        return instance;
    }

    /* 打开并搜索wifi
     */
    public List<ScanResult> openAndSearch() {
        if (!mWifiManager.isWifiEnabled()) {
            //开启wifi
            mWifiManager.setWifiEnabled(true);
            mWifiManager.startScan();
            return null;
        }else{
            return mWifiManager.getScanResults();
        }
    }

    /* 搜索wifi
     */
    public void search() {
        mWifiManager.startScan();
    }

    /* 关闭wifi
     */
    public void close() {
        if (mWifiManager.isWifiEnabled()) {
            //开启wifi
            mWifiManager.setWifiEnabled(false);
        }
    }


    public WifiManager getWifiManager(){
        return mWifiManager;
    }

    /**
     * 创建WifiConfiguration对象
     * 分为三种情况：1没有密码;2用wep加密;3用wpa加密
     *
     * @param SSID
     * @param Password
     * @param Type
     * @return
     */
    public WifiConfiguration CreateWifiInfo(String SSID, String Password,
                                            int Type) {
        WifiConfiguration config = new WifiConfiguration();
        config.allowedAuthAlgorithms.clear();
        config.allowedGroupCiphers.clear();
        config.allowedKeyManagement.clear();
        config.allowedPairwiseCiphers.clear();
        config.allowedProtocols.clear();
        config.SSID = "\"" + SSID + "\"";

//        WifiConfiguration tempConfig = this.IsExsits(SSID);
//        if (tempConfig != null) {
//            mWifiManager.removeNetwork(tempConfig.networkId);
//        }

        if (Type == 1) // WIFICIPHER_NOPASS
        {
            //config.wepKeys[0] = "";
            config.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE);
            //config.wepTxKeyIndex = 0;
        }
        if (Type == 2) // WIFICIPHER_WEP
        {
            config.hiddenSSID = true;
            config.wepKeys[0] = "\"" + Password + "\"";
            config.allowedAuthAlgorithms
                    .set(WifiConfiguration.AuthAlgorithm.SHARED);
            config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);
            config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);
            config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP40);
            config.allowedGroupCiphers
                    .set(WifiConfiguration.GroupCipher.WEP104);
            config.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE);
            config.wepTxKeyIndex = 0;
        }
        if (Type == 3) // WIFICIPHER_WPA
        {
            config.preSharedKey = "\"" + Password + "\"";
            config.hiddenSSID = true;
            config.allowedAuthAlgorithms
                    .set(WifiConfiguration.AuthAlgorithm.OPEN);
            config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);
            config.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.WPA_PSK);
            config.allowedPairwiseCiphers
                    .set(WifiConfiguration.PairwiseCipher.TKIP);
            // config.allowedProtocols.set(WifiConfiguration.Protocol.WPA);
            config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);
            config.allowedPairwiseCiphers
                    .set(WifiConfiguration.PairwiseCipher.CCMP);
            config.status = WifiConfiguration.Status.ENABLED;
        }
        return config;
    }

    public WifiConfiguration IsExsits(String SSID) {
        List<WifiConfiguration> existingConfigs = mWifiManager
                .getConfiguredNetworks();
        if(existingConfigs != null){
            for (WifiConfiguration existingConfig : existingConfigs) {
                if (existingConfig.SSID.equals("\"" + SSID + "\"")) {
                    return existingConfig;
                }
            }
        }
        return null;
    }

    public void connect(WifiConfiguration config) {
        int wcgID = mWifiManager.addNetwork(config);
        mWifiManager.enableNetwork(wcgID, true);
    }

    public void connected(WifiConfiguration config){
        Logger.json(JSON.toJSONString(config));
        Logger.e(config.networkId+"");
        mWifiManager.enableNetwork(config.networkId, true);
        mWifiManager.saveConfiguration();
    }

    //是否连接WIFI
    public static boolean isWifiConnected(Context context)
    {
        ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifiNetworkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if(wifiNetworkInfo.isConnected())
        {
            return true ;
        }

        return false ;
    }

    public static int getWifiType(String capabilities){
        int type = WIFICIPHER_WPA;
        if (!TextUtils.isEmpty(capabilities)) {
            if (capabilities.contains("WPA") || capabilities.contains("wpa")) {
                type = WIFICIPHER_WPA;
            } else if (capabilities.contains("WEP") || capabilities.contains("wep")) {
                type = WIFICIPHER_WEP;
            } else {
                type = WIFICIPHER_NOPASS;
            }
        }
        return type;
    }

    /**
     * 创建Wifi热点
     */
    public boolean createWifiHotspot(String SSID, String pwd) {
        if (mWifiManager.isWifiEnabled()) {
            //如果wifi处于打开状态，则关闭wifi,
            mWifiManager.setWifiEnabled(false);
        }
        WifiConfiguration config = new WifiConfiguration();
        config.SSID = SSID;
        config.preSharedKey = pwd;
        config.hiddenSSID = true;
        config.allowedAuthAlgorithms
                .set(WifiConfiguration.AuthAlgorithm.OPEN);//开放系统认证
        config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);
        config.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.WPA_PSK);
        config.allowedPairwiseCiphers
                .set(WifiConfiguration.PairwiseCipher.TKIP);
        config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);
        config.allowedPairwiseCiphers
                .set(WifiConfiguration.PairwiseCipher.CCMP);
        config.status = WifiConfiguration.Status.ENABLED;
        //通过反射调用设置热点
        boolean enable = false;
        try {
            Method method = mWifiManager.getClass().getMethod(
                    "setWifiApEnabled", WifiConfiguration.class, Boolean.TYPE);
            enable = (Boolean) method.invoke(mWifiManager, config, true);

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return enable;
    }

    public boolean getWiFiState(){
        return mWifiManager.isWifiEnabled();
    }

    /**
     * 关闭WiFi热点
     */
    public void closeWifiHotspot() {
        try {
            Method method = mWifiManager.getClass().getMethod("getWifiApConfiguration");
            method.setAccessible(true);
            WifiConfiguration config = (WifiConfiguration) method.invoke(mWifiManager);
            Method method2 = mWifiManager.getClass().getMethod("setWifiApEnabled", WifiConfiguration.class, boolean.class);
            method2.invoke(mWifiManager, config, false);
        } catch (NoSuchMethodException | IllegalArgumentException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**判断热点开启状态*/
    public boolean isWifiApEnabled() {
        return getWifiApState() == WIFI_AP_STATE.WIFI_AP_STATE_ENABLED;
    }

    private WIFI_AP_STATE getWifiApState(){
        int tmp;
        try {
            Method method = mWifiManager.getClass().getMethod("getWifiApState");
            tmp = ((Integer) method.invoke(mWifiManager));
            // Fix for Android 4
            if (tmp > 10) {
                tmp = tmp - 10;
            }
            return WIFI_AP_STATE.class.getEnumConstants()[tmp];
        } catch (Exception e) {
            e.printStackTrace();
            return WIFI_AP_STATE.WIFI_AP_STATE_FAILED;
        }
    }

    public enum WIFI_AP_STATE {
        WIFI_AP_STATE_DISABLING, WIFI_AP_STATE_DISABLED, WIFI_AP_STATE_ENABLING,  WIFI_AP_STATE_ENABLED, WIFI_AP_STATE_FAILED
    }


}
