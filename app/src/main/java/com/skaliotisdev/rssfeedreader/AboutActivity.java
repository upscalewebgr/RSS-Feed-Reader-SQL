package com.skaliotisdev.rssfeedreader;
/**
 * Created by Σπύρος Σκαλιώτης on 26/2/2017.
 */
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        final Context context;
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        TextView textAppVersion = (TextView) findViewById(R.id.textViewAppVersion);
        ImageView imageMail = (ImageView) findViewById(R.id.imageViewMail);
        ImageView imageRate = (ImageView) findViewById(R.id.imageViewRate);
        ImageView imageLike = (ImageView) findViewById(R.id.imageViewLike);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        textAppVersion.setText("Agrotes.Eu έκδοση : " + BuildConfig.VERSION_NAME);

        imageMail.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("message/rfc822");
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"info@skaliotisdev.com"});
                intent.putExtra(Intent.EXTRA_SUBJECT, "Hello");
                intent.putExtra(Intent.EXTRA_TEXT, "");
                startActivity(Intent.createChooser(intent, "Στείλτε mail"));
            }
        });

        imageRate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.android.vending");
                ComponentName comp = new ComponentName("com.android.vending", "com.google.android.finsky.activities.LaunchUrlHandlerActivity");
                launchIntent.setComponent(comp);
                launchIntent.setData(Uri.parse("market://details?id=4946364898076253938"));

                startActivity(launchIntent);
            }
        });

        imageLike.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("fb://profile/skaliotisdev"));
                    startActivity(intent);
                } catch(Exception e) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/skaliotisdev")));
                }
            }
        });
    }
}
