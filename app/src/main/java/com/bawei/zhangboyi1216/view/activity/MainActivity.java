package com.bawei.zhangboyi1216.view.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

import com.bawei.zhangboyi1216.R;
import com.bawei.zhangboyi1216.base.BaseActivity;
import com.bawei.zhangboyi1216.base.BasePresenter;
import com.bawei.zhangboyi1216.presenter.HomePresenter;
import com.bawei.zhangboyi1216.view.fragment.HomeFragment;
import com.bawei.zhangboyi1216.view.fragment.OtherFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 张博一
 * 1707A
 * 移动智能安卓
 * 2019.12.16
 * 主页面
 **/
public class MainActivity extends BaseActivity {


    private ViewPager viewPager;
    private RadioGroup radioGroup;
    List<Fragment> list = new ArrayList<>();

    @Override
    protected void initData() {
        HomeFragment homeFragment = new HomeFragment();
        list.add(homeFragment);

        OtherFragment otherFragment = OtherFragment.getInstance("分类");
        list.add(otherFragment);

        OtherFragment otherFragment1 = OtherFragment.getInstance("发现");
        list.add(otherFragment1);

        OtherFragment otherFragment2 = OtherFragment.getInstance("购物车");
        list.add(otherFragment2);

        OtherFragment otherFragment3 = OtherFragment.getInstance("我的");
        list.add(otherFragment3);

        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return list.get(i);
            }

            @Override
            public int getCount() {
                return list.size();
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.radio_btua:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.radio_btub:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.radio_btuc:
                        viewPager.setCurrentItem(2);
                        break;
                    case R.id.radio_btud:
                        viewPager.setCurrentItem(3);
                        break;
                    case R.id.radio_btue:
                        viewPager.setCurrentItem(4);
                        break;
                }
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                radioGroup.check(radioGroup.getChildAt(i).getId());
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    @Override
    protected void initView() {
        viewPager = findViewById(R.id.viewpager);
        radioGroup = findViewById(R.id.radio_group);
    }

    @Override
    protected BasePresenter providerPresenter() {
        return new HomePresenter();
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_main;
    }
}
