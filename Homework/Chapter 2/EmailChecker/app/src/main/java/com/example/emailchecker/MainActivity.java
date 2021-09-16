package com.example.emailchecker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String MSG_FAIL = "INVALID";
    private static final String MSG_SUCCESS = "VALID";

    private Button btnCheck;
    private EditText edtEmail;
    private TextView txtLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtEmail = (EditText) findViewById(R.id.edtEmail);
        btnCheck = (Button) findViewById(R.id.btnCheck);
        txtLabel = (TextView) findViewById(R.id.txtLabel);

        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtLabel.setText(checkEmail(edtEmail.getText().toString()) ? MSG_SUCCESS : MSG_FAIL);
            }

            private boolean checkEmail(String s) {
                return s.contains("@");
            }

        });



    }
}