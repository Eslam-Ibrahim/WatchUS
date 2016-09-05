package com.mal.wweqqful.watchus;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import Model.MovieEntity;
import Model.onMovieClickListener;

public class MovieMainActivity extends AppCompatActivity implements onMovieClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(null==savedInstanceState){
            MovieMainActivityFragment moviesDisplayFragment = new MovieMainActivityFragment();
            moviesDisplayFragment.movieClickListener(this);
            getSupportFragmentManager().beginTransaction().add(R.id.mainFragment, moviesDisplayFragment).commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_movie_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity(new Intent(this,SettingsActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void targetMovieLoader(MovieEntity targetMovie) {

        // Load target movie
        Intent intent = new Intent(this, MovieDetailsActivity.class);
        intent.putExtra("targetMovie", targetMovie);
        startActivity(intent);
    }
}
