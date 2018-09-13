package com.shia.library.http;

import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;
import com.afollestad.materialdialogs.MaterialDialog;
import com.google.gson.JsonParseException;
import com.shia.library.R;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import org.json.JSONException;
import retrofit2.HttpException;

import java.net.SocketTimeoutException;

/**
 * Created by hehz on 2017/3/30.
 */
public abstract class BaseObserver<T> implements Observer<Response<T>> {

    // 对应HTTP的状态码
    protected static final int UNAUTHORIZED = 401;
    protected static final int FORBIDDEN = 403;
    protected static final int NOT_FOUND = 404;
    protected static final int REQUEST_TIMEOUT = 408;
    protected static final int INTERNAL_SERVER_ERROR = 500;
    protected static final int BAD_GATEWAY = 502;
    protected static final int SERVICE_UNAVAILABLE = 503;
    protected static final int GATEWAY_TIMEOUT = 504;

    protected Context mContext;
    protected MaterialDialog mDialog;
    protected Disposable mDisposable;
    protected boolean mIsQuilt = false;
    protected static final String TAG = "BaseObserver";

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
        e.printStackTrace();
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
        }
        Throwable throwable = e;
        // 获取最根源的异常
        while (throwable.getCause() != null) {
            e = throwable;
            throwable = throwable.getCause();
        }

        if (e instanceof HttpException) { // HTTP错误
            HttpException httpException = (HttpException) e;
            switch (httpException.code()) {
            case UNAUTHORIZED:
            case FORBIDDEN:
            case 871202:
                Toast.makeText(mContext, "登录状态失效，请重新登录", Toast.LENGTH_LONG).show();
                break;
            case NOT_FOUND:
                Toast.makeText(mContext, "服务器地址错误", Toast.LENGTH_LONG).show();
                break;
            case REQUEST_TIMEOUT:
            case GATEWAY_TIMEOUT:
                Toast.makeText(mContext, "连接服务器超时", Toast.LENGTH_LONG).show();
                break;
            case INTERNAL_SERVER_ERROR:
            case BAD_GATEWAY:
            case SERVICE_UNAVAILABLE:
            default:
                Toast.makeText(mContext, R.string.network_error, Toast.LENGTH_LONG).show();
                break;
            }
        } else if (e instanceof SocketTimeoutException) {
            Toast.makeText(mContext, "连接服务器超时", Toast.LENGTH_LONG).show();
        } else if (e instanceof JsonParseException || e instanceof JSONException) {
            Toast.makeText(mContext, "数据解析错误", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(mContext, R.string.network_error, Toast.LENGTH_LONG).show();
        }
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
