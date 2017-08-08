package com.example.huifeng.library.fragment

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.view.View
import android.widget.ListView
import com.example.huifeng.library.MainActivity
import com.example.huifeng.library.R
import com.example.huifeng.library.adapter.KotlinContentAdapter
import com.example.huifeng.library.adapter.OnItemClickListener
import com.example.huifeng.library.bean.AllContentBean
import com.example.huifeng.library.core.BaseFragment
import com.example.huifeng.library.core.Constant
import com.example.huifeng.library.custom_widget.CustomSwipRefreshLayout
import com.example.huifeng.library.custom_widget.OnLoadMoreListener
import com.example.huifeng.library.net.retrofit.ApiManager
import com.example.huifeng.library.utils.LogUtils
import rx.schedulers.Schedulers

/**
 * Kotlin Fragment
 * Created by ShineF on 2017/7/17 0017.
 */

class KotlinFragment : BaseFragment() {

    private val UP_DATA: Int = 100
    private val LOAD_MORE: Int = 200
    private var mLvContent: ListView? = null
    private var mSrLayout: CustomSwipRefreshLayout? = null
    private var mPager: Int = 1
    private var mItem: Int = 20
    private var mDataList = ArrayList<AllContentBean.ResultsBean>()
    private var mHandler = Handler({ msg: Message? ->
        val dataList: ArrayList<AllContentBean.ResultsBean> = msg!!.obj as ArrayList<AllContentBean.ResultsBean>
        var adapter: KotlinContentAdapter? = null
        when (msg.what) {
            UP_DATA -> {
                mDataList.clear()
                mDataList.addAll(dataList)
                adapter = KotlinContentAdapter(dataList, mContext)
                mLvContent!!.adapter = adapter
                if (mSrLayout!!.isRefreshing) {
                    mSrLayout!!.isRefreshing = false
                }
            }
            LOAD_MORE -> {
                mDataList.addAll(dataList)
                adapter = KotlinContentAdapter(mDataList, mContext)
                mSrLayout!!.setLoading(false)
                mLvContent!!.adapter = adapter
                mLvContent!!.setSelection(mItem - 10)
            }
        }
        adapter!!.setOnItemClick(object : OnItemClickListener {
            override fun onItemClick(view: View, position: Int, bean: AllContentBean.ResultsBean) {
                val webFragment = WebViewFragment()
                val bundle = Bundle()
                bundle.putParcelable("data", bean)
                webFragment.arguments = bundle
                pushFragment(webFragment)
            }
        })
        return@Handler false
    })

    override fun setContentLayout(): Int {
        return R.layout.fragment_kotlin
    }

    override fun setTitle() {
        (mContext as MainActivity).setTitleText("Kotlin")
    }

    override fun init() {
        super.init()
        mLvContent = mContext.findViewById(R.id.lv_content) as ListView
        mSrLayout = mContext.findViewById(R.id.srl_layout) as CustomSwipRefreshLayout
        showProgressDialog("正在加载")
        initData()
    }


    fun initData() {
//        ApiManager.getApiManager().initApiService(Constant.GANK_BASE).loadHistory(20, 1).enqueue(object : Callback<String> {
//            override fun onResponse(call: Call<String>?, response: Response<String>?) {
//                LogUtils.showErrLog(response!!.body())
//            }
//
//            override fun onFailure(call: Call<String>?, t: Throwable?) {
//
//            }
//        })
        mSrLayout!!.setColorSchemeColors(Color.BLACK)
        mSrLayout!!.setDistanceToTriggerSync(100)
        mSrLayout!!.setOnRefreshListener({
            mPager++
            mHandler.postDelayed({
                loadData(UP_DATA)
            }, 1200)
        })
        mSrLayout!!.setOnLoadMoreListener(object : OnLoadMoreListener {
            override fun onLoadMore() {
                mItem += 10
                loadData(LOAD_MORE)
            }
        })
        loadData(UP_DATA)
    }

    fun loadData(what: Int): Unit {
        ApiManager.getApiManager().initApiService(Constant.GANK_BASE).loadAllData(mItem, mPager)
                .subscribeOn(Schedulers.io())
                .subscribe({ success ->
                    val obtain = Message.obtain()
                    when (what) {
                        UP_DATA -> obtain.what = UP_DATA
                        LOAD_MORE -> obtain.what = LOAD_MORE
                    }

                    obtain.obj = success.results
                    mHandler.sendMessage(obtain)
                }, { error ->
                    LogUtils.showErrLog(error.message)
                }, {
                    dismissProgressDialog()
                })
    }
}
