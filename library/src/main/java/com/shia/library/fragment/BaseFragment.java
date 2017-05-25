package com.shia.library.fragment;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.shia.library.R;

/**
 * Created by hehz on 2017/4/9.
 */
public class BaseFragment extends Fragment {

    public Context mContext;
    private Toolbar toolbar;
    private TextView titleTextView, leftButton, rightButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this.getActivity();
    }

    public View inflate(LayoutInflater inflater, int resource, ViewGroup root, boolean attachToRoot) {
        View view = inflater.inflate(resource, root, attachToRoot);
        initToolbar(view);
        return view;
    }

    public <T extends ViewDataBinding> T bindingInflate(LayoutInflater inflater, int layoutId,
            @Nullable ViewGroup parent, boolean attachToParent) {
        T binding = DataBindingUtil.inflate(inflater, layoutId, parent, false);
        initToolbar(binding.getRoot());
        return binding;
    }

    private void initToolbar(View view) {
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        titleTextView = (TextView) view.findViewById(R.id.title);
        leftButton = (TextView) view.findViewById(R.id.left_button);
        rightButton = (TextView) view.findViewById(R.id.right_button);
    }

    public void setTitle(CharSequence title) {
        if (titleTextView != null) {
            titleTextView.setText(title);
        }
    }

    public void setNavigationVisible(boolean visible) {
        if (leftButton != null) {
            leftButton.setVisibility(visible ? View.VISIBLE : View.GONE);
        }
    }

    public void setNavigationIcon(@DrawableRes int resId) {
        if (leftButton != null) {
            leftButton.setVisibility(View.VISIBLE);
            leftButton.setBackgroundResource(resId);
        }
    }

    public void setNavigationText(String text) {
        if (leftButton != null) {
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

    public void onSelected() {
    }
}
