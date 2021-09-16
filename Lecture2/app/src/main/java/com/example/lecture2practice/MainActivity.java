package com.example.lecture2practice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button btnSayHello;
    private EditText edtMsg;
    private TextView txtMsg;

    final private String LOGTAG = "Quence";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(LOGTAG, "onCreate");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtMsg = (EditText) findViewById(R.id.edtMsg);
        btnSayHello = (Button) findViewById(R.id.btnSayHello);
        txtMsg = (TextView) findViewById(R.id.txtMsg);

        btnSayHello.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtMsg.setText("Hi Friends!!");
                txtMsg.setText("Hi Friends!!");
            }
        });
    }

    @Override
    protected void onPause() {
        Log.i(LOGTAG, "onPause");
        super.onPause();
    }

    @Override
    protected void onStart() {
        Log.i(LOGTAG, "onStart");
        super.onStart();
    }

    @Override
    protected void onRestart() {
        Log.i(LOGTAG, "onRestart");
        super.onRestart();
    }

    @Override
    protected void onStop() {
        Log.i(LOGTAG, "onStop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.i(LOGTAG, "onDestroy");
        super.onDestroy();
    }

}