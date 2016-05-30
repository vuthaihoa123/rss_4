package framgia.vn.voanews.data.service;

import java.util.List;

import framgia.vn.voanews.data.model.News;
import io.realm.RealmResults;

/**
 * Created by nghicv on 23/05/2016.
 */
public interface NewsService {
    void insertNews(News news, NewsContract.OnInsertNewsListener onInsertNewsListenner);
    void insertNews(List<News> newses, NewsContract.OnInsertNewsListener onInsertNewsListenner);
    RealmResults<News> getAllNews();
    void updateNewsToViewedNews(News news);
    News getNewsByTitle(String title);
    RealmResults<News> getNewsByCategory(String category);
    void clearDB();
    public boolean isExists(News news);
}
