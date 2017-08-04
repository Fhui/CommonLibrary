package com.example.huifeng.library.fragment;

import android.view.View;

import com.example.huifeng.library.MainActivity;
import com.example.huifeng.library.R;
import com.example.huifeng.library.core.BaseFragment;
import com.example.huifeng.library.custom_widget.AdderSubtractorView;

import butterknife.BindView;

/**
 * 数字加减器
 * Created by ShineF on 2017/8/4 0004.
 */

public class AdderSubtractorFragment extends BaseFragment implements AdderSubtractorView.INumberListener {

    @BindView(R.id.adder_subtractor_view)
    AdderSubtractorView mAdder01 = null;
    @BindView(R.id.adder_subtractor_view_2)
    AdderSubtractorView mAdder02 = null;


    @Override
    public void init() {
        super.init();
        mAdder01.setStyle(1);//设置选择器的样式
        mAdder01.setLeastValue(10);//设置最小值
        mAdder01.setMaximumValue(99);//设置最大值
        mAdder01.setDefaultValue(10);//设置默认值
        mAdder01.setIntNumberListener(this);//添加当数字到达两界点时的监听

        mAdder02.setStyle(2);//设置选择器的样式
        mAdder02.setLeastValue(0);//设置最小值
        mAdder02.setMaximumValue(99);//设置最大值
        mAdder02.setDefaultValue(0);//设置默认值
        mAdder02.setIntNumberListener(this);//添加当数字到达两界点时的监听
    }

    @Override
    public int setContentLayout() {
        return R.layout.fragment_adder_subtractor;
    }

    @Override
    public void setTitle() {
        ((MainActivity) mContext).setmTitleText("数字加减器");
    }

    @Override
    public void setMoreListener(int number, View view) {

    }

    @Override
    public void setLeastListener(int number, View view) {

    }
}
