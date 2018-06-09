package com.jachat.translateor.utils.allapk;

import java.util.ArrayList;

public class AllAppSettings {
	public static final int MENU_STYLE_GRID = 0;
	public static final int MENU_STYLE_V = 1;
	public static final int MENU_STYLE_H = 2;
	
	public static class MenuStyle{
		public int mStyleId;
		public int mPreviewId;
		public int mWallpaperId;
		public MenuStyle(int styleId, int previewId, int wallpaperId){
			mStyleId = styleId;
			mPreviewId = previewId;
			mWallpaperId = wallpaperId;
		}
	}	
	
	public static ArrayList<String> PKG_SORT_LIST = new ArrayList<String>();
	static{
		PKG_SORT_LIST.add("com.android.settings.Settings");
		PKG_SORT_LIST.add("com.ty.translationui.settings.SystemSettingsActivity");
		PKG_SORT_LIST.add("com.android.soundrecorder.SoundRecorder");
		PKG_SORT_LIST.add("com.android.music.MusicBrowserActivity");
		PKG_SORT_LIST.add("com.android.fmradio.FmMainActivity");
		
		PKG_SORT_LIST.add("com.android.deskclock.DeskClock");
		PKG_SORT_LIST.add("com.android.calculator2.Calculator");
		PKG_SORT_LIST.add("com.mediatek.filemanager.FileManagerOperationActivity");
		PKG_SORT_LIST.add("com.android.browser.BrowserActivity");
		
		PKG_SORT_LIST.add("com.android.dialer.DialtactsActivity");
		PKG_SORT_LIST.add("com.android.mms.ui.ConversationList");
	}
	
	public static ArrayList<String> PKG_IGNORE_LIST = new ArrayList<String>();
	static{
		PKG_SORT_LIST.add("comcom.jachat.translateor.MainActivity");
		//PKG_IGNORE_LIST.add("com.android.settings.Settings");
/*
		PKG_IGNORE_LIST.add("com.android.calendar.AllInOneActivity");
		PKG_IGNORE_LIST.add("com.cootek.smartinput5.Guide");
		PKG_IGNORE_LIST.add("com.android.gallery3d.app.GalleryActivity");
		PKG_IGNORE_LIST.add("com.android.camera.CameraLauncher");
*/
	}
	
	public static boolean isIgnored(String clsName) {
		return PKG_IGNORE_LIST.contains(clsName);
	}
}
