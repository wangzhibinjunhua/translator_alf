package com.jachat.translateor.fragment;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.jachat.translateor.R;
import com.jachat.translateor.base.BaseAdapter;
import com.jachat.translateor.base.BaseFragment;
import com.jachat.translateor.base.BasePresenter;
import com.jachat.translateor.utils.allapk.AllAppAdapter;
import com.jachat.translateor.utils.allapk.AllAppSettings;
import com.jachat.translateor.utils.allapk.AppInfo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.BindView;

public class FragmentApps extends BaseFragment implements BaseAdapter.OnItemClickListener {

    public static final String TAG = "Perryn";
    @BindView(R.id.act_main_recycler)
    RecyclerView mRecyclerView;
    private AllAppAdapter mAllAppAdapter;
    private PackageManager mPackageManager;
    private List<AppInfo> mAllApps = new ArrayList<AppInfo>();
    private Handler mHandler = new Handler();

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_apps;
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
        mPackageManager = getActivity().getPackageManager();

        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
        mAllAppAdapter = new AllAppAdapter(getActivity());
        //mAllAppAdapter.setOnItemClickListener(mAllAppClickListener);
        mRecyclerView.setAdapter(mAllAppAdapter);
        mAllApps = mAllAppAdapter.getDatas();
        mAllAppAdapter.setOnItemClickListener(this);
        loadAllApps();

    }


    private Runnable mNotifyAdapterChangeRunnable = new Runnable() {
        @Override
        public void run() {
            if (mAllAppAdapter == null) return;
            mAllAppAdapter.notifyDataSetChanged();
        }
    };

    private Comparator<AppInfo> mAppsComparator = new Comparator<AppInfo>() {
        @Override
        public int compare(AppInfo lhs, AppInfo rhs) {
            if (lhs.mSortIdx > rhs.mSortIdx) {
                return 1;
            }
            return -1;
        }
    };

    private void loadAllApps() {
        new Thread() {
            public void run() {
                final Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
                mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);

                List<ResolveInfo> apps = null;
                apps = mPackageManager.queryIntentActivities(mainIntent, 0);
                if (apps == null) {
                    Log.i(TAG, "loadAllApps apps == null");
                    return;
                }
                Log.i(TAG, "loadAllApps start time=" + SystemClock.uptimeMillis());
                //filter
                int count = apps.size();
                for (int i = 0; i < count; i++) {
                    ResolveInfo resolveInfo = apps.get(i);
                    if (AllAppSettings.isIgnored(resolveInfo.activityInfo.name)) {
                        continue;
                    }
                    AppInfo appInfo = new AppInfo(resolveInfo, mPackageManager);
//                    Log.w(TAG, "输出全部列表+" + appInfo.mLabel);
                    if (appInfo.mLabel.equals("Translator")) {
                       continue;
                    }
                    mAllApps.add(appInfo);
                }

                Log.i(TAG, "loadAllApps end time=" + SystemClock.uptimeMillis());

                Collections.sort(mAllApps, mAppsComparator);

                mHandler.post(mNotifyAdapterChangeRunnable);
            }
        }.start();
    }

    private void startActivitySafe(Intent intent) {
        try {
            startActivity(intent);
        } catch (Exception e) {
        }
    }


    @Override
    public void onItemClick(int position, Object data) {
        AppInfo appInfo = (AppInfo) data;
        Log.i("hcj", "appInfo.mIntent=" + appInfo.mIntent);

        if (appInfo.mFolderName != null) {
            //showSubFolder(appInfo);
        } else if (appInfo.mIntent != null) {
            startActivitySafe(appInfo.mIntent);
        }
    }
}
