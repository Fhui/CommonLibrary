package com.example.huifeng.library.fragment;

import com.example.huifeng.library.activity.MainActivity;
import com.example.huifeng.library.R;
import com.example.huifeng.library.core.BaseFragment;

/**
 *  EasyRecycleView
 * Created by ShineF on 2017/7/21 0021.
 */

public class EasyRecycleViewFragment extends BaseFragment {

    @Override
    public int setContentLayout() {
        return R.layout.fragment_easyrecycle_view;
    }

    @Override
    public void setTitle() {
        ((MainActivity) mContext).setTitleText("EasyRecycleView");
    }
}
