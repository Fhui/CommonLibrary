package com.example.huifeng.library.mvp.base;

/**
 * Model 顶层接口
 * Created by hui.feng on 2018/4/2.
 */

public interface ILoadListener {

    void success(Object object);

    void error(String code, String msg);

}
