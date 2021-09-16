package com.example.hangman;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PlayFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PlayFragment extends Fragment {

    private static ImageView hangImage;
    private static String hangState;
    private Button newGameButton;
    private static SparseArray<String> keyValues;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PlayFragment() {
        // Required empty public constructor
    }

    public interface PlayFragmentListener {

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PlayFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PlayFragment newInstance(String param1, String param2) {
        PlayFragment fragment = new PlayFragment();
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

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_play, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
        hangImage = (ImageView) view.findViewById(R.id.hangImage);
        newGameButton = (Button) view.findViewById(R.id.newGameButton);

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

        newGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] otherWords = new String[4];
                int j = 0;
                for (String word : MainActivity.getWords()) {
                    if(!word.equals(MainActivity.getAnswer())) {
                        otherWords[j] = word;
                        j++;
                    }
                }
                final Random rand = new Random();
                int randj = rand.nextInt(4);
                MainActivity.setAnswer(otherWords[randj]);

                String blanks = "";
                MainActivity.setScore(0);
                MainActivity.setCount(1);

                MainActivity.clearDisabledKeys();

                for (int i=0; i < MainActivity.getAnswer().length(); i++) {
                    blanks += "_ ";
                }
                setHangImage(R.drawable.first, "first");
                MainActivity.enableHintButton();
                MainActivity.disableKeyboardKey(R.id.button_delete);
                MainActivity.clearHintText();
                MainActivity.setHintPointer(0);
                MainActivity.setGameOver(false);

                MainActivity.setAnswerText(blanks);

                for (int i=0; i< keyValues.size(); i++) {
                    MainActivity.enableKeyboardKey(keyValues.keyAt(i));
                }
            }
        });
    }

    public void setHangImage(int id, String hangState) {
        hangImage.setImageResource(id);
        this.hangState = hangState;
    }

    public String getHangState() {
        return this.hangState;
    }

    public void restoreHangState(String hangState) {
        switch (hangState) {
            case "first":
                hangImage.setImageResource(R.drawable.first);
                break;
            case "second":
                hangImage.setImageResource(R.drawable.second);
                break;
            case "third":
                hangImage.setImageResource(R.drawable.third);
                break;
            case "fourth":
                hangImage.setImageResource(R.drawable.fourth);
                break;
            case "fifth":
                hangImage.setImageResource(R.drawable.fifth);
                break;
            case "sixth":
                hangImage.setImageResource(R.drawable.sixth);
                break;
            case "seventh":
                hangImage.setImageResource(R.drawable.seventh);
                break;
        }
    }
}