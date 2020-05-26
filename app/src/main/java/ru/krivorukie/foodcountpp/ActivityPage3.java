package ru.krivorukie.foodcountpp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

public class ActivityPage3 extends AppCompatActivity {
    String[] array = {"Odin", "Dva", "Tri"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_3);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recview);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.page_3, array);
        //recyclerView.setAdapter(adapter);

        Button button9 = (Button) findViewById(R.id.button9);
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ActivityPage3.this, ActivityPage0.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
        });
    }
}
