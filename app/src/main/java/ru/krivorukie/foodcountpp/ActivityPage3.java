package ru.krivorukie.foodcountpp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;

import android.widget.ArrayAdapter;
import android.widget.Button;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ActivityPage3 extends AppCompatActivity {
    public List<UserJSONSerializer.Product> productList;

    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_3);


        //RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recview);
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.page_3, array);
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


        recyclerView = (RecyclerView) findViewById(R.id.RecyclerView31);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<Product> products = new ArrayList<Product>();
        products.add(new Product("Молоко"));
        products.add(new Product("Вафли"));
        products.add(new Product("Хлеб"));

        ListOfProducts listOfProducts = new ListOfProducts(products);

        recyclerView.setAdapter(listOfProducts);
    }

    class TaskGetList extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... params) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://gottagofood-backend.herokuapp.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            ProductService service = retrofit.create(ProductService.class);
            Call<List<UserJSONSerializer.Product>> call = service.fetchProducts();
            try {
                Response<List<UserJSONSerializer.Product>> productsResponse = call.execute();
                productList = productsResponse.body();

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

    }
}
