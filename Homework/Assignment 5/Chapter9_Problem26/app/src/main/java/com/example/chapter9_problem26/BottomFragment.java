package com.example.chapter9_problem26;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class BottomFragment extends Fragment {

    private EditText guessField;
    private Button btn;

    public BottomFragment() {
        // Required empty public constructor
    }

    public interface ControlFragmentListener {
        public void sendMessage(String msg);
    }

    ControlFragmentListener CFL;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        CFL = (ControlFragmentListener) context;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bottom, container, false);

        guessField = (EditText) view.findViewById(R.id.guessField);
        btn = (Button) view.findViewById(R.id.btn1);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CFL.sendMessage(guessField.getText().toString());
            }
        });

        return view;
    }
}