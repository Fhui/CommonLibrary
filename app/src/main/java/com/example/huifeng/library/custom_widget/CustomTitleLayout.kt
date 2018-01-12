package com.example.huifeng.library.custom_widget

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.example.huifeng.library.R

/**
 *  自定义title
 * Created by ShineF on 2018/1/10 0010.
 */
class CustomTitleLayout(context: Context?, attributes: AttributeSet) : LinearLayout(context, attributes) {

    private var mIvLeft: ImageView? = null
    private var mIvRight: ImageView? = null
    private var mTvTitleContent: TextView? = null
    private var mRootView: View? = null
    private var isShowLeftBtn = false
    private var isShowTitleText = false
    private var isShowRighthBtn = false
    private var returnBtnRes: Int = 0
    private var contentText: String? = null
    private var searchBtnRes: Int = 0


    init {
        mRootView = View.inflate(context, R.layout.layout_custom_title, null)
//        val array : TypedArray = getContext().obtainStyledAttributes(attributes, R.styleable.titleBar)
//        isShowLeftBtn = array.getBoolean(R.styleable.titleBar_isShowLeft, false)
//        isShowTitleText = array.getBoolean(R.styleable.titleBar_isShowText, false)
//        isShowRighthBtn = array.getBoolean(R.styleable.titleBar_isShowRight, false)
//        returnBtnRes = array.getResourceId(R.styleable.titleBar_leftRes, 0)
//        searchBtnRes = array.getResourceId(R.styleable.titleBar_rightRes, 0)
//        contentText = array.getString(R.styleable.titleBar_titleText)
        mIvLeft = mRootView!!.findViewById(R.id.iv_return)
        mIvRight = mRootView!!.findViewById(R.id.iv_return)
        mTvTitleContent = mRootView!!.findViewById(R.id.tv_content)
        if(isShowLeftBtn){
            mIvLeft!!.visibility = View.VISIBLE
        }
        if(isShowRighthBtn){
            mIvRight!!.visibility = View.VISIBLE
        }
        if(isShowTitleText){
            mTvTitleContent!!.visibility = View.VISIBLE
        }
//        array.recycle()
    }




}