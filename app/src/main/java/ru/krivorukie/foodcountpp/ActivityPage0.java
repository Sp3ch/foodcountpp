package ru.krivorukie.foodcountpp;

import androidx.appcompat.app.AppCompatActivity;


import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
//import androidx.fragment.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ToggleButton;




import static android.app.PendingIntent.getActivity;

public class ActivityPage0 extends AppCompatActivity {
Button button1,button2,button3,button4;
    private boolean navRailDefaultState=true; // TODO fix coordinates bug and set this shit to true!!!
    private boolean isNavRailDestroyed =false;
    private boolean navRailState=false;

    FragmentManager fragMan = getFragmentManager();
    FragmentNavRail fnr1=new FragmentNavRail();
    Fragment fnr2;
    FragmentTransaction navRailTransaction;

    FragmentTransaction fragTrans;
    Page0Posts.Decoy postTest;
    Page0Posts page0 = new Page0Posts();

    @Override
    protected void onCreate(Bundle savedInstanceState) {/*
        View decorView = getWindow().getDecorView();        // /    Hiding status bar
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;     // |
        decorView.setSystemUiVisibility(uiOptions);         // \    _________________
*/
        //requestWindowFeature(Window.FEATURE_NO_TITLE);      //will hide the title
        //getSupportActionBar().hide();                       // hide the title bar

        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_0);


        //int position=0;         //TODO
        if(savedInstanceState!=null)fnr2=fragMan.getFragment(savedInstanceState,"fnr1");


//test


        View.OnClickListener listener1= new View.OnClickListener(){
            Intent i1;
            @Override
            public void onClick(View v){
                switch(v.getId()){
                    case R.id.button2:
                        i1=new Intent(ActivityPage0.this, ActivityPage4.class);
                        startActivity(i1);
                        break;

                    case R.id.button3:
                        i1=new Intent(ActivityPage0.this, ActivityPage5.class);
                        startActivity(i1);

                        break;

                    case R.id.button1:
                        break;
                    default: break;
                }
            }

        };


        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (ToggleButton) findViewById(R.id.button4);

        button3.setOnClickListener(listener1);
        button2.setOnClickListener(listener1);
        button1.setOnClickListener(listener1);

        if (savedInstanceState!=null) isNavRailDestroyed = savedInstanceState.getBoolean("nabRailState"); else isNavRailDestroyed =navRailDefaultState;
    }



    @Override
    protected void onStart(){
        super.onStart();

// building scrollview content
        if(((LinearLayout)findViewById(R.id.postsCont)).getChildAt(0)==null){

            page0.buildScroll(ActivityPage0.this,R.id.postsCont);
            // /*
            for(int i=0;i<10;i++){
            fragTrans = fragMan.beginTransaction();
            postTest = new  Page0Posts.Decoy ();
            if (i==1) fragTrans.add(R.id.postsCont, postTest, "iterator");
            else fragTrans.add(R.id.postsCont, postTest);
            fragTrans.commit(); }
            // */
        }
// building navRail
        if(findViewById(R.id.navRail)==null && isNavRailDestroyed){
            navRailTransaction = fragMan.beginTransaction();
            navRailTransaction.add(R.id.page_0_upper_screen, fnr1);
            navRailTransaction.commit();
            //TODO add navRail fragment to the screen if it's stated by the settings of user
            ((ToggleButton) button4).setChecked(true);
            button4.setText(" ——>");
            isNavRailDestroyed =false;
            navRailState=true;
        }


        button4 = (ToggleButton) findViewById(R.id.button4);
        CompoundButton.OnCheckedChangeListener navRailOpener = new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    navRailTransaction = fragMan.beginTransaction();
                    navRailTransaction.add(R.id.page_0_upper_screen, fnr1,"fnr1");
                    navRailTransaction.commit();
                    navRailState=true;
                    //TODO add navRail fragment to the screen (if navRailDefaultState then able to delete back)
                }
                else{
                    if(fnr1 != null) {fragMan.beginTransaction().remove(fnr1).commit();}
                    if(fnr2 != null) {fragMan.beginTransaction().remove(fnr2).commit();fnr2=null;}
                    navRailState =false;
                    //TODO remove navRail fragment from the screen (if NOT navRailDefaultState then able to delete back)
                }
            }};
        ((ToggleButton) button4).setOnCheckedChangeListener(navRailOpener);


        }
    @Override
    protected void onResume(){
        super.onResume();

    }
    @Override
    public void onSaveInstanceState(Bundle state) {
        super.onSaveInstanceState(state);
        //if(fragMan.findFragmentByTag("fnr1")!=null)fragMan.putFragment(state,"fnr1",fnr1);
        if(findViewById(R.id.navRail)!=null){if(fnr2==null)fragMan.putFragment(state,"fnr1",fnr1); else fragMan.putFragment(state,"fnr1",fnr2); isNavRailDestroyed=true;}
        state.putBoolean("navRailState", isNavRailDestroyed);
    }
}




/*
i1=new Intent(MainActivity.this,Page_0.class);
startActivity(i1);
requestWindowFeature(Window.FEATURE_NO_TITLE);      //will hide the title
    getSupportActionBar().hide();                       // hide the title bar      */