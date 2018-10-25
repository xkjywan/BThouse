package com.bthouse.manager;

import com.bthouse.config.CacheKey;
import com.bthouse.mvp.module.UserModel;
import com.orhanobut.hawk.Hawk;

/**
     * 用户管理相关类
     */

public class UserDataManager {

    public static void setUserModel(UserModel userModel){
       Hawk.put(CacheKey.USER_ACCESS_TOKEN_KEY,userModel);
    }

    public static UserModel getUserBean() {
        return Hawk.get(CacheKey.USER_ACCESS_TOKEN_KEY);
    }

}
