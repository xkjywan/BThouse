package com.bthouse.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.bthouse.App;
import com.bthouse.MainActivity;
import com.bthouse.R;
import com.bthouse.mvp.module.IloginMoudle;
import com.bthouse.mvp.module.UserResponse;
import com.bthouse.mvp.presenter.ILoginPresenter;
import com.bthouse.mvp.presenter.LoginPresenter;
import com.bthouse.mvp.view.IloginView;
import com.bthouse.mvp.view.LoginView;
import com.bthouse.util.NetUtil;
import com.bthouse.util.PreferenceUtils;
import com.bthouse.util.ToastUtil;
import com.bthouse.view.CustomTextView;
import com.bthouse.view.LoadingDialog;
import com.jude.swipbackhelper.SwipeBackHelper;
import butterknife.Bind;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity<ILoginPresenter> implements LoginView {
    @Bind(R.id.et_username)
    EditText mEtUsername;

    @Bind(R.id.et_passward)
    EditText mEtPassword;

    @Bind(R.id.title_bar)
    CustomTextView customTextView;

    @Bind(R.id.tv_wechat)
    TextView tv_wechat;

    public LoadingDialog mLoadingDialog;

    //记录默认的帐号
    private static String USER_NAME = "user_name_key";

    @Override
    protected ILoginPresenter createPresenter() {
        return new ILoginPresenter(this);
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {
        SwipeBackHelper.getCurrentPage(this)
                .setSwipeBackEnable(false);
        customTextView.setOnTextViewClickListener(new CustomTextView.OnTextViewClickListener(){
            @Override
            public void OnLeftImgClick() {
                super.OnLeftImgClick();
                finish();
//                overridePendingTransition(R.anim.in_from_right,R.anim.out_to_left);
            }

            @Override
            public void OnRightTvClick() {
                super.OnRightTvClick();
                //注册
                RegistActivity.startActivity();
                finish();
//                overridePendingTransition(R.anim.in_from_right,R.anim.out_to_left);
            }
        });
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLoadingDialog = new LoadingDialog(LoginActivity.this, "");
        String userName = PreferenceUtils.getPrefString(this, USER_NAME, "");
        mEtUsername.setText(userName);
        mEtUsername.setSelection(userName.length());//将光标移至文字末尾
    }

    @OnClick({R.id.tv_login, R.id.tv_forget_passward,R.id.tv_wechat})
    public void Onclick(View v) {
        switch (v.getId()) {
            case R.id.tv_login:

                /*MainActivity.startActivity(this);
                finish();
                overridePendingTransition(R.anim.in_from_right,R.anim.out_to_left);*/


                /*String userName = mEtUsername.getText().toString().trim();
                String userPwd = mEtPassword.getText().toString().trim();
                if (null == userName || userName.length() < 3) {
                    ToastUtil.show(this, "请输入用户名");
                    return;
                }
                if (null == userPwd || userPwd.length() < 6) {
                    ToastUtil.show(this, "请输入用户密码6－12位");
                    return;
                }
                if (mLoadingDialog != null)
                    mLoadingDialog.show();
                mPresenter.login(userName, userPwd);*/
                mPresenter.login("123656354","13324234");
                break;

            case R.id.tv_forget_passward:

                break;
            case R.id.tv_wechat:
                //微信登录
                WechatLoginActivity.startActivity();
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
        if(!NetUtil.isConnected(LoginActivity.this)){
            ToastUtil.show(LoginActivity.this,"网络异常");
            return;
        }
        ToastUtil.show(LoginActivity.this, "登陆失败");


    }

    @Override
    public void onLoginSuc(IloginMoudle response) {

        IloginMoudle iloginMoudle = response;

    }

    /*@Override
    public void onLoginSuc(UserResponse response) {
        if (null != response) {
            MainActivity.startActivity(LoginActivity.this);
            UserResponse user = mApp.getUserBean();
            user.setUserId(response.getUserId());
            user.setPhone(response.getPhone());
            user.setName(response.getName());
            user.setImgUrl(response.getImgUrl());
            user.setLoginName(response.getLoginName());
            user.setRefereeRole(response.getRefereeRole());
            user.setRefereeRoleName(response.getRefereeRoleName());
            App.getInstance().setUserBean(user);

            App.getInstance().getaCache().put("IsLogin","true");

            PreferenceUtils.setPrefString(this, USER_NAME, response.getLoginName());
            finish();
        }
    }*/

    public static void startActivity() {
        Intent intent = new Intent(App.getContext(), LoginActivity.class);
        Bundle bundle = new Bundle();
        intent.putExtras(bundle);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        App.getContext().startActivity(intent);
    }


}
