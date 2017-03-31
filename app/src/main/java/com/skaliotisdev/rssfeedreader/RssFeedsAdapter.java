package com.skaliotisdev.rssfeedreader;
/**
 * Created by Σπύρος Σκαλιώτης on 26/2/2017.
 */
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class RssFeedsAdapter extends ArrayAdapter<RssFeed> {

    public RssFeedsAdapter(Context context, List<RssFeed> feeds){
        super(context, 0, feeds);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        RssFeed feed = getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.rss_edit_feed_item_row, parent, false);
        }
        TextView feedAddress = (TextView) convertView.findViewById(R.id.rssEditFeedAddressTextView);
        TextView feedTitle = (TextView) convertView.findViewById(R.id.rssEditFeedAddressTextView);
        feedAddress.setText(feed.strRssFeedAddress);
        feedTitle.setText(feed.strRssFeedTitle);

        return convertView;
    }
}
