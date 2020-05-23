package ru.krivorukie.foodcountpp;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
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

public class FragmentNavRail extends Fragment {
    boolean navRailState = false;
    int amountOfPostsVisible = 6;
    //TODO make all (if possible) final variables global in this file

    int[] buttons_ids = new int[6]; //TODO get the amount of buttons (posts) when creating the single listener for all buttons
    FragmentManager fragMan = getFragmentManager();
    final Context context = getActivity();

    //SharedPreferences memory;
    public static FragmentNavRail newInstance() {
        return new FragmentNavRail();
    }

    private final short amount_of_posts = 10;
    private short[] settings = new short[amount_of_posts];

    private void getNavRailSetting() {
        for (short i = amount_of_posts; i > 0; i--) settings[i - 1] = (short) (i - 1);// Kostili
        //TODO get settings from memory
    }

    private void setNavRailSetting() {
        //TODO load settings to memory
    }

    short getmax(boolean[] state) { // gets the next element to load in loadContent() (it's "code") (codes start from 1)
        getNavRailSetting();
        short index = 0;
        short comp = 0;
        for (short i = 0; i < amount_of_posts; i++)
            if (settings[i] > comp && state[i]) {
                comp = settings[i];
                index = i;
            }
        if (comp == 0) return -1;
        else return index;
    }


    short counter = 0;
    View iterator;

    void loadContent(View navRailIn) {
        getNavRailSetting();
        final LinearLayout navRail = (LinearLayout) navRailIn;

        boolean[] unloaded = new boolean[amount_of_posts];
        for (int i = 0; i < amount_of_posts; i++) unloaded[i] = true;
        short toLoad = getmax(unloaded);

        final ScrollView scroll = (ScrollView) getActivity().findViewById(R.id.scroll0);
        final LinearLayout postsCont = scroll.findViewById(R.id.postsCont);
        //TODO this realization needs to have the raw of posts same in first 6 posts as buttons of navRail, but it doesn't have to (fix it)

        Button buToLoad;
        int id;

        while (toLoad != -1 && counter < amountOfPostsVisible) {//

            iterator = postsCont.getChildAt(counter);


            buToLoad = new Button(getActivity());

            buToLoad.setText("№");
            buToLoad.setId(generateViewId());
            LinearLayout.LayoutParams buToLoadParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1);
            buToLoadParams.setMargins(0, -10 * (int) navRail.getResources().getDisplayMetrics().density, 0, -8 * (int) navRail.getResources().getDisplayMetrics().density);
            buToLoad.setLayoutParams(buToLoadParams);
            //TODO may be useful to replace buttons with toggle buttons and onCheckedChangedListener()
            //TODO [[ scroll.smoothScrollBy(0,0); ]] this stops any scroll movement, but eats the time from animation as a delay, it looks like sh, +1 to epilepsy, but it works tho
            // TODO: scroll the scroll and change the post
            //TODO replace the fragment and toggle the button (action bar buttons actions should be changed too)
            buToLoad.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) { //navRail.removeView(v);

                    ScrollView scroll = (ScrollView) getActivity().findViewById(R.id.scroll0);
                    //scroll.smoothScrollTo(0,300*2);
                    ((Button) v).setText(Integer.toString(scroll.getScrollY()));
                    scroll.smoothScrollBy(0, 0); // there is also scrollBy(x,y), which doesn't work
                    ObjectAnimator objectAnimator;
                    int coordCurent;
                    int inCounter = 0;
                    //TODO shuffle the id's in the array once in the order that they go in the posts list
                    coordCurent = 0; // global "else" for "if's" below
                    LinearLayout botomPanel= (LinearLayout) getActivity().findViewById(R.id.p0_bottom_panel);
                    for(int i=0;i<amountOfPostsVisible;i++) if(v.getId() == buttons_ids[i]){coordCurent = postsCont.getChildAt(i).getBottom()-(botomPanel.getTop()); break;}
                    if(coordCurent<0)coordCurent=0;

                    objectAnimator = ObjectAnimator.ofInt(scroll, "scrollY", scroll.getScrollY(), coordCurent).setDuration(500);
                    objectAnimator.start();
                    scroll.scrollTo(0, coordCurent);
                } // getScrollY() -- current scroll position
            });

            buttons_ids[counter]=generateViewId();
            buToLoad.setId(buttons_ids[counter]); //TODO generate id's for buttons from outer method for all the buttons possible at once, then send these ids here amd use em
            switch (toLoad) {
                case 0:
                    buToLoad.setText("admin " + Integer.toString(buttons_ids[counter]));
                    break;
                case 1:
                    buToLoad.setText("1 " + Integer.toString(buttons_ids[counter]));
                    break;
                case 2:
                    buToLoad.setText("2 " + Integer.toString(buttons_ids[counter]));
                    break;
                case 3:
                    buToLoad.setText("3 " + Integer.toString(buttons_ids[counter]));
                    break;
                case 4:
                    buToLoad.setText("4 " + Integer.toString(buttons_ids[counter]));
                    break;
                case 5:
                    buToLoad.setText("5 " + Integer.toString(buttons_ids[counter]));
                    break;
                case 6:
                    buToLoad.setText("6 " + Integer.toString(buttons_ids[counter]));
                    break;
                case 7:
                    buToLoad.setText("7 " + Integer.toString(buttons_ids[counter]));
                    break;
                case 8:
                    buToLoad.setText("8 " + Integer.toString(buttons_ids[counter]));
                    break;
                case 9:
                    buToLoad.setText("9 " + Integer.toString(buttons_ids[counter]));
                    break;
                default:
                    buToLoad.setText("default " + Integer.toString(buttons_ids[counter]));
                    break;
            }
            //buToLoad.setText(Integer.toString(___));
            navRail.addView(buToLoad);

            unloaded[toLoad] = false;
            toLoad = getmax(unloaded);
            counter++;
        }
        counter=0;
    } //static int [] coord=new int[6];

    LinearLayout navRail;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        final View view = inflater.inflate(R.layout.fragment_navrail, container, false);

        navRail = (LinearLayout) view.findViewById(R.id.navRail);


        //static int coord = coord1[1];

        return view;
    }

    public void onStart() {
        super.onStart();
        navRail = (LinearLayout) getActivity().findViewById(R.id.navRail);
        if (!navRailState && navRail.getChildAt(1)==null) {
            loadContent(navRail);
            navRailState = true;
        }
    }

    public void onDestroyView() {
        super.onDestroyView();
        navRailState = false;
        Log.d("navr","running build");
        navRail.removeAllViews();

    }
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