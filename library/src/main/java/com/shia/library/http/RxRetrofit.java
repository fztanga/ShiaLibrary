package com.shia.library.http;

import android.content.Context;
import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.NetworkUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.shia.library.BuildConfig;
import okhttp3.*;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.File;
import java.io.IOException;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.concurrent.TimeUnit;

/**
 * Created by hehz on 2017/3/30.
 */
public class RxRetrofit {

    private static Context CONTEXT;
    public static String BASE_URL;
    private static Retrofit retrofit;

    public static void config(Context context, String baseUrl) {
        CONTEXT = context;
        BASE_URL = baseUrl;
        retrofit = getRetrofit(context, baseUrl);
    }

    public static void resetWithInterceptor(Interceptor interceptor) {
        retrofit = getRetrofit(CONTEXT, BASE_URL, interceptor);
    }

    public static Retrofit getRetrofit(final Context context, String baseUrl) {
        return getRetrofit(context, baseUrl, null);
    }

    public static Retrofit getRetrofit(final Context context, String baseUrl, Interceptor interceptor) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        // 设置缓存
        final File chachefile = new File(context.getExternalCacheDir(), "HttpCache");
        final Cache cache = new Cache(chachefile, 1024 * 1024 * 50);// 缓存文件
        Interceptor cacheInterceptor = new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                if (!NetworkUtils.isAvailableByPing()) {
                    request = request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build();
                }
                okhttp3.Response response = chain.proceed(request);
                if (NetworkUtils.isAvailableByPing()) {
                    int maxAge = 0;
                    // 有网络时 设置缓存超时时间0个小时
                    response.newBuilder().header("Cache-Control", "public, max-age=" + maxAge).removeHeader("Pragma")// 清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
                            .build();
                } else {
                    // 无网络时，设置超时为1天
                    int maxStale = 60 * 60 * 24;
                    response.newBuilder().header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                            .removeHeader("Pragma").build();
                }

                return response;
            }
        };
        // builder.cache(cache).addInterceptor(cacheInterceptor);
        // 公共参数
        Interceptor addQueryParameterInterceptor = new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request originalRequest = chain.request();
                String method = originalRequest.method();
                HttpUrl modifiedUrl = originalRequest.url().newBuilder().addQueryParameter("platform", "adnroid")
                        .addQueryParameter("version", AppUtils.getAppVersionName(context)).build();
                return chain.proceed(originalRequest.newBuilder().url(modifiedUrl).build());
            }
        };
        // 公共参数
        builder.addInterceptor(addQueryParameterInterceptor);
        // 设置头
        Interceptor headerInterceptor = new Interceptor() {

            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request orignaRequest = chain.request();
                Request request = orignaRequest.newBuilder().header("Content-Type", "application/json")
                        .header("Accept", "application/json").method(orignaRequest.method(), orignaRequest.body())
                        .build();

                return chain.proceed(request);
            }
        };
        builder.addInterceptor(headerInterceptor);

        // Log信息拦截器
        if (BuildConfig.DEBUG) {
            // log信息拦截器
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            // 设置Debug Log模式
            builder.addInterceptor(httpLoggingInterceptor);
        }
        // 自定义的interceptor
        if (interceptor != null) {
            builder.addInterceptor(interceptor);
        }
        // 设置cookie
        CookieManager cookieManager = new CookieManager();
        cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
        builder.cookieJar(new JavaNetCookieJar(cookieManager));
        // 设置超时和重连
        // 设置超时
        builder.connectTimeout(15, TimeUnit.SECONDS);
        builder.readTimeout(20, TimeUnit.SECONDS);
        builder.writeTimeout(20, TimeUnit.SECONDS);
        // 错误重连
        builder.retryOnConnectionFailure(false);
        // 以上设置结束，才能build(),不然设置白搭
        OkHttpClient okHttpClient = builder.build();
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss").create();

        Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).client(okHttpClient).build();
        return retrofit;
    }

    public static Retrofit getRetrofit() {
        return retrofit;
    }

}
