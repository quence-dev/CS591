package com.example.hangman;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputConnection;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link KeyboardFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class KeyboardFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public KeyboardFragment() {
        // Required empty public constructor
    }

    public interface KeyboardFragmentListener {

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment KeyboardFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static KeyboardFragment newInstance(String param1, String param2) {
        KeyboardFragment fragment = new KeyboardFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_keyboard, container, false);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        init(view);
    }

    private Button button_a, button_b, button_c, button_d, button_e, button_f, button_g,
            button_h, button_i, button_j, button_k, button_l, button_m, button_n, button_o,
            button_p, button_q, button_r, button_s, button_t, button_u, button_v, button_w,
            button_x, button_y, button_z, buttonDelete, buttonEnter;

    private EditText inputLetter;

    private SparseArray<String> keyValues = new SparseArray<>();
    private ArrayList<Integer> disabledKeys = new ArrayList<>();
    private InputConnection inputConnection;
    private int lastLetterButton = 0;

    private void init(View view) {

        inputLetter = (EditText) view.findViewById(R.id.inputLetter);

        button_a = (Button) view.findViewById(R.id.button_a);
        button_a.setOnClickListener(listener);
        button_b = (Button) view.findViewById(R.id.button_b);
        button_b.setOnClickListener(listener);
        button_c = (Button) view.findViewById(R.id.button_c);
        button_c.setOnClickListener(listener);
        button_d = (Button) view.findViewById(R.id.button_d);
        button_d.setOnClickListener(listener);
        button_e = (Button) view.findViewById(R.id.button_e);
        button_e.setOnClickListener(listener);
        button_f = (Button) view.findViewById(R.id.button_f);
        button_f.setOnClickListener(listener);
        button_g = (Button) view.findViewById(R.id.button_g);
        button_g.setOnClickListener(listener);
        button_h = (Button) view.findViewById(R.id.button_h);
        button_h.setOnClickListener(listener);
        button_i = (Button) view.findViewById(R.id.button_i);
        button_i.setOnClickListener(listener);
        button_j = (Button) view.findViewById(R.id.button_j);
        button_j.setOnClickListener(listener);
        button_k = (Button) view.findViewById(R.id.button_k);
        button_k.setOnClickListener(listener);
        button_l = (Button) view.findViewById(R.id.button_l);
        button_l.setOnClickListener(listener);
        button_m = (Button) view.findViewById(R.id.button_m);
        button_m.setOnClickListener(listener);
        button_n = (Button) view.findViewById(R.id.button_n);
        button_n.setOnClickListener(listener);
        button_o = (Button) view.findViewById(R.id.button_o);
        button_o.setOnClickListener(listener);
        button_p = (Button) view.findViewById(R.id.button_p);
        button_p.setOnClickListener(listener);
        button_q = (Button) view.findViewById(R.id.button_q);
        button_q.setOnClickListener(listener);
        button_r = (Button) view.findViewById(R.id.button_r);
        button_r.setOnClickListener(listener);
        button_s = (Button) view.findViewById(R.id.button_s);
        button_s.setOnClickListener(listener);
        button_t = (Button) view.findViewById(R.id.button_t);
        button_t.setOnClickListener(listener);
        button_u = (Button) view.findViewById(R.id.button_u);
        button_u.setOnClickListener(listener);
        button_v = (Button) view.findViewById(R.id.button_v);
        button_v.setOnClickListener(listener);
        button_w = (Button) view.findViewById(R.id.button_w);
        button_w.setOnClickListener(listener);
        button_x = (Button) view.findViewById(R.id.button_x);
        button_x.setOnClickListener(listener);
        button_y = (Button) view.findViewById(R.id.button_y);
        button_y.setOnClickListener(listener);
        button_z = (Button) view.findViewById(R.id.button_z);
        button_z.setOnClickListener(listener);
        buttonDelete = (Button) view.findViewById(R.id.button_delete);
        buttonDelete.setOnClickListener(listener);
        buttonEnter = (Button) view.findViewById(R.id.button_enter);
        buttonEnter.setOnClickListener(listener);

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

    private View.OnClickListener listener = new View.OnClickListener() {
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
                    getView().findViewById(lastLetterButton).setEnabled(false);
                    disabledKeys.add(lastLetterButton);
                }

                System.out.println(getInputText());

                if(!(inputConnection.getTextBeforeCursor(1, 0).equals("") && inputConnection.getTextAfterCursor(1,0).equals(""))) {
                    MainActivity.guessLetter(keyValues.get(lastLetterButton), getView().getContext());
                    if(MainActivity.getGameOver()) {
                        for (int i=0; i< keyValues.size(); i++) {
                            getView().findViewById(keyValues.keyAt(i)).setEnabled(false);
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
    };


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
            getView().findViewById(key).setEnabled(false);
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

    public void clearDisabledKeys() {
        disabledKeys.clear();
    }

    public void disableKey(int id) {
        getView().findViewById(id).setEnabled(false);
    }

    public void enableKey(int id) {
        getView().findViewById(id).setEnabled(true);
    }
}