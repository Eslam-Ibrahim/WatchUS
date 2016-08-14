package com.mal.wweqqful.watchus;

import android.net.Uri;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import Model.MovieEntity;
import Model.MovieGridAdapter;
import Model.onMovieClickListener;

/**
 * A placeholder fragment containing a simple view.
 */
public class MovieMainActivityFragment extends Fragment {

    // Attributes
    private GridView moviesGridView;
    private MovieGridAdapter moviesAdapter;
    private ArrayList<MovieEntity> moviesList;
    private MoviesFetcher moviesGridFercher;
    private onMovieClickListener movieClickListener;


    public MovieMainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_movie_main, container, false);
        moviesGridView = (GridView) rootView.findViewById(R.id.moviegridView);
        moviesAdapter = new MovieGridAdapter(getActivity());
        moviesGridView.setAdapter(moviesAdapter);

        // Listen to movie click
        moviesGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                int targetTndx = (int) moviesAdapter.getItemId(position);
                Toast.makeText(getActivity(),"title: "+moviesList.get(position).getTitle(),Toast.LENGTH_SHORT).show();
                movieClickListener.targetMovieLoader(moviesList.get(position));
            }
        });

        return  rootView;
    }

    public void movieClickListener(onMovieClickListener movieClickListener){
        this.movieClickListener = movieClickListener;
    }

    @Override
    public void onStart() {
        super.onStart();
        moviesList = new ArrayList<>();
        moviesGridFercher = new MoviesFetcher();
        moviesGridFercher.execute();
    }

    // Movies Retrieval AsyncTask Inner Class
    public class MoviesFetcher extends AsyncTask<Void, Void, String> {

        // Constants
        private final String LOG_TAG = MoviesFetcher.class.getSimpleName();
        private final String BASE_URL = "https://api.themoviedb.org/3/movie/popular?";
        private final String APPID_PARAM = "api_key";
        @Override
        protected String doInBackground(Void... voids) {
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;
            String moviesJsonString = null;

            try {
                Uri builtUri = Uri.parse(BASE_URL).buildUpon()
                        .appendQueryParameter(APPID_PARAM, BuildConfig.Movie_DB_ApiKey)
                        .build();
                URL url = new URL(builtUri.toString());
                Log.v(LOG_TAG, "Built URI " + url.toString());
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                // Read the input stream into a String
                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                if (inputStream == null) {
                    // Nothing to do.
                    Toast.makeText(getActivity(), "Connection Error!",
                            Toast.LENGTH_LONG).show();
                    return null;
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line + "\n");
                }
                if (buffer.length() == 0) {
                    // Stream was empty.  No point in parsing.
                    return null;
                }
                moviesJsonString = buffer.toString();
            } catch (IOException e) {
                Log.e(LOG_TAG, "Error ", e);
                Toast.makeText(getActivity(), "An Error Has occurred!",
                        Toast.LENGTH_LONG).show();
                return null;
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        Log.e(LOG_TAG, "Error closing stream", e);
                        Toast.makeText(getActivity(), "Error in streams!",
                                Toast.LENGTH_LONG).show();
                    }
                }
            }
            return moviesJsonString;
        }


        @Override
        protected void onPostExecute(String result) {
            if (result != null) {
                // New data is back from the server.  Hooray!
                try{
                    JSONObject allJson = new JSONObject(result);
                    JSONArray moviesJsonArray = allJson.getJSONArray("results");
                    for(int indx=0; indx<moviesJsonArray.length(); indx++){
                        moviesList.add(new MovieEntity(moviesJsonArray.getJSONObject(indx)));
                    }
                    moviesAdapter.setData(moviesList);
                    moviesGridView.setAdapter(moviesAdapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getActivity(), "Error in parsing json!",
                            Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}



