package com.bthouse.mvp.view;


import com.bthouse.mvp.module.SearchCollectBean;

import java.util.List;

/**
 * @description: 获取收藏列表View回调接口
 */

public interface SearchCollectView {
    void  onFinish();
    void  onError();
    void  onSucc(List<SearchCollectBean> collects);
}
