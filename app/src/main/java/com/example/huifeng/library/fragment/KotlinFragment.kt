package com.example.huifeng.library.fragment

import android.widget.ListView
import android.widget.TextView
import com.example.huifeng.library.MainActivity
import com.example.huifeng.library.R
import com.example.huifeng.library.core.BaseFragment

/**
 * Kotlin Fragment
 * Created by ShineF on 2017/7/17 0017.
 */

class KotlinFragment : BaseFragment() {

    var mTvText : TextView ?= null
    var mLvContent : ListView  ?= null

    override fun setContentLayout(): Int {
        return R.layout.fragment_kotlin
    }

    override fun setTitle() {
        (mContext as MainActivity).setmTitleText("Kotlin")
    }

    override fun init() {
        super.init()
        mTvText = (mContext.findViewById(R.id.tv_kotlin) as TextView?)!!
        (mTvText as TextView).text = "Hello, Kotlin"
    }
}
