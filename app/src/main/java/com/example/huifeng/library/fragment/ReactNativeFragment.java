package com.example.huifeng.library.fragment;

import com.example.huifeng.library.MainActivity;
import com.example.huifeng.library.R;
import com.example.huifeng.library.core.BaseFragment;

/**
 * ReactNative Fragment
 * Created by ShineF on 2017/7/17 0017.
 */

public class ReactNativeFragment extends BaseFragment {


    @Override
    public int setContentLayout() {
        return R.layout.fragment_react_native;
    }

    @Override
    public void setTitle() {
        ((MainActivity) mContext).setmTitleText("ReactNative");
    }


}
