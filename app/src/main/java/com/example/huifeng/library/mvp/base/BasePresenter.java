package com.example.huifeng.library.mvp.base;

/**
 * 顶层Presenter类
 * Created by hui.feng on 2018/4/2.
 */

public class BasePresenter<T extends IBaseView> implements IBasePresenter<T> {
    private T mView;

    @Override
    public void attachView(T view) {
        this.mView = view;
    }

    @Override
    public void detachView() {
        mView = null;
    }

    public boolean isNull() {
        return mView == null;
    }

    public T getView() {
        return mView;
    }

    public void checkIsAttach() {
        if (isNull()) {
            throw new RuntimeException("presenter没有和view绑定");
        }
    }
}
