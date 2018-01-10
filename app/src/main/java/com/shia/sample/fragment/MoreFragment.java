package com.shia.sample.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.shia.sample.R;
import com.shia.sample.activity.FeedbackActivity;
import com.shia.sample.activity.LoginActivity;
import com.shia.sample.activity.ModifyPwdActivity;
import com.shia.sample.application.AppApplication;
import com.shia.sample.databinding.FragmentMoreBinding;
import com.shia.sample.event.LoginEvent;
import com.shia.library.fragment.BaseFragment;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by administrator on 2017/4/9.
 */
public class MoreFragment extends BaseFragment {

    private FragmentMoreBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = bindingInflate(inflater, R.layout.fragment_more, container, false);
        setTitle("更多");
        binding.setHandler(this);
        binding.setIsLogin(AppApplication.getInstance().getUser() != null);

        EventBus.getDefault().register(this);// 注册监听
        return binding.getRoot();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLoginEvent(LoginEvent event) {
        binding.setIsLogin(AppApplication.getInstance().getUser() != null);
    }

    public void about(View view) {
    }

    public void feedback(View view) {
        Intent intent = new Intent(getContext(), FeedbackActivity.class);
        startActivity(intent);
    }

    public void modifyPwd(View view) {
        Intent intent = new Intent(getContext(), ModifyPwdActivity.class);
        startActivity(intent);
    }

    public void login(View view) {
        Intent intent = new Intent(getContext(), LoginActivity.class);
        startActivity(intent);
    }

    public void logout(View view) {
        binding.setIsLogin(false);
        AppApplication.getInstance().setUser(null);
    }
}
