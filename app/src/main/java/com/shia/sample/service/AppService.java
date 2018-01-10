package com.shia.sample.service;

import com.shia.sample.bean.NewsDetail;
import com.shia.sample.bean.User;
import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by administrator on 2017/3/30.
 */
public interface AppService {

    @POST("app/qmgb-user-login.jspx")
    @FormUrlEncoded
    Observable<User> login(@Field("username") String userCode, @Field("password") String password);

    @POST("app/qmgb-content.jspx")
    @FormUrlEncoded
    Observable<NewsDetail> getNewsDetail(@Field("contentId") String contentId);
}
