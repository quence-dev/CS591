package com.example.ch2q29;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText ramen;
    private EditText pizza;
    private EditText burger;
    private EditText sandwich;
    private TextView resultNumber;
    private Button totalCal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ramen = (EditText) findViewById(R.id.Ramen);
        pizza = (EditText) findViewById(R.id.Pizza);
        burger = (EditText) findViewById(R.id.Burger);
        sandwich = (EditText) findViewById(R.id.Sandwich);
        resultNumber = (TextView) findViewById(R.id.result);
        totalCal = (Button) findViewById(R.id.total);



        totalCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int calRamen = 500;
                int calBurger = 600;
                int calPizza = 550;
                int calSandwich = 330;
                int output;

                try{
                int totalRamen = Integer.parseInt(ramen.getText().toString()) * calRamen;
                int totalBurger = Integer.parseInt(burger.getText().toString()) * calBurger;
                int totalPizza = Integer.parseInt(pizza.getText().toString()) * calPizza;
                int totalSandwich = Integer.parseInt(sandwich.getText().toString()) * calSandwich;


                output = totalRamen+totalBurger+totalPizza+totalSandwich;

                resultNumber.setText(Integer.toString(output));
            }
                catch(NumberFormatException e){
                    Toast.makeText(getApplicationContext(),"Please input integers only",Toast.LENGTH_LONG).show();
                    ramen.setHint("Input Number");
                    burger.setHint("Input Number");
                    pizza.setHint("Input Number");
                    sandwich.setHint("Input Number");
                }
        }

    });
}
}