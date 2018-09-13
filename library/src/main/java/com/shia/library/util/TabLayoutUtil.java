package com.shia.library.util;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.SegmentTabLayout;
import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;
import java.util.List;

public class TabLayoutUtil {

    public static void initSegmentTabLayout(FragmentManager fm, final SegmentTabLayout tabLayout,
            final ViewPager viewPager, final List<Fragment> fragments, final String[] titles, int limit) {
        viewPager.setOffscreenPageLimit(limit);
        viewPager.setAdapter(new FragmentPagerAdapter(fm) {

            @Override
            public int getCount() {
                return fragments.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return titles[position];
            }

            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }
        });
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                tabLayout.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        tabLayout.setTabData(titles);
        tabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                viewPager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {
            }
        });
        viewPager.setCurrentItem(0);
    }

    public static void initSlidingTabLayout(FragmentManager fm, final SlidingTabLayout tabLayout,
            final ViewPager viewPager, final List<Fragment> fragments, final String[] titles, int limit) {
        viewPager.setOffscreenPageLimit(limit);
        viewPager.setAdapter(new FragmentPagerAdapter(fm) {

            @Override
            public int getCount() {
                return fragments.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return titles[position];
            }

            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }
        });
        tabLayout.setViewPager(viewPager, titles);
        viewPager.setCurrentItem(0);
    }

    public static void initCommonTabLayout(FragmentManager fm, final CommonTabLayout tabLayout,
            final ViewPager viewPager, final ArrayList<CustomTabEntity> tabEntities, int limit) {
        viewPager.setOffscreenPageLimit(limit);
        viewPager.setAdapter(new FragmentPagerAdapter(fm) {

            @Override
            public int getCount() {
                return tabEntities.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return tabEntities.get(position).getTabTitle();
            }

            @Override
            public Fragment getItem(int position) {
                return ((Tab) tabEntities.get(position)).getFragment();
            }
        });
        tabLayout.setTabData(tabEntities);
        tabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                viewPager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {
            }
        });
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                tabLayout.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        viewPager.setCurrentItem(0);
    }

    public static class Tab implements CustomTabEntity {
        public String title;
        public int selectedIcon;
        public int unSelectedIcon;
        public Fragment fragment;

        public Tab(String title, int selectedIcon, int unSelectedIcon) {
            this.title = title;
            this.selectedIcon = selectedIcon;
            this.unSelectedIcon = unSelectedIcon;
        }

        public Tab(String title, int selectedIcon, int unSelectedIcon, Fragment fragment) {
            this.title = title;
            this.selectedIcon = selectedIcon;
            this.unSelectedIcon = unSelectedIcon;
            this.fragment = fragment;
        }

        @Override
        public String getTabTitle() {
            return title;
        }

        @Override
        public int getTabSelectedIcon() {
            return selectedIcon;
        }

        @Override
        public int getTabUnselectedIcon() {
            return unSelectedIcon;
        }

        public Fragment getFragment() {
            return fragment;
        }
    }
}
