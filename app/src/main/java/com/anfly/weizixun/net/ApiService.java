package com.anfly.weizixun.net;

import com.anfly.weizixun.bean.LoginBean;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Observer;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiService {
    String baseLoginUrl = "https://www.wanandroid.com/";

    @POST("user/login")
    @FormUrlEncoded
    Flowable<LoginBean> login(@Field("username") String username, @Field("password") String password);
}
