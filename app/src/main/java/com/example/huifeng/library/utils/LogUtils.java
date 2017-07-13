package com.example.huifeng.library.utils;

import android.text.TextUtils;
import android.util.Log;

public class LogUtils {
	private static boolean debug = true;
	public static void setDebug(boolean isDebug) {
		debug = isDebug;
	}

	public static void showLog(String text) {
		if (debug) {
			if(!TextUtils.isEmpty(text)&&text.length() > 3000) {
				for(int i=0;i<text.length();i+=3000){
					if(i+3000<text.length())
						Log.d("wangcang",text.substring(i, i+3000));
					else
						Log.d("wangcang",text.substring(i, text.length()));
				}
			} else
				Log.d("wangcang",text);
		}
	}

	public static void showErrLog(String text) {
		if (debug) {
			Log.e("wangcang", text);
		}
	}

	public static void showInfoLog(String text){
		if (debug) {
			if(!TextUtils.isEmpty(text)&&text.length() > 3000) {
				for(int i=0;i<text.length();i+=3000){
					if(i+3000<text.length())
						Log.i("wangcang",text.substring(i, i+3000));
					else
						Log.i("wangcang",text.substring(i, text.length()));
				}
			} else
				Log.i("wangcang",text);
		}
	}
}
