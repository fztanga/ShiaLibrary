package com.shia.sample.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import com.github.yoojia.inputs.*;
import com.github.yoojia.inputs.verifiers.NotEmptyVerifier;
import com.shia.sample.R;
import com.shia.library.activity.BaseActivity;

public class ModifyPwdActivity extends BaseActivity {
    private EditText pwd1, pwd2;

    public void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        setContentView(R.layout.activity_more_modify_pwd);

        setTitle("修改密码");
        setNavigationText("取消");
        setOptionButtonText("保存");

        pwd1 = (EditText) findViewById(R.id.pwd1);
        pwd2 = (EditText) findViewById(R.id.pwd2);

        final AndroidNextInputs inputs = new AndroidNextInputs();
        final WidgetAccess access = new WidgetAccess(this);
        // 一、流式API
        inputs.add(access.findEditText(R.id.pwd1))
                .with(new Scheme(new NotEmptyVerifier()).msg("请输入原始密码"))
                .add(access.findEditText(R.id.pwd2))
                .with(new Scheme(new NotEmptyVerifier()).msg("请输入新密码"))
                .add(access.findEditText(R.id.pwd3))
                .with(new Scheme(new NotEmptyVerifier()).msg("请再次输入新密码"),
                        ValueScheme.EqualsTo(new LazyLoaders(this).fromEditText(R.id.pwd2)));

        setOptionButtonOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (inputs.test()) {
                }
            }
        });
    }

}
