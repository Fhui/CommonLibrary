package com.example.huifeng.library.mvp.view;

import com.example.huifeng.library.mvp.base.IBaseView;

/**
 * user view interface
 * Created by hui.feng on 2018/4/2.
 */

public interface IUserView extends IBaseView {

    void login();

    String getUserName();

    String getPwd();

}
