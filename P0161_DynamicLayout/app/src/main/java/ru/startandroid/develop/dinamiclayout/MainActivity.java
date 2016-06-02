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
                System.out.println("getText() ::: " + ((TextView) v).getText());
                if (isMainMenuOpened) {
                    List<String> tmp = MenuConstants.TOTAL_MENU.get(((TextView) v).getText());
                    updateAdapterBy(tmp);
                    isMainMenuOpened = false;
                } else {
                    Toast.makeText(getApplicationContext(),
                            ((TextView) v).getText(), Toast.LENGTH_SHORT).show();
                }
                System.out.println("gridView.setOnItemClickListener ::: " + MenuConstants.ITEMS_MAIN);
            }
        });
        btnBack = (Button) findViewById(R.id.button_back);
        btnBack.setText("Back");
        View.OnClickListener backBtnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("back is clicked");
                if (!isMainMenuOpened) {
                    System.out.println("Adapter is updated ::: " + MenuConstants.ITEMS_MAIN);
                    updateAdapterBy(MenuConstants.ITEMS_MAIN);
                    isMainMenuOpened = true;
                }
                System.out.println("onClick ::: " + MenuConstants.ITEMS_MAIN);
            }
        };
        btnBack.setOnClickListener(backBtnClickListener);
        btn = (Button) findViewById(R.id.button);
        btn.setText("My Home");
        System.out.println("onCreate ::: " + MenuConstants.ITEMS_MAIN);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void updateAdapterBy(List<String> items) {
        System.out.println("before updateAdapterBy ::: " + items);
        arrayAdapter.clear();
        arrayAdapter.notifyDataSetChanged();
        arrayAdapter.addAll(items);
        arrayAdapter.notifyDataSetChanged();
        System.out.println("updateAdapterBy ::: " + MenuConstants.ITEMS_MAIN);
    }

    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("onStart ::: " + MenuConstants.ITEMS_MAIN);
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("onResume ::: " + MenuConstants.ITEMS_MAIN);
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("onPause ::: " + MenuConstants.ITEMS_MAIN);
    }

    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("onStop ::: " + MenuConstants.ITEMS_MAIN);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("onDestroy ::: " + MenuConstants.ITEMS_MAIN);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        System.out.println("onRestart ::: " + MenuConstants.ITEMS_MAIN);
    }
}

