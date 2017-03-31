package com.skaliotisdev.rssfeedreader;
/**
 * Created by Σπύρος Σκαλιώτης on 26/2/2017.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Σπύρος on 26/2/2017.
 */

public class DataBase extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1 ;
    private static final String DATABASE_NAME = "rssfeeds";
    private static final String TABlE_FEEDS = "feeds";
    private static final String KEY_ID = "id";
    private static final String KEY_FEED_TITLE = "title";
    private static final String KEY_FEED_ADDRESS = "address";

    public DataBase(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_FEEDS_TABLE = "CREATE TABLE " + TABlE_FEEDS + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_FEED_TITLE + " TEXT," + KEY_FEED_ADDRESS + " TEXT" + ")";
        db.execSQL(CREATE_FEEDS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + TABlE_FEEDS);
        onCreate(db);
    }

    public void AddRSSFeed(RssFeed feed){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_FEED_TITLE, feed.strRssFeedTitle);
        values.put(KEY_FEED_ADDRESS, feed.strRssFeedAddress);
        db.insert(TABlE_FEEDS, null, values);
        db.close();
    }

    public List<RssFeed> getRssFeeds(){
    List<RssFeed> results= new ArrayList<RssFeed>();
        String selectQuery = "SELECT * FROM " + TABlE_FEEDS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        try {
            if (cursor.moveToFirst()) {
                do {
                    RssFeed rssFeed = new RssFeed(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2));
                    results.add(rssFeed);
                } while (cursor.moveToNext());
            }
        }finally {
            db.close();
        }
        return results;
    }

    public void deleteRSSFeed(RssFeed feed){
        SQLiteDatabase db= this.getWritableDatabase();
        db.delete(TABlE_FEEDS, KEY_ID + " = ? ", new String[]{String.valueOf(feed.intID)});
        db.close();
    }
}
