package com.jachat.translateor.utils.allapk;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.LauncherActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;

/**
 * Author: Perryn
 * Date:   2018/5/11
 * Email:  945266506@qq.com
 */

public class AppInfo {
    public Drawable mIcon;
    public String mLabel;
    public Intent mIntent;
    public String mPackage;
    public int mSortIdx;
    public String mFolderName;
    public static final int ORDER_MAX = 1000;
    public int mType;
    public static final int TYPE_APP = 0;
    public static final int TYPE_CLK = 1;

    public AppInfo(int type){
        mType = type;
    }

    public AppInfo(ResolveInfo resolveInfo, PackageManager packageManager){
        if(resolveInfo == null || packageManager == null){
            //mLabel = "";
            return;
        }

        initOrder(resolveInfo.activityInfo.name);
        if(/*mSortIdx == ORDER_MAX*/true){
            mIcon = AllAppIcons.getIcon(resolveInfo, packageManager);
        }else{
            //mIcon = mAllAppStyle.getItems().get(mSortIdx).mIconDrawable;
        }

        mLabel = resolveInfo.loadLabel(packageManager).toString();
        resolveIntent(new ComponentName(resolveInfo.activityInfo.applicationInfo.packageName, resolveInfo.activityInfo.name));
        //mIcon = getFixIcon(resolveInfo.activityInfo.name);
        if(mIcon == null){
            //mIcon = getIcon(packageManager,resolveInfo.activityInfo,mIconDpi);
        }

        mType = TYPE_APP;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public AppInfo(LauncherActivityInfo actInfo){
        //mIcon = actInfo.getIcon(mIconDpi);
        mLabel = actInfo.getLabel().toString();
        resolveIntent(actInfo.getComponentName());
        mIcon = actInfo.getIcon(160);
			/*
			mIcon = getFixIcon(actInfo.getComponentName().getClassName());
			if(mIcon == null){
				mIcon = actInfo.getBadgedIcon(mIconDpi);
				if((actInfo.getApplicationFlags() & ApplicationInfo.FLAG_SYSTEM) == 0 && mIcon != null){
					mIcon = getMergeIcon(mIcon,getIconBgIdx(actInfo.getComponentName().getPackageName(),actInfo.getName()));
		        }
			}
			*/
        mType = TYPE_APP;

        initOrder(actInfo.getComponentName().getClassName());
    }

    private void initOrder(String clsName){
        int index = AllAppSettings.PKG_SORT_LIST.indexOf(clsName);
        mSortIdx = (index != -1) ? index : 1000;
        //Log.i(TAG, "initCom clsName="+clsName+",mSortIdx="+mSortIdx);
    }

    private void resolveIntent(ComponentName cn){
        mIntent = new Intent(Intent.ACTION_MAIN);
        mIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        mPackage = cn.getPackageName();
        mIntent.setComponent(cn);
        if(!"com.cj.wtlauncher".equals(mPackage)){
            mIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
        }
    }
}
