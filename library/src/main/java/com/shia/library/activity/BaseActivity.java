package com.shia.library.activity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.shia.library.R;

public abstract class BaseActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView titleTextView, leftButton, rightButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    protected void onCreate(Bundle savedInstanceState, int requestedOrientation) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(requestedOrientation);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        configureToolbar();
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        configureToolbar();
    }

    @Override
    public void setTitle(CharSequence title) {
        if (titleTextView != null) {
            super.setTitle("");
            titleTextView.setText(title);
        } else {
            super.setTitle(title);
        }
    }

    private void configureToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        titleTextView = (TextView) findViewById(R.id.title);
        leftButton = (TextView) findViewById(R.id.left_button);
        rightButton = (TextView) findViewById(R.id.right_button);

        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintResource(R.color.colorPrimaryDark);
        tintManager.setStatusBarTintEnabled(true);

        if (toolbar != null) {
            setSupportActionBar(toolbar);
            if (getSupportActionBar() != null) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                tintManager.setNavigationBarTintColor(R.color.colorPrimary);
            }

            // 返回键点击事件
            toolbar.setNavigationIcon(R.drawable.ico_avigation);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }

        if (leftButton != null) {
            leftButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });
        }
    }

    public void hideNavigation() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }

    public void setNavigationIcon(@DrawableRes int resId) {
        if (leftButton != null) {
            hideNavigation();
            leftButton.setVisibility(View.VISIBLE);
            leftButton.setBackgroundResource(resId);
        }
    }

    public void setNavigationText(String text) {
        if (leftButton != null) {
            hideNavigation();
            leftButton.setVisibility(View.VISIBLE);
            leftButton.setText(text);
        }
    }

    public void setNavigationOnClickListener(View.OnClickListener listener) {
        if (leftButton != null) {
            leftButton.setOnClickListener(listener);
        }
    }

    public void setOptionButtonIconVisible(boolean visible) {
        if (rightButton != null) {
            rightButton.setVisibility(visible ? View.VISIBLE : View.GONE);
        }
    }

    public void setOptionButtonIcon(@DrawableRes int resId) {
        if (rightButton != null) {
            rightButton.setVisibility(View.VISIBLE);
            rightButton.setBackgroundResource(resId);
        }
    }

    public void setOptionButtonText(String text) {
        if (rightButton != null) {
            rightButton.setVisibility(View.VISIBLE);
            rightButton.setText(text);
        }
    }

    public void setOptionButtonOnClickListener(View.OnClickListener listener) {
        if (rightButton != null) {
            rightButton.setOnClickListener(listener);
        }
    }

    public void setToolbarVisible(boolean visible) {
        if (toolbar != null) {
            toolbar.setVisibility(visible ? View.VISIBLE : View.GONE);
        }
    }

    // @Override
    // public Resources getResources() {
    // Resources res = super.getResources();
    // Configuration config = new Configuration();
    // config.setToDefaults();
    // res.updateConfiguration(config, res.getDisplayMetrics());
    // return res;
    // }
}
