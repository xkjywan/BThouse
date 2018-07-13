package com.bthouse.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bthouse.MainActivity;
import com.bthouse.R;
import com.bthouse.adapter.SearchCollectAdapter;
import com.bthouse.mvp.module.SearchCollectBean;
import com.bthouse.mvp.presenter.SearchCollectPresenter;
import com.bthouse.mvp.view.SearchCollectView;
import com.bthouse.util.NetUtil;
import com.bthouse.util.ToastUtil;
import com.chaychan.uikit.powerfulrecyclerview.PowerfulRecyclerView;
import java.util.ArrayList;
import java.util.List;
import butterknife.Bind;
import butterknife.OnClick;

/**
 * 收藏的搜索
 */

public class TwoFragment extends BaseFragment<SearchCollectPresenter> implements SearchCollectView {

    @Bind(R.id.rlv_collect)
    PowerfulRecyclerView rlv_collect;

    private MainActivity mMainActivity;

    private List<BaseFragment> mFragments;

    private ArrayList<SearchCollectBean> searchCollectBeans = new ArrayList<SearchCollectBean>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        mMainActivity = (MainActivity) getActivity();
        return view;
    }

    @Override
    protected View initContentLayout(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_two, container, false);
    }

    @Override
    public void refClass() {
    }

    @Override
    protected SearchCollectPresenter createPresenter() {
        return new SearchCollectPresenter(this);
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
        mFragments = new ArrayList<>();
        mFragments.add(new TwoFragment());
        for (int i=0;i<5;i++){
            SearchCollectBean searchCollectBean = new SearchCollectBean();
            searchCollectBeans.add(searchCollectBean);
        }
        SearchCollectAdapter searchCollectAdapter = new SearchCollectAdapter(getContext(), searchCollectBeans);
        rlv_collect.setAdapter(searchCollectAdapter);
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
    }

    @Override
    public void onError() {
       if(!NetUtil.isConnected(getActivity())){
            ToastUtil.show(getActivity(),"网络异常");
            return;
        }
        ToastUtil.show(getActivity(),"退出登陆失败");
    }

    @Override
    public void onSucc(List<SearchCollectBean> collects) {
        //获取到收藏数据

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mLoadingDialog=null;
    }
}
