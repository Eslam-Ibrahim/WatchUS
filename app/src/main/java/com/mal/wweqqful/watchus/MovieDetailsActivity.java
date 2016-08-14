package com.mal.wweqqful.watchus;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class MovieDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle extras = getIntent().getExtras();
        if(null == savedInstanceState ){
            MovieDetailsActivityFragment movieDetailsFragment = new MovieDetailsActivityFragment();
            movieDetailsFragment.setArguments(extras);
            getSupportFragmentManager().beginTransaction().add(R.id.fragment,movieDetailsFragment).commit();
        }
    }
}
