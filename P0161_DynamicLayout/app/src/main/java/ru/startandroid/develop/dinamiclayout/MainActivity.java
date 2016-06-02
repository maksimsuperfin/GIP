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
    Button btnback;
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
    static final List<String>itemsSpa = new ArrayList<String>();
    static{itemsSpa.add("Spa");
        itemsSpa.add("Hears stile");
        itemsSpa.add("Mani and Pedicure");
        itemsSpa.add("Vocations");
    }
    static final ArrayList<String> itemsShop = new ArrayList<>();
    static {itemsShop.add("Men");
        itemsShop.add("Women");
        itemsShop.add("Kids");
        itemsShop.add("For Home");
    }
    static final ArrayList<String> itemsVacation = new ArrayList<>();
    static { itemsVacation.add("Local");
        itemsVacation.add("Abroad");
        itemsVacation.add("Attractions");
    }
    static final Map<String, List<String>> totalMenu = new HashMap<String, List<String>>();
    static {
        totalMenu.put("Food and Drink", itemsFood);
        totalMenu.put("Shop",itemsShop);
        totalMenu.put("Spa/Hear style",itemsSpa);
        totalMenu.put("Vacations",itemsVacation);
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
        btnback= (Button) findViewById(R.id.button_back);
        btnback.setText("Back");
        btn = (Button) findViewById(R.id.button);
        btn.setText("My Home");
        View.OnClickListener onklBtn=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.button_back:
                     gridView.setAdapter(arrayAdapter);

                }
            }
        };

    }
}

