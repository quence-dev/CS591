package com.example.simplecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import static java.lang.Double.NaN;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button btn0;
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;
    private Button btn5;
    private Button btn6;
    private Button btn7;
    private Button btn8;
    private Button btn9;

    private Button btn_dot;
    private Button btn_equ;
    private Button btn_div;
    private Button btn_min;
    private Button btn_mult;
    private Button btn_plus;
    private Button btn_sqrt;
    private Button btn_clear;

    private TextView txt_input;

    private Double Op1,Op2,Answer;
    private int pos;
    private char operation;
    private int equalflag;
    private int switch_flag;
    private int minors_flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn0 = (Button) findViewById(R.id.btn0);
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn4 = (Button) findViewById(R.id.btn4);
        btn5 = (Button) findViewById(R.id.btn5);
        btn6 = (Button) findViewById(R.id.btn6);
        btn7 = (Button) findViewById(R.id.btn7);
        btn8 = (Button) findViewById(R.id.btn8);
        btn9 = (Button) findViewById(R.id.btn9);
        btn_div = (Button) findViewById(R.id.btn_div);
        btn_dot = (Button) findViewById(R.id.btn_dot);
        btn_min = (Button) findViewById(R.id.btn_min);
        btn_mult = (Button) findViewById(R.id.btn_mult);
        btn_plus = (Button) findViewById(R.id.btn_plus);
        btn_sqrt = (Button) findViewById(R.id.btn_sqrt);
        btn_equ = (Button) findViewById(R.id.btn_equ);
        btn_clear = (Button) findViewById(R.id.btn_clear);

        txt_input = (TextView) findViewById(R.id.txt_input);
        Answer = 0.0;
        pos = 0;
        Op1 = 0.0;
        Op2 = 0.0;
        equalflag = 1;
        switch_flag = 0;
        operation = '+';
        minors_flag =0;


        btn_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Answer = 0.0;
                pos = 0;
                Op1 = 0.0;
                Op2 = 0.0;
                equalflag = 1;
                switch_flag = 0;
                operation = '+';
                minors_flag =0;
                txt_input.setText("0");
            }
            });

        btn_sqrt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Op2 = Double.valueOf(txt_input.getText().toString());
                if (Op2>=0){
                    Answer = Math.sqrt(Op2);
                }else{
                    Toast.makeText(getApplicationContext(), "Can not sqrt negative numbers, reset!", Toast.LENGTH_LONG).show();
                }
                String displayedAnswer = Answer.toString();
                txt_input.setText(displayedAnswer);
                pos = 0;
                Op1 = 0.0;
                Op2 = 0.0;
                Answer = 0.0;
                equalflag = 1;
                switch_flag = 1;

            }

        });

        btn_equ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pos == 0) {
                    //do nothing
                } else {
                    Op2 = Double.valueOf(txt_input.getText().toString());
                    try {
                        switch (operation) {
                            case '+':
                                Answer = Op1 + Op2;
                                break;
                            case '-':
                                Answer = Op1 - Op2;
                                break;
                            case '*':
                                Answer = Op1 * Op2;
                                break;
                            case '/':
                                Answer = (Op2 == 0.0) ? NaN : Op1 / Op2;
                                break;
                        }
                        String displayedAnswer;
                        if (Answer.equals(NaN)) {
                            Toast.makeText(getApplicationContext(), "Cannot divided by zero, reset!", Toast.LENGTH_LONG).show();
                            displayedAnswer = "0";
                        } else {
                            operation = '+';
                            displayedAnswer = Answer.toString();
                        }
                        pos = 0;
                        Op1 = 0.0;
                        Op2 = 0.0;
                        Answer = 0.0;
                        equalflag = 1;
                        switch_flag = 1;
                        txt_input.setText(displayedAnswer);
                    } catch (Exception exception) {
                        Toast.makeText(getApplicationContext(), "Invalid Operand Entered.", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        btn_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String displayedAnswer;
                if (pos == 0) {
                    pos = 1;
                    operation = '+';
                    if (equalflag==1) {
                        Double value = Double.valueOf(txt_input.getText().toString());
                        Op1 = value;
                        equalflag = 0;
                    }
                    displayedAnswer = Op1.toString();
                    switch_flag = 1;
                    txt_input.setText(displayedAnswer);
                } else {
                    Double value = Double.valueOf(txt_input.getText().toString());
                    Op2 = value;
                    equalflag = 0;
                    try {
                        switch (operation) {
                            case '+':
                                Answer = Op1 + Op2;
                                break;
                            case '-':
                                Answer = Op1 - Op2;
                                break;
                            case '*':
                                Answer = Op1 * Op2;
                                break;
                            case '/':
                                Answer = (Op2 == 0.0) ? NaN : Op1 / Op2;
                                break;
                        }
                        if (Answer.equals(NaN)) {
                            Toast.makeText(getApplicationContext(), "Cannot divided by zero, reset!", Toast.LENGTH_LONG).show();
                            displayedAnswer = "0";
                            txt_input.setText(displayedAnswer);
                            pos = 0;
                            Op1 = 0.0;
                            Op2 = 0.0;
                            Answer = 0.0;
                        }else {
                            pos = 1;
                            operation = '+';
                            displayedAnswer = Answer.toString();
                            txt_input.setText(displayedAnswer);
                            //Op1 = Answer;

                            Op1 = Answer;
                            //Op2 = 0.0;
                            Answer = 0.0;
                            //equalflag = 1;
                            switch_flag = 1;
                        }
                    }catch (Exception exception) {
                        Toast.makeText(getApplicationContext(), "Invalid Operand Entered.", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });


        btn_mult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String displayedAnswer;
                if (pos == 0) {
                    pos = 1;
                    operation = '*';
                    if (equalflag==1) {
                        Double value = Double.valueOf(txt_input.getText().toString());
                        Op1 = value;
                        equalflag = 0;
                    }
                    displayedAnswer = Op1.toString();
                    switch_flag = 1;
                    txt_input.setText(displayedAnswer);
                } else {
                    Double value = Double.valueOf(txt_input.getText().toString());
                    Op2 = value;
                    equalflag = 0;
                    try {
                        switch (operation) {
                            case '+':
                                Answer = Op1 + Op2;
                                break;
                            case '-':
                                Answer = Op1 - Op2;
                                break;
                            case '*':
                                Answer = Op1 * Op2;
                                break;
                            case '/':
                                Answer = (Op2 == 0.0) ? NaN : Op1 / Op2;
                                break;
                        }
                        if (Answer.equals(NaN)) {
                            Toast.makeText(getApplicationContext(), "Cannot divided by zero, reset!", Toast.LENGTH_LONG).show();
                            displayedAnswer = "0";
                            txt_input.setText(displayedAnswer);
                            pos = 0;
                            Op1 = 0.0;
                            Op2 = 0.0;
                            Answer = 0.0;
                        }else {
                            operation = '*';
                            displayedAnswer = Answer.toString();
                            txt_input.setText(displayedAnswer);
                            //Op1 = Answer;
                            Op1 = Answer;
                            //Op2 = 0.0;
                            Answer = 0.0;
                            //equalflag = 1;
                            switch_flag = 1;
                        }
                    }catch (Exception exception) {
                        Toast.makeText(getApplicationContext(), "Invalid Operand Entered.", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        btn_div.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String displayedAnswer;
                if (pos == 0) {
                    pos = 1;
                    operation = '/';
                    if (equalflag==1) {
                        Double value = Double.valueOf(txt_input.getText().toString());
                        Op1 = value;
                        equalflag = 0;
                    }
                    displayedAnswer = Op1.toString();
                    switch_flag = 1;
                    txt_input.setText(displayedAnswer);
                } else {
                    Double value = Double.valueOf(txt_input.getText().toString());
                    Op2 = value;
                    equalflag = 0;
                    try {
                        switch (operation) {
                            case '+':
                                Answer = Op1 + Op2;
                                break;
                            case '-':
                                Answer = Op1 - Op2;
                                break;
                            case '*':
                                Answer = Op1 * Op2;
                                break;
                            case '/':
                                Answer = (Op2 == 0.0) ? NaN : Op1 / Op2;
                                break;
                        }
                        if (Answer.equals(NaN)) {
                            Toast.makeText(getApplicationContext(), "Cannot divided by zero, reset!", Toast.LENGTH_LONG).show();
                            displayedAnswer = "0";
                            txt_input.setText(displayedAnswer);
                            pos = 0;
                            Op1 = 0.0;
                            Op2 = 0.0;
                            Answer = 0.0;
                        }else {
                            operation = '/';
                            displayedAnswer = Answer.toString();
                            txt_input.setText(displayedAnswer);
                            //Op1 = Answer;
                            Op1 = Answer;
                            //Op2 = 0.0;
                            Answer = 0.0;
                            //equalflag = 1;
                            switch_flag = 1;
                        }
                    }catch (Exception exception) {
                        Toast.makeText(getApplicationContext(), "Invalid Operand Entered.", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        btn_min.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String displayedAnswer;
                if(switch_flag==0){
                    String s = txt_input.getText().toString();
                    if (minors_flag ==1){
                        Toast.makeText(getApplicationContext(), "don't press minors twice", Toast.LENGTH_LONG).show();
                    }else{
                    Double value = Double.valueOf(s);

                    if (value == 0.0){
                        displayedAnswer = "-";
                        minors_flag = 1;
                        txt_input.setText(displayedAnswer);
                    }else {
                        //String displayedAnswer;
                        if (pos == 0) {
                            pos = 1;
                            operation = '-';
                            if (equalflag == 1) {
                                value = Double.valueOf(txt_input.getText().toString());
                                Op1 = value;
                                equalflag = 0;
                            }
                            displayedAnswer = Op1.toString();
                            switch_flag = 1;
                            txt_input.setText(displayedAnswer);
                        } else {
                            value = Double.valueOf(txt_input.getText().toString());
                            Op2 = value;
                            equalflag = 0;
                            try {
                                switch (operation) {
                                    case '+':
                                        Answer = Op1 + Op2;
                                        break;
                                    case '-':
                                        Answer = Op1 - Op2;
                                        break;
                                    case '*':
                                        Answer = Op1 * Op2;
                                        break;
                                    case '/':
                                        Answer = (Op2 == 0.0) ? NaN : Op1 / Op2;
                                        break;
                                }
                                if (Answer.equals(NaN)) {
                                    Toast.makeText(getApplicationContext(), "Cannot divided by zero, reset!", Toast.LENGTH_LONG).show();
                                    displayedAnswer = "0";
                                    txt_input.setText(displayedAnswer);
                                    pos = 0;
                                    Op1 = 0.0;
                                    Op2 = 0.0;
                                    Answer = 0.0;
                                } else {
                                    operation = '-';
                                    displayedAnswer = Answer.toString();
                                    txt_input.setText(displayedAnswer);
                                    //Op1 = Answer;
                                    Op1 = Answer;
                                    //Op2 = 0.0;
                                    Answer = 0.0;
                                    //equalflag = 1;
                                    switch_flag = 1;
                                }
                            } catch (Exception exception) {
                                Toast.makeText(getApplicationContext(), "Invalid Operand Entered.", Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                    }
                }else{
                    displayedAnswer = "-";
                    txt_input.setText(displayedAnswer);
                    switch_flag = 0;
                    minors_flag = 1;
                }
            }
        });


        btn_dot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String displayedAnswer;
                String s = txt_input.getText().toString();
                if (s.contains(".")){
                    Toast.makeText(getApplicationContext(), "Already a float", Toast.LENGTH_LONG).show();
                }else{

                    if(switch_flag==0){


                        if(minors_flag==1){
                            Log.d("MainActivity", "!@3");
                            displayedAnswer = "-0.";
                            minors_flag =0;
                            txt_input.setText(displayedAnswer);
                        }else {
                            Double value = Double.valueOf(s);
                            if (value == 0.0) {
                                displayedAnswer = "0.";
                                txt_input.setText(displayedAnswer);
                            } else {
                                displayedAnswer = s + ".";
                                txt_input.setText(displayedAnswer);
                            }
                        }
                    }else{
                        displayedAnswer = "0.";
                        txt_input.setText(displayedAnswer);
                        switch_flag = 0;
                    }
                }
            }
        });

        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String displayedAnswer;
                if(switch_flag==0){
                    String s = txt_input.getText().toString();
                    Log.d("MainActivity", s);
                    if(minors_flag==1){
                        Log.d("MainActivity", "!@3");
                        displayedAnswer = "-";
                        minors_flag =1;
                        txt_input.setText(displayedAnswer);
                    }else {
                        Double value = Double.valueOf(s);
                        if (value == 0.0) {
                            displayedAnswer = "0";
                            txt_input.setText(displayedAnswer);
                        } else {
                            displayedAnswer = s + "0";
                            txt_input.setText(displayedAnswer);
                        }
                    }
                }else{
                    displayedAnswer = "0";
                    txt_input.setText(displayedAnswer);
                    switch_flag = 0;
                }
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String displayedAnswer;
                if(switch_flag==0){
                    String s = txt_input.getText().toString();
                    Log.d("MainActivity", s);
                    if(minors_flag==1){
                        Log.d("MainActivity", "!@3");
                        displayedAnswer = "-1";
                        minors_flag =0;
                        txt_input.setText(displayedAnswer);
                    }else {
                        Double value = Double.valueOf(s);
                        if (value == 0.0) {
                            displayedAnswer = "1";
                            txt_input.setText(displayedAnswer);
                        } else {
                            displayedAnswer = s + "1";
                            txt_input.setText(displayedAnswer);
                        }
                    }
                }else{
                    displayedAnswer = "1";
                    txt_input.setText(displayedAnswer);
                    switch_flag = 0;
                }
            }
        });


        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String displayedAnswer;
                if(switch_flag==0){
                    String s = txt_input.getText().toString();
                    Log.d("MainActivity", s);
                    if(minors_flag==1){
                        Log.d("MainActivity", "!@3");
                        displayedAnswer = "-2";
                        minors_flag =0;
                        txt_input.setText(displayedAnswer);
                    }else {
                        Double value = Double.valueOf(s);
                        if (value == 0.0) {
                            displayedAnswer = "2";
                            txt_input.setText(displayedAnswer);
                        } else {
                            displayedAnswer = s + "2";
                            txt_input.setText(displayedAnswer);
                        }
                    }
                }else{
                    displayedAnswer = "2";
                    txt_input.setText(displayedAnswer);
                    switch_flag = 0;
                }
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String displayedAnswer;
                if(switch_flag==0){
                    String s = txt_input.getText().toString();
                    Log.d("MainActivity", s);
                    if(minors_flag==1){
                        Log.d("MainActivity", "!@3");
                        displayedAnswer = "-3";
                        minors_flag =0;
                        txt_input.setText(displayedAnswer);
                    }else {
                        Double value = Double.valueOf(s);
                        if (value == 0.0) {
                            displayedAnswer = "3";
                            txt_input.setText(displayedAnswer);
                        } else {
                            displayedAnswer = s + "3";
                            txt_input.setText(displayedAnswer);
                        }
                    }
                }else{
                    displayedAnswer = "3";
                    txt_input.setText(displayedAnswer);
                    switch_flag = 0;
                }
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String displayedAnswer;
                if(switch_flag==0){
                    String s = txt_input.getText().toString();
                    //Log.d("MainActivity", s);
                    if(minors_flag==1){
                        displayedAnswer = "-4";
                        minors_flag =0;
                        txt_input.setText(displayedAnswer);
                    }else {
                        Double value = Double.valueOf(s);
                        if (value == 0.0) {
                            displayedAnswer = "4";
                            txt_input.setText(displayedAnswer);
                        } else {
                            displayedAnswer = s + "4";
                            txt_input.setText(displayedAnswer);
                        }
                    }
                }else{
                    displayedAnswer = "4";
                    txt_input.setText(displayedAnswer);
                    switch_flag = 0;
                }
            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String displayedAnswer;
                if(switch_flag==0){
                    String s = txt_input.getText().toString();
                    //Log.d("MainActivity", s);
                    if(minors_flag==1){
                        displayedAnswer = "-5";
                        minors_flag =0;
                        txt_input.setText(displayedAnswer);
                    }else {
                        Double value = Double.valueOf(s);
                        if (value == 0.0) {
                            displayedAnswer = "5";
                            txt_input.setText(displayedAnswer);
                        } else {
                            displayedAnswer = s + "5";
                            txt_input.setText(displayedAnswer);
                        }
                    }
                }else{
                    displayedAnswer = "5";
                    txt_input.setText(displayedAnswer);
                    switch_flag = 0;
                }
            }
        });

        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String displayedAnswer;
                if(switch_flag==0){
                    String s = txt_input.getText().toString();
                    //Log.d("MainActivity", s);
                    if(minors_flag==1){
                        displayedAnswer = "-6";
                        minors_flag =0;
                        txt_input.setText(displayedAnswer);
                    }else {
                        Double value = Double.valueOf(s);
                        if (value == 0.0) {
                            displayedAnswer = "6";
                            txt_input.setText(displayedAnswer);
                        } else {
                            displayedAnswer = s + "6";
                            txt_input.setText(displayedAnswer);
                        }
                    }
                }else{
                    displayedAnswer = "6";
                    txt_input.setText(displayedAnswer);
                    switch_flag = 0;
                }
            }
        });

        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String displayedAnswer;
                if(switch_flag==0){
                    String s = txt_input.getText().toString();
                    //Log.d("MainActivity", s);
                    if(minors_flag==1){
                        displayedAnswer = "-7";
                        minors_flag =0;
                        txt_input.setText(displayedAnswer);
                    }else {
                        Double value = Double.valueOf(s);
                        if (value == 0.0) {
                            displayedAnswer = "7";
                            txt_input.setText(displayedAnswer);
                        } else {
                            displayedAnswer = s + "7";
                            txt_input.setText(displayedAnswer);
                        }
                    }
                }else{
                    displayedAnswer = "7";
                    txt_input.setText(displayedAnswer);
                    switch_flag = 0;
                }
            }
        });

        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String displayedAnswer;
                if(switch_flag==0){
                    String s = txt_input.getText().toString();
                    //Log.d("MainActivity", s);
                    if(minors_flag==1){
                        displayedAnswer = "-8";
                        minors_flag =0;
                        txt_input.setText(displayedAnswer);
                    }else {
                        Double value = Double.valueOf(s);
                        if (value == 0.0) {
                            displayedAnswer = "8";
                            txt_input.setText(displayedAnswer);
                        } else {
                            displayedAnswer = s + "8";
                            txt_input.setText(displayedAnswer);
                        }
                    }
                }else{
                    displayedAnswer = "8";
                    txt_input.setText(displayedAnswer);
                    switch_flag = 0;
                }
            }
        });

        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String displayedAnswer;
                if(switch_flag==0){
                    String s = txt_input.getText().toString();
                    //Log.d("MainActivity", s);
                    if(minors_flag==1){
                        displayedAnswer = "-9";
                        minors_flag =0;
                        txt_input.setText(displayedAnswer);
                    }else {
                        Double value = Double.valueOf(s);
                        if (value == 0.0) {
                            displayedAnswer = "9";
                            txt_input.setText(displayedAnswer);
                        } else {
                            displayedAnswer = s + "9";
                            txt_input.setText(displayedAnswer);
                        }
                    }
                }else{
                    displayedAnswer = "9";
                    txt_input.setText(displayedAnswer);
                    switch_flag = 0;
                }
            }
        });





        }
    }