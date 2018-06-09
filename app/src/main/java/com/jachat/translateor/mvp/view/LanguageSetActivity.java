package com.jachat.translateor.mvp.view;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.jachat.translateor.R;
import com.jachat.translateor.adapter.LngSetAdapter;
import com.jachat.translateor.base.BaseActivity;
import com.jachat.translateor.base.BaseAdapter;
import com.jachat.translateor.base.BasePresenter;
import com.jachat.translateor.config.App;
import com.jachat.translateor.config.Constant;
import com.jachat.translateor.config.LngConstant;
import com.jachat.translateor.mvp.model.EventObject;
import com.jachat.translateor.utils.SPUtils;
import com.jachat.translateor.utils.newapi.JsonUtils;
import com.jachat.translateor.utils.newapi.LanguageBean;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class LanguageSetActivity extends BaseActivity implements BaseAdapter.OnItemClickListener {

    @BindView(R.id.act_lngset_recycler)
    RecyclerView mRecyclerView;
    @BindView(R.id.act_lngset_txt_title)
    TextView mTxtTitle;
    private LngSetAdapter mAdapter;
    private int mType;
    private int mPos;

    //最新API
    String mLngStr;
    private ArrayList<LanguageBean> mList;

    @Override
    protected int getLayout() {
        return R.layout.activity_language_set;
    }

    @Override
    protected BasePresenter onCreatePresenter() {
        return null;
    }

    @Override
    protected boolean setEventBus() {
        return false;
    }

    @Override
    protected void initData() {

        mType = getIntent().getIntExtra("type", 0);
        mLngStr = getIntent().getStringExtra(Constant.ACTION_LNG_DATA);
        Logger.e("type = " + mType+"输出+"+mLngStr);
        if(TextUtils.isEmpty(App.mLngStr) || "[]".equals(App.mLngStr))return;
        mList = JsonUtils.fromJson(App.mLngStr, new TypeToken<ArrayList<LanguageBean>>(){});
        if(mList == null) return;
        String curLng = getIntent().getStringExtra("lng");
        mAdapter = new LngSetAdapter(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(this);
        mAdapter.addDatas(mList);
        if (mType == 1) {//母语
            mTxtTitle.setText("〈 " + App.getSysText(Constant.TEXT_TYPE_MOTHER_TONGUE_CHOOSE));
            getData(curLng);
        } else if (mType == 2) {//外语
            mTxtTitle.setText("〈 " + App.getSysText(Constant.TEXT_TYPE_FORENGN_LNG_CHOOSE));
            getForeignLanguage(curLng);
            Logger.w("传入的语种+" + curLng);
        } else if (mType == 8) {
            mTxtTitle.setText("〈 " + App.getSysText(Constant.TEXT_TYPE_MOTHER_TONGUE_CHOOSE));
            getForeignLanguage(curLng);
        } else if (mType == 9) {
            mTxtTitle.setText("〈 " + App.getSysText(Constant.TEXT_TYPE_FORENGN_LNG_CHOOSE));
            getForeignLanguage(curLng);
        } else {
            mTxtTitle.setText(App.getSysText(Constant.TEXT_TYPE_SYSTEM_LNG_CHOOSE));
            getData(App.mSysLanguage);
        }
        mRecyclerView.smoothScrollToPosition(mPos);
    }

    private void getData(String curLng) {
//        List<LanguageBean> list = new ArrayList<>();
//        list.add(new LanguageBean(LngConstant.LANGUAGE_CHINESE, "中文"));
//        list.add(new LanguageBean(LngConstant.LANGUAGE_ENGLISH, "English"));
////        list.add(new LanguageBean(LngConstant.LANGUAGE_ENGLISH_US, "English(US)"));
////        list.add(new LanguageBean(LngConstant.LANGUAGE_ENGLISH_IND, "English(IND)"));
//        list.add(new LanguageBean(LngConstant.LANGUAGE_YUEYU, "粤语"));
//        //list.add(new LanguageBean(LngConstant.LANGUAGE_WEI, "维吾尔语"));
//        list.add(new LanguageBean(LngConstant.LANGUAGE_JAPENESE, "日本語"));
//        list.add(new LanguageBean(LngConstant.LANGUAGE_KOREAN, "한국어"));
//
//        list.add(new LanguageBean(LngConstant.LANGUAGE_THAI, "ภาษาไทย"));
//        list.add(new LanguageBean(LngConstant.LANGUAGE_ARABIAN, "عربي"));
//        list.add(new LanguageBean(LngConstant.LANGUAGE_RUSSIAN, "русский"));
//        list.add(new LanguageBean(LngConstant.LANGUAGE_SPANISH, "El español"));
//        list.add(new LanguageBean(LngConstant.LANGUAGE_VIETNAMESE, "Los vietnamitas"));
//
//        list.add(new LanguageBean(LngConstant.LANGUAGE_FRENCH, "Français"));
//        list.add(new LanguageBean(LngConstant.LANGUAGE_GERMAN, "Deutsch"));
//        list.add(new LanguageBean(LngConstant.LANGUAGE_PORTUGAL, "Português"));
//        list.add(new LanguageBean(LngConstant.LANGUAGE_ITALIAN, "In Italiano"));
//        list.add(new LanguageBean(LngConstant.LANGUAGE_HOLLAND, "De Nederlandse"));
//
//        list.add(new LanguageBean(LngConstant.LANGUAGE_GREEK, "Ελληνική γλώσσα"));
//        //list.add(new LanguageBean(LngConstant.LANGUAGE_INDONESIA, "Bahasa indonesia"));
//
//        for (int i = 0; i < list.size(); i++) {
//            if (curLng.equals(list.get(i).lngFlag)) {
//                list.get(i).isCurLng = true;
//                mPos = i;
//            } else {
//                list.get(i).isCurLng = false;
//            }
//        }
        //mAdapter.addDatas(list);
    }

    public List<LanguageBean> getForeignLanguage(String curLanguage) {
        List<LanguageBean> list = new ArrayList<>();

//        list.add(new LanguageBean(LngConstant.LANGUAGE_CHINESE, App.getSysText(Constant.TEXT_TYPE_LNG_CHINESE)));
//        list.add(new LanguageBean(LngConstant.LANGUAGE_ENGLISH, App.getSysText(Constant.TEXT_TYPE_LNG_ENGLISH)));
////        list.add(new LanguageBean(LngConstant.LANGUAGE_ENGLISH_US, App.getSysText(Constant.TEXT_TYPE_LNG_ENGLISH_US)));
////        list.add(new LanguageBean(LngConstant.LANGUAGE_ENGLISH_IND, App.getSysText(Constant.TEXT_TYPE_LNG_ENGLISH_IND)));
//        list.add(new LanguageBean(LngConstant.LANGUAGE_YUEYU, App.getSysText(Constant.TEXT_TYPE_LNG_YUEYU)));
//        list.add(new LanguageBean(LngConstant.LANGUAGE_JAPENESE, App.getSysText(Constant.TEXT_TYPE_LNG_JAPANESE)));
//        list.add(new LanguageBean(LngConstant.LANGUAGE_KOREAN, App.getSysText(Constant.TEXT_TYPE_LNG_KOREAN)));
//
//        list.add(new LanguageBean(LngConstant.LANGUAGE_THAI, App.getSysText(Constant.TEXT_TYPE_LNG_THAI)));
//        list.add(new LanguageBean(LngConstant.LANGUAGE_ARABIAN, App.getSysText(Constant.TEXT_TYPE_LNG_ARABIAN)));
//        list.add(new LanguageBean(LngConstant.LANGUAGE_RUSSIAN, App.getSysText(Constant.TEXT_TYPE_LNG_RUSSIAN)));
//        list.add(new LanguageBean(LngConstant.LANGUAGE_SPANISH, App.getSysText(Constant.TEXT_TYPE_LNG_SPANISH)));
//        list.add(new LanguageBean(LngConstant.LANGUAGE_VIETNAMESE, App.getSysText(Constant.TEXT_TYPE_LNG_VIETNAMESE)));
//
//        list.add(new LanguageBean(LngConstant.LANGUAGE_FRENCH, App.getSysText(Constant.TEXT_TYPE_LNG_FRENCH)));
//        list.add(new LanguageBean(LngConstant.LANGUAGE_GERMAN, App.getSysText(Constant.TEXT_TYPE_LNG_GERMAN)));
//        list.add(new LanguageBean(LngConstant.LANGUAGE_PORTUGAL, App.getSysText(Constant.TEXT_TYPE_LNG_PORTUGAL)));
//        list.add(new LanguageBean(LngConstant.LANGUAGE_ITALIAN, App.getSysText(Constant.TEXT_TYPE_LNG_ITALIAN)));
//        list.add(new LanguageBean(LngConstant.LANGUAGE_HOLLAND, App.getSysText(Constant.TEXT_TYPE_LNG_HOLLAND)));
//
//        list.add(new LanguageBean(LngConstant.LANGUAGE_GREEK, App.getSysText(Constant.TEXT_TYPE_LNG_GREEK)));
//        //list.add(new LanguageBean(LngConstant.LANGUAGE_INDONESIA, App.getSysText(Constant.TEXT_TYPE_LNG_INDONESIA)));
//
//        for (int i = 0; i < list.size(); i++) {
//            if (curLanguage.equals(list.get(i).lngFlag)) {
//                list.get(i).isCurLng = true;
//                mPos = i;
//            } else {
//                list.get(i).isCurLng = false;
//            }
//        }
        //mAdapter.addDatas(list);
        return list;
    }


    @Override
    public void onItemClick(int position, Object data) {
        LanguageBean bean = (LanguageBean) data;
        if (mType == 1) {
            EventBus.getDefault().post(new EventObject(Constant.EVENT_MOTHERTONGUE_SET_BACK, bean));
            App.mSysLanguage = bean.langcode;//改变系统语言
            SPUtils.put(this, Constant.SP_MOTHER_TONGUE, bean.langcode);
            SPUtils.put(this, Constant.SP_SYSTEM_LANGUAGE, bean.langcode);
            //EventBus.getDefault().post(new EventObject(Constant.EVENT_SYSTEMLNG_SET_BACK, bean));
            finish();
        } else if (mType == 2) {
            EventBus.getDefault().post(new EventObject(Constant.EVENT_FOREIGNLNG_SET_BACK, bean));
            SPUtils.put(this, Constant.SP_FOREIGN_LANGUAGE, bean.langcode);
            finish();
        } else if (mType == 8) {//拍译源语言
            EventBus.getDefault().post(new EventObject(Constant.LNG_LEFT, bean.langcode));
            //SPUtils.put(this, Constant.SP_MOTHER_TONGUE, bean.lngFlag);
            onBackPressedSupport();

        } else if (mType == 9) {//拍译目标语言
            EventBus.getDefault().post(new EventObject(Constant.LNG_TRANSLATED, bean.langcode));
            SPUtils.put(this, Constant.SP_FOREIGN_LANGUAGE_TRANSLATOR, bean.langcode);
            onBackPressedSupport();
        } else {
            App.mSysLanguage = bean.langcode;
            SPUtils.put(this, Constant.SP_SYSTEM_LANGUAGE, bean.langcode);
            EventBus.getDefault().post(new EventObject(Constant.EVENT_SYSTEMLNG_SET_BACK, bean));
            finish();
        }
    }

    @OnClick({R.id.act_lngset_image_back, R.id.act_lngset_txt_title})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.act_lngset_image_back:
                onBackPressedSupport();
                break;
            case R.id.act_lngset_txt_title:
                onBackPressedSupport();
                break;

        }
    }
}
