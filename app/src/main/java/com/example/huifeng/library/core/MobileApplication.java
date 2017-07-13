package com.example.huifeng.library.core;

import android.app.Application;

import com.example.huifeng.library.utils.SharedPreferencesHelper;


/**
 *  Application
 * Created by ShineFon 2016/12/5 0005.
 */

public class MobileApplication extends Application {

    private static MobileApplication application;
    public static SharedPreferencesHelper sp;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        sp = new SharedPreferencesHelper(this);
    }


    public static MobileApplication getApplicationInstance() {
        return application;
    }
}
