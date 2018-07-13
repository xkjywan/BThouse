package com.bthouse.mvp.view;


import com.bthouse.mvp.module.ResultResponse;

public interface ILogoutView {
    void  onFinish();
    void  onError();
    void  onLogoutSuc(ResultResponse response);
}
