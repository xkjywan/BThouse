package com.bthouse.mvp.presenter;


import com.bthouse.mvp.module.HouseCollectBean;
import com.bthouse.mvp.module.SearchCollectBean;
import com.bthouse.mvp.view.HouseCollectView;
import com.bthouse.mvp.view.SearchCollectView;

import java.util.List;

import rx.Subscriber;

/**
 * @description: 收藏列表的presenter
 */

public class HouseCollectPresenter extends BasePresenter<HouseCollectView> {


    public HouseCollectPresenter(HouseCollectView view) {
        super(view);
    }
    public void getCollectList(String userId) {

        addSubscription(mApiService.getCollectList(userId),  new Subscriber<List<HouseCollectBean>>()  {
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
            public void onNext(List<HouseCollectBean> searchCollectBean) {
                mView.onSucc(searchCollectBean);
            }

        });
    }

}
