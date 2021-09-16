package com.example.sse.butterknife_simple;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

//ButterKnife Imports
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;

public class MainActivity extends AppCompatActivity {

// (1) ButterKnife to BindViews ////////
    @BindView(R.id.tvMsg)
    TextView tvMsg;

    @BindView(R.id.btnClickMe)
    Button btnClickMe;
///////////////////////////////////////

    // (1) ButterKnife to BinDrawables ////////
//    @BindView(R.id.tvMsg)
//    TextView tvMsg;
//
//    @BindView(R.id.btnClickMe)
//    Button btnClickMe;
///////////////////////////////////////

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);  //establishes view references, ez'ier than findViewByID(..) n times.

        tvMsg.setText("Hello ButterKnife");

//This is ugly!  ButterKnife simplifies
//    btnClickMe.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            tvMsg.setText("I WAS CLICKED");
//        }
//    });

//This is also ugly!
//btnClickMe.setOnLongClickListener(new View.OnLongClickListener() {
//    @Override
//    public boolean onLongClick(View v) {
//        tvMsg.setText("I WAS LONG CLICKED");
//        return false;
//    }
//});

    }

//LET'S BEAUTIFY WITH BUTTERKNIFE
// (2) ButterKnife to Bind Events ////////

    @OnClick(R.id.btnClickMe)
    public void anything(){
        tvMsg.setText("I WAS CLICKED (BUTTERKNIFE)");
    }

    @OnLongClick(R.id.btnClickMe)
    public boolean OnLongClick(){
        tvMsg.setText("I WAS LONG CLICKED (BUTTERKNIFE)");
        return false;
    }
//////////////////////////////////////////


}

