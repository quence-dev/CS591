package com.example.flingingeuros;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener {

    private EditText euroValue;
    private GestureDetector GD;
    private TextView rupeeValue;
    private TextView yenValue;
    private TextView florinValue;
    private TextView dollarValue;


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.GD.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GD = new GestureDetector(this, this);
        euroValue = (EditText) findViewById(R.id.euroValue);
        rupeeValue = (TextView) findViewById(R.id.rupeeValue);
        yenValue = (TextView) findViewById(R.id.yenValue);
        florinValue = (TextView) findViewById(R.id.florinValue);
        dollarValue = (TextView) findViewById(R.id.dollarValue);

        euroValue.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(euroValue.getText().toString().length() ==0){
                    return;
                }
                else{
                    Double euro = Double.parseDouble(euroValue.getText().toString());
                    setCurrency(euro);
                }
            }
        });

    }
    public void setCurrency(double euro){
        Double dollar = euro * 1.18;
        Double rupee = euro *86.14;
        Double florin = euro *2.12;
        Double yen = euro * 124.38;

        rupeeValue.setText("₹" + Double.toString(rupee));
        dollarValue.setText("$" + Double.toString(dollar));
        florinValue.setText("ƒ" + Double.toString(florin));
        yenValue.setText("¥" + Double.toString(yen));
    }
    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        float velocity = 100;
        if(euroValue.getText().toString().length() != 0) {
            if (velocityX < -velocity) {
                Double val = Double.parseDouble(euroValue.getText().toString()) - 1.00;
                euroValue.setText(Double.toString(val));
                setCurrency(val);
                Toast.makeText(MainActivity.this, "-1", Toast.LENGTH_SHORT).show();
            } else if (velocityX >velocity) {
                Double val = Double.parseDouble(euroValue.getText().toString()) + 1.00;
                euroValue.setText(Double.toString(val));
                setCurrency(val);
                Toast.makeText(MainActivity.this, "+1", Toast.LENGTH_SHORT).show();
            }
        }
        else{
            Toast.makeText(MainActivity.this, "Enter Euro Amount", Toast.LENGTH_SHORT).show();
        }
        return true;
    }


    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        if (Math.abs(distanceX) > 1500){
            return false;
        }

        if(euroValue.getText().toString().length() !=0) {
            if (distanceY > 0) {
                Double val = Double.parseDouble(euroValue.getText().toString()) + 0.05;
                euroValue.setText(Double.toString(val));
                setCurrency(val);
            } else if (distanceY < 0) {
                Double val = Double.parseDouble(euroValue.getText().toString()) - 0.05;
                euroValue.setText(Double.toString(val));
                setCurrency(val);
            }
        }
        else{
            Toast.makeText(MainActivity.this, "Enter Euro Amount", Toast.LENGTH_SHORT).show();
        }
        return true;
    }



    // UNUSED EVENTS ////////
    ///////////////////////////////////////////////////////////
    @Override
    public void onLongPress(MotionEvent e) { }

    @Override
    public boolean onDown(MotionEvent e) { return false; }

    @Override
    public void onShowPress(MotionEvent e) { }

    @Override
    public boolean onSingleTapUp(MotionEvent e) { return false; }
    //////////////////////////////////////////////////////////
}