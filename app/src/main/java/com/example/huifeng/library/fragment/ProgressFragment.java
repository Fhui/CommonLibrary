package com.example.huifeng.library.fragment;

import android.animation.ValueAnimator;
import android.view.View;

import com.example.huifeng.library.MainActivity;
import com.example.huifeng.library.R;
import com.example.huifeng.library.core.BaseFragment;
import com.example.huifeng.library.custom_widget.CircleProgressView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 *  Progress Fragment
 * Created by ShineF on 2017/7/10 0010.
 */

public class ProgressFragment extends BaseFragment {

    private ValueAnimator mCircleAnimator;
    @BindView(R.id.circle_progress_view)
    CircleProgressView mCircleView;
    @OnClick({R.id.circle_progress_view})
    public void click(View view){
        switch (view.getId()){
            case R.id.circle_progress_view:
                mCircleAnimator.start();
                break;
        }
    }

    @Override
    public void init() {
        super.init();
        mCircleAnimator = ValueAnimator.ofInt(0, mCircleView.getProgress());
        mCircleAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                //获得当前动画的进度值，整型，1-100之间
                int currentValue = (Integer) animator.getAnimatedValue();
                mCircleView.setProgress(currentValue);
            }
        });
        mCircleAnimator.setDuration(1000);
    }

    @Override
    public int setContentLayout() {
        return R.layout.fragment_progress;
    }

    @Override
    public void setTitle() {
        ((MainActivity)mContext).setmTitleText("Progress");
    }
}
