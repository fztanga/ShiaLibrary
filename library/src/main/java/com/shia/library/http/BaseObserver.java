package com.shia.library.http;

import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;
import com.afollestad.materialdialogs.MaterialDialog;
import com.shia.library.R;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by hehz on 2017/3/30.
 */
public abstract class BaseObserver<T> implements Observer<Response<T>> {
    private Context mContext;
    private MaterialDialog mDialog;
    private Disposable mDisposable;
    private boolean mIsQuilt = false;
    private static final String TAG = "BaseObserver";

    public BaseObserver(Context context) {
        mContext = context;
    }

    public BaseObserver(Context context, boolean isQuilt) {
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
    public void onNext(Response<T> response) {
        if (response.isSuccess()) {
            T t = response.getData();
            onSuccess(t);
        } else {
            onError(response.getResultCode(), response.getResultDes());
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
}
