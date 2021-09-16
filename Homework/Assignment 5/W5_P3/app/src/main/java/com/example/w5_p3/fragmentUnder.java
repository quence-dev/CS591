package com.example.w5_p3;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class fragmentUnder extends Fragment {

    private ImageView imgView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_under, container, false);
        imgView = (ImageView) view.findViewById(R.id.imgView);
        return view;
    }

    public void chooseSports(String image_name){
        switch(image_name){
            case "Baseball":
                imgView.setImageResource(R.drawable.baseball);
                break;
            case "Basketball":
                imgView.setImageResource(R.drawable.basketball);
                break;
            case "Soccer":
                imgView.setImageResource(R.drawable.soccer);
                break;
            case "Tennis":
                imgView.setImageResource(R.drawable.tennis);
                break;
            case "IceHockey":
                imgView.setImageResource(R.drawable.icehockey);
                break;
        }

        return;
    }
}
