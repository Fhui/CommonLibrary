package com.example.huifeng.library.fragment

import com.example.huifeng.library.R
import com.example.huifeng.library.core.BaseFragment
import com.example.huifeng.library.utils.LogUtils
import rx.Observable
import rx.Observer
import rx.functions.Action1


/**
 *  RXJava Fragment for Kotlin
 * Created by ShineF on 2017/8/9 0009.
 */
class RxJavaFragment : BaseFragment() {

    override fun setContentLayout(): Int = R.layout.fragment_rx_java

    override fun init() {
        super.init()
        Observable.create<String>({ subscriber -> subscriber.onNext("info") })
                .subscribe(object : Observer<String> {
                    override fun onCompleted() {
                        LogUtils.showLog("onComplete")
                    }

                    override fun onError(e: Throwable) {
                        LogUtils.showErrLog("error----->" + e.message)
                    }

                    override fun onNext(s: String) {
                        LogUtils.showErrLog("info----->" + s)
                    }
                })

        Observable.just("this", "is", "Observable").subscribe({ t -> LogUtils.showErrLog(t) })
    }

    override fun setTitle() {

    }


}