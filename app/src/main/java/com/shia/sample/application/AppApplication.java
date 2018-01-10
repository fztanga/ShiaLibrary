package com.shia.sample.application;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.Utils;
import com.shia.sample.BuildConfig;
import com.shia.sample.bean.User;
import com.shia.library.http.RxRetrofit;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;

import java.io.IOException;

public class AppApplication extends Application {

    private static AppApplication mApp = null;

    private static final String TAG = "AppApplication";
    public User user;

    @Override
    public void onCreate() {
        super.onCreate();
        mApp = this;

        if (BuildConfig.DEBUG) {
            RxRetrofit.config(this, "http://mobile.whkpb.gov.cn/");
        } else {
            RxRetrofit.config(this, "http://mobile.whkpb.gov.cn/");
        }
        Log.d(TAG, RxRetrofit.BASE_URL);

        Utils.init(this);

        // 保存用户信息
        SPUtils sp = new SPUtils("user");
        String userId = sp.getString("userId");
        if (userId != null && !"".equals(userId)) {
            User user = new User();
            user.setUserId(userId);
            user.setGroupId(sp.getString("groupId"));
            user.setUsername(sp.getString("username"));
            user.setRealname(sp.getString("realname"));
            setUser(user);
        }

    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    public static AppApplication sharedApp() {
        if (mApp == null) {
            synchronized (new Object()) {
                mApp = new AppApplication();
            }
        }
        return mApp;
    }

    public static Context getContext() {
        return sharedApp().getApplicationContext();
    }

    public static AppApplication getInstance() {
        return mApp;
    }

    public void setUser(final User user) {
        this.user = user;

        SPUtils sp = new SPUtils("user");
        if (user != null) {
            // 保存用户信息
            sp.put("username", user.getUsername());
            sp.put("groupId", user.getGroupId());
            sp.put("userId", user.getUserId());
            sp.put("realname", user.getRealname());

            RxRetrofit.resetWithInterceptor(new Interceptor() {
                @Override
                public okhttp3.Response intercept(Chain chain) throws IOException {
                    Request originalRequest = chain.request();
                    HttpUrl modifiedUrl = originalRequest.url().newBuilder()
                            .addQueryParameter("userId", user.getUserId()).build();
                    return chain.proceed(originalRequest.newBuilder().url(modifiedUrl).build());
                }
            });
        } else {
            sp.clear();
        }
    }

    public User getUser() {
        return user;
    }
}
