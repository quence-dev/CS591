package com.jw.passwordchecker;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final String MSG_SUCCESS = "THANK YOU";
    private static final String MSG_FAIL = "PASSWORDS MUST MATCH";

    private TextView tfLabel;
    private EditText etPassword0;
    private EditText etPassword1;
    private Button bCheckPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tfLabel = findViewById(R.id.tfLabel);
        etPassword0 = findViewById(R.id.etPassword0);
        etPassword1 = findViewById(R.id.etPassword1);
        bCheckPassword = findViewById(R.id.bCheckPassword);

        bCheckPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tfLabel.setText(
                        etPassword0.getText().toString().equals(etPassword1.getText().toString()) ?
                                MSG_SUCCESS : MSG_FAIL
                );
            }
        });
    }
}