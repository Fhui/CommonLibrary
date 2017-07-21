package com.example.huifeng.library.fragment;

import com.example.huifeng.library.MainActivity;
import com.example.huifeng.library.R;
import com.example.huifeng.library.core.BaseFragment;

/**
 * Kotlin Fragment
 * Created by ShineF on 2017/7/17 0017.
 */

public class KotlinFragment extends BaseFragment {


    @Override
    public int setContentLayout() {
        return R.layout.fragment_kotlin;
    }

    @Override
    public void setTitle() {
        ((MainActivity) mContext).setmTitleText("Kotlin");
    }


}
