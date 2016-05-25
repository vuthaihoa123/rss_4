package framgia.vn.voanews.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import java.util.List;

import framgia.vn.voanews.R;
import framgia.vn.voanews.asyntask.AsyncResponse;
import framgia.vn.voanews.asyntask.ReadRssAsyntask;
import framgia.vn.voanews.data.model.News;
import framgia.vn.voanews.data.service.NewsContract;
import framgia.vn.voanews.data.service.NewsService;
import framgia.vn.voanews.data.service.NewsServiceImp;
import framgia.vn.voanews.utils.LinkRssUtil;
import io.realm.Realm;

/**
 * Created by Adm on 4/11/2016.
 */
public class SplashActivity extends AppCompatActivity {
    private NewsService mNewsService;
    private Realm mRealm;
    private boolean isLoaded;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        mRealm = Realm.getDefaultInstance();
        mNewsService = new NewsServiceImp(mRealm);
        getLink();
    }

    public void getLink() {
        for (int i = 0; i < LinkRssUtil.LINK_RSS.length; i++) {
            new ReadRssAsyntask(getBaseContext(), new AsyncResponse() {
                @Override
                public void processFinish(List<News> output) {
                    mNewsService.insertNews(output, new NewsContract.OnInsertNewsListener() {
                        @Override
                        public void onSuccess() {
                            if(!isLoaded) {
                                isLoaded = true;
                                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        }
                    });
                }
            }).execute(LinkRssUtil.LINK_RSS[i], LinkRssUtil.TITLE_RSS[i]);
        }
    }


    @Override
    protected void onDestroy() {
        mRealm.close();
        super.onDestroy();
    }
}
