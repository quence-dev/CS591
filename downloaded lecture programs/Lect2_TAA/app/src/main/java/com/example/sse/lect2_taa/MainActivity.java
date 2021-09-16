package com.example.sse.lect2_taa;

import androidx.appcompat.app.AppCompatActivity;

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

    private Button btnCalculate;
    private EditText txtOp1;
    private EditText txtOp2;
    private TextView lblAnswer;
    private Spinner spinner;
    private char Operation;




private static final String MyFlag = "LFA";  //this will be our trail of breadcrumbs for logging events.


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(MyFlag, "OnCreate just called!");
        super.onCreate(savedInstanceState);
//        this.getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        btnCalculate = (Button) findViewById(R.id.btnCalculate);
        txtOp1 = (EditText) findViewById(R.id.txtOp1);
        txtOp2 = (EditText) findViewById(R.id.txtOp2);
        lblAnswer = (TextView) findViewById(R.id.lblAnswer);
        spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);

        Operation = '+';

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Double Op1, Op2, Answer=0.0;

    try {
      Op1 = Double.parseDouble(txtOp1.getText().toString());
      Op2 = Double.parseDouble(txtOp2.getText().toString());

      switch(Operation){
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
      }

      if (Answer.equals(NaN))
          lblAnswer.setText("Cannot divide by 0");
      else
        lblAnswer.setText(Answer.toString());
    }
    catch (Exception exception) {
        Toast.makeText(getApplicationContext(),"Invalid Operand Entered.",Toast.LENGTH_LONG).show();
    }

            }
        });

    }


    @Override
    protected void onPause() {
        Log.i(MyFlag, "OnPause just called!");
        super.onPause();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long l) {
        Operation = parent.getItemAtPosition(pos).toString().charAt(0);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        Operation = '+';
    }
}
