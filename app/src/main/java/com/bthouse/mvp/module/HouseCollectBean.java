package com.bthouse.mvp.module;

import java.io.Serializable;

/**
 * 收藏查询字条
 */
public class HouseCollectBean implements Serializable {

    private String key;
    private String img_str;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getImg_str() {
        return img_str;
    }

    public void setImg_str(String img_str) {
        this.img_str = img_str;
    }
}
