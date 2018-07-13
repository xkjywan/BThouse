package com.bthouse.mvp.presenter;


import com.bthouse.mvp.module.ResultResponse;
import com.bthouse.mvp.view.ILogoutView;

import rx.Subscriber;

/**
 * @author ChayChan
 * @description: 新闻列表的presenter
 * @date 2017/6/18  10:04
 */

public class LogoutPresenter extends BasePresenter<ILogoutView> {


    public LogoutPresenter(ILogoutView view) {
        super(view);
    }
    public void logout(String userId) {

        addSubscription(mApiService.logout(userId),  new Subscriber<ResultResponse>()  {
            @Override
            public void onCompleted() {
                mView.onFinish();
            }
            @Override
            public void onError(Throwable e) {
                mView.onError();
                mView.onFinish();
            }

            @Override
            public void onNext(ResultResponse changePwdResponse) {
                mView.onLogoutSuc(changePwdResponse);
            }

        });
    }

}
