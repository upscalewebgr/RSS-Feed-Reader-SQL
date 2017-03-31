package com.skaliotisdev.rssfeedreader;
/**
 * Created by Σπύρος Σκαλιώτης on 26/2/2017.
 */
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class RssItemViewActivity extends AppCompatActivity {
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_rss_item_view);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        WebView webView = (WebView) findViewById(R.id.rssItemWebView);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        progressBar.setVisibility(View.VISIBLE);
        String url = this.getIntent().getStringExtra("url");
        webView.loadUrl(url);

        webView.setWebViewClient(new WebViewClient() {
            private int       webViewPreviousState;
            private final int PAGE_STARTED    = 0x1;
            private final int PAGE_REDIRECTED = 0x2;

            @Override
            public void onPageFinished(WebView view, String url) {
                progressBar.setVisibility(View.GONE);
            }
        });

    }

}
