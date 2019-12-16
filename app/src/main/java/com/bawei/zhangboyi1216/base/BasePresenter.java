package com.bawei.zhangboyi1216.base;

/**
 * date:2019/12/16
 * author:张博一(zhangboyi)
 * function:Presenter封装基类
 */
public abstract class BasePresenter<V> {

    protected V view;

    public BasePresenter(){
        initModel();
    }

    protected abstract void initModel();

    public void attach(V view){
        this.view = view;
    }

    public void detach(){
        view = null;
    }
}
