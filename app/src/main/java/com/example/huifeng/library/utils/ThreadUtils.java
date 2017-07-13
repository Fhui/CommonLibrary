package com.example.huifeng.library.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *  线程工具类
 * Created by ShineF on 2017/7/10 0010.
 */

public class ThreadUtils {

    static ExecutorService cachedThreadPool = null;

    public static void newThread(Runnable runnable) {

        if (cachedThreadPool == null) {
            cachedThreadPool = Executors.newCachedThreadPool();
        }
        cachedThreadPool.execute(runnable);
    }

}
