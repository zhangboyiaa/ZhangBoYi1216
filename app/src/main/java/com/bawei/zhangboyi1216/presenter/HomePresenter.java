package com.bawei.zhangboyi1216.presenter;

import com.bawei.zhangboyi1216.base.BasePresenter;
import com.bawei.zhangboyi1216.contract.IHomeContract;
import com.bawei.zhangboyi1216.model.HomeModel;
import com.bawei.zhangboyi1216.model.bean.BaseBean;

/**
 * date:2019/12/16
 * author:张博一(zhangboyi)
 * function:p层
 */
public class HomePresenter extends BasePresenter<IHomeContract.IView> implements IHomeContract.IPresenter {

    private HomeModel homeModel;

    @Override
    protected void initModel() {
        homeModel = new HomeModel();
    }

    @Override
    public void getHomeData(String keyword) {
        homeModel.getHomeData(keyword, new IHomeContract.IModel.IModelCallBack() {
            @Override
            public void onHomeSuccsess(BaseBean baseBean) {
                view.onHomeSuccsess(baseBean);
            }

            @Override
            public void onHomeFailure(Throwable throwable) {
                view.onHomeFailure(throwable);
            }
        });
    }
}
