package ru.krivorukie.foodcountpp;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class UserJSONSerializer {
    static Gson serializer = new Gson();
    static GsonBuilder builder = new GsonBuilder();
    static Gson deserializer = builder.create();

    public static class TestUser {
        public int amount;
        public int [] postsSettings=new int[amount];
        TestUser(){}
    };

    public static class Product {

            private Integer productID;
            private String name;
            private String size;
            private double energy_value;
            private double proteins;
            private double fats;
            private double carbohydrates;
            private double price;

            public Integer getProductID() {
                return productID;
            }

            public void setProductID(Integer productID) {
                this.productID = productID;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getSize() {
                return size;
            }

            public void setSize(String size) {
                this.size = size;
            }

            public double getEnergy_value() {
                return energy_value;
            }

            public void setEnergy_value(Double energy_value) {
                this.energy_value = energy_value;
            }

            public double getProteins() {
                return proteins;
            }

            public void setProteins(Double proteins) {
                this.proteins = proteins;
            }

            public double getFats() {
                return fats;
            }

            public void setFats(Double fats) {
                this.fats = fats;
            }

            public double getCarbohydrates() {
                return carbohydrates;
            }

            public void setCarbohydrates(Double carbohydrates) {
                this.carbohydrates = carbohydrates;
            }

            public double getPrice() {
                return price;
            }

            public void setPrice(double price) {
                this.price = price;
            }
    };

    static TestUser deserialize (String data) {
        return deserializer.fromJson(data,TestUser.class);
    }

    static String serialize(TestUser data) {
        return serializer.toJson(data,TestUser.class);
    }

    static Product deserializeProduct(String data) {
        return deserializer.fromJson(data, Product.class);
    }

    static String serializeProduct(Product data) {
        return serializer.toJson(data, Product.class);
    }
}
// server_adress"/json"