package com.grest.gip;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import com.grest.gip.com.grest.gip.dao.GrouponConstants;
import java.util.Arrays;

/**
 * Created by User on 10.06.2016.
 */
public class ToolbarTest extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
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
        return true;
    }

    /*@Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.countries, menu);
    }*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.chooseCountry:
                //Toast.makeText(this, "Select country was clicked", Toast.LENGTH_LONG).show();
                showCountries(toolbar);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        if (GrouponConstants.countries2Codes.get(item.getTitle()) != null) {
            Toast.makeText(this, item.getTitle(), Toast.LENGTH_LONG).show();
            return true;
        } else {
            Toast.makeText(this, "Strange name of country was selected", Toast.LENGTH_LONG).show();
            return false;
        }
    }
}
