package framgia.vn.voanews.activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import framgia.vn.voanews.R;
import framgia.vn.voanews.adapters.ViewpagerAdapter;
import framgia.vn.voanews.asyntask.AsyncResponse;
import framgia.vn.voanews.asyntask.ReadRssAsyntask;
import framgia.vn.voanews.fragments.AllZonesFragment;
import framgia.vn.voanews.utils.LinkRssUtil;
import framgia.vn.voanews.data.model.News;
import framgia.vn.voanews.data.service.NewsContract;
import framgia.vn.voanews.data.service.NewsService;
import framgia.vn.voanews.data.service.NewsServiceImp;
import framgia.vn.voanews.fragments.AllZonesFragment;
import framgia.vn.voanews.utils.LinkRssUtil;
import framgia.vn.voanews.utils.TimeUtils;
import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {
    private ViewpagerAdapter mViewpagerAdapter;
    private Toolbar mToolbar;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private ReadRssAsyntask mReadRssAsyntask;
    private NewsService mNewsService;
    private Realm mRealm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        mRealm = Realm.getDefaultInstance();
        mNewsService = new NewsServiceImp(mRealm);
    }

    private void initViews() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        mViewPager = (ViewPager) findViewById(R.id.viewpager_home);
        setupViewPager(mViewPager, LinkRssUtil.TITLE_RSS);
        mTabLayout = (TabLayout) findViewById(R.id.tab_home);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    public void setupViewPager(ViewPager viewPager, String[] titleRss) {
        mViewpagerAdapter = new ViewpagerAdapter(getSupportFragmentManager());
        for (int i = 0; i < titleRss.length; i++) {
            mViewpagerAdapter.addFragment(new AllZonesFragment(), titleRss[i]);
        }
        viewPager.setAdapter(mViewpagerAdapter);
    }
}
