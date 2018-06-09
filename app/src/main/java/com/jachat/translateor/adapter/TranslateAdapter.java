package com.jachat.translateor.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jachat.translateor.R;
import com.jachat.translateor.base.BaseAdapter;
import com.jachat.translateor.mvp.model.TransResultBean;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by plcgo on 2016/12/29.
 */
public class TranslateAdapter extends BaseAdapter<TransResultBean> {

    private Context context;

    public TranslateAdapter(Context context) {
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreate(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_frag_trans_recycler, parent, false));
    }

    @Override
    public void onBind(RecyclerView.ViewHolder viewHolder, final int position, final TransResultBean data) {
        MyViewHolder holder = (MyViewHolder) viewHolder;
        holder.mTxtFrom.setText(data.fromText);
        holder.mTxtTo.setText(data.toText);

        if(data.isSelected){
            holder.mRelativeContent.setBackgroundResource(R.drawable.bg_translate_item);
            holder.mTxtFrom.setTextColor(Color.WHITE);
            holder.mTxtTo.setTextColor(Color.WHITE);
        }else{
            holder.mRelativeContent.setBackgroundResource(R.drawable.bg_translate_item);
            holder.mTxtFrom.setTextColor(Color.WHITE);
            holder.mTxtTo.setTextColor(Color.WHITE);
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_frag_trans_txt_from)
        TextView mTxtFrom;
        @BindView(R.id.item_frag_trans_txt_to)
        TextView mTxtTo;
        @BindView(R.id.item_frag_trans_relative_content)
        RelativeLayout mRelativeContent;

        public MyViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
