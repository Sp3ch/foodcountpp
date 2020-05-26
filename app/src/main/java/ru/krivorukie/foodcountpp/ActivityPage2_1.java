package ru.krivorukie.foodcountpp;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ActivityPage2_1 extends Activity {

    String[] list_of_products = { "Мясо", "Рыба", "Морковь" };

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_2_1);

        ListView list2_11 = (ListView) findViewById(R.id.list2_11);
        ArrayAdapter <String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list_of_products);
        list2_11.setAdapter(adapter);
    }
}
