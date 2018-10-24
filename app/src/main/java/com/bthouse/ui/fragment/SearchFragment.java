package com.bthouse.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.bthouse.MainActivity;
import com.bthouse.R;
import com.bthouse.mvp.module.ResultResponse;
import com.bthouse.mvp.presenter.LogoutPresenter;
import com.bthouse.mvp.view.ILogoutView;
import com.bthouse.ui.activity.SearchActivity;

import butterknife.Bind;
import butterknife.OnClick;

public class SearchFragment extends BaseFragment<LogoutPresenter> implements ILogoutView {
    @Bind(R.id.search)
    LinearLayout search;

    private MainActivity mMainActivity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        mMainActivity = (MainActivity) getActivity();
        initTitleBar();

        return view;
    }

    public void initTitleBar() {
//        mTitleBar.getCenterTv().setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
    }

    @Override
    protected View initContentLayout(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void refClass() {

    }

    @Override
    protected LogoutPresenter createPresenter() {
        return new LogoutPresenter(this);
    }

    @Override
    public void initView(View rootView) {
        super.initView(rootView);
    }

    @Override
    protected int provideContentViewId() {
        return 0;
    }

    @Override
    protected void onFragmentVisibleChange(boolean isVisible) {
        super.onFragmentVisibleChange(isVisible);
    }

    @Override
    protected void loadData() {

    }

    @OnClick({R.id.search})
    public void onClick(View v) {
        switch (v.getId()) {
             case R.id.search:
                 SearchActivity.startActivity();
                break;
        }
    }

    @Override
    public void onFinish() {

    }

    @Override
    public void onError() {
       /* if(!NetUtil.isConnected(getActivity())){
            ToastUtil.show(getActivity(),"网络异常");
            return;
        }
        ToastUtil.show(getActivity(),"退出登陆失败");*/
    }

    @Override
    public void onLogoutSuc(ResultResponse response) {
//        LoginActivity.startActivity();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mLoadingDialog=null;
    }
}
