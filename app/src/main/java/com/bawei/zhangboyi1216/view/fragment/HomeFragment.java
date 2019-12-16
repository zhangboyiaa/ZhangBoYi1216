package com.bawei.zhangboyi1216.view.fragment;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bawei.zhangboyi1216.R;
import com.bawei.zhangboyi1216.base.BaseFragment;
import com.bawei.zhangboyi1216.contract.IHomeContract;
import com.bawei.zhangboyi1216.model.HomeModel;
import com.bawei.zhangboyi1216.model.bean.BaseBean;
import com.bawei.zhangboyi1216.presenter.HomePresenter;
import com.bawei.zhangboyi1216.state.NetState;
import com.bawei.zhangboyi1216.view.zhidingyi.FlowLayout;

import java.util.List;

/**
 * date:2019/12/16
 * author:张博一(zhangboyi)
 * function:
 */
public class HomeFragment extends BaseFragment<HomePresenter> implements IHomeContract.IView {

    private FlowLayout flowLayout;
    private TextView textView1;
    private TextView textView;
    private ImageView imageView;

    @Override
    protected HomePresenter providerPresenter() {
        return new HomePresenter();
    }

    @Override
    protected void initView(View inflate) {
        textView = inflate.findViewById(R.id.text_title);
        textView1 = inflate.findViewById(R.id.text_sou);
        flowLayout = inflate.findViewById(R.id.flow);
        imageView = inflate.findViewById(R.id.imageview);
    }

    @Override
    protected int layoutId() {
        return R.layout.fragmenet_home;
    }

    @Override
    protected void initData() {
        if (NetState.getInstance().hasNet(getActivity())){
            presenter.getHomeData("手机");
            flowLayout.setVisibility(View.VISIBLE);
            textView.setVisibility(View.VISIBLE);
            textView1.setVisibility(View.VISIBLE);
            imageView.setVisibility(View.GONE);
            flowLayout.setOnTagClickListener(new FlowLayout.OnTagClickListener() {
                @Override
                public void onTagClick(String tag) {
                    Toast.makeText(getActivity(), tag, Toast.LENGTH_SHORT).show();
                }
            });

            textView1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    presenter.getHomeData("电脑");
                }
            });

            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ObjectAnimator scaleX = ObjectAnimator.ofFloat(textView, "scaleX", 0.1f, 1, 0.5f);
                    ObjectAnimator scaleY = ObjectAnimator.ofFloat(textView, "scaleY", 0.1f, 0, 0.5f);
                    AnimatorSet animatorSet = new AnimatorSet();
                    animatorSet.setDuration(2000);
                    animatorSet.addListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationCancel(Animator animation) {
                            super.onAnimationCancel(animation);
                            Toast.makeText(getActivity(), "播放完毕", Toast.LENGTH_SHORT).show();
                        }
                    });
                    animatorSet.playTogether(scaleY,scaleX);
                    animatorSet.start();
                }
            });
        }else {
            flowLayout.setVisibility(View.GONE);
            textView.setVisibility(View.GONE);
            textView1.setVisibility(View.GONE);
            imageView.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void onHomeSuccsess(BaseBean baseBean) {
        flowLayout.removeAllViews();
        List<String> tags = baseBean.getTags();
        for (int i = 0;i<tags.size(); i++){
            String s = tags.get(i);
            flowLayout.addTag(s);
        }


    }

    @Override
    public void onHomeFailure(Throwable throwable) {

    }
}
