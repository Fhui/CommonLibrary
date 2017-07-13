package com.example.huifeng.library.core;


/**
 *  Common
 * Created by ShineF on 2016/12/13 0013.
 */

public class Common {

    private static Common common;
    private String tempToken;
    private long lastClickTime = 0;

    public static Common getInstance() {

        synchronized (Common.class) {
            if (common == null) {
                common = new Common();
            }
        }
        return common;
    }

    public boolean isNotFastClick() {
        long time = System.currentTimeMillis();
        if (time - lastClickTime >= 500) {
            lastClickTime = time;
            return true;
        } else {
            return false;
        }
    }


    public String getTempToken() {
        return tempToken;
    }

    public void setTempToken(String tempToken) {
        this.tempToken = tempToken;
    }
}
