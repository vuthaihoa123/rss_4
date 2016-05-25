package framgia.vn.voanews.asyntask;

import java.util.List;

import framgia.vn.voanews.data.model.News;

/**
 * Created by toannguyen201194 on 13/05/2016.
 */
public interface AsyncResponse {
    void processFinish(List<News> output);
}
