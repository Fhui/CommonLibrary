package com.example.huifeng.library.fragment;

import com.example.huifeng.library.MainActivity;
import com.example.huifeng.library.R;
import com.example.huifeng.library.core.BaseFragment;

/**
 *  Dialog Fragment
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
    }

    @Override
    public void setTitle() {
        ((MainActivity) mContext).setTitleText("Dialog");
    }
}
