package com.example.huifeng.library.fragment

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.huifeng.library.MainActivity
import com.example.huifeng.library.R
import com.example.huifeng.library.bean.AllContentBean
import com.example.huifeng.library.core.BaseFragment

@Suppress("OverridingDeprecatedMember")
/**
 *  WebView Fragment
 * Created by ShineF on 2017/8/1 0001.
 */
class WebViewFragment : BaseFragment() {

    private var mUrl: String? = null
    private var mWebView: WebView? = null

    override fun init() {
        super.init()
        mWebView = rootView.findViewById(R.id.wb_content) as WebView
        initData()
    }

    @SuppressLint("SetJavaScriptEnabled")
    fun initData(): Unit {
        val bean = arguments.getParcelable<AllContentBean.ResultsBean>("data")
        mUrl = bean.url
        val setting = mWebView!!.settings
        setting.javaScriptEnabled = true //支持java script
        setting.useWideViewPort = true //图片适应
        setting.loadWithOverviewMode = true //自适应屏幕
        setting.setSupportZoom(true) //支持缩放
        setting.builtInZoomControls = true //设置内置的缩放控件. 若为false，则该WebView不可缩放
        setting.displayZoomControls = false //隐藏原生缩放控件
        setting.javaScriptCanOpenWindowsAutomatically = true  //支持通过JS打开新窗口
        setting.loadsImagesAutomatically = true  //支持自动加载图片
        mWebView!!.loadUrl(mUrl)
        mWebView!!.setWebViewClient(object : WebViewClient() {
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                showProgressDialog("正在加载")
            }
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                view!!.loadUrl(url)
                return true
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                dismissProgressDialog()
            }
        })

    }

    override fun setContentLayout(): Int {
        return R.layout.fragment_webview
    }

    override fun setTitle() {
        (mContext as MainActivity).setmTitleText("Kotlin")
    }

}