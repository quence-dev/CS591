package com.example.ch2q23;

import androidx.appcompat.app.AppCompatActivity;


import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button NextColor;
    private View lightGreen;
    private View lightYellow;
    private View lightRed;
    private TextView light;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lightGreen = (View) findViewById(R.id.GreenLight);
        lightYellow = (View) findViewById(R.id.YellowLight);
        lightRed = (View) findViewById(R.id.RedLight);
        NextColor = (Button) findViewById(R.id.Button);
        light = (TextView) findViewById(R.id.label);
        light.setText("None");


        NextColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (light.getText() == "None") {
                    lightGreen.setBackgroundColor(Color.GREEN);
                    lightYellow.setBackgroundColor(Color.GRAY);
                    lightRed.setBackgroundColor(Color.GRAY);
                    light.setText("GREEN");
                }
                else if (light.getText() == "GREEN") {
                    lightYellow.setBackgroundColor(Color.YELLOW);
                    lightGreen.setBackgroundColor(Color.GRAY);
                    light.setText("YELLOW");
                }
                else if (light.getText() == "YELLOW") {
                    lightYellow.setBackgroundColor(Color.GRAY);
                    lightRed.setBackgroundColor(Color.RED);
                    light.setText("RED");
                }
                else if (light.getText() == "RED") {
                    lightRed.setBackgroundColor(Color.GRAY);
                    lightGreen.setBackgroundColor(Color.GREEN);
                    light.setText("GREEN");

                }
                ;


            }

        });
    }
}