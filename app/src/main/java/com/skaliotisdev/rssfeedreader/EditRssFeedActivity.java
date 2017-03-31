package com.skaliotisdev.rssfeedreader;
/**
 * Created by Σπύρος Σκαλιώτης on 26/2/2017.
 */
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

public class EditRssFeedActivity extends AppCompatActivity {
    List<RssFeed> feeds;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_rss_feed);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        feeds = new DataBase(this).getRssFeeds();
        RssFeedsAdapter adapter = new RssFeedsAdapter(this, feeds);

        final ListView listView = (ListView) findViewById(R.id.editRssFeedListView);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
        @Override
            public  void onItemClick(AdapterView<?> parent, View view, int position, long id){
            AlertDialog.Builder dialog = new AlertDialog.Builder(EditRssFeedActivity.this);
            dialog.setTitle("Remove Feed");
            dialog.setMessage("Are you sure you want to remove this feed");
            final int positionToRemove = position;
            dialog.setNegativeButton("No", null);
            dialog.setPositiveButton("Yes",new AlertDialog.OnClickListener(){
                public void onClick(DialogInterface dialog, int which){

                    RssFeed selectedFeed = feeds.get(positionToRemove);

                    new DataBase(EditRssFeedActivity.this).deleteRSSFeed(selectedFeed);

                    feeds = new DataBase(EditRssFeedActivity.this).getRssFeeds();
                    RssFeedsAdapter adapterNew = new RssFeedsAdapter(EditRssFeedActivity.this,feeds);

                    listView.setAdapter(adapterNew);
                }
             });
                dialog.show();
            }
        });
    }

}
