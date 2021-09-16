package com.example.sse.lect9requestpermission2;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CALL = 9999;   //this is our dye, so we can track "who" asked for "which" permissions.

    private EditText edtPhoneNumber;
    private Button btnCall;
    private String phoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        edtPhoneNumber = (EditText) findViewById(R.id.edtPhoneNumber);
        btnCall = (Button) findViewById(R.id.btnCall2);


        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CallNumber();
            }
        });
    }

    private void CallNumber() {
        phoneNumber = edtPhoneNumber.getText().toString().trim();  //trim removes whitespace

        //Code hunk1, this is good, but the first time we try to call, we get prompted then nothing happens.
        //second time, if user granted, then call goes thru.
        if (ContextCompat.checkSelfPermission(getBaseContext(), Manifest.permission.CALL_PHONE) != PackageManager .PERMISSION_GRANTED) {   //if we don't have permission, ask for it.
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.CALL_PHONE}, REQUEST_CALL );
        }
        else {
            String dialURI = "tel:" + phoneNumber;
            startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dialURI)));  //1 karate chop, to create the intent and start the activity.
        }
        //End Code Hunk1

    }

    //Code Hunk2, this is how we can ensure that the phone call goes, through after the user requests permissions.
    //it's also how we can let the user no that permissions might be absolutely necessary, ie, give them feedback if they deny.


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CALL) {   //identifying which request was made, this CallBack handles ALL requests made from this activity.
           if (grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {   //grantResults comes back as an array, because more than one permission can be requested at once.
//              String dialURI = "tel:" + phoneNumber;                           //duplicate code from the else portion above, this should be refactored into a single method. Why?
//              startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dialURI)));
           }
           else {
               Toast.makeText(getBaseContext(), "USER DENIED PHONE CALL", Toast.LENGTH_LONG).show();  //let the user know why they can't call out.
           }
        }
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
