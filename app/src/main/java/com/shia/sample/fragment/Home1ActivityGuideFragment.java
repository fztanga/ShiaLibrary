package com.shia.sample.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.blankj.utilcode.util.ConvertUtils;
import com.bumptech.glide.Glide;
import com.shia.library.http.RxRetrofit;
import com.shia.library.util.SwipeRefreshHelper;
import com.shia.sample.R;
import com.shia.sample.adapter.NewsAdapter;
import com.shia.sample.bean.News;
import com.shia.sample.service.WHKPBDataGridConverter;
import com.shia.sample.service.WHKPBTransfer;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;
import io.reactivex.functions.Consumer;
import io.reactivex.internal.functions.Functions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by administrator on 2017/3/25.
 */
public class Home1ActivityGuideFragment extends Fragment {

    private List<News> banners = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_recycler_refresh_notitle, container, false);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swiperefreshlayout);

        final NewsAdapter adapter = new NewsAdapter(getContext());
        final Banner banner = (Banner) getActivity().getLayoutInflater().inflate(R.layout.layout_banner,
                (ViewGroup) recyclerView.getParent(), false);
        banner.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ConvertUtils.dp2px(160)));
        banner.setIndicatorGravity(BannerConfig.RIGHT);
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                adapter.openDetail(banners.get(position));
            }
        });
        adapter.addHeaderView(banner);

        SwipeRefreshHelper
                .create()
                .bindView(swipeRefreshLayout, recyclerView)
                .setAdapter(adapter)
                .configPage("pageNo", "pageSize", 1, 20)
                .configPostRequest("app/qmgb-content-list.jspx?channelId=65&typeId=1",
                        new WHKPBDataGridConverter(News.class, "contents")).query();

        // 轮播图
        RxRetrofit.getRetrofit().create(SwipeRefreshHelper.QueryService.class)
                .postQuery("app/qmgb-content-list.jspx?channelId=69&typeId=3", new HashMap<String, Object>())
                .compose(WHKPBTransfer.<News> listTransfer(News.class, "contents"))
                .subscribe(new Consumer<List<News>>() {
                    @Override
                    public void accept(List<News> newsList) throws Exception {
                        List<String> images = new ArrayList<>(), titles = new ArrayList<>();
                        for (int i = 0; i < Math.min(newsList.size(), 5); i++) {
                            News news = newsList.get(i);
                            banners.add(news);
                            images.add(RxRetrofit.BASE_URL + news.getTypeImg());
                            titles.add(news.getTitle());
                        }
                        banner.setImages(images).setBannerTitles(titles).setImageLoader(new ImageLoader() {
                            @Override
                            public void displayImage(Context context, Object path, ImageView imageView) {
                                Glide.with(getContext()).load(path).crossFade().into(imageView);
                            }
                        }).start();
                    }
                }, Functions.<Throwable> emptyConsumer());
        return view;
    }
}
