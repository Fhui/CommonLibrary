package com.example.huifeng.library.fragment;

import com.example.huifeng.library.activity.MainActivity;
import com.example.huifeng.library.R;
import com.example.huifeng.library.core.BaseFragment;
import com.example.huifeng.library.utils.LogUtils;

import rx.Observable;
import rx.Observer;

/**
 * Dialog Fragment
 * Created by ShineF on 2017/7/21 0021.
 */

public class DialogFragment extends BaseFragment {

    @Override
    public int setContentLayout() {
        return R.layout.fragment_dialog;
    }

    @Override
    public void init() {
        super.init();
        ((MainActivity) mContext).showReturn();
        Observable.create((Observable.OnSubscribe<String>)
                subscriber -> subscriber.onNext("info"))
                .subscribe(new Observer<String>() {
                    @Override
                    public void onCompleted() {
                        LogUtils.showLog("onComplete");
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.showErrLog("error----->" + e.getMessage());
                    }

                    @Override
                    public void onNext(String s) {
                        LogUtils.showErrLog("info----->" + s);
                    }
                });
    }

    @Override
    public void setTitle() {
        ((MainActivity) mContext).setTitleText("Dialog");
    }
}
