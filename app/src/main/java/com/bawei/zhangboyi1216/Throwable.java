package com.bawei.zhangboyi1216;

import android.util.Log;

/**
 * date:2019/12/16
 * author:张博一(zhangboyi)
 * function:捕捉异常报错
 */
public class Throwable implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(Thread t, java.lang.Throwable e) {
        Log.e("zby","报错"+e.toString());
    }
}
