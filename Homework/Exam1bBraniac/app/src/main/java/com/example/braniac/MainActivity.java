package com.example.braniac;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btnLogin;
    private EditText edtUserName;
    private EditText edtPassword;

    private static final String PASSWORD = "password";
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLogin = (Button) findViewById(R.id.btnLogin);
        edtUserName  = (EditText) findViewById(R.id.edtUserName);
        edtPassword = (EditText) findViewById(R.id.edtPassword);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = edtUserName.getText().toString();
                String password = edtPassword.getText().toString();

                if (username.isEmpty() || !password.equals(PASSWORD)){
                    Toast.makeText(getApplicationContext(), getString(R.string.try_again), Toast.LENGTH_SHORT).show();

                    edtPassword.setText("");
                }
                else {
                    Intent i = new Intent(getApplicationContext(), Activity2.class);
                    i.putExtra("username", username);

                    startActivity(i);
                }


            }
        });


    }
}