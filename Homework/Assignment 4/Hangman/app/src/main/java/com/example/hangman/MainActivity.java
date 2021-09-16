package com.example.hangman;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.text.InputType;
import android.util.SparseArray;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private static String answer;
    private static TextView answerText;
    private TextView hintText;
    private Button newGameButton;
    private Button hintButton;
    final static String[] words = new String[]{"apple", "star", "pinecone", "red", "light"};
    final static Map<String, int[]> hints = new HashMap<String, int[]>();
    private static SparseArray<String> keyValues;
    private static int score;
    private int hintPointer;
    private static int count;
    private static ImageView hang;
    private static boolean gameOver;

    @Override
    protected void onSaveInstanceState(Bundle savedInstaceState) {

        super.onSaveInstanceState(savedInstaceState);
        savedInstaceState.putString("score", String.valueOf(score));
        savedInstaceState.putString("hintPointer", String.valueOf(hintButton));
        savedInstaceState.putString("count", String.valueOf(count));
        savedInstaceState.putBoolean("hang", Boolean.parseBoolean(String.valueOf(hang)));
        savedInstaceState.putBoolean("gameOver", Boolean.parseBoolean(String.valueOf(gameOver)));
        savedInstaceState.putBoolean("answer", Boolean.parseBoolean(answer));

    }
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        answer = savedInstanceState.getString(answer);
        answerText.setText(savedInstanceState.getString("answer"));
        score = Integer.parseInt(savedInstanceState.getString("secNum"));
        hintPointer = Integer.parseInt(savedInstanceState.getString("sign"));
        count = savedInstanceState.getInt("count");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hang = (ImageView) findViewById(R.id.hangImage);
        EditText editText = (EditText) findViewById(R.id.inputLetter);
        Keyboard keyboard = (Keyboard) findViewById(R.id.keyboard);
        answerText = (TextView) findViewById(R.id.answerText);
        hintText = (TextView) findViewById(R.id.hintText);
        newGameButton = (Button) findViewById(R.id.newGameButton);
        hintButton = (Button) findViewById(R.id.hintButton);
        keyValues = keyboard.getKeyValues();
        editText.setRawInputType(InputType.TYPE_CLASS_TEXT);
        editText.setTextIsSelectable(true);

        final Random rand = new Random();
        int randi = rand.nextInt(5);
        answer = words[randi];

        String blanks = "";
        score = 0;
        count = 1;
        hintPointer = 0;
        gameOver = false;


        int[] appleHints = new int[]{R.string.food, R.string.its_red, R.string.teachers_love_them};
        hints.put("apple", appleHints);
        int[] starHints = new int[]{R.string.seen_at_night, R.string.also_a_shape,
                R.string.twinkle_twinkle};
        hints.put("star", starHints);
        int[] pineconeHints = new int[]{R.string.trees_drop_them, R.string.produces_seeds,
                R.string.pointy};
        hints.put("pinecone", pineconeHints);
        int[] redHints = new int[]{R.string.color, R.string.taylor_swift_album,
                R.string.primary_color};
        hints.put("red", redHints);
        int[] lightHints = new int[]{R.string.like_from_a_bulb, R.string.not_heavy,
                R.string.dim_or_bright};
        hints.put("light", lightHints);

        for (int i=0; i < answer.length(); i++) {
            blanks += "_ ";
        }

        answerText.setText(blanks);

        InputConnection ic = editText.onCreateInputConnection(new EditorInfo());
        keyboard.setInputConnection(ic);

        newGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] otherWords = new String[4];
                int j = 0;
                for (String word : words) {
                    if(!word.equals(answer)) {
                        otherWords[j] = word;
                        j++;
                    }
                }
                int randj = rand.nextInt(4);
                answer = otherWords[randj];

                String blanks = "";
                score = 0;
                count = 1;

                for (int i=0; i < answer.length(); i++) {
                    blanks += "_ ";
                }
                hang.setImageResource(R.drawable.first);
                hintButton.setEnabled(true);
                hintText.setText("");
                hintPointer = 0;
                gameOver = false;

                answerText.setText(blanks);

                for (int i=0; i< keyValues.size(); i++) {
                    findViewById(keyValues.keyAt(i)).setEnabled(true);
                }
            }
        });

        hintButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(hintPointer < 3) {
                    hintText.setText(hints.get(answer)[hintPointer]);
                    hintPointer++;

                    if (!gameOver) {
                        changeLife();
                    }

                    if (hintPointer == 3) {
                        hintButton.setEnabled(false);
                    }
                    if (count == 7) {
                        hintButton.setEnabled(false);
                        Toast.makeText(MainActivity.this, "Game over. your score is: " + score, Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    public static void guessLetter(String letter, Context context){
        if (answer.contains(letter)) {
            String newAnswerText = "";
            String verifyAnswer = "";
            for (int i=0; i<answer.length(); i++) {
                if (answer.charAt(i) == letter.charAt(0)) {
                    score += 5;
                    newAnswerText += letter + " ";
                    verifyAnswer += letter;
                } else {
                    newAnswerText += answerText.getText().charAt(i * 2) + " ";
                    verifyAnswer += answerText.getText().charAt(i * 2);
                }
            }
            answerText.setText(newAnswerText);
            if (verifyAnswer.equals(answer)) {
                Toast.makeText(context, "You win! Score: " + score, Toast.LENGTH_LONG).show();
                gameOver = true;
            }

        }
        else {
            if(!gameOver) {
                changeLife();
                if (count == 7) {
                    Toast.makeText(context, "Game over. your score is: " + score,
                            Toast.LENGTH_LONG).show();
                    gameOver = true;
                }
            }
        }
    }

    public static void changeLife(){
        count++;

        if (count == 2) {
            hang.setImageResource(R.drawable.second);
        }
        if (count == 3) {
            hang.setImageResource(R.drawable.third);
        }
        if (count == 4) {
            hang.setImageResource(R.drawable.forth);
        }
        if (count == 5) {
            hang.setImageResource(R.drawable.fifth);
        }
        if (count == 6) {
            hang.setImageResource(R.drawable.sixth);
        }
        if (count == 7) {
            hang.setImageResource(R.drawable.seventh);
        }
    }
}