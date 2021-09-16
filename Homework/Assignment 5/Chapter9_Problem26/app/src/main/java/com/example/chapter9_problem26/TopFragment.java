package com.example.chapter9_problem26;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class TopFragment extends Fragment {

    private TextView text;
    private Button reset;

    public interface ControlFragmentListener {
        public void resetNum();
    }

    ControlFragmentListener CFL;

    public TopFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        CFL = (ControlFragmentListener) context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_top, container, false);
        text = (TextView) view.findViewById(R.id.message);
        reset = (Button) view.findViewById(R.id.resetBtn);

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CFL.resetNum();
            }
        });


        return view;
    }

    public void setMessage(String msg){
        text.setText(msg);
    }

}