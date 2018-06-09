package com.jachat.translateor.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jachat.translateor.R;
import com.jachat.translateor.base.BaseAdapter;
import com.jachat.translateor.config.LngConstant;
import com.jachat.translateor.utils.newapi.LanguageBean;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by plcgo on 2016/12/29.
 */
public class LngSetAdapter extends BaseAdapter<LanguageBean> {

    private Context context;

    public LngSetAdapter(Context context) {
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreate(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_act_lngset_recycler, parent, false));
    }


    @Override
    public void onBind(RecyclerView.ViewHolder viewHolder, final int position, final LanguageBean data) {
        MyViewHolder holder = (MyViewHolder) viewHolder;
        holder.mTxtContent.setText(data.memo);
        int res = 0;
        switch (data.langcode){
            case LngConstant.LANGUAGE_CHINESE:
                res = R.drawable.ic_chinese;
                break;
            case LngConstant.LANGUAGE_CHINESE_HK:
                res = R.drawable.ic_chinese;
                break;
            case LngConstant.LANGUAGE_ENGLISH:
                res = R.drawable.ic_english;
                break;
//            case LngConstant.LANGUAGE_ENGLISH_US:
//                res = R.drawable.ic_english;
//                break;
//            case LngConstant.LANGUAGE_ENGLISH_IND:
//                res = R.drawable.ic_english;
//                break;
            case LngConstant.LANGUAGE_YUEYU:
                res = R.drawable.ic_chinese;
                break;
            case LngConstant.LANGUAGE_JAPENESE:
                res = R.drawable.ic_japanese;
                break;
            case LngConstant.LANGUAGE_KOREAN:
                res = R.drawable.ic_korean;
                break;
            case LngConstant.LANGUAGE_THAI:
                res = R.drawable.ic_thai;
                break;
            case LngConstant.LANGUAGE_ARABIAN:
                res = R.drawable.ic_arabic;
                break;
            case LngConstant.LANGUAGE_RUSSIAN:
                res = R.drawable.ic_russian;
                break;
            case LngConstant.LANGUAGE_SPANISH:
                res = R.drawable.ic_spanish;
                break;
            case LngConstant.LANGUAGE_VIETNAMESE:
                res = R.drawable.ic_vietnamese;
                break;
            case LngConstant.LANGUAGE_FRENCH:
                res = R.drawable.ic_french;
                break;
            case LngConstant.LANGUAGE_GERMAN:
                res = R.drawable.ic_german;
                break;
            case LngConstant.LANGUAGE_PORTUGAL:
                res = R.drawable.ic_portuese;
                break;
            case LngConstant.LANGUAGE_ITALIAN:
                res = R.drawable.ic_italian;
                break;
            case LngConstant.LANGUAGE_HOLLAND:
                res = R.drawable.ic_dutch;
                break;
            case LngConstant.LANGUAGE_GREEK:
                res = R.drawable.ic_greek;
                break;
        }
        holder.mImageIcon.setImageResource(res);

//        if(data.isCurLng){
           // holder.itemView.setBackgroundResource(R.drawable.bg_curlng);
//        }else{
            holder.itemView.setBackgroundResource(0);
//        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_act_lngset_txt_content)
        TextView mTxtContent;
        @BindView(R.id.item_act_lngset_image_icon)
        ImageView mImageIcon;

        public MyViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
