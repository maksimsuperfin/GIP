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
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;
import com.grest.gip.com.grest.gip.dao.GrouponConstants;
import java.util.List;

/**
 * Created by Maksim.Superfin on 5/13/2016.
 */
public class SearchResults extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener{
    Toolbar toolbar;
    MenuItem countryIconMenuItem;
    String iconURI;
    private Intent intent;
    String country;
    public static final String CATEGORY_EXTRA_MESSAGE = "com.grest.gip.CATEGORY_MESSAGE";
    public static final String COUNTRY_EXTRA_MESSAGE = "com.grest.gip.COUNTRY_MESSAGE";

    GridView gridView;
    ArrayAdapter<String> arrayAdapter;
    boolean isMainMenuOpened = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        intent = getIntent();
        country = intent.getStringExtra(SearchResults.COUNTRY_EXTRA_MESSAGE);
        if (country == null) {
            country = "IL";
        }
        gridView = (GridView) findViewById(R.id.gridview);
        arrayAdapter = new ArrayAdapter<String>(this, R.layout.list_item, GrouponConstants.MENU_ITEMS_MAIN);
        gridView.setAdapter(arrayAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                if (isMainMenuOpened) {
                    List<String> tmp = GrouponConstants.TOTAL_MENU.get(((TextView) v).getText());
                    if (tmp != null) {
                        updateAdapterBy(tmp);
                        displayNavigationOnTolbar();
                    } else {
                        displayResults(parent, position);
                    }
                    isMainMenuOpened = false;
                } else {
                    displayResults(parent, position);
                }
            }
        });

        View.OnClickListener backBtnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isMainMenuOpened) {
                    updateAdapterBy(GrouponConstants.MENU_ITEMS_MAIN);
                    isMainMenuOpened = true;
                }
            }
        };
        toolbar.setNavigationOnClickListener(backBtnClickListener);
    }

    private int getIconID(String countryAbbr) {
        String uri = "@drawable/" + countryAbbr.toLowerCase();
        int imageResource = getResources().getIdentifier(uri, null, getPackageName());
        return imageResource;
    }

    private void displayNavigationOnTolbar() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.getNavigationIcon().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);

    }

    private void displayResults(AdapterView<?> parent, int position) {
        Intent intent = new Intent(SearchResults.this, MapsActivity.class);
        String message = GrouponConstants.categories2Abbrevations.get(
                (CharSequence) parent.getAdapter().getItem(position));
        intent.putExtra(CATEGORY_EXTRA_MESSAGE, message);
        intent.putExtra(COUNTRY_EXTRA_MESSAGE, country);
        startActivity(intent);
    }

    private void updateAdapterBy(List<String> items) {
        arrayAdapter = new ArrayAdapter<String>(this, R.layout.list_item, items);
        gridView.setAdapter(arrayAdapter);
    }

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
        if (!isMainMenuOpened) {
            displayNavigationOnTolbar();
        }
        toolbar.getOverflowIcon().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
        countryIconMenuItem = toolbar.getMenu().getItem(0);
        countryIconMenuItem.setIcon(getDrawable(getIconID(country)));
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
        String countryName = item.getTitle().toString();
        String countryCode = GrouponConstants.countries2Codes.get(countryName);
        if (countryCode != null) {
            country = countryCode;
            countryIconMenuItem.setIcon(getDrawable(getIconID(country)));
            Toast.makeText(this, countryName + " was selected",
                    Toast.LENGTH_LONG).show();
            return true;
        } else {
            Toast.makeText(this, "Unknown name of country [" + item.getTitle() + "] was selected",
                    Toast.LENGTH_LONG).show();
            return false;
        }
    }
}
