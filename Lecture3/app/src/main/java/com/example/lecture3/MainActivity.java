package com.example.lecture3;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private CheckBox cbHamburger;
    private CheckBox cbFrenchFries;
    private CheckBox cbOnionRings;
    private TextView tvTotal;
    private Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cbHamburger = (CheckBox) findViewById(R.id.cbHamburger);
        cbFrenchFries = (CheckBox) findViewById(R.id.cbFrenchFries);
        cbOnionRings = (CheckBox) findViewById(R.id.cbOnionRings);
        tvTotal = (TextView) findViewById(R.id.tvTotal);
        btnNext = (Button) findViewById(R.id.btnNext);


        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Main2Activity.class );

                i.putExtra("extraInfo","I love movies");
                startActivity(i);
            }
        });


    }

    public void onCBClick(View v){
        Integer totalPrice = 0;

        if (cbHamburger.isChecked())
            totalPrice = totalPrice + 1;

        if (cbFrenchFries.isChecked())
            totalPrice = totalPrice + 1;

        if (cbOnionRings.isChecked())
            totalPrice = totalPrice + 1;

        String tvMessage = "Total: " + totalPrice.toString();

        tvTotal.setText(tvMessage);

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {

        outState.putBoolean("hamburger", cbHamburger.isChecked());
        outState.putBoolean("frenchfries", cbFrenchFries.isChecked());
        outState.putBoolean("onionrings", cbOnionRings.isChecked());
        outState.putString("total", tvTotal.getText().toString());

        //DO THIS LAST
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        tvTotal.setText(savedInstanceState.getString("total"));

        cbHamburger.setChecked(savedInstanceState.getBoolean("hamburger"));
        cbOnionRings.setChecked(savedInstanceState.getBoolean("onionrings"));
        cbFrenchFries.setChecked(savedInstanceState.getBoolean("frenchfries"));
    }
}