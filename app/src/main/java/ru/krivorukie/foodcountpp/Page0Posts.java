package ru.krivorukie.foodcountpp;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

//import retrofit2.Call;
//import retrofit2.Response;
//import retrofit2.Retrofit;
//import retrofit2.converter.gson.GsonConverterFactory;
//import retrofit2.http.GET;
//import retrofit2.http.Query;

import android.app.Fragment; // TODO adapt for Support library
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/*Vocabulary:
* [NOTE: correcting this, please, follow the alphabet order in lists of things]
* IDs HashMap keys available match fragment's class name
* fragments (posts) available:
* --CafesBrowser
* --Carts
* --Decoy
* --Fridge
* --PlanACafe
* --Progress
* --TotalProducts
*
* weights will go from 0 to 30 in order from top to bottom, "unlisted"weight is -1
*
*
* */

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Page0Posts {
     private final short amount=6;  private short scope_size=7;
    private  String [] posts={"Decoy","CafesBrowser","Carts","Fridge","PlanACafe","Progress","TotalProducts"}; // names of posts for son=me iterators to use in cycle
    private HashMap<Short,String> weight=new HashMap<>();  // position in the scope
    private HashMap<String,Integer> id = new HashMap<>(); // id's for taking em as elements manipulating with layouts
    private short[] weightVals = new short [amount+1];
    private String[] weightKeys = new String[amount+1];
    short weightByKey(String key){for(short i=0;i<amount+1;i++)if(key==weightKeys[i])return weightVals[i]; return -1;}
    String weightByValue(short val){for(short i=0;i<amount+1;i++)if(val==weightVals[i])return weightKeys[i]; return null;}
    short  posByKey(String key){for(short i=0;i<amount+1;i++)if(key==weightKeys[i])return i; return 0;}

    public Page0Posts(){ // initialising some things (kinda constructor for class of no exemplar)
        for(short i=0;i<amount;i++)weightVals[i]=-1;
        Short a=50;short i=0;
        for(String ii:posts){
            weightVals[i]=a;
            weightKeys[i]=ii;
            //id.put(i,a);
            //weight.put(a,ii);
            a++; i++;
        }

    }
    /*public interface UserService {
        @GET("json/")
        Call<User> fetchUser(@Query("amount") int amount, @Query("postsSettings") int[] weights);
    }
    User userFromServer; int amountjson; int[] weightsjson;
    class MyAsyncTask extends AsyncTask<String, Void, Integer> {

        @Override
        protected Integer doInBackground(String... params) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://gottagofood-backend.herokuapp.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            UserService service = retrofit.create(UserService.class);
            Call<User> call = service.fetchUser(amountjson, weightsjson);
            try {
                Response<User> userResponse = call.execute();
                userFromServer = userResponse.body();
                //Log.d("MainActivity", userFromServer.fullName);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return 0;
        }

        @Override
        protected void onPostExecute(Integer result) {
            super.onPostExecute(result);
            Log.d("glhf", Arrays.toString(userFromServer.weights));
        }
    }*/
    /*public interface GerritAPI {

        @GET("json/")
        Call<List<User>> loadChanges();//@Query("q") String status
    }*/
    public static class User {
        public int amount;
        public int[] weights;
    }

    /*private void getOnlineNavRailSettings(){

        Log.d("glhf", "12345");
        new MyAsyncTask().execute("");
    }*/


    private void getNavRailSetting(){// gets info about weight[]
        //for(short i=amount;i>0;i--) weights[i-1]=(short)(i-1);// Kostili till [preferences]
        //TODO [preferences] get settings from memory
        //getOnlineNavRailSettings();

        weightVals[posByKey("Progress")]=0;
        weightVals[posByKey("TotalProducts")]=1;
        weightVals[posByKey("Carts")]=2;



    }
    private static void setNavRailSetting(){
        //TODO [preferences] load settings to memory
    }

    public <T extends Fragment> void launch(Activity activity, int container, T fragment){//TODO find activity using LinearLayout
        FragmentManager fragMan = activity.getFragmentManager();
        FragmentTransaction transaction=fragMan.beginTransaction();
        transaction.add(container, fragment);
        transaction.commit();
    }
    public void unlaunch(){
//TODO remove the fragment from the screen
    }
    public void relaunch(){
//TODO replace fragment
}
    public void buildScroll(Activity activity, int scroll){
        getNavRailSetting();
        String curent;Fragment frag;short count=0;
        for(Short i=0;i<100;i++){
            //curent=weight.get(i);
            curent=weightByValue(i);
            if(curent!=null) {
                frag=new_by_name(curent);
                if (frag!=null)
                {
                    launch(activity,scroll,frag);
                    //TODO get an ID of
                    //id.put(curent,number);
                }
                else
                {
                    launch(activity,scroll,new Decoy());
                    //TODO get an ID of
                    //id.put(curent,number);
                }
                count++;}
            if(count==scope_size) break;
        }
    }

    private Fragment new_by_name(String name){
        Fragment fragment;
        switch (name){
            case "CafesBrowser":fragment = new CafesBrowser();break;
            case "Carts":fragment = new Carts();break;
            case "Fridge":fragment = new Fridge();break;
            case "PlanACafe":fragment = new PlanACAfe();break;
            case "Progress":fragment  = new Progress();break;
            case "TotalProducts":fragment = new TotalProducts();break;
            case "Decoy":fragment=new Decoy(); break;
            default: fragment=null; break;
        }
        return fragment;
    }

    public static class Decoy extends Fragment {
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View decoy = inflater.inflate(R.layout.fragment_post_decoy, container, false);
            Button report = (Button)decoy.findViewById(R.id.report_a_bug);
            report.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse("https://www.facebook.com/sp3ch/"));
                    startActivity(i);
                }
            });
            return decoy; }
    }


    public static class CafesBrowser extends Fragment {
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
            final View cafesBrowser=inflater.inflate(R.layout.fragment_post_cafes_browser, container, false);
            return cafesBrowser;}}


    public static class Carts extends Fragment{
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
            final View carts=inflater.inflate(R.layout.fragment_post_carts, container, false);
            return carts;}}


    public static class Fridge extends Fragment{
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
            final View fridge=inflater.inflate(R.layout.fragment_post_fridge, container, false);
            return fridge;}}


    public static class PlanACAfe extends Fragment{
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
            final View planACafe=inflater.inflate(R.layout.fragment_post_plan_a_cafe, container, false);
            return planACafe;}}


    public static class Progress extends Fragment{
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
            final View progress=inflater.inflate(R.layout.fragment_post_progress, container, false);
            return progress;}
    }


    public static class TotalProducts extends Fragment{
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
            final View totalProducts=inflater.inflate(R.layout.fragment_post_total_products, container, false);
            LinearLayout post =totalProducts.findViewById(R.id.tp_main);
            post.setOnClickListener(new View.OnClickListener(){
                Intent i;
                @Override
                public void onClick(View v){
                    i=new Intent(totalProducts.getContext(), ActivityPageInDevelopment.class);
                    startActivity(i);
                }
            });
            return totalProducts;}}

}
/*
* i1=new Intent(ActivityPage0.this, ActivityPage4.class);
                        startActivity(i1);
* */