package ru.krivorukie.foodcountpp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ArrayAdapter;

public class ActivityPage3 extends AppCompatActivity {
    String[] array = {"Odin", "Dva", "Tri"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_3);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recview);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.page_3, array);
        //recyclerView.setAdapter(adapter);

    }
}
