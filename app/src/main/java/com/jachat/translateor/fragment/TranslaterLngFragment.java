package com.jachat.translateor.fragment;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jachat.translateor.MainActivity;
import com.jachat.translateor.R;
import com.jachat.translateor.base.BaseFragment;
import com.jachat.translateor.base.BasePresenter;
import com.jachat.translateor.config.App;
import com.jachat.translateor.config.Constant;
import com.jachat.translateor.config.LngConstant;
import com.jachat.translateor.mvp.model.EventObject;
import com.jachat.translateor.mvp.view.LanguageSetActivity;
import com.jachat.translateor.utils.SPUtils;
import com.jachat.translateor.utils.newapi.LanguageBean;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class TranslaterLngFragment extends BaseFragment {
    public static final String TAG = "wtf";
    @BindView(R.id.tx_moth_language)
    TextView mTxtMotherLanguage;
    @BindView(R.id.tx_transalter_language)
    TextView mTxtForeignLaguage;
    @BindView(R.id.img_moth_language)
    ImageView mImageMotherTongueE;
    @BindView(R.id.img_translater_language)
    ImageView mImageForeignE;
    @BindView(R.id.tx_language_toast)
    TextView mTxKeyToast;

    String mMothLng = App.mSysLanguage;
    String mFrogLng = LngConstant.LANGUAGE_ENGLISH;

    //最新API
    String mLngStr;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_translater_lng;
    }

    @Override
    protected BasePresenter onCreatePresenter() {
        return null;
    }

    @Override
    protected boolean setEventBus() {
        return true;
    }

    @Override
    public void onResume() {
        super.onResume();
        String mothStr = (String) SPUtils.get(getActivity(), Constant.SP_MOTHER_TONGUE, LngConstant.LANGUAGE_CHINESE);
        App.mSysLanguage = mothStr;
        showLngUIE(mothStr, mTxtMotherLanguage, mImageMotherTongueE);
        String forgStr = (String) SPUtils.get(getActivity(), Constant.SP_FOREIGN_LANGUAGE, LngConstant.LANGUAGE_ENGLISH);
        showForeignLngE(forgStr, mTxtForeignLaguage, mImageForeignE);

        if (App.mSysLanguage.equals(LngConstant.LANGUAGE_CHINESE) || App.mSysLanguage.equals(LngConstant.LANGUAGE_CHINESE_HK)) {
            mTxKeyToast.setText(App.getSysText(Constant.LANGUAGE_TOAST));
        } else {
            mTxKeyToast.setText("Long press the Mother Key and Foreign Key to Translate");
        }
    }

    @Override
    protected void initData() {

    }


    @OnClick({R.id.re_lng_left, R.id.re_lng_right})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.re_lng_left:
                Intent intentMotherTongue2 = new Intent(getActivity(), LanguageSetActivity.class);
                intentMotherTongue2.putExtra("type", 1);
                intentMotherTongue2.putExtra(Constant.ACTION_LNG_DATA, mLngStr);
                intentMotherTongue2.putExtra("lng", ((MainActivity) getActivity()).mFromLng);
                startActivity(intentMotherTongue2);
                break;
            case R.id.re_lng_right:
                Intent intentForeign2 = new Intent(getActivity(), LanguageSetActivity.class);
                intentForeign2.putExtra("type", 2);
                intentForeign2.putExtra(Constant.ACTION_LNG_DATA, mLngStr);
                intentForeign2.putExtra("lng", ((MainActivity) getActivity()).mToLng);
                startActivity(intentForeign2);
                break;


        }
    }


    int mForeignLngTypeTx;

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(EventObject event) {
        switch (event.eventType) {
            case Constant.EVENT_MOTHERTONGUE_SET_BACK://母语回调
                LanguageBean languageLeftBean = (LanguageBean) event.obj;
                String str = ((MainActivity) getActivity()).mFromLng = languageLeftBean.langcode;
                mMothLng = str;
                mTxtMotherLanguage.setText(languageLeftBean.memo);
                Logger.w("输出语言列表传递的数据+" + languageLeftBean.memo + "lng+" + languageLeftBean.langcode);
                showForeignLngE(((MainActivity) getActivity()).mToLng, mTxtForeignLaguage, mImageForeignE);
                App.mSysLanguage = str;//改变系统语言
                mTxtForeignLaguage.setText(App.getSysText(mForeignLngTypeTx));
                if (str.equals(LngConstant.LANGUAGE_CHINESE)) {
                    Logger.w("设置中文");
                    mTxKeyToast.setText(App.getSysText(Constant.LANGUAGE_TOAST));
                } else {
                    Logger.w("设置英文");
                    mTxKeyToast.setText("Long press the Mother Key and Foreign Key to Translate");
                }

                EventBus.getDefault().post(new EventObject(Constant.EVENT_MOTH_LAGUAGE, ((MainActivity) getActivity()).mFromLng));//传递语言

                break;
            case Constant.EVENT_FOREIGNLNG_SET_BACK://外语回调
                LanguageBean languageRightBean = (LanguageBean) event.obj;
                mFrogLng = ((MainActivity) getActivity()).mToLng = languageRightBean.langcode;
                showForeignLngE(((MainActivity) getActivity()).mToLng, mTxtForeignLaguage, mImageForeignE);
                EventBus.getDefault().post(new EventObject(Constant.EVENT_FROG_LANG, mFrogLng));//传递语言
                SPUtils.put(getActivity(), Constant.SP_FOREIGN_LANGUAGE, mFrogLng);
                break;
            case Constant.EVENT_LNG_DATA:
                Log.w(TAG, "传递语言" + (String) event.obj);
                mLngStr = (String) event.obj;
                break;
        }
    }


    public void showForeignLngE(String showLanguage, TextView textView, ImageView imageView) {
        String str = null;
        int res = 0;
        switch (showLanguage) {
            case LngConstant.LANGUAGE_CHINESE:
                str = App.getSysText(Constant.TEXT_TYPE_LNG_CHINESE);
                mForeignLngTypeTx = Constant.TEXT_TYPE_LNG_CHINESE;
                res = R.drawable.ic_chinese;
                break;
            case LngConstant.LANGUAGE_ENGLISH:
                str = App.getSysText(Constant.TEXT_TYPE_LNG_ENGLISH);
                mForeignLngTypeTx = Constant.TEXT_TYPE_LNG_ENGLISH;
                res = R.drawable.ic_english;
                break;
            case LngConstant.LANGUAGE_ENGLISH_US:
                str = App.getSysText(Constant.TEXT_TYPE_LNG_ENGLISH_US);
                mForeignLngTypeTx = Constant.TEXT_TYPE_LNG_ENGLISH_US;
                res = R.drawable.ic_english;
                break;
//            case LngConstant.LANGUAGE_ENGLISH_IND:
//                str = App.getSysText(Constant.TEXT_TYPE_LNG_ENGLISH_IND);
//                res = R.drawable.ic_english;
//                break;
            case LngConstant.LANGUAGE_YUEYU:
                str = App.getSysText(Constant.TEXT_TYPE_LNG_YUEYU);
                mForeignLngTypeTx = Constant.TEXT_TYPE_LNG_YUEYU;
                res = R.drawable.ic_chinese;
                break;
            case LngConstant.LANGUAGE_JAPENESE:
                str = App.getSysText(Constant.TEXT_TYPE_LNG_JAPANESE);
                mForeignLngTypeTx = Constant.TEXT_TYPE_LNG_JAPANESE;
                res = R.drawable.ic_japanese;
                break;
            case LngConstant.LANGUAGE_KOREAN:
                str = App.getSysText(Constant.TEXT_TYPE_LNG_KOREAN);
                mForeignLngTypeTx = Constant.TEXT_TYPE_LNG_KOREAN;
                res = R.drawable.ic_korean;
                break;
            case LngConstant.LANGUAGE_THAI:
                str = App.getSysText(Constant.TEXT_TYPE_LNG_THAI);
                mForeignLngTypeTx = Constant.TEXT_TYPE_LNG_THAI;
                res = R.drawable.ic_thai;
                break;
            case LngConstant.LANGUAGE_ARABIAN:
                str = App.getSysText(Constant.TEXT_TYPE_LNG_ARABIAN);
                mForeignLngTypeTx = Constant.TEXT_TYPE_LNG_ARABIAN;
                res = R.drawable.ic_arabic;
                break;
            case LngConstant.LANGUAGE_RUSSIAN:
                str = App.getSysText(Constant.TEXT_TYPE_LNG_RUSSIAN);
                mForeignLngTypeTx = Constant.TEXT_TYPE_LNG_RUSSIAN;
                res = R.drawable.ic_russian;
                break;
            case LngConstant.LANGUAGE_SPANISH:
                str = App.getSysText(Constant.TEXT_TYPE_LNG_SPANISH);
                mForeignLngTypeTx = Constant.TEXT_TYPE_LNG_SPANISH;
                res = R.drawable.ic_spanish;
                break;
            case LngConstant.LANGUAGE_VIETNAMESE:
                str = App.getSysText(Constant.TEXT_TYPE_LNG_VIETNAMESE);
                mForeignLngTypeTx = Constant.TEXT_TYPE_LNG_VIETNAMESE;
                res = R.drawable.ic_vietnamese;
                break;
            case LngConstant.LANGUAGE_FRENCH:
                str = App.getSysText(Constant.TEXT_TYPE_LNG_FRENCH);
                mForeignLngTypeTx = Constant.TEXT_TYPE_LNG_FRENCH;
                res = R.drawable.ic_french;
                break;
            case LngConstant.LANGUAGE_GERMAN:
                str = App.getSysText(Constant.TEXT_TYPE_LNG_GERMAN);
                mForeignLngTypeTx = Constant.TEXT_TYPE_LNG_GERMAN;
                res = R.drawable.ic_german;
                break;
            case LngConstant.LANGUAGE_PORTUGAL:
                str = App.getSysText(Constant.TEXT_TYPE_LNG_PORTUGAL);
                mForeignLngTypeTx = Constant.TEXT_TYPE_LNG_PORTUGAL;
                res = R.drawable.ic_portuese;
                break;
            case LngConstant.LANGUAGE_ITALIAN:
                str = App.getSysText(Constant.TEXT_TYPE_LNG_ITALIAN);
                mForeignLngTypeTx = Constant.TEXT_TYPE_LNG_ITALIAN;
                res = R.drawable.ic_italian;
                break;
            case LngConstant.LANGUAGE_HOLLAND:
                str = App.getSysText(Constant.TEXT_TYPE_LNG_HOLLAND);
                mForeignLngTypeTx = Constant.TEXT_TYPE_LNG_HOLLAND;
                res = R.drawable.ic_dutch;
                break;
            case LngConstant.LANGUAGE_GREEK:
                str = App.getSysText(Constant.TEXT_TYPE_LNG_GREEK);
                mForeignLngTypeTx = Constant.TEXT_TYPE_LNG_GREEK;
                res = R.drawable.ic_greek;
                break;
            case LngConstant.LANGUAGE_INDONESIA:
                str = App.getSysText(Constant.TEXT_TYPE_LNG_INDONESIA);
                mForeignLngTypeTx = Constant.TEXT_TYPE_LNG_INDONESIA;
                res = R.drawable.ic_trans_en_ind;
                break;
        }
        textView.setText(str);
        imageView.setImageResource(res);
    }


    public void showLngUIE(String showLanguage, TextView textView, ImageView imageView) {
        Logger.w("创建时传递进来的值+" + showLanguage);
        String str = null;
        int res = 0;
        switch (showLanguage) {
            case LngConstant.LANGUAGE_CHINESE:
                str = "中文";
                res = R.drawable.ic_chinese;
                break;
            case LngConstant.LANGUAGE_CHINESE_HK:
                str = "粤语";
                res = R.drawable.ic_chinese;
                break;

            case LngConstant.LANGUAGE_ENGLISH:
                str = "English";
                res = R.drawable.ic_english;
                break;
            case LngConstant.LANGUAGE_ENGLISH_US:
                str = "English(US)";
                res = R.drawable.ic_english;
                break;
            case LngConstant.LANGUAGE_ENGLISH_IND:
                str = "English(IND)";
                res = R.drawable.ic_english;
                break;
            case LngConstant.LANGUAGE_YUEYU:
                str = "粤语";
                res = R.drawable.ic_chinese;
                break;
            case LngConstant.LANGUAGE_JAPENESE:
                str = "日本語";
                res = R.drawable.ic_japanese;
                break;
            case LngConstant.LANGUAGE_KOREAN:
                str = "한국어";
                res = R.drawable.ic_korean;
                break;
            case LngConstant.LANGUAGE_THAI:
                str = "ภาษาไทย";
                res = R.drawable.ic_thai;
                break;
            case LngConstant.LANGUAGE_ARABIAN:
                str = "عربي";
                res = R.drawable.ic_arabic;
                break;
            case LngConstant.LANGUAGE_RUSSIAN:
                str = "русский";
                res = R.drawable.ic_russian;
                break;
            case LngConstant.LANGUAGE_SPANISH:
                str = "El español";
                res = R.drawable.ic_spanish;
                break;
            case LngConstant.LANGUAGE_VIETNAMESE:
                str = "Los vietnamitas";
                res = R.drawable.ic_vietnamese;
                break;
            case LngConstant.LANGUAGE_FRENCH:
                str = "Français";
                res = R.drawable.ic_french;
                break;
            case LngConstant.LANGUAGE_GERMAN:
                str = "Deutsch";
                res = R.drawable.ic_german;
                break;
            case LngConstant.LANGUAGE_PORTUGAL:
                str = "Português";
                res = R.drawable.ic_portuese;
                break;
            case LngConstant.LANGUAGE_ITALIAN:
                str = "In Italiano";
                res = R.drawable.ic_italian;
                break;
            case LngConstant.LANGUAGE_HOLLAND:
                str = "De Nederlandse";
                res = R.drawable.ic_dutch;
                break;
            case LngConstant.LANGUAGE_GREEK:
                str = "Ελληνική γλώσσα";
                res = R.drawable.ic_greek;
                break;
        }
        textView.setText(str);
        imageView.setImageResource(res);
    }

}
