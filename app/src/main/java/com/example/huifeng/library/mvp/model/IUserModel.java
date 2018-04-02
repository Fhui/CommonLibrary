package com.example.huifeng.library.mvp.model;


import com.example.huifeng.library.mvp.base.ILoadListener;

/**
 * 用户model
 * Created by hui.feng on 2018/4/2.
 */

public interface IUserModel  {

    void login(ILoadListener listener, String username, String pwd);

}
