package com.bthouse.ui.activity;

import android.accounts.Account;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.bthouse.App;
import com.bthouse.R;
import com.bthouse.mvp.presenter.RegisterPresenter;
import com.bthouse.mvp.view.RegisterView;
import com.bthouse.util.CheckUtil;
import com.bthouse.util.NetUtil;
import com.bthouse.util.ToastUtil;
import com.bthouse.view.CustomTextView;
import com.jude.swipbackhelper.SwipeBackHelper;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;
import butterknife.OnClick;


/**
 * 手机注册界面，找回密码界面
 */

public class PhoneRegistActivity extends BaseActivity<RegisterPresenter> implements RegisterView {
    @Bind(R.id.et_username)
    EditText et_username;

    @Bind(R.id.et_code)
    EditText et_code;

    @Bind(R.id.title_bar)
    CustomTextView customTextView;

    @Bind(R.id.getcode)
    TextView getcode;

    @Bind(R.id.bt_next)
    Button bt_next;


    private Handler handler;
    private boolean isRegister;

    @Override
    protected RegisterPresenter createPresenter() {
        return new RegisterPresenter(this);
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_phone_regist;
    }

    @Override
    public void initView() {

        //业务状态　注册　找回密码
        isRegister = getIntent().getBooleanExtra("isRegister",false);

        SwipeBackHelper.getCurrentPage(this)
                .setSwipeBackEnable(false);
        customTextView.setOnTextViewClickListener(new CustomTextView.OnTextViewClickListener(){
            @Override
            public void OnLeftImgClick() {
                super.OnLeftImgClick();
                finish();
            }
        });

        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                if (msg.what == 1) {

                } else if (msg.what == 2) {
                    getcode.setClickable(false);
                    getcode.setTextColor(Color.parseColor("#999999"));
                    getcode.setText(msg.obj + "S");
                } else if (msg.what == 3) {
                    getcode.setClickable(true);
                    getcode.setTextColor(Color.parseColor("#71AAF7"));
                    getcode.setText("重新获取");
//                    tv_forgetpsw.setText("收不到短信验证码？语音获取验证码");
                } else if (msg.what == 4){
//                    iv_code.setImageBitmap(bit);
                }
                return false;
            }
        });

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {

                    if (!TextUtils.isEmpty(et_username.getText())&&!TextUtils.isEmpty(et_code.getText())){
                        bt_next.setEnabled(true);
                    }else{
                        bt_next.setEnabled(false);
                    }
                }
        };

        et_username.addTextChangedListener(textWatcher);
        et_code.addTextChangedListener(textWatcher);

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private int number = 60;
    @OnClick({R.id.bt_next,R.id.getcode,R.id.et_username,R.id.tv_regist_change})
    public void Onclick(View v) {
        switch (v.getId()) {
            case R.id.bt_next:
                //验证验证码成功后跳转
                if(CheckUtil.checkPhoneNumber(et_username.getText())){
                    if (isRegister){
                        mPresenter.CheckcPhoneCode(et_username.getText().toString(),"+86",et_code.getText().toString());
                    }else{
                        mPresenter.FindPswByPhoneCode(et_username.getText().toString(),"+86",et_code.getText().toString());
                    }

//                    Account.CREATOR(new Acc)

                }else{
                    ToastUtil.show(this,"请输入正确的手机号");
                }

                break;
            case R.id.getcode:
                if(!CheckUtil.checkPhoneNumber(et_username.getText())){

                    ToastUtil.show(this,"请输入正确的手机号");
                    return;
                }

                final Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        Message msg = new Message();
                        if (number == 0) {
                            msg.what = 3;
                            timer.cancel();
                        } else {
                            msg.what = 2;
                            msg.obj = number;
                            number--;
                        }
                        handler.sendMessage(msg);
                    }
                }, 0, 1000);
                    mPresenter.getPhoneCode(et_username.getText().toString(),"+86","1");

                break;

            case R.id.tv_regist_change:
                //切换注册方式
                EmailRegistActivity.startActivity(isRegister);
                break;

        }
    }

    @Override
    public void onFinish() {
        hidingLoading();
    }

    @Override
    public void onError() {
        hidingLoading();
        if(!NetUtil.isConnected(PhoneRegistActivity.this)){
            ToastUtil.show(PhoneRegistActivity.this,"网络异常");
            return;
        }
        ToastUtil.show(PhoneRegistActivity.this, "失败");
    }


    @Override
    public void onCheckCodeSuccessed() {
        //验证电话和邮箱验证码成功
        SetPswActivity.startActivity("",et_username.getText().toString().trim());

    }


    public static void startActivity(boolean isRegister) {
        Intent intent = new Intent(App.getContext(), PhoneRegistActivity.class);
        intent.putExtra("isRegister",isRegister);
        App.getContext().startActivity(intent);
    }


}
