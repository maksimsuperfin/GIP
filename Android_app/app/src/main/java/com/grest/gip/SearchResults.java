package com.grest.gip;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.grest.gip.com.grest.gip.dao.GrouponConstants;

/**
 * Created by Maksim.Superfin on 5/13/2016.
 */
public class SearchResults extends AppCompatActivity {

    public static final String CATEGORY_EXTRA_MESSAGE = "com.grest.gip.CATEGORY_MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, GrouponConstants.categories);
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(mMessageClickedHandler);
    }

    // Create a message handling object as an anonymous class.
    private AdapterView.OnItemClickListener mMessageClickedHandler = new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView parent, View v, int position, long id) {
            // Do something in response to the click
            Intent intent = new Intent(SearchResults.this, MapsActivity.class);
            String message = GrouponConstants.categories2Abbrevations.get((CharSequence) parent.getAdapter().getItem(position));
            intent.putExtra(CATEGORY_EXTRA_MESSAGE, message);
            startActivity(intent);
        }
    };
}
