package com.example.sse.interfragmentcommratingbar;

import android.app.Activity;
import android.app.Fragment;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;

import java.util.HashMap;

public class MainActivity extends Activity implements buttonLeft.ControlFragmentListener, buttonRight.ControlFragmentListener, DrawableFragment.ControlFragmentListener {
    // use HashMap to store ratings for specific picture
    private HashMap<Integer,Float> hashRate = new HashMap<Integer, Float>();
    private int currDrawableIndex;  //keeping track of which drawable is currently displayed.
    private DrawableFragment fragment;
    private int drawSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //define fragments and current Index
        currDrawableIndex = 0;
        drawSize = ((DrawableFragment) getFragmentManager().findFragmentById(R.id.fragment)).getDrawables().size();
        fragment = (DrawableFragment) getFragmentManager().findFragmentById(R.id.fragment);

        //set current rating as 0
        hashRate.put(currDrawableIndex, (float) 0);

    }

    //implemented method to set rating of a specific key value in hash map
    @Override
    public void setRating(float rating){
        hashRate.put(currDrawableIndex, rating);
    }

    //set method for buttonLeft fragment
    @Override
    public void btnLeft(){
        float rating = 0;
        if (currDrawableIndex == 0)
            currDrawableIndex = drawSize - 1;
        else
            currDrawableIndex--;

        //get Value of the Key Index
        if(hashRate.containsKey(currDrawableIndex)){
            rating = hashRate.get(currDrawableIndex);
        }

        fragment.changePicture(currDrawableIndex,rating);

    }

    //set method for buttonRight fragment
    @Override
    public void btnRight() {
        float rating =0;
        if (currDrawableIndex == drawSize - 1)
            currDrawableIndex = 0;
        else
            currDrawableIndex++;

        //get Value of the Key Index
        if(hashRate.containsKey(currDrawableIndex)){
            rating = hashRate.get(currDrawableIndex);
        }
        fragment.changePicture(currDrawableIndex, rating);
    }
}

