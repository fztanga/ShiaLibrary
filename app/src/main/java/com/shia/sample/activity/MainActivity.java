package com.shia.sample.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.shia.library.util.TabLayoutUtil;
import com.shia.sample.R;
import com.shia.sample.fragment.HomeFragment;
import com.shia.sample.fragment.MoreFragment;

import java.util.ArrayList;

/**
 * Created by administrator on 2017/3/25.
 */
public class MainActivity extends AppCompatActivity {

    private CommonTabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // BarUtils.setStatusBarColor(this,
        // getResources().getColor(R.color.colorPrimaryDark));

        mTabLayout = (CommonTabLayout) findViewById(R.id.sliding_tabs);

        final ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new MoreFragment());

        ArrayList<CustomTabEntity> tabEntities = new ArrayList<>();
        tabEntities.add(new TabLayoutUtil.Tab("首页", R.drawable.tab_home_on, R.drawable.tab_home_off));
        tabEntities.add(new TabLayoutUtil.Tab("更多", R.drawable.tab_home_on, R.drawable.tab_more_off));
        mTabLayout.setTabData(tabEntities, this, R.id.tab_content, fragments);
    }

}
