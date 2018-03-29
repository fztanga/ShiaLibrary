package com.shia.sample.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.shia.library.util.SwipeRefreshHelper;
import com.shia.sample.R;
import com.shia.sample.adapter.NewsAdapter;
import com.shia.sample.bean.News;
import com.shia.sample.service.WHKPBDataGridConverter;

/**
 * Created by administrator on 2017/3/25.
 */
public class Home3TypicalLeadFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_common_recycler_refresh_notitle, container, false);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swiperefreshlayout);

        SwipeRefreshHelper
                .create()
                .bindView(swipeRefreshLayout, recyclerView)
                .setAdapter(new NewsAdapter(getContext()))
                .configPage("pageNo", "pageSize", 1, 20)
                .configPostRequest("app/qmgb-content-list.jspx?channelId=67&typeId=1",
                        new WHKPBDataGridConverter(News.class, "contents")).query();
        return view;
    }
}
