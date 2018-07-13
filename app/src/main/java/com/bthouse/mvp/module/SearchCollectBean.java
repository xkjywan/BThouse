package com.bthouse.mvp.module;

import java.io.Serializable;

/**
 * 收藏查询字条
 */
public class SearchCollectBean implements Serializable {

    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
