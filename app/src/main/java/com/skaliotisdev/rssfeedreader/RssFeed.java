package com.skaliotisdev.rssfeedreader;
/**
 * Created by Σπύρος Σκαλιώτης on 26/2/2017.
 */
public class RssFeed {
    public String strRssFeedTitle;
    public String strRssFeedAddress;
    public int intID;

    public RssFeed(String title, String address){
        strRssFeedTitle = title;
        strRssFeedAddress = address;
    }

    public RssFeed(int feedID, String title, String address){
        intID = feedID;
        strRssFeedTitle = title;
        strRssFeedAddress = address;
    }
}
