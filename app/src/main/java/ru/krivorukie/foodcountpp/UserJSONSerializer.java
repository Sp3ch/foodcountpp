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
    static TestUser deserialize (String data){
        return deserializer.fromJson(data,TestUser.class);
    }
    static String serialize(TestUser data){
        return serializer.toJson(data,TestUser.class);
    }

}
// server_adress"/json"