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
import com.bthouse.mvp.module.UserResponse;
import com.bthouse.mvp.presenter.LoginPresenter;
import com.bthouse.mvp.view.IloginView;
import com.bthouse.util.NetUtil;
import com.bthouse.util.PreferenceUtils;
import com.bthouse.util.ToastUtil;
import com.bthouse.view.CustomTextView;
import com.bthouse.view.LoadingDialog;
import com.jude.swipbackhelper.SwipeBackHelper;

import butterknife.Bind;
import butterknife.BindInt;
import butterknife.OnClick;

/**
 * 设置密码,昵称
 */
public class SetPswActivity extends BaseActivity<LoginPresenter> implements IloginView {
    @Bind(R.id.et_psw)
    EditText et_psw;

    @Bind(R.id.et_psw_confirm)
    EditText et_psw_confirm;

    @Bind(R.id.tv_confirm)
    TextView tv_confirm;

    @Bind(R.id.title_bar)
    CustomTextView customTextView;

    public LoadingDialog mLoadingDialog;

    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenter(this);
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_psw;
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
            }

            @Override
            public void OnRightTvClick() {
                super.OnRightTvClick();
                //注册
                RegistActivity.startActivity();
            }
        });
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLoadingDialog = new LoadingDialog(SetPswActivity.this, "");
//        String userName = PreferenceUtils.getPrefString(this, USER_NAME, "");
//        mEtUsername.setText(userName);
//        mEtUsername.setSelection(userName.length());//将光标移至文字末尾
    }

    @OnClick({R.id.tv_login, R.id.tv_forget_passward})
    public void Onclick(View v) {
        switch (v.getId()) {
            case R.id.tv_login:
                MainActivity.startActivity(this);
                finish();

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
                break;

            case R.id.tv_forget_passward:

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
        if(!NetUtil.isConnected(SetPswActivity.this)){
            ToastUtil.show(SetPswActivity.this,"网络异常");
            return;
        }
        ToastUtil.show(SetPswActivity.this, "登陆失败");
    }

    @Override
    public void onLoginSuc(UserResponse response) {
        if (null != response) {
            MainActivity.startActivity(SetPswActivity.this);
           /* UserResponse user = mApp.getUserBean();
            user.setUserId(response.getUserId());
            user.setPhone(response.getPhone());
            user.setName(response.getName());
            user.setImgUrl(response.getImgUrl());
            user.setLoginName(response.getLoginName());
            user.setRefereeRole(response.getRefereeRole());
            user.setRefereeRoleName(response.getRefereeRoleName());
            App.getInstance().setUserBean(user);

            App.getInstance().getaCache().put("IsLogin","true");*/

            finish();
        }
    }

    /**
     *
     * @param type  注册类型
     * @param username　注册用户名
     */
    public static void startActivity(String type,String username) {
        Intent intent = new Intent(App.getContext(), SetPswActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("type",type);
        bundle.putString("username",username);
        intent.putExtras(bundle);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        App.getContext().startActivity(intent);
    }


}
