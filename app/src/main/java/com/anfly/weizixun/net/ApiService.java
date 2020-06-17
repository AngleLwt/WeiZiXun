package com.anfly.weizixun.net;

import com.anfly.weizixun.bean.DailyBean;
import com.anfly.weizixun.bean.DailyNewsDetailsBean;
import com.anfly.weizixun.bean.HotBean;
import com.anfly.weizixun.bean.ItInfoBean;
import com.anfly.weizixun.bean.LoginBean;
import com.anfly.weizixun.bean.RegisterBean;
import com.anfly.weizixun.bean.SpecialBean;
import com.anfly.weizixun.bean.WxArticleBean;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Observer;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {
    String baseUrl = "http://47.110.151.50/p6/";
    String baseZhiUrl = "http://news-at.zhihu.com/";
    String baseWanAndroidUrl = "https://www.wanandroid.com/";

    /**
     * 注册,
     *
     * @param userid
     * @param psd
     * @param accessToken 三方平台唯一标识,可选参数,三方注册使用
     * @param typeid      ,可选参数,三方注册使用,三方平台类型,1是qq,2是微信,3微博
     * @return
     */
    @POST("register.do")
    @FormUrlEncoded
    Flowable<RegisterBean> register(@Field("userid") String userid,
                                    @Field("password") String psd,
                                    @Field("accessToken") String accessToken,
                                    @Field("typeid") String typeid);

    /**
     * 账号密码登录
     *
     * @param userid
     * @param psd
     * @return
     */
    @POST("login.do")
    @FormUrlEncoded
    Flowable<LoginBean> login(@Field("userid") String userid,
                              @Field("password") String psd);

    /**
     * 日报
     *
     * @return
     */
    @GET("api/4/news/latest")
    Flowable<DailyBean> getDailyData();

    /**
     * 获取往期数据
     *
     * @param date
     * @return
     */
    @GET("api/4/news/before/{date}")
    Flowable<DailyBean> getBeforeData(@Path("date") String date);

    /**
     * 获取专栏数据
     *
     * @return
     */
    @GET("api/4/sections")
    Flowable<SpecialBean> getSpecialData();

    /**
     * 获取热门数据
     *
     * @return
     */
    @GET("api/4/news/hot")
    Flowable<HotBean> getHotData();

    /**
     * 获取日报详情
     *
     * @param id
     * @return
     */
    @GET("api/4/news/{id}")
    Flowable<DailyNewsDetailsBean> getDailyNewsDetalisData(@Path("id") int id);

    /**
     * 获取微信公众号
     *
     * @return
     */
    @GET("wxarticle/chapters/json")
    Flowable<ItInfoBean> getItInfoData();

    /**
     * 获取公众号文章
     *
     * @param id
     * @param page
     * @return
     */
    @GET("wxarticle/list/{id}/{page}/json")
    Flowable<WxArticleBean> getWxArticleData(@Path("id") int id, @Path("page") int page);
}
