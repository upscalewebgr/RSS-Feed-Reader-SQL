package com.skaliotisdev.rssfeedreader;
/**
 * Created by Σπύρος Σκαλιώτης on 26/2/2017.
 */
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddFeedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_feed);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Button saveButton = (Button) findViewById(R.id.addFeedButton);
        final EditText feedName = (EditText) findViewById(R.id.addFeedNameEditText);
        final EditText feedAddress = (EditText) findViewById(R.id.addFeedAddressEditText);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(feedAddress.getText().length() < 6 ) {
                    Toast.makeText(getApplicationContext(),"Η διέυθυνση RSS είναι πολύ μικρή", Toast.LENGTH_LONG).show();
                    return;
                }
                    DataBase db = new DataBase(AddFeedActivity.this);
                    RssFeed feed = new RssFeed(feedName.getText().toString(), feedAddress.getText().toString());
                    db.AddRSSFeed(feed);
                    Toast.makeText(getApplicationContext(),"Η διέυθυνση RSS προστέθηκε", Toast.LENGTH_LONG).show();
                    feedAddress.setText("");
                    feedName.setText("");
            }
        });
    }

}
