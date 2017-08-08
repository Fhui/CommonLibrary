package com.example.huifeng.library.fragment;

import com.example.huifeng.library.MainActivity;
import com.example.huifeng.library.R;
import com.example.huifeng.library.core.BaseFragment;

/**
 * Retrofit Demo
 * Created by ShineF on 2017/7/3 0003.
 */

public class NoHttpFragment extends BaseFragment {

    private String mType;

    public NoHttpFragment(){

    }

    @Override
    public int setContentLayout() {
        return R.layout.fragment_retrofit;
    }

    @Override
    public void init() {
        super.init();
    }

    @Override
    public void setTitle() {
        ((MainActivity) mContext).setTitleText("NoHttp");
    }

}
