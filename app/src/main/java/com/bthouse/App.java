package com.bthouse;

import android.app.Application;
import android.content.Context;

import com.bthouse.util.CrashHandler;
import com.google.gson.Gson;
import com.bthouse.config.ACacheKey;
import com.bthouse.mvp.module.UserResponse;
import com.bthouse.util.ACache;

public class App extends Application {
    public static App ctx;
    private static Context mContext;//上下文

    public static App getInstance() {
        return ctx;
    }

    private ACache mCache;
    //全部的用戶令牌;
    private UserResponse userBean;

    private int newVersion;

    @Override
    public void onCreate() {
        super.onCreate();
        ctx = this;
        //对全局属性赋值
        mContext = getApplicationContext();
        mCache = ACache.get(mContext, "/ACache");
        //取用户的令牌信息
        getUserBean();

        //打印异常日志
        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(getApplicationContext());
    }

    //读用户令牌信息
    public UserResponse getUserBean() {
        if (null == this.userBean) {
            this.userBean = getUserBeanCache();
            // LoginResultResponse response = new LoginResultResponse();
            //this.userBean = response.new ContentBean();
        }
        return this.userBean;
    }

    //保存全局的用户令牌信息;
    public void setUserBean(UserResponse userBean) {

        this.userBean = userBean;
        try {
            mCache.put(ACacheKey.USER_ACCESS_TOKEN_KEY, new Gson().toJson(userBean));
        } catch (Exception e) {
        }
    }


    //保存全局的用户令牌信息;
    public UserResponse getUserBeanCache() {
        String result = mCache.getAsString(ACacheKey.USER_ACCESS_TOKEN_KEY);

        UserResponse userBean = new Gson().fromJson(result, UserResponse.class);//对于javabean直接给出class实例

        if (null == userBean) {
            userBean = new UserResponse();
        }
        return userBean;
    }

    public ACache getaCache() {
        return mCache;
    }

    public static Context getContext() {
        return mContext;
    }

    public int getNewVersion() {
        return newVersion;
    }

    public void setNewVersion(int newVersion) {
        this.newVersion = newVersion;
    }
}
