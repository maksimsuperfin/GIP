package ru.startandroid.develop.dinamiclayout;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends Activity {
    GridView gridView;
    Button btn;
    ArrayAdapter<String> arrayAdapter;
    boolean isFirstMenuOpened = false;
    static final List<String> itemsMain = new ArrayList<String>();
    static {
        itemsMain.add("Food and Drink");
        itemsMain.add("Shop");
        itemsMain.add("Spa/hear style");
        itemsMain.add("Vocations");
    }
    static final List<String> itemsFood = new ArrayList<String>();
    static {
        itemsFood.add("Bars");
        itemsFood.add("Restaurants");
        itemsFood.add("Cafe");
    }
    static final String[] itemsSpa = new String[]{
            "Spa", "Hears stile", "Mani and Pedicure", "Vocations"};
    static final String[] itemsShop = new String[]{
            "Men", "Women", "Kids", "For Home"};
    static final String[] itemsVacation = new String[]{
            "Local", "Abroad", "Attractions"};
    static final Map<String, List<String>> totalMenu = new HashMap<String, List<String>>();
    static {
        totalMenu.put("Food and Drink", itemsFood);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gridView = (GridView) findViewById(R.id.gridview);
        arrayAdapter = new ArrayAdapter<String>(this, R.layout.list_item, itemsMain);
        gridView.setAdapter(arrayAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @TargetApi(Build.VERSION_CODES.HONEYCOMB)
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                if (isFirstMenuOpened) {
                    Toast.makeText(getApplicationContext(),
                            ((TextView) v).getText(), Toast.LENGTH_SHORT).show();
                } else {
                    List<String> tmp = totalMenu.get(((TextView) v).getText());
                    arrayAdapter.clear();
                    arrayAdapter.addAll(tmp);
                    arrayAdapter.notifyDataSetChanged();
                    isFirstMenuOpened = true;
                }
            }
        });
        btn = (Button) findViewById(R.id.button);
        btn.setText("My Home");
    }
}


