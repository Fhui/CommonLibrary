package com.example.huifeng.library.mvp.view;

import android.app.ProgressDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.huifeng.library.R;
import com.example.huifeng.library.activity.MainActivity;
import com.example.huifeng.library.core.BaseFragment;
import com.example.huifeng.library.mvp.model.impl.UserModel;
import com.example.huifeng.library.mvp.presenter.UserPresenter;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * MVP Fragment
 * Created by ShineF on 2017/7/17 0017.
 */

public class MVPFragment extends BaseFragment implements IUserView {


    @BindView(R.id.et_username)
    EditText mEtUsername;
    @BindView(R.id.et_pwd)
    EditText mEtPwd;
    @BindView(R.id.btn_login)
    Button mBtnLogin;

    @OnClick(R.id.btn_login)
    public void onClick(View view) {
        presenter.login();
    }

    private UserPresenter presenter;

    @Override
    public void init() {
        super.init();
        presenter = new UserPresenter(new UserModel());
        presenter.attachView(this);
    }

    @Override
    public int setContentLayout() {
        return R.layout.fragment_mvp;
    }

    @Override
    public void setTitle() {
        ((MainActivity) mContext).setTitleText("MVP");
    }


    @Override
    public void showLoaddingView(String msg) {

    }

    @Override
    public void showLoaddingDialog(String msg) {
        showProgressDialog(msg);
    }

    @Override
    public void showErrorInfo(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void hideLoaddingView() {

    }

    @Override
    public void hideLoaddingDialog() {
        dismissProgressDialog();
    }

    @Override
    public void showResult() {
        Toast.makeText(mContext, "success", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void login() {
        presenter.login();
    }

    @Override
    public String getUserName() {
        return mEtUsername.getText().toString();
    }

    @Override
    public String getPwd() {
        return mEtPwd.getText().toString();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }
}
