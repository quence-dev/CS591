package com.example.hangman;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputConnection;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class Keyboard extends LinearLayout implements View.OnClickListener {

        private Button button_a, button_b, button_c, button_d, button_e, button_f, button_g,
                button_h, button_i, button_j, button_k, button_l, button_m, button_n, button_o,
                button_p, button_q, button_r, button_s, button_t, button_u, button_v, button_w,
                button_x, button_y, button_z, buttonDelete, buttonEnter;

        private EditText inputLetter;

        private SparseArray<String> keyValues = new SparseArray<>();
        private ArrayList<Integer> disabledKeys = new ArrayList<>();
        private InputConnection inputConnection;
        private int lastLetterButton = 0;

        public Keyboard(Context context) {
            this(context, null, 0);
        }

        public Keyboard(Context context, AttributeSet attrs) {
            this(context, attrs, 0);
        }

        public Keyboard(Context context, AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
            init(context, attrs);
        }

        private void init(Context context, AttributeSet attrs) {
            LayoutInflater.from(context).inflate(R.layout.keyboard, this, true);

            inputLetter = (EditText) findViewById(R.id.inputLetter);

            button_a = (Button) findViewById(R.id.button_a);
            button_a.setOnClickListener(this);
            button_b = (Button) findViewById(R.id.button_b);
            button_b.setOnClickListener(this);
            button_c = (Button) findViewById(R.id.button_c);
            button_c.setOnClickListener(this);
            button_d = (Button) findViewById(R.id.button_d);
            button_d.setOnClickListener(this);
            button_e = (Button) findViewById(R.id.button_e);
            button_e.setOnClickListener(this);
            button_f = (Button) findViewById(R.id.button_f);
            button_f.setOnClickListener(this);
            button_g = (Button) findViewById(R.id.button_g);
            button_g.setOnClickListener(this);
            button_h = (Button) findViewById(R.id.button_h);
            button_h.setOnClickListener(this);
            button_i = (Button) findViewById(R.id.button_i);
            button_i.setOnClickListener(this);
            button_j = (Button) findViewById(R.id.button_j);
            button_j.setOnClickListener(this);
            button_k = (Button) findViewById(R.id.button_k);
            button_k.setOnClickListener(this);
            button_l = (Button) findViewById(R.id.button_l);
            button_l.setOnClickListener(this);
            button_m = (Button) findViewById(R.id.button_m);
            button_m.setOnClickListener(this);
            button_n = (Button) findViewById(R.id.button_n);
            button_n.setOnClickListener(this);
            button_o = (Button) findViewById(R.id.button_o);
            button_o.setOnClickListener(this);
            button_p = (Button) findViewById(R.id.button_p);
            button_p.setOnClickListener(this);
            button_q = (Button) findViewById(R.id.button_q);
            button_q.setOnClickListener(this);
            button_r = (Button) findViewById(R.id.button_r);
            button_r.setOnClickListener(this);
            button_s = (Button) findViewById(R.id.button_s);
            button_s.setOnClickListener(this);
            button_t = (Button) findViewById(R.id.button_t);
            button_t.setOnClickListener(this);
            button_u = (Button) findViewById(R.id.button_u);
            button_u.setOnClickListener(this);
            button_v = (Button) findViewById(R.id.button_v);
            button_v.setOnClickListener(this);
            button_w = (Button) findViewById(R.id.button_w);
            button_w.setOnClickListener(this);
            button_x = (Button) findViewById(R.id.button_x);
            button_x.setOnClickListener(this);
            button_y = (Button) findViewById(R.id.button_y);
            button_y.setOnClickListener(this);
            button_z = (Button) findViewById(R.id.button_z);
            button_z.setOnClickListener(this);
            buttonDelete = (Button) findViewById(R.id.button_delete);
            buttonDelete.setOnClickListener(this);
            buttonEnter = (Button) findViewById(R.id.button_enter);
            buttonEnter.setOnClickListener(this);

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
        }

        @Override
        public void onClick(View view) {
            if (inputConnection == null)
                return;

            if (view.getId() == R.id.button_delete) {
                CharSequence selectedText = inputConnection.getSelectedText(0);

                if (TextUtils.isEmpty(selectedText)) {
                    inputConnection.deleteSurroundingText(1, 0);
                } else {
                    inputConnection.commitText("", 1);
                }
            } else if (view.getId() == R.id.button_enter) {
                CharSequence selectedText = inputConnection.getSelectedText(0);

                if (lastLetterButton != 0) {
                    findViewById(lastLetterButton).setEnabled(false);
                    disabledKeys.add(lastLetterButton);
                }

                System.out.println(getInputText());

                if(!(inputConnection.getTextBeforeCursor(1, 0).equals("") && inputConnection.getTextAfterCursor(1,0).equals(""))) {
                    MainActivity.guessLetter(keyValues.get(lastLetterButton), this.getContext());
                    if(MainActivity.getGameOver()) {
                        for (int i=0; i< keyValues.size(); i++) {
                            findViewById(keyValues.keyAt(i)).setEnabled(false);
                            MainActivity.disableHintButton();
                            buttonDelete.setEnabled(false);
                        }
                    }
                }

                if (TextUtils.isEmpty(selectedText)) {
                    inputConnection.deleteSurroundingText(1, 0);
                } else {
                    inputConnection.commitText("", 1);
                }
            } else {
                String value = keyValues.get(view.getId());
                lastLetterButton = view.getId();
                inputConnection.commitText(value, 1);
            }
        }


    public void setInputConnection(InputConnection ic) {
        inputConnection = ic;
    }

    public SparseArray<String> getKeyValues() {
            return this.keyValues;
    }

    public ArrayList<Integer> getDisabledKeys() {return this.disabledKeys;}

    public void setDisabledKeys(ArrayList<Integer> disabledKeys) {
        this.disabledKeys = disabledKeys;
        for(int key : disabledKeys) {
            findViewById(key).setEnabled(false);
        }
    }

    public String getInputText() {
        if(inputLetter != null) {
            return (inputLetter.getText().toString());
        } else {
            return("");
        }
    }

    public int getLastLetterButton() {
            return this.lastLetterButton;
    }

    public void setInputText(String text, int lastLetterButton) {
        if(inputLetter != null) {
            inputLetter.setText(text);
        }
        this.lastLetterButton = lastLetterButton;
    }
}
