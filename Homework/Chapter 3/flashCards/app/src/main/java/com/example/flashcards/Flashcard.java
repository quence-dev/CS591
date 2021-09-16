package com.example.flashcards;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Random;

public class Flashcard extends AppCompatActivity {
    private Button genProblem;
    private Button submitButton;
    private EditText answer;
    private TextView firstNum;
    private TextView secNum;
    private TextView sign;
    private CheckBox add;
    private CheckBox sub;
    private int count;
    private int corrCount;
    private TextView countAns;

    @Override
    protected void onSaveInstanceState(Bundle savedInstaceState) {

        super.onSaveInstanceState(savedInstaceState);
        savedInstaceState.putString("firstNum",firstNum.getText().toString());
        savedInstaceState.putString("secNum",secNum.getText().toString());
        savedInstaceState.putString("sign",sign.getText().toString());
        savedInstaceState.putBoolean("addCheck",add.isChecked());
        savedInstaceState.putBoolean("addEn",add.isEnabled());
        savedInstaceState.putBoolean("subCheck",sub.isChecked());
        savedInstaceState.putBoolean("subEn",sub.isEnabled());
        savedInstaceState.putInt("count",count);
        savedInstaceState.putInt("corrCount",corrCount);
        savedInstaceState.putString("counted",countAns.getText().toString());

    }
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        firstNum.setText(savedInstanceState.getString("firstNum"));
        secNum.setText(savedInstanceState.getString("secNum"));
        sign.setText(savedInstanceState.getString("sign"));
        add.setChecked(savedInstanceState.getBoolean("addCheck"));
        sub.setChecked(savedInstanceState.getBoolean("subCheck"));
        add.setEnabled(savedInstanceState.getBoolean("addEn"));
        sub.setEnabled(savedInstanceState.getBoolean("subEn"));
        count = savedInstanceState.getInt("count");
        corrCount = savedInstanceState.getInt("corrCount");
        countAns.setText(savedInstanceState.getString("counted"));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flashcard);
            genProblem = (Button) findViewById(R.id.generate);
            submitButton = (Button) findViewById(R.id.submit);
            answer = (EditText) findViewById(R.id.answer);
            firstNum = (TextView) findViewById(R.id.firstNum);
            secNum = (TextView) findViewById(R.id.secondNum);
            sign = (TextView) findViewById(R.id.addOrSub);
            add = (CheckBox) findViewById(R.id.checkAdd);
            sub = (CheckBox) findViewById(R.id.checkSub);
            countAns = (TextView) findViewById(R.id.count);

        genProblem.setOnClickListener(new View.OnClickListener() {
            Random r = new Random();
            @Override
            public void onClick(View v) {
                    count = 1;
                    corrCount=0;
                    countAns.setText(Integer.toString(count) + " / 10");
                    if(sub.isChecked()&&add.isChecked()){
                    Toast.makeText(getApplicationContext(),"Check only one of the Arithmetic",Toast.LENGTH_LONG).show();
                }
                    else if(add.isChecked()){
                        submitButton.setEnabled(true);
                        int firstRan = r.nextInt(101);
                        int secRan = r.nextInt(101);
                        firstNum.setText(Integer.toString(firstRan));
                        secNum.setText(Integer.toString(secRan));
                        sign.setText("+");
                        sub.setEnabled(false);
                        add.setEnabled(false);
                    }
                    else if(sub.isChecked()){
                        submitButton.setEnabled(true);
                        int firstRan = r.nextInt(101);
                        int secRan = r.nextInt(firstRan);
                        firstNum.setText(Integer.toString(firstRan));
                        secNum.setText(Integer.toString(secRan));
                        sign.setText("-");
                        add.setEnabled(false);
                        add.setEnabled(false);
                    }

                    else if (!sub.isChecked() && !add.isChecked()){
                        Toast.makeText(getApplicationContext(),"Please choose one Arithmetic",Toast.LENGTH_LONG).show();
                    }
                    }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            Random r = new Random();
            @Override
            public void onClick(View v) {
                int firstnumber = Integer.parseInt(firstNum.getText().toString());
                int secondnumber = Integer.parseInt(secNum.getText().toString());
                if(count == 10){
                    if (add.isChecked()) {
                        if (firstnumber + secondnumber == Integer.parseInt(answer.getText().toString())) {
                            corrCount += 1;
                            Toast.makeText(getApplicationContext(),"Your score is:" + Integer.toString(corrCount),Toast.LENGTH_LONG).show();
                            submitButton.setEnabled(false);
                            add.setEnabled(true);
                            sub.setEnabled(true);

                        } else {
                            Toast.makeText(getApplicationContext(),"Your score is:" + Integer.toString(corrCount),Toast.LENGTH_LONG).show();
                            submitButton.setEnabled(false);
                            add.setEnabled(true);
                            sub.setEnabled(true);
                        }
                    }
                    if (sub.isChecked()) {
                        if (firstnumber - secondnumber == Integer.parseInt(answer.getText().toString())) {
                            corrCount += 1;
                            Toast.makeText(getApplicationContext(),"Your score is:" + Integer.toString(corrCount),Toast.LENGTH_LONG).show();
                            submitButton.setEnabled(false);
                            add.setEnabled(true);
                            sub.setEnabled(true);
                        } else {
                            Toast.makeText(getApplicationContext(),"Your score is:" + Integer.toString(corrCount),Toast.LENGTH_LONG).show();
                            submitButton.setEnabled(false);
                            add.setEnabled(true);
                            sub.setEnabled(true);
                        }
                    }
                }
                else if (add.isChecked()) {
                        if (firstnumber + secondnumber == Integer.parseInt(answer.getText().toString())) {
                            count += 1;
                            corrCount += 1;
                            countAns.setText(Integer.toString(count) + " / 10");
                            int firstRan = r.nextInt(101);
                            int secRan = r.nextInt(101);
                            firstNum.setText(Integer.toString(firstRan));
                            secNum.setText(Integer.toString(secRan));

                    } else {
                        int firstRan = r.nextInt(101);
                        int secRan = r.nextInt(101);
                        firstNum.setText(Integer.toString(firstRan));
                        secNum.setText(Integer.toString(secRan));
                        count +=1;
                        countAns.setText(Integer.toString(count) + " / 10");
                    }

                }
                else if (sub.isChecked()) {
                    if (firstnumber - secondnumber == Integer.parseInt(answer.getText().toString())) {
                        count += 1;
                        corrCount += 1;
                        countAns.setText(Integer.toString(count) + " / 10");
                        int firstRan = r.nextInt(101);
                        int secRan = r.nextInt(firstRan);
                        firstNum.setText(Integer.toString(firstRan));
                        secNum.setText(Integer.toString(secRan));
                    } else {
                        count += 1;
                        countAns.setText(Integer.toString(count) + " / 10");
                        int firstRan = r.nextInt(101);
                        int secRan = r.nextInt(firstRan);
                        firstNum.setText(Integer.toString(firstRan));
                        secNum.setText(Integer.toString(secRan));
                    }
                }
        }
        });
    }
}