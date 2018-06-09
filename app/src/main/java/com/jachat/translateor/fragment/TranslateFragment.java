package com.jachat.translateor.fragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jachat.translateor.MainActivity;
import com.jachat.translateor.R;
import com.jachat.translateor.adapter.TranslateAdapter;
import com.jachat.translateor.base.BaseAdapter;
import com.jachat.translateor.base.BaseFragment;
import com.jachat.translateor.config.App;
import com.jachat.translateor.config.Constant;
import com.jachat.translateor.config.KeyCode;
import com.jachat.translateor.config.LngConstant;
import com.jachat.translateor.mvp.contract.TransContract;
import com.jachat.translateor.mvp.model.EventObject;
import com.jachat.translateor.mvp.model.TransResultBean;
import com.jachat.translateor.mvp.presenter.TransPresenter;
import com.jachat.translateor.mvp.view.LanguageSetActivity;
import com.jachat.translateor.utils.FileUtils;
import com.jachat.translateor.utils.MediaPlayerManager;
import com.jachat.translateor.utils.newapi.LanguageBean;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

//翻译页面
public class TranslateFragment extends BaseFragment<TransContract.Presenter> implements
        BaseAdapter.OnItemClickListener, TransContract.View, View.OnClickListener {

    @BindView(R.id.frag_translate_recycler)
    RecyclerView mRecyclerView;
    private TranslateAdapter mAdapter;

    @BindView(R.id.frag_translate_txt_mothergongue)
    TextView mTxtMotherTongue;
    @BindView(R.id.frag_translate_txt_foreignlng)
    TextView mTxtForeignLng;
    @BindView(R.id.frag_translate_image_mothergongue)
    ImageView mImageMotherTongue;
    @BindView(R.id.frag_translate_image_foreignlng)
    ImageView mImageForeignLng;

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

    @BindView(R.id.tx_clear_data)
    TextView mTxClean;
    @BindView(R.id.act_main_image_back)
    TextView mReturn;
    @BindView(R.id.tx_toast_1)
    TextView mTxToas;
    @BindView(R.id.tx_toast_2)
    TextView mTxToas1;
    @BindView(R.id.tx_toast_3)
    TextView mTxToas2;
    @BindView(R.id.re_toast_layout)
    RelativeLayout mToastRelativeLayout;

    private int mPos;

    private List<TransResultBean> mList;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_translate;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    protected TransContract.Presenter onCreatePresenter() {
        return new TransPresenter(getActivity(), this);
    }

    @Override
    protected boolean setEventBus() {
        return true;
    }//接收回调事件

    @Override
    protected void initData() {
        //inflaterViewStub();
        mReturn.setText("〈 " + App.getSysText(Constant.TEXT_RETURN));
        mTxClean.setText(App.getSysText(Constant.TEXT_CLEAN));
        mAdapter = new TranslateAdapter(getActivity());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mAdapter);
        mList = mAdapter.getDatas();
        mAdapter.setOnItemClickListener(this);
        if (mList.size() == 0) {
            Log.w("wtf", "系统语言+" + App.mSysLanguage);
            if (App.mSysLanguage.equals(LngConstant.LANGUAGE_CHINESE)) {
                mTxToas.setText("○长按翻译键说话");
                mTxToas1.setText("○松开后即可自动翻译");
                mTxToas2.setText("○点击翻译结果可重播");
                mTxToas1.setVisibility(View.VISIBLE);
                mTxToas2.setVisibility(View.VISIBLE);
            } else {
                mTxToas.setText("Long press the Right Key and speak,then Release to translate.");
                //mTxToas.setText("○ Long press the translation key to speak.");
                mTxToas1.setVisibility(View.GONE);
                mTxToas2.setVisibility(View.GONE);
                //mTxToas1.setText("○ After release, it can be automatically translated.");
                //mTxToas2.setText("○ Click translate results to rebroadcast.");
            }
            mToastRelativeLayout.setVisibility(View.VISIBLE);
        } else {
            mToastRelativeLayout.setVisibility(View.GONE);
        }

        mTxKeyToast.setText(App.getSysText(Constant.LANGUAGE_TOAST));//设置提示
        showLngUI(((MainActivity) getActivity()).mFromLng, mTxtMotherTongue, mImageMotherTongue);
        showForeignLng(((MainActivity) getActivity()).mToLng, mTxtForeignLng, mImageForeignLng);

        showLngUIE(((MainActivity) getActivity()).mFromLng, mTxtMotherLanguage, mImageMotherTongueE);
        showForeignLngE(((MainActivity) getActivity()).mToLng, mTxtForeignLaguage, mImageForeignE);
    }

    public void showLngUI(String showLanguage, TextView textView, ImageView imageView) {
        String str = null;
        int res = 0;
        switch (showLanguage) {
            case LngConstant.LANGUAGE_CHINESE:
                str = "中文";
                res = R.drawable.ic_trans_chinese;
                break;
            case LngConstant.LANGUAGE_ENGLISH:
                str = "English(EN)";
                res = R.drawable.ic_trans_en_en;
                break;
            case LngConstant.LANGUAGE_ENGLISH_US:
                str = "English(US)";
                res = R.drawable.ic_trans_en_us;
                break;
            case LngConstant.LANGUAGE_ENGLISH_IND:
                str = "English(IND)";
                res = R.drawable.ic_trans_en_ind;
                break;
            case LngConstant.LANGUAGE_YUEYU:
                str = "粤语";
                res = R.drawable.ic_trans_yue;
                break;
            case LngConstant.LANGUAGE_JAPENESE:
                str = "日本語";
                res = R.drawable.ic_trans_japanese;
                break;
            case LngConstant.LANGUAGE_KOREAN:
                str = "한국어";
                res = R.drawable.ic_trans_korean;
                break;
            case LngConstant.LANGUAGE_THAI:
                str = "ภาษาไทย";
                res = R.drawable.ic_trans_thai;
                break;
            case LngConstant.LANGUAGE_ARABIAN:
                str = "عربي";
                res = R.drawable.ic_trans_arabian;
                break;
            case LngConstant.LANGUAGE_RUSSIAN:
                str = "русский";
                res = R.drawable.ic_trans_russian;
                break;
            case LngConstant.LANGUAGE_SPANISH:
                str = "El español";
                res = R.drawable.ic_trans_spanish;
                break;
            case LngConstant.LANGUAGE_VIETNAMESE:
                str = "Los vietnamitas";
                res = R.drawable.ic_trans_vie;
                break;
            case LngConstant.LANGUAGE_FRENCH:
                str = "Français";
                res = R.drawable.ic_trans_french;
                break;
            case LngConstant.LANGUAGE_GERMAN:
                str = "Deutsch";
                res = R.drawable.ic_trans_german;
                break;
            case LngConstant.LANGUAGE_PORTUGAL:
                str = "Português";
                res = R.drawable.ic_trans_portugal;
                break;
            case LngConstant.LANGUAGE_ITALIAN:
                str = "In Italiano";
                res = R.drawable.ic_trans_italian;
                break;
            case LngConstant.LANGUAGE_HOLLAND:
                str = "De Nederlandse";
                res = R.drawable.ic_trans_holland;
                break;
            case LngConstant.LANGUAGE_GREEK:
                str = "Ελληνική γλώσσα";
                res = R.drawable.ic_trans_greek;
                break;
        }
        textView.setText(str);
        imageView.setImageResource(res);
    }

    public void showForeignLng(String showLanguage, TextView textView, ImageView imageView) {
        String str = null;
        int res = 0;
        switch (showLanguage) {
            case LngConstant.LANGUAGE_CHINESE:
                str = App.getSysText(Constant.TEXT_TYPE_LNG_CHINESE);
                res = R.drawable.ic_trans_chinese;
                break;
            case LngConstant.LANGUAGE_ENGLISH:
                str = App.getSysText(Constant.TEXT_TYPE_LNG_ENGLISH);
                res = R.drawable.ic_trans_en_en;
                break;
            case LngConstant.LANGUAGE_ENGLISH_US:
                str = App.getSysText(Constant.TEXT_TYPE_LNG_ENGLISH_US);
                res = R.drawable.ic_trans_en_us;
                break;
            case LngConstant.LANGUAGE_ENGLISH_IND:
                str = App.getSysText(Constant.TEXT_TYPE_LNG_ENGLISH_IND);
                res = R.drawable.ic_trans_en_ind;
                break;
            case LngConstant.LANGUAGE_YUEYU:
                str = App.getSysText(Constant.TEXT_TYPE_LNG_YUEYU);
                res = R.drawable.ic_trans_chinese;
                break;
            case LngConstant.LANGUAGE_JAPENESE:
                str = App.getSysText(Constant.TEXT_TYPE_LNG_JAPANESE);
                res = R.drawable.ic_trans_japanese;
                break;
            case LngConstant.LANGUAGE_KOREAN:
                str = App.getSysText(Constant.TEXT_TYPE_LNG_KOREAN);
                res = R.drawable.ic_trans_korean;
                break;
            case LngConstant.LANGUAGE_THAI:
                str = App.getSysText(Constant.TEXT_TYPE_LNG_THAI);
                res = R.drawable.ic_trans_thai;
                break;
            case LngConstant.LANGUAGE_ARABIAN:
                str = App.getSysText(Constant.TEXT_TYPE_LNG_ARABIAN);
                res = R.drawable.ic_trans_arabian;
                break;
            case LngConstant.LANGUAGE_RUSSIAN:
                str = App.getSysText(Constant.TEXT_TYPE_LNG_RUSSIAN);
                res = R.drawable.ic_trans_russian;
                break;
            case LngConstant.LANGUAGE_SPANISH:
                str = App.getSysText(Constant.TEXT_TYPE_LNG_SPANISH);
                res = R.drawable.ic_trans_spanish;
                break;
            case LngConstant.LANGUAGE_VIETNAMESE:
                str = App.getSysText(Constant.TEXT_TYPE_LNG_VIETNAMESE);
                res = R.drawable.ic_trans_vie;
                break;
            case LngConstant.LANGUAGE_FRENCH:
                str = App.getSysText(Constant.TEXT_TYPE_LNG_FRENCH);
                res = R.drawable.ic_trans_french;
                break;
            case LngConstant.LANGUAGE_GERMAN:
                str = App.getSysText(Constant.TEXT_TYPE_LNG_GERMAN);
                res = R.drawable.ic_trans_german;
                break;
            case LngConstant.LANGUAGE_PORTUGAL:
                str = App.getSysText(Constant.TEXT_TYPE_LNG_PORTUGAL);
                res = R.drawable.ic_trans_portugal;
                break;
            case LngConstant.LANGUAGE_ITALIAN:
                str = App.getSysText(Constant.TEXT_TYPE_LNG_ITALIAN);
                res = R.drawable.ic_trans_italian;
                break;
            case LngConstant.LANGUAGE_HOLLAND:
                str = App.getSysText(Constant.TEXT_TYPE_LNG_HOLLAND);
                res = R.drawable.ic_trans_holland;
                break;
            case LngConstant.LANGUAGE_GREEK:
                str = App.getSysText(Constant.TEXT_TYPE_LNG_GREEK);
                res = R.drawable.ic_trans_greek;
                break;
            case LngConstant.LANGUAGE_INDONESIA:
                str = App.getSysText(Constant.TEXT_TYPE_LNG_INDONESIA);
                res = R.drawable.ic_trans_en_ind;
                break;
        }
        textView.setText(str);
        imageView.setImageResource(res);
    }


    @Override
    public void onItemClick(int position, Object data) {
        MediaPlayerManager.getInstance().play(mList.get(position).filename);
        mPos = position;
        setItemStyle();
    }

    @OnClick({R.id.frag_translate_linear_mothergongue, R.id.frag_translate_linear_foreignlng,
            R.id.tx_moth_language, R.id.tx_transalter_language, R.id.act_main_image_back,
            R.id.tx_clear_data
    })
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.frag_translate_linear_mothergongue:
                Intent intentMotherTongue = new Intent(getActivity(), LanguageSetActivity.class);
                intentMotherTongue.putExtra("type", 1);
                intentMotherTongue.putExtra("lng", ((MainActivity) getActivity()).mFromLng);
                startActivity(intentMotherTongue);
                break;
            case R.id.frag_translate_linear_foreignlng:
                Intent intentForeign = new Intent(getActivity(), LanguageSetActivity.class);
                intentForeign.putExtra("type", 2);
                intentForeign.putExtra("lng", ((MainActivity) getActivity()).mToLng);
                startActivity(intentForeign);
                break;

            case R.id.tx_moth_language:
                Intent intentMotherTongue2 = new Intent(getActivity(), LanguageSetActivity.class);
                intentMotherTongue2.putExtra("type", 1);
                intentMotherTongue2.putExtra("lng", ((MainActivity) getActivity()).mFromLng);
                startActivity(intentMotherTongue2);
                break;
            case R.id.tx_transalter_language:
                Intent intentForeign2 = new Intent(getActivity(), LanguageSetActivity.class);
                intentForeign2.putExtra("type", 2);
                intentForeign2.putExtra("lng", ((MainActivity) getActivity()).mToLng);
                startActivity(intentForeign2);
                break;
            case R.id.act_main_image_back:
                EventBus.getDefault().post(new EventObject(KeyCode.KEY_BACK, null));
                break;
            case R.id.tx_clear_data:
                mAdapter.clear();
                mAdapter.notifyDataSetChanged();
                mToastRelativeLayout.setVisibility(View.VISIBLE);
//                if (App.mSysLanguage.equals("zh")) {
//                    mTxToas.setText("○长按翻译键说话\n\n" +
//                            "○松开后即可自动翻译\n\n" +
//                            "○点击翻译结果可重播");
//                } else {
//                    mTxToas.setText("Long press the  key  speak,then Release to translate.");
//                }
                break;

        }
    }

    private void setItemStyle() {
        for (int i = 0; i < mList.size(); i++) {
            mList.get(i).isSelected = false;
        }
        mList.get(mPos).isSelected = true;
        mAdapter.notifyDataSetChanged();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(EventObject event) {
        switch (event.eventType) {
            case Constant.EVENT_MOTHERTONGUE_SET_BACK://母语回调
                String str = ((MainActivity) getActivity()).mFromLng = ((LanguageBean) event.obj).langcode;
                showLngUI(((MainActivity) getActivity()).mFromLng, mTxtMotherTongue, mImageMotherTongue);
                showLngUIE(((MainActivity) getActivity()).mFromLng, mTxtMotherLanguage, mImageMotherTongueE);

                App.mSysLanguage = str;//改变系统语言
                mTxtForeignLaguage.setText(App.getSysText(mForeignLngTypeTx));
                if (str.equals(LngConstant.LANGUAGE_CHINESE)) {
                    Logger.w("设置中文");
                    mTxKeyToast.setText(App.getSysText(Constant.LANGUAGE_TOAST));
                } else {
                    Logger.w("设置英文");
                    mTxKeyToast.setText("Long press the Right Key to Translate");
                }

                mReturn.setText("〈 " + App.getSysText(Constant.TEXT_RETURN));
                mTxClean.setText(App.getSysText(Constant.TEXT_CLEAN));

                break;
            case Constant.EVENT_FOREIGNLNG_SET_BACK://外语回调
                ((MainActivity) getActivity()).mToLng = ((LanguageBean) event.obj).langcode;
                showForeignLng(((MainActivity) getActivity()).mToLng, mTxtForeignLng, mImageForeignLng);
                showForeignLngE(((MainActivity) getActivity()).mToLng, mTxtForeignLaguage, mImageForeignE);
                mTxClean.setText(App.getSysText(Constant.TEXT_CLEAN));
                mReturn.setText("〈 " + App.getSysText(Constant.TEXT_RETURN));

                break;
            case Constant.EVENT_KEY_CANCEL:
                if (!((MainActivity) getActivity()).mIsKeyOpen) {
                    //MediaPlayerManager.getInstance().playSoundFromFile(MediaPlayerManager.getInstance().TYPE_SOUND_SLEEP_WAKE);
                    MediaPlayerManager.getInstance().playSound(getActivity(), MediaPlayerManager.TYPE_SOUND_SLEEP_WAKE);
                    return;
                }
                if (mList.size() > 0) {
                    //Logger.e("filename = " + getMainActivity().mTransList.get(mPos).filename);
                    MediaPlayerManager.getInstance().play(mList.get(mPos).filename);
                }
                break;
            case Constant.EVENT_TRANS_RESULT:
                Logger.w("翻译结果回调");
                mToastRelativeLayout.setVisibility(View.GONE);
                mList.add((TransResultBean) event.obj);
                if (mList.size() > 10) {
                    FileUtils.deleteFile(mList.get(0).filename);
                    mList.remove(0);
                }
                mPos = mList.size() - 1;
                setItemStyle();
                mRecyclerView.smoothScrollToPosition(mPos);
                break;
            case KeyCode.KEY_BACK://返回键操作
                //getActivity().finish();
                break;
            case Constant.OPENT_TRANSLATER:
                break;
            case Constant.SELET_FRAGMENT:
                break;

            case Constant.EVENT_MOTH_LAGUAGE:
//
                if (App.mSysLanguage.equals(LngConstant.LANGUAGE_CHINESE)) {
                    mTxToas.setText("○长按翻译键说话");
                    mTxToas1.setText("○松开后即可自动翻译");
                    mTxToas2.setText("○点击翻译结果可重播");
                    mTxToas1.setVisibility(View.VISIBLE);
                    mTxToas2.setVisibility(View.VISIBLE);
                } else {
                    mTxToas.setText("Long press the Right Key and speak,then Release to translate.");
                    //mTxToas.setText("○ Long press the translation key to speak.");
//                    mTxToas1.setText("○ After release, it can be automatically translated.");
//                    mTxToas2.setText("○ Click translate results to rebroadcast.");
                    mTxToas1.setVisibility(View.GONE);
                    mTxToas2.setVisibility(View.GONE);
                }

                break;
            case Constant.EVENT_MOTH_LANG:
                String lngFlg = (String) event.obj;
                if (lngFlg.equals(LngConstant.LANGUAGE_CHINESE)) {
                    mTxToas.setText("○长按翻译键说话");
                    mTxToas1.setText("○松开后即可自动翻译");
                    mTxToas2.setText("○点击翻译结果可重播");
                    mTxToas1.setVisibility(View.VISIBLE);
                    mTxToas2.setVisibility(View.VISIBLE);
                } else {
                    mTxToas.setText("Long press the Right Key and speak,then Release to translate.");
                    //  mTxToas.setText("○ Long press the translation key to speak.");//Long press the Right Key and speak,then Release to translate.
//                    mTxToas1.setText("○ After release, it can be automatically translated.");
//                    mTxToas2.setText("○ Click translate results to rebroadcast.");
                    mTxToas1.setVisibility(View.GONE);
                    mTxToas2.setVisibility(View.GONE);
                }
                break;


        }


    }

    int mForeignLngTypeTx;

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
        String str = null;
        int res = 0;
        switch (showLanguage) {
            case LngConstant.LANGUAGE_CHINESE:
                str = "中文";
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
