package ru.krivorukie.foodcountpp;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ProductService {
    @GET("/gen/listdev")
    Call<List<UserJSONSerializer.Product>> fetchProducts();
}
