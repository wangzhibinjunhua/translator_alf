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
import com.jachat.translateor.config.Constant;
import com.jachat.translateor.mvp.model.FuncBean;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by plcgo on 2016/12/29.
 */
public class FunctionAdapter extends BaseAdapter<FuncBean> {

    private Context context;

    public FunctionAdapter(Context context) {
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreate(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_frag_func_recycler, parent, false));
    }

    @Override
    public void onBind(RecyclerView.ViewHolder viewHolder, final int position, final FuncBean data) {
        MyViewHolder holder = (MyViewHolder) viewHolder;
        int res = 0;
        switch (data.type){
            case Constant.TEXT_TYPE_MUSIC:
                res = R.drawable.ic_music;
                break;
            case Constant.TEXT_TYPE_RECORDER:
                res = R.drawable.ic_recorder;
                break;
            case Constant.TEXT_TYPE_FM:
                res = R.drawable.ic_fm;
                break;
            case Constant.TEXT_TYPE_DICTIONARY:
                res = R.drawable.ic_dictionary;
                break;
            case Constant.TEXT_TYPE_CLOCK:
                res = R.drawable.ic_clock;
                break;
            case Constant.TEXT_TYPE_CALCULATOR:
                res = R.drawable.ic_calculator;
                break;
            case Constant.TEXT_TYPE_BROWSER:
                res = R.drawable.ic_browser;
                break;
            case Constant.TEXT_TYPE_DOCUMENTS:
                res = R.drawable.ic_documents;
                break;
            case Constant.TEXT_TYPE_WECHAT:
                res = R.drawable.ic_wechat;
                break;
            case Constant.TEXT_TYPE_QQ:
                res = R.drawable.ic_qq;
                break;
            case Constant.TEXT_TYPE_FACEBOOK:
                res = R.drawable.ic_facebook;
                break;
            case Constant.TEXT_TYPE_TWITTER:
                res = R.drawable.ic_twitter;
                break;
        }
        holder.mImageIcon.setImageResource(res);
        holder.mTxtContent.setText(data.name);

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_frag_func_txt_name)
        TextView mTxtContent;
        @BindView(R.id.item_frag_func_image_icon)
        ImageView mImageIcon;

        public MyViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
