package com.example.storyboardpanel3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;



public class MainActivity extends AppCompatActivity {
    private String[] castList; //get all the names of actors in the cast and put into string list
    TextView movieTitle, movie, rating;
    //grab movie title and rating and display in a textview
    //rating textview clickable and takes the next activity
    String[] actors = {"actor1", "actor2", "actor3", "actor4", "actor5"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}