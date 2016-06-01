package com.grest.gip;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Maksim.Superfin on 6/1/2016.
 */
public class DealDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);
        Intent intent = getIntent();
        setTitle("Deal details " + intent.getStringExtra(SearchResults.EXTRA_MESSAGE));
    }
}
