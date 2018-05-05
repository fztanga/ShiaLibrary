package com.shia.library.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.Serializable;

/**
 * Created by hehz on 2018/4/9.
 */
public abstract class BaseFragment extends Fragment {

    protected View view;

    public void setSerializableArgs(Serializable... args) {
        Bundle bundle = new Bundle();
        for (int i = 0; i < args.length; i++) {
            Object arg = args[i];
            if (arg == null) {
                return;
            }
            bundle.putSerializable("arg" + i, (Serializable) arg);
        }
        this.setArguments(bundle);
    }

    protected Serializable getSerializableArg(String key) {
        if (getArguments() == null || !getArguments().containsKey(key)) {
            return null;
        }
        return getArguments().getSerializable(key);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            view = onFirstCreateView(inflater, container, savedInstanceState);
        }

        return view;
    }

    protected abstract View onFirstCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

}
