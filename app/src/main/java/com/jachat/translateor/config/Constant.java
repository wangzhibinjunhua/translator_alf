package com.jachat.translateor.config;

import android.os.Environment;

/**
 * Created by Perryn on 2017/8/13 0013.
 */

public class Constant {

    public static final String SP_BOOT = "ja_boot";
    public static final String SP_MOTHER_TONGUE = "ja_mother_tongue";
    public static final String SP_FOREIGN_LANGUAGE = "ja_foreign_language";
    public static final String SP_SYSTEM_LANGUAGE = "ja_system_language";

    public static final int LANGUAGE_TOAST = 909;
    public static final int TEXT_CLEAN = 910;
    public static final int TEXT_RETURN = 911;
    public static final int EVENT_MOTH_LAGUAGE = 912;

    public static final int RETURN_MIAN = 109;

    //传递母语外语
    public static final int EVENT_MOTH_LANG = 913;
    public static final int EVENT_FROG_LANG = 914;

    public static final String LED_GREEN_OFF = "echo \"0\">/sys/class/leds/green/brightness";

    //相机
    public static final int CAMERA_TYPE_1 = 1;
    public static final int CAMERA_TYPE_2 = 2;
    public static final String SP_CAMERA_FLASH = "sp_camera_flash";
    public static final String PHOTO_RECOGNITION_LUANG = "photo_recognition_luang";
    public static final int PROCESS = 1;
    public static final String CAMERA_RETURN_PATH = "return_path";
    public static final String CAMERA_PATH_VALUE2 = "path";
    public static final int DECODEING_PROCESS_FAILD = 1001;
    public static final int DECODEING_PROCESS_FINISH = 1002;
    public static final int INITLANGUAGE_PROCESS_FAILD = 1003;

    //拍译
    public static final int LNG_LEFT = 105;
    public static final int LNG_TRANSLATED = 106;
    public static final String SP_FOREIGN_LANGUAGE_TRANSLATOR = "sp_foreign_language_translator";

    public static final String XF_REPLAY_VOICE_PATH = Environment.getExternalStorageDirectory() + "/jachat/speak.wav";
    public static final String HCI_REPLAY_VOICE_PATH = Environment.getExternalStorageDirectory() + "/jachat/speak.mp3";

    public static final int EVENT_SCREEN_ON = 4;
    public static final int EVENT_NETWORK_STATE = 7;
    public static final int EVENT_BATTERY_CHANGED = 8;
    public static final int EVENT_WIFI_SIGNAL_CHANGE = 9;
    public static final int EVENT_WIFI_SCAN_LIST = 10;
    public static final int EVENT_WIFI_CONNECT_SUCCEED = 11;
    public static final int EVENT_SIM_STATE = 12;
    public static final int EVENT_PHONE_STRENGTH = 13;
    public static final int EVENT_UPGRADE_RES = 15;
    public static final int EVENT_SCREEN_OFF = 17;
    public static final int EVENT_WIFI_CONN_DISCONNECT = 18;
    public static final int EVENT_TRANS_HAN_TO_WEI = 19;
    public static final int EVENT_KEY_MOTHER_TONGUE_DOWN = 20;
    public static final int EVENT_KEY_CANCEL = 21;
    public static final int EVENT_KEY_FOREIGN_LANGUAGE_DOWN = 22;

    public static final int EVENT_REC_WEI_RESULT = 28;
    public static final int EVENT_KEY_MOTHER_TONGUE_UP = 29;
    public static final int EVENT_KEY_FOREIGN_LANGUAGE_UP = 30;
    public static final int EVENT_TRANS_WEI_TO_HAN = 32;
    public static final int EVENT_UPDATE_APP = 34;

    public static final int EVENT_TRANS_RESULT = 37;
    public static final int EVENT_WIFI_PASSWORD_ERROR = 39;
    public static final int EVENT_WIFI_CLOSE = 40;
    public static final int EVENT_UPDATE_APP_PROGRESS = 42;
    public static final int EVENT_CHARGING_UI = 43;
    public static final int EVENT_GET_IMEI = 44;
    public static final int EVENT_MOTHERTONGUE_SET_BACK = 51;
    public static final int EVENT_FOREIGNLNG_SET_BACK = 52;
    public static final int EVENT_WIFI_CONN_STATE = 53;
    public static final int EVENT_SYSTEMLNG_SET_BACK = 55;
    //最新API
    public static final int EVENT_LNG_DATA = 56;
    public static final String ACTION_LNG_DATA = "action_lng_data";
    public static final int FRAGMENT_LNG_OPNE = 57;

    public static final int TEXT_TYPE_OPEN_WIFI = 1;
    public static final int TEXT_TYPE_UPDATING = 2;
    public static final int TEXT_TYPE_UPDATE_FAILED = 3;
    public static final int TEXT_TYPE_CHOOSE_WIFI = 4;
    public static final int TEXT_TYPE_CLOSE_WIFI = 5;
    public static final int TEXT_TYPE_OPEN_HOTSPOT = 6;
    public static final int TEXT_TYPE_CLOSE_HOTSPOT = 7;
    public static final int TEXT_TYPE_PWD_ERROR = 8;
    public static final int TEXT_TYPE_WIFI_CONN_SUCC = 9;
    public static final int TEXT_TYPE_INPUT_WIFI_PWD = 10;
    public static final int TEXT_TYPE_NO_NETWORK = 11;

    public static final int TEXT_TYPE_FOREIGN_LANGUAGE = 12;
    public static final int TEXT_TYPE_MOTHER_TONGUE = 13;
    public static final int TEXT_TYPE_WIFI_SETTING = 14;
    public static final int TEXT_TYPE_HOTSPOT = 15;
    public static final int TEXT_TYPE_SCREEN_BRIGHT = 16;
    public static final int TEXT_TYPE_ABOUT_US = 17;

    public static final int TEXT_TYPE_LNG_CHINESE = 18;
    public static final int TEXT_TYPE_LNG_ENGLISH = 19;
    public static final int TEXT_TYPE_LNG_YUEYU = 20;
    public static final int TEXT_TYPE_LNG_JAPANESE = 21;
    public static final int TEXT_TYPE_LNG_KOREAN = 22;
    public static final int TEXT_TYPE_LNG_THAI = 23;
    public static final int TEXT_TYPE_LNG_ARABIAN = 24;
    public static final int TEXT_TYPE_LNG_RUSSIAN = 25;
    public static final int TEXT_TYPE_LNG_SPANISH = 26;
    public static final int TEXT_TYPE_LNG_FRENCH = 28;
    public static final int TEXT_TYPE_LNG_GERMAN = 29;
    public static final int TEXT_TYPE_LNG_PORTUGAL = 30;
    public static final int TEXT_TYPE_LNG_ITALIAN = 31;
    public static final int TEXT_TYPE_LNG_HOLLAND = 32;
    public static final int TEXT_TYPE_LNG_GREEK = 33;
    public static final int TEXT_TYPE_LNG_INDONESIA = 34;
    public static final int TEXT_TYPE_LNG_WEI = 35;
    public static final int TEXT_TYPE_FRIEND = 36;
    public static final int TEXT_TYPE_LNG_VIETNAMESE = 37;
    public static final int TEXT_TYPE_SETTING = 38;
    public static final int TEXT_TYPE_SYSTEM_LNG = 39;
    public static final int TEXT_TYPE_ABOUT_DEVICE = 40;
    public static final int TEXT_TYPE_CHOOSE = 41;
    public static final int TEXT_TYPE_SEARCHING = 42;
    public static final int TEXT_TYPE_WIFI_CLOSE = 43;
    public static final int TEXT_TYPE_PWD_LENGTH_8 = 44;
    public static final int TEXT_TYPE_SEARCH_COMPLETE = 45;
    public static final int TEXT_TYPE_CONNECTED = 46;
    public static final int TEXT_TYPE_CONNECTING = 47;
    public static final int TEXT_TYPE_MODIFY_IDENTIFY = 48;
    public static final int TEXT_TYPE_GET_IP_ADDRESS = 49;
    public static final int TEXT_TYPE_ENCIPHER = 50;
    public static final int TEXT_TYPE_OPEN_NETWORK = 51;
    public static final int TEXT_TYPE_INPUT_PWD = 52;
    public static final int TEXT_TYPE_PASSWORD = 53;
    public static final int TEXT_TYPE_WIFI_CONN = 54;
    public static final int TEXT_TYPE_SELECT_NEAR_WLAN = 55;
    public static final int TEXT_TYPE_MOTHER_TONGUE_CHOOSE = 56;
    public static final int TEXT_TYPE_FORENGN_LNG_CHOOSE = 57;
    public static final int TEXT_TYPE_SYSTEM_LNG_CHOOSE = 58;
    public static final int TEXT_TYPE_LNG_ENGLISH_US = 59;
    public static final int TEXT_TYPE_LNG_ENGLISH_IND = 60;
    public static final int TEXT_TYPE_BROWSER = 61;
    public static final int TEXT_TYPE_CALCULATOR = 62;
    public static final int TEXT_TYPE_CLOCK = 63;
    public static final int TEXT_TYPE_DICTIONARY = 64;
    public static final int TEXT_TYPE_DOCUMENTS = 65;
    public static final int TEXT_TYPE_FACEBOOK = 66;
    public static final int TEXT_TYPE_FM = 67;
    public static final int TEXT_TYPE_MUSIC = 68;
    public static final int TEXT_TYPE_QQ = 69;
    public static final int TEXT_TYPE_RECORDER = 70;
    public static final int TEXT_TYPE_TWITTER = 71;
    public static final int TEXT_TYPE_WECHAT = 72;

    public static final int TEXT_NOT_IMEI = 1005;

    public static final int OPENT_TRANSLATER = 99;

    public static final int SELET_FRAGMENT = 102;
}
