package com.bthouse;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Process;

import com.bthouse.adapter.MainTabAdapter;
import com.bthouse.mvp.presenter.BasePresenter;
import com.bthouse.ui.activity.BaseActivity;
import com.bthouse.ui.fragment.BaseFragment;
import com.bthouse.ui.fragment.FourFragment;
import com.bthouse.ui.fragment.SearchFragment;
import com.bthouse.ui.fragment.ThreeFragment;
import com.bthouse.ui.fragment.TwoFragment;
import com.chaychan.library.BottomBarItem;
import com.chaychan.library.BottomBarLayout;
import com.chaychan.uikit.NoScrollViewPager;

import java.util.ArrayList;
import java.util.List;
import butterknife.Bind;

public class MainActivity extends BaseActivity {
    @Bind(R.id.vp_content)
    NoScrollViewPager mVpContent;

    @Bind(R.id.bottom_bar)
    BottomBarLayout mBottomBarLayout;

    private List<BaseFragment> mFragments;
    private MainTabAdapter mTabAdapter;
    private int mCurrTab = 0;
    public static MainActivity mainActivity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainActivity = this;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    public void initData() {
        mFragments = new ArrayList<>();
        mFragments.add(new SearchFragment());
        mFragments.add(new TwoFragment());
        mFragments.add(new ThreeFragment());
        mFragments.add(new FourFragment());
    }

    @Override
    public void initListener() {
        mTabAdapter = new MainTabAdapter(mFragments, getSupportFragmentManager());
        mVpContent.setAdapter(mTabAdapter);
        mVpContent.setOffscreenPageLimit(mFragments.size());
        mBottomBarLayout.setViewPager(mVpContent);
        //设置条目点击的监听
        mBottomBarLayout.setOnItemSelectedListener(new BottomBarLayout.OnItemSelectedListener() {
            @Override
            public void onItemSelected(BottomBarItem bottomBarItem, int position) {
                mCurrTab = position;
                mFragments.get(mCurrTab).refClass();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
//        版本检测
//        checkVersion();
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_main;
    }


    public static void startActivity(Activity fromActivity) {
        Intent intent = new Intent(fromActivity, MainActivity.class);
        Bundle bundle = new Bundle();
        intent.putExtras(bundle);
        fromActivity.startActivity(intent);
    }
}
