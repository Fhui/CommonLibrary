package com.example.huifeng.library.fragment;

import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.huifeng.library.activity.MainActivity;
import com.example.huifeng.library.R;
import com.example.huifeng.library.adapter.MainAdapter;
import com.example.huifeng.library.bean.LibraryBean;
import com.example.huifeng.library.core.BaseFragment;
import com.example.huifeng.library.mvp.view.MVPFragment;
import com.example.huifeng.library.utils.SpaceItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 主页面Fragment
 * Created by ShineF on 2017/7/3 0003.
 */

public class DeskTopFragment extends BaseFragment implements MainAdapter.ItemClickListener {

    @BindView(R.id.rv_list)
    RecyclerView mRvContent;
    private List<LibraryBean> mList;

    @Override
    public int setContentLayout() {
        return R.layout.fragment_desk_top;
    }

    @Override
    public void init() {
        super.init();
        mList = new ArrayList<>();
        mRvContent.setLayoutManager(new GridLayoutManager(mContext, 1));
        mRvContent.addItemDecoration(new SpaceItemDecoration(10, 10));
        initData();
    }

    @Override
    public void setTitle() {
        ((MainActivity) mContext).setTitleText("MyNote");
    }

    public void initData() {
        int temp = 0;
        List<String> fragmentList = new ArrayList<>();
        String[] stringArray = getResources().getStringArray(R.array.recycle_list);
        fragmentList.add(RetrofitFragment.class.getName());
        fragmentList.add(OkHttpFragment.class.getName());
        fragmentList.add(NoHttpFragment.class.getName());
        fragmentList.add(AdderSubtractorFragment.class.getName());
        fragmentList.add(LoginFragment.class.getName());
        fragmentList.add(SelectPicFragment.class.getName());
        fragmentList.add(SharedElementsFragment.class.getName());
//        fragmentList.add(ContactsFragment.class.getName());
        fragmentList.add(ChildContractsFragment.class.getName());
        fragmentList.add(ProgressFragment.class.getName());
        fragmentList.add(PopupWindowFragment.class.getName());
        fragmentList.add(FingerprintFragment.class.getName());
        fragmentList.add(DialogFragment.class.getName());
        fragmentList.add(EasyRecycleViewFragment.class.getName());
        fragmentList.add(MarginDesignFragment.class.getName());
        fragmentList.add(NotificationFragment.class.getName());
        fragmentList.add(CustomTitleFragment.class.getName());
        fragmentList.add(RxJavaFragment.class.getName());
        fragmentList.add(MVPFragment.class.getName());
        fragmentList.add(MVVMFragment.class.getName());
        fragmentList.add(KotlinFragment.class.getName());
        for (String s : stringArray) {
            if (s.startsWith("+")) {
                mList.add(new LibraryBean(null, s));
            } else {
                if (fragmentList.size() - 1 >= temp) {
                    mList.add(new LibraryBean(Fragment.instantiate(mContext, fragmentList.get(temp)), s));
                    temp++;
                } else {
                    mList.add(new LibraryBean(null, s));
                }
            }
        }
        MainAdapter adapter = new MainAdapter(mList, mContext);
        mRvContent.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity) mContext).hideReturn();
    }

    @Override
    public void clickItem(int position, View view, LibraryBean bean) {
        Fragment fragment = bean.getmFragment();
        if (fragment != null) {
            pushFragment(fragment);
        }
    }
}
