package com.example.subtitledemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private TextView tvMsg;
    private EditText etSearch;
    private Button btnSubmit;
    private Spinner sprMenu;
    private RequestQueue queue;
    private ArrayList<String> IDs = new ArrayList<String>();
    private ArrayList<ArrayList<String>> films;
    private ArrayList<String> sharedFilms;
    private String selectedItem = "Movie";
    private static final String LOGTAG = "logme";

    private int globalCounter = 0;

    //Strings for building api endpoint URL
    private String url = "https://www.myapimovies.com/api";
    private String movieSearch = "/v1/movie/search";
    private String actorSearch = "/v1/name/search";
    private String apiKey = "?token=4c2943ca-a936-4010-84ff-68730cd178fc";
    private String movie = "&title=";
    private String actor = "&name=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Instantiate the RequestQueue.
        queue = Volley.newRequestQueue(this);

        films = new ArrayList();
        sharedFilms = new ArrayList<String>();

        etSearch = (EditText) findViewById(R.id.etSearch);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        tvMsg = (TextView) findViewById(R.id.tvMsg);
        tvMsg.setMovementMethod(new ScrollingMovementMethod());
        sprMenu = (Spinner) findViewById(R.id.sprMenu);

        sprMenu.setOnItemSelectedListener(this);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //parse search string into an array of names
                String search = etSearch.getText().toString();
                String[] names = search.split(", ");
                for (int i = 0; i < names.length; i++){
                    names[i] = names[i].replaceAll(" ", "-");
                    System.out.println(names[i]);
                }

                Log.i(LOGTAG, "search is " + search);

                //clear queue, just in case
                queue.cancelAll(this);

                //Do a search based on the search type chosen from the spinner
                switch (selectedItem) {
                    case "Movie":
                        jsonSubParse();
                        //jsonMovieParse(search);
                        break;
                    case "Actor":
                        jsonActorParse(search);
                        break;
                    case "Filmography":
                        jsonFilmographyParseSingle(search);
                        break;
                    case "Compare":
                        for (int i = 0; i < names.length; i++){
                            Log.i(LOGTAG, "name is " + names[i]);
                            jsonFilmographyParseMulti(names[i], i);
                        }
                        break;
                }

            }
        });

        /*
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        tvMsg.setText("Response is: "+ response.substring(0,500));
                        Log.i(LOGTAG, response.toString());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                tvMsg.setText("That didn't work!");
                Log.i(LOGTAG, "error, no response");
            }
        })
        {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("api-key", "xnTl1UpBN2TOofPB5JjZSnOXwgQxneQy");
                return params;
            }
        };

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
        */
    }


    private void updateTextView(int i) {
        ArrayList<String> sharedMovies = films.get(i);

        if (films.get(i+1) != null)
            films.get(i).retainAll(films.get(i+1));

        Log.i(LOGTAG, "should be updating textview...");

        for (String film : films.get(i)){
            tvMsg.append(film + "\n\n");
            Log.i(LOGTAG, "shared movie is " + film);
        }
    }

    //hard coded test of the open subtitles API. should search for Avengers Endgame
    private void jsonSubParse() {
        String url = "https://www.opensubtitles.com/api/v1/subtitles";
        String key = "xnTl1UpBN2TOofPB5JjZSnOXwgQxneQy";
        String movie = "?imdb_id=tt4154796";

        JsonObjectRequest objectRequest = new JsonObjectRequest(
                Request.Method.GET,
                url + movie,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("data");
                            JSONObject movie = jsonArray.getJSONObject(0);
                            JSONObject attributes = movie.getJSONObject("attributes");
                            JSONObject featureDetails = attributes.getJSONObject("feature_details");

                            String title = featureDetails.getString("title");

                            tvMsg.setText("Response is: " + title);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Log.i(LOGTAG, response.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                }) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    HashMap<String, String> headers = new HashMap<String, String>();
                    headers.put("Api-Key", "xnTl1UpBN2TOofPB5JjZSnOXwgQxneQy");
                    return headers;
                }
        };

        queue.add(objectRequest);
    }

    private void jsonMovieParse(String search) {
        JsonObjectRequest objectRequest = new JsonObjectRequest(
                Request.Method.GET,
                url + movieSearch + apiKey + movie + search,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("data");
                            JSONObject movie = jsonArray.getJSONObject(0);
                            String releaseDate = movie.getString("releaseDate");

                            tvMsg.setText("Response is: " + releaseDate.substring(4, 6) + "/" + releaseDate.substring(6) + "/" + releaseDate.substring(0, 4));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Log.i(LOGTAG, response.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                }
        );

        queue.add(objectRequest);
    }

    private void jsonActorParse(String search) {
        JsonObjectRequest objectRequest = new JsonObjectRequest(
                Request.Method.GET,
                url + actorSearch + apiKey + actor + search,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("data");
                            JSONObject actor = jsonArray.getJSONObject(0);
                            String biography = actor.getString("biography");

                            tvMsg.setText("Response is: " + biography);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Log.i(LOGTAG, response.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                }
        );

        queue.add(objectRequest);
    }

    // do two nested calls -> one to retrieve actor's id, second to retrieve their filmography
    private void jsonFilmographyParseMulti(String search, int i) {
        final String searchName = search;
        final int arrayIndex = i;
        Log.i(LOGTAG, "index is " + i);

        JsonObjectRequest objectRequest = new JsonObjectRequest(
                Request.Method.GET,
                url + actorSearch + apiKey + actor + search,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("data");
                            JSONObject actor = jsonArray.getJSONObject(0);
                            String imdbID = actor.getString("imdbId");
                            Log.i(LOGTAG,  searchName + "'s ID is " + imdbID);

                            JsonObjectRequest filmRequest = new JsonObjectRequest(
                                    Request.Method.GET,
                                    url + "/v1/name/" + imdbID + "/filmographies" + apiKey,
                                    null,
                                    new Response.Listener<JSONObject>() {
                                        @Override
                                        public void onResponse(JSONObject response) {
                                            try {
                                                JSONArray filmArray = response.getJSONArray("data");
                                                Log.i(LOGTAG, response.toString());
                                                String toCheck = "Actor";
                                                JSONArray filmography = new JSONArray();

                                                for (int i = 0; i < filmArray.length(); i++){
                                                    JSONObject actorSection = filmArray.getJSONObject(i);

                                                    String fieldName = actorSection.getString("section");
                                                    if (fieldName.equals(toCheck)) {
                                                        filmography = actorSection.getJSONArray("filmographiesNames");
                                                        System.out.println(fieldName);
                                                    }
                                                }

                                                films.add(new ArrayList<String>());

                                                for (int i = 0; i < filmography.length(); i++){
                                                        JSONObject film = filmography.getJSONObject(i);
                                                        String title = film.getString("title");

                                                        films.get(arrayIndex).add(title);
                                                        if (sharedFilms.isEmpty()) {
                                                            sharedFilms.add(title);
                                                        }
                                                }
                                                sharedFilms.retainAll(films.get(arrayIndex));
                                                StringBuilder message = new StringBuilder();
                                                for (String films : sharedFilms){
                                                    message.append(films + "\n");
                                                }
                                                tvMsg.setText(message);

                                                //updateTextView(arrayIndex);

                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    },
                                    new Response.ErrorListener() {
                                        @Override
                                        public void onErrorResponse(VolleyError error) {
                                            error.printStackTrace();
                                        }
                                    }
                            );

                            queue.add(filmRequest);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                }
        );

        queue.add(objectRequest);
        globalCounter++;
        Log.i(LOGTAG, "current count: " + globalCounter);

        //if (globalCounter > 1)
        //     updateTextView(arrayIndex);
    }

    // returns filmography of one actor
    private void jsonFilmographyParseSingle(String search) {
        final String searchName = search;

        JsonObjectRequest objectRequest = new JsonObjectRequest(
                Request.Method.GET,
                url + actorSearch + apiKey + actor + search,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("data");
                            JSONObject actor = jsonArray.getJSONObject(0);
                            String imdbID = actor.getString("imdbId");
                            Log.i(LOGTAG, searchName + "'s ID is " + imdbID);

                            JsonObjectRequest filmRequest = new JsonObjectRequest(
                                    Request.Method.GET,
                                    url + "/v1/name/" + imdbID + "/filmographies" + apiKey,
                                    null,
                                    new Response.Listener<JSONObject>() {
                                        @Override
                                        public void onResponse(JSONObject response) {
                                            try {
                                                JSONArray filmArray = response.getJSONArray("data");
                                                Log.i(LOGTAG, response.toString());
                                                JSONObject actorSection = filmArray.getJSONObject(0);
                                                String fieldName = actorSection.getString("section");
                                                System.out.println(fieldName);
                                                JSONArray filmography = actorSection.getJSONArray("filmographiesNames");

                                                for (int i = 0; i < filmography.length(); i++){
                                                    JSONObject film = filmography.getJSONObject(i);
                                                    String title = film.getString("title");

                                                    tvMsg.append(title + "\n\n");
                                                }
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                            Log.i(LOGTAG, response.toString());
                                        }
                                    },
                                    new Response.ErrorListener() {
                                        @Override
                                        public void onErrorResponse(VolleyError error) {
                                            error.printStackTrace();
                                        }
                                    }
                            );

                            queue.add(filmRequest);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                }
        );

        queue.add(objectRequest);
    }

    private void getID (String search){
        JsonObjectRequest objectRequest = new JsonObjectRequest(
                Request.Method.GET,
                url + actorSearch + apiKey + actor + search,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("data");
                            JSONObject actor = jsonArray.getJSONObject(0);
                            String imdbID = actor.getString("imdbId");
                            Log.i(LOGTAG," ID is " + imdbID);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Log.i(LOGTAG, response.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                }
        );

        queue.add(objectRequest);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        selectedItem = parent.getItemAtPosition(pos).toString();
        switch (selectedItem) {
            case "Movie":
                etSearch.setHint("Ex: The Godfather");
                break;
            case "Actor":
                etSearch.setHint("Ex: George Clooney");
                break;
            case "Filmography":
                etSearch.setHint("Ex: Will Smith");
                break;
            case "Compare":
                etSearch.setHint("Ex: George Clooney, Sandra Bullock");
                break;
        }
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}