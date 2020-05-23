package ru.krivorukie.foodcountpp;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.animation.ObjectAnimator;
import android.widget.ListView;
import android.widget.ScrollView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import static android.view.View.generateViewId;

public class FragmentNavRail extends Fragment{
    //TODO make all (if possible) final variables global in this file

    int[] buttons_ids = new int [6]; //TODO get the amount of buttons (posts) when creating the single listener for all buttons
    FragmentManager fragMan = getFragmentManager();
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
    short getmax(boolean [] state){ // gets the next element to load in loadContent() (it's "code") (codes start from 1)
        getNavRailSetting();
        short index=0; short comp=0; for(short i=0;i<amount_of_posts;i++)if(settings[i]>comp && state[i]){comp=settings[i];index=i;}
        if(comp==0) return -1;
            else return index;
    }

    void loadContent(View navRailIn){
        getNavRailSetting();
        final ScrollView scroll = (ScrollView) getActivity().findViewById(R.id.scroll0);
        final LinearLayout navRail = (LinearLayout)navRailIn;

        boolean []unloaded = new boolean[amount_of_posts];
        for(int i=0;i<amount_of_posts;i++)unloaded[i]=true;
        short toLoad=getmax(unloaded);short counter=0;
        LinearLayout postsCont = scroll.findViewById(R.id.postsCont);
        View iterator = postsCont.getChildAt(1);
        coord=iterator.getTop();


Button buToLoad;
int id;

        while(toLoad!=-1 && counter<6){//
            buToLoad = new Button(getActivity());

            buToLoad.setText("№");
            buToLoad.setId(generateViewId());
            LinearLayout.LayoutParams buToLoadParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT,1);
            buToLoadParams.setMargins(0,-10*(int)navRail.getResources().getDisplayMetrics().density,0,-8*(int)navRail.getResources().getDisplayMetrics().density);
            buToLoad.setLayoutParams(buToLoadParams);
            buToLoad.setOnClickListener(new View.OnClickListener() {
                @Override   //TODO may be usefull to replace this with toggle buttons and onCheckedChangedListener()
                public void onClick(View v) { //navRail.removeView(v);
                    ScrollView scroll = (ScrollView) getActivity().findViewById(R.id.scroll0);
                    //scroll.smoothScrollTo(0,300*2);
                    ((Button)v).setText(Integer.toString(scroll.getScrollY()));

                    scroll.smoothScrollBy(0,0); //TODO this stops any scroll movement, but eats the time from animation as a delay, it looks like sh, +1 to epilepsy, but it works tho
                    ObjectAnimator objectAnimator = ObjectAnimator.ofInt(scroll, "scrollY", scroll.getScrollY(), coord*a).setDuration(500);
                    objectAnimator.start();a++;
                    //TODO replace the fragment and toggle the button (action bar buttons actions should be changed too)
                } // TODO: scroll the scroll and change the post
            });
            switch(toLoad){//TODO generate id's for buttons from outer method for all the buttons possible at once, then send these ids here amd use em
                    case 0:
                        id=generateViewId();
                        buToLoad.setId(id);
                        buToLoad.setText("admin "+Integer.toString(id));
                    break;
                    case 1:
                        id=generateViewId();
                        buToLoad.setId(id);
                        buToLoad.setText("1 "+Integer.toString(id));
                    break;
                    case 2:
                        id=generateViewId();
                        buToLoad.setId(id);
                        buToLoad.setText("2 "+Integer.toString(id));
                    break;
                    case 3:
                        id=generateViewId();
                        buToLoad.setId(id);
                        buToLoad.setText("3 "+Integer.toString(id));
                    break;
                    case 4:
                        id=generateViewId();
                        buToLoad.setId(id);
                        buToLoad.setText("4 "+Integer.toString(id));
                    break;
                    case 5:
                        id=generateViewId();
                        buToLoad.setId(id);
                        buToLoad.setText("5 "+Integer.toString(id));
                    break;
                    case 6:
                        id=generateViewId();
                        buToLoad.setId(id);
                        buToLoad.setText("6 "+Integer.toString(id));
                    break;
                    case 7:
                        id=generateViewId();
                        buToLoad.setId(id);
                        buToLoad.setText("7 "+Integer.toString(id));
                    break;
                    case 8:
                        id=generateViewId();
                        buToLoad.setId(id);
                        buToLoad.setText("8 "+Integer.toString(id));
                    break;
                    case 9:
                        id=generateViewId();
                        buToLoad.setId(id);
                        buToLoad.setText("9 "+Integer.toString(id));
                    break;
                default:
                    id=generateViewId();
                    buToLoad.setId(id);
                    buToLoad.setText("default "+Integer.toString(id));break;
            }
            navRail.addView(buToLoad);

            unloaded[toLoad]=false;
            toLoad = getmax(unloaded);
            counter++; }
    }
    private int a=0; static int coord;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){


        final View view=inflater.inflate(R.layout.fragment_navrail, container, false);

        LinearLayout navRail =(LinearLayout)  view.findViewById(R.id.navRail);


        //static int coord = coord1[1];


       loadContent(navRail);


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