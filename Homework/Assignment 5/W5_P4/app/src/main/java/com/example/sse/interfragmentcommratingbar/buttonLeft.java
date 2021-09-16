package com.example.sse.interfragmentcommratingbar;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class buttonLeft extends Fragment {
    private Button buttonLeft;
    ControlFragmentListener fragListener;

    public interface ControlFragmentListener {

        void btnLeft();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        fragListener = (ControlFragmentListener) activity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_left_button, container, false);

        buttonLeft = (Button) view.findViewById(R.id.btnLeft);
        //set Onlick for the "LEFT" button
        buttonLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragListener.btnLeft();
            }
        });
        return view;
    }
}
