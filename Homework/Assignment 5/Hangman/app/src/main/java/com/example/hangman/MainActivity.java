package com.example.hangman;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

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

public class MainActivity extends AppCompatActivity implements
        KeyboardFragment.KeyboardFragmentListener, HintFragment.HintFragmentListener,
        PlayFragment.PlayFragmentListener {

    private static String answer;
    private static TextView answerText;
    final static String[] words = new String[]{"apple", "star", "pinecone", "red", "light"};
    final static Map<String, int[]> hints = new HashMap<String, int[]>();
    private static SparseArray<String> keyValues;
    private static int score;
    private static int count;
    private static ImageView hang;
    private static String hangState;
    private static boolean gameOver;
    private static KeyboardFragment keyboard;
    private static HintFragment hintFragment;
    private static PlayFragment playFragment;
    private static EditText editText;

    @Override
    protected void onSaveInstanceState(Bundle savedInstaceState) {

        super.onSaveInstanceState(savedInstaceState);
        savedInstaceState.putString("answerText", answerText.getText().toString());
        savedInstaceState.putInt("score", score);
        savedInstaceState.putInt("hintPointer", hintFragment.getHintPointer());
        savedInstaceState.putInt("count", count);
        savedInstaceState.putString("hangState", playFragment.getHangState());
        savedInstaceState.putBoolean("gameOver", Boolean.parseBoolean(String.valueOf(gameOver)));
        savedInstaceState.putString("answer", answer);
        savedInstaceState.putIntegerArrayList("disabledKeys", keyboard.getDisabledKeys());
        savedInstaceState.putString("inputText", keyboard.getInputText());
        savedInstaceState.putInt("lastLetterButton", keyboard.getLastLetterButton());
        savedInstaceState.putString("currentHint", hintFragment.getHintText());
    }
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        answer = savedInstanceState.getString("answer");
        answerText.setText(savedInstanceState.getString("answerText"));
        score = savedInstanceState.getInt("score");
        hintFragment.setHintPointer(savedInstanceState.getInt("hintPointer"));
        count = savedInstanceState.getInt("count");
        playFragment.restoreHangState(savedInstanceState.getString("hangState"));
        gameOver = savedInstanceState.getBoolean("gameOver");
        keyboard.setDisabledKeys(savedInstanceState.getIntegerArrayList("disabledKeys"));
        keyboard.setInputText(savedInstanceState.getString("inputText"), savedInstanceState.getInt("lastLetterButton"));
        hintFragment.setHintText(savedInstanceState.getString("currentHint"));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.inputLetter);
        keyboard = (KeyboardFragment) getSupportFragmentManager().findFragmentById(R.id.keyboardFragment);
        answerText = (TextView) findViewById(R.id.answerText);
        hintFragment = (HintFragment) getSupportFragmentManager().findFragmentById(R.id.hintFragment);
        playFragment = (PlayFragment) getSupportFragmentManager().findFragmentById(R.id.playFragment);
        keyValues = new SparseArray<String>();
        keyValues.put(R.id.button_a, "a");
        keyValues.put(R.id.button_b, "b");
        keyValues.put(R.id.button_c, "c");
        keyValues.put(R.id.button_d, "d");
        keyValues.put(R.id.button_e, "e");
        keyValues.put(R.id.button_f, "f");
        keyValues.put(R.id.button_g, "g");
        keyValues.put(R.id.button_h, "h");
        keyValues.put(R.id.button_i, "i");
        keyValues.put(R.id.button_j, "j");
        keyValues.put(R.id.button_k, "k");
        keyValues.put(R.id.button_l, "l");
        keyValues.put(R.id.button_m, "m");
        keyValues.put(R.id.button_n, "n");
        keyValues.put(R.id.button_o, "o");
        keyValues.put(R.id.button_p, "p");
        keyValues.put(R.id.button_q, "q");
        keyValues.put(R.id.button_r, "r");
        keyValues.put(R.id.button_s, "s");
        keyValues.put(R.id.button_t, "t");
        keyValues.put(R.id.button_u, "u");
        keyValues.put(R.id.button_v, "v");
        keyValues.put(R.id.button_w, "w");
        keyValues.put(R.id.button_x, "x");
        keyValues.put(R.id.button_y, "y");
        keyValues.put(R.id.button_z, "z");
        keyValues.put(R.id.button_enter, "\n");
        editText.setRawInputType(InputType.TYPE_CLASS_TEXT);
        editText.setTextIsSelectable(true);
        editText.setFocusable(false);

        final Random rand = new Random();
        int randi = rand.nextInt(5);
        answer = words[randi];

        String blanks = "";
        score = 0;
        count = 1;
        hintFragment.setHintPointer(0);
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

    }

    public static void guessLetter(String letter, Context context) {
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
            if(!gameOver) {
                answerText.setText(newAnswerText);
            }
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

    public static void changeLife() {
        count++;

        if (count == 2) {
            playFragment.setHangImage(R.drawable.second, "second");
        }
        if (count == 3) {
            playFragment.setHangImage(R.drawable.third, "third");
        }
        if (count == 4) {
            playFragment.setHangImage(R.drawable.fourth, "fourth");
        }
        if (count == 5) {
            playFragment.setHangImage(R.drawable.fifth, "fifth");
        }
        if (count == 6) {
            playFragment.setHangImage(R.drawable.sixth, "sixth");
        }
        if (count == 7) {
            playFragment.setHangImage(R.drawable.seventh, "seventh");
        }
    }

    public static boolean getGameOver() {
        return gameOver;
    }

    public static void setGameOver(boolean newGameOver) {
        gameOver = newGameOver;
    }

    public static String getAnswer() {
        return answer;
    }

    public static void setAnswer(String newAnswer) {
        answer = newAnswer;
    }

    public static int getCount() {
        return count;
    }

    public static void setCount(int newCount) {
        count = newCount;
    }

    public static int getScore() {
        return score;
    }

    public static void setScore(int newScore) {
        score = newScore;
    }

    public static void disableHintButton() {
        hintFragment.disableHintButton();
    }

    public static void enableHintButton() {
        hintFragment.enableHintButton();
    }

    public static String[] getWords() {
        return words;
    }

    public static void clearHintText() {
        hintFragment.clearHintText();
    }

    public static void setHintPointer(int hintPointer) {
        hintFragment.setHintPointer(hintPointer);
    }

    public static void setAnswerText(String newAnswerText) {
        answerText.setText(newAnswerText);
    }

    public static void clearDisabledKeys() {
        keyboard.clearDisabledKeys();
    }

    public static void disableKeyboardKey(int id) {
        keyboard.disableKey(id);
    }

    public static void enableKeyboardKey(int id) {
        keyboard.enableKey(id);
    }
}