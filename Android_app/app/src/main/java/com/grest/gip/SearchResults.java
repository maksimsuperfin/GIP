package com.grest.gip;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.grest.gip.com.grest.gip.dao.GrouponConstants;

/**
 * Created by Maksim.Superfin on 5/13/2016.
 */
public class SearchResults extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener{
    Toolbar toolbar;
    private Intent intent;
    String country;
    public static final String CATEGORY_EXTRA_MESSAGE = "com.grest.gip.CATEGORY_MESSAGE";
    public static final String COUNTRY_EXTRA_MESSAGE = "com.grest.gip.COUNTRY_MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, GrouponConstants.categories);
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(mMessageClickedHandler);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        intent = getIntent();
        country = intent.getStringExtra(SearchResults.COUNTRY_EXTRA_MESSAGE);
        if (country == null) {
            country = "IL";
        }
    }

    // Create a message handling object as an anonymous class.
    private AdapterView.OnItemClickListener mMessageClickedHandler = new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView parent, View v, int position, long id) {
            // Do something in response to the click
            Intent intent = new Intent(SearchResults.this, MapsActivity.class);
            String message = GrouponConstants.categories2Abbrevations.get(
                    (CharSequence) parent.getAdapter().getItem(position));
            intent.putExtra(CATEGORY_EXTRA_MESSAGE, message);
            intent.putExtra(COUNTRY_EXTRA_MESSAGE, country);
            startActivity(intent);
        }
    };

    public void showCountries(View v) {
        PopupMenu popup = new PopupMenu(this, v, Gravity.RIGHT);
        for(String country: GrouponConstants.sortedCountries) {
            popup.getMenu().add(country);
        }
        // This activity implements OnMenuItemClickListener
        popup.setOnMenuItemClickListener(this);
        popup.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.app_menu, menu);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setSubtitleTextColor(Color.WHITE);
        toolbar.getOverflowIcon().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.chooseCountry:
                showCountries(toolbar);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        String countryCode = GrouponConstants.countries2Codes.get(item.getTitle());
        if (countryCode != null) {
            country = countryCode;
            return true;
        } else {
            Toast.makeText(this, "Unknown name of country [" + item.getTitle() + "] was selected",
                    Toast.LENGTH_LONG).show();
            return false;
        }
    }
}
