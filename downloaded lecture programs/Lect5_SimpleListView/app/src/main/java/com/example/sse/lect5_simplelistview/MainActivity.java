package com.example.sse.lect5_simplelistview;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {


    MediaPlayer mp;

    private ListView lvMenu;
 //   private ListAdapter laMenu;
 //   private  String MenuItems[] = {"Hamburger", "Pizza", "Chicken"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//Creating a Simple ListView
// EZ as 1-2-3 (4)

//1. get the reference to your ListView
        lvMenu = (ListView) findViewById(R.id.lvMenu);

//2. Create an Adapter to bind to your ListView.
        final String[] Animals = {"Dog", "Cow", "Turkey", "Chimp", "Horse", "Lion", "Chipmunk"};  //array of strings to put into our ListAdapter.
        ArrayAdapter AnimalListAdapter = new ArrayAdapter<String>(MainActivity.this,           //Context
                                                                 android.R.layout.simple_list_item_1, //type of list (simple)
                                                                 Animals);                            //Data for the list
                                                                 //We will see much more complex Adapters as we go.
//3. ListViews work (display items) by binding themselves to an adapter.
        lvMenu.setAdapter(AnimalListAdapter);    //Let's put some things in our simple listview by binding it to our adaptor.

// 4. Create an onClick Handler.  Not for the ListView, but for its items!
        lvMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String Animal;
                // Animal = Animals[position];  //Note, This is much simpler,
                //Q (for above): Why is referring to the original array less preferable then using CallBack Parms (below)? A: _____________
                Animal = String.valueOf(parent.getItemAtPosition(position));  //Parent refers to the parent of the item, the ListView.  position is the index of the item clicked.
                Toast.makeText(MainActivity.this, "You Clicked on "  + Animal, Toast.LENGTH_LONG).show();

                //https://www.fesliyanstudios.com/royalty-free-sound-effects-download/cow-264
                switch (Animal) {
                    case "Cow":
                        mp = MediaPlayer.create(MainActivity.this, R.raw.cowmooing);
                        mp.start();
                        break;
                    case "Dog":
                        Toast.makeText(getApplicationContext(), "Dog", Toast.LENGTH_SHORT).show();
                        break;
                    case "Turkey":
                        Toast.makeText(getApplicationContext(), "Turkey", Toast.LENGTH_SHORT).show();
                        break;
                    case "Chimp":
                        Toast.makeText(getApplicationContext(), "Chimp", Toast.LENGTH_SHORT).show();
                        break;
                    case "Horse":
                        Toast.makeText(getApplicationContext(), "Horse", Toast.LENGTH_SHORT).show();
                        break;
                    case "Lion":
                        Toast.makeText(getApplicationContext(), "Lion", Toast.LENGTH_SHORT).show();
                        break;
                    case "Chipmunk":
                        Toast.makeText(getApplicationContext(), "Chipmunk", Toast.LENGTH_SHORT).show();
                        break;
                }

            }
        });

    }
}
