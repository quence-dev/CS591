package com.example.simplecalculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatCallback;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import static java.lang.Double.NaN;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private EditText edtOp1;
    private EditText edtOp2;
    private Spinner spinner;
    private Button btnCalc;
    private TextView txtAnswer;

    private char Operation;

    private static final String LOGTAG = "Spencer";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(LOGTAG, "OnCreate called!");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //capture object instances
        edtOp1 = (EditText) findViewById(R.id.edtOp1);
        edtOp2 = (EditText) findViewById(R.id.edtOp2);
        btnCalc = (Button) findViewById(R.id.btnCalc);
        txtAnswer = (TextView) findViewById(R.id.txtAnswer);

        spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);

        Operation = '+';


        btnCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(LOGTAG, "OnClick activated");
                Double Op1, Op2, Answer = 0.0;


                try {
                    Op1 = Double.parseDouble(edtOp1.getText().toString());
                    Op2 = Double.parseDouble(edtOp2.getText().toString());

                    switch (Operation){
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
                        case '%':
                            Answer = Op1 % Op2;
                            break;
                        case '^':
                            Answer = Math.pow(Op1,Op2);
                            break;
                    }

                    if (Answer.equals(NaN)){
                        txtAnswer.setText("Cannot divide by 0.");
                    }
                    else {
                        txtAnswer.setText(Answer.toString());
                    }
                }
                catch (Exception e){
                    Toast.makeText(getApplicationContext(), "Invalid operand entered.", Toast.LENGTH_LONG).show();
                }
            }
        });

    }


    @Override
    protected void onPause() {
        Log.i(LOGTAG, "OnPause just called!");
        super.onPause();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Operation = parent.getItemAtPosition(position).toString().charAt(0);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Operation = '+';
    }
}