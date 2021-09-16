package com.example.sse.interfragmentcommratingbar;


import android.app.Activity;
import android.app.Fragment;
import android.graphics.drawable.Drawable;
import android.media.Rating;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;

import java.lang.reflect.Field;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class DrawableFragment extends Fragment {

    ArrayList<Drawable> drawables;  //keeping track of our drawables
    //private int currDrawableIndex;  //keeping track of which drawable is currently displayed. //commented out; do not use

 //Boiler Plate Stuff.
    private ImageView imgRateMe;
    //private Button btnLeft; //commented out; do not use
    //private Button btnRight;//commented out; do not use
    private RatingBar ratingBar;

//    public DrawableFragment() {
//        // Required empty public constructor
//    }
    //commented out; do not use

    ControlFragmentListener fragListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        fragListener = (DrawableFragment.ControlFragmentListener) activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_drawable, container, false);  //comment this out, it would return the default view, without our setup/amendments.
        View v = inflater.inflate(R.layout.fragment_drawable, container, false);   //MUST HAPPEN FIRST, otherwise components don't exist.

        imgRateMe = (ImageView) v.findViewById(R.id.imgRateMe);
        /**btnRight = (Button) v.findViewById(R.id.btnRight);
        btnLeft = (Button) v.findViewById(R.id.btnLeft);
         **/
        //commented out; do not use
        ratingBar = (RatingBar) v.findViewById(R.id.ratingBar);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar ratingBar, float rating,
                                        boolean fromUser) {
                fragListener.setRating(rating);
            }
        });


        //currDrawableIndex = 0;  //ArrayList Index of Current Drawable. //commented out; do not use
        drawables = getDrawables();         //Retrieves the drawables we want, ie, prefixed with "animal_"
        imgRateMe.setImageDrawable(null);  //Clearing out the default image from design time.
        changePicture(0,0);        //Sets the ImageView to the first drawable in the list.


/**setting up navigation call backs.  (Left and Right Buttons)
        btnLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currDrawableIndex == 0)
                    currDrawableIndex = drawables.size() - 1;
                else
                    currDrawableIndex--;
                changePicture();
            }
        });

        btnRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currDrawableIndex == drawables.size() - 1)
                    currDrawableIndex = 0;
                else
                    currDrawableIndex++;
                changePicture();
            }
        });
**/ //commented out; do not use
        return v;   //returns the view, with our must happen last, Why? A: ____________
    }


//Routine to change the picture in the image view dynamically.
//implemented input type to (key,Value)
    public void changePicture(int index, float rating) {
      imgRateMe.setImageDrawable(drawables.get(index));
      //set rating
      ratingBar.setRating(rating);
    }

//Quick and Dirty way to get drawable resources, we prefix with "animal_" to filter out just the ones we want to display.
//REF: http://stackoverflow.com/questions/31921927/how-to-get-all-drawable-resources
//implemented to return an ArrayList
    public ArrayList<Drawable> getDrawables() {
        Field[] drawablesFields = com.example.sse.interfragmentcommratingbar.R.drawable.class.getFields();  //getting array of ALL drawables.
        drawables = new ArrayList<>();  //we prefer an ArrayList, to store the drawables we are interested in.  Why ArrayList and not an Array here? A: _________

        String fieldName;
        for (Field field : drawablesFields) {   //1. Looping over the Array of All Drawables...
            try {
                fieldName = field.getName();    //2. Identifying the Drawables Name, eg, "animal_bewildered_monkey"
                Log.i("LOG_TAG", "com.your.project.R.drawable." + fieldName);
                if (fieldName.startsWith("animals_"))  //3. Adding drawable resources that have our prefix, specifically "animal_".
                    drawables.add(getResources().getDrawable(field.getInt(null)));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //implemented to return an ArrayList
        return drawables;
    }

    public interface ControlFragmentListener {
        void setRating(float rating);
    }
}