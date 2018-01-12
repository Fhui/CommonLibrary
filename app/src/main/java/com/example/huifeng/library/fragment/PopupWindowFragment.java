package com.example.huifeng.library.fragment;

import android.view.View;
import android.widget.Button;

import com.example.huifeng.library.activity.MainActivity;
import com.example.huifeng.library.R;
import com.example.huifeng.library.core.BaseFragment;
import com.example.huifeng.library.custom_widget.popup_window.EasyPopupWindow;
import com.example.huifeng.library.utils.PopUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * PopupWindow Fragment
 * Created by ShineF on 2017/7/17 0017.
 */

public class PopupWindowFragment extends BaseFragment implements EasyPopupWindow.ChildClickListener, View.OnClickListener {

    private EasyPopupWindow pop = null;
    @BindView(R.id.btn_center_parent)
    Button mBtnCenterParent;
    @BindView(R.id.btn_center_right)
    Button mBtnRight;
    @BindView(R.id.btn_center_left)
    Button mBtnLeft;

    @OnClick({R.id.btn_center_parent, R.id.btn_center_right, R.id.btn_center_left})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.btn_center_parent:
                pop = PopUtils.getInstance().showFullScreen(mContext, R.layout.view_easy_popupwindow_full_screen, mBtnCenterParent, true, this);
                break;
            case R.id.btn_center_right:
                pop = PopUtils.getInstance().showRight(mContext, R.layout.view_easy_popupwineow_right_and_left, mBtnRight, true, this);
                break;
            case R.id.btn_center_left:
                pop = PopUtils.getInstance().showLeft(mContext, R.layout.view_easy_popupwineow_right_and_left, mBtnLeft, true, this);
                break;
        }
    }

    @Override
    public int setContentLayout() {
        return R.layout.fragment_pop_window;
    }

    @Override
    public void setTitle() {
        ((MainActivity) mContext).setTitleText("PopupWindow");
    }

    @Override
    public void getChildView(int layoutId, View view) {
        switch (layoutId) {
            case R.layout.view_easy_popupwindow_full_screen:
                Button tackPhotos = (Button) view.findViewById(R.id.btn_tack_photo);
                Button photos = (Button) view.findViewById(R.id.btn_photos);
                Button cancel = (Button) view.findViewById(R.id.btn_cancel);
                tackPhotos.setOnClickListener(this);
                photos.setOnClickListener(this);
                cancel.setOnClickListener(this);
                break;
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_tack_photo:

                break;
            case R.id.btn_photos:

                break;
            case R.id.btn_cancel:
                pop.dismiss();
                break;
        }
    }
}
