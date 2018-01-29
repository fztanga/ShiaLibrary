package com.shia.sample.service;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;
import com.afollestad.materialdialogs.MaterialDialog;
import com.blankj.utilcode.util.ToastUtils;
import com.shia.sample.R;
import com.shia.sample.bean.WHKPBResponse;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by administrator on 2017/4/8.
 */
public abstract class WHKPBObserver<T extends WHKPBResponse> implements Observer<WHKPBResponse> {
    private Context mContext;
    private MaterialDialog mDialog;
    private Disposable mDisposable;
    private boolean mIsQuilt = false;
    private static final String TAG = "WHKPBObserver";

    public WHKPBObserver(Context context) {
        mContext = context;
    }

    public WHKPBObserver(Context context, boolean isQuilt) {
        mContext = context;
        mIsQuilt = isQuilt;
    }

    @Override
    public void onSubscribe(Disposable d) {
        mDisposable = d;
        if (!mIsQuilt) {
            mDialog = new MaterialDialog.Builder(mContext).content(R.string.network_waiting).progress(true, 0).show();
            mDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialogInterface) {
                    mDisposable.dispose();
                }
            });
        }
    }

    @Override
    public void onNext(WHKPBResponse response) {
        if (response.isSuccess()) {
            onSuccess((T) response);
        } else {
            onError(response.getErrcode(), response.getErrmsg());
        }
    }

    @Override
    public void onError(Throwable e) {

        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
        }

        Toast.makeText(mContext, R.string.network_error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onComplete() {
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
        }
    }

    protected abstract void onSuccess(T t);

    protected void onError(String code, String message) {
        Toast.makeText(mContext, message, Toast.LENGTH_LONG).show();
    }

    public static WHKPBObserver<WHKPBResponse> emptyObserver(Context context, final String message) {
        return new WHKPBObserver<WHKPBResponse>(context) {
            @Override
            protected void onSuccess(WHKPBResponse response) {
                ToastUtils.showShort(message);
            }
        };
    }

    public static WHKPBObserver<WHKPBResponse> finishObserver(final Activity activity, final String message) {
        return new WHKPBObserver<WHKPBResponse>(activity) {
            @Override
            protected void onSuccess(WHKPBResponse response) {
                ToastUtils.showShort(message);
                activity.finish();
            }
        };
    }
}
