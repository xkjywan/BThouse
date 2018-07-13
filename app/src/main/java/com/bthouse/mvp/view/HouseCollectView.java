package com.bthouse.mvp.view;


import com.bthouse.mvp.module.HouseCollectBean;
import com.bthouse.mvp.module.SearchCollectBean;

import java.util.List;

/**
 * @description: 房屋收藏列表View回调接口
 */

public interface HouseCollectView {
    void  onFinish();
    void  onError();
    void  onSucc(List<HouseCollectBean> collects);
}
