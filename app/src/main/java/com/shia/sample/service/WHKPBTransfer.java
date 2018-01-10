package com.shia.sample.service;

import com.shia.library.bean.DataGrid;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

import java.util.List;

/**
 * Created by administrator on 2017/4/12.
 */
public class WHKPBTransfer {

    public static <T> ObservableTransformer<ResponseBody, List<T>> listTransfer(final Class clazz, final String listName) {
        return new ObservableTransformer<ResponseBody, List<T>>() {
            @Override
            public ObservableSource<List<T>> apply(Observable<ResponseBody> upstream) {
                return upstream.subscribeOn(Schedulers.io()).map(new Function<ResponseBody, List<T>>() {
                    @Override
                    public List<T> apply(ResponseBody responseBody) throws Exception {
                        DataGrid dataGrid = new WHKPBDataGridConverter(clazz, listName).convert(responseBody.string());
                        return dataGrid.getRows();
                    }
                }).observeOn(AndroidSchedulers.mainThread());
            }
        };
    }
}
