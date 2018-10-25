package com.bthouse.ui.activity;

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

public class RegistActivity extends BaseActivity<RegisterPresenter> implements RegisterView {
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

    @Override
    protected RegisterPresenter createPresenter() {
        return new RegisterPresenter(this);
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_regist;
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
    @OnClick({R.id.bt_next,R.id.getcode,R.id.et_code,R.id.et_username})
    public void Onclick(View v) {
        switch (v.getId()) {
            case R.id.bt_next:
                //验证验证码成功后跳转
                if (CheckUtil.checkEmail(et_username.getText())){
                    //邮件验证码


                }else{
                    //电话验证码


                }



                break;

            case R.id.getcode:
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        Message msg = new Message();
                        if (number == 0) {
                            msg.what = 3;
                        } else {
                            msg.what = 2;
                            msg.obj = number;
                            number--;
                        }
                        handler.sendMessage(msg);
                    }
                }, 0, 1000);
                if (CheckUtil.checkEmail(et_username.getText())){
                    mPresenter.getEmailCode(et_username.getText().toString());
                }else if(CheckUtil.checkPhoneNumber(et_username.getText())){
                    mPresenter.getPhoneCode(et_username.getText().toString(),"+86","1");
                 }
                break;
            case R.id.et_code:


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
        if(!NetUtil.isConnected(RegistActivity.this)){
            ToastUtil.show(RegistActivity.this,"网络异常");
            return;
        }
        ToastUtil.show(RegistActivity.this, "登陆失败");
    }



    @Override
    public void onCheckCodeSuccessed() {
        //验证电话和邮箱验证码成功
        SetPswActivity.startActivity("",et_username.getText().toString().trim());

    }


    public static void startActivity() {
        Intent intent = new Intent(App.getContext(), RegistActivity.class);
        Bundle bundle = new Bundle();
        intent.putExtras(bundle);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        App.getContext().startActivity(intent);
    }


}
