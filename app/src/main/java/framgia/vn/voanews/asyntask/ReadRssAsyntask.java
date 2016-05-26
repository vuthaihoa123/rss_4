package framgia.vn.voanews.asyntask;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import framgia.vn.voanews.data.model.News;
import framgia.vn.voanews.data.service.NewsContract;
import framgia.vn.voanews.utils.LinkRssUtil;
import framgia.vn.voanews.utils.XmlParser;

/**
 * Created by nguyen van toan on 5/22/2016.
 */
public class ReadRssAsyntask extends AsyncTask<String, Void, List<News>> {
    private XmlParser mXmlParser = new XmlParser();
    private Context mContext;
    private AsyncResponse mAsyncResponse;

    public ReadRssAsyntask(Context context, AsyncResponse asyncResponse) {
        this.mContext = context;
        this.mAsyncResponse = asyncResponse;
    }

    @Override
    protected List<News> doInBackground(String... params) {
        String url =  params[0];
        List<News> newses = null;
        XmlPullParser myParser;
        try {
            myParser = mXmlParser.downLoadUrl(params[0]);
            newses = mXmlParser.parse(myParser, params[1]);
        } catch (IOException | XmlPullParserException e) {
            e.printStackTrace();
        }
        return newses;
    }

    @Override
    protected void onPostExecute(List<News> result) {
        super.onPostExecute(result);
        mAsyncResponse.processFinish(result);
    }
}
