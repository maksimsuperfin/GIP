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

import java.util.List;

public class MainActivity extends Activity {
    GridView gridView;
    Button btn;
    Button btnBack;
    ArrayAdapter<String> arrayAdapter;
    boolean isMainMenuOpened = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gridView = (GridView) findViewById(R.id.gridview);
        arrayAdapter = new ArrayAdapter<String>(this, R.layout.list_item, MenuConstants.ITEMS_MAIN);
        gridView.setAdapter(arrayAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @TargetApi(Build.VERSION_CODES.HONEYCOMB)
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                if (isMainMenuOpened) {
                    List<String> tmp = MenuConstants.TOTAL_MENU.get(((TextView) v).getText());
                    updateAdapterBy(tmp);
                    isMainMenuOpened = false;
                } else {
                    Toast.makeText(getApplicationContext(),
                            ((TextView) v).getText(), Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnBack = (Button) findViewById(R.id.button_back);
        btnBack.setText("Back");
        View.OnClickListener backBtnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isMainMenuOpened) {
                    updateAdapterBy(MenuConstants.ITEMS_MAIN);
                    isMainMenuOpened = true;
                }
            }
        };
        btnBack.setOnClickListener(backBtnClickListener);
        btn = (Button) findViewById(R.id.button);
        btn.setText("My Home");
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void updateAdapterBy(List<String> items) {
        arrayAdapter = new ArrayAdapter<String>(this, R.layout.list_item, items);
        gridView.setAdapter(arrayAdapter);
    }
}

