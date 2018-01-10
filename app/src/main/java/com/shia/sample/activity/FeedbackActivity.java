package com.shia.sample.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.DeviceUtils;
import com.shia.sample.R;
import com.shia.library.activity.BaseActivity;

public class FeedbackActivity extends BaseActivity {
    private EditText feedback;

    public void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        setContentView(R.layout.activity_more_feedback);

        setTitle("意见反馈");
        setNavigationText("取消");
        setOptionButtonText("提交");
        setOptionButtonOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("---------");
            }
        });

        feedback = (EditText) findViewById(R.id.feedback);

        String appInfo = "设备：" + DeviceUtils.getModel() + "  系统：" + DeviceUtils.getSDKVersion() + "  当前版本："
                + AppUtils.getAppVersionName(this);
        ((TextView) findViewById(R.id.app_info)).setText(appInfo);

    }

}
