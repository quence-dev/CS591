package com.example.w5_p3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements fragmentAbove.ControlFragmentListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void sportsString(String msg) {
        fragmentUnder rFragment = (fragmentUnder) getSupportFragmentManager().findFragmentById(R.id.under);
        rFragment.chooseSports(msg);
    }
}