package com.bthouse.mvp.presenter;

import com.bthouse.api.SubscriberCallBack;
import com.bthouse.mvp.module.data;
import com.bthouse.mvp.view.RegisterView;

public class SetPswPresenter extends BasePresenter<RegisterView> {


    public SetPswPresenter(RegisterView view) {
        super(view);
    }

    public void getPhoneCode(String phone, String phone_type,String send_type) {

        addSubscription(mApiService.getPhoneCode(phone, phone_type,send_type), new SubscriberCallBack<data>() {

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
            }

            @Override
            protected void onError() {
                mView.onFinish();
            }

        });
    }

}
