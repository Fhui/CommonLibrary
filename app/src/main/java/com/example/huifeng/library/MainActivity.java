package com.example.huifeng.library;

import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.transition.Fade;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.huifeng.library.core.BaseActivity;
import com.example.huifeng.library.custom_widget.DetailsTransition;
import com.example.huifeng.library.fragment.DeskTopFragment;

import java.util.Stack;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * 主页面
 */
public class MainActivity extends BaseActivity {


    private Stack<Fragment> mBackStack = null;
    @BindView(R.id.back_bt)
    ImageView mImgReturn;
    @BindView(R.id.tv_title_text)
    TextView mTitleText;
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
        setmTitleText("MyNote");
    }

    public void hideReturn() {
        mImgReturn.setVisibility(View.GONE);
    }

    public void showReturn() {
        mImgReturn.setVisibility(View.VISIBLE);
    }

    public void setmTitleText(String title) {
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
        fts.replace(R.id.rl_content, fragment)
                .addToBackStack("BackStack")
                .commitAllowingStateLoss();
        mBackStack.push(fragment);
        if (!(peekFragment() instanceof DeskTopFragment)) {
            showReturn();
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
        fts.addSharedElement(view, "simple transition name")
                .replace(R.id.rl_content, nextFragment)
                .addToBackStack("BackStack")
                .commitAllowingStateLoss();
        mBackStack.push(nextFragment);
        if (!(peekFragment() instanceof DeskTopFragment)) {
            showReturn();
        }
    }

    /**
     * Fragment出栈
     */
    public void popFragment() {
        FragmentManager fm = getSupportFragmentManager();
        fm.popBackStackImmediate();
        Fragment pop = mBackStack.pop();
        FragmentTransaction ft = fm.beginTransaction();
        ft.remove(pop);
        ft.commitAllowingStateLoss();
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
}
