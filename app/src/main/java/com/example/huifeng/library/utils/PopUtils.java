package com.example.huifeng.library.utils;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;

import com.example.huifeng.library.R;
import com.example.huifeng.library.custom_widget.popup_window.EasyPopupWindow;

/**
 * PopupWindow 工具类
 * Created by ShineF on 2017/7/17 0017.
 */

public class PopUtils {

    private static PopUtils mPopUtils;
    private EasyPopupWindow mEasyPopupWindow;

    public static PopUtils getInstance() {
        if (null == mPopUtils) {
            synchronized (PopUtils.class) {
                if (null == mPopUtils) {
                    mPopUtils = new PopUtils();
                }
            }
        }
        return mPopUtils;
    }

    public EasyPopupWindow showRight(Context context, int layout, View parent
            , boolean touchOutHide, EasyPopupWindow.ChildClickListener listener){
        if (mEasyPopupWindow != null && mEasyPopupWindow.isShowing())
            return null;
        mEasyPopupWindow = new EasyPopupWindow.Builder(context)
                .setView(layout)
                .setSize(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                .setAnim(R.style.AnimHorizontal)
                .setOutsideTouchHide(touchOutHide)
                .setOnChildClickListener(listener)
                .create();
        mEasyPopupWindow.showAsDropDown(parent, parent.getWidth(), -parent.getHeight());
        return mEasyPopupWindow;
    }

    public EasyPopupWindow showLeft(Context context, int layout, View parent
            , boolean touchOutHide, EasyPopupWindow.ChildClickListener listener){
        if (mEasyPopupWindow != null && mEasyPopupWindow.isShowing())
            return null;
        mEasyPopupWindow = new EasyPopupWindow.Builder(context)
                .setView(layout)
                .setSize(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                .setAnim(R.style.AnimRight)
                .setOutsideTouchHide(touchOutHide)
                .setOnChildClickListener(listener)
                .create();
        mEasyPopupWindow.showAsDropDown(parent,  parent.getWidth() - mEasyPopupWindow.getWidth() , -parent.getHeight());
        return mEasyPopupWindow;
    }


    /**
     * 显示向上的PopupWindow
     *
     * @param context      上下文
     * @param layout       布局ID
     * @param parent       相对父控件
     * @param touchOutHide 点击外面是否消失
     * @param listener     针对布局里面的控件回调
     * @return EasyPopupWindow
     */
    public EasyPopupWindow showFullScreen(Context context, int layout, View parent
            , boolean touchOutHide, EasyPopupWindow.ChildClickListener listener) {
        if (mEasyPopupWindow != null && mEasyPopupWindow.isShowing())
            return null;
        mEasyPopupWindow = new EasyPopupWindow.Builder(context)
                .setView(layout) //设置popupwindow view 里面可以传view, layout
                .setSize(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT) //设置宽高
                .setBackgroundAlpha(0.7f) //设置背景透明度
                .setAnim(R.style.anim_menu_top_bar) //设置动画
                .setOutsideTouchHide(touchOutHide) //点击外面是否关闭
                .setOnChildClickListener(listener) //布局中的事件回调
                .create(); //创建
        mEasyPopupWindow.showAtLocation(parent, Gravity.BOTTOM, 0, 0);

        return mEasyPopupWindow;
    }


}
