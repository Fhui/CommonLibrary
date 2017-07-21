package com.example.huifeng.library.fragment;

import com.example.huifeng.library.MainActivity;
import com.example.huifeng.library.R;
import com.example.huifeng.library.core.BaseFragment;

/**
 * Weex Fragment
 * Created by ShineF on 2017/7/17 0017.
 */

public class WeexFragment extends BaseFragment {


    @Override
    public int setContentLayout() {
        return R.layout.fragment_weex;
    }

    @Override
    public void setTitle() {
        ((MainActivity) mContext).setmTitleText("Weex");
    }


}
