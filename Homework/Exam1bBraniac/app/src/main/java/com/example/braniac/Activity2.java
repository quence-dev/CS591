package com.example.braniac;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class Activity2 extends AppCompatActivity {

    private static final String LOGTAG = "spencer";
    private Button btnStartOver;
    private Button btnSubmit;
    private EditText edtAnswer;
    private TextView tvMathProblem;
    private int count, correct, op1, op2, op3, answer;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.i(LOGTAG, "reached activity 2");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2);

        Bundle i = getIntent().getExtras();
        String username = i.getString("username");

        btnStartOver = (Button) findViewById(R.id.btnStartOver);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        edtAnswer = (EditText) findViewById(R.id.edtAnswer);
        tvMathProblem = (TextView) findViewById(R.id.tvMathProblem);

        correct = 0;
        count = 0;
        answer = 0;

        //WELCOME MESSAGE
        Toast.makeText(Activity2.this, getString(R.string.welcome)+ username, Toast.LENGTH_LONG).show();

        tvMathProblem.setText(GenerateNumbers());

        //RESTART GAME
        btnStartOver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();
            }
        });
    }

    //EARLY BOUND BUTTON CLICK
    public void OnSubmit (View view) {
        int userAnswer = Integer.parseInt(edtAnswer.getText().toString());
        edtAnswer.setText("");

        if (userAnswer == answer){
            correct++;
            Toast.makeText(Activity2.this, getString(R.string.correct), Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(Activity2.this, getString(R.string.incorrect), Toast.LENGTH_SHORT).show();
        }

        count++;

        tvMathProblem.setText(GenerateNumbers());
        if (count == 5){
            Toast.makeText(Activity2.this, "Score: " + Integer.toString(correct), Toast.LENGTH_LONG).show();
            resetGame();
        }
    }

    //RESETS GAME STATS
    private void resetGame () {
        edtAnswer.setText("");
        correct = 0;
        count = 0;
        answer = 0;
        tvMathProblem.setText(GenerateNumbers());
        Toast.makeText(Activity2.this, "New Game", Toast.LENGTH_LONG).show();
    }

    //SETS RANDOM NUMBERS AND RETURNS MATH PROBLEM
    private String GenerateNumbers () {
        Random r = new Random();
        op1 = r.nextInt(51);
        op2 = r.nextInt(51);
        op3 = r.nextInt(51);
        answer = op1 + op2 + op3;

        return Integer.toString(op1) + " + " +
                Integer.toString(op2) + " + " + Integer.toString(op3) + " = ";
    }
}
