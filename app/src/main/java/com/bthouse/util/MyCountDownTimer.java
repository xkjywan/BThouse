package com.bthouse.util;

import android.os.CountDownTimer;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/10/27 0027.
 */


//复写倒计时
public class MyCountDownTimer extends CountDownTimer {

    private TextView mView;

    public MyCountDownTimer(long millisInFuture, long countDownInterval, TextView view) {
        super(millisInFuture, countDownInterval);
        this.mView = view;
    }

    //计时过程
    @Override
    public void onTick(long l) {
        //防止计时过程中重复点击
        mView.setClickable(false);
        mView.setText(l / 1000 + "s");

    }

    //计时完毕的方法
    @Override
    public void onFinish() {
        //重新给Button设置文字
        mView.setText("发送验证码");
        //设置可点击
        mView.setClickable(true);
    }
}