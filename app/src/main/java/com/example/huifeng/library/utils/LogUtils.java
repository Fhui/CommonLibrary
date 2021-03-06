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
						Log.d("Library",text.substring(i, i+3000));
					else
						Log.d("Library",text.substring(i, text.length()));
				}
			} else
				Log.d("Library",text);
		}
	}

	public static void showErrLog(String text) {
		if (debug) {
			Log.e("Library", text);
		}
	}

	public static void showInfoLog(String text){
		if (debug) {
			if(!TextUtils.isEmpty(text)&&text.length() > 3000) {
				for(int i=0;i<text.length();i+=3000){
					if(i+3000<text.length())
						Log.i("Library",text.substring(i, i+3000));
					else
						Log.i("Library",text.substring(i, text.length()));
				}
			} else
				Log.i("Library",text);
		}
	}
}
