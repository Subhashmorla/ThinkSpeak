package com.example.root.thinkspeak;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class GraphActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);
        Bundle extra=getIntent().getExtras();
        String CHANNEL_ID=extra.getString("channel id");
        String FIELD_ID=extra.get("field id").toString();

        String html ="<iframe width=\"450\" height=\"250\" style=\"border: 1px solid #cccccc;\" src=\"http://thingspeak.com/channels/"+CHANNEL_ID+"/charts/"+FIELD_ID+"?dynamic=true\"></iframe>"
                ;
        WebView webview= (WebView) findViewById(R.id.web_view);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.setInitialScale(210);
        webview.loadData(html, "text/html", null);    }
}
