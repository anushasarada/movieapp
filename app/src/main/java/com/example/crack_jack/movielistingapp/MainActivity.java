package com.example.crack_jack.movielistingapp;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    //Declare RecyclerView, MovieAdapter
    private RecyclerView recyclerView;
    private MovieAdapter movieAdapter;
    private List<MovieDetails> movieList;
    public static final String LOG_TAG = MovieAdapter.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //mActivity = MainActivity.this;

        recyclerView = findViewById(R.id.recycler_view);
        movieList = new ArrayList<>();
        movieAdapter = new MovieAdapter(this,movieList);

        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(movieAdapter);
        movieAdapter.notifyDataSetChanged();

        loadJSON();


//      Get and Load Data
/*        try {
            JSONObject jo = null;
            try{
                jo = new JSONObject(loadJSONFromAsset());
            }
            catch(JSONException e){
                e.printStackTrace();
            }
            JSONArray moviesArray = jo.getJSONArray( "results");
            for(int i=0, s = moviesArray.length();i<s;i++)
            {
                JSONObject movie = moviesArray.getJSONObject(i);

                String movieName = movie.getString("release_date");
                String releaseDate = movie.getString("release_date");
                String rating = movie.getString("vote_average");
                String imageURI="https://image.tmdb.org/t/p/w500"+movie.getString("poster_path");

                //Use these variables to load to the MovieAdapter
                movieAdapter.addMovieItem(new MovieModel(movieName, releaseDate, imageURI, rating));
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
*/






//      Load JSON from Raw JSON file

    }

    public Activity getActivity(){
        Context context = this;
        while(context instanceof ContextWrapper){
            if(context instanceof Activity)
                return (Activity) context;
            context = ((ContextWrapper) context).getBaseContext();
        }
        return null;
    }

    private void loadJSON(){

        Toast.makeText(getApplicationContext(),"App started.",Toast.LENGTH_SHORT).show();
        try{
            if(BuildConfig.THE_MOVIE_DB_API_TOKEN.isEmpty()){
                Toast.makeText(getApplicationContext(),"Please obtain API key from themoviedb.org",Toast.LENGTH_SHORT).show();
                return;
            }

            Client Client = new Client();
            Service apiService = Client.getClient().create(Service.class);
            Call<MovieResponse> call = apiService.getPopularMovies(BuildConfig.THE_MOVIE_DB_API_TOKEN);
            call.enqueue(new Callback<MovieResponse>(){
                @Override
                public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response){
                    List<MovieDetails> movies = response.body().getResults();
                    //Toast.makeText(getApplicationContext(),"response",Toast.LENGTH_SHORT).show();
                    recyclerView.setAdapter(new MovieAdapter(getApplicationContext(), movies));
                    //recyclerView.smoothScrollToPosition(0);
                }

                @Override
                public void onFailure(Call<MovieResponse> call, Throwable t){
                    Log.d("Error",t.getMessage());
                    Toast.makeText(MainActivity.this,"Error Fetching data!",Toast.LENGTH_SHORT).show();
                }
            });
        }
        catch(Exception e){
            Log.d("Error",e.getMessage());
            Toast.makeText(this,e.toString(),Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.menu_settings:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /*    private String loadJSONFromAsset() {

        String movieJSON = "";

        //  Work on it
        try{
            InputStream is = getResources().openRawResource(R.raw.movie);

            int size = is.available();

            byte[] buff = new byte[size];

            is.read(buff);
            is.close();

            movieJSON = new String(buff,"UTF-8");
        }
        catch(IOException e){
            e.printStackTrace();
        }

        return movieJSON;
    }
*/
}
