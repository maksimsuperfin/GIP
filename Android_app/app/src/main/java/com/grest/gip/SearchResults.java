package com.grest.gip;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.grest.gip.com.grest.gip.dao.Categories;

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

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, Categories.categories);
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(mMessageClickedHandler);
    }

    // Create a message handling object as an anonymous class.
    private AdapterView.OnItemClickListener mMessageClickedHandler = new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView parent, View v, int position, long id) {
            // Do something in response to the click
            EditText editText = (EditText) findViewById(R.id.editText);
            editText.setText(Categories.categories2Abbrevations.get((CharSequence) parent.getAdapter().getItem(position)));
        }
    };

    public void displayResults(View view) {
        Intent intent = new Intent(SearchResults.this, MapsActivity.class);
        EditText editText = (EditText) findViewById(R.id.editText);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
}
