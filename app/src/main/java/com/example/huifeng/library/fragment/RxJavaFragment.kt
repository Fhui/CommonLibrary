package com.example.huifeng.library.fragment

import com.example.huifeng.library.R
import com.example.huifeng.library.core.BaseFragment
import rx.Observable




/**
 *  RXJava Fragment for Kotlin
 * Created by ShineF on 2017/8/9 0009.
 */
class RxJavaFragment : BaseFragment() {

    override fun setContentLayout(): Int = R.layout.fragment_rx_java

    override fun init() {
        super.init()
//        var observable = Observable.create<String>( { e ->
//            (e.onNext("create observable"))
//        })

    }

    override fun setTitle() {

    }


}