package com.bawei.zhangboyi1216;

import android.app.Application;

/**
 * date:2019/12/16
 * author:张博一(zhangboyi)
 * function:app类
 */
public class App extends Application {

    //全局
    public static App app;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        //Thread.setDefaultUncaughtExceptionHandler(new Throwable());
    }
}
