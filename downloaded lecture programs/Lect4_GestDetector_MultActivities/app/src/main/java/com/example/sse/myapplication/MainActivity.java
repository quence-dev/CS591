package com.example.sse.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    Button btnNext, btnLeft, btnCenter, btnBottom;
    EditText edtMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnNext = (Button) findViewById(R.id.btnNext);
        btnLeft = (Button) findViewById(R.id.btnLeft);
        btnCenter = (Button) findViewById(R.id.btnCenter);
        btnBottom = (Button) findViewById(R.id.btnBottom);

        edtMessage = (EditText) findViewById(R.id.edtMessage);


    btnLeft.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          edtMessage.setText("On Click Listener Called." );
        }
    });

        btnLeft.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                edtMessage.setText("On LONG Click Listener Called." );
                return false;
            }
        });


     btnNext.setOnLongClickListener(new View.OnLongClickListener() {
         @Override
         public boolean onLongClick(View v) {
             Intent NextScreen = new Intent("com.example.sse.myapplication.Main2Activity");
             NextScreen.putExtra("SourceEvent", " OnLongClick");
             startActivity(NextScreen);

             Toast.makeText(getBaseContext(), "OnLongClick Toast, about to return TRUE", Toast.LENGTH_LONG).show();

             return true;
         }
     });

    btnNext.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent NextScreen = new Intent("com.example.sse.myapplication.Main2Activity");
            NextScreen.putExtra("SourceEvent", " OnClick");  //Ref. http://stackoverflow.com/questions/17286942/passing-string-to-another-activity-without-using-intent-extras-in-android
            startActivity(NextScreen);
            Toast.makeText(getBaseContext(), "OnClick Toast", Toast.LENGTH_LONG).show();
        }





    });



    }


}
