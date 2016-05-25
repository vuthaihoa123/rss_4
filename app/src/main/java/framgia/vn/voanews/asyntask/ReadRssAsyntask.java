package framgia.vn.voanews.asyntask;

import android.content.Context;
import android.os.AsyncTask;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import framgia.vn.voanews.utils.RssItem;
import framgia.vn.voanews.utils.XmlParser;

/**
 * Created by nguyen van toan on 5/22/2016.
 */
public class ReadRssAsyntask extends AsyncTask<String, Void, List<RssItem>> {
    private List<RssItem> mRssItemList = new ArrayList<>();
    private XmlParser mXmlParser = new XmlParser();
    private Context mContext;
    private AsyncResponse mAsyncResponse;

    public ReadRssAsyntask(Context context, List<RssItem> rssItemList, AsyncResponse asyncResponse) {
        this.mRssItemList = rssItemList;
        this.mContext = context;
        this.mAsyncResponse = asyncResponse;
    }

    @Override
    protected List<RssItem> doInBackground(String... params) {
        String url =  params[0];
        List<RssItem> rssItems = new ArrayList<>();
        XmlPullParser myParser;
        try {
            myParser = mXmlParser.downLoadUrl(url);
            rssItems = mXmlParser.parse(myParser);
        } catch (IOException | XmlPullParserException e) {
            e.printStackTrace();
        }


        return rssItems;
    }

    @Override
    protected void onPostExecute(List<RssItem> result) {
        super.onPostExecute(result);
        mAsyncResponse.processFinish(result);
    }
}
