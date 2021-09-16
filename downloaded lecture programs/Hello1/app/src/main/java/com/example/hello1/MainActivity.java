package com.example.hello1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private Button btnSayHello;
    private EditText txtMsg;

    private Button CHANGEME;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSayHello = (Button) findViewById(R.id.btnSayHello);
        txtMsg = (EditText) findViewById(R.id.txtMsg);

        btnSayHello.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtMsg.setText("Hello World... FINALLY!");
            }
        });

    }
}