package com.bawei.zhangboyi1216.contract;

import com.bawei.zhangboyi1216.model.bean.BaseBean;

/**
 * date:2019/12/16
 * author:张博一(zhangboyi)
 * function:契约接口类
 */
public interface IHomeContract {

    interface IView{
        void onHomeSuccsess(BaseBean baseBean);

        void onHomeFailure(Throwable throwable);
    }

    interface IPresenter{
        void getHomeData(String keyword);
    }

    interface IModel{
        void getHomeData(String keyword,IModelCallBack iModelCallBack);

        interface IModelCallBack{
            void onHomeSuccsess(BaseBean baseBean);

            void onHomeFailure(Throwable throwable);
        }
    }
}
