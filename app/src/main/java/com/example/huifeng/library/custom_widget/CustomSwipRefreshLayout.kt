package com.example.huifeng.library.custom_widget

import android.content.Context
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.ViewConfiguration
import android.widget.AbsListView
import android.widget.ListView
import com.example.huifeng.library.R

/**
 * Kotlin for SwipRefreshLayout
 * Created by ShineF on 2017/8/2 0002.
 */
class CustomSwipRefreshLayout(context: Context?, attributes: AttributeSet) : SwipeRefreshLayout(context, attributes) {

    private var mFootView: View? = null
    private var mTouchSlop: Int? = 0
    private var mListView: ListView? = null
    private var mRecycleView: RecyclerView? = null
    private var mDownY: Int? = 0
    private var mUpY: Int? = 0
    private var mItemCount: Int? = 0
    private var mIsLoading: Boolean? = false
    private var mListener: OnLoadMoreListener? = null


    init {
        mFootView = View.inflate(context, R.layout.view_refreshlayout_footview, null)
        mTouchSlop = ViewConfiguration.get(context).scaledTouchSlop
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        if (mListView == null || mRecycleView == null) {
            if (childCount > 0) {
                when (getChildAt(0)) {
                    is ListView -> {
                        mListView = getChildAt(0) as ListView
                        setListViewOnScroll()
                    }
                    is RecyclerView -> {
                        mRecycleView = getChildAt(0) as RecyclerView
                        setRecycleViewOnScroll()
                    }
                }
            }
        }
    }

    fun setListViewOnScroll(): Unit {
        mListView!!.setOnScrollListener(object : AbsListView.OnScrollListener {
            override fun onScroll(view: AbsListView?, firstVisibleItem: Int, visibleItemCount: Int, totalItemCount: Int) {

            }

            override fun onScrollStateChanged(view: AbsListView?, scrollState: Int) {
                if (isLoadMore()) {
                    loadData()
                }
            }
        })
    }

    fun setRecycleViewOnScroll(): Unit {
        mRecycleView!!.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (isLoadMore()) {
                    loadData()
                }
            }
        })
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        when (ev!!.action) {
            MotionEvent.ACTION_DOWN -> mDownY = ev.y.toInt()
            MotionEvent.ACTION_UP -> mUpY = ev.y.toInt()
            MotionEvent.ACTION_MOVE -> {
                if (isLoadMore()) loadData()
            }

        }
        return super.dispatchTouchEvent(ev)
    }

    /**
     * 是否满足加载更多条件
     */
    fun isLoadMore(): Boolean {
        val isUp: Boolean = (mDownY!!.toInt() - mUpY!!.toInt()) >= mTouchSlop!!.toInt()
        var isTouch: Boolean = false
        if (mListView != null && mListView!!.adapter != null) {
            if (mItemCount!! > 0) {
                if (mItemCount!!.toInt() < mListView!!.adapter.count) isTouch = false
                else isTouch = mListView!!.lastVisiblePosition == (mListView!!.adapter.count - 1)
            } else isTouch = mListView!!.lastVisiblePosition == (mListView!!.adapter.count - 1)
        }
        val isLoading = !mIsLoading!!
        return isUp && isTouch && isLoading
    }

    /**
     * 加载数据
     */
    fun loadData(): Unit {
        if (mListView != null) {
            mListener!!.onLoadMore()
            setLoading(true)

        }
    }

    /**
     * 设置条目数量
     */
    fun setItemCount(itemCount: Int): Unit {
        this.mItemCount = itemCount
    }

    /**
     * 设置加载状态
     */
    fun setLoading(isLoading: Boolean): Unit {
        this.mIsLoading = isLoading
        if (isLoading) {
            mListView!!.addFooterView(mFootView)
        } else {
            mListView!!.removeFooterView(mFootView)
            mUpY = 0
            mDownY = 0
        }
    }

    fun setOnLoadMoreListener(listener: OnLoadMoreListener): Unit {
        this.mListener = listener
    }
}

interface OnLoadMoreListener {
    fun onLoadMore(): Unit
}