package com.example.huifeng.library.fragment

import android.net.Uri
import com.example.huifeng.library.R
import com.example.huifeng.library.core.BaseFragment
import android.content.ContentResolver
import android.view.WindowManager
import com.example.huifeng.library.MainActivity
import com.example.huifeng.library.custom_widget.CustomVideoView


/**
 *  模仿默默登陆
 * Created by ShineF on 2017/8/8 0008.
 */
class LoginFragment : BaseFragment() {

    private var mVideoView: CustomVideoView? = null


    override fun init() {
        super.init()
        val uri = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://"
                + mContext.packageName + "/"
                + R.raw.video_login)
        mVideoView = rootView.findViewById(R.id.vv_player)
        mVideoView!!.setVideoURI(uri)
        mVideoView!!.start()
        mVideoView!!.setOnPreparedListener({ mp ->
            mp.start()
            mp.isLooping = true
        })
    }

    override fun setContentLayout(): Int = R.layout.fragment_login

    override fun setTitle() {
        (mContext as MainActivity).setTitleText("Login")
    }


}