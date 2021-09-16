package com.example.sse.interfragmentcommratingbar;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class buttonRight extends Fragment {
    ControlFragmentListener fragListener;
    private Button buttonRight;
    public interface ControlFragmentListener {
        void btnRight();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        fragListener = (ControlFragmentListener) activity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_right_button, container, false);

        buttonRight = (Button) view.findViewById(R.id.btnRight);
        //set Onlick for the "RIGHT" button
        buttonRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragListener.btnRight();
            }
        });
        return view;
    }
}
