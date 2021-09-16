package com.example.sse.lect4_rollyourown3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;


public class Main2Activity extends Activity {

    final int TOTAL_BUTTONS = 5;
//Lecture Notes (2): Creating your own layout from scratch, no layout xml.

    private LinearLayout LLMain;
    private LinearLayout.LayoutParams LLP;  //Ref: https://developer.android.com/reference/android/widget/RelativeLayout.LayoutParams.html
    Button[] Buttons;  //array of buttons.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//1: get rid of this inflater... we won't use the designer at all.
        //       setContentView(R.layout.activity_main2);

        //Let's roll our own everything, including the base view group (Linear Layout).


//2: create the View Group
        LLMain = new LinearLayout(Main2Activity.this);  //Q: Wny not MainActivity.this??? A: __________
        LLMain.setOrientation(LinearLayout.HORIZONTAL);

//3: Create the Layout Parameters
        LLP = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT  );  //Q: Does this specify how the LinearLayout will be displayed? A: _Not really, specifies how views will be displayed._____

//4: Create our array of Buttons and add them to the viewgroup.
        Buttons = new Button[TOTAL_BUTTONS];   //Q: Can you dynamically create an array of views in the designer? A: _______________
        for(int i=0;i<Buttons.length;i++) {
            Buttons[i] = new Button(getBaseContext());
            Buttons[i].setText("Button_" + String.valueOf(i));
            Buttons[i].setTag(i);  //tag our button in case we want to distinguish later, eg, in an onClick event.
            Buttons[i].setLayoutParams(LLP); //Let the button know how it will be laid out.
            LLMain.addView(Buttons[i]);
        }
//5: Render the View Group with it's contents.  (instead of the usual call to SetContentView)
        this.addContentView(LLMain, LLP);   //Q: Wait a minute, I thought LLP was for the views, not the view group.  What's different this time? A: _______

//MISC...
        Buttons[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Main2Activity.this, Main3Activity.class );
//        Intent i = new Intent(getApplicationContext(), Main2Activity.class );
                startActivity(i);
            }
        });

    }
}

