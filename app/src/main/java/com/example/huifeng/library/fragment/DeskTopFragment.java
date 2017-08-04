package com.example.huifeng.library.fragment;

import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.huifeng.library.MainActivity;
import com.example.huifeng.library.R;
import com.example.huifeng.library.adapter.MainAdapter;
import com.example.huifeng.library.bean.LibraryBean;
import com.example.huifeng.library.core.BaseFragment;
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
        ((MainActivity) mContext).setmTitleText("MyNote");
    }

    public void initData() {
        int temp = 0;
        String[] fragmentArray = getResources().getStringArray(R.array.fragment_name);
        String[] stringArray = getResources().getStringArray(R.array.recycle_list);
        for (String s : stringArray) {
            if (s.startsWith("+")) {
                mList.add(new LibraryBean(null, s));
            } else {
                if (fragmentArray.length - 1 >= temp) {
                    mList.add(new LibraryBean(Fragment.instantiate(mContext, fragmentArray[temp]), s));
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
