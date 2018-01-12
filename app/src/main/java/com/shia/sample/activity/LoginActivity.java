package com.shia.sample.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import com.blankj.utilcode.util.ToastUtils;
import com.github.yoojia.inputs.AndroidNextInputs;
import com.github.yoojia.inputs.Scheme;
import com.github.yoojia.inputs.WidgetAccess;
import com.github.yoojia.inputs.verifiers.NotEmptyVerifier;
import com.shia.library.http.RxRetrofit;
import com.shia.library.http.RxSchedulers;
import com.shia.sample.R;
import com.shia.sample.application.AppApplication;
import com.shia.sample.bean.User;
import com.shia.sample.event.LoginEvent;
import com.shia.sample.service.AppService;
import com.shia.sample.service.WHKPBObserver;
import org.greenrobot.eventbus.EventBus;

public class LoginActivity extends AppCompatActivity {
    private EditText loginPhone, loginPwd;
    private static final String TAG = "LoginActivity";

    public void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        setContentView(R.layout.activity_login);

        loginPhone = (EditText) findViewById(R.id.login_phone);
        loginPwd = (EditText) findViewById(R.id.login_pwd);

        final AndroidNextInputs inputs = new AndroidNextInputs();
        final WidgetAccess access = new WidgetAccess(this);
        // 一、流式API
        inputs.add(access.findEditText(R.id.login_phone)).with(new Scheme(new NotEmptyVerifier()).msg("用户名不能为空"))
                .add(access.findEditText(R.id.login_pwd)).with(new Scheme(new NotEmptyVerifier()).msg("密码不能为空"));

        findViewById(R.id.login_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (inputs.test()) {

                    RxRetrofit.getRetrofit().create(AppService.class)
                            .login(loginPhone.getText().toString(), loginPwd.getText().toString())
                            .compose(RxSchedulers.<User> defaultSchedulers())
                            .subscribe(new WHKPBObserver<User>(LoginActivity.this) {
                                @Override
                                protected void onSuccess(final User user) {
                                    // 添加用户信息
                                    AppApplication.getInstance().setUser(user);
                                    EventBus.getDefault().post(new LoginEvent());
                                    finish();
                                }

                                @Override
                                protected void onError(String code, String message) {
                                    ToastUtils.showShortToast("用户名或密码错误！");
                                }
                            });
                }

            }
        });
    }

}
