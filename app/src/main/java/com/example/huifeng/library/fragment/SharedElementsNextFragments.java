package com.example.huifeng.library.fragment;


import com.example.huifeng.library.MainActivity;
import com.example.huifeng.library.R;
import com.example.huifeng.library.core.BaseFragment;

/**
 * 共享元素第二页
 * Created by ShineF on 2017/7/28 0028.
 */

public class SharedElementsNextFragments extends BaseFragment {

    @Override
    public int setContentLayout() {
        return R.layout.fragment_shared_elements_next;
    }

    @Override
    public void setTitle() {
        ((MainActivity) mContext).setTitleText("ShardElements");
    }

    @Override
    public void init() {
        super.init();
    }
}
