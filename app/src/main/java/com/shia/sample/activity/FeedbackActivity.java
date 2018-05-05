package com.shia.sample.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.DeviceUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.shia.library.widget.Titlebar;
import com.shia.sample.R;

public class FeedbackActivity extends AppCompatActivity {
    private EditText feedback;

    public void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        setContentView(R.layout.activity_more_feedback);

        ((Titlebar) findViewById(R.id.titlebar)).setMoreAction(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showShort("提交");
            }
        });

        feedback = (EditText) findViewById(R.id.feedback);

        String appInfo = "设备：" + DeviceUtils.getModel() + "  系统：" + DeviceUtils.getSDKVersionName() + "  当前版本："
                + AppUtils.getAppVersionName();
        ((TextView) findViewById(R.id.app_info)).setText(appInfo);

    }

}
