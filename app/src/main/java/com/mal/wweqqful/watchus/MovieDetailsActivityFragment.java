package com.mal.wweqqful.watchus;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.Serializable;

import Model.MovieEntity;
import Model.MovieViewer;

/**
 * A placeholder fragment containing a simple view.
 */
public class MovieDetailsActivityFragment extends Fragment implements Serializable {

    // Attributes
    MovieViewer movieDetailsViewer;
    MovieEntity targetMovie;


    public MovieDetailsActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView =inflater.inflate(R.layout.fragment_movie_details, container, false);
        // Setup the movie viewer
        movieDetailsViewer = new MovieViewer(rootView);

        // Get target movie from prev activity
        Intent intent = getActivity().getIntent();
        targetMovie = (MovieEntity) intent.getSerializableExtra("targetMovie");

//        Bundle extras = this.getArguments();
//        if(!extras.getSerializable("targetMovie").equals(null)) {
//            targetMovie = (MovieEntity) extras.getSerializable("targetMovie");
//        }

        // Load movie details into the view
        movieDetailsViewer.showMovie(targetMovie , rootView.getContext());
        return  rootView;
    }
}
