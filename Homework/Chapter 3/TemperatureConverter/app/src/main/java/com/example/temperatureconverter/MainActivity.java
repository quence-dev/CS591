package com.example.temperatureconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private SeekBar fahSeekBar;
    private SeekBar celSeekBar;
    private TextView fahNum;
    private TextView celNum;
    private TextView interestingText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fahSeekBar = (SeekBar) findViewById(R.id.seekFah);
        celSeekBar = (SeekBar) findViewById(R.id.seekCel);
        fahNum = (TextView) findViewById(R.id.fahNum);
        celNum = (TextView) findViewById(R.id.celNum);
        interestingText = (TextView) findViewById(R.id.interestingText);

        fahSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int temp1 = 0;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser) {
                    temp1 = progress;
                    int b = (int) ((progress - 32)/1.8);
                    celSeekBar.setProgress(b);
                    fahNum.setText(Integer.toString(progress));
                    celNum.setText(Integer.toString(b));
                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                fahNum.setText(Integer.toString(temp1));
                fahSeekBar.setProgress(temp1);
                if (temp1 < 53.6){
                    interestingText.setText(R.string.its_cold);
                }
                else {
                    interestingText.setText(R.string.its_not_cold);
                }
            }
        });

        celSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int temp;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser) {
                    temp = progress;
                    int a = (int) (progress * (9.0 / 5.0) + 32);
                    fahSeekBar.setProgress(a);
                    celNum.setText(Integer.toString(progress));
                    fahNum.setText(Integer.toString(a));
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                celNum.setText(Integer.toString(temp));
                celSeekBar.setProgress(temp);
                if (temp < 12){
                    interestingText.setText(R.string.its_cold);
                }
                else{
                    interestingText.setText(R.string.its_not_cold);
                }
            }
        });
    }
}