package com.example.huifeng.library.fragment;

import android.animation.ValueAnimator;
import android.view.View;
import android.widget.ProgressBar;

import com.example.huifeng.library.activity.MainActivity;
import com.example.huifeng.library.R;
import com.example.huifeng.library.core.BaseFragment;
import com.example.huifeng.library.custom_widget.CircleProgressView;
import com.example.huifeng.library.custom_widget.HorizontalProgressView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Progress Fragment
 * Created by ShineF on 2017/7/10 0010.
 */

public class ProgressFragment extends BaseFragment {

    @BindView(R.id.circle_progress_view)
    CircleProgressView mCircleView;
    @BindView(R.id.circle_progress_view_no_background)
    CircleProgressView mCircleViewNoBackground;
    @BindView(R.id.circle_progress_view_custom_text)
    CircleProgressView mCircleViewCustomText;
    @BindView(R.id.progress_follow)
    HorizontalProgressView mFollowProgress;
    @BindView(R.id.progress_right)
    HorizontalProgressView mRightProgress;
    @BindView(R.id.progress_top)
    HorizontalProgressView mTopProgress;
    private boolean isStart = false;
    private ValueAnimator hpvAnimator;

    @OnClick({R.id.circle_progress_view, R.id.circle_progress_view_no_background,
            R.id.circle_progress_view_custom_text, R.id.progress_follow, R.id.progress_right, R.id.progress_top})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.circle_progress_view:
                hpvAnimator = getValueAnimator(mCircleView);
                if (!hpvAnimator.isRunning()) {
                    startAnimator(mCircleView);
                }
                break;
            case R.id.circle_progress_view_no_background:
                hpvAnimator = getValueAnimator(mCircleViewNoBackground);
                if (!hpvAnimator.isRunning()) {
                    startAnimator(mCircleViewNoBackground);
                }
                break;
            case R.id.circle_progress_view_custom_text:
                hpvAnimator = getValueAnimator(mCircleViewCustomText);
                if (!hpvAnimator.isRunning()) {
                    startAnimator(mCircleViewCustomText);
                }
                break;
            case R.id.progress_follow:
                hpvAnimator = getValueAnimator(mFollowProgress);
                if (!hpvAnimator.isRunning()) {
                    startAnimator(mFollowProgress);
                }
                break;
            case R.id.progress_right:
                hpvAnimator = getValueAnimator(mRightProgress);
                if (!hpvAnimator.isRunning()) {
                    startAnimator(mRightProgress);
                }
                break;
            case R.id.progress_top:
                hpvAnimator = getValueAnimator(mTopProgress);
                if (!hpvAnimator.isRunning()) {
                    startAnimator(mTopProgress);
                }
                break;
        }
    }

    @Override
    public void init() {
        super.init();
    }

    public ValueAnimator getValueAnimator(ProgressBar view) {
        return hpvAnimator = ValueAnimator.ofInt(0, view.getProgress());
    }

    public void startAnimator(final ProgressBar view) {
        hpvAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                int currentValue = (Integer) animator.getAnimatedValue();
                view.setProgress(currentValue);
            }
        });
        hpvAnimator.setDuration(1000);
        hpvAnimator.start();
    }

    @Override
    public int setContentLayout() {
        return R.layout.fragment_progress;
    }

    @Override
    public void setTitle() {
        ((MainActivity) mContext).setTitleText("Progress");
    }
}
