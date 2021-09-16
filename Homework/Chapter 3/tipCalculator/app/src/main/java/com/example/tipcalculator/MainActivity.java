package com.example.tipcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    private TextView tipCalculated;
    private TextView totalCalculated;
    private SeekBar seekbar;
    private EditText enterTip;
    private TextView tipRate;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tipCalculated = (TextView) findViewById(R.id.totaltip);
        totalCalculated = (TextView) findViewById(R.id.totalcalcul);
        seekbar = (SeekBar) findViewById(R.id.seekBar);
        enterTip = (EditText) findViewById(R.id.tipAmount);
        tipRate = (TextView) findViewById(R.id.tipRate);

        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int temp;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser){
                    temp = progress;
                    tipRate.setText(Integer.toString(progress) + "%");
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                if(enterTip.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),"Please enter amount",Toast.LENGTH_LONG).show();
                    seekbar.setProgress(0);
                    tipRate.setText(Integer.toString(0) + "%");
                }
                else {
                    double tip = Double.parseDouble(enterTip.getText().toString()) * temp;
                    double tip2 = (double) tip / 100;
                    tipCalculated.setText("$" + Double.toString(tip2));
                    double total = (Double.parseDouble(enterTip.getText().toString()) * (double) 1.00) + tip2;
                    totalCalculated.setText("$" + Double.toString(total));
                }

            }
        });
    }
}