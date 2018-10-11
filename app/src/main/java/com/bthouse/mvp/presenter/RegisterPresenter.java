package com.bthouse.mvp.presenter;

import com.bthouse.api.SubscriberCallBack;
import com.bthouse.mvp.module.UserResponse;
import com.bthouse.mvp.module.data;
import com.bthouse.mvp.view.BaseView;
import com.bthouse.mvp.view.IloginView;
import com.bthouse.mvp.view.RegisterView;

public class RegisterPresenter extends BasePresenter<RegisterView> {


    public RegisterPresenter(RegisterView view) {
        super(view);
    }
    public void getPhoneCode(String phone, String phone_type) {

        addSubscription(mApiService.getPhoneCode(phone, phone_type), new SubscriberCallBack<data>() {

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
                mView.onPhoneCodeSuccessed();
            }

            @Override
            protected void onError() {
                mView.onFinish();
            }

        });
    }

    public void getEmailCode(String email) {

        addSubscription(mApiService.getEmailCode(email), new SubscriberCallBack<data>() {
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
                mView.onEmailCodeSuccessed();
            }

            @Override
            protected void onError() {
                mView.onFinish();
            }

        });
    }

    public void CheckcEmailCode(String email,String code) {

        addSubscription(mApiService.checkEmailCode(email,code), new SubscriberCallBack<data>() {
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
                mView.onCheckCodeSuccessed();
            }

            @Override
            protected void onError() {
                mView.onFinish();
            }

        });
    }

    //phone_type  是  手机号区域  +86（中国），+61（澳大利亚）
    public void CheckcPhoneCode(String phone,String type,String code) {

        addSubscription(mApiService.checkPhoneCode(phone,type,code), new SubscriberCallBack<data>() {
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
                mView.onCheckCodeSuccessed();
            }

            @Override
            protected void onError() {
                mView.onFinish();
            }

        });
    }

}
