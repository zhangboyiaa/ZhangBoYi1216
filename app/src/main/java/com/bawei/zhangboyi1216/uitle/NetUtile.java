package com.bawei.zhangboyi1216.uitle;

import android.widget.ImageView;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bawei.zhangboyi1216.App;
import com.bawei.zhangboyi1216.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

import java.util.Map;

/**
 * date:2019/12/16
 * author:张博一(zhangboyi)
 * function:网络判断封装类
 */
public class NetUtile {

    //单例
    private static NetUtile netUtile;
    //声明
    private final RequestQueue requestQueue;

    //调用
    private NetUtile(){
        requestQueue = Volley.newRequestQueue(App.app);
    }

    //单列声明
    public static NetUtile getInstance() {
        if (netUtile == null){
            synchronized (NetUtile.class){
                if (netUtile == null){
                    netUtile = new NetUtile();
                }
            }
        }
        return netUtile;
    }

    //get请求方式
    public void getJsonGet(String getUrl,final MyCallBack myCallBack){
        StringRequest stringRequest = new StringRequest(StringRequest.Method.GET, getUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                myCallBack.ongetJson(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                myCallBack.onError(error);
            }
        });
        requestQueue.add(stringRequest);
    }

    //post请求方式
    public void getJsonPost(String postUrl, final Map<String, String> map, final MyCallBack myCallBack){
        StringRequest stringRequest = new StringRequest(StringRequest.Method.POST, postUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                myCallBack.ongetJson(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                myCallBack.onError(error);
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return map;
            }
        };
        requestQueue.add(stringRequest);
    }

    //图片请求方式
    public void getJsonPhoto(String photoUrl, ImageView imageView){
        Glide.with(imageView)
                .load(photoUrl)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(50)))
                .into(imageView);
    }

    //借口对调
    public interface MyCallBack{
        void ongetJson(String json);

        void onError(Throwable throwable);
    }
}
