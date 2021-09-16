package com.jw.emailchecker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String MSG_SUCCESS = "VALID";
    private static final String MSG_FAIL = "INVALID";

    private Button bCheck;
    private EditText etEmail;
    private TextView tfLabel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bCheck = findViewById(R.id.bCheck);
        etEmail = findViewById(R.id.etEmail);
        tfLabel = findViewById(R.id.tfLabel);

        bCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tfLabel.setText(checkEmail(etEmail.getText().toString()) ? MSG_SUCCESS : MSG_FAIL);
            }

            private boolean checkEmail(String s) {
                // extremely basic way to check email input..
                return s.contains("@");
            }
        });
    }
}