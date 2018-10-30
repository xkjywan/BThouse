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
import com.bthouse.mvp.presenter.SetPswPresenter;
import com.bthouse.mvp.view.IloginView;
import com.bthouse.mvp.view.SetPswView;
import com.bthouse.util.CheckUtil;
import com.bthouse.util.NetUtil;
import com.bthouse.util.PreferenceUtils;
import com.bthouse.util.ToastUtil;
import com.bthouse.view.CustomTextView;
import com.bthouse.view.LoadingDialog;
import com.jude.swipbackhelper.SwipeBackHelper;
import com.jude.swipbackhelper.Utils;

import butterknife.Bind;
import butterknife.BindInt;
import butterknife.OnClick;

/**
 * 设置密码,昵称
 */
public class SetPswActivity extends BaseActivity<SetPswPresenter> implements SetPswView {
    @Bind(R.id.et_psw)
    EditText et_psw;

    @Bind(R.id.et_psw_confirm)
    EditText et_psw_confirm;

    @Bind(R.id.tv_confirm)
    TextView tv_confirm;

    @Bind(R.id.et_nickname)
    EditText et_nickname;

    @Bind(R.id.title_bar)
    CustomTextView customTextView;

    public LoadingDialog mLoadingDialog;

    @Override
    protected SetPswPresenter createPresenter() {
        return new SetPswPresenter(this);
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
    }

    @OnClick({R.id.tv_confirm})
    public void Onclick(View v) {
        switch (v.getId()) {
            case R.id.tv_confirm:
                String type = getIntent().getStringExtra("type");
                String username = getIntent().getStringExtra("username");
                if (!et_psw.getText().toString().equals(et_psw_confirm.getText().toString()) && CheckUtil.isNull(et_psw.getText().toString())){
                    ToastUtil.show(this,"密码请保持一致");
                }else if(CheckUtil.isNull(et_nickname.getText().toString().trim())){
                    ToastUtil.show(this,"请输入昵称");
                }else{
                    mPresenter.regphone(username,type,et_psw.getText().toString(),et_psw_confirm.getText().toString(),et_nickname.getText().toString());
                }
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
    public void onSuccess() {

    }

    @Override
    public void onSetPswSuc() {

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
