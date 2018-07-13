package com.bthouse.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.bthouse.MainActivity;
import com.bthouse.R;
import com.bthouse.adapter.HouseCollectAdapter;
import com.bthouse.mvp.module.HouseCollectBean;
import com.bthouse.mvp.module.ResultResponse;
import com.bthouse.mvp.module.SearchCollectBean;
import com.bthouse.mvp.presenter.LogoutPresenter;
import com.bthouse.mvp.view.ILogoutView;
import com.bthouse.util.UIUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chaychan.uikit.powerfulrecyclerview.PowerfulRecyclerView;
import com.chaychan.uikit.refreshlayout.BGANormalRefreshViewHolder;
import com.chaychan.uikit.refreshlayout.BGARefreshLayout;
import com.github.nukc.stateview.StateView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

public class ThreeFragment extends BaseFragment<LogoutPresenter> implements ILogoutView, BGARefreshLayout.BGARefreshLayoutDelegate, BaseQuickAdapter.RequestLoadMoreListener {

    @Bind(R.id.rlv_collect)
    PowerfulRecyclerView rlv_collect;

    @Bind(R.id.refresh_layout)
    BGARefreshLayout mRefreshLayout;

    @Bind(R.id.fl_content)
    FrameLayout mFlContent;

    protected StateView mStateView;
    private MainActivity mMainActivity;

    private List<BaseFragment> mFragments;

    private ArrayList<HouseCollectBean> houseCollectBeans = new ArrayList<HouseCollectBean>();

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
        return inflater.inflate(R.layout.fragment_three, container, false);
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
        mStateView = StateView.inject(mFlContent);
        mStateView.setLoadingResource(R.layout.page_loading);
        mStateView.setRetryResource(R.layout.page_net_error);
        mStateView.setRetryResource(R.layout.pager_no_comment);
        mStateView.setOnRetryClickListener(new StateView.OnRetryClickListener() {
            @Override
            public void onRetryClick() {
                loadData();
            }
        });
        mRefreshLayout.setDelegate(this);
        BGANormalRefreshViewHolder bgaNormalRefreshViewHolder = new BGANormalRefreshViewHolder(mActivity, true);
        mRefreshLayout.setRefreshViewHolder(bgaNormalRefreshViewHolder);
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
        mFragments = new ArrayList<>();
        mFragments.add(new ThreeFragment());

        for (int i=0;i<5;i++){
            HouseCollectBean houseCollectBean = new HouseCollectBean();
            houseCollectBeans.add(houseCollectBean);
        }
        HouseCollectAdapter searchCollectAdapter = new HouseCollectAdapter(getContext(), houseCollectBeans);

        rlv_collect.setAdapter(searchCollectAdapter);
        //接口请求调用
//        mStateView.showLoading();
    }

    @OnClick({})
    public void onClick(View v) {
        switch (v.getId()) {
           /* case R.id.lyt_connect_us:
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+4008));
                startActivity(intent);
                break;
            case R.id.bt_logout:
                mPresenter.logout(mApp.getUserBean().getUserId());

                break;*/
        }
    }

    @Override
    public void onFinish() {
        mRefreshLayout.endRefreshing();
        mRefreshLayout.endLoadingMore();
    }

    @Override
    public void onError() {
        //失败 点击重新请求
        mStateView.showRetry();
    }

    @Override
    public void onLogoutSuc(ResultResponse response) {
     //请求成功
        mStateView.showContent();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mLoadingDialog=null;
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        //page =1
        loadData();
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        return false;
    }

    @Override
    public void onLoadMoreRequested() {
        loadData();
    }
}
