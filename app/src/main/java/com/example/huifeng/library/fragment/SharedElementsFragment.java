package com.example.huifeng.library.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.huifeng.library.MainActivity;
import com.example.huifeng.library.R;
import com.example.huifeng.library.adapter.SharedElementsAdapter;
import com.example.huifeng.library.core.BaseFragment;
import com.example.huifeng.library.utils.SpaceItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 共享元素
 * Created by ShineF on 2017/7/28 0028.
 */

public class SharedElementsFragment extends BaseFragment implements SharedElementsAdapter.OnItemClick {

    @BindView(R.id.rv_shared_elements)
    RecyclerView mSharedElements;
    SharedElementsAdapter mAdapter;

    @Override
    public void setTitle() {
        ((MainActivity) mContext).setTitleText("ShardElements");
    }

    @Override
    public int setContentLayout() {
        return R.layout.fragment_shared_elements;
    }

    @Override
    public void init() {
        super.init();
        List<String> dataList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            dataList.add(String.valueOf(i));
        }
        mAdapter = new SharedElementsAdapter(mContext, dataList);
        mSharedElements.setLayoutManager(new GridLayoutManager(mContext, 2));
        mSharedElements.addItemDecoration(new SpaceItemDecoration(10, 10));
        mSharedElements.setAdapter(mAdapter);
        mAdapter.setOnItemClick(this);
    }

    @Override
    public void itemClick(View view, int position) {
        SharedElementsNextFragments fragments = new SharedElementsNextFragments();
        pushFragment(this, fragments, view);
    }
}
