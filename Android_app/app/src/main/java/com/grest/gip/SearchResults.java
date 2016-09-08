package com.grest.gip;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;
import com.grest.gip.com.grest.gip.dao.GrouponConstants;
import com.grest.gip.databinding.ActivitySearchResultsBinding;
import java.util.List;

/**
 * Created by Maksim.Superfin on 5/13/2016.
 */
public class SearchResults extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener{
    MenuItem countryIconMenuItem;
    private Intent intent;
    String country;
    public static final String CATEGORY_EXTRA_MESSAGE = "com.grest.gip.CATEGORY_MESSAGE";
    public static final String COUNTRY_EXTRA_MESSAGE = "com.grest.gip.COUNTRY_MESSAGE";

    ArrayAdapter<String> arrayAdapter;
    boolean isMainMenuOpened = true;
    ActivitySearchResultsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search_results);

        setSupportActionBar(binding.toolbar);
        intent = getIntent();
        country = intent.getStringExtra(SearchResults.COUNTRY_EXTRA_MESSAGE);
        if (country == null) {
            country = "IL";
        }
        arrayAdapter = new ArrayAdapter<String>(this, R.layout.list_item, GrouponConstants.MENU_ITEMS_MAIN);
        binding.gridview.setAdapter(arrayAdapter);

        binding.gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
                    getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                    getSupportActionBar().setDisplayShowHomeEnabled(false);
                }
            }
        };
        binding.toolbar.setNavigationOnClickListener(backBtnClickListener);
    }

    private int getIconID(String countryAbbr) {
        String uri = "@drawable/" + countryAbbr.toLowerCase();
        int imageResource = getResources().getIdentifier(uri, null, getPackageName());
        return imageResource;
    }

    private void displayNavigationOnTolbar() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        binding.toolbar.getNavigationIcon().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);

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
        binding.gridview.setAdapter(arrayAdapter);
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
        binding.toolbar.setTitleTextColor(Color.WHITE);
        binding.toolbar.setSubtitleTextColor(Color.WHITE);
        if (!isMainMenuOpened) {
            displayNavigationOnTolbar();
        }
        binding.toolbar.getOverflowIcon().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
        countryIconMenuItem = binding.toolbar.getMenu().getItem(0);
        countryIconMenuItem.setIcon(
                ResourcesCompat.getDrawable(getResources(), getIconID(country), null));
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.chooseCountry:
                showCountries(binding.toolbar);
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
            countryIconMenuItem.setIcon(
                    ResourcesCompat.getDrawable(getResources(), getIconID(country), null));
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
