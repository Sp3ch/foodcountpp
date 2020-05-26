package ru.krivorukie.foodcountpp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class ActivityPage3 extends AppCompatActivity {

    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_3);

        recyclerView = (RecyclerView) findViewById(R.id.RecyclerView31);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<Product> products = new ArrayList<Product>();
        products.add(new Product("Молоко"));
        products.add(new Product("Вафли"));
        products.add(new Product("Хлеб"));

        ListOfProducts listOfProducts = new ListOfProducts(products);

        recyclerView.setAdapter(listOfProducts);
    }
}
