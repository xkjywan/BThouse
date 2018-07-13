package com.bthouse.mvp.presenter;


import com.bthouse.mvp.module.SearchCollectBean;
import com.bthouse.mvp.view.SearchCollectView;
import java.util.List;
import rx.Subscriber;

/**
 * @description: 收藏列表的presenter
 */

public class SearchCollectPresenter extends BasePresenter<SearchCollectView> {


    public SearchCollectPresenter(SearchCollectView view) {
        super(view);
    }
    public void getCollectList(String userId) {

        addSubscription(mApiService.getCollectList(userId),  new Subscriber<List<SearchCollectBean>>()  {
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
            public void onNext(List<SearchCollectBean> searchCollectBean) {
                mView.onSucc(searchCollectBean);
            }

        });
    }

}
