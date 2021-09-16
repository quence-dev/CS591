package com.example.sse.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    TextView txtMessage;
    Button btnNextActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        txtMessage = (TextView)findViewById(R.id.txtMessage);
        btnNextActivity = (Button)findViewById(R.id.btnNextActivity);

        btnNextActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent NextActivity = new Intent("com.example.sse.myapplication.Main22Activity");
                startActivity(NextActivity);
            }
        });


        Intent i = getIntent();
        if (i.hasExtra("SourceEvent")){
            String value = i.getStringExtra("SourceEvent");
            txtMessage.setText(value);
        }

    }
}
