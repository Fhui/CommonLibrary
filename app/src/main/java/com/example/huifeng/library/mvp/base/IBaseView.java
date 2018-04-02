package com.example.huifeng.library.mvp.base;

/**
 * 顶层Base View 接口
 * Created by hui.feng on 2018/4/2.
 */

public interface IBaseView {

    void showLoaddingView(String msg);

    void showLoaddingDialog(String msg);

    void showErrorInfo(String msg);

    void hideLoaddingView();

    void hideLoaddingDialog();

    void showResult();

}
