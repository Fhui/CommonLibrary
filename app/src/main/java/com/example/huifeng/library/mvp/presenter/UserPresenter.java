package com.example.huifeng.library.mvp.presenter;

import com.example.huifeng.library.mvp.base.BasePresenter;
import com.example.huifeng.library.mvp.base.ILoadListener;
import com.example.huifeng.library.mvp.model.IUserModel;
import com.example.huifeng.library.mvp.model.impl.UserModel;
import com.example.huifeng.library.mvp.view.IUserView;
import com.example.huifeng.library.mvp.view.MVPFragment;

/**
 * user presenter
 * Created by hui.feng on 2018/4/2.
 */

public class UserPresenter extends BasePresenter<MVPFragment> implements IUserPresenter {

    private IUserModel model;

    public UserPresenter(UserModel userModel) {
        this.model = userModel;
    }

    @Override
    public void login() {
        checkIsAttach();
        IUserView view = getView();
        view.showLoaddingDialog("正在加载");
        model.login(new ILoadListener() {
            @Override
            public void success(Object object) {
                view.showResult();
                view.hideLoaddingDialog();
            }

            @Override
            public void error(String code, String msg) {
                view.showErrorInfo(msg);
                view.hideLoaddingDialog();
            }
        }, view.getUserName(), view.getPwd());
    }
}
