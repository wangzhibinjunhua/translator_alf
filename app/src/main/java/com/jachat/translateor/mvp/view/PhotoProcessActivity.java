package com.jachat.translateor.mvp.view;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.jachat.translateor.R;
import com.jachat.translateor.base.BaseActivity;
import com.jachat.translateor.config.App;
import com.jachat.translateor.config.Constant;
import com.jachat.translateor.config.KeyCode;
import com.jachat.translateor.config.LngConstant;
import com.jachat.translateor.mvp.contract.TransContract;
import com.jachat.translateor.mvp.model.EventObject;
import com.jachat.translateor.mvp.presenter.TransPresenter;
import com.jachat.translateor.utils.SPUtils;
import com.jachat.translateor.utils.mytext.AdaptableTextView;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

//照片显示页面
public class PhotoProcessActivity extends BaseActivity<TransContract.Presenter> implements View.OnClickListener, TransContract.View {

    @BindView(R.id.photo_imageview)
    ImageView photoImageView;

    @BindView(R.id.img_language_left)
    TextView mTxLanguageLeft;
    @BindView(R.id.img_language_right)
    TextView mTxLanguageRight;


    //选择语言
    String mLuangeLeft = LngConstant.LANGUAGE_CHINESE;
    String mLuangeRight = LngConstant.LANGUAGE_ENGLISH;

    private String path = "";
    List<String> mList;

    //图片识别
    ProgressDialog mypDialog;
    AlertDialog.Builder failedDialog2;
    AlertDialog.Builder setLanguageDialog;
    AlertDialog.Builder failedDialog3;


    String orignalTextTranslter = "";
    public Handler mHandler = new MainHandler();
    //ViewStub加载翻译页面
    View mShowTranslationLayout;
    TextView mTxOriginal;
    TextView mTxTranslation;
    Button mBtnTakePicture;

    List<String> mTranslaterTxList;

    @Override
    protected void initData() {
        String str = (String) SPUtils.get(this, Constant.SP_MOTHER_TONGUE, App.mSysLanguage);
        mLuangeLeft = str;
        setLeftLng(str, mTxLanguageLeft);
        String strForgth = (String) SPUtils.get(this, Constant.SP_FOREIGN_LANGUAGE_TRANSLATOR, LngConstant.LANGUAGE_ENGLISH);
        mLuangeRight = strForgth;
        setRightLng(strForgth, mTxLanguageRight);
        setLuangData();
        createDialog();
        path = "/sdcard/dyk" + "test_one" + ".jpg";
        // mBitmap = Compressor.getDefault(this).compressToBitmap(new File(path));
        //mBitmap = BitmapUtils.getBitmapFromPath(path);

//        mBitmap = BitmapUtils.rotateBitmap(mBitmap, 0);//旋转
//        photoImageView.setImageBitmap(mBitmap);
//        photoImageView.setVisibility(View.VISIBLE);

        inflaterViewStu();

    }

    @OnClick({R.id.img_return, R.id.img_home, R.id.img_camera, R.id.img_language_left, R.id.img_language_right})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.takePic_two://
                mShowTranslationLayout.setVisibility(View.GONE);
                break;

            case R.id.img_camera:
                mTxTranslation.setText(" ");
                break;

            case R.id.img_return:
                onBackPressedSupport();
                break;
            case R.id.img_home:
                EventBus.getDefault().post(new EventObject(Constant.RETURN_MIAN,null));
                finish();
                break;
            case R.id.img_language_left:
                Intent intentLanguageLeft = new Intent(PhotoProcessActivity.this, LanguageSetActivity.class);
                intentLanguageLeft.putExtra("type", 8);
                intentLanguageLeft.putExtra("lng", mLuangeLeft);
                intentLanguageLeft.putExtra("lng2", mLuangeRight);
                startActivity(intentLanguageLeft);
                break;
            case R.id.img_language_right:
                Intent intentLanguageRight = new Intent(PhotoProcessActivity.this, LanguageSetActivity.class);
                intentLanguageRight.putExtra("type", 9);
                intentLanguageRight.putExtra("lng", mLuangeLeft);
                intentLanguageRight.putExtra("lng2", mLuangeRight);
                startActivity(intentLanguageRight);
                break;
        }
    }


    private void getData(String curLng, TextView view) {
        switch (curLng) {
            case LngConstant.LANGUAGE_CHINESE:
                view.setText("中文");
                break;
            case LngConstant.LANGUAGE_ENGLISH:
                view.setText("English");
                break;
            case LngConstant.LANGUAGE_ENGLISH_US:
                view.setText("粤语");
                break;
            case LngConstant.LANGUAGE_ENGLISH_IND:
                view.setText("日本語");
                break;
            case LngConstant.LANGUAGE_YUEYU:
                view.setText("한국어");
                break;
            case LngConstant.LANGUAGE_JAPENESE:
                view.setText("ภาษาไทย");
                break;
            case LngConstant.LANGUAGE_KOREAN:
                view.setText("عربي");
                break;
            case LngConstant.LANGUAGE_FRENCH:
                view.setText("русский");
                break;
            case LngConstant.LANGUAGE_RUSSIAN:
                view.setText("El español");
                break;
            case LngConstant.LANGUAGE_SPANISH:
                view.setText("Los vietnamitas");
                break;
            case LngConstant.LANGUAGE_THAI:
                view.setText("Français");
                break;
            case LngConstant.LANGUAGE_VIETNAMESE:
                view.setText("Deutsch");
                break;
            case LngConstant.LANGUAGE_ARABIAN:
                view.setText("Português");
                break;
            case LngConstant.LANGUAGE_PORTUGAL:
                view.setText("");
                break;
            case LngConstant.LANGUAGE_GERMAN:
                view.setText("");
                break;
            case LngConstant.LANGUAGE_ITALIAN:
                view.setText("In Italiano");
                break;
            case LngConstant.LANGUAGE_HOLLAND:
                view.setText("De Nederlandse");
                break;
            default://如果没有执行、默认选英文
                view.setText(App.getSysText(Constant.TEXT_TYPE_LNG_CHINESE));
                break;

        }


    }

    public void setLeftLng(String local, TextView view) {
        switch (local) {
            case LngConstant.LANGUAGE_CHINESE:
                view.setText(App.getSysText(Constant.TEXT_TYPE_LNG_CHINESE));
                break;
            case LngConstant.LANGUAGE_ENGLISH:
                view.setText(App.getSysText(Constant.TEXT_TYPE_LNG_ENGLISH));
                break;
            case LngConstant.LANGUAGE_ENGLISH_US:
                view.setText(App.getSysText(Constant.TEXT_TYPE_LNG_ENGLISH_US));
                break;
            case LngConstant.LANGUAGE_ENGLISH_IND:
                view.setText(App.getSysText(Constant.TEXT_TYPE_LNG_ENGLISH_IND));
                break;
            case LngConstant.LANGUAGE_YUEYU:
                view.setText(App.getSysText(Constant.TEXT_TYPE_LNG_YUEYU));
                break;
            case LngConstant.LANGUAGE_JAPENESE:
                view.setText(App.getSysText(Constant.TEXT_TYPE_LNG_JAPANESE));
                break;
            case LngConstant.LANGUAGE_KOREAN:
                view.setText(App.getSysText(Constant.TEXT_TYPE_LNG_KOREAN));
                break;
            case LngConstant.LANGUAGE_FRENCH:
                view.setText(App.getSysText(Constant.TEXT_TYPE_LNG_FRENCH));
                break;
            case LngConstant.LANGUAGE_RUSSIAN:
                view.setText(App.getSysText(Constant.TEXT_TYPE_LNG_RUSSIAN));
                break;
            case LngConstant.LANGUAGE_SPANISH:
                view.setText(App.getSysText(Constant.TEXT_TYPE_LNG_SPANISH));
                break;
            case LngConstant.LANGUAGE_THAI:
                view.setText(App.getSysText(Constant.TEXT_TYPE_LNG_THAI));
                break;
            case LngConstant.LANGUAGE_VIETNAMESE:
                view.setText(App.getSysText(Constant.TEXT_TYPE_LNG_VIETNAMESE));
                break;
            case LngConstant.LANGUAGE_ARABIAN:
                view.setText(App.getSysText(Constant.TEXT_TYPE_LNG_ARABIAN));
                break;
            case LngConstant.LANGUAGE_PORTUGAL:
                view.setText(App.getSysText(Constant.TEXT_TYPE_LNG_PORTUGAL));
                break;
            case LngConstant.LANGUAGE_GERMAN:
                view.setText(App.getSysText(Constant.TEXT_TYPE_LNG_GERMAN));
                break;
            case LngConstant.LANGUAGE_ITALIAN:
                view.setText(App.getSysText(Constant.TEXT_TYPE_LNG_ITALIAN));
                break;
            case LngConstant.LANGUAGE_HOLLAND:
                view.setText(App.getSysText(Constant.TEXT_TYPE_LNG_HOLLAND));
                break;
            case LngConstant.LANGUAGE_GREEK:
                view.setText(App.getSysText(Constant.TEXT_TYPE_LNG_GREEK));
                break;
            default://如果没有执行、默认选英文
                view.setText(App.getSysText(Constant.TEXT_TYPE_LNG_CHINESE));
                break;
        }
    }


    public void setRightLng(String local, TextView view) {
        switch (local) {
            case LngConstant.LANGUAGE_CHINESE:
                view.setText(App.getSysText(Constant.TEXT_TYPE_LNG_CHINESE));
                break;
            case LngConstant.LANGUAGE_ENGLISH:
                view.setText(App.getSysText(Constant.TEXT_TYPE_LNG_ENGLISH));
                break;
            case LngConstant.LANGUAGE_ENGLISH_US:
                view.setText(App.getSysText(Constant.TEXT_TYPE_LNG_ENGLISH_US));
                break;
            case LngConstant.LANGUAGE_ENGLISH_IND:
                view.setText(App.getSysText(Constant.TEXT_TYPE_LNG_ENGLISH_IND));
                break;
            case LngConstant.LANGUAGE_YUEYU:
                view.setText(App.getSysText(Constant.TEXT_TYPE_LNG_YUEYU));
                break;
            case LngConstant.LANGUAGE_JAPENESE:
                view.setText(App.getSysText(Constant.TEXT_TYPE_LNG_JAPANESE));
                break;
            case LngConstant.LANGUAGE_KOREAN:
                view.setText(App.getSysText(Constant.TEXT_TYPE_LNG_KOREAN));
                break;
            case LngConstant.LANGUAGE_FRENCH:
                view.setText(App.getSysText(Constant.TEXT_TYPE_LNG_FRENCH));
                break;
            case LngConstant.LANGUAGE_RUSSIAN:
                view.setText(App.getSysText(Constant.TEXT_TYPE_LNG_RUSSIAN));
                break;
            case LngConstant.LANGUAGE_SPANISH:
                view.setText(App.getSysText(Constant.TEXT_TYPE_LNG_SPANISH));
                break;
            case LngConstant.LANGUAGE_THAI:
                view.setText(App.getSysText(Constant.TEXT_TYPE_LNG_THAI));
                break;
            case LngConstant.LANGUAGE_VIETNAMESE:
                view.setText(App.getSysText(Constant.TEXT_TYPE_LNG_VIETNAMESE));
                break;
            case LngConstant.LANGUAGE_ARABIAN:
                view.setText(App.getSysText(Constant.TEXT_TYPE_LNG_ARABIAN));
                break;
            case LngConstant.LANGUAGE_PORTUGAL:
                view.setText(App.getSysText(Constant.TEXT_TYPE_LNG_PORTUGAL));
                break;
            case LngConstant.LANGUAGE_GERMAN:
                view.setText(App.getSysText(Constant.TEXT_TYPE_LNG_GERMAN));
                break;
            case LngConstant.LANGUAGE_ITALIAN:
                view.setText(App.getSysText(Constant.TEXT_TYPE_LNG_ITALIAN));
                break;
            case LngConstant.LANGUAGE_HOLLAND:
                view.setText(App.getSysText(Constant.TEXT_TYPE_LNG_HOLLAND));
                break;
            case LngConstant.LANGUAGE_GREEK:
                view.setText(App.getSysText(Constant.TEXT_TYPE_LNG_GREEK));
                break;
            default://如果没有执行、默认选英文
                view.setText(App.getSysText(Constant.TEXT_TYPE_LNG_CHINESE));
                break;
        }
    }


    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyCode.KEY_BACK
                && event.getRepeatCount() == 0) {
            Intent intent = new Intent();
            intent.putExtra(Constant.CAMERA_PATH_VALUE2, path);
            setResult(0, intent);
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected int getLayout() {
        return R.layout.photo_activity;
    }

    @Override
    protected TransContract.Presenter onCreatePresenter() {
        return new TransPresenter(this, this);
    }

    @Override
    protected boolean setEventBus() {
        return true;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(EventObject event) {
        switch (event.eventType) {
            case Constant.LNG_LEFT:
               mLuangeLeft = (String) event.obj;
                setLeftLng(mLuangeLeft,mTxLanguageLeft);
                Logger.w("输出+"+mLuangeLeft);
                break;

            case Constant.LNG_TRANSLATED:
                mLuangeRight = (String) event.obj;
                setLeftLng(mLuangeRight, mTxLanguageRight);
                Logger.w("输出+"+mLuangeRight);
                break;

        }
    }


    public void inflaterViewStu() {
        //加载隐藏的ViewStub布局
        ViewStub stub = (ViewStub) findViewById(R.id.vs_act_camera_surface_translation);
        if (mShowTranslationLayout == null) {
            mShowTranslationLayout = stub.inflate();
        }
        mTxOriginal = (AdaptableTextView) mShowTranslationLayout.findViewById(R.id.tv_original_tx);
        mTxTranslation = (AdaptableTextView) mShowTranslationLayout.findViewById(R.id.tv_translation_tx);
        mBtnTakePicture = (Button) mShowTranslationLayout.findViewById(R.id.takePic_two);
        mBtnTakePicture.setOnClickListener(this);
        if (mShowTranslationLayout != null) {
            mShowTranslationLayout.setVisibility(View.VISIBLE);
            return;
        }

    }

    /**
     * 调用翻译Api
     */
    public void showTranslationResults() {
        Logger.w("将要翻译的文字+" + orignalTextTranslter);
        if (mTranslaterTxList.size() != 0) {
            mTranslaterTxList.remove(0);
        }
    }

    public void setLuangData() {
        mList = new ArrayList<>();
        mList.add(App.getSysText(Constant.TEXT_TYPE_LNG_CHINESE));
        mList.add(App.getSysText(Constant.TEXT_TYPE_LNG_ENGLISH));
        //list.add(new LanguageBean(LngConstant.LANGUAGE_YUEYU, App.getSysText(Constant.TextType.YUEYU)));
        mList.add(App.getSysText(Constant.TEXT_TYPE_LNG_JAPANESE));
        mList.add(App.getSysText(Constant.TEXT_TYPE_LNG_KOREAN));
        //list.add(new LanguageBean(LngConstant.LANGUAGE_THAI, App.getSysText(Constant.TextType.THAI)));
        //list.add(new LanguageBean(LngConstant.LANGUAGE_ARABIAN, App.getSysText(Constant.TextType.ARABIAN)));
        mList.add(App.getSysText(Constant.TEXT_TYPE_LNG_RUSSIAN));
        mList.add(App.getSysText(Constant.TEXT_TYPE_LNG_SPANISH));
        // list.add(new LanguageBean(LngConstant.LANGUAGE_VIETNAMESE, App.getSysText(Constant.TextType.VIETNAMESE)));
        mList.add(App.getSysText(Constant.TEXT_TYPE_LNG_FRENCH));
        mList.add(App.getSysText(Constant.TEXT_TYPE_LNG_GERMAN));
        mList.add(App.getSysText(Constant.TEXT_TYPE_LNG_PORTUGAL));
        mList.add(App.getSysText(Constant.TEXT_TYPE_LNG_ITALIAN));
        mList.add(App.getSysText(Constant.TEXT_TYPE_LNG_HOLLAND));
    }

    public void createDialog() {
        mypDialog = new ProgressDialog(this);
        mypDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        //mypDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        mypDialog.setProgressDrawable(getResources().getDrawable(R.drawable.progressbar));
        mypDialog.setMessage("识别中...");//识别引擎工作中
        mypDialog.setIndeterminate(false);
        mypDialog.setCancelable(true);


        failedDialog2 = new AlertDialog.Builder(this);
        failedDialog2.setTitle("Im Sorry");//对不起
        failedDialog2.setMessage("识别失败");//识别失败
        failedDialog2.setPositiveButton("返回", new DialogInterface.OnClickListener() {//返回
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub

            }
        });
        failedDialog2.create();

        //failedDialog1.create();

        setLanguageDialog = new AlertDialog.Builder(this);
        setLanguageDialog.setTitle("选择语言");
        setLanguageDialog.setIcon(android.R.drawable.ic_dialog_alert);
        return;
    }

    StringBuffer stringBuffer = new StringBuffer();



    //得到消息进行相关处理
    private class MainHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case Constant.DECODEING_PROCESS_FAILD:
                    if (mypDialog != null && mypDialog.isShowing()) {
                        mypDialog.dismiss();
                    }
                    failedDialog2.show();
                    break;

                case Constant.INITLANGUAGE_PROCESS_FAILD:
                    if (mypDialog != null && mypDialog.isShowing()) {
                        mypDialog.dismiss();
                    }
                    failedDialog3.show();

                    break;
                case Constant.DECODEING_PROCESS_FINISH:
                    if (mypDialog != null && mypDialog.isShowing()) {
                        mypDialog.dismiss();
                    }
                    //得到图片文字调用翻译API
                    if (orignalTextTranslter == null) {
                        Logger.w("图片识别为null");
                        failedDialog2.show();
                    } else
                        showTranslationResults();
                    break;

                default:
                    break;

            }
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
