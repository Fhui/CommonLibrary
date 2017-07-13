package com.example.huifeng.library;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.huifeng.library.core.BaseActivity;
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
     * fragment 出栈
     *
     * @param fragment
     */
    public void pushFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fts = fm.beginTransaction();
        fts.replace(R.id.rl_content, fragment);
        fts.addToBackStack("BackStack");
        fts.commitAllowingStateLoss();
        mBackStack.push(fragment);
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
