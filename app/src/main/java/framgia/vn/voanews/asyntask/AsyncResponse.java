package framgia.vn.voanews.asyntask;

import java.util.List;

import framgia.vn.voanews.utils.RssItem;

/**
 * Created by toannguyen201194 on 13/05/2016.
 */
public interface AsyncResponse {
    void processFinish(List<RssItem> output);
}
