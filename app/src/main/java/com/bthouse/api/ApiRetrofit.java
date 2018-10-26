package com.bthouse.api;


import android.util.Log;

import com.bthouse.App;
import com.bthouse.config.AppConfig;
import com.bthouse.util.AppTools;
import com.bthouse.util.NetUtil;
import com.readystatesoftware.chuck.ChuckInterceptor;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;


public class ApiRetrofit {

    private static ApiRetrofit mApiRetrofit;
    private final Retrofit mRetrofit;
    private ApiService mApiService;


    public ApiRetrofit() {

        //新建log拦截器
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.d("OK_HTTP", "OkHttp====Message:" + message);
            }
        });
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        //缓存拦截器设置
        Interceptor baseInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                if (!NetUtil.isConnected(App.getContext())) {
                    /**
                     * 离线缓存控制  总的缓存时间=在线缓存时间+设置离线缓存时间
                     */
                    int maxStale = 60 * 60 * 24 * 28; // 离线时缓存保存4周,单位:秒
                    CacheControl tempCacheControl = new CacheControl.Builder()
                            .onlyIfCached()
                            .maxStale(maxStale, TimeUnit.SECONDS)
                            .build();
                    request = request.newBuilder()
                            .cacheControl(tempCacheControl)
                            .build();
                }
                return chain.proceed(request);
            }
        };

        //只有 网络拦截器环节 才会写入缓存写入缓存,在有网络的时候 设置缓存时间
        Interceptor rewriteCacheControlInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                Response originalResponse = chain.proceed(request);
                int maxAge = 1 * 10; // 在线缓存在10秒
                return originalResponse.newBuilder()
                        .removeHeader("Pragma")// 清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
                        .removeHeader("Cache-Control")
                        .header("Cache-Control", "public, max-age=" + maxAge)
                        .build();
            }
        };


        //缓存大小及存放位置
        int size = 1024 * 1024 * 100;
        File cacheFile = new File(App.getContext().getCacheDir(), "OkHttpCache");
        Cache cache = new Cache(cacheFile, size);
        //初始化okhttp;
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .addInterceptor(new ChuckInterceptor(App.getContext()))
                .addInterceptor(loggingInterceptor)
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request()
                                .newBuilder()
                                .build();
                        return chain.proceed(request);
                    }

                })
                .addInterceptor(baseInterceptor)
                .addNetworkInterceptor(rewriteCacheControlInterceptor)
                .sslSocketFactory(AppTools.getSSLContext().getSocketFactory(),AppTools.getX509TrustManager()).hostnameVerifier(AppTools.getHostnameVerifier())
                .cache(cache)
                .build();



        //初始化 retrofit;
        mRetrofit = new Retrofit.Builder()
                .baseUrl(AppConfig.BASE_SERVER_URL)
                .client(client)
                //重要，带文件 提交表单时，需要;
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        mApiService = mRetrofit.create(ApiService.class);
    }


    public static ApiRetrofit getInstance() {
        if (mApiRetrofit == null) {
            synchronized (Object.class) {
                if (mApiRetrofit == null) {
                    mApiRetrofit = new ApiRetrofit();
                }
            }
        }
        return mApiRetrofit;
    }

    public ApiService getApiService() {
        return mApiService;
    }
}
