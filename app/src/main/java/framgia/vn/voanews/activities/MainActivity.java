package framgia.vn.voanews.activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import framgia.vn.voanews.R;
import framgia.vn.voanews.adapters.ViewpagerAdapter;
import framgia.vn.voanews.fragments.AllZonesFragment;
import framgia.vn.voanews.utils.LinkRssUtil;

public class MainActivity extends AppCompatActivity {
    private ViewpagerAdapter mViewpagerAdapter;
    private Toolbar mToolbar;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private LinkRssUtil mLinkRss;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        mViewPager = (ViewPager) findViewById(R.id.viewpager_home);
        setupviewpager(mViewPager, mLinkRss.TITLE_RSS);
        mTabLayout = (TabLayout) findViewById(R.id.tab_home);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    public void setupviewpager(ViewPager viewPager, String[] titleRss) {
        mViewpagerAdapter = new ViewpagerAdapter(getSupportFragmentManager());
        for (int i = 0; i < titleRss.length; i++) {
            mViewpagerAdapter.addFragment(new AllZonesFragment(), titleRss[i]);
        }
        viewPager.setAdapter(mViewpagerAdapter);
    }
}
