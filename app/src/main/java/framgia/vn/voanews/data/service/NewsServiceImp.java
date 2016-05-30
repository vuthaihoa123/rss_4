package framgia.vn.voanews.data.service;

import java.util.ArrayList;
import java.util.List;

import framgia.vn.voanews.data.model.News;
import framgia.vn.voanews.utils.LinkRssUtil;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by nghicv on 24/05/2016.
 */
public class NewsServiceImp implements NewsService {
    private Realm mRealm;
    public static final String TITLE_FIELD = "mTitle";
    public static final String CATEGORY_FIELD = "mCategory";

    public NewsServiceImp(Realm realm) {
        mRealm = realm;
    }

    @Override
    public void insertNews(final News news, final NewsContract.OnInsertNewsListener onInsertNewsListenner) {
        mRealm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealm(news);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                if (onInsertNewsListenner != null)
                    onInsertNewsListenner.onSuccess();
            }
        });
    }

    @Override
    public void insertNews(final List<News> newses, final NewsContract.OnInsertNewsListener onInsertNewsListenner) {
        final List<News> realmNews = new ArrayList<>();
        for(int i = 0; i < newses.size(); i++) {
            if(!isExists(newses.get(i)))
                realmNews.add(newses.get(i));
        }
        mRealm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealm(realmNews);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                if (onInsertNewsListenner != null)
                    onInsertNewsListenner.onSuccess();
            }
        });
    }

    @Override
    public RealmResults<News> getAllNews() {
        return mRealm.where(News.class).findAll();
    }

    @Override
    public void updateNewsToViewedNews(News news) {
        mRealm.beginTransaction();
        news.setIsViewed(true);
        mRealm.commitTransaction();
    }

    @Override
    public News getNewsByTitle(String title) {
        return mRealm.where(News.class)
                .equalTo(TITLE_FIELD, title)
                .findFirst();
    }

    @Override
    public RealmResults<News> getNewsByCategory(String category) {
        return mRealm.where(News.class)
                .equalTo(CATEGORY_FIELD, category)
                .findAll();
    }

    public void clearDB() {
        mRealm.beginTransaction();
        mRealm.delete(News.class);
        mRealm.commitTransaction();
    }

    @Override
    public boolean isExists(News news) {
        if (news.getCategory().equals(LinkRssUtil.TITLE_RSS[0]))
            return mRealm.where(News.class).equalTo(TITLE_FIELD, news.getTitle()).count() <= 1 ? false : true;
        return mRealm.where(News.class).equalTo(TITLE_FIELD, news.getTitle())
                .notEqualTo(CATEGORY_FIELD, LinkRssUtil.TITLE_RSS[0]).count() == 0 ? false : true;
    }
}
