package com.bthouse;

import android.app.Application;
import android.content.Context;

import com.bthouse.config.AppConfig;
import com.bthouse.util.CrashHandler;
import com.orhanobut.hawk.Hawk;
import com.orhanobut.hawk.HawkBuilder;
import com.orhanobut.hawk.LogLevel;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

public class App extends Application {
    public static App ctx;
    private static Context mContext;//上下文

    public static App getInstance() {
        return ctx;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        ctx = this;
        //对全局属性赋值
        mContext = getApplicationContext();

        //注册到微信
        registerToWX();

        //打印异常日志
        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(getApplicationContext());

        //Hawk数据库初始化
        Hawk.init(context).build();

    public static Context getContext() {
        return mContext;
    }


    public static IWXAPI mWxApi;
    private void registerToWX() {
        //第二个参数是指你应用在微信开放平台上的AppID
        mWxApi = WXAPIFactory.createWXAPI(this, AppConfig.APP_ID_WX, false);
//         将该app注册到微信
        mWxApi.registerApp(AppConfig.APP_ID_WX);
//        }
    }

}
