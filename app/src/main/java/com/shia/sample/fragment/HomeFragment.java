package com.shia.sample.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.flyco.tablayout.SegmentTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
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
        setupViewPager(mViewPager);

        mTabLayout = (SegmentTabLayout) view.findViewById(R.id.sliding_tabs);
        String[] titles = new String[] { "活动指南", "工作动态", "典型引领" };
        mTabLayout.setTabData(titles);
        mTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                mViewPager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {
            }
        });

        mTabLayout.setCurrentTab(0);
        return view;
    }

    private void setupViewPager(ViewPager mViewPager) {
        final List<Fragment> fragments = new ArrayList<>();
        fragments.add(new Home1ActivityGuideFragment());
        fragments.add(new Home2WorkDynamicsFragment());
        fragments.add(new Home3TypicalLeadFragment());
        mViewPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {

            @Override
            public int getCount() {
                return fragments.size();
            }

            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }
        });
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                mTabLayout.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    // private void setTabLayout() {
    // for (int i = 0; i < mViewPager.getAdapter().getCount(); i++) {
    // TabLayout.Tab tab = mTabLayout.newTab().setText(titles[i]);
    // mTabLayout.addTab(tab);
    // }
    // // mTabLayout.setupWithViewPager(mViewPager);
    // mViewPager.addOnPageChangeListener(new
    // TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
    // mTabLayout.addOnTabSelectedListener(new
    // TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
    // }
}
