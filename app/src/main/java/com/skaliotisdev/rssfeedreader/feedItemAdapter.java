package com.skaliotisdev.rssfeedreader;
/**
 * Created by Σπύρος Σκαλιώτης on 26/2/2017.
 */
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class feedItemAdapter extends ArrayAdapter<RssItem>{

    public feedItemAdapter(Context context, ArrayList<RssItem> items){super(context, 0, items);}

    private static final DateFormat PARSE_PATTERN = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US);
    private static final DateFormat FORMAT_PATTERN = new SimpleDateFormat("EEEE, dd MMMM yyyy");
    String strOutputDate;
    public View getView(int position, View convertView, ViewGroup parent){
        RssItem item = getItem(position);
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.rss_feed_item_row, parent,  false);
        }

       TextView title = (TextView) convertView.findViewById(R.id.textViewTitle);
       title.setText(item.getStrTitle());

        try {
            Date objDate = PARSE_PATTERN.parse(item.getStrDate());
            strOutputDate = FORMAT_PATTERN.format(objDate);
        } catch (ParseException e) {
        }

        TextView date = (TextView) convertView.findViewById(R.id.textViewDate);
        date.setText(strOutputDate);
        return convertView;
    }
}
