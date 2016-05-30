package framgia.vn.voanews.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import framgia.vn.voanews.R;
import framgia.vn.voanews.adapters.holders.HotNewsViewHolder;
import framgia.vn.voanews.adapters.holders.OtherNewsViewHolder;
import framgia.vn.voanews.models.NewsItem;

/**
 * Created by hoavt on 24/05/2016.
 */
public class NewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public final int ITEM_HOT_NEW = 6;
    public final int ITEM_OTHER_NEW = 9;
    // The mItems to display in your RecyclerView
    private List<Object> mItems;

    // Provide a suitable constructor (depends on the kind of dataset)
    public NewsAdapter(List<Object> items) {
        mItems = items;
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mItems.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (mItems.get(position) instanceof NewsItem) {
            NewsItem newsItem = (NewsItem) mItems.get(position);
            if (newsItem.isHot()) return ITEM_HOT_NEW;
            else return ITEM_OTHER_NEW;
        }
        return -1;
    }

    /**
     * This method creates different RecyclerView.ViewHolder objects based on the item view type.\
     *
     * @param viewGroup ViewGroup container for the item
     * @param viewType  type of view to be inflated
     * @return viewHolder to be inflated
     */
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());

        switch (viewType) {
            case ITEM_HOT_NEW:
                View hotNewsView = inflater.inflate(R.layout.item_hot_news, viewGroup, false);
                viewHolder = new HotNewsViewHolder(hotNewsView);
                break;
            case ITEM_OTHER_NEW:
                View otherNewsView = inflater.inflate(R.layout.item_other_news, viewGroup, false);
                viewHolder = new OtherNewsViewHolder(otherNewsView);
                break;
        }
        return viewHolder;
    }

    /**
     * This method internally calls onBindViewHolder(ViewHolder, int) to update the
     * RecyclerView.ViewHolder contents with the item at the given position
     * and also sets up some private fields to be used by RecyclerView.
     *
     * @param viewHolder The type of RecyclerView.ViewHolder to populate
     * @param position   Item position in the viewgroup.
     */
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        switch (viewHolder.getItemViewType()) {
            case ITEM_HOT_NEW:
                HotNewsViewHolder hotNewsViewHolder = (HotNewsViewHolder) viewHolder;
                configHotNewsHolder(hotNewsViewHolder, position);
                break;
            case ITEM_OTHER_NEW:
                OtherNewsViewHolder otherNewsViewHolder = (OtherNewsViewHolder) viewHolder;
                configOtherNewsHolder(otherNewsViewHolder, position);
                break;
        }
    }

    private void configOtherNewsHolder(OtherNewsViewHolder otherNewsViewHolder, int position) {
        NewsItem newsItem = (NewsItem) mItems.get(position);
        if (newsItem != null) {
            otherNewsViewHolder.getIvNews().setImageResource(newsItem.getIvNews());
            otherNewsViewHolder.getTvTitle().setText(newsItem.getTvTitle());
            otherNewsViewHolder.getTvTime().setText(newsItem.getTvTime());
        }
    }

    private void configHotNewsHolder(HotNewsViewHolder hotNewsViewHolder, int position) {
        NewsItem newsItem = (NewsItem) mItems.get(position);
        if (newsItem != null) {
            hotNewsViewHolder.getIvHotNews().setImageResource(newsItem.getIvNews());
            hotNewsViewHolder.getTvHotTitle().setText(newsItem.getTvTitle());
            hotNewsViewHolder.getTvHotTime().setText(newsItem.getTvTime());
        }
    }

    public List<Object> getItemsArr() {
        return mItems;
    }

    public void setItemsArr(List<Object> items) {
        mItems = items;
    }
}
