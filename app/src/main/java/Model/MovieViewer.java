package Model;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.mal.wweqqful.watchus.MovieReviewActivity;
import com.mal.wweqqful.watchus.R;
import com.squareup.picasso.Picasso;

import java.io.Serializable;

/**
 * Created by wweqqful on 8/14/16.
 */
public class MovieViewer implements Serializable{

    // Attributes

    private ImageView moviePoster;
    private TextView movieTitle;
    private TextView movieOverview;
    private TextView movieReleaseDate;
    private RatingBar voteAvg;
    private TextView voteNum;
    private ImageButton favButton;
    private Button movieReviewsBtn;

    // Constructors

    public MovieViewer(ImageView moviePoster, TextView movieTitle, TextView movieOverview,
                       TextView movieReleaseDate, RatingBar voteAvg, TextView voteNum, ImageButton favButton,
                       Button movieReviewsBtn) {
        this.moviePoster = moviePoster;
        this.movieTitle = movieTitle;
        this.movieOverview = movieOverview;
        this.movieReleaseDate = movieReleaseDate;
        this.voteAvg = voteAvg;
        this.voteNum = voteNum;
        this.favButton = favButton;
        this.movieReviewsBtn = movieReviewsBtn;
    }

    public MovieViewer(View movieView) {
        moviePoster = (ImageView) movieView.findViewById(R.id.moviePoster);
        movieTitle = (TextView) movieView.findViewById(R.id.movieTitle);
        movieOverview = (TextView) movieView.findViewById(R.id.movieOverview);
        movieReleaseDate = (TextView) movieView.findViewById(R.id.release_date);
        voteAvg = (RatingBar) movieView.findViewById(R.id.vote_average);
        voteNum = (TextView) movieView.findViewById(R.id.voteNum);
        favButton = (ImageButton) movieView.findViewById(R.id.favButn);
        movieReviewsBtn = (Button) movieView.findViewById(R.id.movieReviewsBtn);
    }

    // Setters and Getters

    public ImageView getMoviePoster() {
        return moviePoster;
    }

    public void setMoviePoster(ImageView moviePoster) {
        this.moviePoster = moviePoster;
    }

    public TextView getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(TextView movieTitle) {
        this.movieTitle = movieTitle;
    }

    public TextView getMovieOverview() {
        return movieOverview;
    }

    public void setMovieOverview(TextView movieOverview) {
        this.movieOverview = movieOverview;
    }

    public TextView getMovieReleaseDate() {
        return movieReleaseDate;
    }

    public void setMovieReleaseDate(TextView movieReleaseDate) {
        this.movieReleaseDate = movieReleaseDate;
    }

    public RatingBar getVoteAvg() {
        return voteAvg;
    }

    public void setVoteAvg(RatingBar voteAvg) {
        this.voteAvg = voteAvg;
    }

    public TextView getVoteNum() {
        return voteNum;
    }

    public void setVoteNum(TextView voteNum) {
        this.voteNum = voteNum;
    }

    public ImageButton getFavButton() {
        return favButton;
    }

    public void setFavButton(ImageButton favButton) {
        this.favButton = favButton;
    }


    // Public Methods
    public void showMovie(final MovieEntity targetMovie, final Context contxtOfMovieView) {

        Picasso.with(contxtOfMovieView).load("https://image.tmdb.org/t/p/w300"
                + targetMovie.getPosterImgPath()).into(moviePoster);
        movieTitle.setText(targetMovie.getTitle());
        movieOverview.setText(targetMovie.getOverview());
        movieReleaseDate.setText(targetMovie.getReleaseDate());
        voteNum.setText("Rate: " + targetMovie.getVoteAvg() + "/10");
        voteAvg.setNumStars(5);
        voteAvg.setStepSize(1 / 5);
        voteAvg.setRating((float) targetMovie.getVoteAvg() / 2);
        voteAvg.setIsIndicator(true);
        favButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                   Favorite Logic Goes Here
                 */
                Toast.makeText(contxtOfMovieView, "Fav Button Clicked!",
                        Toast.LENGTH_LONG).show();
            }
        });
        movieReviewsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                   Redirect user to movie reviews activity
                 */
                Intent intent = new Intent(contxtOfMovieView, MovieReviewActivity.class);
                intent.putExtra("target_movie_id",targetMovie.getID());
                contxtOfMovieView.startActivity(intent);
            }
        });
    }
}
