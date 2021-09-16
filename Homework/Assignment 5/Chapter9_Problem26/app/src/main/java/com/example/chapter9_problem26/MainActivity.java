/*
Write an app containing one activity using two fragments
(one at the top occupying 75% of the space and one below occupying 25% of the space) as follows:
The app generates a random number between 1 and 5 and the user needs to guess it.
The bottom fragment shows an EditText asking the user to enter a number.
The top fragment shows some feedback to the user (for example, “You won” or “You lost”).
*/
package com.example.chapter9_problem26;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements BottomFragment.ControlFragmentListener,
        TopFragment.ControlFragmentListener {

    private static final String correct = "Hey, you got it!";
    private static final String incorrect = "Nope, guess again.";

    private int randomNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRandomNum();
    }


    //set a new random integer 1-5
    private void setRandomNum() {
        randomNum = (int) ((Math.random()*5) + 1);
    }

    @Override
    public void resetNum(){
        setRandomNum();
        TopFragment receivingFragment = (TopFragment) getSupportFragmentManager().findFragmentById(R.id.topFragment);
        receivingFragment.setMessage("Guess a number from 1-5.");
        Toast.makeText(getApplicationContext(), "game reset", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void sendMessage(String msg) {
        TopFragment receivingFragment = (TopFragment) getSupportFragmentManager().findFragmentById(R.id.topFragment);
        if (checkGuess(msg)){
            receivingFragment.setMessage(correct);
        }
        else
            receivingFragment.setMessage(incorrect);
    }

    private boolean checkGuess (String input){
        int temp = Integer.parseInt(input);
        if (randomNum == temp)
            return true;
        else
            return false;
    }


}