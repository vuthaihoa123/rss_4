package framgia.vn.voanews.utils;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import framgia.vn.voanews.data.model.News;

/**
 * Created by toannguyen201194 on 20/05/2016.
 */
public class XmlParser {
    private static final int READ_TIMEOUT = 1000;
    private static final int CONNECTION_TIMEOUT = 10000;
    private static final int CHECKCONNECT = 200;
    private static final String SELECT_IMG = "url";
    private static final String TAG_ITEM = "item";
    private static final String TAG_TITLE = "title";
    private static final String TAG_DESCRIPTION = "description";
    private static final String TAG_LINK = "link";
    private static final String TAG_PUDATE = "pubDate";
    private static final String TAG_AUTHOR = "author";
    private static final String TAG_ENCLOSURE = "enclosure";
    private static final String REQUEST_METHOD = "GET";
    private News mNews;
    private int mStatusConnection;
    private XmlPullParserFactory mXmlPullParserFactory;
    private XmlPullParser mParser;
    private HttpURLConnection mConnection;


    public XmlPullParser downLoadUrl(String urlstring) throws IOException, XmlPullParserException {
        URL url = new URL(urlstring);
        mConnection = (HttpURLConnection) url.openConnection();
        mConnection.setReadTimeout(READ_TIMEOUT);
        mConnection.setConnectTimeout(CONNECTION_TIMEOUT);
        mConnection.setRequestMethod(REQUEST_METHOD);
        mConnection.setDoInput(true);
        mConnection.connect();
        mStatusConnection = mConnection.getResponseCode();
        if (mStatusConnection == CHECKCONNECT) {
            InputStream is = mConnection.getInputStream();
            mXmlPullParserFactory = XmlPullParserFactory.newInstance();
            mParser = mXmlPullParserFactory.newPullParser();
            mParser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            mParser.setInput(is, null);
        }
        return mParser;
    }

    public List<News> parse(XmlPullParser myParser, String category) {
        List<News> data = new ArrayList<>();
        int event;
        String text = "";

        try {
            boolean checktitle = false;
            event = myParser.getEventType();
            while (event != XmlPullParser.END_DOCUMENT) {
                String name = myParser.getName();
                switch (event) {
                    case XmlPullParser.START_TAG:
                        if (name.equals(TAG_ITEM)) {
                            checktitle = true;
                            mNews = new News();
                            mNews.setCategory(category);
                        }
                        break;
                    case XmlPullParser.TEXT:
                        text = myParser.getText().trim();
                        break;
                    case XmlPullParser.END_TAG:
                        if (checktitle) {
                            if (name.equals(TAG_TITLE)) {
                                mNews.setTitle(text);
                            } else if (name.equals(TAG_PUDATE)) {
                                mNews.setDate(TimeUtils.stringToDate(text));
                            } else if (name.equals(TAG_DESCRIPTION)) {
                                mNews.setDescription(text);
                            } else if (name.equals(TAG_LINK)) {
                                mNews.setLink(text);
                            } else if (name.equals(TAG_AUTHOR)) {
                                mNews.setAuthor(text);
                            } else if (name.equals(TAG_ENCLOSURE)) {
                                mNews.setEnclosure(myParser.getAttributeValue(null, SELECT_IMG));
                            } else if (name.equals(TAG_ITEM)) {
                                checktitle = false;
                                data.add(mNews);
                            }
                        }

                        break;
                }
                event = myParser.next();
            }
        } catch (XmlPullParserException | IOException e) {
            e.printStackTrace();
        }
        return data;
    }
}
