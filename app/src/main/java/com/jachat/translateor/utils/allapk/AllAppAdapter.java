package com.jachat.translateor.utils.allapk;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jachat.translateor.R;
import com.jachat.translateor.base.BaseAdapter;


/**
 * Created by plcgo on 2016/12/29.
 */
public class AllAppAdapter extends BaseAdapter<AppInfo> {

    private Context context;

    public AllAppAdapter(Context context) {
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreate(ViewGroup parent, int viewType) {
        return new AllAppViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_allapp_list_item, parent, false));
    }

    @Override
    public void onBind(RecyclerView.ViewHolder viewHolder, final int position, final AppInfo data) {
        AllAppViewHolder holder = (AllAppViewHolder) viewHolder;
        if(getItemViewType(position) == AppInfo.TYPE_CLK) return;

        //Log.i(TAG, "mIcon="+appInfo.mIcon);
        holder.mIconView.setImageDrawable(data.mIcon);
        holder.mLabelView.setText(data.mLabel);
        holder.mRootView.setTag(data);
    }

    private class AllAppViewHolder extends RecyclerView.ViewHolder {
        View mRootView;
        ImageView mIconView;
        TextView mLabelView;
        public AllAppViewHolder(View view){
            super(view);
            mIconView = (ImageView) view.findViewById(R.id.icon_view);
            mLabelView = (TextView) view.findViewById(R.id.label_view);
            mRootView = view;
        }
    }
}
