package com.shia.sample.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.shia.sample.R;
import com.shia.sample.fragment.HomeFragment;
import com.shia.sample.fragment.MoreFragment;
import com.shia.library.activity.BaseActivity;
import com.shia.library.fragment.BaseFragment;
import java.util.ArrayList;

/**
 * Created by administrator on 2017/3/25.
 */
public class MainActivity extends BaseActivity {

    private CommonTabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTabLayout = (CommonTabLayout) findViewById(R.id.sliding_tabs);

        final ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new MoreFragment());

        ArrayList<CustomTabEntity> tabEntities = new ArrayList<>();
        tabEntities.add(new Tab("首页", R.drawable.tab_home_on, R.drawable.tab_home_off));
        tabEntities.add(new Tab("更多", R.drawable.tab_home_on, R.drawable.tab_more_off));
        mTabLayout.setTabData(tabEntities, this, R.id.tab_content, fragments);
        mTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                ((BaseFragment) fragments.get(position)).onSelected();
            }

            @Override
            public void onTabReselect(int position) {
                ((BaseFragment) fragments.get(position)).onSelected();
            }
        });
    }

    class Tab implements CustomTabEntity {
        public String title;
        public int selectedIcon;
        public int unSelectedIcon;

        public Tab(String title, int selectedIcon, int unSelectedIcon) {
            this.title = title;
            this.selectedIcon = selectedIcon;
            this.unSelectedIcon = unSelectedIcon;
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
    }
}
