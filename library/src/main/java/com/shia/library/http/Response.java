package com.shia.library.http;

/**
 * Created by hehz on 2017/3/30.
 */
public class Response<T> {
    public String resultCode;
    public String resultDes;
    public T data;

    public boolean isSuccess() {
        return "1".equals(resultCode);
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultDes() {
        return resultDes;
    }

    public void setResultDes(String resultDes) {
        this.resultDes = resultDes;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Result{" + "resultCode='" + resultCode + '\'' + ", resultDes='" + resultDes + '\'' + ", data=" + data
                + '}';
    }
}
