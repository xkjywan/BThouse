package com.bthouse.mvp.presenter;

import com.bthouse.api.SubscriberCallBack;
import com.bthouse.mvp.module.data;
import com.bthouse.mvp.view.SetPswView;

public class SetPswPresenter extends BasePresenter<SetPswView> {


    public SetPswPresenter(SetPswView view) {
        super(view);
    }

    public void regphone(String phone, String phone_type,String user_pass,String re_user_pass,String user_nice_name ) {

        addSubscription(mApiService.reg_phone(phone, phone_type,user_pass,re_user_pass,user_nice_name), new SubscriberCallBack<data>() {

            @Override
            public void onCompleted() {
                mView.onFinish();
            }

            @Override
            public void onError(Throwable e) {
                mView.onError();
            }

            @Override
            protected void onSuccess(data d) {
                mView.onSetPswSuc();
            }

            @Override
            protected void onError() {
                mView.onFinish();
            }

        });
    }

}
