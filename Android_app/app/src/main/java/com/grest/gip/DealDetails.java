package com.grest.gip;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;


/**
 * Created by Maksim.Superfin on 6/1/2016.
 */
public class DealDetails extends AppCompatActivity {

    String category;
    String id;
    String offset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);
        Intent intent = getIntent();
        category = intent.getStringExtra(SearchResults.CATEGORY_EXTRA_MESSAGE);
        id = intent.getStringExtra(MapsActivity.DEAL_ID_EXTRA_MESSAGE);
        offset = intent.getStringExtra(MapsActivity.OFFSET_EXTRA_MESSAGE);
        System.out.println("onCreate::: " + category + " " + id);
        setTitle("Deal details " + id);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(DealDetails.this, MapsActivity.class);
                intent.putExtra(SearchResults.CATEGORY_EXTRA_MESSAGE, category);
                intent.putExtra(MapsActivity.OFFSET_EXTRA_MESSAGE, offset);
                startActivity(intent);
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

