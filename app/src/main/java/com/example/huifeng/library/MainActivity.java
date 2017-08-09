package com.example.huifeng.library;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.transition.Fade;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.huifeng.library.core.BaseActivity;
import com.example.huifeng.library.custom_widget.DetailsTransition;
import com.example.huifeng.library.fragment.DeskTopFragment;
import com.example.huifeng.library.fragment.LoginFragment;
import com.example.huifeng.library.fragment.SelectPicFragment;
import com.example.huifeng.library.utils.LogUtils;
import com.zhihu.matisse.Matisse;

import java.util.Stack;

import butterknife.BindView;
import butterknife.OnClick;
import it.sephiroth.android.library.easing.Linear;


/**
 * 主页面
 */
public class MainActivity extends BaseActivity {


    private Stack<Fragment> mBackStack = null;
    @BindView(R.id.back_bt)
    ImageView mImgReturn;
    @BindView(R.id.tv_title_text)
    TextView mTitleText;
    @BindView(R.id.ll_title)
    LinearLayout mLlTitle;
    private long mExitTime = 0;

    @OnClick(R.id.back_bt)
    public void click(View view) {
        popFragment();
    }

    @Override
    public int setContentLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void init() {
        super.init();
        hideReturn();
        mBackStack = new Stack<>();
        pushFragment(new DeskTopFragment());
        setTitleText("MyNote");
    }

    public void hideReturn() {
        mImgReturn.setVisibility(View.GONE);
    }

    public void showReturn() {
        mImgReturn.setVisibility(View.VISIBLE);
    }

    public void setTitleText(String title) {
        LogUtils.showErrLog("stack ----- > " + mBackStack.size());
        mTitleText.setText(title);
    }


    /**
     * fragment 入栈
     *
     * @param fragment
     */
    public void pushFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fts = fm.beginTransaction();
        fts.add(R.id.fl_content, fragment)
                .addToBackStack("BackStack")
                .commitAllowingStateLoss();
        mBackStack.push(fragment);
        if (!(peekFragment() instanceof DeskTopFragment)) {
            showReturn();
        }
        if (peekFragment() instanceof LoginFragment) {
            if (mTitleText.getVisibility() == View.VISIBLE)
                mLlTitle.setVisibility(View.GONE);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
    }

    public void pushFragment(Fragment thisFragment, Fragment nextFragment, View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            nextFragment.setSharedElementEnterTransition(new DetailsTransition());
            nextFragment.setEnterTransition(new Fade());
            thisFragment.setExitTransition(new Fade());
            nextFragment.setSharedElementReturnTransition(new DetailsTransition());
        }
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fts = fm.beginTransaction();
        fts.addSharedElement(view, getResources().getString(R.string.transName))
                .replace(R.id.fl_content, nextFragment)
                .addToBackStack("BackStack")
                .commitAllowingStateLoss();
        mBackStack.push(nextFragment);
        if (!(peekFragment() instanceof DeskTopFragment))
            showReturn();

    }

    /**
     * Fragment出栈
     */
    public void popFragment() {
        if (mLlTitle.getVisibility() == View.GONE)
            mLlTitle.setVisibility(View.VISIBLE);
        if (isFullScreen(this)) {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        FragmentManager fm = getSupportFragmentManager();
        fm.popBackStackImmediate();
        Fragment pop = mBackStack.pop();
        FragmentTransaction ft = fm.beginTransaction();
        ft.remove(pop);
        ft.commitAllowingStateLoss();
        Handler handler = new Handler();
        handler.postDelayed(() -> {
            Intent it = new Intent("title");
            sendBroadcast(it);
        }, 100);
    }

    /**
     * 判断是否是全屏
     *
     * @param activity Activity
     * @return 结果
     */
    public static boolean isFullScreen(Activity activity) {
        int flag = activity.getWindow().getAttributes().flags;
        if ((flag & WindowManager.LayoutParams.FLAG_FULLSCREEN)
                == WindowManager.LayoutParams.FLAG_FULLSCREEN) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 查看栈顶的fragment
     *
     * @return
     */
    public Fragment peekFragment() {
        if (mBackStack.size() > 0) {
            return mBackStack.peek();
        }

        return null;
    }


    @Override
    public void onBackPressed() {
        if (mBackStack.size() > 1) {
            popFragment();
            return;
        } else {
            if (System.currentTimeMillis() - mExitTime > 2000) {
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                mExitTime = System.currentTimeMillis();
                return;
            }
            finish();
        }
        super.onBackPressed();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Fragment fragment = mBackStack.peek();
        if (fragment instanceof SelectPicFragment) {
            ((SelectPicFragment) fragment).setData(Matisse.obtainResult(data));
        }
    }
}
