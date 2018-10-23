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

    //验证邮箱验证码　　/index.php?g=api&m=Home&a=check_reg_email
    @FormUrlEncoded
    @POST("index.php?g=api&m=Home&a=check_reg_email")
    Observable<ResultResponse<ResultResponse<Object>>> checkEmailCode(
            @Field("user_email") String user_email,
            @Field("email_code") String email_code
    );

    //验证电话验证码　/index.php?g=api&m=Home&a=check_reg_phone
    @FormUrlEncoded
    @POST("index.php?g=api&m=Home&a=check_reg_phone")
    Observable<ResultResponse<ResultResponse<Object>>> checkPhoneCode(
            @Field("phone") String phone,
            @Field("phone_type") String phone_type,
            @Field("phone_code") String phone_code
    );


    //邮箱找回密码　　/index.php?g=api&m=User&a=email_find
    @FormUrlEncoded
    @POST("index.php?g=api&m=User&a=email_find")
    Observable<ResultResponse<ResultResponse<Object>>> findPswByEmailCode(
            @Field("user_email") String user_email,
            @Field("email_code") String email_code
    );

    //电话找回密码　/index.php?g=api&m=Home&a=check_reg_phone
    @FormUrlEncoded
    @POST("/index.php?g=api&m=User&a=phone_find")
    Observable<ResultResponse<ResultResponse<Object>>> findPswByPhoneCode(
            @Field("phone") String phone,
            @Field("phone_type") String phone_type,
            @Field("phone_code") String phone_code
    );


    //邮箱注册
    @FormUrlEncoded
    @POST("index.php?g=Api&m=Api&a=login")
//    @POST("index.php")
    /* Call<ResponseBody> getWeather(@Query("ip") String ip);*/
    Observable<ResultResponse<String>> register(
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

    //获取收藏的词条
    @FormUrlEncoded
    @POST("index.php?g=api&m=User&a=getMyWords")
    Observable<SearchCollectBean> getMyWords(
            @Field("id") String id,
            @Field("num") String num,
            @Field("token") String token,
            @Field("p") String p


}

