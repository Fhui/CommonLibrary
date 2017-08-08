package com.example.huifeng.library.fragment;

import com.example.huifeng.library.MainActivity;
import com.example.huifeng.library.R;
import com.example.huifeng.library.core.BaseFragment;

/**
 * Notification Fragment
 * Created by ShineF on 2017/7/17 0017.
 */

public class NotificationFragment extends BaseFragment {


    @Override
    public int setContentLayout() {
        return R.layout.fragment_notification;
    }

    @Override
    public void setTitle() {
        ((MainActivity) mContext).setTitleText("Notification");
    }


}
