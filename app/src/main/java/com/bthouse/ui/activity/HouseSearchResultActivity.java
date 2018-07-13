package com.bthouse.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.bthouse.App;
import com.bthouse.R;
import com.bthouse.adapter.HouseResultAdapter;
import com.bthouse.mvp.module.UserResponse;
import com.bthouse.mvp.presenter.LoginPresenter;
import com.bthouse.mvp.view.IloginView;
import com.bthouse.ui.fragment.HouseAdvancedSearch01Fragment;
import com.bthouse.ui.fragment.HouseAdvancedSearchFragment;
import com.bthouse.util.NetUtil;
import com.bthouse.util.PreferenceUtils;
import com.bthouse.util.ToastUtil;
import com.bthouse.view.CustomTextView;
import com.bthouse.view.LoadingDialog;
//import com.bthouse.view.PPDTabLayout;
import com.bthouse.view.PPDTabLayout;
import com.jude.swipbackhelper.SwipeBackHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 搜索结果
 */

public class HouseSearchResultActivity extends BaseActivity<LoginPresenter> implements IloginView {
    @Bind(R.id.title_bar)
    CustomTextView title_bar;

    @Bind(R.id.tabLayout)
    PPDTabLayout tabLayout;

    @Bind(R.id.viewPager)
    ViewPager viewPager;

    public LoadingDialog mLoadingDialog;

    //记录默认的帐号
    private static String USER_NAME = "user_name_key";

    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenter(this);
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_search_result;
    }

    @Override
    public void initView() {
        SwipeBackHelper.getCurrentPage(this)
                .setSwipeBackEnable(false);

        tabLayout.setupWithViewPager(viewPager);

        String used_no = "优质民宿";
        String expiry_no = "学生公寓";
        List<String> mTitles = new ArrayList<String>();
        mTitles.add(used_no);
        mTitles.add(expiry_no);

        HouseResultAdapter houseResultAdapter = new HouseResultAdapter(getSupportFragmentManager(),mTitles,this);
        viewPager.setAdapter(houseResultAdapter);

        title_bar.setOnTextViewClickListener(new CustomTextView.OnTextViewClickListener() {
            @Override
            public void OnLeftImgClick() {
                super.OnLeftImgClick();
                finish();
            }
        });
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @OnClick({R.id.tv_advanced,R.id.tv_paixu})
    public void Onclick(View v) {
        switch (v.getId()) {
            case R.id.tv_advanced:
                HouseAdvancedSearchFragment houseAdvancedSearchFragment = new HouseAdvancedSearchFragment().newInstance(this);
                houseAdvancedSearchFragment.show(getSupportFragmentManager(),"");
                break;
            case R.id.tv_paixu:
                HouseAdvancedSearch01Fragment houseAdvancedSearch01Fragment = new HouseAdvancedSearch01Fragment().newInstance(this);
                houseAdvancedSearch01Fragment.show(getSupportFragmentManager(),"");
                break;

        }
    }

    @Override
    public void onFinish() {
        if (mLoadingDialog != null)
            mLoadingDialog.dismiss();
    }

    @Override
    public void onError() {
        if (mLoadingDialog != null)
            mLoadingDialog.dismiss();
        if(!NetUtil.isConnected(HouseSearchResultActivity.this)){
            ToastUtil.show(HouseSearchResultActivity.this,"网络异常");
            return;
        }
        ToastUtil.show(HouseSearchResultActivity.this, "登陆失败");


    }

    @Override
    public void onLoginSuc(UserResponse response) {

    }

    public static void startActivity() {
        Intent intent = new Intent(App.getContext(), HouseSearchResultActivity.class);
        Bundle bundle = new Bundle();
        intent.putExtras(bundle);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        App.getContext().startActivity(intent);
    }


}
