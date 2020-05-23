package ru.krivorukie.foodcountpp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;


import android.app.FragmentManager;
//import androidx.fragment.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ToggleButton;


import static android.app.PendingIntent.getActivity;

public class MainActivity extends AppCompatActivity {
Button button1,button2,button3,button4;
    private boolean navRailDefaultState=true;

    FragmentManager fragMan = getFragmentManager();
    FragmentNavRail fnr1=new FragmentNavRail();
    FragmentTransaction navRailTransaction;
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



        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (ToggleButton) findViewById(R.id.button4);




        //int position=0;         //TODO
        FragmentTransaction fragTrans;
        FragmentPostTest postTest;

        for(int i=0;i<10;i++){
        fragTrans = fragMan.beginTransaction();
        postTest = new FragmentPostTest();
        if (i==1) fragTrans.add(R.id.postsCont, postTest, "iterator");
        else fragTrans.add(R.id.postsCont, postTest);

        fragTrans.commit(); } //TODO set the position variable of second fragment to bundle, then catch it from navRail fragment class
        //Bundle bundle = new Bundle();
        //bundle.putString("position",Integer.toString(position));


        if(navRailDefaultState){((ToggleButton) button4).setChecked(true);
            navRailTransaction = fragMan.beginTransaction();
            navRailTransaction.add(R.id.page_0_upper_screen, fnr1);
            navRailTransaction.commit();
            //TODO add navRail fragment to the screen if it's stated by the settings of user
        }


        CompoundButton.OnCheckedChangeListener navRailOpener = new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    navRailTransaction = fragMan.beginTransaction();
                    navRailTransaction.add(R.id.page_0_upper_screen, fnr1);
                    navRailTransaction.commit();
                    //TODO add navRail fragment to the screen (if navRailDefaultState then able to delete back)
                }
                else{
                    if(fnr1 != null) fragMan.beginTransaction().remove(fnr1).commit();
                    //TODO remove navRail fragment from the screen (if NOT navRailDefaultState then able to delete back)
                }
            }};
        ((ToggleButton) button4).setOnCheckedChangeListener(navRailOpener);

        //final Context context=getApplicationContext();

        View.OnClickListener listener1= new View.OnClickListener(){
            Intent i1;
            @Override
            public void onClick(View v){
                switch(v.getId()){
                    case R.id.button2:
                        i1=new Intent(MainActivity.this,Page4Activity.class);
                        startActivity(i1);
                        break;

                    case R.id.button3:
                        i1=new Intent(MainActivity.this,Page5Activity.class);
                        startActivity(i1);

                        break;

                    case R.id.button1:
                        break;
                default: break;
                }
            }

        };
button3.setOnClickListener(listener1);
button2.setOnClickListener(listener1);
button1.setOnClickListener(listener1);
    }

}



/*
i1=new Intent(MainActivity.this,Page_0.class);
startActivity(i1);
requestWindowFeature(Window.FEATURE_NO_TITLE);      //will hide the title
    getSupportActionBar().hide();                       // hide the title bar      */