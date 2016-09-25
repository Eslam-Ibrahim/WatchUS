package com.mal.wweqqful.watchus;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

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
import java.util.List;

import Model.MovieEntity;
import Model.MovieReviewEntity;
import Model.MovieReviewListAdapter;

/**
 * A placeholder fragment containing a simple view.
 */
public class MovieReviewActivityFragment extends Fragment {


    // Attributes
    ListView movieReviewsListView;
    MovieReviewListAdapter movieReviewsAdapter;
    MovieReviewsFetcher movieReviewsFetcher;
    ArrayList<MovieReviewEntity> movieReviewsList;


    public MovieReviewActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.fragment_movie_review, container, false);
        movieReviewsListView = (ListView) rootView.findViewById(R.id.movieReviewListview);
        return rootView;

    }

    @Override
    public void onStart(){
        super.onStart();
        movieReviewsFetcher = new MovieReviewsFetcher();
        movieReviewsList = new ArrayList<>();
        Intent intent = getActivity().getIntent();
        String movieID = intent.getStringExtra("target_movie_id").toString();
        movieReviewsFetcher.execute(movieID);
    }



    public class MovieReviewsFetcher extends AsyncTask<String, Void, String> {

        private final String LOG_TAG = MovieReviewsFetcher.class.getSimpleName();
        private  String BASE_URL = "https://api.themoviedb.org/3/movie/";
        private final String APPID_PARAM = "api_key";
        private final String REVIEWS_PARAM = "reviews";

        @Override
        protected String doInBackground(String... params) {

            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;
            String movieReviewsJsonString = null;

            try {

                BASE_URL += params[0]+"/" +REVIEWS_PARAM+ "?";
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
                movieReviewsJsonString = buffer.toString();
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
            return movieReviewsJsonString;
        }

        @Override
        protected void onPostExecute(String result) {
            if (result != null) {
                // New data is back from the server.  Hooray!
                try{
                    JSONObject allJson = new JSONObject(result);

                    JSONArray movieReviewsJsonArray = allJson.getJSONArray("results");

                    for(int indx=0; indx < movieReviewsJsonArray.length(); indx++){
                        movieReviewsList.add(new MovieReviewEntity(movieReviewsJsonArray.getJSONObject(indx)));
                    }
                    if (movieReviewsList.isEmpty()){

                        movieReviewsList.add(new MovieReviewEntity("NO Reviews","NO Reviews","NO Reviews","NO Reviews"));
                    }

                    movieReviewsAdapter = new MovieReviewListAdapter(getActivity(),R.layout.single_movie_review_item ,movieReviewsList);
                    movieReviewsListView.setAdapter(movieReviewsAdapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getActivity(), "Error in parsing json!",
                            Toast.LENGTH_LONG).show();
                }
            }
        }
    }

}
