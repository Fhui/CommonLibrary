package com.example.huifeng.library.fragment

import com.example.huifeng.library.R
import com.example.huifeng.library.core.BaseFragment
import com.example.huifeng.library.utils.LogUtils
import rx.Observable
import rx.Observer


/**
 *  RXJava Fragment for Kotlin
 * Created by ShineF on 2017/8/9 0009.
 */
class RxJavaFragment : BaseFragment() {

    override fun setContentLayout(): Int = R.layout.fragment_rx_java

    override fun init() {
        super.init()
//        var observable = Observable.create<String>({ e ->
//            (e.onNext("create observable"))
//        })
//        var observer = Observer(object : Observer<String> {
//            override fun onCompleted() {
//
//            }
//
//            override fun onNext(t: String?) {
//
//            }
//
//            override fun onError(e: Throwable?) {
//
//            }
//        })
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
    }

    override fun setTitle() {

    }


}