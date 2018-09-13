package com.shia.sample.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.flyco.tablayout.SegmentTabLayout;
import com.shia.library.util.TabLayoutUtil;
import com.shia.sample.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by administrator on 2017/3/25.
 */
public class HomeFragment extends Fragment {

    private ViewPager mViewPager;
    private SegmentTabLayout mTabLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        mViewPager = (ViewPager) view.findViewById(R.id.viewpager);

        mTabLayout = (SegmentTabLayout) view.findViewById(R.id.sliding_tabs);
        String[] titles = new String[] { "活动指南", "工作动态", "典型引领" };
        final List<Fragment> fragments = new ArrayList<>();
        fragments.add(new Home1ActivityGuideFragment());
        fragments.add(new Home2WorkDynamicsFragment());
        fragments.add(new Home3TypicalLeadFragment());
        TabLayoutUtil.initSegmentTabLayout(getChildFragmentManager(), mTabLayout, mViewPager, fragments, titles, 2);
        return view;
    }
}
