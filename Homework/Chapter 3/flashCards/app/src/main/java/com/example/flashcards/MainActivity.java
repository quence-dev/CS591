package com.example.flashcards;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText inPassword;
    private EditText inUser;
    private Button loginButton;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void login(View v){
        inPassword = (EditText) findViewById(R.id.inputPassword);
        inUser = (EditText) findViewById(R.id.inputUser);
        loginButton = (Button) findViewById(R.id.login);

        intent = new Intent(this, Flashcard.class);

        String userAnswer = "jihoseo";
        String passAnswer = "12345";

        if(inUser.getText().toString().equals(userAnswer) && inPassword.getText().toString().equals(passAnswer)){
            Toast.makeText(getApplicationContext(),"Welomce jihoseo",Toast.LENGTH_LONG).show();
            startActivity(intent);
        }
        else{
            Toast.makeText(getApplicationContext(),"Please type correct Username & Password",Toast.LENGTH_LONG).show();
            inPassword.setText("");
            inUser.setText("");
        }



    }

}