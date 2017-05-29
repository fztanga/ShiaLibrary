package com.shia.library.util;

import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.shia.library.R;
import com.shia.library.bean.DataGrid;
import com.shia.library.http.DataGridConverter;
import com.shia.library.http.RxRetrofit;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.http.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hehz on 2017/4/6.
 */
public class SwipeRefreshHelper {

    private RecyclerView recyclerView;
    private BaseQuickAdapter adapter;
    private SwipeRefreshLayout swipeRefreshLayout;

    private DataGridConverter dataGridConverter;

    private boolean post;// 是否是post方式
    private String requestUrl;

    private String PAGE_INDEX_NAME = "pageIndex";
    private String PAGE_SIZE_NAME = "pageSize";
    private int PAGE_INDEX = 0;
    private int PAGE_SIZE = 10;
    private int currentPageIndex;

    private static final int WHAT_DID_REFRESH = 1;
    private static final int WHAT_DID_MORE = 2;

    private Map<String, Object> params = new HashMap<String, Object>();

    private SwipeRefreshHelper() {
    }

    public static SwipeRefreshHelper create() {
        return new SwipeRefreshHelper();
    }

    public SwipeRefreshHelper bindView(SwipeRefreshLayout swipeRefreshLayout, RecyclerView recyclerView) {
        this.swipeRefreshLayout = swipeRefreshLayout;
        this.recyclerView = recyclerView;

        this.swipeRefreshLayout.setColorSchemeColors(Color.rgb(47, 223, 189));
        this.recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        this.recyclerView.addItemDecoration(new LinearItemDecoration());

        this.swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData(WHAT_DID_REFRESH);
            }
        });
        return this;
    }

    public SwipeRefreshHelper setAdapter(BaseQuickAdapter adapter) {
        this.adapter = adapter;
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                loadData(WHAT_DID_MORE);
            }
        }, recyclerView);
        recyclerView.setAdapter(adapter);

        return this;
    }

    public SwipeRefreshHelper configPage(String pageIndexName, String pageSizeName, int startIndex, int pageSize) {
        this.PAGE_INDEX_NAME = pageIndexName;
        this.PAGE_SIZE_NAME = pageSizeName;
        this.PAGE_INDEX = startIndex;
        this.PAGE_SIZE = pageSize;
        return this;
    }

    public SwipeRefreshHelper configRequest(String requestUrl, Class clazz) {
        this.requestUrl = requestUrl;
        return configRequest(requestUrl, DataGridConverter.create(clazz));
    }

    public SwipeRefreshHelper configPostRequest(String requestUrl, Class clazz) {
        this.post = true;
        this.requestUrl = requestUrl;
        return configRequest(requestUrl, DataGridConverter.create(clazz));
    }

    public SwipeRefreshHelper configRequest(String requestUrl, DataGridConverter dataGridConverter) {
        this.requestUrl = requestUrl;
        this.dataGridConverter = dataGridConverter;
        return this;
    }

    public SwipeRefreshHelper configPostRequest(String requestUrl, DataGridConverter dataGridConverter) {
        this.post = true;
        this.requestUrl = requestUrl;
        this.dataGridConverter = dataGridConverter;
        return this;
    }

    public void query() {
        loadData(WHAT_DID_REFRESH);
    }

    public void query(Map<String, Object> params) {
        if (params != null) {
            this.params = params;
        }
        loadData(WHAT_DID_REFRESH);
    }

    private void loadData(final int what) {
        params.put(PAGE_SIZE_NAME, PAGE_SIZE);

        if (what == WHAT_DID_REFRESH) {// 刷新
            adapter.setEnableLoadMore(false);
            params.put(PAGE_INDEX_NAME, PAGE_INDEX);
        } else {
            swipeRefreshLayout.setEnabled(false);
            params.put(PAGE_INDEX_NAME, currentPageIndex + 1);
        }
        QueryService service = RxRetrofit.getRetrofit().create(QueryService.class);
        Observable<ResponseBody> observable = post ? service.postQuery(requestUrl, params) : service.query(requestUrl,
                params);
        observable.subscribeOn(Schedulers.io()).map(new Function<ResponseBody, List>() {
            @Override
            public List apply(ResponseBody responseBody) throws Exception {
                DataGrid dataGrid = dataGridConverter.convert(responseBody.string());
                return dataGrid.getRows();
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<List>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(List list) {
                if (what == WHAT_DID_REFRESH) {// 刷新
                    currentPageIndex = PAGE_INDEX;// 初始化当前第几页
                    adapter.setNewData(list);
                    swipeRefreshLayout.setRefreshing(false);// 结束刷新
                    adapter.setEnableLoadMore(list.size() == PAGE_SIZE);
                } else {
                    currentPageIndex++;// 初始化当前第几页

                    adapter.addData(list);
                    if (list.size() < PAGE_SIZE) {
                        adapter.loadMoreEnd(true);
                    } else {
                        adapter.loadMoreComplete();
                    }
                    swipeRefreshLayout.setEnabled(true);
                }
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                Toast.makeText(recyclerView.getContext(), R.string.network_error, Toast.LENGTH_SHORT).show();
                adapter.loadMoreFail();
            }

            @Override
            public void onComplete() {
                adapter.loadMoreComplete();
            }
        });
    }

    public void setEnableLoadMore(boolean enable) {
        adapter.setEnableLoadMore(enable);
    }

    public void setEnableRefresh(boolean enable) {
        swipeRefreshLayout.setEnabled(enable);
    }

    public interface QueryService {

        @GET
        Observable<ResponseBody> query(@Url String url, @QueryMap Map<String, Object> map);

        @POST
        @FormUrlEncoded
        Observable<ResponseBody> postQuery(@Url String url, @FieldMap Map<String, Object> map);
    }
}
