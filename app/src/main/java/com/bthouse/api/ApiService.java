package com.bthouse.api;

import com.bthouse.mvp.module.IloginMoudle;
import com.bthouse.mvp.module.SearchCollectBean;
import com.bthouse.mvp.module.ResultResponse;
import com.bthouse.mvp.module.UserResponse;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

public interface ApiService {
    //登陆
    @FormUrlEncoded
    @POST("index.php?g=Api&m=Api&a=login")
//    @POST("index.php")
    /* Call<ResponseBody> getWeather(@Query("ip") String ip);*/
    Observable<ResultResponse<ResultResponse<IloginMoudle>>> login(
            @Field("username") String username,
            @Field("password") String password
    );

    //获取手机验证码
    @FormUrlEncoded
    @POST("index.php?g=&m=Index&a=send_code2")
    Observable<ResultResponse<ResultResponse<Object>>> getPhoneCode(
            @Field("phone") String phone,
            @Field("phone_type") String phone_type
    );

    //获取邮箱验证码
    @FormUrlEncoded
    @POST("index.php?g=&m=Index&a=send_code")
    Observable<ResultResponse<ResultResponse<Object>>> getEmailCode(
            @Field("email") String email
    );


    //邮箱注册
    @FormUrlEncoded
    @POST("index.php?g=Api&m=Api&a=login")
//    @POST("index.php")
    /* Call<ResponseBody> getWeather(@Query("ip") String ip);*/
    Observable<ResultResponse<ResultResponse<String>>> register(
            @Field("user_email") String user_email,
            @Field("email_code") String email_code,
            @Field("user_pass") String user_pass,
            @Field("re_user_pass") String re_user_pass,
            @Field("user_nicename") String user_nicename
    );

    //退出登陆
    @FormUrlEncoded
    @POST("app/logout")
    Observable<ResultResponse> logout(
            @Field("userId") String userId);

    //获取收藏列表
    @FormUrlEncoded
    @POST("app/logout")
    Observable<SearchCollectBean> getCollectList(
            @Field("userId") String userId);


}
