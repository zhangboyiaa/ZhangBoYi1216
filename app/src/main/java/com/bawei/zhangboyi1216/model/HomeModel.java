package com.bawei.zhangboyi1216.model;

import com.bawei.zhangboyi1216.contract.IHomeContract;
import com.bawei.zhangboyi1216.model.bean.BaseBean;
import com.bawei.zhangboyi1216.uitle.NetUtile;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.URLEncoder;
import java.security.Key;

/**
 * date:2019/12/16
 * author:张博一(zhangboyi)
 * function:Model层
 */
public class HomeModel implements IHomeContract.IModel {
    @Override
    public void getHomeData(String keyword, final IModelCallBack iModelCallBack) {
        try {
            String encode = URLEncoder.encode(keyword, "UTF-8");
            String url = "http://blog.zhaoliang5156.cn/baweiapi/手机?page=1&count=5&keyword="+encode;
            NetUtile.getInstance().getJsonGet(url, new NetUtile.MyCallBack() {
                @Override
                public void ongetJson(String json) {
                    BaseBean baseBean = new Gson().fromJson(json, BaseBean.class);
                    iModelCallBack.onHomeSuccsess(baseBean);
                }

                @Override
                public void onError(Throwable throwable) {
                    iModelCallBack.onHomeFailure(throwable);
                }
            });
        }catch (IOException i){
            i.printStackTrace();
        }
    }
}
