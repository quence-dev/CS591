package com.example.customanimation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btnAnimate;
    private Animation anim_custom, anim1, anim2, anim3,
            anim4, anim5, anim6, anim7, anim8, anim9, anim10;
    // private AnimationSet a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAnimate = (Button) findViewById(R.id.btnAnimate);
        anim_custom = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_custom);

//        anim1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim1);
//        anim2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim2);
//        anim3 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim3);
//        anim4 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim4);
//        anim5 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim5);
//        anim6 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim6);
//        anim7 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim7);
//        anim8 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim8);
//        anim9 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim9);
//        anim10 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim10);

        btnAnimate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnAnimate.startAnimation(anim_custom);
                //btnAnimate.startAnimation(a);

                Toast.makeText(getApplicationContext(), "Animation started.",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}