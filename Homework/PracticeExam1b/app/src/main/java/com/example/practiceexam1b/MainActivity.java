package com.example.practiceexam1b;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button echoBtn;
    private EditText echoText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        echoBtn = (Button) findViewById(R.id.btnEcho);
        echoText = (EditText) findViewById(R.id.edtEcho);


        echoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), echoText.getText().toString(), Toast.LENGTH_LONG).show();
            }
        });
    }
}