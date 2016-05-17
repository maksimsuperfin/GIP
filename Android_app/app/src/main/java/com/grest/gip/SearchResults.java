package com.grest.gip;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

/**
 * Created by Maksim.Superfin on 5/13/2016.
 */
public class SearchResults extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.grest.gip.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);

        AppCompatButton searchBtn = (AppCompatButton) findViewById(R.id.resultsBtn);
        assert searchBtn != null;
        searchBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                displayResults(v);
            }
        });
    }

    public void displayResults(View view) {
        Intent intent = new Intent(SearchResults.this, MapsActivity.class);
        EditText editText = (EditText) findViewById(R.id.editText);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
}
