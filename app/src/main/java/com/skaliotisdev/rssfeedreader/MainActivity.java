package com.skaliotisdev.rssfeedreader;
/**
 * Created by Σπύρος Σκαλιώτης on 26/2/2017.
 */
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

import at.theengine.android.simple_rss2_android.RSSItem;
import at.theengine.android.simple_rss2_android.SimpleRss2Parser;
import at.theengine.android.simple_rss2_android.SimpleRss2ParserCallback;
public class MainActivity extends AppCompatActivity {

    ArrayList<RssItem> rssItems;
    List<RssFeed> rssFeeds;
    int intFeedCount;
    int intRetrieveFeedCount;

    private static final int PROGRESS = 0x1;
    private ProgressBar progressBar;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        rssItems = new ArrayList<RssItem>();
        rssFeeds = new DataBase(this).getRssFeeds();
        intFeedCount = rssFeeds.size();
        intRetrieveFeedCount = 0;

         if (isOnline() == true) {
            progressBar.setVisibility(View.VISIBLE);
            for(int i = 0; i < rssFeeds.size(); i++){
                GetFeedItems(rssFeeds.get(i).strRssFeedAddress);
            }
        }
        else{
            Toast.makeText(getApplicationContext(),"Ελέγξε την σύνδεση σας στο διαδίκτυο", Toast.LENGTH_SHORT).show();
        }
    }

    public void GetFeedItems(String feedAddress) {
        try {
            SimpleRss2Parser parser = new SimpleRss2Parser("feed address link",
                    new SimpleRss2ParserCallback() {

                        @Override
                        public void onFeedParsed(List<RSSItem> items) {
                            for (int i = 0; i < items.size(); i++) {
                                RssItem item = new RssItem();
                                {
                                    item.setStrTitle(items.get(i).getTitle());
                                    item.setStrDate(items.get(i).getDate());
                                    item.setStrLink(items.get(i).getLink());
                                    rssItems.add(item);
                                }
                            }
                            populateListView();
                            progressBar.setVisibility(View.GONE);
                        }

                        @Override
                        public void onError(Exception ex) {
                            populateListView();
                        }
                    });
                    parser.parseAsync();
             }
            catch(Exception e){
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
                populateListView();
            }
    }
    public void populateListView() {
        intRetrieveFeedCount++;
        if (intRetrieveFeedCount == intFeedCount) {
            feedItemAdapter fd = new feedItemAdapter(this, rssItems);
            ListView listView = (ListView) findViewById(R.id.rssFeedItemListView);

            listView.setAdapter(fd);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                Intent intent = new Intent(MainActivity.this, RssItemViewActivity.class);
                    RssItem item = rssItems.get(position);
                    intent.putExtra("url", item.getStrLink().toString());
                    startActivity(intent);
                }
            });
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
           int id = item.getItemId();

//       if (id == R.id.action_settings) {
//            Intent intent =  new Intent(this, AboutActivity.class);
//            startActivity(intent);
//        }

        if (id == R.id.action_about) {
            Intent intent =  new Intent(this, AboutActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    public Boolean isOnline()	{
        ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        if(ni != null && ni.isConnected())
            return true;

        return false;
    }
}
