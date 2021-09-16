package com.example.sse.pendingintentsandnotifications_sse;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText edtMomsPhoneNo;
    private Button btnSetPendingIntent;
    private Button btnSetNotification;
    private EditText edtSeconds;
    private Intent notificationIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSetPendingIntent = (Button) findViewById(R.id.btnSetPendingIntent);
        btnSetNotification = (Button) findViewById(R.id.btnSetNotification);
        edtMomsPhoneNo = (EditText) findViewById(R.id.edtMomsPhoneNo);
        edtSeconds = (EditText) findViewById(R.id.edtSeconds);

        btnSetPendingIntent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setupPendingIntent();
            }
        });

        btnSetNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setupNotification();
            }
        });

    }

//GOAL: USE THE ALARM MANAGER TO MAKE A PHONE CALL AT SOME LATER TIME.
//EZ AS 1-2-3
    protected void setupPendingIntent() {

        int seconds = Integer.parseInt(edtSeconds.getText().toString());  //how long to wait
        int milliseconds = seconds * 1000;

//STEP 1: Create an Intent.  Could be anything, eg, opening a second activity, boring... going to make a phone call instead.
//        Intent intent = new Intent(this, ActivityTwo.class);

//Phone call code should look familiar, ref: minimaps
// We won't actually call mom this time, just create the intent and wrap it in a PendingIntent..
        String phoneNo = edtMomsPhoneNo.getText().toString();
        Intent phoneCallMom = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNo));
//        startActivity(phoneCallMom);  //This is how we could call mom now, but that's not the goal.

//STEP 2. Create the pending intent and WRAP our intent, provide some dye to track it later.
        PendingIntent pendingIntent =
                PendingIntent.getActivity(   this,      9999,        phoneCallMom,        PendingIntent.FLAG_CANCEL_CURRENT);
                                 //PARMS: -Context-, -request_code- , -intent-,      - destroy the original before creating new-

//aside, could also just call the PendingIntent right now, by invoking send() method.
// Why would this be pointless?   A: Would just be the same as starting the activity right now.
        //try {
        //    pendingIntent.send();
        //} catch (PendingIntent.CanceledException e) {
        //    e.printStackTrace();
        //}

//STEP 3. Get a reference to the Android Alarm Manager service and schedule it to go off after GUI specified time
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);  //just another manager, this one sets an alarm.
        alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + (milliseconds), pendingIntent);  //When the alarm manager runs it will be running as the user who initiated it.

        Toast.makeText(this, "Will call mom in " + seconds + " seconds", Toast.LENGTH_SHORT).show();  //Let the user know the plan

// ********* END PENDING INTENT CODE ********* //
}

    protected void setupNotification()
    {
        // ********* START NOTIFICATION BUILDER CODE ********* //

        //Creating a notification is a multi part process.
        //need to create the notification, then build it, then get a reference to the notification manager,
        //then use that to spawn the notification.

//1a. Setting up a Notification Builder (not the actual notification yet).  This will define what the notification looks like.
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this);

        notificationBuilder.setSmallIcon(R.drawable.turkey2);  //Notification Builder comes with some standard fields, eg, SmallIcon, Title, Text.
        notificationBuilder.setColor(android.R.color.holo_orange_light);
        notificationBuilder.setContentTitle("HAPPY THANKSGIVING!!");
        notificationBuilder.setContentText("Hey, why not try lamb for Thanksgiving?");
        notificationBuilder.setAutoCancel(true);  //we want notification to go away once we click it.

//1b. setting up what we want to do when notification is clicked.

        notificationIntent = new Intent(this, ActivityTwo.class);
        PendingIntent aPendingIntent = PendingIntent.getActivity(this, 9998, notificationIntent, PendingIntent.FLAG_CANCEL_CURRENT);  //parms: context, request_code, intent, destroy the original before creating a new.
        notificationBuilder.setContentIntent(aPendingIntent);  //this identifies what the notification will do if you click on it.

//2. Building a Notification - The previous was just setup, now we "build" our notification.
        Notification aNotification = notificationBuilder.build();   //using the builder object we build ourselves a Notification.

//3. Issue the notification using the Notification Manager, yep, another android manager saves the day.
        NotificationManagerCompat.from(this).notify(9997, aNotification);  //Parm List: Who From, requestcode, the notification to be displayed.


        // ********* END NOTIFICATION BUILDER CODE ********* //
    }

}
