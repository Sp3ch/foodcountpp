package ru.krivorukie.foodcountpp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityPage1 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_1);
        Button button7 = (Button) findViewById(R.id.button9);
        Button button6 = (Button) findViewById(R.id.button6);
        button7.setOnClickListener(new View.OnClickListener() { // if generation is automatic
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ActivityPage1.this, ActivityPage3.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
        });
        button6.setOnClickListener(new View.OnClickListener() { // if generation is custom
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ActivityPage1.this, ActivityPage2.class);
                //i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
        });
    }
}
