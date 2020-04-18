package ru.krivorukie.foodcountpp;

import android.app.Fragment;
//import android.content.SharedPreferences;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;
import android.animation.Animator;
import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.ScrollView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import static android.view.View.generateViewId;

public class FragmentNavRail extends Fragment{

    final Context context = getActivity();
    //SharedPreferences memory;
    public static FragmentNavRail newInstance() {
        return new FragmentNavRail();
    }
    private final short amount_of_posts=10; private short[] settings = new short[amount_of_posts];

    private void getNavRailSetting(){
        for(short i=amount_of_posts;i>0;i--)settings[i-1]=(short)(i-1);// Kostili
        //TODO get settings from memory
    }
    private void setNavRailSetting(){
        //TODO load settings to memory
    }
    short getmax(boolean [] state){
        short index=0; short comp=0; for(short i=0;i<amount_of_posts;i++)if(settings[i]>comp && state[i]){comp=settings[i];index=i;}
        if(comp==0) return -1;
            else return index;
    }

    void loadContent(View navRailIn){
        getNavRailSetting();
        final LinearLayout navRail = (LinearLayout)navRailIn;

        boolean []unloaded = new boolean[amount_of_posts];
        short toLoad=getmax(unloaded);short counter=0;

        Button b1 = new Button(getActivity());
        b1.setText("456");
        b1.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT, 1));
        b1.setId(generateViewId());
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { navRail.removeView(v);
            } // TODO: scroll the scroll and change the post
        });
        navRail.addView(b1);

        while(counter<1){

            switch(toLoad){
                case 1: break;
                default:
            break;
            }counter++;
        }
/*
        while( counter<1){//toLoad!=-1 &&
            FragmentTransaction fragTrans = fragMan.beginTransaction();
            switch(toLoad){
                case 0:/*
                                          //pasted code of dynamical button placement

                    Button b = new Button(getActivity());
                    b.setText("№");
                    b.setLayoutParams(new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT, 1));
                    b.setId(generateViewId());
                    b.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) { //navRail.removeView(v);
                        } // TODO: scroll the scroll and change the post
                    });
                    navRail.addView(b);                                                              // end of button placement



                    //FragmentPostTest postTest = new FragmentPostTest();
                    //fragTrans.add(R.id.navRail, postTest);
                    break;/*
                case 1:
                    FragmentPostTest postTest1 = new FragmentPostTest();
                    fragTrans.add(R.id.navRail, postTest1);
                    break;
                case 2:
                    FragmentPostTest postTest2 = new FragmentPostTest();
                    fragTrans.add(R.id.navRail, postTest2);
                    break;
                case 3:
                    FragmentPostTest postTest3 = new FragmentPostTest();
                    fragTrans.add(R.id.navRail, postTest3);
                    break;
                case 4:
                    FragmentPostTest postTest4 = new FragmentPostTest();
                    fragTrans.add(R.id.navRail, postTest4);
                    break;
                case 5: // last in testing
                    FragmentPostTest postTest5 = new FragmentPostTest();
                    fragTrans.add(R.id.navRail, postTest5);
                    break;
                case 6:
                    FragmentPostTest postTest6 = new FragmentPostTest();
                    fragTrans.add(R.id.navRail, postTest6);
                    break;
                case 7:
                    FragmentPostTest postTest7 = new FragmentPostTest();
                    fragTrans.add(R.id.navRail, postTest7);
                    break;
                case 8:
                    FragmentPostTest postTest8 = new FragmentPostTest();
                    fragTrans.add(R.id.navRail, postTest8);
                    break;
                case 9:
                    FragmentPostTest postTest9 = new FragmentPostTest();
                    fragTrans.add(R.id.navRail, postTest9);
                    break;

                default:
                    //getActivity();

                    /*
                    Button b2 = new Button(getActivity());
                    b2.setText("789");
                    b2.setLayoutParams(new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT, 1));
                    b2.setId(generateViewId());
                    b2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) { //navRail.removeView(v);
                        } // TODO: scroll the scroll and change the post
                    });
                    navRail.addView(b2);
                    break;
            }
            FragmentPostTest postTest = new FragmentPostTest();
            fragTrans.add(R.id.navRail, postTest);
            fragTrans.commit();

            unloaded[toLoad]=false;
            toLoad = getmax(unloaded);
            counter++; }*/
    }

    private int a=0;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){


        final View view=inflater.inflate(R.layout.fragment_nav_rail, container, false);

        LinearLayout navRail =(LinearLayout)  view.findViewById(R.id.navRail);

        Button b1 = new Button(getActivity());
        b1.setText("№");
        b1.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT, 1));
        b1.setId(generateViewId());
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //navRail.removeView(v);
                ScrollView scroll = (ScrollView) getActivity().findViewById(R.id.scroll0);
                //scroll.smoothScrollTo(0,300*2);
                ((Button)v).setText(Integer.toString(scroll.getScrollY()));

                ObjectAnimator objectAnimator = ObjectAnimator.ofInt(scroll, "scrollY", scroll.getScrollY(), 100*a+600).setDuration(200);
                objectAnimator.start();a++;
                //TODO catch the position variable from bundle
                //String strtext = getArguments().getString("position");
            } // TODO: scroll the scroll and change the post
        });
        navRail.addView(b1);
        loadContent(navRail);

        final FragmentManager fragMan = getFragmentManager();
        FragmentTransaction fragTrans = fragMan.beginTransaction();
        FragmentPostTest postTest = new FragmentPostTest();
        fragTrans.add(R.id.postsCont, postTest);
        fragTrans.commit();



    return view;}
}
/*

    Animator scaleDown = ObjectAnimator.ofPropertyValuesHolder((Object)null);   // animation of appearance
        scaleDown.setDuration(10000);                                                 // pasted code
                scaleDown.setInterpolator(new OvershootInterpolator());

                Animator scaleUp = ObjectAnimator.ofPropertyValuesHolder((Object)null);     // cut down     , PropertyValuesHolder.ofFloat("scaleX", 0, 1), PropertyValuesHolder.ofFloat("scaleY", 0, 1)
                scaleUp.setDuration(10000);
                scaleUp.setStartDelay(10000);
                scaleUp.setInterpolator(new OvershootInterpolator());

                LayoutTransition itemLayoutTransition = new LayoutTransition();
                itemLayoutTransition.setAnimator(LayoutTransition.APPEARING, scaleUp);
                itemLayoutTransition.setAnimator(LayoutTransition.DISAPPEARING, scaleDown);

                ViewGroup av = (ViewGroup)findViewById(R.id.navRail);
                av.setLayoutTransition(itemLayoutTransition);                              //end of animation of appearance


        int USERID=1;int countID=0; final LinearLayout navRail = findViewById(R.id.navRail);    //pasted code of dynamical button placement
        for(int i=0;i<3;i++){
            Button b = new Button(getApplicationContext());
            b.setText("№" + Integer.toString(countID + 1));
            b.setLayoutParams(
                    new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT)
            );
            b.setId(USERID + countID);
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    navRail.removeView(v);
                }
            });
            navRail.addView(b);
            countID++;
        }                                                                                       // end of button placement
 */