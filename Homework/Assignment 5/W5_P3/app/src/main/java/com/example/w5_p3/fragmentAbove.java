package com.example.w5_p3;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class fragmentAbove extends Fragment {
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        fragListner = (ControlFragmentListener) context;
    }

    private ListView sportsList;
    ControlFragmentListener fragListner;

    public interface ControlFragmentListener {
        public void sportsString(String msg);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_above, container, false);

        sportsList = (ListView) view.findViewById(R.id.sportsList);

        final String[] sports = {"Soccer", "IceHockey", "Baseball", "Basketball", "Tennis"};
        ArrayAdapter adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, sports);
        sportsList.setAdapter(adapter);

        sportsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectSports = String.valueOf(parent.getItemAtPosition(position));
                fragListner.sportsString(selectSports);
            }
        });



        return view;
    }


}
