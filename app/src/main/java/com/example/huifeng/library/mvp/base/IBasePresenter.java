package com.example.huifeng.library.mvp.base;

/**
 * 顶层Presenter接口
 * Created by hui.feng on 2018/4/2.
 */

public interface IBasePresenter<T extends IBaseView> {

    void attachView(T view);

    void detachView();

}
