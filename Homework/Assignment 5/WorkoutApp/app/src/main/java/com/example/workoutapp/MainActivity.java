package com.example.workoutapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private String TAG = "SIG_SHAKE";

    private float lastX, lastY, lastZ;  //old coordinate positions from accelerometer, needed to calculate delta.
    private float acceleration;
    private float currentAcceleration;
    private float lastAcceleration;

    private long workoutStart;

    private boolean hasFlash;
    private boolean runBlink;
    private boolean workoutRunning;

    private ListView lvMenu;
    private Button buttonStart;
    private Button buttonStop;
    private TextView SIGSHAKE_tracker;

    private int NUMBEROFSTEPS;
    private static int SIGNIFICANT_SHAKE;
    private int lastPlayed;
    private int stepThreshold;

    MediaPlayer mp;

    @Override
    protected void onSaveInstanceState(Bundle savedInstaceState) {
        super.onSaveInstanceState(savedInstaceState);
        System.out.println("Saving instance state with " + NUMBEROFSTEPS + " steps");
        savedInstaceState.putInt("NUMBEROFSTEPS", NUMBEROFSTEPS);
        savedInstaceState.putBoolean("workoutRunning", workoutRunning);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
         }
    return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final SensorManager sensorManager = (SensorManager) this.getSystemService(
                Context.SENSOR_SERVICE);   //The last param specifies the type of Sensor we want to monitor

        //Now that we have a Sensor Handle, let's start "listening" for movement (accelerometer).
        //3 parms, The Listener, Sensor Type (accelerometer), and Sampling Frequency.

        setContentView(R.layout.activity_main);

        hasFlash = getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);

        acceleration = 0.00f;                                         //Initializing Acceleration data.
        currentAcceleration = SensorManager.GRAVITY_EARTH;            //We live on Earth.
        lastAcceleration = SensorManager.GRAVITY_EARTH;               //Ctrl-Click to see where else we could use our phone.

        if (savedInstanceState != null) {
            NUMBEROFSTEPS = savedInstanceState.getInt("NUMBEROFSTEPS");
            System.out.println("Restoring instance state with " + NUMBEROFSTEPS + " steps");
        } else {
            System.out.println("savedinstancestate is null");
            NUMBEROFSTEPS = 0;
        }

        if (workoutRunning != false) {
            workoutRunning = savedInstanceState.getBoolean("workoutRunning");
        } else {
            workoutRunning = false;
        }

        lvMenu = (ListView) findViewById(R.id.lvMenu);
        buttonStart = (Button) findViewById(R.id.buttonStart);
        buttonStop = (Button) findViewById(R.id.buttonStop);
        SIGSHAKE_tracker = (TextView) findViewById(R.id.SIGSHAKE_tracker);
        SIGSHAKE_tracker.setText("Number of Steps: " + NUMBEROFSTEPS);
        runBlink = false;

        if(workoutRunning) {
            buttonStart.setEnabled(false);
            buttonStop.setEnabled(true);
        } else {
            buttonStart.setEnabled(false);
            buttonStop.setEnabled(false);
        }

        t1.start();

        final String[] Difficulty = {"Easy", "Medium", "Hard"};

        ArrayAdapter DifficultyListAdapter = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_list_item_1,
                Difficulty);

        lvMenu.setAdapter(DifficultyListAdapter);

        lvMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String difficulty;
                // Animal = Animals[position];  //Note, This is much simpler,
                //Q (for above): Why is referring to the original array less preferable then using CallBack Parms (below)? A: _____________
                difficulty = String.valueOf(parent.getItemAtPosition(position));  //Parent refers to the parent of the item, the ListView.  position is the index of the item clicked.

                switch (difficulty) {
                    case "Easy":
                        mp = MediaPlayer.create(MainActivity.this, R.raw.star_wars_main_theme);
                        lastPlayed = R.raw.star_wars_main_theme;
                        buttonStart.setEnabled(true);
                        stepThreshold = 30;
                        SIGNIFICANT_SHAKE = 10000;
                        break;
                    case "Medium":
                        mp = MediaPlayer.create(MainActivity.this, R.raw.superman_main_theme);
                        lastPlayed = R.raw.superman_main_theme;
                        buttonStart.setEnabled(true);
                        stepThreshold = 50;
                        SIGNIFICANT_SHAKE = 15000;
                        break;
                    case "Hard":
                        mp = MediaPlayer.create(MainActivity.this, R.raw.rocky_theme_song);
                        lastPlayed = R.raw.rocky_theme_song;
                        buttonStart.setEnabled(true);
                        stepThreshold = 70;
                        SIGNIFICANT_SHAKE = 20000;
                        break;
                }

            }
        });

        buttonStart.setOnClickListener(new AdapterView.OnClickListener(){

            @Override
            public void onClick(View view) {
                buttonStop.setEnabled(true);
                sensorManager.registerListener(sensorEventListener,
                        sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                        SensorManager.SENSOR_DELAY_NORMAL);
                workoutStart = System.currentTimeMillis();
            }
        });

        buttonStop.setOnClickListener(new AdapterView.OnClickListener() {

            @Override
            public void onClick(View view) {
                disableAccelerometerListening();
                runBlink = false;
                buttonStop.setEnabled(false);
                mp.stop();
                mp = MediaPlayer.create(MainActivity.this, lastPlayed);
                NUMBEROFSTEPS = 0;
                SIGSHAKE_tracker.setText("Number of Steps: " + NUMBEROFSTEPS);
            }
        });
    }

    private final SensorEventListener sensorEventListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            // get x, y, and z values for the SensorEvent
            //each time the event fires, we have access to three dimensions.
            //compares these values to previous values to determine how "fast"
            // the device was shaken.
            //Ref: http://developer.android.com/reference/android/hardware/SensorEvent.html

            float x = event.values[0];   //obtaining the latest sensor data.
            float y = event.values[1];   //sort of ugly, but this is how data is captured.
            float z = event.values[2];

            // save previous acceleration value
            lastAcceleration = currentAcceleration;

            // calculate the current acceleration
            currentAcceleration = x * x + y * y + z * z;   //This is a simplified calculation, to be real we would need time and a square root.

            // calculate the change in acceleration        //Also simplified, but good enough to determine random shaking.
            acceleration = currentAcceleration *  (currentAcceleration - lastAcceleration);

            // if the acceleration is above a certain threshold
            if (acceleration > SIGNIFICANT_SHAKE) {
                System.out.println("significant shake");
                Log.e(TAG, "delta x = " + (x-lastX));
                Log.e(TAG, "delta y = " + (y-lastY));
                Log.e(TAG, "delta z = " + (z-lastZ));

                Context context = getApplicationContext();
                int duration = Toast.LENGTH_SHORT;

                //Steps tracker
                NUMBEROFSTEPS = NUMBEROFSTEPS + 1;
                if(NUMBEROFSTEPS == 10) {
                    if(hasFlash) {
                        runBlink = true;
                    }
                    else {
                        Toast.makeText(MainActivity.this, "No flashlight on your device", Toast.LENGTH_SHORT).show();
                    }
                } else if(NUMBEROFSTEPS == stepThreshold) {
                    mp.start();

                } else if(NUMBEROFSTEPS == 100) {
                    disableAccelerometerListening();
                    runBlink = false;
                    buttonStop.setEnabled(false);
                    mp.stop();
                    mp = MediaPlayer.create(MainActivity.this, lastPlayed);
                    NUMBEROFSTEPS = 0;
                    long workoutEnd = System.currentTimeMillis();
                    long workoutTime = workoutEnd - workoutStart;
                    double workoutTimeSeconds = workoutTime / 1000.0;
                    CharSequence text = "You worked out for " + workoutTimeSeconds + " seconds.";
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
                SIGSHAKE_tracker.setText("Number of Steps: " + NUMBEROFSTEPS);
            }

            lastX = x;
            lastY = y;
            lastZ = z;
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

    private void disableAccelerometerListening() {

//Disabling Sensor Event Listener is two step process.
        //1. Retrieve SensorManager Reference from the activity.
        //2. call unregisterListener to stop listening for sensor events
        //THis will prevent interruptions of other Apps and save battery.

        // get the SensorManager
        SensorManager sensorManager =
                (SensorManager) this.getSystemService(
                        Context.SENSOR_SERVICE);

        // stop listening for accelerometer events
        sensorManager.unregisterListener(sensorEventListener,
                sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER));
    }

    protected void onStop() {
        Log.i(TAG, "onStop Triggered.");
        super.onStop();
    }

    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart Triggered.");
    }

    Thread t1 = new Thread() {
        @RequiresApi(api = Build.VERSION_CODES.M)
        public void run() {
            while(true) {
                if(runBlink) {
                    flashOn();
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    flashOff();
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    };

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void flashOn(){
        CameraManager cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        try {
            String cameraID = cameraManager.getCameraIdList()[0];
            cameraManager.setTorchMode(cameraID, true);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void flashOff(){
        CameraManager cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        try{
            String cameraID = cameraManager.getCameraIdList()[0];
            cameraManager.setTorchMode(cameraID, false);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }

    }

}