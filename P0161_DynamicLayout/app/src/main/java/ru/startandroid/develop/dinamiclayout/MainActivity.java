package ru.startandroid.develop.dinamiclayout;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;

public class MainActivity extends Activity {

        GridView gridView;
        Button btn;
        static final String[] items_main = new String[] {
                "Food and Drink", "Shop", "Spa/hear style", "Vocations"};
        static final String[] items_food = new String[] {
            "Bars", "Restaurants", "Cafe"};
        static final String[] items_spa = new String[] {
            "Spa", "Hears stile", "Mani and Pedicure", "Vocations"};
        static final String[] items_shop = new String[] {
            "Men", "Women", "Kids", "For Home"};
        static final String[] items_vacation = new String[] {
            "Local", "Abroad", "Attractions"};
        static final String[][] totalmenu = new String[][]{items_main,items_shop,items_food,items_spa, items_vacation};

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            gridView= (GridView) findViewById(R.id.gridview);
            for(int i=0;i<totalmenu.length;i++){
            ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this, R.layout.list_item, totalmenu[i]);
            gridView.setAdapter(arrayAdapter);
            }

            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View v,
                                        int position, long id) {
                   String[] tmp=getNextmenu(position);

                    ArrayAdapter<String> arad=new ArrayAdapter<String>(this,R.layout.list_item,tmp);
                    gridView.setAdapter(arad);
                     /* Toast.makeText(getApplicationContext(),
                            ((TextView) v).getText(), Toast.LENGTH_SHORT).show();*/
                }
            });

            btn= (Button) findViewById(R.id.button);
            btn.setText("My Home");


        }

    private String[] getNextmenu(int position) {
        String[]tmp=new String[]{};
        for(int j=position, i=0;i<totalmenu[position].length;i++){
            String tmpstr=totalmenu[position][i].toString();

            tmp[i]=tmpstr;
        }
        return tmp;
    }
}


