package com.bthouse.mvp.presenter;

import com.bthouse.api.SubscriberCallBack;
import com.bthouse.mvp.module.UserResponse;
import com.bthouse.mvp.view.IloginView;

public class LoginPresenter extends BasePresenter<IloginView> {


    public LoginPresenter(IloginView view) {
        super(view);
    }
    public void login(String username, String pwd) {

        addSubscription(mApiService.login(username, pwd), new SubscriberCallBack<UserResponse>() {
            @Override
            public void onCompleted() {
                mView.onFinish();
            }

            @Override
            public void onError(Throwable e) {
                mView.onError();
            }

            @Override
            protected void onSuccess(UserResponse response) {
                mView.onLoginSuc(response);
            }

            @Override
            protected void onError() {
                mView.onFinish();
            }

//            @Override
//            public void onNext(UserResponse response) {
//                mView.onLoginSuc(response);
//            }
        });
    }

}
