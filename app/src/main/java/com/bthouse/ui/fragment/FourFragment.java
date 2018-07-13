package com.bthouse.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bthouse.MainActivity;
import com.bthouse.R;
import com.bthouse.mvp.module.ResultResponse;
import com.bthouse.mvp.presenter.LogoutPresenter;
import com.bthouse.mvp.view.ILogoutView;
import com.bthouse.ui.activity.AboutMeActivity;
import com.bthouse.ui.activity.LoginActivity;
import com.bthouse.ui.activity.SettingActivity;
import com.bthouse.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

public class FourFragment extends BaseFragment<LogoutPresenter> implements ILogoutView {

    @Bind(R.id.ll_login)
    LinearLayout ll_login;

    @Bind(R.id.tv_publish)
    TextView mTvPublish;

    private MainActivity mMainActivity;

    private List<BaseFragment> mFragments;

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
    @OnClick({R.id.tv_publish, R.id.ll_about_me,R.id.ll_setting,R.id.ll_my_order,R.id.ll_my_house_resource,R.id.tv_login})
    public void Onclick(View v) {
        switch (v.getId()) {
            case R.id.tv_publish:
                ToastUtil.show(getActivity(),"发布房源");
                break;

            case R.id.ll_about_me:
                AboutMeActivity.startActivity();
                break;
            case R.id.ll_setting:
                SettingActivity.startActivity();
                break;
            case R.id.ll_my_order:

                break;
            case R.id.ll_my_house_resource:

                break;

            case R.id.tv_login:
                LoginActivity.startActivity();
                break;
        }
    }

    public void initTitleBar() {
//        mTitleBar.getCenterTv().setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
    }

    @Override
    protected View initContentLayout(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_four, container, false);
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
        mFragments = new ArrayList<>();
        mFragments.add(new FourFragment());
    }

    @OnClick({R.id.ll_login})
    public void onClick(View v) {
        switch (v.getId()) {
           /* case R.id.lyt_connect_us:
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+4008));
                startActivity(intent);
                break;*/
            case R.id.ll_login:
//                mPresenter.logout(mApp.getUserBean().getUserId());
                LoginActivity.startActivity();
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
