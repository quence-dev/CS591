package com.example.c7_28;


import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.GestureDetector;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Random;


public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener{

    private TextView txtView;
    private ConstraintLayout layout;
    private GestureDetector GD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtView = (TextView) findViewById(R.id.txtView);
        layout = (ConstraintLayout) findViewById(R.id.layoutScreen);
        GD = new GestureDetector(this,this);
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // We need our gestureDetector to receive the event
        this.GD.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, final float velocityX, final float velocityY) {
        DisplayMetrics m = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(m);
        ObjectAnimator oAnimate =null;
        final float textDimension[] = {txtView.getWidth(), txtView.getHeight()};
        final float screenDimension[] = {m.widthPixels, m.heightPixels};
        final float layoutHeight = layout.getMeasuredHeight();

        textDimension[1] = textDimension[1]-(screenDimension[1] - layoutHeight);

        int[] location = new int[2];
        txtView.getLocationOnScreen(location);

        if(Math.abs(velocityX) > Math.abs(velocityY)) {
            if (velocityX > 0) {
                oAnimate = ObjectAnimator.ofFloat(txtView, "translationX", location[0], ((screenDimension[0] / 2) - (textDimension[0] / 2)));
                oAnimate.setDuration(300);
                oAnimate.start();
            } else if (velocityX < 0) {
                oAnimate = ObjectAnimator.ofFloat(txtView, "translationX", location[0], ((screenDimension[0] / 2) + (textDimension[0] / 2)));
                oAnimate.setDuration(300);
                oAnimate.start();
            }
        }
        if(Math.abs(velocityX) < Math.abs(velocityY)) {
            if (velocityY > 0) {
                oAnimate = ObjectAnimator.ofFloat(txtView, "translationY", location[0], ((layoutHeight/2) - (textDimension[1] / 2)));
                oAnimate.setDuration(300);
                oAnimate.start();
            } else if (velocityY < 0) {
                oAnimate = ObjectAnimator.ofFloat(txtView, "translationY", location[0], ((layoutHeight/2) + (textDimension[1] / 2)));
                oAnimate.setDuration(300);
                oAnimate.start();
            }
        }
        oAnimate.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
            float thresholdX = 15000;
            float thresholdY = 15000;

            if(Math.abs(velocityX) > thresholdX && Math.abs(velocityY)>thresholdY){

                txtView.setVisibility(View.INVISIBLE);

                int capX = (int) (screenDimension[0] - textDimension[0]);
                int newX = new Random().nextInt(capX);
                int capY = (int) (layoutHeight - textDimension[1]);
                int newY = new Random().nextInt(capY);

                Toast.makeText(getApplicationContext(), "New location for TEXT", Toast.LENGTH_SHORT).show();
                txtView.setX(newX);
                txtView.setY(newY);
                txtView.setVisibility(View.VISIBLE);

            }

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        return true;
    }

}