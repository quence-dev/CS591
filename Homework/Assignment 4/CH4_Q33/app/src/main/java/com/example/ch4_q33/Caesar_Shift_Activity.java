package com.example.ch4_q33;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Caesar_Shift_Activity extends AppCompatActivity {
    private int offset = 0;
    private String offset_input;
    private Button shiftbtn;
    private EditText shift_input;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ceasar_shift_activity);


        shiftbtn = (Button) findViewById(R.id.set_shiftbtn);
        shift_input = (EditText) findViewById(R.id.shift_input);

        shiftbtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                if(shift_input.getText().toString().equals("")){
                    Toast.makeText(getBaseContext(), "Please input a valid number",Toast.LENGTH_LONG).show();
                } else {
                    offset_input = shift_input.getText().toString();
                    offset = Integer.parseInt(offset_input) % 26;
                    Intent Encryption = new Intent("android.intent.action.Secondary_MAIN");
                    Encryption.putExtra("Offset", offset);  //Ref. http://stackoverflow.com/questions/17286942/passing-string-to-another-activity-without-using-intent-extras-in-android
                    startActivity(Encryption);
                }
            }
        });
    }
}
