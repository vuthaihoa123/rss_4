package framgia.vn.voanews.models;

/**
 * Created by hoavt on 24/05/2016.
 */
public class NewsItem {
    private int mPathImg;
    private String mTitle;
    private String mTime;
    private boolean mIsHot;

    public NewsItem(int ivNews, String tvTitle, String tvTime, boolean isHot) {
        mPathImg = ivNews;
        mTitle = tvTitle;
        mTime = tvTime;
        mIsHot = isHot;
    }

    public int getIvNews() {
        return mPathImg;
    }

    public String getTvTitle() {
        return mTitle;
    }

    public String getTvTime() {
        return mTime;
    }

    public boolean isHot() {
        return mIsHot;
    }
}
