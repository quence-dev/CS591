package com.example.sse.myapplication;

import android.app.Activity;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.widget.TextView;

///////  Set of imports for Gesture Detection ////////
import android.view.MotionEvent;
import android.view.GestureDetector;
//import android.support.v4.view.GestureDetector;
//////////////////////////////////////////////////////

public class Main22Activity extends Activity
        implements GestureDetector.OnGestureListener,
             GestureDetector.OnDoubleTapListener {

    private TextView txtMessage;
    private GestureDetector GD;    //must instantiate the gesture detector

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main22);

        GD = new GestureDetector(this, this);   //Context, Listener as per Constructor Doc.
        GD.setOnDoubleTapListener(this);   //DoubleTaps implemented a bit differently, must be bound like this.

        txtMessage = (TextView)findViewById(R.id.txtMessage);
    }



        //////////////////////////////////////////////////////////////////////////
    //very important step, otherwise we won't be able to capture our touches//
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.GD.onTouchEvent(event);               //Our GD will not automatically receive Android Framework Touch notifications.
                                                   // Insert this line to consume the touch event locally by our GD,
                                                   // IF YOU DON'T insert this before the return, our GD will not receive the event, and therefore won't do anything.
        return super.onTouchEvent(event);          // Do this last, why?
    }
    //////////////////////////////////////////////////////////////////////////




    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        txtMessage.setText("onSingleTapConfirmed");
        return true;
    }




    @Override
    public boolean onDoubleTap(MotionEvent e) {
        txtMessage.setText("onDoubleTap");
        return true;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        txtMessage.setText("onDoubleTapEvent");
        return true;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        txtMessage.setText("onDown");
        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {
        txtMessage.setText("onShowPress");
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        txtMessage.setText("onSingleTapUp");
        return true;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2,
                            float distanceX, float distanceY) {

        txtMessage.setText("onScroll");
        return true;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        txtMessage.setText("onLongPress");
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        txtMessage.setText("onFling");
        return true;
    }
}
