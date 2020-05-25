package ru.krivorukie.foodcountpp;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


public class Page0ActionPosts extends Page0Posts {
    static FragmentManager fragman;

    static void init_fragman(FragmentManager f){fragman=f;} // to call from outer space
    static void actionPostResolve(int i){
    if(i!=0) {
        }

    }
//TODO void [name] to replace the post with action_post
//TODO void [name] to replace action_post with post
    /*static void replace(boolean action, Fragment post, Fragment apost){

        FragmentTransaction replacing = fragman.beginTransaction();
        if(action){replacing.replace(post.getView().getId(),apost);


    }*/




    public static class ActionCafesBrowser extends Fragment {
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
            final View actionCafesBrowser=inflater.inflate(R.layout.fragment_actionpost_cafes_browser, container, false);
            return actionCafesBrowser;}}
    public static class ActionCarts extends Fragment{
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
            final View actionCarts=inflater.inflate(R.layout.fragment_actionpost_carts, container, false);
            return actionCarts;}}
    public static class ActionFridge extends Fragment{
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
            final View actionFridge=inflater.inflate(R.layout.fragment_actionpost_fridge, container, false);
            return actionFridge;}}
    public static class ActionPlanACAfe extends Fragment{
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
            final View actionPlanACafe=inflater.inflate(R.layout.fragment_actionpost_plan_a_cafe, container, false);
            return actionPlanACafe;}}
    public static class ActionProgress extends Fragment{
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
            final View actionProgress=inflater.inflate(R.layout.fragment_actionpost_progress, container, false);
            return actionProgress;}}
    public static class ActionTotalProducts extends Fragment{
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
            final View actionTotalProducts=inflater.inflate(R.layout.fragment_actionpost_total_products, container, false);
            return actionTotalProducts;}}

}
