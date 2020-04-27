package ru.krivorukie.foodcountpp;

import android.app.Fragment;

/*Vocabulary:
*
* 0:decoy
* 1:
* 2:
* 3:
* 4:
* 5:
* 6:
* 7:
* 8:
* 9:
* 10:
*
*
*
* */


public class Posts {
    final int amount=6;
    short[] weight = new short[amount+1];
    int[] id = new int[amount+1];

    private void getNavRailSetting(){
        for(short i=amount;i>0;i--)weight[i-1]=(short)(i-1);// Kostili till [preferences]
        //TODO [preferences] get settings from memory
    }
    private void setNavRailSetting(){
        //TODO [preferences] load settings to memory
    }
    short getmax(boolean [] state){ // gets the next element to load in loadContent() (it's "code") (codes start from 1)
        getNavRailSetting();
        short index=0; short comp=0; for(short i=0;i<amount;i++)if(weight[i]>comp && state[i]){comp=weight[i];index=i;}
        if(comp==0) return -1;
        else return index;
    }
//void launch() to launch the fragment


    public static class CafesBrowser extends Fragment {}
    public static class Carts extends Fragment{}
    public static class Fridge extends Fragment{}
    public static class PlanACAfe extends Fragment{}
    public static class Progress extends Fragment{}
    public static class TotalProducts extends Fragment{}
}
