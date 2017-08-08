package com.example.huifeng.library.fragment;

import com.example.huifeng.library.MainActivity;
import com.example.huifeng.library.R;
import com.example.huifeng.library.core.BaseFragment;

/**
 *  MarginDesign Fragament
 * Created by ShineF on 2017/7/21 0021.
 */

class MarginDesignFragment extends BaseFragment {

    @Override
    public int setContentLayout() {
        return R.layout.fragment_margin_design;
    }

    @Override
    public void setTitle() {
        ((MainActivity) mContext).setTitleText("MarginDesign");
    }
}
