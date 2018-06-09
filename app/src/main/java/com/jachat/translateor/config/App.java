package com.jachat.translateor.config;

import android.app.Application;

import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;

/**
 * Created by Perryn on 2017/7/28 0028.
 */
public class App extends Application {

    private static App mContext;
    public static String mSysLanguage = LngConstant.LANGUAGE_CHINESE;
    public static String mLngStr = "";

    @Override
    public void onCreate() {
        super.onCreate();

        mContext = this;

        Logger.init(ConfConstant.LOG_TAG)       // Log Tag
                .methodCount(1)                 // default 2
                .hideThreadInfo()               // default shown
                .logLevel(LogLevel.FULL)        // default LogLevel.FULL
                .methodOffset(0);               // default 0
    }

    public static App getContext() {
        return mContext;
    }

    public static String getSysText(int textType) {
        String str = "";
        switch (mSysLanguage) {
            case LngConstant.LANGUAGE_CHINESE://中文
                switch (textType) {
                    case Constant.TEXT_NOT_IMEI:
                        str = "NO IMEI";
                        break;
                    case Constant.LANGUAGE_TOAST:
                        str = "长按对应的母语或者外语键说话";
                        break;
                    case Constant.TEXT_CLEAN:
                        str = "清除";
                        break;
                    case Constant.TEXT_RETURN:
                        str = "返回";
                        break;
                    case Constant.TEXT_TYPE_OPEN_WIFI:
                        str = "打开WIFI";
                        break;
                    case Constant.TEXT_TYPE_UPDATING:
                        str = "正在更新...";
                        break;
                    case Constant.TEXT_TYPE_UPDATE_FAILED:
                        str = "更新失败:";
                        break;
                    case Constant.TEXT_TYPE_CHOOSE_WIFI:
                        str = "选择WIFI";
                        break;
                    case Constant.TEXT_TYPE_CLOSE_WIFI:
                        str = "关闭WIFI";
                        break;
                    case Constant.TEXT_TYPE_OPEN_HOTSPOT:
                        str = "打开热点";
                        break;
                    case Constant.TEXT_TYPE_CLOSE_HOTSPOT:
                        str = "关闭热点";
                        break;
                    case Constant.TEXT_TYPE_PWD_ERROR:
                        str = "密码错误!";
                        break;
                    case Constant.TEXT_TYPE_WIFI_CONN_SUCC:
                        str = "wifi连接成功";
                        break;
                    case Constant.TEXT_TYPE_INPUT_WIFI_PWD:
                        str = "请输入wifi密码";
                        break;
                    case Constant.TEXT_TYPE_NO_NETWORK:
                        str = "无网络";
                        break;
                    case Constant.TEXT_TYPE_FOREIGN_LANGUAGE:
                        str = "外语";
                        break;
                    case Constant.TEXT_TYPE_MOTHER_TONGUE:
                        str = "母语";
                        break;
                    case Constant.TEXT_TYPE_WIFI_SETTING:
                        str = "WIFI设置";
                        break;
                    case Constant.TEXT_TYPE_HOTSPOT:
                        str = "热点共享";
                        break;
                    case Constant.TEXT_TYPE_SCREEN_BRIGHT:
                        str = "屏幕亮度";
                        break;
                    case Constant.TEXT_TYPE_ABOUT_US:
                        str = "关于我们";
                        break;
                    case Constant.TEXT_TYPE_LNG_CHINESE:
                        str = "中文";
                        break;
                    case Constant.TEXT_TYPE_LNG_ENGLISH:
                        str = "英语";
                        break;
                    case Constant.TEXT_TYPE_LNG_ENGLISH_US:
                        str = "英语";
                        break;
                    case Constant.TEXT_TYPE_LNG_ENGLISH_IND:
                        str = "英语";
                        break;
                    case Constant.TEXT_TYPE_LNG_YUEYU:
                        str = "粤语";
                        break;
                    case Constant.TEXT_TYPE_LNG_JAPANESE:
                        str = "日语";
                        break;
                    case Constant.TEXT_TYPE_LNG_KOREAN:
                        str = "韩语";
                        break;
                    case Constant.TEXT_TYPE_LNG_THAI:
                        str = "泰语";
                        break;
                    case Constant.TEXT_TYPE_LNG_ARABIAN:
                        str = "阿拉伯语";
                        break;
                    case Constant.TEXT_TYPE_LNG_RUSSIAN:
                        str = "俄语";
                        break;
                    case Constant.TEXT_TYPE_LNG_SPANISH:
                        str = "西班牙语";
                        break;
                    case Constant.TEXT_TYPE_LNG_VIETNAMESE:
                        str = "越南语";
                        break;
                    case Constant.TEXT_TYPE_LNG_FRENCH:
                        str = "法语";
                        break;
                    case Constant.TEXT_TYPE_LNG_GERMAN:
                        str = "德语";
                        break;
                    case Constant.TEXT_TYPE_LNG_PORTUGAL:
                        str = "葡萄牙语";
                        break;
                    case Constant.TEXT_TYPE_LNG_ITALIAN:
                        str = "意大利语";
                        break;
                    case Constant.TEXT_TYPE_LNG_HOLLAND:
                        str = "荷兰语";
                        break;
                    case Constant.TEXT_TYPE_LNG_GREEK:
                        str = "希腊语";
                        break;
                    case Constant.TEXT_TYPE_LNG_WEI:
                        str = "维吾尔语";
                        break;
                    case Constant.TEXT_TYPE_LNG_INDONESIA:
                        str = "印尼语";
                        break;
                    case Constant.TEXT_TYPE_FRIEND:
                        str = "好友";
                        break;
                    case Constant.TEXT_TYPE_SETTING:
                        str = "设置";
                        break;
                    case Constant.TEXT_TYPE_SYSTEM_LNG:
                        str = "系统语言";
                        break;
                    case Constant.TEXT_TYPE_ABOUT_DEVICE:
                        str = "关于设备";
                        break;
                    case Constant.TEXT_TYPE_CHOOSE:
                        str = "选择";
                        break;
                    case Constant.TEXT_TYPE_SEARCHING:
                        str = "正在搜索...";
                        break;
                    case Constant.TEXT_TYPE_WIFI_CLOSE:
                        str = "WIFI关闭";
                        break;
                    case Constant.TEXT_TYPE_PWD_LENGTH_8:
                        str = "密码长度不能小于8位";
                        break;
                    case Constant.TEXT_TYPE_SEARCH_COMPLETE:
                        str = "搜索完成";
                        break;
                    case Constant.TEXT_TYPE_CONNECTED:
                        str = "已连接";
                        break;
                    case Constant.TEXT_TYPE_CONNECTING:
                        str = "连接中...";
                        break;
                    case Constant.TEXT_TYPE_MODIFY_IDENTIFY:
                        str = "正在验证身份信息...";
                        break;
                    case Constant.TEXT_TYPE_GET_IP_ADDRESS:
                        str = "正在获取IP地址...";
                        break;
                    case Constant.TEXT_TYPE_ENCIPHER:
                        str = "加密";
                        break;
                    case Constant.TEXT_TYPE_OPEN_NETWORK:
                        str = "开放网络";
                        break;
                    case Constant.TEXT_TYPE_INPUT_PWD:
                        str = "输入密码";
                        break;
                    case Constant.TEXT_TYPE_PASSWORD:
                        str = "密码";
                        break;
                    case Constant.TEXT_TYPE_WIFI_CONN:
                        str = "WIFI连接";
                        break;
                    case Constant.TEXT_TYPE_SELECT_NEAR_WLAN:
                        str = "选取附近的WLAN...";
                        break;
                    case Constant.TEXT_TYPE_MOTHER_TONGUE_CHOOSE:
                        str = "母语选择";
                        break;
                    case Constant.TEXT_TYPE_FORENGN_LNG_CHOOSE:
                        str = "外语选择";
                        break;
                    case Constant.TEXT_TYPE_SYSTEM_LNG_CHOOSE:
                        str = "系统语言选择";
                        break;

                }
                break;
            case LngConstant.LANGUAGE_ENGLISH:
            case LngConstant.LANGUAGE_ENGLISH_US:
            case LngConstant.LANGUAGE_ENGLISH_IND://英语
                switch (textType) {
                    case Constant.TEXT_NOT_IMEI:
                        str = "NO IMEI";
                        break;
                    case Constant.TEXT_TYPE_OPEN_WIFI:
                        str = "Open WIFI";
                        break;
                    case Constant.TEXT_CLEAN:
                        str = "Clean";
                        break;
                    case Constant.TEXT_RETURN:
                        str = "Back";
                        break;
                    case Constant.LANGUAGE_TOAST:
                        str = "Long press the Right Key to speak";
                        break;
                    case Constant.TEXT_TYPE_UPDATING:
                        str = "Updating...";
                        break;
                    case Constant.TEXT_TYPE_UPDATE_FAILED:
                        str = "Update failed:";
                        break;
                    case Constant.TEXT_TYPE_CHOOSE_WIFI:
                        str = "Select WIFI";
                        break;
                    case Constant.TEXT_TYPE_CLOSE_WIFI:
                        str = "Close WIFI";
                        break;
                    case Constant.TEXT_TYPE_OPEN_HOTSPOT:
                        str = "Open hotspot";
                        break;
                    case Constant.TEXT_TYPE_CLOSE_HOTSPOT:
                        str = "Close hotspot";
                        break;
                    case Constant.TEXT_TYPE_PWD_ERROR:
                        str = "Password error!";
                        break;
                    case Constant.TEXT_TYPE_WIFI_CONN_SUCC:
                        str = "WiFi connection";
                        break;
                    case Constant.TEXT_TYPE_INPUT_WIFI_PWD:
                        str = "Input WiFi passwd";
                        break;
                    case Constant.TEXT_TYPE_NO_NETWORK:
                        str = "No network";
                        break;
                    case Constant.TEXT_TYPE_FOREIGN_LANGUAGE:
                        str = "Foreign language";
                        break;
                    case Constant.TEXT_TYPE_MOTHER_TONGUE:
                        str = "Mother tongue";
                        break;
                    case Constant.TEXT_TYPE_WIFI_SETTING:
                        str = "WIFI settings";
                        break;
                    case Constant.TEXT_TYPE_HOTSPOT:
                        str = "Hotspot sharing";
                        break;
                    case Constant.TEXT_TYPE_SCREEN_BRIGHT:
                        str = "Screen brightness";
                        break;
                    case Constant.TEXT_TYPE_ABOUT_US:
                        str = "About us";
                        break;
                    case Constant.TEXT_TYPE_LNG_CHINESE:
                        str = "Chinese";
                        break;
                    case Constant.TEXT_TYPE_LNG_ENGLISH:
                        str = "English";
                        break;
                    case Constant.TEXT_TYPE_LNG_ENGLISH_US:
                        str = "English";
                        break;
                    case Constant.TEXT_TYPE_LNG_ENGLISH_IND:
                        str = "English";
                        break;
                    case Constant.TEXT_TYPE_LNG_YUEYU:
                        str = "Cantonese";
                        break;
                    case Constant.TEXT_TYPE_LNG_JAPANESE:
                        str = "Japanese";
                        break;
                    case Constant.TEXT_TYPE_LNG_KOREAN:
                        str = "Korean";
                        break;
                    case Constant.TEXT_TYPE_LNG_THAI:
                        str = "Thai";
                        break;
                    case Constant.TEXT_TYPE_LNG_ARABIAN:
                        str = "Arabic";
                        break;
                    case Constant.TEXT_TYPE_LNG_RUSSIAN:
                        str = "Russian";
                        break;
                    case Constant.TEXT_TYPE_LNG_SPANISH:
                        str = "Spanish";
                        break;
                    case Constant.TEXT_TYPE_LNG_VIETNAMESE:
                        str = "Vietnamese";
                        break;
                    case Constant.TEXT_TYPE_LNG_FRENCH:
                        str = "French";
                        break;
                    case Constant.TEXT_TYPE_LNG_GERMAN:
                        str = "German";
                        break;
                    case Constant.TEXT_TYPE_LNG_PORTUGAL:
                        str = "Portuguese";
                        break;
                    case Constant.TEXT_TYPE_LNG_ITALIAN:
                        str = "Italian";
                        break;
                    case Constant.TEXT_TYPE_LNG_HOLLAND:
                        str = "Dutch";
                        break;
                    case Constant.TEXT_TYPE_LNG_GREEK:
                        str = "Greek";
                        break;
                    case Constant.TEXT_TYPE_LNG_INDONESIA:
                        str = "Indonesian";
                        break;
                    case Constant.TEXT_TYPE_FRIEND:
                        str = "Friends";
                        break;
                    case Constant.TEXT_TYPE_SETTING:
                        str = "Settings";
                        break;
                    case Constant.TEXT_TYPE_SYSTEM_LNG:
                        str = "System language";
                        break;
                    case Constant.TEXT_TYPE_ABOUT_DEVICE:
                        str = "About equipment";
                        break;
                    case Constant.TEXT_TYPE_CHOOSE:
                        str = "choice";
                        break;
                    case Constant.TEXT_TYPE_SEARCHING:
                        str = "Searching...";
                        break;
                    case Constant.TEXT_TYPE_WIFI_CLOSE:
                        str = "WIFI closes";
                        break;
                    case Constant.TEXT_TYPE_PWD_LENGTH_8:
                        str = "The length of the password should not be less than 8";
                        break;
                    case Constant.TEXT_TYPE_SEARCH_COMPLETE:
                        str = "search complete";
                        break;
                    case Constant.TEXT_TYPE_CONNECTED:
                        str = "Connected";
                        break;
                    case Constant.TEXT_TYPE_CONNECTING:
                        str = "Connecting...";
                        break;
                    case Constant.TEXT_TYPE_MODIFY_IDENTIFY:
                        str = "Verifing authentication information...";
                        break;
                    case Constant.TEXT_TYPE_GET_IP_ADDRESS:
                        str = "Getting the IP address...";
                        break;
                    case Constant.TEXT_TYPE_ENCIPHER:
                        str = "Encryption";
                        break;
                    case Constant.TEXT_TYPE_OPEN_NETWORK:
                        str = "Open network";
                        break;
                    case Constant.TEXT_TYPE_INPUT_PWD:
                        str = "Input password";
                        break;
                    case Constant.TEXT_TYPE_PASSWORD:
                        str = "Password";
                        break;
                    case Constant.TEXT_TYPE_WIFI_CONN:
                        str = "WIFI connection";
                        break;
                    case Constant.TEXT_TYPE_SELECT_NEAR_WLAN:
                        str = "Select the nearby WLAN...";
                        break;
                    case Constant.TEXT_TYPE_MOTHER_TONGUE_CHOOSE:
                        str = "Native Language";
                        break;
                    case Constant.TEXT_TYPE_FORENGN_LNG_CHOOSE:
                        str = "Foreign language";
                        break;
                    case Constant.TEXT_TYPE_SYSTEM_LNG_CHOOSE:
                        str = "System language selection";
                        break;
                }
                break;
            case LngConstant.LANGUAGE_YUEYU:
                switch (textType) {
                    case Constant.TEXT_TYPE_OPEN_WIFI:
                        str = "打开WIFI";
                        break;
                    case Constant.TEXT_CLEAN:
                        str = "清除";
                        break;
                    case Constant.TEXT_RETURN:
                        str = "返回";
                        break;
                    case Constant.LANGUAGE_TOAST:
                        str = "长按右侧对应的翻译键说话";
                        break;
                    case Constant.TEXT_TYPE_UPDATING:
                        str = "正更新...";
                        break;
                    case Constant.TEXT_TYPE_UPDATE_FAILED:
                        str = "更新失败:";
                        break;
                    case Constant.TEXT_TYPE_CHOOSE_WIFI:
                        str = "拣WiFi";
                        break;
                    case Constant.TEXT_TYPE_CLOSE_WIFI:
                        str = "关闭WIFI";
                        break;
                    case Constant.TEXT_TYPE_OPEN_HOTSPOT:
                        str = "开放嘅热点";
                        break;
                    case Constant.TEXT_TYPE_CLOSE_HOTSPOT:
                        str = "关闭热点";
                        break;
                    case Constant.TEXT_TYPE_PWD_ERROR:
                        str = "密码错误!";
                        break;
                    case Constant.TEXT_TYPE_WIFI_CONN_SUCC:
                        str = "wifi链接成功";
                        break;
                    case Constant.TEXT_TYPE_INPUT_WIFI_PWD:
                        str = "请输入wifi密码";
                        break;
                    case Constant.TEXT_TYPE_NO_NETWORK:
                        str = "无网络";
                        break;
                    case Constant.TEXT_TYPE_FOREIGN_LANGUAGE:
                        str = "外语";
                        break;
                    case Constant.TEXT_TYPE_MOTHER_TONGUE:
                        str = "母语";
                        break;
                    case Constant.TEXT_TYPE_WIFI_SETTING:
                        str = "WIFI设置";
                        break;
                    case Constant.TEXT_TYPE_HOTSPOT:
                        str = "热点共亯";
                        break;
                    case Constant.TEXT_TYPE_SCREEN_BRIGHT:
                        str = "屏幕亮度";
                        break;
                    case Constant.TEXT_TYPE_ABOUT_US:
                        str = "关於我哋";
                        break;
                    case Constant.TEXT_TYPE_LNG_CHINESE:
                        str = "中文";
                        break;
                    case Constant.TEXT_TYPE_LNG_ENGLISH:
                        str = "英文";
                        break;
                    case Constant.TEXT_TYPE_LNG_ENGLISH_US:
                        str = "英文";
                        break;
                    case Constant.TEXT_TYPE_LNG_ENGLISH_IND:
                        str = "英文";
                        break;
                    case Constant.TEXT_TYPE_LNG_YUEYU:
                        str = "粤语";
                        break;
                    case Constant.TEXT_TYPE_LNG_JAPANESE:
                        str = "日文";
                        break;
                    case Constant.TEXT_TYPE_LNG_KOREAN:
                        str = "韩文";
                        break;
                    case Constant.TEXT_TYPE_LNG_THAI:
                        str = "泰文";
                        break;
                    case Constant.TEXT_TYPE_LNG_ARABIAN:
                        str = "阿剌伯话";
                        break;
                    case Constant.TEXT_TYPE_LNG_RUSSIAN:
                        str = "俄文";
                        break;
                    case Constant.TEXT_TYPE_LNG_SPANISH:
                        str = "西班牙文";
                        break;
                    case Constant.TEXT_TYPE_LNG_VIETNAMESE:
                        str = "越南话";
                        break;
                    case Constant.TEXT_TYPE_LNG_FRENCH:
                        str = "法文";
                        break;
                    case Constant.TEXT_TYPE_LNG_GERMAN:
                        str = "德文";
                        break;
                    case Constant.TEXT_TYPE_LNG_PORTUGAL:
                        str = "葡萄牙语";
                        break;
                    case Constant.TEXT_TYPE_LNG_ITALIAN:
                        str = "意大利话";
                        break;
                    case Constant.TEXT_TYPE_LNG_HOLLAND:
                        str = "荷兰话";
                        break;
                    case Constant.TEXT_TYPE_LNG_GREEK:
                        str = "希腊语";
                        break;
                    case Constant.TEXT_TYPE_LNG_INDONESIA:
                        str = "印尼语";
                        break;
                    case Constant.TEXT_TYPE_FRIEND:
                        str = "好友";
                        break;
                    case Constant.TEXT_TYPE_SETTING:
                        str = "设置";
                        break;
                    case Constant.TEXT_TYPE_SYSTEM_LNG:
                        str = "系统语言";
                        break;
                    case Constant.TEXT_TYPE_ABOUT_DEVICE:
                        str = "关於设备";
                        break;
                    case Constant.TEXT_TYPE_CHOOSE:
                        str = "拣";
                        break;
                    case Constant.TEXT_TYPE_SEARCHING:
                        str = "正搜寻...";
                        break;
                    case Constant.TEXT_TYPE_WIFI_CLOSE:
                        str = "WIFI关闭";
                        break;
                    case Constant.TEXT_TYPE_PWD_LENGTH_8:
                        str = "密码长度唔小于八位";
                        break;
                    case Constant.TEXT_TYPE_SEARCH_COMPLETE:
                        str = "搜寻完成";
                        break;
                    case Constant.TEXT_TYPE_CONNECTED:
                        str = "已经链接";
                        break;
                    case Constant.TEXT_TYPE_CONNECTING:
                        str = "链接中...";
                        break;
                    case Constant.TEXT_TYPE_MODIFY_IDENTIFY:
                        str = "正在验证身份信息...";
                        break;
                    case Constant.TEXT_TYPE_GET_IP_ADDRESS:
                        str = "正验证身份信息...";
                        break;
                    case Constant.TEXT_TYPE_ENCIPHER:
                        str = "加密";
                        break;
                    case Constant.TEXT_TYPE_OPEN_NETWORK:
                        str = "开放网络";
                        break;
                    case Constant.TEXT_TYPE_INPUT_PWD:
                        str = "输入密码";
                        break;
                    case Constant.TEXT_TYPE_PASSWORD:
                        str = "密码";
                        break;
                    case Constant.TEXT_TYPE_WIFI_CONN:
                        str = "WIFI链接";
                        break;
                    case Constant.TEXT_TYPE_SELECT_NEAR_WLAN:
                        str = "选取附近嘅WLAN...";
                        break;
                    case Constant.TEXT_TYPE_MOTHER_TONGUE_CHOOSE:
                        str = "母语拣";
                        break;
                    case Constant.TEXT_TYPE_FORENGN_LNG_CHOOSE:
                        str = "外语拣";
                        break;
                    case Constant.TEXT_TYPE_SYSTEM_LNG_CHOOSE:
                        str = "系统语言拣";
                        break;
                }
                break;
            case LngConstant.LANGUAGE_JAPENESE://日本
                switch (textType) {
                    case Constant.TEXT_NOT_IMEI:
                        str = "NO IMEI";
                        break;
                    case Constant.TEXT_TYPE_OPEN_WIFI:
                        str = "WIFIを開けて";
                        break;
                    case Constant.LANGUAGE_TOAST:
                        str = "Long press the Right Key to speak";
                        break;
                    case Constant.TEXT_CLEAN:
                        str = "除去する";
                        break;
                    case Constant.TEXT_RETURN:
                        str = "戻る";
                        break;
                    case Constant.TEXT_TYPE_UPDATING:
                        str = "更新中...";
                        break;
                    case Constant.TEXT_TYPE_UPDATE_FAILED:
                        str = "更新の更新:";
                        break;
                    case Constant.TEXT_TYPE_CHOOSE_WIFI:
                        str = "wifiの選択";
                        break;
                    case Constant.TEXT_TYPE_CLOSE_WIFI:
                        str = "閉鎖WIFI";
                        break;
                    case Constant.TEXT_TYPE_OPEN_HOTSPOT:
                        str = "ホットスポットを開く";
                        break;
                    case Constant.TEXT_TYPE_CLOSE_HOTSPOT:
                        str = "ホットスポットを閉鎖する";
                        break;
                    case Constant.TEXT_TYPE_PWD_ERROR:
                        str = "パスワードエラー!";
                        break;
                    case Constant.TEXT_TYPE_WIFI_CONN_SUCC:
                        str = "wifi接続成功";
                        break;
                    case Constant.TEXT_TYPE_INPUT_WIFI_PWD:
                        str = "wifiパスワードを入力してください";
                        break;
                    case Constant.TEXT_TYPE_NO_NETWORK:
                        str = "無ネットワーク";
                        break;
                    case Constant.TEXT_TYPE_FOREIGN_LANGUAGE:
                        str = "外国語";
                        break;
                    case Constant.TEXT_TYPE_MOTHER_TONGUE:
                        str = "母国語";
                        break;
                    case Constant.TEXT_TYPE_WIFI_SETTING:
                        str = "WIFI設置";
                        break;
                    case Constant.TEXT_TYPE_HOTSPOT:
                        str = "ホットスポット共有";
                        break;
                    case Constant.TEXT_TYPE_SCREEN_BRIGHT:
                        str = "スクリーンの輝度";
                        break;
                    case Constant.TEXT_TYPE_ABOUT_US:
                        str = "私たちについて";
                        break;
                    case Constant.TEXT_TYPE_LNG_CHINESE:
                        str = "中国語";
                        break;
                    case Constant.TEXT_TYPE_LNG_ENGLISH:
                        str = "英語";
                        break;
                    case Constant.TEXT_TYPE_LNG_ENGLISH_US:
                        str = "英語";
                        break;
                    case Constant.TEXT_TYPE_LNG_ENGLISH_IND:
                        str = "英語";
                        break;
                    case Constant.TEXT_TYPE_LNG_YUEYU:
                        str = "広東語";
                        break;
                    case Constant.TEXT_TYPE_LNG_JAPANESE:
                        str = "日本語";
                        break;
                    case Constant.TEXT_TYPE_LNG_KOREAN:
                        str = "韓国語";
                        break;
                    case Constant.TEXT_TYPE_LNG_THAI:
                        str = "タイ語";
                        break;
                    case Constant.TEXT_TYPE_LNG_ARABIAN:
                        str = "アラビア語";
                        break;
                    case Constant.TEXT_TYPE_LNG_RUSSIAN:
                        str = "ロシア語";
                        break;
                    case Constant.TEXT_TYPE_LNG_SPANISH:
                        str = "スペイン語";
                        break;
                    case Constant.TEXT_TYPE_LNG_VIETNAMESE:
                        str = "ベトナム語";
                        break;
                    case Constant.TEXT_TYPE_LNG_FRENCH:
                        str = "フランス語";
                        break;
                    case Constant.TEXT_TYPE_LNG_GERMAN:
                        str = "ドイツ語";
                        break;
                    case Constant.TEXT_TYPE_LNG_PORTUGAL:
                        str = "ポルトガル語";
                        break;
                    case Constant.TEXT_TYPE_LNG_ITALIAN:
                        str = "イタリア語";
                        break;
                    case Constant.TEXT_TYPE_LNG_HOLLAND:
                        str = "オランダ語";
                        break;
                    case Constant.TEXT_TYPE_LNG_GREEK:
                        str = "ギリシャ語";
                        break;
                    case Constant.TEXT_TYPE_LNG_INDONESIA:
                        str = "インドネシア語";
                        break;
                    case Constant.TEXT_TYPE_FRIEND:
                        str = "親友";
                        break;
                    case Constant.TEXT_TYPE_SETTING:
                        str = "設置";
                        break;
                    case Constant.TEXT_TYPE_SYSTEM_LNG:
                        str = "システム言語";
                        break;
                    case Constant.TEXT_TYPE_ABOUT_DEVICE:
                        str = "設備について";
                        break;
                    case Constant.TEXT_TYPE_CHOOSE:
                        str = "選択";
                        break;
                    case Constant.TEXT_TYPE_SEARCHING:
                        str = "捜索中です...";
                        break;
                    case Constant.TEXT_TYPE_WIFI_CLOSE:
                        str = "WIFI閉鎖";
                        break;
                    case Constant.TEXT_TYPE_PWD_LENGTH_8:
                        str = "パスワードの長さは8位以下ではありません";
                        break;
                    case Constant.TEXT_TYPE_SEARCH_COMPLETE:
                        str = "検索完了";
                        break;
                    case Constant.TEXT_TYPE_CONNECTED:
                        str = "接続済み";
                        break;
                    case Constant.TEXT_TYPE_CONNECTING:
                        str = "接続中...";
                        break;
                    case Constant.TEXT_TYPE_MODIFY_IDENTIFY:
                        str = "身分情報を検証している...";
                        break;
                    case Constant.TEXT_TYPE_GET_IP_ADDRESS:
                        str = "IPアドレスを取得しています...";
                        break;
                    case Constant.TEXT_TYPE_ENCIPHER:
                        str = "暗号化";
                        break;
                    case Constant.TEXT_TYPE_OPEN_NETWORK:
                        str = "オープンネットワーク";
                        break;
                    case Constant.TEXT_TYPE_INPUT_PWD:
                        str = "パスワードを入力する";
                        break;
                    case Constant.TEXT_TYPE_PASSWORD:
                        str = "パスワード";
                        break;
                    case Constant.TEXT_TYPE_WIFI_CONN:
                        str = "WIFI接続";
                        break;
                    case Constant.TEXT_TYPE_SELECT_NEAR_WLAN:
                        str = "近くのWLAN選択...";
                        break;
                    case Constant.TEXT_TYPE_MOTHER_TONGUE_CHOOSE:
                        str = "母国語の選択";
                        break;
                    case Constant.TEXT_TYPE_FORENGN_LNG_CHOOSE:
                        str = "外国語の選択";
                        break;
                    case Constant.TEXT_TYPE_SYSTEM_LNG_CHOOSE:
                        str = "システム言語選択";
                        break;
                }

                break;
            case LngConstant.LANGUAGE_KOREAN://韩语
                switch (textType) {
                    case Constant.TEXT_TYPE_OPEN_WIFI:
                        str = "WIFI 열기";
                        break;
                    case Constant.TEXT_NOT_IMEI:
                        str = "NO IMEI";
                        break;
                    case Constant.LANGUAGE_TOAST:
                        str = "Long press the Right Key to speak";
                        break;
                    case Constant.TEXT_CLEAN:
                        str = "없애다";
                        break;
                    case Constant.TEXT_RETURN:
                        str = "되돌아가다";
                        break;
                    case Constant.TEXT_TYPE_UPDATING:
                        str = "업데이트 중...";
                        break;
                    case Constant.TEXT_TYPE_UPDATE_FAILED:
                        str = "업데이트 실패:";
                        break;
                    case Constant.TEXT_TYPE_CHOOSE_WIFI:
                        str = "선택 무선";
                        break;
                    case Constant.TEXT_TYPE_CLOSE_WIFI:
                        str = "WIFI 닫기";
                        break;
                    case Constant.TEXT_TYPE_OPEN_HOTSPOT:
                        str = "뜨거운 열기";
                        break;
                    case Constant.TEXT_TYPE_CLOSE_HOTSPOT:
                        str = "닫기 이슈";
                        break;
                    case Constant.TEXT_TYPE_PWD_ERROR:
                        str = "암호가 잘못되었습니다!";
                        break;
                    case Constant.TEXT_TYPE_WIFI_CONN_SUCC:
                        str = "wifi 연결 성공";
                        break;
                    case Constant.TEXT_TYPE_INPUT_WIFI_PWD:
                        str = "작동 거리에서 암호를 입력하십시오";
                        break;
                    case Constant.TEXT_TYPE_NO_NETWORK:
                        str = "네트워크없음";
                        break;
                    case Constant.TEXT_TYPE_FOREIGN_LANGUAGE:
                        str = "외국어";
                        break;
                    case Constant.TEXT_TYPE_MOTHER_TONGUE:
                        str = "모국어";
                        break;
                    case Constant.TEXT_TYPE_WIFI_SETTING:
                        str = "WIFI 설정";
                        break;
                    case Constant.TEXT_TYPE_HOTSPOT:
                        str = "이슈 공유";
                        break;
                    case Constant.TEXT_TYPE_SCREEN_BRIGHT:
                        str = "화면 밝기";
                        break;
                    case Constant.TEXT_TYPE_ABOUT_US:
                        str = "우리";
                        break;
                    case Constant.TEXT_TYPE_LNG_CHINESE:
                        str = "중문";
                        break;
                    case Constant.TEXT_TYPE_LNG_ENGLISH:
                        str = "영어";
                        break;
                    case Constant.TEXT_TYPE_LNG_ENGLISH_US:
                        str = "영어";
                        break;
                    case Constant.TEXT_TYPE_LNG_ENGLISH_IND:
                        str = "영어";
                        break;
                    case Constant.TEXT_TYPE_LNG_YUEYU:
                        str = "월어";
                        break;
                    case Constant.TEXT_TYPE_LNG_JAPANESE:
                        str = "일본어";
                        break;
                    case Constant.TEXT_TYPE_LNG_KOREAN:
                        str = "한국어";
                        break;
                    case Constant.TEXT_TYPE_LNG_THAI:
                        str = "타이어";
                        break;
                    case Constant.TEXT_TYPE_LNG_ARABIAN:
                        str = "아랍어";
                        break;
                    case Constant.TEXT_TYPE_LNG_RUSSIAN:
                        str = "러시아어";
                        break;
                    case Constant.TEXT_TYPE_LNG_SPANISH:
                        str = "러시아어";
                        break;
                    case Constant.TEXT_TYPE_LNG_VIETNAMESE:
                        str = "월남";
                        break;
                    case Constant.TEXT_TYPE_LNG_FRENCH:
                        str = "프랑스어";
                        break;
                    case Constant.TEXT_TYPE_LNG_GERMAN:
                        str = "독일어";
                        break;
                    case Constant.TEXT_TYPE_LNG_PORTUGAL:
                        str = "포르투갈";
                        break;
                    case Constant.TEXT_TYPE_LNG_ITALIAN:
                        str = "이탈리아어";
                        break;
                    case Constant.TEXT_TYPE_LNG_HOLLAND:
                        str = "네덜란드어";
                        break;
                    case Constant.TEXT_TYPE_LNG_GREEK:
                        str = "그리스어";
                        break;
                    case Constant.TEXT_TYPE_LNG_INDONESIA:
                        str = "인도네시아 인";
                        break;
                    case Constant.TEXT_TYPE_FRIEND:
                        str = "친구";
                        break;
                    case Constant.TEXT_TYPE_SETTING:
                        str = "설치";
                        break;
                    case Constant.TEXT_TYPE_SYSTEM_LNG:
                        str = "시스템 언어";
                        break;
                    case Constant.TEXT_TYPE_ABOUT_DEVICE:
                        str = "장치 정보";
                        break;
                    case Constant.TEXT_TYPE_CHOOSE:
                        str = "선택";
                        break;
                    case Constant.TEXT_TYPE_SEARCHING:
                        str = "검색 중...";
                        break;
                    case Constant.TEXT_TYPE_WIFI_CLOSE:
                        str = "WIFI 닫기";
                        break;
                    case Constant.TEXT_TYPE_PWD_LENGTH_8:
                        str = "비밀번호 길이 못 < 8 비트";
                        break;
                    case Constant.TEXT_TYPE_SEARCH_COMPLETE:
                        str = "검색 완료";
                        break;
                    case Constant.TEXT_TYPE_CONNECTED:
                        str = "연결됨";
                        break;
                    case Constant.TEXT_TYPE_CONNECTING:
                        str = "연결 중...";
                        break;
                    case Constant.TEXT_TYPE_MODIFY_IDENTIFY:
                        str = "지금 신분 인증 정보를...";
                        break;
                    case Constant.TEXT_TYPE_GET_IP_ADDRESS:
                        str = "IP 주소 가져오는 중...";
                        break;
                    case Constant.TEXT_TYPE_ENCIPHER:
                        str = "암호화";
                        break;
                    case Constant.TEXT_TYPE_OPEN_NETWORK:
                        str = "개방 네트워크";
                        break;
                    case Constant.TEXT_TYPE_INPUT_PWD:
                        str = "비밀번호 입력";
                        break;
                    case Constant.TEXT_TYPE_PASSWORD:
                        str = "비밀번호";
                        break;
                    case Constant.TEXT_TYPE_WIFI_CONN:
                        str = "WIFI 연결";
                        break;
                    case Constant.TEXT_TYPE_SELECT_NEAR_WLAN:
                        str = "근처 WLAN 선택...";
                        break;
                    case Constant.TEXT_TYPE_MOTHER_TONGUE_CHOOSE:
                        str = "모국어 선택";
                        break;
                    case Constant.TEXT_TYPE_FORENGN_LNG_CHOOSE:
                        str = "외국어 선택";
                        break;
                    case Constant.TEXT_TYPE_SYSTEM_LNG_CHOOSE:
                        str = "시스템 언어 선택";
                        break;
                }
                break;
            case LngConstant.LANGUAGE_THAI://泰语
                switch (textType) {
                    case Constant.TEXT_TYPE_OPEN_WIFI:
                        str = "เปิด WiFi";
                        break;
                    case Constant.TEXT_NOT_IMEI:
                        str = "NO IMEI";
                        break;
                    case Constant.LANGUAGE_TOAST:
                        str = "Long press the Right Key to speak";
                        break;
                    case Constant.TEXT_CLEAN:
                        str = "ล้าง";
                        break;
                    case Constant.TEXT_RETURN:
                        str = "กลับไปที่";
                        break;
                    case Constant.TEXT_TYPE_UPDATING:
                        str = "กำลังปรับปรุง...";
                        break;
                    case Constant.TEXT_TYPE_UPDATE_FAILED:
                        str = "การปรับปรุงล้มเหลว:";
                        break;
                    case Constant.TEXT_TYPE_CHOOSE_WIFI:
                        str = "เลือก WiFi";
                        break;
                    case Constant.TEXT_TYPE_CLOSE_WIFI:
                        str = "ปิด WiFi";
                        break;
                    case Constant.TEXT_TYPE_OPEN_HOTSPOT:
                        str = "เปิดฮอตสปอต";
                        break;
                    case Constant.TEXT_TYPE_CLOSE_HOTSPOT:
                        str = "ปปิดร้อน";
                        break;
                    case Constant.TEXT_TYPE_PWD_ERROR:
                        str = "รหัสผ่านไม่ถูกต้อง!";
                        break;
                    case Constant.TEXT_TYPE_WIFI_CONN_SUCC:
                        str = "WiFi เชื่อมต่อสําเร็จ";
                        break;
                    case Constant.TEXT_TYPE_INPUT_WIFI_PWD:
                        str = "กรุณาป้อนรหัสผ่าน WiFi";
                        break;
                    case Constant.TEXT_TYPE_NO_NETWORK:
                        str = "ไม่มีเครือข่าย";
                        break;
                    case Constant.TEXT_TYPE_FOREIGN_LANGUAGE:
                        str = "ภาษาต่างประเทศ";
                        break;
                    case Constant.TEXT_TYPE_MOTHER_TONGUE:
                        str = "ภาษาแม่";
                        break;
                    case Constant.TEXT_TYPE_WIFI_SETTING:
                        str = "การตั้งค่า WiFi";
                        break;
                    case Constant.TEXT_TYPE_HOTSPOT:
                        str = "จุดที่ใช้ร่วมกัน";
                        break;
                    case Constant.TEXT_TYPE_SCREEN_BRIGHT:
                        str = "ความสว่างของหน้าจอ";
                        break;
                    case Constant.TEXT_TYPE_ABOUT_US:
                        str = "เกี่ยวกับเรื่องที่เรา";
                        break;
                    case Constant.TEXT_TYPE_LNG_CHINESE:
                        str = "ภาษาจีน";
                        break;
                    case Constant.TEXT_TYPE_LNG_ENGLISH:
                        str = "ภาษาอังกฤษ";
                        break;
                    case Constant.TEXT_TYPE_LNG_ENGLISH_US:
                        str = "ภาษาอังกฤษ";
                        break;
                    case Constant.TEXT_TYPE_LNG_ENGLISH_IND:
                        str = "ภาษาอังกฤษ";
                        break;
                    case Constant.TEXT_TYPE_LNG_YUEYU:
                        str = "จีนกวางตุ้ง";
                        break;
                    case Constant.TEXT_TYPE_LNG_JAPANESE:
                        str = "ภาษาญี่ปุ่น";
                        break;
                    case Constant.TEXT_TYPE_LNG_KOREAN:
                        str = "ภาษาเกาหลี";
                        break;
                    case Constant.TEXT_TYPE_LNG_THAI:
                        str = "ภาษาไทย";
                        break;
                    case Constant.TEXT_TYPE_LNG_ARABIAN:
                        str = "ภาษาอาหรับ";
                        break;
                    case Constant.TEXT_TYPE_LNG_RUSSIAN:
                        str = "ภาษารัสเซีย";
                        break;
                    case Constant.TEXT_TYPE_LNG_SPANISH:
                        str = "ภาษาสเปน";
                        break;
                    case Constant.TEXT_TYPE_LNG_VIETNAMESE:
                        str = "ภาษาเวียดนาม";
                        break;
                    case Constant.TEXT_TYPE_LNG_FRENCH:
                        str = "ภาษาฝรั่งเศส";
                        break;
                    case Constant.TEXT_TYPE_LNG_GERMAN:
                        str = "ภาษาเยอรมัน";
                        break;
                    case Constant.TEXT_TYPE_LNG_PORTUGAL:
                        str = "ภาษาโปรตุเกส";
                        break;
                    case Constant.TEXT_TYPE_LNG_ITALIAN:
                        str = "ภาษาโปรตุเกส";
                        break;
                    case Constant.TEXT_TYPE_LNG_HOLLAND:
                        str = "ดัตช์";
                        break;
                    case Constant.TEXT_TYPE_LNG_GREEK:
                        str = "กรีก";
                        break;
                    case Constant.TEXT_TYPE_LNG_INDONESIA:
                        str = "ชาวอินโดนีเซีย";
                        break;
                    case Constant.TEXT_TYPE_FRIEND:
                        str = "เพื่อนของ";
                        break;
                    case Constant.TEXT_TYPE_SETTING:
                        str = "การตั้งค่า";
                        break;
                    case Constant.TEXT_TYPE_SYSTEM_LNG:
                        str = "ภาษาของระบบ";
                        break;
                    case Constant.TEXT_TYPE_ABOUT_DEVICE:
                        str = "เกี่ยวกับอุปกรณ์";
                        break;
                    case Constant.TEXT_TYPE_CHOOSE:
                        str = "การเลือก";
                        break;
                    case Constant.TEXT_TYPE_SEARCHING:
                        str = "กำลังค้นหา...";
                        break;
                    case Constant.TEXT_TYPE_WIFI_CLOSE:
                        str = "ปิด WiFi";
                        break;
                    case Constant.TEXT_TYPE_PWD_LENGTH_8:
                        str = "รหัสผ่านความยาวไม่ต่ำกว่า 8 บิต";
                        break;
                    case Constant.TEXT_TYPE_SEARCH_COMPLETE:
                        str = "การค้นหาเสร็จสมบูรณ์";
                        break;
                    case Constant.TEXT_TYPE_CONNECTED:
                        str = "ที่เชื่อมต่อ";
                        break;
                    case Constant.TEXT_TYPE_CONNECTING:
                        str = "ในการเชื่อมต่อ...";
                        break;
                    case Constant.TEXT_TYPE_MODIFY_IDENTIFY:
                        str = "การตรวจสอบข้อมูลประจำตัว...";
                        break;
                    case Constant.TEXT_TYPE_GET_IP_ADDRESS:
                        str = "จะได้รับที่อยู่ IP...";
                        break;
                    case Constant.TEXT_TYPE_ENCIPHER:
                        str = "การเข้ารหัส";
                        break;
                    case Constant.TEXT_TYPE_OPEN_NETWORK:
                        str = "เครือข่ายเปิด";
                        break;
                    case Constant.TEXT_TYPE_INPUT_PWD:
                        str = "ป้อนรหัสผ่าน";
                        break;
                    case Constant.TEXT_TYPE_PASSWORD:
                        str = "รหัสผ่าน";
                        break;
                    case Constant.TEXT_TYPE_WIFI_CONN:
                        str = "การเชื่อมต่อ WiFi";
                        break;
                    case Constant.TEXT_TYPE_SELECT_NEAR_WLAN:
                        str = "เลือกที่ใกล้เคียง WLAN...";
                        break;
                    case Constant.TEXT_TYPE_MOTHER_TONGUE_CHOOSE:
                        str = "การเลือกภาษา";
                        break;
                    case Constant.TEXT_TYPE_FORENGN_LNG_CHOOSE:
                        str = "การเลือกภาษา";
                        break;
                    case Constant.TEXT_TYPE_SYSTEM_LNG_CHOOSE:
                        str = "เลือกภาษาของระบบ";
                        break;
                }
                break;
            case LngConstant.LANGUAGE_ARABIAN://阿拉伯
                switch (textType) {
                    case Constant.TEXT_TYPE_OPEN_WIFI:
                        str = "فتح  واي فاي";
                        break;
                    case Constant.TEXT_NOT_IMEI:
                        str = "NO IMEI";
                        break;
                    case Constant.LANGUAGE_TOAST:
                        str = "Long press the Right Key to speak";
                        break;
                    case Constant.TEXT_CLEAN:
                        str = "إزالة";
                        break;
                    case Constant.TEXT_RETURN:
                        str = "العودة";
                        break;
                    case Constant.TEXT_TYPE_UPDATING:
                        str = "تحديث...";
                        break;
                    case Constant.TEXT_TYPE_UPDATE_FAILED:
                        str = "فشل التحديث:";
                        break;
                    case Constant.TEXT_TYPE_CHOOSE_WIFI:
                        str = "اختيار واي فاي";
                        break;
                    case Constant.TEXT_TYPE_CLOSE_WIFI:
                        str = "إيقاف  واي فاي";
                        break;
                    case Constant.TEXT_TYPE_OPEN_HOTSPOT:
                        str = "فتح  الساخنة";
                        break;
                    case Constant.TEXT_TYPE_CLOSE_HOTSPOT:
                        str = "إغلاق  الساخنة";
                        break;
                    case Constant.TEXT_TYPE_PWD_ERROR:
                        str = "كلمة السر خاطئة!";
                        break;
                    case Constant.TEXT_TYPE_WIFI_CONN_SUCC:
                        str = "واي فاي   اتصال ناجح";
                        break;
                    case Constant.TEXT_TYPE_INPUT_WIFI_PWD:
                        str = "الرجاء إدخال   كلمة السر   واي فاي";
                        break;
                    case Constant.TEXT_TYPE_NO_NETWORK:
                        str = "بدون   شبكة";
                        break;
                    case Constant.TEXT_TYPE_FOREIGN_LANGUAGE:
                        str = "اللغات الأجنبية";
                        break;
                    case Constant.TEXT_TYPE_MOTHER_TONGUE:
                        str = "الأم";
                        break;
                    case Constant.TEXT_TYPE_WIFI_SETTING:
                        str = "إعدادات الواي فاي";
                        break;
                    case Constant.TEXT_TYPE_HOTSPOT:
                        str = "تقاسم  الساخنة";
                        break;
                    case Constant.TEXT_TYPE_SCREEN_BRIGHT:
                        str = "سطوع الشاشة";
                        break;
                    case Constant.TEXT_TYPE_ABOUT_US:
                        str = "حول بنا";
                        break;
                    case Constant.TEXT_TYPE_LNG_CHINESE:
                        str = "الصينية";
                        break;
                    case Constant.TEXT_TYPE_LNG_ENGLISH:
                        str = "اللغة الإنجليزية";
                        break;
                    case Constant.TEXT_TYPE_LNG_ENGLISH_US:
                        str = "اللغة الإنجليزية";
                        break;
                    case Constant.TEXT_TYPE_LNG_ENGLISH_IND:
                        str = "اللغة الإنجليزية";
                        break;
                    case Constant.TEXT_TYPE_LNG_YUEYU:
                        str = "الكانتونية";
                        break;
                    case Constant.TEXT_TYPE_LNG_JAPANESE:
                        str = "اليابانية";
                        break;
                    case Constant.TEXT_TYPE_LNG_KOREAN:
                        str = "الكورية";
                        break;
                    case Constant.TEXT_TYPE_LNG_THAI:
                        str = "التايلاندية";
                        break;
                    case Constant.TEXT_TYPE_LNG_ARABIAN:
                        str = "عربي";
                        break;
                    case Constant.TEXT_TYPE_LNG_RUSSIAN:
                        str = "الروسية";
                        break;
                    case Constant.TEXT_TYPE_LNG_SPANISH:
                        str = "الإسبانية";
                        break;
                    case Constant.TEXT_TYPE_LNG_VIETNAMESE:
                        str = "الفيتنامية";
                        break;
                    case Constant.TEXT_TYPE_LNG_FRENCH:
                        str = "الفرنسية";
                        break;
                    case Constant.TEXT_TYPE_LNG_GERMAN:
                        str = "الألمانية";
                        break;
                    case Constant.TEXT_TYPE_LNG_PORTUGAL:
                        str = "البرتغالية";
                        break;
                    case Constant.TEXT_TYPE_LNG_ITALIAN:
                        str = "الإيطالية";
                        break;
                    case Constant.TEXT_TYPE_LNG_HOLLAND:
                        str = "الهولندية";
                        break;
                    case Constant.TEXT_TYPE_LNG_GREEK:
                        str = "اليونانية";
                        break;
                    case Constant.TEXT_TYPE_LNG_INDONESIA:
                        str = "الأندونيسية";
                        break;
                    case Constant.TEXT_TYPE_FRIEND:
                        str = "صديق";
                        break;
                    case Constant.TEXT_TYPE_SETTING:
                        str = "إعدادات";
                        break;
                    case Constant.TEXT_TYPE_SYSTEM_LNG:
                        str = "نظام اللغة";
                        break;
                    case Constant.TEXT_TYPE_ABOUT_DEVICE:
                        str = "عن   الجهاز";
                        break;
                    case Constant.TEXT_TYPE_CHOOSE:
                        str = "اختيار";
                        break;
                    case Constant.TEXT_TYPE_SEARCHING:
                        str = "هو البحث عن...";
                        break;
                    case Constant.TEXT_TYPE_WIFI_CLOSE:
                        str = "إيقاف   واي فاي";
                        break;
                    case Constant.TEXT_TYPE_PWD_LENGTH_8:
                        str = "طول كلمة المرور   لا تقل عن   8 بت";
                        break;
                    case Constant.TEXT_TYPE_SEARCH_COMPLETE:
                        str = "الانتهاء من البحث";
                        break;
                    case Constant.TEXT_TYPE_CONNECTED:
                        str = "متصل";
                        break;
                    case Constant.TEXT_TYPE_CONNECTING:
                        str = "في   اتصال...";
                        break;
                    case Constant.TEXT_TYPE_MODIFY_IDENTIFY:
                        str = "التحقق من   الهوية...";
                        break;
                    case Constant.TEXT_TYPE_GET_IP_ADDRESS:
                        str = "الحصول على   عنوان بروتوكول الإنترنت...";
                        break;
                    case Constant.TEXT_TYPE_ENCIPHER:
                        str = "التشفير";
                        break;
                    case Constant.TEXT_TYPE_OPEN_NETWORK:
                        str = "شبكة مفتوحة";
                        break;
                    case Constant.TEXT_TYPE_INPUT_PWD:
                        str = "أدخل كلمة السر";
                        break;
                    case Constant.TEXT_TYPE_PASSWORD:
                        str = "كلمة السر";
                        break;
                    case Constant.TEXT_TYPE_WIFI_CONN:
                        str = "واي فاي   اتصال";
                        break;
                    case Constant.TEXT_TYPE_SELECT_NEAR_WLAN:
                        str = "اختيار   الشبكات اللاسلكية   القريبة...";
                        break;
                    case Constant.TEXT_TYPE_MOTHER_TONGUE_CHOOSE:
                        str = "اختيار   اللغة";
                        break;
                    case Constant.TEXT_TYPE_FORENGN_LNG_CHOOSE:
                        str = "اختيار   اللغة";
                        break;
                    case Constant.TEXT_TYPE_SYSTEM_LNG_CHOOSE:
                        str = "نظام   اختيار اللغة";
                        break;
                }
                break;
            case LngConstant.LANGUAGE_RUSSIAN://俄语
                switch (textType) {
                    case Constant.TEXT_TYPE_OPEN_WIFI:
                        str = "открыть  WiFi";
                        break;
                    case Constant.TEXT_NOT_IMEI:
                        str = "NO IMEI";
                        break;
                    case Constant.LANGUAGE_TOAST:
                        str = "Long press the Right Key to speak";
                        break;
                    case Constant.TEXT_CLEAN:
                        str = "Очистить";
                        break;
                    case Constant.TEXT_RETURN:
                        str = "возвращение ";
                        break;
                    case Constant.TEXT_TYPE_UPDATING:
                        str = "обновление...";
                        break;
                    case Constant.TEXT_TYPE_UPDATE_FAILED:
                        str = "Не удалось обновить:";
                        break;
                    case Constant.TEXT_TYPE_CHOOSE_WIFI:
                        str = "выберите WiFi";
                        break;
                    case Constant.TEXT_TYPE_CLOSE_WIFI:
                        str = "закрыть  WiFi";
                        break;
                    case Constant.TEXT_TYPE_OPEN_HOTSPOT:
                        str = "Открыть  в горячих точках";
                        break;
                    case Constant.TEXT_TYPE_CLOSE_HOTSPOT:
                        str = "закрыть  в горячих точках";
                        break;
                    case Constant.TEXT_TYPE_PWD_ERROR:
                        str = "Неверный пароль!";
                        break;
                    case Constant.TEXT_TYPE_WIFI_CONN_SUCC:
                        str = "Успешное соединение  WiFi";
                        break;
                    case Constant.TEXT_TYPE_INPUT_WIFI_PWD:
                        str = "Пожалуйста, введите  пароль  WiFi";
                        break;
                    case Constant.TEXT_TYPE_NO_NETWORK:
                        str = "не  в сети";
                        break;
                    case Constant.TEXT_TYPE_FOREIGN_LANGUAGE:
                        str = "иностранный язык";
                        break;
                    case Constant.TEXT_TYPE_MOTHER_TONGUE:
                        str = "родной язык";
                        break;
                    case Constant.TEXT_TYPE_WIFI_SETTING:
                        str = "WiFi  настройки";
                        break;
                    case Constant.TEXT_TYPE_HOTSPOT:
                        str = "в горячих точках  обмена";
                        break;
                    case Constant.TEXT_TYPE_SCREEN_BRIGHT:
                        str = "яркость экрана";
                        break;
                    case Constant.TEXT_TYPE_ABOUT_US:
                        str = "о нас";
                        break;
                    case Constant.TEXT_TYPE_LNG_CHINESE:
                        str = "китайский";
                        break;
                    case Constant.TEXT_TYPE_LNG_ENGLISH:
                        str = "английский";
                        break;
                    case Constant.TEXT_TYPE_LNG_ENGLISH_US:
                        str = "английский";
                        break;
                    case Constant.TEXT_TYPE_LNG_ENGLISH_IND:
                        str = "английский";
                        break;
                    case Constant.TEXT_TYPE_LNG_YUEYU:
                        str = "кантонский";
                        break;
                    case Constant.TEXT_TYPE_LNG_JAPANESE:
                        str = "японский";
                        break;
                    case Constant.TEXT_TYPE_LNG_KOREAN:
                        str = "корейский";
                        break;
                    case Constant.TEXT_TYPE_LNG_THAI:
                        str = "Тайский";
                        break;
                    case Constant.TEXT_TYPE_LNG_ARABIAN:
                        str = "арабский";
                        break;
                    case Constant.TEXT_TYPE_LNG_RUSSIAN:
                        str = "русский";
                        break;
                    case Constant.TEXT_TYPE_LNG_SPANISH:
                        str = "испанский";
                        break;
                    case Constant.TEXT_TYPE_LNG_VIETNAMESE:
                        str = "вьетнамский";
                        break;
                    case Constant.TEXT_TYPE_LNG_FRENCH:
                        str = "французский";
                        break;
                    case Constant.TEXT_TYPE_LNG_GERMAN:
                        str = "немецкий";
                        break;
                    case Constant.TEXT_TYPE_LNG_PORTUGAL:
                        str = "португальский";
                        break;
                    case Constant.TEXT_TYPE_LNG_ITALIAN:
                        str = "итальянский";
                        break;
                    case Constant.TEXT_TYPE_LNG_HOLLAND:
                        str = "голландский";
                        break;
                    case Constant.TEXT_TYPE_LNG_GREEK:
                        str = "греческий";
                        break;
                    case Constant.TEXT_TYPE_LNG_INDONESIA:
                        str = "индонезийский";
                        break;
                    case Constant.TEXT_TYPE_FRIEND:
                        str = "друзья";
                        break;
                    case Constant.TEXT_TYPE_SETTING:
                        str = "Настройки";
                        break;
                    case Constant.TEXT_TYPE_SYSTEM_LNG:
                        str = "система языка";
                        break;
                    case Constant.TEXT_TYPE_ABOUT_DEVICE:
                        str = "в отношении оборудования";
                        break;
                    case Constant.TEXT_TYPE_CHOOSE:
                        str = "выбор";
                        break;
                    case Constant.TEXT_TYPE_SEARCHING:
                        str = "поиск...";
                        break;
                    case Constant.TEXT_TYPE_WIFI_CLOSE:
                        str = "WiFi  закрыть";
                        break;
                    case Constant.TEXT_TYPE_PWD_LENGTH_8:
                        str = "длина пароля  не  менее  8 бит";
                        break;
                    case Constant.TEXT_TYPE_SEARCH_COMPLETE:
                        str = "Поиск завершён";
                        break;
                    case Constant.TEXT_TYPE_CONNECTED:
                        str = "Подключен";
                        break;
                    case Constant.TEXT_TYPE_CONNECTING:
                        str = "в  связи...";
                        break;
                    case Constant.TEXT_TYPE_MODIFY_IDENTIFY:
                        str = "проверка  личности  информации...";
                        break;
                    case Constant.TEXT_TYPE_GET_IP_ADDRESS:
                        str = "получение  IP - адрес...";
                        break;
                    case Constant.TEXT_TYPE_ENCIPHER:
                        str = "Шифрование";
                        break;
                    case Constant.TEXT_TYPE_OPEN_NETWORK:
                        str = "открыть сеть";
                        break;
                    case Constant.TEXT_TYPE_INPUT_PWD:
                        str = "Введите пароль";
                        break;
                    case Constant.TEXT_TYPE_PASSWORD:
                        str = "пароль";
                        break;
                    case Constant.TEXT_TYPE_WIFI_CONN:
                        str = "WiFi  соединения";
                        break;
                    case Constant.TEXT_TYPE_SELECT_NEAR_WLAN:
                        str = "вблизи  wlan  выбор...";
                        break;
                    case Constant.TEXT_TYPE_MOTHER_TONGUE_CHOOSE:
                        str = "выбор  родного языка";
                        break;
                    case Constant.TEXT_TYPE_FORENGN_LNG_CHOOSE:
                        str = "выбор  иностранных языков";
                        break;
                    case Constant.TEXT_TYPE_SYSTEM_LNG_CHOOSE:
                        str = "система  выбора языка";
                        break;
                }
                break;
            case LngConstant.LANGUAGE_SPANISH://西班牙语
                switch (textType) {
                    case Constant.TEXT_TYPE_OPEN_WIFI:
                        str = "WiFi abierta";
                        break;
                    case Constant.TEXT_NOT_IMEI:
                        str = "NO IMEI";
                        break;
                    case Constant.LANGUAGE_TOAST:
                        str = "Long press the Right Key to speak";
                        break;
                    case Constant.TEXT_CLEAN:
                        str = "La eliminación de";
                        break;
                    case Constant.TEXT_RETURN:
                        str = "Devuelve";
                        break;
                    case Constant.TEXT_TYPE_UPDATING:
                        str = "La actualización de...";
                        break;
                    case Constant.TEXT_TYPE_UPDATE_FAILED:
                        str = "Actualización fallida:";
                        break;
                    case Constant.TEXT_TYPE_CHOOSE_WIFI:
                        str = "Seleccione el WiFi";
                        break;
                    case Constant.TEXT_TYPE_CLOSE_WIFI:
                        str = "Cerca de WiFi";
                        break;
                    case Constant.TEXT_TYPE_OPEN_HOTSPOT:
                        str = "Abrir el foco";
                        break;
                    case Constant.TEXT_TYPE_CLOSE_HOTSPOT:
                        str = "Cierre caliente";
                        break;
                    case Constant.TEXT_TYPE_PWD_ERROR:
                        str = "Error de contraseña!";
                        break;
                    case Constant.TEXT_TYPE_WIFI_CONN_SUCC:
                        str = "El éxito de conexión wifi";
                        break;
                    case Constant.TEXT_TYPE_INPUT_WIFI_PWD:
                        str = "Introduzca la contraseña wifi";
                        break;
                    case Constant.TEXT_TYPE_NO_NETWORK:
                        str = "Sin red";
                        break;
                    case Constant.TEXT_TYPE_FOREIGN_LANGUAGE:
                        str = "Lenguas Extranjera";
                        break;
                    case Constant.TEXT_TYPE_MOTHER_TONGUE:
                        str = "La lengua materna";
                        break;
                    case Constant.TEXT_TYPE_WIFI_SETTING:
                        str = "Configuración de wifi";
                        break;
                    case Constant.TEXT_TYPE_HOTSPOT:
                        str = "El intercambio de calor";
                        break;
                    case Constant.TEXT_TYPE_SCREEN_BRIGHT:
                        str = "El brillo de la pantalla";
                        break;
                    case Constant.TEXT_TYPE_ABOUT_US:
                        str = "Acerca de nosotros";
                        break;
                    case Constant.TEXT_TYPE_LNG_CHINESE:
                        str = "El chino";
                        break;
                    case Constant.TEXT_TYPE_LNG_ENGLISH:
                        str = "El inglés";
                        break;
                    case Constant.TEXT_TYPE_LNG_ENGLISH_US:
                        str = "El inglés";
                        break;
                    case Constant.TEXT_TYPE_LNG_ENGLISH_IND:
                        str = "El inglés";
                        break;
                    case Constant.TEXT_TYPE_LNG_YUEYU:
                        str = "Cantonés";
                        break;
                    case Constant.TEXT_TYPE_LNG_JAPANESE:
                        str = "El japonés";
                        break;
                    case Constant.TEXT_TYPE_LNG_KOREAN:
                        str = "Coreano";
                        break;
                    case Constant.TEXT_TYPE_LNG_THAI:
                        str = "El tailandés";
                        break;
                    case Constant.TEXT_TYPE_LNG_ARABIAN:
                        str = "árabe";
                        break;
                    case Constant.TEXT_TYPE_LNG_RUSSIAN:
                        str = "El ruso";
                        break;
                    case Constant.TEXT_TYPE_LNG_SPANISH:
                        str = "El español";
                        break;
                    case Constant.TEXT_TYPE_LNG_VIETNAMESE:
                        str = "Los vietnami";
                        break;
                    case Constant.TEXT_TYPE_LNG_FRENCH:
                        str = "El francés";
                        break;
                    case Constant.TEXT_TYPE_LNG_GERMAN:
                        str = "El alemán";
                        break;
                    case Constant.TEXT_TYPE_LNG_PORTUGAL:
                        str = "El portugués";
                        break;
                    case Constant.TEXT_TYPE_LNG_ITALIAN:
                        str = "Italiano";
                        break;
                    case Constant.TEXT_TYPE_LNG_HOLLAND:
                        str = "El holandés";
                        break;
                    case Constant.TEXT_TYPE_LNG_GREEK:
                        str = "El griego";
                        break;
                    case Constant.TEXT_TYPE_LNG_INDONESIA:
                        str = "Indonesio";
                        break;
                    case Constant.TEXT_TYPE_FRIEND:
                        str = "Amigos";
                        break;
                    case Constant.TEXT_TYPE_SETTING:
                        str = "Siempre";
                        break;
                    case Constant.TEXT_TYPE_SYSTEM_LNG:
                        str = "El sistema de la lengua";
                        break;
                    case Constant.TEXT_TYPE_ABOUT_DEVICE:
                        str = "Sobre el equipo";
                        break;
                    case Constant.TEXT_TYPE_CHOOSE:
                        str = "La elección de";
                        break;
                    case Constant.TEXT_TYPE_SEARCHING:
                        str = "Es la búsqueda de...";
                        break;
                    case Constant.TEXT_TYPE_WIFI_CLOSE:
                        str = "Cierre de wifi";
                        break;
                    case Constant.TEXT_TYPE_PWD_LENGTH_8:
                        str = "La longitud de la contraseña no puede menos de 8 bits";
                        break;
                    case Constant.TEXT_TYPE_SEARCH_COMPLETE:
                        str = "La búsqueda completa";
                        break;
                    case Constant.TEXT_TYPE_CONNECTED:
                        str = "Ha conectado";
                        break;
                    case Constant.TEXT_TYPE_CONNECTING:
                        str = "En la conexión...";
                        break;
                    case Constant.TEXT_TYPE_MODIFY_IDENTIFY:
                        str = "Verificar que la información de identidad...";
                        break;
                    case Constant.TEXT_TYPE_GET_IP_ADDRESS:
                        str = "Están en proceso de obtener la dirección IP...";
                        break;
                    case Constant.TEXT_TYPE_ENCIPHER:
                        str = "El cifrado";
                        break;
                    case Constant.TEXT_TYPE_OPEN_NETWORK:
                        str = "Red abierta";
                        break;
                    case Constant.TEXT_TYPE_INPUT_PWD:
                        str = "Introduzca la contraseña";
                        break;
                    case Constant.TEXT_TYPE_PASSWORD:
                        str = "La contraseña";
                        break;
                    case Constant.TEXT_TYPE_WIFI_CONN:
                        str = "Conectividad wifi";
                        break;
                    case Constant.TEXT_TYPE_SELECT_NEAR_WLAN:
                        str = "La selección de cerca de WLAN...";
                        break;
                    case Constant.TEXT_TYPE_MOTHER_TONGUE_CHOOSE:
                        str = "La elección de la lengua materna";
                        break;
                    case Constant.TEXT_TYPE_FORENGN_LNG_CHOOSE:
                        str = "La elección de lenguas extranjeras";
                        break;
                    case Constant.TEXT_TYPE_SYSTEM_LNG_CHOOSE:
                        str = "El sistema de selección de idioma";
                        break;
                }
                break;
            case LngConstant.LANGUAGE_VIETNAMESE://越南语
                switch (textType) {
                    case Constant.TEXT_TYPE_OPEN_WIFI:
                        str = "Mở WIFI";
                        break;
                    case Constant.TEXT_NOT_IMEI:
                        str = "NO IMEI";
                        break;
                    case Constant.LANGUAGE_TOAST:
                        str = "Long press the Right Key to speak";
                        break;
                    case Constant.TEXT_CLEAN:
                        str = "Xóa";
                        break;
                    case Constant.TEXT_RETURN:
                        str = "Quay trở lại";
                        break;
                    case Constant.TEXT_TYPE_UPDATING:
                        str = "Đang cập nhật...";
                        break;
                    case Constant.TEXT_TYPE_UPDATE_FAILED:
                        str = "Cập nhật thất bại:";
                        break;
                    case Constant.TEXT_TYPE_CHOOSE_WIFI:
                        str = "Chọn WiFi";
                        break;
                    case Constant.TEXT_TYPE_CLOSE_WIFI:
                        str = "Đóng WiFi";
                        break;
                    case Constant.TEXT_TYPE_OPEN_HOTSPOT:
                        str = "Mở nóng";
                        break;
                    case Constant.TEXT_TYPE_CLOSE_HOTSPOT:
                        str = "Tắt nóng";
                        break;
                    case Constant.TEXT_TYPE_PWD_ERROR:
                        str = "Mật khẩu sai!";
                        break;
                    case Constant.TEXT_TYPE_WIFI_CONN_SUCC:
                        str = "WiFi kết nối thành công";
                        break;
                    case Constant.TEXT_TYPE_INPUT_WIFI_PWD:
                        str = "Hãy nhập mật khẩu wifi";
                        break;
                    case Constant.TEXT_TYPE_NO_NETWORK:
                        str = "Không có mạng lưới";
                        break;
                    case Constant.TEXT_TYPE_FOREIGN_LANGUAGE:
                        str = "Ngoại ngữ";
                        break;
                    case Constant.TEXT_TYPE_MOTHER_TONGUE:
                        str = "Tiếng mẹ đẻ";
                        break;
                    case Constant.TEXT_TYPE_WIFI_SETTING:
                        str = "Thiết lập WIFI";
                        break;
                    case Constant.TEXT_TYPE_HOTSPOT:
                        str = "Điểm nóng chia sẻ";
                        break;
                    case Constant.TEXT_TYPE_SCREEN_BRIGHT:
                        str = "Độ sáng màn hình";
                        break;
                    case Constant.TEXT_TYPE_ABOUT_US:
                        str = "Về chúng ta";
                        break;
                    case Constant.TEXT_TYPE_LNG_CHINESE:
                        str = "Tiếng Trung";
                        break;
                    case Constant.TEXT_TYPE_LNG_ENGLISH:
                        str = "Tiếng Anh";
                        break;
                    case Constant.TEXT_TYPE_LNG_ENGLISH_US:
                        str = "Tiếng Anh";
                        break;
                    case Constant.TEXT_TYPE_LNG_ENGLISH_IND:
                        str = "Tiếng Anh";
                        break;
                    case Constant.TEXT_TYPE_LNG_YUEYU:
                        str = "Tiếng Quảng";
                        break;
                    case Constant.TEXT_TYPE_LNG_JAPANESE:
                        str = "Tiếng Nhật";
                        break;
                    case Constant.TEXT_TYPE_LNG_KOREAN:
                        str = "Hàn Quốc";
                        break;
                    case Constant.TEXT_TYPE_LNG_THAI:
                        str = "Tiếng Thái";
                        break;
                    case Constant.TEXT_TYPE_LNG_ARABIAN:
                        str = "Tiếng Ả Rập";
                        break;
                    case Constant.TEXT_TYPE_LNG_RUSSIAN:
                        str = "Tiếng Nga";
                        break;
                    case Constant.TEXT_TYPE_LNG_SPANISH:
                        str = "Tiếng Tây Ban Nha";
                        break;
                    case Constant.TEXT_TYPE_LNG_VIETNAMESE:
                        str = "Tiếng Việt";
                        break;
                    case Constant.TEXT_TYPE_LNG_FRENCH:
                        str = "Tiếng Pháp";
                        break;
                    case Constant.TEXT_TYPE_LNG_GERMAN:
                        str = "Tiếng Đức";
                        break;
                    case Constant.TEXT_TYPE_LNG_PORTUGAL:
                        str = "Tiếng Bồ Đào Nha";
                        break;
                    case Constant.TEXT_TYPE_LNG_ITALIAN:
                        str = "Tiếng Ý";
                        break;
                    case Constant.TEXT_TYPE_LNG_HOLLAND:
                        str = "Tiếng Hà Lan";
                        break;
                    case Constant.TEXT_TYPE_LNG_GREEK:
                        str = "Tiếng Hy Lạp";
                        break;
                    case Constant.TEXT_TYPE_LNG_INDONESIA:
                        str = "Người Indonesia";
                        break;
                    case Constant.TEXT_TYPE_FRIEND:
                        str = "Bạn thân.";
                        break;
                    case Constant.TEXT_TYPE_SETTING:
                        str = "Thiết lập";
                        break;
                    case Constant.TEXT_TYPE_SYSTEM_LNG:
                        str = "Hệ thống ngôn ngữ.";
                        break;
                    case Constant.TEXT_TYPE_ABOUT_DEVICE:
                        str = "Về thiết bị";
                        break;
                    case Constant.TEXT_TYPE_CHOOSE:
                        str = "Lựa chọn.";
                        break;
                    case Constant.TEXT_TYPE_SEARCHING:
                        str = "Đang tìm kiếm....";
                        break;
                    case Constant.TEXT_TYPE_WIFI_CLOSE:
                        str = "WIFI đóng";
                        break;
                    case Constant.TEXT_TYPE_PWD_LENGTH_8:
                        str = "Mật mã không thể ngắn hơn chiều dài 8 - bit.";
                        break;
                    case Constant.TEXT_TYPE_SEARCH_COMPLETE:
                        str = "Tìm kiếm xong";
                        break;
                    case Constant.TEXT_TYPE_CONNECTED:
                        str = "Đã kết nối";
                        break;
                    case Constant.TEXT_TYPE_CONNECTING:
                        str = "Trong kết nối...";
                        break;
                    case Constant.TEXT_TYPE_MODIFY_IDENTIFY:
                        str = "Đang xác minh thông tin....";
                        break;
                    case Constant.TEXT_TYPE_GET_IP_ADDRESS:
                        str = "Đang có được địa chỉ IP...";
                        break;
                    case Constant.TEXT_TYPE_ENCIPHER:
                        str = "Mã hóa";
                        break;
                    case Constant.TEXT_TYPE_OPEN_NETWORK:
                        str = "Mở mạng";
                        break;
                    case Constant.TEXT_TYPE_INPUT_PWD:
                        str = "Nhập mật mã.";
                        break;
                    case Constant.TEXT_TYPE_PASSWORD:
                        str = "Mật mã.";
                        break;
                    case Constant.TEXT_TYPE_WIFI_CONN:
                        str = "WIFI kết nối";
                        break;
                    case Constant.TEXT_TYPE_SELECT_NEAR_WLAN:
                        str = "Chọn gần WLAN...";
                        break;
                    case Constant.TEXT_TYPE_MOTHER_TONGUE_CHOOSE:
                        str = "Tiếng mẹ đẻ lựa chọn.";
                        break;
                    case Constant.TEXT_TYPE_FORENGN_LNG_CHOOSE:
                        str = "Được lựa chọn.";
                        break;
                    case Constant.TEXT_TYPE_SYSTEM_LNG_CHOOSE:
                        str = "Hệ thống lựa chọn ngôn ngữ.";
                        break;
                }
                break;
            case LngConstant.LANGUAGE_FRENCH://法语
                switch (textType) {
                    case Constant.TEXT_TYPE_OPEN_WIFI:
                        str = "ouvrez le wifi";
                        break;
                    case Constant.TEXT_NOT_IMEI:
                        str = "NO IMEI";
                        break;
                    case Constant.LANGUAGE_TOAST:
                        str = "Long press the Right Key to speak";
                        break;
                    case Constant.TEXT_CLEAN:
                        str = "éliminer";
                        break;
                    case Constant.TEXT_RETURN:
                        str = "De retour";
                        break;
                    case Constant.TEXT_TYPE_UPDATING:
                        str = "Est mise à jour...";
                        break;
                    case Constant.TEXT_TYPE_UPDATE_FAILED:
                        str = "L'échec de la mise à jour:";
                        break;
                    case Constant.TEXT_TYPE_CHOOSE_WIFI:
                        str = "sélectionnez le wifi";
                        break;
                    case Constant.TEXT_TYPE_CLOSE_WIFI:
                        str = "Le Wifi";
                        break;
                    case Constant.TEXT_TYPE_OPEN_HOTSPOT:
                        str = "Ouvre le point chaud";
                        break;
                    case Constant.TEXT_TYPE_CLOSE_HOTSPOT:
                        str = "La fermeture de points chauds";
                        break;
                    case Constant.TEXT_TYPE_PWD_ERROR:
                        str = "Erreur de mot de passe!";
                        break;
                    case Constant.TEXT_TYPE_WIFI_CONN_SUCC:
                        str = "Connexion wifi de succès";
                        break;
                    case Constant.TEXT_TYPE_INPUT_WIFI_PWD:
                        str = "Veuillez saisir le mot de passe de wifi";
                        break;
                    case Constant.TEXT_TYPE_NO_NETWORK:
                        str = "Pas de réseau";
                        break;
                    case Constant.TEXT_TYPE_FOREIGN_LANGUAGE:
                        str = "Langue étrangère";
                        break;
                    case Constant.TEXT_TYPE_MOTHER_TONGUE:
                        str = "Langue maternelle";
                        break;
                    case Constant.TEXT_TYPE_WIFI_SETTING:
                        str = "Wifi est disposé";
                        break;
                    case Constant.TEXT_TYPE_HOTSPOT:
                        str = "Partage de points chauds";
                        break;
                    case Constant.TEXT_TYPE_SCREEN_BRIGHT:
                        str = "La luminosité de l'écran";
                        break;
                    case Constant.TEXT_TYPE_ABOUT_US:
                        str = "Sur nous";
                        break;
                    case Constant.TEXT_TYPE_LNG_CHINESE:
                        str = "Chinois";
                        break;
                    case Constant.TEXT_TYPE_LNG_ENGLISH:
                        str = "Anglais";
                        break;
                    case Constant.TEXT_TYPE_LNG_ENGLISH_US:
                        str = "Anglais";
                        break;
                    case Constant.TEXT_TYPE_LNG_ENGLISH_IND:
                        str = "Anglais";
                        break;
                    case Constant.TEXT_TYPE_LNG_YUEYU:
                        str = "Cantonais";
                        break;
                    case Constant.TEXT_TYPE_LNG_JAPANESE:
                        str = "Japonais";
                        break;
                    case Constant.TEXT_TYPE_LNG_KOREAN:
                        str = "Coréen";
                        break;
                    case Constant.TEXT_TYPE_LNG_THAI:
                        str = "Thaï";
                        break;
                    case Constant.TEXT_TYPE_LNG_ARABIAN:
                        str = "Arabe";
                        break;
                    case Constant.TEXT_TYPE_LNG_RUSSIAN:
                        str = "Russe";
                        break;
                    case Constant.TEXT_TYPE_LNG_SPANISH:
                        str = "Espagnol";
                        break;
                    case Constant.TEXT_TYPE_LNG_VIETNAMESE:
                        str = "Vietnamien";
                        break;
                    case Constant.TEXT_TYPE_LNG_FRENCH:
                        str = "Français";
                        break;
                    case Constant.TEXT_TYPE_LNG_GERMAN:
                        str = "Allemand";
                        break;
                    case Constant.TEXT_TYPE_LNG_PORTUGAL:
                        str = "Portugais";
                        break;
                    case Constant.TEXT_TYPE_LNG_ITALIAN:
                        str = "Italien";
                        break;
                    case Constant.TEXT_TYPE_LNG_HOLLAND:
                        str = "Néerlandais";
                        break;
                    case Constant.TEXT_TYPE_LNG_GREEK:
                        str = "Grec";
                        break;
                    case Constant.TEXT_TYPE_LNG_INDONESIA:
                        str = "Indonésien";
                        break;
                    case Constant.TEXT_TYPE_FRIEND:
                        str = "Des amis";
                        break;
                    case Constant.TEXT_TYPE_SETTING:
                        str = "Ensemble";
                        break;
                    case Constant.TEXT_TYPE_SYSTEM_LNG:
                        str = "Système de langage";
                        break;
                    case Constant.TEXT_TYPE_ABOUT_DEVICE:
                        str = "Sur le dispositif";
                        break;
                    case Constant.TEXT_TYPE_CHOOSE:
                        str = "Sélection";
                        break;
                    case Constant.TEXT_TYPE_SEARCHING:
                        str = "Recherche...";
                        break;
                    case Constant.TEXT_TYPE_WIFI_CLOSE:
                        str = "Wifi de fermeture";
                        break;
                    case Constant.TEXT_TYPE_PWD_LENGTH_8:
                        str = "La longueur de mot de passe ne peut pas être inférieure à 8 bits";
                        break;
                    case Constant.TEXT_TYPE_SEARCH_COMPLETE:
                        str = "Recherche terminée";
                        break;
                    case Constant.TEXT_TYPE_CONNECTED:
                        str = "Est connecté";
                        break;
                    case Constant.TEXT_TYPE_CONNECTING:
                        str = "Lors de la connexion...";
                        break;
                    case Constant.TEXT_TYPE_MODIFY_IDENTIFY:
                        str = "Vérification des informations d'identité...";
                        break;
                    case Constant.TEXT_TYPE_GET_IP_ADDRESS:
                        str = "Est d'obtenir une adresse IP...";
                        break;
                    case Constant.TEXT_TYPE_ENCIPHER:
                        str = "Chiffrement";
                        break;
                    case Constant.TEXT_TYPE_OPEN_NETWORK:
                        str = "Un réseau ouvert";
                        break;
                    case Constant.TEXT_TYPE_INPUT_PWD:
                        str = "Saisissez le mot de passe";
                        break;
                    case Constant.TEXT_TYPE_PASSWORD:
                        str = "Mot de passe";
                        break;
                    case Constant.TEXT_TYPE_WIFI_CONN:
                        str = "Connexion wifi.";
                        break;
                    case Constant.TEXT_TYPE_SELECT_NEAR_WLAN:
                        str = "Sélection de réseau local sans fil (WLAN) à proximité...";
                        break;
                    case Constant.TEXT_TYPE_MOTHER_TONGUE_CHOOSE:
                        str = "Sélection de la langue maternelle";
                        break;
                    case Constant.TEXT_TYPE_FORENGN_LNG_CHOOSE:
                        str = "Langues étrangères";
                        break;
                    case Constant.TEXT_TYPE_SYSTEM_LNG_CHOOSE:
                        str = "Système de sélection de la langue";
                        break;
                }
                break;
            case LngConstant.LANGUAGE_GERMAN://德语
                switch (textType) {
                    case Constant.TEXT_TYPE_OPEN_WIFI:
                        str = "offene wlan";
                        break;
                    case Constant.TEXT_NOT_IMEI:
                        str = "NO IMEI";
                        break;
                    case Constant.LANGUAGE_TOAST:
                        str = "Long press the Right Key to speak";
                        break;
                    case Constant.TEXT_CLEAN:
                        str = "Klar,";
                        break;
                    case Constant.TEXT_RETURN:
                        str = "Zurück";
                        break;
                    case Constant.TEXT_TYPE_UPDATING:
                        str = "Aktualisierung der...";
                        break;
                    case Constant.TEXT_TYPE_UPDATE_FAILED:
                        str = "Update fehlgeschlagen:";
                        break;
                    case Constant.TEXT_TYPE_CHOOSE_WIFI:
                        str = "wählen sie wifi";
                        break;
                    case Constant.TEXT_TYPE_CLOSE_WIFI:
                        str = "In der nähe der WiFi";
                        break;
                    case Constant.TEXT_TYPE_OPEN_HOTSPOT:
                        str = "Offene hotspots";
                        break;
                    case Constant.TEXT_TYPE_CLOSE_HOTSPOT:
                        str = "Schließung der hotspot";
                        break;
                    case Constant.TEXT_TYPE_PWD_ERROR:
                        str = "Das passwort falsch!";
                        break;
                    case Constant.TEXT_TYPE_WIFI_CONN_SUCC:
                        str = "WiFi - MIT Erfolg";
                        break;
                    case Constant.TEXT_TYPE_INPUT_WIFI_PWD:
                        str = "Bitte geben sie die WiFi -";
                        break;
                    case Constant.TEXT_TYPE_NO_NETWORK:
                        str = "Ohne Netzwerk";
                        break;
                    case Constant.TEXT_TYPE_FOREIGN_LANGUAGE:
                        str = "Fremdsprache";
                        break;
                    case Constant.TEXT_TYPE_MOTHER_TONGUE:
                        str = "Die muttersprache";
                        break;
                    case Constant.TEXT_TYPE_WIFI_SETTING:
                        str = "WLAN - einstellungen";
                        break;
                    case Constant.TEXT_TYPE_HOTSPOT:
                        str = "Hotspot - sharing";
                        break;
                    case Constant.TEXT_TYPE_SCREEN_BRIGHT:
                        str = "Die bildschirm - helligkeit";
                        break;
                    case Constant.TEXT_TYPE_ABOUT_US:
                        str = "über uns";
                        break;
                    case Constant.TEXT_TYPE_LNG_CHINESE:
                        str = "Chinesisch";
                        break;
                    case Constant.TEXT_TYPE_LNG_ENGLISH:
                        str = "Englisch";
                        break;
                    case Constant.TEXT_TYPE_LNG_ENGLISH_US:
                        str = "Englisch";
                        break;
                    case Constant.TEXT_TYPE_LNG_ENGLISH_IND:
                        str = "Englisch";
                        break;
                    case Constant.TEXT_TYPE_LNG_YUEYU:
                        str = "Kantonesisch";
                        break;
                    case Constant.TEXT_TYPE_LNG_JAPANESE:
                        str = "Japanisch";
                        break;
                    case Constant.TEXT_TYPE_LNG_KOREAN:
                        str = "Koreanisch";
                        break;
                    case Constant.TEXT_TYPE_LNG_THAI:
                        str = "Thai";
                        break;
                    case Constant.TEXT_TYPE_LNG_ARABIAN:
                        str = "Arabisch";
                        break;
                    case Constant.TEXT_TYPE_LNG_RUSSIAN:
                        str = "Russisch";
                        break;
                    case Constant.TEXT_TYPE_LNG_SPANISH:
                        str = "Spanisch";
                        break;
                    case Constant.TEXT_TYPE_LNG_VIETNAMESE:
                        str = "Vietnamesisch";
                        break;
                    case Constant.TEXT_TYPE_LNG_FRENCH:
                        str = "Französisch";
                        break;
                    case Constant.TEXT_TYPE_LNG_GERMAN:
                        str = "Deutsch";
                        break;
                    case Constant.TEXT_TYPE_LNG_PORTUGAL:
                        str = "Portugiesisch";
                        break;
                    case Constant.TEXT_TYPE_LNG_ITALIAN:
                        str = "Italienisch";
                        break;
                    case Constant.TEXT_TYPE_LNG_HOLLAND:
                        str = "Niederländisch";
                        break;
                    case Constant.TEXT_TYPE_LNG_GREEK:
                        str = "Griechisch";
                        break;
                    case Constant.TEXT_TYPE_LNG_INDONESIA:
                        str = "Indonesisch";
                        break;
                    case Constant.TEXT_TYPE_FRIEND:
                        str = "Freunde";
                        break;
                    case Constant.TEXT_TYPE_SETTING:
                        str = "Einstellungen";
                        break;
                    case Constant.TEXT_TYPE_SYSTEM_LNG:
                        str = "System - Sprache";
                        break;
                    case Constant.TEXT_TYPE_ABOUT_DEVICE:
                        str = "Für die ausrüstung";
                        break;
                    case Constant.TEXT_TYPE_CHOOSE:
                        str = "Wahl";
                        break;
                    case Constant.TEXT_TYPE_SEARCHING:
                        str = "Auf der suche nach...";
                        break;
                    case Constant.TEXT_TYPE_WIFI_CLOSE:
                        str = "WiFi geschlossen";
                        break;
                    case Constant.TEXT_TYPE_PWD_LENGTH_8:
                        str = "Passwort länge nicht weniger ALS 8 - bit -";
                        break;
                    case Constant.TEXT_TYPE_SEARCH_COMPLETE:
                        str = "Die suche abgeschlossen";
                        break;
                    case Constant.TEXT_TYPE_CONNECTED:
                        str = "Verbunden ist";
                        break;
                    case Constant.TEXT_TYPE_CONNECTING:
                        str = "Verbindung...";
                        break;
                    case Constant.TEXT_TYPE_MODIFY_IDENTIFY:
                        str = "überprüfung der Identität Informationen...";
                        break;
                    case Constant.TEXT_TYPE_GET_IP_ADDRESS:
                        str = "Ist die IP - Adresse...";
                        break;
                    case Constant.TEXT_TYPE_ENCIPHER:
                        str = "Die verschlüsselung";
                        break;
                    case Constant.TEXT_TYPE_OPEN_NETWORK:
                        str = "Open Network";
                        break;
                    case Constant.TEXT_TYPE_INPUT_PWD:
                        str = "Das passwort";
                        break;
                    case Constant.TEXT_TYPE_PASSWORD:
                        str = "Passwort";
                        break;
                    case Constant.TEXT_TYPE_WIFI_CONN:
                        str = "Eine WiFi - Verbindung";
                        break;
                    case Constant.TEXT_TYPE_SELECT_NEAR_WLAN:
                        str = "In der nähe der WLAN - auswahl...";
                        break;
                    case Constant.TEXT_TYPE_MOTHER_TONGUE_CHOOSE:
                        str = "Muttersprache wählen";
                        break;
                    case Constant.TEXT_TYPE_FORENGN_LNG_CHOOSE:
                        str = "Fremdsprache wählen";
                        break;
                    case Constant.TEXT_TYPE_SYSTEM_LNG_CHOOSE:
                        str = "System Sprache wählen.";
                        break;
                }
                break;
            case LngConstant.LANGUAGE_PORTUGAL://葡萄牙语
                switch (textType) {
                    case Constant.TEXT_TYPE_OPEN_WIFI:
                        str = "Abra o wi - fi";
                        break;
                    case Constant.TEXT_NOT_IMEI:
                        str = "NO IMEI";
                        break;
                    case Constant.LANGUAGE_TOAST:
                        str = "Long press the Right Key to speak";
                        break;
                    case Constant.TEXT_CLEAN:
                        str = "Limpar";
                        break;
                    case Constant.TEXT_RETURN:
                        str = "Voltar para";
                        break;
                    case Constant.TEXT_TYPE_UPDATING:
                        str = "A atualização...";
                        break;
                    case Constant.TEXT_TYPE_UPDATE_FAILED:
                        str = "Falha EM atualização:";
                        break;
                    case Constant.TEXT_TYPE_CHOOSE_WIFI:
                        str = "Selecione wi - fi";
                        break;
                    case Constant.TEXT_TYPE_CLOSE_WIFI:
                        str = "Desligar o wi - fi";
                        break;
                    case Constant.TEXT_TYPE_OPEN_HOTSPOT:
                        str = "Abrir Quente";
                        break;
                    case Constant.TEXT_TYPE_CLOSE_HOTSPOT:
                        str = "Fechar o foco";
                        break;
                    case Constant.TEXT_TYPE_PWD_ERROR:
                        str = "Senha errada!";
                        break;
                    case Constant.TEXT_TYPE_WIFI_CONN_SUCC:
                        str = "A conexão wi - fi";
                        break;
                    case Constant.TEXT_TYPE_INPUT_WIFI_PWD:
                        str = "Por favor, Digite a senha Da WiFi";
                        break;
                    case Constant.TEXT_TYPE_NO_NETWORK:
                        str = "SEM rede";
                        break;
                    case Constant.TEXT_TYPE_FOREIGN_LANGUAGE:
                        str = "Língua Estrangeira";
                        break;
                    case Constant.TEXT_TYPE_MOTHER_TONGUE:
                        str = "A língua Materna";
                        break;
                    case Constant.TEXT_TYPE_WIFI_SETTING:
                        str = "Configurações de wi - fi";
                        break;
                    case Constant.TEXT_TYPE_HOTSPOT:
                        str = "A partilha de Pontos";
                        break;
                    case Constant.TEXT_TYPE_SCREEN_BRIGHT:
                        str = "O brilho Da tela";
                        break;
                    case Constant.TEXT_TYPE_ABOUT_US:
                        str = "Sobre nós";
                        break;
                    case Constant.TEXT_TYPE_LNG_CHINESE:
                        str = "Chinês";
                        break;
                    case Constant.TEXT_TYPE_LNG_ENGLISH:
                        str = "EM inglês";
                        break;
                    case Constant.TEXT_TYPE_LNG_ENGLISH_US:
                        str = "EM inglês";
                        break;
                    case Constant.TEXT_TYPE_LNG_ENGLISH_IND:
                        str = "EM inglês";
                        break;
                    case Constant.TEXT_TYPE_LNG_YUEYU:
                        str = "Cantonês";
                        break;
                    case Constant.TEXT_TYPE_LNG_JAPANESE:
                        str = "Japonês";
                        break;
                    case Constant.TEXT_TYPE_LNG_KOREAN:
                        str = "Coreano";
                        break;
                    case Constant.TEXT_TYPE_LNG_THAI:
                        str = "Thai";
                        break;
                    case Constant.TEXT_TYPE_LNG_ARABIAN:
                        str = "O árabe";
                        break;
                    case Constant.TEXT_TYPE_LNG_RUSSIAN:
                        str = "Russo";
                        break;
                    case Constant.TEXT_TYPE_LNG_SPANISH:
                        str = "Espanhol";
                        break;
                    case Constant.TEXT_TYPE_LNG_VIETNAMESE:
                        str = "Vietnamita";
                        break;
                    case Constant.TEXT_TYPE_LNG_FRENCH:
                        str = "EM francês";
                        break;
                    case Constant.TEXT_TYPE_LNG_GERMAN:
                        str = "EM alemão";
                        break;
                    case Constant.TEXT_TYPE_LNG_PORTUGAL:
                        str = "Português";
                        break;
                    case Constant.TEXT_TYPE_LNG_ITALIAN:
                        str = "EM Italiano";
                        break;
                    case Constant.TEXT_TYPE_LNG_HOLLAND:
                        str = "Holandês";
                        break;
                    case Constant.TEXT_TYPE_LNG_GREEK:
                        str = "O Grego";
                        break;
                    case Constant.TEXT_TYPE_LNG_INDONESIA:
                        str = "Indonésio";
                        break;
                    case Constant.TEXT_TYPE_FRIEND:
                        str = "Amigos";
                        break;
                    case Constant.TEXT_TYPE_SETTING:
                        str = "Set";
                        break;
                    case Constant.TEXT_TYPE_SYSTEM_LNG:
                        str = "O sistema de linguagem";
                        break;
                    case Constant.TEXT_TYPE_ABOUT_DEVICE:
                        str = "Sobre o equipamento";
                        break;
                    case Constant.TEXT_TYPE_CHOOSE:
                        str = "Escolher";
                        break;
                    case Constant.TEXT_TYPE_SEARCHING:
                        str = "EM Busca de...";
                        break;
                    case Constant.TEXT_TYPE_WIFI_CLOSE:
                        str = "WiFi desligado";
                        break;
                    case Constant.TEXT_TYPE_PWD_LENGTH_8:
                        str = "A senha não Pode ser inferior a 8 bits";
                        break;
                    case Constant.TEXT_TYPE_SEARCH_COMPLETE:
                        str = "A pesquisa completa";
                        break;
                    case Constant.TEXT_TYPE_CONNECTED:
                        str = "Já a conexão";
                        break;
                    case Constant.TEXT_TYPE_CONNECTING:
                        str = "A conexão...";
                        break;
                    case Constant.TEXT_TYPE_MODIFY_IDENTIFY:
                        str = "Verificar informações de identidade...";
                        break;
                    case Constant.TEXT_TYPE_GET_IP_ADDRESS:
                        str = "A obter o endereço IP...";
                        break;
                    case Constant.TEXT_TYPE_ENCIPHER:
                        str = "A criptografia";
                        break;
                    case Constant.TEXT_TYPE_OPEN_NETWORK:
                        str = "A rede Aberta";
                        break;
                    case Constant.TEXT_TYPE_INPUT_PWD:
                        str = "Digite a senha";
                        break;
                    case Constant.TEXT_TYPE_PASSWORD:
                        str = "A senha";
                        break;
                    case Constant.TEXT_TYPE_WIFI_CONN:
                        str = "Conectividade wi - fi";
                        break;
                    case Constant.TEXT_TYPE_SELECT_NEAR_WLAN:
                        str = "Perto de WLAN...";
                        break;
                    case Constant.TEXT_TYPE_MOTHER_TONGUE_CHOOSE:
                        str = "A escolha Da língua Materna";
                        break;
                    case Constant.TEXT_TYPE_FORENGN_LNG_CHOOSE:
                        str = "A escolha de línguas estrangeiras";
                        break;
                    case Constant.TEXT_TYPE_SYSTEM_LNG_CHOOSE:
                        str = "Sistema de seleção de idioma";
                        break;
                }
                break;
            case LngConstant.LANGUAGE_ITALIAN://意大利语
                switch (textType) {
                    case Constant.TEXT_TYPE_OPEN_WIFI:
                        str = "Apri il Wi - Fi";
                        break;
                    case Constant.TEXT_NOT_IMEI:
                        str = "NO IMEI";
                        break;
                    case Constant.LANGUAGE_TOAST:
                        str = "Long press the Right Key to speak";
                        break;
                    case Constant.TEXT_CLEAN:
                        str = "Eliminazione";
                        break;
                    case Constant.TEXT_RETURN:
                        str = "Di ritorno";
                        break;
                    case Constant.TEXT_TYPE_UPDATING:
                        str = "è in corso di aggiornamento...";
                        break;
                    case Constant.TEXT_TYPE_UPDATE_FAILED:
                        str = "Aggiornare il fallimento:";
                        break;
                    case Constant.TEXT_TYPE_CHOOSE_WIFI:
                        str = "seleziona il wi - fi";
                        break;
                    case Constant.TEXT_TYPE_CLOSE_WIFI:
                        str = "Spegni il Wi - Fi";
                        break;
                    case Constant.TEXT_TYPE_OPEN_HOTSPOT:
                        str = "Aprire il caldo";
                        break;
                    case Constant.TEXT_TYPE_CLOSE_HOTSPOT:
                        str = "La chiusura di caldo";
                        break;
                    case Constant.TEXT_TYPE_PWD_ERROR:
                        str = "Password sbagliata!";
                        break;
                    case Constant.TEXT_TYPE_WIFI_CONN_SUCC:
                        str = "IL successo di connessione Wi - Fi";
                        break;
                    case Constant.TEXT_TYPE_INPUT_WIFI_PWD:
                        str = "Inserisci la Password Wi - Fi";
                        break;
                    case Constant.TEXT_TYPE_NO_NETWORK:
                        str = "Senza La Rete";
                        break;
                    case Constant.TEXT_TYPE_FOREIGN_LANGUAGE:
                        str = "Lingua straniera";
                        break;
                    case Constant.TEXT_TYPE_MOTHER_TONGUE:
                        str = "Di lingua Madre";
                        break;
                    case Constant.TEXT_TYPE_WIFI_SETTING:
                        str = "IL Wi - Fi";
                        break;
                    case Constant.TEXT_TYPE_HOTSPOT:
                        str = "La condivisione di Temi";
                        break;
                    case Constant.TEXT_TYPE_SCREEN_BRIGHT:
                        str = "La luminosità dello schermo";
                        break;
                    case Constant.TEXT_TYPE_ABOUT_US:
                        str = "Su di noi";
                        break;
                    case Constant.TEXT_TYPE_LNG_CHINESE:
                        str = "IL cinese";
                        break;
                    case Constant.TEXT_TYPE_LNG_ENGLISH:
                        str = "L'inglese";
                        break;
                    case Constant.TEXT_TYPE_LNG_ENGLISH_US:
                        str = "L'inglese";
                        break;
                    case Constant.TEXT_TYPE_LNG_ENGLISH_IND:
                        str = "L'inglese";
                        break;
                    case Constant.TEXT_TYPE_LNG_YUEYU:
                        str = "IL cantonese";
                        break;
                    case Constant.TEXT_TYPE_LNG_JAPANESE:
                        str = "In giapponese";
                        break;
                    case Constant.TEXT_TYPE_LNG_KOREAN:
                        str = "IL coreano";
                        break;
                    case Constant.TEXT_TYPE_LNG_THAI:
                        str = "Thai";
                        break;
                    case Constant.TEXT_TYPE_LNG_ARABIAN:
                        str = "L'arabo";
                        break;
                    case Constant.TEXT_TYPE_LNG_RUSSIAN:
                        str = "Russo";
                        break;
                    case Constant.TEXT_TYPE_LNG_SPANISH:
                        str = "in Spagnolo";
                        break;
                    case Constant.TEXT_TYPE_LNG_VIETNAMESE:
                        str = "Vietnamiti";
                        break;
                    case Constant.TEXT_TYPE_LNG_FRENCH:
                        str = "IL Francese";
                        break;
                    case Constant.TEXT_TYPE_LNG_GERMAN:
                        str = "IL Tedesco";
                        break;
                    case Constant.TEXT_TYPE_LNG_PORTUGAL:
                        str = "Portoghese";
                        break;
                    case Constant.TEXT_TYPE_LNG_ITALIAN:
                        str = "In Italiano";
                        break;
                    case Constant.TEXT_TYPE_LNG_HOLLAND:
                        str = "Olandese";
                        break;
                    case Constant.TEXT_TYPE_LNG_GREEK:
                        str = "IL Greco";
                        break;
                    case Constant.TEXT_TYPE_LNG_INDONESIA:
                        str = "indonesiano";
                        break;
                    case Constant.TEXT_TYPE_FRIEND:
                        str = "Amici";
                        break;
                    case Constant.TEXT_TYPE_SETTING:
                        str = "Impostazioni";
                        break;
                    case Constant.TEXT_TYPE_SYSTEM_LNG:
                        str = "La Lingua del Sistema";
                        break;
                    case Constant.TEXT_TYPE_ABOUT_DEVICE:
                        str = "In materia di attrezzature";
                        break;
                    case Constant.TEXT_TYPE_CHOOSE:
                        str = "La scelta di";
                        break;
                    case Constant.TEXT_TYPE_SEARCHING:
                        str = "Sta cercando di...";
                        break;
                    case Constant.TEXT_TYPE_WIFI_CLOSE:
                        str = "IL Wi - Fi di chiusura";
                        break;
                    case Constant.TEXT_TYPE_PWD_LENGTH_8:
                        str = "La Password di durata non inferiore a 8 bit";
                        break;
                    case Constant.TEXT_TYPE_SEARCH_COMPLETE:
                        str = "Una ricerca completa";
                        break;
                    case Constant.TEXT_TYPE_CONNECTED:
                        str = "è collegato";
                        break;
                    case Constant.TEXT_TYPE_CONNECTING:
                        str = "In collegamento...";
                        break;
                    case Constant.TEXT_TYPE_MODIFY_IDENTIFY:
                        str = "Sta per verificare l'identità di informazioni...";
                        break;
                    case Constant.TEXT_TYPE_GET_IP_ADDRESS:
                        str = "Sta per ottenere l'indirizzo IP...";
                        break;
                    case Constant.TEXT_TYPE_ENCIPHER:
                        str = "Cifratura";
                        break;
                    case Constant.TEXT_TYPE_OPEN_NETWORK:
                        str = "Rete Aperta di";
                        break;
                    case Constant.TEXT_TYPE_INPUT_PWD:
                        str = "Inserire la Password";
                        break;
                    case Constant.TEXT_TYPE_PASSWORD:
                        str = "La Password";
                        break;
                    case Constant.TEXT_TYPE_WIFI_CONN:
                        str = "Connettività Wi - Fi";
                        break;
                    case Constant.TEXT_TYPE_SELECT_NEAR_WLAN:
                        str = "La SELEZIONE nelle vicinanze di WLAN...";
                        break;
                    case Constant.TEXT_TYPE_MOTHER_TONGUE_CHOOSE:
                        str = "La Lingua scelta";
                        break;
                    case Constant.TEXT_TYPE_FORENGN_LNG_CHOOSE:
                        str = "La scelta delle lingue straniere";
                        break;
                    case Constant.TEXT_TYPE_SYSTEM_LNG_CHOOSE:
                        str = "La scelta del linguaggio";
                        break;
                }
                break;
            case LngConstant.LANGUAGE_HOLLAND://荷兰语
                switch (textType) {
                    case Constant.TEXT_TYPE_OPEN_WIFI:
                        str = "Open wi - fi";
                        break;
                    case Constant.TEXT_NOT_IMEI:
                        str = "NO IMEI";
                        break;
                    case Constant.LANGUAGE_TOAST:
                        str = "Long press the Right Key to speak";
                        break;
                    case Constant.TEXT_CLEAN:
                        str = "Verwijdering van";
                        break;
                    case Constant.TEXT_RETURN:
                        str = "Terug naar de";
                        break;
                    case Constant.TEXT_TYPE_UPDATING:
                        str = "Bijwerking...";
                        break;
                    case Constant.TEXT_TYPE_UPDATE_FAILED:
                        str = "Een nieuwe mislukking:";
                        break;
                    case Constant.TEXT_TYPE_CHOOSE_WIFI:
                        str = "De keuze van een wifi";
                        break;
                    case Constant.TEXT_TYPE_CLOSE_WIFI:
                        str = "Sluit de wifi";
                        break;
                    case Constant.TEXT_TYPE_OPEN_HOTSPOT:
                        str = "De open plek";
                        break;
                    case Constant.TEXT_TYPE_CLOSE_HOTSPOT:
                        str = "Sluit";
                        break;
                    case Constant.TEXT_TYPE_PWD_ERROR:
                        str = "Fout wachtwoord!";
                        break;
                    case Constant.TEXT_TYPE_WIFI_CONN_SUCC:
                        str = "De wi - fi in verband met succes";
                        break;
                    case Constant.TEXT_TYPE_INPUT_WIFI_PWD:
                        str = "Gelieve de WIFI";
                        break;
                    case Constant.TEXT_TYPE_NO_NETWORK:
                        str = "Geen netwerk";
                        break;
                    case Constant.TEXT_TYPE_FOREIGN_LANGUAGE:
                        str = "Vreemde talen";
                        break;
                    case Constant.TEXT_TYPE_MOTHER_TONGUE:
                        str = "De moedertaal";
                        break;
                    case Constant.TEXT_TYPE_WIFI_SETTING:
                        str = "Wifi";
                        break;
                    case Constant.TEXT_TYPE_HOTSPOT:
                        str = "Hete delen";
                        break;
                    case Constant.TEXT_TYPE_SCREEN_BRIGHT:
                        str = "Het scherm";
                        break;
                    case Constant.TEXT_TYPE_ABOUT_US:
                        str = "Over ons";
                        break;
                    case Constant.TEXT_TYPE_LNG_CHINESE:
                        str = "De Chinese";
                        break;
                    case Constant.TEXT_TYPE_LNG_ENGLISH:
                        str = "Engels";
                        break;
                    case Constant.TEXT_TYPE_LNG_ENGLISH_US:
                        str = "Engels";
                        break;
                    case Constant.TEXT_TYPE_LNG_ENGLISH_IND:
                        str = "Engels";
                        break;
                    case Constant.TEXT_TYPE_LNG_YUEYU:
                        str = "Kantonees";
                        break;
                    case Constant.TEXT_TYPE_LNG_JAPANESE:
                        str = "Japans";
                        break;
                    case Constant.TEXT_TYPE_LNG_KOREAN:
                        str = "Koreaanse";
                        break;
                    case Constant.TEXT_TYPE_LNG_THAI:
                        str = "Thai";
                        break;
                    case Constant.TEXT_TYPE_LNG_ARABIAN:
                        str = "Arabisch";
                        break;
                    case Constant.TEXT_TYPE_LNG_RUSSIAN:
                        str = "De Russische";
                        break;
                    case Constant.TEXT_TYPE_LNG_SPANISH:
                        str = "Spaans";
                        break;
                    case Constant.TEXT_TYPE_LNG_VIETNAMESE:
                        str = "De Vietnamese";
                        break;
                    case Constant.TEXT_TYPE_LNG_FRENCH:
                        str = "De Franse";
                        break;
                    case Constant.TEXT_TYPE_LNG_GERMAN:
                        str = "De Duitse";
                        break;
                    case Constant.TEXT_TYPE_LNG_PORTUGAL:
                        str = "De Portugese";
                        break;
                    case Constant.TEXT_TYPE_LNG_ITALIAN:
                        str = "De Italiaanse";
                        break;
                    case Constant.TEXT_TYPE_LNG_HOLLAND:
                        str = "De Nederlandse";
                        break;
                    case Constant.TEXT_TYPE_LNG_GREEK:
                        str = "De Griekse";
                        break;
                    case Constant.TEXT_TYPE_LNG_INDONESIA:
                        str = "Indonesisch";
                        break;
                    case Constant.TEXT_TYPE_FRIEND:
                        str = "Vrienden.";
                        break;
                    case Constant.TEXT_TYPE_SETTING:
                        str = "Die";
                        break;
                    case Constant.TEXT_TYPE_SYSTEM_LNG:
                        str = "Het systeem taal";
                        break;
                    case Constant.TEXT_TYPE_ABOUT_DEVICE:
                        str = "Met betrekking tot de apparatuur";
                        break;
                    case Constant.TEXT_TYPE_CHOOSE:
                        str = "De keuze van";
                        break;
                    case Constant.TEXT_TYPE_SEARCHING:
                        str = "Op zoek naar...";
                        break;
                    case Constant.TEXT_TYPE_WIFI_CLOSE:
                        str = "Wifi gesloten";
                        break;
                    case Constant.TEXT_TYPE_PWD_LENGTH_8:
                        str = "Het wachtwoord is niet minder dan 8";
                        break;
                    case Constant.TEXT_TYPE_SEARCH_COMPLETE:
                        str = "De zoektocht is";
                        break;
                    case Constant.TEXT_TYPE_CONNECTED:
                        str = "Is verbonden.";
                        break;
                    case Constant.TEXT_TYPE_CONNECTING:
                        str = "In verband met de in de...";
                        break;
                    case Constant.TEXT_TYPE_MODIFY_IDENTIFY:
                        str = "Is de verificatie van de identiteit van informatie...";
                        break;
                    case Constant.TEXT_TYPE_GET_IP_ADDRESS:
                        str = "Is het verkrijgen van een IP - adres...";
                        break;
                    case Constant.TEXT_TYPE_ENCIPHER:
                        str = "-";
                        break;
                    case Constant.TEXT_TYPE_OPEN_NETWORK:
                        str = "De openstelling van de netwerken";
                        break;
                    case Constant.TEXT_TYPE_INPUT_PWD:
                        str = "Code invoeren";
                        break;
                    case Constant.TEXT_TYPE_PASSWORD:
                        str = "Wachtwoord.";
                        break;
                    case Constant.TEXT_TYPE_WIFI_CONN:
                        str = "Een WiFi verbinding";
                        break;
                    case Constant.TEXT_TYPE_SELECT_NEAR_WLAN:
                        str = "Bij de selectie van WLAN -...";
                        break;
                    case Constant.TEXT_TYPE_MOTHER_TONGUE_CHOOSE:
                        str = "De keuze van de moedertaal";
                        break;
                    case Constant.TEXT_TYPE_FORENGN_LNG_CHOOSE:
                        str = "De keuze van vreemde talen";
                        break;
                    case Constant.TEXT_TYPE_SYSTEM_LNG_CHOOSE:
                        str = "- de keuze van de taal";
                        break;
                }
                break;
            case LngConstant.LANGUAGE_GREEK://希腊语
                switch (textType) {
                    case Constant.TEXT_TYPE_OPEN_WIFI:
                        str = "άνοιξε το Wi - fi";
                        break;
                    case Constant.TEXT_NOT_IMEI:
                        str = "NO IMEI";
                        break;
                    case Constant.LANGUAGE_TOAST:
                        str = "Long press the Right Key to speak";
                        break;
                    case Constant.TEXT_CLEAN:
                        str = "κάθαρση";
                        break;
                    case Constant.TEXT_RETURN:
                        str = "επιστρέφει";
                        break;
                    case Constant.TEXT_TYPE_UPDATING:
                        str = "Ενημέρωση...";
                        break;
                    case Constant.TEXT_TYPE_UPDATE_FAILED:
                        str = "Ενημέρωση αποτυχία:";
                        break;
                    case Constant.TEXT_TYPE_CHOOSE_WIFI:
                        str = "Επιλογή Wi - fi";
                        break;
                    case Constant.TEXT_TYPE_CLOSE_WIFI:
                        str = "κλείσε το WIFI";
                        break;
                    case Constant.TEXT_TYPE_OPEN_HOTSPOT:
                        str = "ανοικτή καυτό";
                        break;
                    case Constant.TEXT_TYPE_CLOSE_HOTSPOT:
                        str = "κλείσε ένα καυτό";
                        break;
                    case Constant.TEXT_TYPE_PWD_ERROR:
                        str = "λάθος κωδικός!";
                        break;
                    case Constant.TEXT_TYPE_WIFI_CONN_SUCC:
                        str = "WiFi σύνδεση με επιτυχία";
                        break;
                    case Constant.TEXT_TYPE_INPUT_WIFI_PWD:
                        str = "Παρακαλώ εισάγετε WiFi";
                        break;
                    case Constant.TEXT_TYPE_NO_NETWORK:
                        str = "Χωρίς δίκτυο";
                        break;
                    case Constant.TEXT_TYPE_FOREIGN_LANGUAGE:
                        str = "ξένη γλώσσα";
                        break;
                    case Constant.TEXT_TYPE_MOTHER_TONGUE:
                        str = "μητρική γλώσσα";
                        break;
                    case Constant.TEXT_TYPE_WIFI_SETTING:
                        str = "WiFi ρυθμίσεις";
                        break;
                    case Constant.TEXT_TYPE_HOTSPOT:
                        str = "καυτό ανταλλαγή";
                        break;
                    case Constant.TEXT_TYPE_SCREEN_BRIGHT:
                        str = "Η φωτεινότητα της οθόνης";
                        break;
                    case Constant.TEXT_TYPE_ABOUT_US:
                        str = "Για μας";
                        break;
                    case Constant.TEXT_TYPE_LNG_CHINESE:
                        str = "Κινέζικα";
                        break;
                    case Constant.TEXT_TYPE_LNG_ENGLISH:
                        str = "Αγγλικά";
                        break;
                    case Constant.TEXT_TYPE_LNG_ENGLISH_US:
                        str = "Αγγλικά";
                        break;
                    case Constant.TEXT_TYPE_LNG_ENGLISH_IND:
                        str = "Αγγλικά";
                        break;
                    case Constant.TEXT_TYPE_LNG_YUEYU:
                        str = "καντονέζικα";
                        break;
                    case Constant.TEXT_TYPE_LNG_JAPANESE:
                        str = "γιαπωνέζικα";
                        break;
                    case Constant.TEXT_TYPE_LNG_KOREAN:
                        str = "Κορέα";
                        break;
                    case Constant.TEXT_TYPE_LNG_THAI:
                        str = "ταϊλανδέζικο";
                        break;
                    case Constant.TEXT_TYPE_LNG_ARABIAN:
                        str = "Αραβικά";
                        break;
                    case Constant.TEXT_TYPE_LNG_RUSSIAN:
                        str = "Ρωσική";
                        break;
                    case Constant.TEXT_TYPE_LNG_SPANISH:
                        str = "Ισπανικά";
                        break;
                    case Constant.TEXT_TYPE_LNG_VIETNAMESE:
                        str = "Το Βιετνάμ";
                        break;
                    case Constant.TEXT_TYPE_LNG_FRENCH:
                        str = "Γαλλικά";
                        break;
                    case Constant.TEXT_TYPE_LNG_GERMAN:
                        str = "Γερμανικά";
                        break;
                    case Constant.TEXT_TYPE_LNG_PORTUGAL:
                        str = "στην πορτογαλική γλώσσα";
                        break;
                    case Constant.TEXT_TYPE_LNG_ITALIAN:
                        str = "Ιταλικά";
                        break;
                    case Constant.TEXT_TYPE_LNG_HOLLAND:
                        str = "Ολλανδικά";
                        break;
                    case Constant.TEXT_TYPE_LNG_GREEK:
                        str = "Ελληνική γλώσσα";
                        break;
                    case Constant.TEXT_TYPE_LNG_INDONESIA:
                        str = "Ινδονήσιος";
                        break;
                    case Constant.TEXT_TYPE_FRIEND:
                        str = "Φίλοι";
                        break;
                    case Constant.TEXT_TYPE_SETTING:
                        str = "ρυθμίσεις";
                        break;
                    case Constant.TEXT_TYPE_SYSTEM_LNG:
                        str = "σύστημα γλώσσα";
                        break;
                    case Constant.TEXT_TYPE_ABOUT_DEVICE:
                        str = "Για τον εξοπλισμό";
                        break;
                    case Constant.TEXT_TYPE_CHOOSE:
                        str = "επιλογή";
                        break;
                    case Constant.TEXT_TYPE_SEARCHING:
                        str = "ψάχνει...";
                        break;
                    case Constant.TEXT_TYPE_WIFI_CLOSE:
                        str = "WiFi κλείσιμο";
                        break;
                    case Constant.TEXT_TYPE_PWD_LENGTH_8:
                        str = "τον κωδικό διάρκεια δεν μπορεί να είναι μικρότερη από 8";
                        break;
                    case Constant.TEXT_TYPE_SEARCH_COMPLETE:
                        str = "Η έρευνα ολοκληρώθηκε.";
                        break;
                    case Constant.TEXT_TYPE_CONNECTED:
                        str = "Ήδη με την";
                        break;
                    case Constant.TEXT_TYPE_CONNECTING:
                        str = "Σύνδεση...";
                        break;
                    case Constant.TEXT_TYPE_MODIFY_IDENTIFY:
                        str = "Είναι επαλήθευσης της ταυτότητας των πληροφοριών...";
                        break;
                    case Constant.TEXT_TYPE_GET_IP_ADDRESS:
                        str = "Είναι για την διεύθυνση IP...";
                        break;
                    case Constant.TEXT_TYPE_ENCIPHER:
                        str = "κρυπτογράφηση";
                        break;
                    case Constant.TEXT_TYPE_OPEN_NETWORK:
                        str = "Ανοικτό δίκτυο";
                        break;
                    case Constant.TEXT_TYPE_INPUT_PWD:
                        str = "Εισάγετε κωδικό";
                        break;
                    case Constant.TEXT_TYPE_PASSWORD:
                        str = "τον κωδικό";
                        break;
                    case Constant.TEXT_TYPE_WIFI_CONN:
                        str = "WiFi σύνδεση";
                        break;
                    case Constant.TEXT_TYPE_SELECT_NEAR_WLAN:
                        str = "Η επιλογή WLAN κοντά...";
                        break;
                    case Constant.TEXT_TYPE_MOTHER_TONGUE_CHOOSE:
                        str = "γλώσσα επιλογής";
                        break;
                    case Constant.TEXT_TYPE_FORENGN_LNG_CHOOSE:
                        str = "επιλογή των ξένων γλωσσών";
                        break;
                    case Constant.TEXT_TYPE_SYSTEM_LNG_CHOOSE:
                        str = "σύστημα γλώσσα επιλογής";
                        break;
                }
                break;
            case LngConstant.LANGUAGE_INDONESIA://印尼语
                switch (textType) {
                    case Constant.TEXT_TYPE_OPEN_WIFI:
                        str = "Buka WIFI";
                        break;
                    case Constant.TEXT_NOT_IMEI:
                        str = "NO IMEI";
                        break;
                    case Constant.LANGUAGE_TOAST:
                        str = "Long press the Right Key to speak";
                        break;
                    case Constant.TEXT_CLEAN:
                        str = "Clean";
                        break;
                    case Constant.TEXT_TYPE_UPDATING:
                        str = "Memperbarui...";
                        break;
                    case Constant.TEXT_RETURN:
                        str = "Return";
                        break;
                    case Constant.TEXT_TYPE_UPDATE_FAILED:
                        str = "Update gagal:";
                        break;
                    case Constant.TEXT_TYPE_CHOOSE_WIFI:
                        str = "Pilih WIFI";
                        break;
                    case Constant.TEXT_TYPE_CLOSE_WIFI:
                        str = "Matikan WIFI";
                        break;
                    case Constant.TEXT_TYPE_OPEN_HOTSPOT:
                        str = "Buka hot spot";
                        break;
                    case Constant.TEXT_TYPE_CLOSE_HOTSPOT:
                        str = "Matikan hot spot";
                        break;
                    case Constant.TEXT_TYPE_PWD_ERROR:
                        str = "Kata sandi salah!";
                        break;
                    case Constant.TEXT_TYPE_WIFI_CONN_SUCC:
                        str = "Koneksi wifi berhasil";
                        break;
                    case Constant.TEXT_TYPE_INPUT_WIFI_PWD:
                        str = "Masukkan kata sandi wifi";
                        break;
                    case Constant.TEXT_TYPE_NO_NETWORK:
                        str = "Tidak ada jaringan";
                        break;
                    case Constant.TEXT_TYPE_FOREIGN_LANGUAGE:
                        str = "Bahasa asing";
                        break;
                    case Constant.TEXT_TYPE_MOTHER_TONGUE:
                        str = "Bahasa asli";
                        break;
                    case Constant.TEXT_TYPE_WIFI_SETTING:
                        str = "Pengaturan WIFI";
                        break;
                    case Constant.TEXT_TYPE_HOTSPOT:
                        str = "Berbagi panas";
                        break;
                    case Constant.TEXT_TYPE_SCREEN_BRIGHT:
                        str = "Kecerahan layar";
                        break;
                    case Constant.TEXT_TYPE_ABOUT_US:
                        str = "Tentang kami";
                        break;
                    case Constant.TEXT_TYPE_LNG_CHINESE:
                        str = "Orang cina";
                        break;
                    case Constant.TEXT_TYPE_LNG_ENGLISH:
                        str = "Bahasa inggris";
                        break;
                    case Constant.TEXT_TYPE_LNG_ENGLISH_US:
                        str = "英语";
                        break;
                    case Constant.TEXT_TYPE_LNG_ENGLISH_IND:
                        str = "英语";
                        break;
                    case Constant.TEXT_TYPE_LNG_YUEYU:
                        str = "Bahasa kanton";
                        break;
                    case Constant.TEXT_TYPE_LNG_JAPANESE:
                        str = "Jepang";
                        break;
                    case Constant.TEXT_TYPE_LNG_KOREAN:
                        str = "Korea";
                        break;
                    case Constant.TEXT_TYPE_LNG_THAI:
                        str = "Orang Thailand";
                        break;
                    case Constant.TEXT_TYPE_LNG_ARABIAN:
                        str = "Bahasa Arab";
                        break;
                    case Constant.TEXT_TYPE_LNG_RUSSIAN:
                        str = "Orang Rusia";
                        break;
                    case Constant.TEXT_TYPE_LNG_SPANISH:
                        str = "Bahasa spanyol";
                        break;
                    case Constant.TEXT_TYPE_LNG_VIETNAMESE:
                        str = "Orang Vietnam";
                        break;
                    case Constant.TEXT_TYPE_LNG_FRENCH:
                        str = "Prancis";
                        break;
                    case Constant.TEXT_TYPE_LNG_GERMAN:
                        str = "Bahasa jerman";
                        break;
                    case Constant.TEXT_TYPE_LNG_PORTUGAL:
                        str = "Portugis";
                        break;
                    case Constant.TEXT_TYPE_LNG_ITALIAN:
                        str = "Orang Italia";
                        break;
                    case Constant.TEXT_TYPE_LNG_HOLLAND:
                        str = "Belanda";
                        break;
                    case Constant.TEXT_TYPE_LNG_GREEK:
                        str = "Bahasa Yunani";
                        break;
                    case Constant.TEXT_TYPE_LNG_INDONESIA:
                        str = "Bahasa indonesia";
                        break;
                    case Constant.TEXT_TYPE_FRIEND:
                        str = "Teman";
                        break;
                    case Constant.TEXT_TYPE_SETTING:
                        str = "设置";
                        break;
                    case Constant.TEXT_TYPE_SYSTEM_LNG:
                        str = "系统语言";
                        break;
                    case Constant.TEXT_TYPE_ABOUT_DEVICE:
                        str = "关于设备";
                        break;
                    case Constant.TEXT_TYPE_CHOOSE:
                        str = "选择";
                        break;
                    case Constant.TEXT_TYPE_SEARCHING:
                        str = "正在搜索...";
                        break;
                    case Constant.TEXT_TYPE_WIFI_CLOSE:
                        str = "WIFI关闭";
                        break;
                    case Constant.TEXT_TYPE_PWD_LENGTH_8:
                        str = "密码长度不能小于8位";
                        break;
                    case Constant.TEXT_TYPE_SEARCH_COMPLETE:
                        str = "搜索完成";
                        break;
                    case Constant.TEXT_TYPE_CONNECTED:
                        str = "已连接";
                        break;
                    case Constant.TEXT_TYPE_CONNECTING:
                        str = "连接中...";
                        break;
                    case Constant.TEXT_TYPE_MODIFY_IDENTIFY:
                        str = "正在验证身份信息...";
                        break;
                    case Constant.TEXT_TYPE_GET_IP_ADDRESS:
                        str = "正在获取IP地址...";
                        break;
                    case Constant.TEXT_TYPE_ENCIPHER:
                        str = "加密";
                        break;
                    case Constant.TEXT_TYPE_OPEN_NETWORK:
                        str = "开放网络";
                        break;
                    case Constant.TEXT_TYPE_INPUT_PWD:
                        str = "输入密码";
                        break;
                    case Constant.TEXT_TYPE_PASSWORD:
                        str = "密码";
                        break;
                    case Constant.TEXT_TYPE_WIFI_CONN:
                        str = "WIFI连接";
                        break;
                    case Constant.TEXT_TYPE_SELECT_NEAR_WLAN:
                        str = "选取附近的WLAN...";
                        break;
                    case Constant.TEXT_TYPE_MOTHER_TONGUE_CHOOSE:
                        str = "母语选择";
                        break;
                    case Constant.TEXT_TYPE_FORENGN_LNG_CHOOSE:
                        str = "外语选择";
                        break;
                    case Constant.TEXT_TYPE_SYSTEM_LNG_CHOOSE:
                        str = "系统语言选择";
                        break;
                }
                break;


                //case LngConstant.其他国语言:    // .....这里需要往LngConstant 类添加对应的语种
                //str = "......";
                //break;

        }
        return str;
    }
}
