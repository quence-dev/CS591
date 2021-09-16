package com.example.sse.startactivityforresult_sse;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.VideoView;

import java.util.ArrayList;
import java.util.Locale;


public class MainActivity extends AppCompatActivity {


    private Button btnClearText;
    private ImageButton btnTakePicture;
    private ImageButton btnSpeechToText;
    private ImageButton btnTakeVideo;

    private EditText edtSpeechToText;
    private ImageView imgPhoto;
    private VideoView vidVideo;

//*** Think of these flags as tracer dye
//*** We will use these later when identifying which Activity returned a result.
//Question:  There is an implication here, what is it?
//Answer: _____

    static final int TAKE_PHOTO = 9999;  //just a flag that we will use to track the result of an intent later.
    static final int TAKE_VIDEO = 9998;  //just a flag that we will use to track the result of an intent later.
    static final int SPEECH_TO_TEXT = 9997;  //just a flag that we will use to track the result of an intent later.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//*** Boiler plate...
        btnClearText = (Button) findViewById(R.id.btnClearText);
        btnTakePicture = (ImageButton) findViewById(R.id.btnTakePicture);
        btnSpeechToText = (ImageButton) findViewById(R.id.btnSpeechToText);
        btnTakeVideo = (ImageButton) findViewById(R.id.btnTakeVideo);
        edtSpeechToText = (EditText) findViewById(R.id.edtSpeechToText);
        imgPhoto = (ImageView) findViewById(R.id.imgPhoto);
        vidVideo = (VideoView) findViewById(R.id.vidVideo);

        btnClearText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtSpeechToText.setText("");  //clearing text in the Speech to text box.
            }
        });

        btnSpeechToText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//*** Syntax for Speech Recognition intent.
                Intent i = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);  //Required extra.  Specifying Free Form Speech.  Could also specify "web search speech". LANGUAGE_MODEL_WEB_SEARCH
//*** Very easy to switch speech transcription languages!
//                i.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());  //Could also specify other languages, could even code to look at GPS Coordinates to identify the "best" language to recognize.
                //  i.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
                //  i.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "fr_FR");
//                  i.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "zh-cmn");   //chinese doesn't work, ref: http://stackoverflow.com/questions/27194988/speech-to-text-api-other-language-android
//                  i.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "en-us");
                    i.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "es");         //Spanish
 //               i.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "ar-EG");      //Arabic

//*** Start our Activity, and inject the tracer dye... :)

                i.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak Now Please...");  //Don't forget to prompt with a friendly message. :)
                try {
                    startActivityForResult(i, SPEECH_TO_TEXT);  //should catch device exception...
                }
                catch(ActivityNotFoundException e) {
                        Toast.makeText(MainActivity.this, "Device Not Supported " + e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });

btnTakePicture.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(i, TAKE_PHOTO);  //again, TAKE_PHOTO is just our dye

    }
});

        btnTakeVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                startActivityForResult(i, TAKE_VIDEO);   //video will require additional manifest permissions...
                //For now just using default folder.
                //Specify Destination?  Good Ref: http://androidexample.com/Camera_Video_Capture_And_Save_On_SDCard_-_Android_Example/index.php?view=article_discription&aid=123
            }
        });
    }


//onActivityResult is a call back from StartActivityForResult.
//Question: What's a callback again?
//Answer: _____

//Question: In this project we have multiple intents, how does onActivityResult "know" which one came back?
//Answer: _______

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

//*** Step 1: verify that we got something back that was valid.
    if (!(resultCode == RESULT_OK))
    {
        Toast.makeText(MainActivity.this, "Start Activity for Result Failed.  Does your device support this feature?", Toast.LENGTH_LONG).show();
        return;
    }
//*** Step 2: what did we get, check our "dye", then check the data.
        switch(requestCode){
            case SPEECH_TO_TEXT:    //these cases represent our dye
                ArrayList<String> strData = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);   //This is our data, in this case the intent will contain a String.
                edtSpeechToText.setText(strData.get(0));  //the first item contains the text.
                break;
            case TAKE_PHOTO:
                Bundle bundleData = data.getExtras();           //images are stored in a bundle wrapped within the intent...
                Bitmap Photo = (Bitmap)bundleData.get("data");  //the bundle key is "data".  Requires some reading of documentation to remember. :)
                imgPhoto.setImageBitmap(Photo);
                break;
            case TAKE_VIDEO:
                edtSpeechToText.setText("Video File : " +data.getData());
                Toast.makeText(MainActivity.this, "Video Location - " + data.getData(), Toast.LENGTH_LONG).show();
                //Exercise, load the video into the video view component and play.
                              //Important: There is sometimes a conflict with other Apps - you may need to disable features in Google (Google voice) or FB, eesh!
                              //Ref: https://forum.xda-developers.com/droid-ultra/help/able-to-start-video-capture-t2830843
                              //Ref: https://androidforums.com/threads/unable-to-capture-media.856845/
                              //Ref: http://forums.androidcentral.com/ask-question/402380-unable-capture-media.html
                break;
        }



    }

//helper routines...
//do we even have a camera or mic??  Better check... before starting the Activity.
//Question: Where would you put this code?
//Answer: _______

    boolean isCameraAvailable(){
        return getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY);  //just another friendly neighborhood manager
    }

    boolean isMicAvailable(){
        return getPackageManager().hasSystemFeature(PackageManager.FEATURE_MICROPHONE);  //just another friendly neighborhood manager
    }

}
