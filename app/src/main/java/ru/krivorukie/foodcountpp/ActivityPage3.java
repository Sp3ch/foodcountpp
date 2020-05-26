package ru.krivorukie.foodcountpp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ActivityPage3 extends AppCompatActivity {
    String[] array = {"Odin", "Dva", "Tri"};
    public List<UserJSONSerializer.Product> productList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_3);



        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recview);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.page_3, array);
        //recyclerView.setAdapter(adapter);

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