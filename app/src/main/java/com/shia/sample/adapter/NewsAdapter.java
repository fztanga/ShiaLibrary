package com.shia.sample.adapter;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.view.View;
import android.view.ViewGroup;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shia.sample.R;
import com.shia.sample.bean.News;
import com.shia.sample.bean.NewsDetail;
import com.shia.sample.databinding.NewsBinding;
import com.shia.sample.service.AppService;
import com.shia.sample.service.WHKPBObserver;
import com.shia.library.activity.WebViewActivity;
import com.shia.library.http.RxRetrofit;
import com.shia.library.http.RxSchedulers;
import com.shia.library.util.FileUtil;

public class NewsAdapter extends BaseQuickAdapter<News, NewsAdapter.ViewHolder> {

    private Context context;

    public NewsAdapter(Context context) {
        super(R.layout.item_news);
        this.context = context;

        this.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                openDetail(getItem(position));
            }
        });
    }

    public void openDetail(News news) {
        RxRetrofit.getRetrofit().create(AppService.class).getNewsDetail(news.getId())
                .compose(RxSchedulers.<NewsDetail> defaultSchedulers())
                .subscribe(new WHKPBObserver<NewsDetail>(context) {

                    @Override
                    protected void onSuccess(NewsDetail newsDetail) {
                        String template = FileUtil.readAssertsFile(context, "template.html");
                        template = template.replace("##title##", newsDetail.getTitle())
                                .replace("##otherinfo##", newsDetail.getDate())
                                .replace("##content##", newsDetail.getTxt())
                                .replace("/u/cms/", RxRetrofit.BASE_URL + "/u/cms/");
                        Intent intent = new Intent(context, WebViewActivity.class);
                        intent.putExtra("title", "详情");
                        intent.putExtra("data", template);
                        intent.putExtra("jsUrl", "javascript:setImgWidth()");
                        context.startActivity(intent);
                    }
                });
    }

    @Override
    protected void convert(ViewHolder helper, News news) {
        NewsBinding binding = helper.getBinding();
        // binding.setVariable(BR.news, news);
        binding.setNews(news);
    }

    @Override
    protected View getItemView(int layoutResId, ViewGroup parent) {
        NewsBinding binding = DataBindingUtil.inflate(mLayoutInflater, layoutResId, parent, false);
        if (binding == null) {
            return super.getItemView(layoutResId, parent);
        }
        View view = binding.getRoot();
        view.setTag(binding);
        return view;
    }

    @Override
    protected ViewHolder createBaseViewHolder(View view) {
        return new ViewHolder(view);
    }

    class ViewHolder extends BaseViewHolder {

        public ViewHolder(View view) {
            super(view);
        }

        public NewsBinding getBinding() {
            return (NewsBinding) getConvertView().getTag();
        }
    }

}
