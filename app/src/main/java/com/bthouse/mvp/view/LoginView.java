package com.bthouse.mvp.view;


import com.bthouse.mvp.module.IloginMoudle;

/**
 * @description: 获取各种频道广告的View回调接口
 */

public interface LoginView {
    void  onFinish();
    void  onError();
    void  onLoginSuc(IloginMoudle response);
}
