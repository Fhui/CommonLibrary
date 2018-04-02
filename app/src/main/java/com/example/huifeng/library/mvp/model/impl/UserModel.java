package com.example.huifeng.library.mvp.model.impl;

import android.os.Handler;

import com.example.huifeng.library.mvp.base.ILoadListener;
import com.example.huifeng.library.mvp.bean.User;
import com.example.huifeng.library.mvp.model.IUserModel;


/**
 * 用户Model
 * Created by hui.feng on 2018/4/2.
 */

public class UserModel implements IUserModel {

    @Override
    public void login(ILoadListener listener, String username, String pwd) {
        Handler handler = new Handler();
        handler.postDelayed(() -> {
            if (username.equals("root") && pwd.equals("root")) {
                User user = new User();
                user.setPwd(pwd);
                user.setUsername(username);
                listener.success(user);
            } else {
                listener.error("100", "密码错误");
            }
        }, 2000);
    }
}
