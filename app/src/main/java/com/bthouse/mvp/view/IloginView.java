package com.bthouse.mvp.view;


import com.bthouse.mvp.module.IloginMoudle;
import com.bthouse.mvp.module.ResultResponse;
import com.bthouse.mvp.module.UserResponse;

/**
 * @description: 获取各种频道广告的View回调接口
 */

public interface IloginView {
    void  onFinish();
    void  onError();
    void  onLoginSuc(UserResponse response);
}
