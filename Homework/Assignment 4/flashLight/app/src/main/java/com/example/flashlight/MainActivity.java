package com.example.flashlight;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.gesture.Gesture;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.SearchView;
import android.widget.Switch;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener {
    private Switch flashSwitch;
    private SearchView enterAction;
    private boolean isOn = false;
    private boolean hasFlash;
    private boolean isEnabled =false;
    private static final int CAMERA_REQUEST = 50;
    private GestureDetector gstDetect;


    @Override
    public boolean onTouchEvent(MotionEvent e) {
        this.gstDetect.onTouchEvent(e);
        return super.onTouchEvent(e);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        flashSwitch = (Switch) findViewById(R.id.Switch);
        enterAction = (SearchView) findViewById(R.id.searchAction);
        hasFlash = getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
        gstDetect= new GestureDetector(this, this);

        enterAction.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public boolean onQueryTextSubmit(String query) {
                if(!isEnabled){
                    ActivityCompat.requestPermissions(MainActivity.this,new String[] {Manifest.permission.CAMERA}, CAMERA_REQUEST);
                }
                else if(hasFlash) {
                    if (isOn != true && enterAction.getQuery().toString().equals("ON")) {
                        //turn on flash light
                        flashOn();
                        isOn = true;
                        flashSwitch.setChecked(true);
                    } else if (isOn == true && enterAction.getQuery().toString().equals("ON")) {
                        //flash already on
                        Toast.makeText(MainActivity.this, "flash already on", Toast.LENGTH_SHORT).show();
                    } else if (isOn != true && enterAction.getQuery().toString().equals("OFF")) {
                        //flash already off
                        Toast.makeText(MainActivity.this, "flash already off", Toast.LENGTH_SHORT).show();
                    } else if (isOn == true && enterAction.getQuery().toString().equals("OFF")) {
                        //turn off flashlight
                        flashOff();
                        isOn = false;
                        flashSwitch.setChecked(false);
                    } else {
                        //toast "Enter valid Action"
                        Toast.makeText(MainActivity.this, "Enter valid Action", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(MainActivity.this, "No flashlight on your device", Toast.LENGTH_SHORT).show();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        flashSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isEnabled){
                    ActivityCompat.requestPermissions(MainActivity.this,new String[] {Manifest.permission.CAMERA}, CAMERA_REQUEST);
                }
                if(hasFlash) {
                    if (flashSwitch.isChecked()) {
                        flashOn();
                        isOn = true;
                    } else if (!flashSwitch.isChecked()) {
                        flashOff();
                        isOn = false;
                    }
                }
                else {
                    if (flashSwitch.isChecked()) {
                        flashSwitch.setChecked(false);
                        Toast.makeText(MainActivity.this, "No flashlight on your device", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        flashSwitch.setChecked(true);
                        Toast.makeText(MainActivity.this, "No flashlight on your device", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
    private void flashOn(){
        CameraManager cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        try{
            String cameraID = cameraManager.getCameraIdList()[0];
            cameraManager.setTorchMode(cameraID, true);
            isOn =true;
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }

    }

    private void flashOff(){
        CameraManager cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        try{
            String cameraID = cameraManager.getCameraIdList()[0];
            cameraManager.setTorchMode(cameraID, false);
            isOn =false;
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch(requestCode) {
            case CAMERA_REQUEST :
                if (grantResults.length > 0  &&  grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    isEnabled = true;
                } else {
                    Toast.makeText(MainActivity.this, "Permission Denied for the Camera",
                            Toast.LENGTH_SHORT).show();
                    isEnabled = false;
                }
                break;
        }
    }


    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

        float velocity = 1000;
        if (velocityX > velocity) {
                if(isOn) {
                    Toast.makeText(MainActivity.this, "Flashlight already on", Toast.LENGTH_SHORT).show();
                }
                else{
                    if(hasFlash) {
                        isOn = true;
                        flashSwitch.setChecked(true);
                        flashOn();
                    }
                    else{
                        Toast.makeText(MainActivity.this, "No flashlight on your device", Toast.LENGTH_SHORT).show();
                    }
                }
        }
        else if (velocityX > -velocity) {
            if(!isOn) {
                Toast.makeText(MainActivity.this, "Flashlight already off", Toast.LENGTH_SHORT).show();
            }
            else{
                if(hasFlash) {
                    isOn = false;
                    flashSwitch.setChecked(false);
                    flashOff();
                }
                else{
                    Toast.makeText(MainActivity.this, "No flashlight on your device", Toast.LENGTH_SHORT).show();
                }
            }
        }
        return true;
    }
}