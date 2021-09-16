package com.example.lecture3;


import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Main2Activity extends AppCompatActivity {

    private TextView tvMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2main);

        tvMsg = (TextView) findViewById(R.id.tvMsg);

        String secretMsg;  //TRUE OR FALSE: This is the key to the Bundle/Hashmap?

        Bundle bundle = getIntent().getExtras();  //this means get me the bundle attached, to the intent that spawned me.
        secretMsg = bundle.getString("extraInfo");  //TRUE OR FALSE: The parameter in quotes, is the key to the Bundle/Hashmap?
        secretMsg = "Extra Info From Activity 1: " + secretMsg;

        tvMsg.setText(secretMsg);  //QUESTION: Is this the Key or Value?
        Toast.makeText(Main2Activity.this, "The first activity sent me a Secret Value = " + secretMsg , Toast.LENGTH_LONG).show();


    }

}
