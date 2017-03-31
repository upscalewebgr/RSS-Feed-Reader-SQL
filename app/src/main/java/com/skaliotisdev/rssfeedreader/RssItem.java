package com.skaliotisdev.rssfeedreader;
/**
 * Created by Σπύρος Σκαλιώτης on 26/2/2017.
 */
import java.net.URL;

public class RssItem {
    private String strTitle;
    private String strDate;
    private URL strLink;

    public RssItem(String title, String date, URL link){
        this.strTitle = title;
        this.strDate = date;
        this.strLink = link;
    }

    public RssItem(){

    }

    public void setStrTitle(String title){
        this.strTitle = title;
    }

    public void setStrDate(String date){
        this.strDate = date;
    }

    public void setStrLink(URL link){
        this.strLink = link;
    }

    public String getStrTitle(){
        return strTitle;
    }

    public String getStrDate(){
        return strDate;
    }

    public URL getStrLink(){
        return strLink;
    }
}
