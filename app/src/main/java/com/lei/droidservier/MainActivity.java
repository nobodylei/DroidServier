package com.lei.droidservier;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private WebConfiguration webConfig;
    private SimpleHttpServier shs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        webConfig = new WebConfiguration();
        webConfig.setPost(8088);
        webConfig.setMaxParallels(50);
        shs = new SimpleHttpServier(webConfig);
        shs.startAsync();
    }

    @Override
    protected void onDestroy() {
        shs.stopAsync();
        super.onDestroy();
    }
}
