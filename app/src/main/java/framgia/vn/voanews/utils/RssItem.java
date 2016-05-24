package framgia.vn.voanews.utils;

/**
 * Created by toannguyen201194 on 19/05/2016.
 */
public class RssItem {
    private String mTitle;
    private String mPubdate;
    private String mDescripion;
    private String mLink;
    private String mImgenclosure;
    private String mAuthor;

    public RssItem(String mtitle, String mpubdate, String mdescripion, String mlink, String mimgenclosure, String mauthor) {
        this.mTitle = mtitle;
        this.mPubdate = mpubdate;
        this.mDescripion = mdescripion;
        this.mLink = mlink;
        this.mImgenclosure = mimgenclosure;
        this.mAuthor = mauthor;
    }

    public RssItem() {
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getPubdate() {
        return mPubdate;
    }

    public void setPubdate(String mPubdate) {
        this.mPubdate = mPubdate;
    }

    public String getDescripion() {
        return mDescripion;
    }

    public void setDescripion(String mDescripion) {
        this.mDescripion = mDescripion;
    }

    public String getLink() {
        return mLink;
    }

    public void setLink(String mLink) {
        this.mLink = mLink;
    }

    public String getImgenclosure() {
        return mImgenclosure;
    }

    public void setImgenclosure(String mImgenclosure) {
        this.mImgenclosure = mImgenclosure;
    }

    public String getAuthor() {
        return mAuthor;
    }

    public void setAuthor(String mAuthor) {
        this.mAuthor = mAuthor;
    }

}
