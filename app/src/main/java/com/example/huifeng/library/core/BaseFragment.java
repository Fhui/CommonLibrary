package com.example.huifeng.library.core;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.huifeng.library.MainActivity;
import com.example.huifeng.library.R;

import butterknife.ButterKnife;

/**
 * BaseFragment
 * Created by HIMan on 2016/12/15 0015.
 */

public abstract class BaseFragment extends Fragment {


    public View rootView;

    public Activity mContext;

    public ProgressDialog progressDialog;

    private BroadCast reveiver;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(setContentLayout(), container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    public int setContentLayout() {
        return 0;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
    }

    public void init() {
        reveiver = new BroadCast();
        IntentFilter filter = new IntentFilter("title");
        mContext.registerReceiver(reveiver, filter);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = getActivity();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mContext.unregisterReceiver(reveiver);
    }

    public abstract void setTitle();

    @Override
    public void onResume() {
        super.onResume();
        setTitle();
    }

    // 显示ProgressDialog
    public void showProgressDialog(String text) {
        if (progressDialog == null || !progressDialog.isShowing()) {
            progressDialog = new ProgressDialog(mContext);
            progressDialog.setMessage(text);
            progressDialog.setCancelable(false);
            progressDialog.setCanceledOnTouchOutside(true);
            progressDialog.show();
        }
    }

    // 隐藏ProgressDialog
    public void dismissProgressDialog() {
        try {
            if (progressDialog != null) {
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void pushFragment(Fragment fragment) {
        ((MainActivity) mContext).pushFragment(fragment);
    }


    public void pushFragment(Fragment fragment, Fragment nextFragment, View view) {
        ((MainActivity) mContext).pushFragment(fragment, nextFragment, view);
    }


    public void popFragment() {
        ((MainActivity) mContext).popFragment();
    }

    class BroadCast extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (null != intent.getAction())
                switch (intent.getAction()) {
                    case "title":
                        setTitle();
                        break;
                }
        }
    }

}
