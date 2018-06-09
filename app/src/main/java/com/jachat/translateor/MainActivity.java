package com.jachat.translateor;

import android.Manifest;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.provider.Settings;
import android.support.annotation.RequiresApi;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;

import com.jachat.translateor.adapter.MainAdapter;
import com.jachat.translateor.base.BaseActivity;
import com.jachat.translateor.config.App;
import com.jachat.translateor.config.Constant;
import com.jachat.translateor.config.KeyCode;
import com.jachat.translateor.config.LngConstant;
import com.jachat.translateor.mvp.contract.MainContract;
import com.jachat.translateor.mvp.model.ChargeBean;
import com.jachat.translateor.mvp.model.EventObject;
import com.jachat.translateor.mvp.model.TransResultBean;
import com.jachat.translateor.mvp.presenter.MainPresenter;
import com.jachat.translateor.utils.MediaPlayerManager;
import com.jachat.translateor.utils.NetworkUtils;
import com.jachat.translateor.utils.PermissionUtils;
import com.jachat.translateor.utils.PopWindowUtils;
import com.jachat.translateor.utils.SPUtils;
import com.jachat.translateor.utils.SystemUtils;
import com.jachat.translateor.utils.ToastUtils;
import com.jachat.translateor.utils.newapi.JsonUtils;
import com.jachat.translateor.utils.newapi.TranslateBean;
import com.jachatcloud.nativemethod.JaChatCloud;
import com.jachatcloud.nativemethod.JaChatCloudListener;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Locale;

import butterknife.BindView;
import butterknife.OnClick;

import static com.jachatcloud.nativemethod.NativeJNI.TYPE_FRIEND;
import static com.jachatcloud.nativemethod.NativeJNI.TYPE_LNG;
import static com.jachatcloud.nativemethod.NativeJNI.TYPE_TEXT;

public class MainActivity extends BaseActivity<MainContract.Presenter> implements
        MainContract.View, ViewPager.OnPageChangeListener, JaChatCloudListener {

    public static final String TAG = "wtf";
    @BindView(R.id.act_main_viewpager)
    ViewPager mViewPager;
    @BindView(R.id.act_main_image_wifi)
    ImageView mImageWifiSigned;
    @BindView(R.id.act_main_image_bat)
    ImageView mImageBatterySigned;
    @BindView(R.id.act_main_image_charging)
    ImageView mImageCharging;
    @BindView(R.id.act_main_image_back)
    ImageView mImageBack;
    @BindView(R.id.img_setting)
    ImageView mImgSetting;

    private MainAdapter mMainAdapter;
    private MainReceiver mMainReceiver;

    private int mFlag;
    public boolean mIsKeyOpen;

    private int mCurWifiSigned;
    private int mCurBat;
    private boolean mIsCharging;
    public static MainActivity mInstance;


    AudioManager mAudioManager;
    int mCurrentVoice;
    int mVoiceMax;
    @BindView(R.id.seekbar)
    SeekBar mSeekBar;
    @BindView(R.id.re_seek_bar)
    RelativeLayout mRelativeLayout;
    Handler mHandlerVoice;

    boolean mIsSetting;

    //最新API
    public static final String APP_KEY = "Ho5XVoCrwRR56vszx=";//初始化JachatCloud所需app_key
    public static final String APP_ID = "1527230453913020";//初始化JachatCloud所需app_id
    private JaChatCloud mJaChatCloudClass;
    private String mStrLng = "";//语言列表
    private String mStrFriend = "";// 朋友列表
    public String mFromLng = "zh_cn";//"zh_cn", "en_us"
    public String mToLng = "en_us";
    TransResultBean mBeanResult;

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected MainContract.Presenter onCreatePresenter() {
        return new MainPresenter(this, this);
    }

    @Override
    protected boolean setEventBus() {
        return true;
    }

    @Override
    protected void onResumeFragments() {
        super.onResumeFragments();
        String locale = Locale.getDefault().getLanguage();
//        Log.w("wtf", "输出当前android系统当前语言+" + locale);
//            Log.e("wtf", "当前设置的母语的与系统不一致当前母语");
//            Configuration config = getResources().getConfiguration();
//            DisplayMetrics dm = getResources().getDisplayMetrics();
//            config.locale = Locale.SIMPLIFIED_CHINESE;
//            getResources().updateConfiguration(config, dm);
//            setLang(locale);
        if (mIsSetting)
            setLang(locale);
        mIsSetting = false;
    }

    public void setLang(String local) {
        switch (local) {
            case "zh":
                App.mSysLanguage = LngConstant.LANGUAGE_CHINESE;
                SPUtils.put(this, Constant.SP_MOTHER_TONGUE, LngConstant.LANGUAGE_CHINESE);
                break;
            case "en":
                App.mSysLanguage = LngConstant.LANGUAGE_ENGLISH;
                SPUtils.put(this, Constant.SP_MOTHER_TONGUE, LngConstant.LANGUAGE_ENGLISH);
                break;
            case "en_us":
                App.mSysLanguage = LngConstant.LANGUAGE_ENGLISH_US;
                SPUtils.put(this, Constant.SP_MOTHER_TONGUE, LngConstant.LANGUAGE_ENGLISH_US);
                break;
            case "en_ind":
                App.mSysLanguage = LngConstant.LANGUAGE_ENGLISH_IND;
                SPUtils.put(this, Constant.SP_MOTHER_TONGUE, LngConstant.LANGUAGE_ENGLISH_IND);
                break;
            case LngConstant.LANGUAGE_YUEYU:
                App.mSysLanguage = LngConstant.LANGUAGE_YUEYU;
                SPUtils.put(this, Constant.SP_MOTHER_TONGUE, LngConstant.LANGUAGE_YUEYU);
                break;
            case "ja":
                App.mSysLanguage = LngConstant.LANGUAGE_JAPENESE;
                SPUtils.put(this, Constant.SP_MOTHER_TONGUE, LngConstant.LANGUAGE_JAPENESE);
                break;
            case "ko":
                App.mSysLanguage = LngConstant.LANGUAGE_KOREAN;
                SPUtils.put(this, Constant.SP_MOTHER_TONGUE, LngConstant.LANGUAGE_KOREAN);
                break;
            case "fr":
                App.mSysLanguage = LngConstant.LANGUAGE_FRENCH;
                SPUtils.put(this, Constant.SP_MOTHER_TONGUE, LngConstant.LANGUAGE_FRENCH);
                break;
            case "ru":
                App.mSysLanguage = LngConstant.LANGUAGE_RUSSIAN;
                SPUtils.put(this, Constant.SP_MOTHER_TONGUE, LngConstant.LANGUAGE_RUSSIAN);
                break;
            case "es":
                App.mSysLanguage = LngConstant.LANGUAGE_SPANISH;
                SPUtils.put(this, Constant.SP_MOTHER_TONGUE, LngConstant.LANGUAGE_SPANISH);
                break;
            case LngConstant.LANGUAGE_THAI:
                App.mSysLanguage = LngConstant.LANGUAGE_THAI;
                SPUtils.put(this, Constant.SP_MOTHER_TONGUE, LngConstant.LANGUAGE_THAI);
                break;
            case "vi":
                App.mSysLanguage = LngConstant.LANGUAGE_VIETNAMESE;
                SPUtils.put(this, Constant.SP_MOTHER_TONGUE, LngConstant.LANGUAGE_VIETNAMESE);
                break;
            case "ar":
                App.mSysLanguage = LngConstant.LANGUAGE_ARABIAN;
                SPUtils.put(this, Constant.SP_MOTHER_TONGUE, LngConstant.LANGUAGE_ARABIAN);
                break;
            case "pt":
                App.mSysLanguage = LngConstant.LANGUAGE_PORTUGAL;
                SPUtils.put(this, Constant.SP_MOTHER_TONGUE, LngConstant.LANGUAGE_PORTUGAL);
                break;
            case LngConstant.LANGUAGE_GERMAN://系统三个德国语言都是de
                App.mSysLanguage = LngConstant.LANGUAGE_GERMAN;
                SPUtils.put(this, Constant.SP_MOTHER_TONGUE, LngConstant.LANGUAGE_GERMAN);
                break;
            case "it":
                App.mSysLanguage = LngConstant.LANGUAGE_ITALIAN;
                SPUtils.put(this, Constant.SP_MOTHER_TONGUE, LngConstant.LANGUAGE_ITALIAN);
                break;
            case "nl":
                App.mSysLanguage = LngConstant.LANGUAGE_HOLLAND;
                SPUtils.put(this, Constant.SP_MOTHER_TONGUE, LngConstant.LANGUAGE_HOLLAND);
                break;
            case LngConstant.LANGUAGE_GREEK:
                App.mSysLanguage = LngConstant.LANGUAGE_GREEK;
                SPUtils.put(this, Constant.SP_MOTHER_TONGUE, LngConstant.LANGUAGE_GREEK);
                break;
            default://如果没有执行、默认选英文
                App.mSysLanguage = LngConstant.LANGUAGE_ENGLISH;
                SPUtils.put(this, Constant.SP_MOTHER_TONGUE, LngConstant.LANGUAGE_GREEK);
                break;
        }
    }

    @Override
    public void onState(final int loginState, final String toastStr) {//是否登录成功  2登录成功 、 8对应登录的 6 种状态
        Log.e(TAG, loginState + "   " + toastStr);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
//                mTxtToState.sett
                if (!toastStr.equals("Connect Close!")) {
                    ToastUtils.showShortToast(MainActivity.this, toastStr);
                }
            }
        });
    }

    @Override
    public void receiveMessage(int type, final String content) {
        Log.e(TAG, "type = " + type + "  content = " + content);
        if (type == TYPE_LNG) {//初始化成功返回语言列表(以json字符串格式返回)
           App.mLngStr =  mStrLng = content;
        } else if (type == TYPE_FRIEND) {//初始化成功返回好友和群列表(以json字符串格式返回)
            EventBus.getDefault().post(new EventObject(Constant.EVENT_LNG_DATA, mStrLng));//传递语言列表
            mStrFriend = content;
        } else if (type == TYPE_TEXT) {//文本类型
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    TranslateBean bean = JsonUtils.fromJson(content, TranslateBean.class);
                    String filename = getFilename(bean.lngcode);
                    if (bean == null) return;
                    Log.w(TAG, bean.lngcode + "文本+" + bean.text + "type=" + bean.type);
                    if ("src".equals(bean.type)) {//翻译成功 原文
                        mBeanResult = new TransResultBean();
                        mBeanResult.fromText = bean.text;
                    } else {//译文
                        mBeanResult.toText = bean.text;
                        mBeanResult.filename = filename;
                        EventBus.getDefault().post(new EventObject(Constant.EVENT_TRANS_RESULT, mBeanResult));
                        mViewPager.setCurrentItem(1,false);
                    }

                }
            });
        }
    }

    @Override
    public void onError(String s, final String s1) {
        Log.e(TAG, s + "   " + s1);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ToastUtils.showShortToast(MainActivity.this, s1);
            }
        });
    }


    @Override
    protected void initData() {
        PermissionUtils.requestPermissionsResult(this, 1, new String[]{    //权限提醒
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.RECORD_AUDIO,
                        Manifest.permission.READ_PHONE_STATE}
                , new PermissionUtils.OnPermissionListener() {
                    @Override
                    public void onPermissionGranted() {
                        //Log.e("Perryn"," ====  000000000000000000000  == ");
                        //jaChatCloudClass = new JaChatCloud(MainActivity.this, APP_KEY, APP_ID, MainActivity.this);
                    }

                    @Override
                    public void onPermissionDenied() {
                        PermissionUtils.showTipsDialog(MainActivity.this);
                    }
                }
        );

        mJaChatCloudClass = new JaChatCloud(MainActivity.this, APP_KEY, APP_ID, MainActivity.this);//登录、获取数据

        Logger.e("当前版本：" + SystemUtils.getVersionCode(this));
        setSwipeBackEnable(false);
        mInstance = this;
        mMainAdapter = new MainAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mMainAdapter);
        mViewPager.setCurrentItem(0);
        mViewPager.addOnPageChangeListener(this);


        mFromLng = (String) SPUtils.get(this, Constant.SP_MOTHER_TONGUE, LngConstant.LANGUAGE_CHINESE);
        mToLng = (String) SPUtils.get(this, Constant.SP_FOREIGN_LANGUAGE, LngConstant.LANGUAGE_ENGLISH);
        App.mSysLanguage = (String) SPUtils.get(this, Constant.SP_MOTHER_TONGUE, LngConstant.LANGUAGE_CHINESE);


        registerBroadcast();

        mAudioManager = (AudioManager) getSystemService(this.AUDIO_SERVICE);
        mCurrentVoice = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        mVoiceMax = mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        //Logger.w("输出系统音量+" + mAudioManager + "目前音量+" + mCurrentVoice + "最大音量+" + mVoiceMax);
        mHandlerVoice = new Handler();

    }



    @Override
    public void onBackPressedSupport() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mMainReceiver);
        mJaChatCloudClass.releaseCloud();
    }

    private void registerBroadcast() {
        if (mMainReceiver != null)
            return;
        mMainReceiver = new MainReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        filter.addAction(Intent.ACTION_SCREEN_ON);
        filter.addAction(Intent.ACTION_BATTERY_CHANGED);
        filter.addAction(WifiManager.RSSI_CHANGED_ACTION);
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        filter.addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION);
        filter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION);
        filter.addAction(WifiManager.SUPPLICANT_STATE_CHANGED_ACTION);

        filter.addAction(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);

        filter.addAction("com.jachat.action.MOTHER_TONGUE_D");
        filter.addAction("com.jachat.action.MOTHER_TONGUE_U");
        filter.addAction("com.jachat.action.REPLAY_D");
        filter.addAction("com.jachat.action.REPLAY_U");
        filter.addAction("com.jachat.action.FOREIGN_LANGUAGE_D");
        filter.addAction("com.jachat.action.FOREIGN_LANGUAGE_U");
        registerReceiver(mMainReceiver, filter);
    }

    private final int PROGRESS_DIALOG = 1;
    private ProgressDialog progressDialog = null;

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case PROGRESS_DIALOG:
                //this表示该对话框是针对当前Activity的
                progressDialog = new ProgressDialog(this);
                //设置最大值为100
                progressDialog.setMax(100);
                progressDialog.setCanceledOnTouchOutside(false);
                //设置进度条风格STYLE_HORIZONTAL
                progressDialog.setProgressStyle(
                        ProgressDialog.STYLE_HORIZONTAL);
                progressDialog.setTitle(App.getSysText(Constant.TEXT_TYPE_UPDATING));
                break;
        }
        return progressDialog;
    }

    @Override
    public void onPrepareDialog(int id, Dialog dialog) {
        switch (id) {
            case PROGRESS_DIALOG:
                //将进度条清0
                progressDialog.incrementProgressBy(-progressDialog.getProgress());
                break;
        }
    }

    private void setBatSigned(int batSigned) {
        //画电量
        int res = 0;
        if (batSigned >= 85) {
            res = R.drawable.d_4;
        } else if (batSigned >= 65) {
            res = R.drawable.d_3;
        } else if (batSigned >= 40) {
            res = R.drawable.d_2;
        } else if (batSigned >= 15) {
            res = R.drawable.d_1;
        } else {
            res = R.drawable.d_0;
        }
        mImageBatterySigned.setImageResource(res);
    }

    private void setWifiSigned(int wifiSigned) {
        //画wifi信号
        if (wifiSigned < 0) {
            if (mImageWifiSigned.getVisibility() == View.VISIBLE)
                mImageWifiSigned.setVisibility(View.GONE);
        } else {
            if (mImageWifiSigned.getVisibility() == View.GONE)
                mImageWifiSigned.setVisibility(View.VISIBLE);
        }
        int wifiRes = 0;
        switch (wifiSigned) {
            case 0:
                wifiRes = R.drawable.w_0;
                break;
            case 1:
                wifiRes = R.drawable.w_1;
                break;
            case 2:
                wifiRes = R.drawable.w_2;
                break;
            case 3:
                wifiRes = R.drawable.w_3;
                break;
            case 4:
                wifiRes = R.drawable.w_4;
                break;
        }
        mImageWifiSigned.setImageResource(wifiRes);
    }

    private void getSleepCount() {
        if (mCurBat >= 85) {
            mSleepCount = 4;
        } else if (mCurBat >= 65) {
            mSleepCount = 3;
        } else if (mCurBat >= 40) {
            mSleepCount = 2;
        } else if (mCurBat >= 15) {
            mSleepCount = 1;
        } else {
            mSleepCount = 0;
        }
    }

    private void setChargeSigned(ChargeBean bean) {
        if (bean.isCharging) {//正在充电
            if (mImageCharging.getVisibility() == View.GONE) {
                mImageCharging.setVisibility(View.VISIBLE);
            }
            if (bean.curElectric < 85) {

                mCurBat = bean.curElectric;
                getSleepCount();
                if (isBatThreadStart) return;
                isBatThreadStart = true;
                mBatHandler.postDelayed(mBatRunnable, 0);
            } else {
                mCurBat = bean.curElectric;
                setBatSigned(mCurBat);
                if (isBatThreadStart) {
                    isBatThreadStart = false;
                    mBatHandler.removeCallbacks(mBatRunnable);
                }
            }
        } else {
            if (mImageCharging.getVisibility() == View.VISIBLE) {
                mImageCharging.setVisibility(View.GONE);
            }
            setBatSigned(bean.curElectric);
        }
    }

    private boolean isBatThreadStart = false;
    private Handler mBatHandler = new Handler();
    private int mSleepCount = 0;

    /**
     * 充量线程
     */
    private Runnable mBatRunnable = new Runnable() {
        @Override
        public void run() {
            if (mIsCharging) {
                int bat = 0;
                mSleepCount++;
                if (mSleepCount > 4) {
                    getSleepCount();
                }
                switch (mSleepCount) {
                    case 0:
                        bat = 5;
                        break;
                    case 1:
                        bat = 25;
                        break;
                    case 2:
                        bat = 55;
                        break;
                    case 3:
                        bat = 80;
                        break;
                    case 4:
                        bat = 90;
                        break;
                }
                EventBus.getDefault().post(new EventObject(Constant.EVENT_CHARGING_UI, bat));
                mBatHandler.postDelayed(this, 1000);
            }
        }
    };


    /**
     * Evnet事件接收
     *
     * @param event
     */
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(EventObject event) {
        switch (event.eventType) {
            case Constant.EVENT_NETWORK_STATE://网络状态发生变化
                break;
            case Constant.EVENT_BATTERY_CHANGED://电池电量发生变化
                ChargeBean bean = (ChargeBean) event.obj;
                mIsCharging = bean.isCharging;
                setChargeSigned(bean);
                break;
            case Constant.EVENT_WIFI_SIGNAL_CHANGE://wifi信号发生变化
                int streng = (int) event.obj;
                if (streng != mCurWifiSigned) {
                    mCurWifiSigned = streng;
                    //Logger.w("检测到wifi信号强度的变化+"+mCurWifiSigned);
                    setWifiSigned(mCurWifiSigned);
                }
                break;
            case Constant.EVENT_UPDATE_APP:
                mFlag = (int) event.obj;
                switch (mFlag) {
                    case 0:
                        showDialog(PROGRESS_DIALOG);
                        break;
                    case 1://网络异常
                    case 2://下载失败
                    case 3://保存本地失败
                    case 4://安装失败
                        progressDialog.hide();
                        ToastUtils.showShortToast(this, App.getSysText(Constant.TEXT_TYPE_UPDATE_FAILED) + mFlag);
                        break;
                }
                break;
            case Constant.EVENT_UPDATE_APP_PROGRESS:
                progressDialog.incrementProgressBy(1);
                break;
            case Constant.EVENT_GET_IMEI:
                break;
            case Constant.EVENT_KEY_MOTHER_TONGUE_DOWN://按下母语键
                break;
            case Constant.EVENT_KEY_MOTHER_TONGUE_UP://放开母语键
                break;
            case Constant.EVENT_KEY_FOREIGN_LANGUAGE_DOWN://按下外语键
                break;
            case Constant.EVENT_KEY_FOREIGN_LANGUAGE_UP://放开外语键
                break;
            case Constant.EVENT_KEY_CANCEL:
                break;
            case Constant.EVENT_SCREEN_OFF:
                break;
            case Constant.EVENT_WIFI_CLOSE:
                break;
            case KeyCode.KEY_BACK:
                mViewPager.setCurrentItem(0,false);
                break;
            case Constant.OPENT_TRANSLATER:
                mViewPager.setCurrentItem(1, false);
                EventBus.getDefault().post(new EventObject(Constant.EVENT_MOTH_LAGUAGE, null));
                break;

            case Constant.EVENT_MOTH_LANG://设置母语
                mFromLng = (String) event.obj;
                break;
            case Constant.EVENT_FROG_LANG://设置外语
                mToLng = (String) event.obj;
                break;
            case KeyCode.KEY_HOME:
                mViewPager.setCurrentItem(0, false);
                break;
            case Constant.RETURN_MIAN:
                mViewPager.setCurrentItem(0, false);
                break;
            case Constant.FRAGMENT_LNG_OPNE:

                break;
        }
    }


    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {

    }

    @Override
    public void onPageScrollStateChanged(int i) {
//        Logger.w("页面发生改变" + i);
        mIsOpenTranslater = true;

    }

    @OnClick({R.id.act_main_image_back, R.id.img_setting})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.act_main_image_back:
                //mViewPager.setCurrentItem(1);
                //EventBus.getDefault().post(new EventObject(KeyCode.KEY_BACK, null)); //del by wzb
                moveTaskToBack(true);//add by wzb
                break;
            case R.id.img_setting:
//                Intent intent = new Intent();
//                intent.setAction("com.android.settings/com.android.settings.Settings");
                Intent intent = new Intent(Settings.ACTION_SETTINGS);
                startActivity(intent);
                mIsSetting = true;
                break;

        }
    }


    boolean mIsOpenTranslater = true;
    boolean mIsFragmentTwo = true;

    @Override
    protected void onResume() {
        super.onResume();
        mIsOpenTranslater = true;
    }

    private boolean mIsMotherTongueDown = false;
    private boolean mIsForeignLngDown = false;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //Logger.e("onKeyDown keyCode = " + keyCode);
        if (keyCode == KeyCode.KEY_MOTHER_TONGUE) {
            if (mIsMotherTongueDown) return true;
            mIsMotherTongueDown = true;
            Logger.e("母语按下+mFromLng"+mFromLng);
            mJaChatCloudClass.voiceTranslate(mFromLng, mToLng);
            ToastUtils.showLongToast(this,"请说话");
        } else if (keyCode == KeyCode.KEY_FOREIGN_LANGUAGE) {//外语键
            if (mIsForeignLngDown) return true;
            mIsForeignLngDown = true;
            Logger.w("外语按下+mToLng"+mToLng);
            mJaChatCloudClass.voiceTranslate(mToLng, mFromLng);
            ToastUtils.showLongToast(this,"请说话");
        } else if (keyCode == KeyCode.KEY_BACK) {//返回事件
            EventBus.getDefault().post(new EventObject(Constant.OPENT_TRANSLATER, null));
            mIsOpenTranslater = true;
            EventBus.getDefault().post(new EventObject(KeyCode.KEY_BACK, null));
            moveTaskToBack(true);//add by wzb
        } else if (keyCode == KeyCode.KEY_MOTHER_TONGUE_LEFT_DOWN) {
            if (mCurrentVoice < mVoiceMax) {
                mCurrentVoice++;
                mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, mCurrentVoice, 0);
            }
            MediaPlayerManager.getInstance().playSound(MainActivity.this, MediaPlayerManager.TYPE_SOUND_KEY_PRESS);

            if (mRelativeLayout.getVisibility() != View.VISIBLE)
                mRelativeLayout.setVisibility(View.VISIBLE);
            mSeekBar.setProgress(mCurrentVoice);
            if (mRunnable != null)
                mHandlerVoice.removeCallbacks(mRunnable);
            mHandlerVoice.postDelayed(mRunnable, 2000);

        } else if (keyCode == KeyCode.KEY_MOTHER_TONGUE_LEFT_UP) {
            if (mCurrentVoice > 0) {
                mCurrentVoice--;
                mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, mCurrentVoice, 0);
            }
            MediaPlayerManager.getInstance().playSound(MainActivity.this, MediaPlayerManager.TYPE_SOUND_KEY_PRESS);

            if (mRelativeLayout.getVisibility() != View.VISIBLE)
                mRelativeLayout.setVisibility(View.VISIBLE);
            mSeekBar.setProgress(mCurrentVoice);
            if (mRunnable != null)
                mHandlerVoice.removeCallbacks(mRunnable);
            mHandlerVoice.postDelayed(mRunnable, 2000);

        }
        return true;
    }

    Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            mRelativeLayout.setVisibility(View.GONE);
        }
    };



    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        //Logger.e("onKeyUp keyCode = " + keyCode);
        switch (keyCode) {
            //case KeyCode.KEY_MOTHER_TONGUE_LEFT_UP:
            case KeyCode.KEY_MOTHER_TONGUE:
                mIsMotherTongueDown = false;
                mJaChatCloudClass.stopVoiceTranslate();
                Logger.w("母语抬起");
                break;

            //case KeyCode.KEY_MOTHER_TONGUE_LEFT_DOWN:
            case KeyCode.KEY_FOREIGN_LANGUAGE:
                mIsForeignLngDown = false;
                mJaChatCloudClass.stopVoiceTranslate();
                Logger.w("外语抬起");
                break;
        }
        return true;
    }


    private String getFilename(String language) {
        String filename = "";
        switch (language) {
            case LngConstant.LANGUAGE_VIETNAMESE://越南语
            case LngConstant.LANGUAGE_YUEYU://粤语
            case LngConstant.LANGUAGE_CHINESE://普通话
            case LngConstant.LANGUAGE_ENGLISH://英语
            case LngConstant.LANGUAGE_FRENCH://法语
            case LngConstant.LANGUAGE_RUSSIAN://俄语
            case LngConstant.LANGUAGE_SPANISH://西班牙语
                filename = Environment.getExternalStorageDirectory() + "/jachat/" + System.currentTimeMillis() + ".wav";
                break;
            case LngConstant.LANGUAGE_JAPENESE://日语
            case LngConstant.LANGUAGE_KOREAN://韩语
            case LngConstant.LANGUAGE_THAI://泰语
            case LngConstant.LANGUAGE_ARABIAN://阿拉伯语
            case LngConstant.LANGUAGE_PORTUGAL://葡萄牙语
            case LngConstant.LANGUAGE_GERMAN://德语
            case LngConstant.LANGUAGE_ITALIAN://意大利语
            case LngConstant.LANGUAGE_HOLLAND://荷兰语
            case LngConstant.LANGUAGE_GREEK://希腊语
//            case LngConstant.LANGUAGE_WEI://维吾尔语
            case LngConstant.LANGUAGE_INDONESIA://印尼语
                filename = Environment.getExternalStorageDirectory() + "/jachat/" + System.currentTimeMillis() + ".mp3";
                break;
        }
        return filename;
    }

}
