package com.shia.library.http;

import android.content.Context;

/**
 * Created by hehz on 2017/3/30.
 */
public abstract class QuietObserver<T> extends BaseObserver<T> {

    public QuietObserver(Context context) {
        super(context, true);
    }

}
