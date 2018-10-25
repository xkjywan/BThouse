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
import com.bthouse.util.UIUtils;
import com.bthouse.view.CustomTextView;
import com.bthouse.view.LoadingDialog;
import com.jude.swipbackhelper.SwipeBackHelper;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;

import butterknife.Bind;
import butterknife.OnClick;

public class WechatLoginActivity extends BaseActivity<LoginPresenter> implements IloginView {

    @Bind(R.id.title_bar)
    CustomTextView customTextView;

    @Bind(R.id.tv_bang_wechat)
    TextView tv_bang_wechat;

    @Bind(R.id.tv_new_wechat)
    TextView tv_new_wechat;

    public LoadingDialog mLoadingDialog;

    //记录默认的帐号
    private static String USER_NAME = "user_name_key";

    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenter(this);
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_wechat_login;
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
            }});
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLoadingDialog = new LoadingDialog(WechatLoginActivity.this, "");
        String userName = PreferenceUtils.getPrefString(this, USER_NAME, "");
    }


    /**
     * 微信登录相关
     */
    private IWXAPI api;

    @OnClick({R.id.tv_new_wechat,R.id.tv_bang_wechat})
    public void Onclick(View v) {
        switch (v.getId()) {
            case R.id.tv_new_wechat:
                RegistActivity.startActivity();
                finish();
                break;
            case R.id.tv_bang_wechat:
                if (!App.mWxApi.isWXAppInstalled()) {
                    UIUtils.showToast("您还未安装微信客户端");
                    return;
                }
                final SendAuth.Req req = new SendAuth.Req();
                req.scope = "snsapi_userinfo";
                req.state = "diandi_wx_login";
                App.mWxApi.sendReq(req);

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
        if(!NetUtil.isConnected(WechatLoginActivity.this)){
            ToastUtil.show(WechatLoginActivity.this,"网络异常");
            return;
        }
        ToastUtil.show(WechatLoginActivity.this, "登陆失败");
    }

    @Override
    public void onLoginSuc(UserResponse response) {
        if (null != response) {
            MainActivity.startActivity(WechatLoginActivity.this);
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

            PreferenceUtils.setPrefString(this, USER_NAME, response.getLoginName());
            finish();
        }
    }

    public static void startActivity() {
        Intent intent = new Intent(App.getContext(), WechatLoginActivity.class);
        Bundle bundle = new Bundle();
        intent.putExtras(bundle);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        App.getContext().startActivity(intent);
    }


}
