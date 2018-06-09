package com.jachat.translateor.utils.allapk;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;

public class AllAppIcons {
	private Context mContext;
	
	public AllAppIcons(Context context){
		mContext = context;
	}

	public static Drawable getIcon(ResolveInfo resolveInfo, PackageManager packageManager){
		return resolveInfo.loadIcon(packageManager);
	}
	
	private Drawable getFixIcon(String actName){
		String name = actName.replaceAll("\\.","_").toLowerCase();
		//Log.i(TAG,"getIcon name="+name);
		Resources res = mContext.getResources();
		int resId = res.getIdentifier(name, "mipmap", "com.cj.wtlauncher");
		return (resId != 0) ? res.getDrawable(resId) : null;
	}
}
