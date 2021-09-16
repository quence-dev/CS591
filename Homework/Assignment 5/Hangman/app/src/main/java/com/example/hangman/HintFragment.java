package com.example.hangman;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HintFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HintFragment extends Fragment {

    private Button hintButton;
    private static TextView hintText;
    private int hintPointer;
    final static Map<String, int[]> hints = new HashMap<String, int[]>();

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HintFragment() {
        // Required empty public constructor
    }

    public interface HintFragmentListener {

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment fragment_hint.
     */
    // TODO: Rename and change types and number of parameters
    public static HintFragment newInstance(String param1, String param2) {
        HintFragment fragment = new HintFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_hint, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
        hintText = (TextView) view.findViewById(R.id.hintText);
        hintButton = (Button) view.findViewById(R.id.hintButton);

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

        hintButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(hintPointer < 3) {
                    hintText.setText(hints.get(MainActivity.getAnswer())[hintPointer]);
                    hintPointer++;

                    if (!(MainActivity.getGameOver())) {
                        MainActivity.changeLife();
                    }

                    if (hintPointer == 3) {
                        hintButton.setEnabled(false);
                    }
                    if (MainActivity.getCount() == 7) {
                        hintButton.setEnabled(false);
                        Toast.makeText(view.getContext(), "Game over. your score is: " + MainActivity.getScore(), Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    public void enableHintButton() {
        hintButton.setEnabled(true);
    }

    public int getHintPointer() {
        return this.hintPointer;
    }

    public void setHintPointer(int hintPointer) {
        this.hintPointer = hintPointer;
    }

    public void disableHintButton() {
        hintButton.setEnabled(false);
    }

    public static void clearHintText() {
        hintText.setText("");
    }

    public String getHintText() {
        return (String) hintText.getText();
    }

    public void setHintText(String newHintText) {
        hintText.setText(newHintText);
    }
}