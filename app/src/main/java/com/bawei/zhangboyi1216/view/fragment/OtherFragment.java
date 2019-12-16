package com.bawei.zhangboyi1216.view.fragment;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.bawei.zhangboyi1216.R;
import com.bawei.zhangboyi1216.base.BaseFragment;
import com.bawei.zhangboyi1216.contract.IHomeContract;
import com.bawei.zhangboyi1216.model.bean.BaseBean;
import com.bawei.zhangboyi1216.presenter.HomePresenter;

/**
 * date:2019/12/16
 * author:张博一(zhangboyi)
 * function:
 */
public class OtherFragment extends BaseFragment<HomePresenter> implements IHomeContract.IView {

    private TextView textView;

    @Override
    protected HomePresenter providerPresenter() {
        return new HomePresenter();
    }

    @Override
    protected void initView(View inflate) {
        textView = inflate.findViewById(R.id.text_name);
    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_other;
    }

    @Override
    protected void initData() {
        presenter.getHomeData("手机");
        String key = getArguments().getString("key");
        textView.setText(key);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectAnimator scaleX = ObjectAnimator.ofFloat(textView, "scaleX", 0.1f, 1, 0.5f);
                ObjectAnimator scaleY = ObjectAnimator.ofFloat(textView, "scaleY", 0.1f, 0, 0.5f);
                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.setDuration(5000);
                animatorSet.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationCancel(Animator animation) {
                        super.onAnimationCancel(animation);
                        Log.e("zby","播放完成"+animation);
                    }
                });
                animatorSet.playTogether(scaleY,scaleX);
                animatorSet.start();
            }
        });
    }

    public static OtherFragment getInstance(String values) {
        OtherFragment otherFragment = new OtherFragment();
        Bundle bundle = new Bundle();
        bundle.putString("key",values);
        otherFragment.setArguments(bundle);
        return otherFragment;
    }

    @Override
    public void onHomeSuccsess(BaseBean baseBean) {

    }

    @Override
    public void onHomeFailure(Throwable throwable) {

    }
}
