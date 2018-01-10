package com.shia.sample.bean;

import android.databinding.BaseObservable;

/**
 * Created by administrator on 2017/4/8.
 */
public class WHKPBResponse extends BaseObservable {

    public String errcode;
    public String errmsg;

    public boolean isSuccess() {
        return "1".equals(errcode);
    }

    public String getErrcode() {
        return errcode;
    }

    public void setErrcode(String errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }
}
