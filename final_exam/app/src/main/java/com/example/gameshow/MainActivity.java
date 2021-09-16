/*
Spencer Vilicic
CS591
Final Exam Game Show
 */

package com.example.gameshow;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button btnEndGame, btnDoorA, btnDoorB, btnDoorC;
    TextView tvStatus;
    ArrayList<Prize> prizes = new ArrayList<>();
    String[] prize_names = {"$5 Gamestop Coupon",
            "2020 Porsche Cayenne", "Pot Belly Pig", "Tokyo Vacation", "$5,000"};
    int[] door = {0, 1, 2};
    private boolean gameReset = false;
    private Random r;

    private String logtag = "check";

    String[] door_prize = new String[3];

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        r = new Random();
        Log.i(logtag, prize_names.toString());

        for (int i = 0; i < prize_names.length; i++) {
            prizes.add(new Prize(prize_names[i], i));
        }

        btnEndGame = (Button) findViewById(R.id.btnEndGame);
        btnDoorA = (Button) findViewById(R.id.btnDoorA);
        btnDoorB = (Button) findViewById(R.id.btnDoorB);
        btnDoorC = (Button) findViewById(R.id.btnDoorC);
        tvStatus = (TextView) findViewById(R.id.tvStatus);

        btnEndGame.setEnabled(false);
        tvStatus.setText(R.string.prompt1);

        assignDoors();

        btnDoorA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnDoorA.setEnabled(false);
                btnEndGame.setEnabled(true);
                message(0);
            }
        });

        btnDoorB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnDoorB.setEnabled(false);
                btnEndGame.setEnabled(true);
                message(1);
            }
        });

        btnDoorC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnDoorC.setEnabled(false);
                btnEndGame.setEnabled(true);
                message(2);
            }
        });

        btnEndGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!gameReset) {
                    btnDoorA.setEnabled(false);
                    btnDoorB.setEnabled(false);
                    btnDoorC.setEnabled(false);
                    tvStatus.setText(R.string.prompt3);
                    btnEndGame.setText(R.string.end_game_alt);
                    gameReset = true;
                } else {
                    resetGame();
                }
            }
        });

    }

    private void message(int _door) {
        String p = "";
        for (Prize prize : prizes) {
            if (prize.door == _door) {
                p = prize.name;
            }
        }

        StringBuilder s = new StringBuilder();
        s.append(getString(R.string.prompt2));
        //s.append(p);
        s.append(" "+door_prize[_door]);
        s.append(getString(R.string.prompt2_5));

        tvStatus.setText(s);
    }

    private void assignDoors() {
        int rand = r.nextInt(5);

        int rand2 = r.nextInt(5);
        while (rand2 == rand) {
            rand2 = r.nextInt(5);
        }

        int rand3 = r.nextInt(5);
        while (rand3 == rand2 && rand3 == rand) {
            rand3 = r.nextInt(5);
        }

        prizes.get(rand).setDoor(door[0]);
        prizes.get(rand2).setDoor(door[1]);
        prizes.get(rand3).setDoor(door[2]);

        door_prize[0] = prize_names[rand];
        door_prize[1] = prize_names[rand2];
        door_prize[2] = prize_names[rand3];
    }

    //method for resetting game state
    private void resetGame() {
        btnDoorA.setEnabled(true);
        btnDoorB.setEnabled(true);
        btnDoorC.setEnabled(true);
        btnEndGame.setEnabled(false);
        btnEndGame.setText(R.string.end_game);

        gameReset = false;
        tvStatus.setText(R.string.prompt1);
        for (Prize door : prizes) {
            door.reset();
        }
        assignDoors();
    }


}