package com.example.huifeng.library.fragment

import com.example.huifeng.library.activity.MainActivity
import com.example.huifeng.library.R
import com.example.huifeng.library.core.BaseFragment
import com.example.huifeng.library.custom_widget.StateButton

/**自定义title fragment for kotlin
 * Created by ShineF on 2018/1/9 0009.
 */
class CustomTitleFragment : BaseFragment() {
    private var mBtnSB: StateButton? = null

    override fun setContentLayout(): Int {
        return R.layout.fragment_custom_title
    }

    override fun init() {
        super.init()
        mBtnSB = rootView.findViewById(R.id.btn_state)
        mBtnSB!!.setOnClickListener {

        }
    }

    override fun setTitle() {
        (mContext as MainActivity).setTitleText("CustomTitle")
    }


}