package Model;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.mal.wweqqful.watchus.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by wweqqful on 8/12/16.
 */
public class MovieGridAdapter extends BaseAdapter{

    // Attributes
    Context cuurentCntxt;
    ArrayList<MovieEntity> currentMoviesList;

    public MovieGridAdapter(Context c) {
        this.cuurentCntxt = c;
        this.currentMoviesList = new ArrayList <>();
    }

    public void setData(ArrayList<MovieEntity> data) {
        this.currentMoviesList = data;
    }

    @Override
    public int getCount() {
        return currentMoviesList.size();
    }

    @Override
    public Object getItem(int position) {
        return currentMoviesList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View viewRow = convertView;
        ImageView moviePoster;

        if(viewRow == null){
            LayoutInflater inflater=(LayoutInflater) cuurentCntxt.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            viewRow = inflater.inflate(R.layout.single_movie_item, parent, false);
            moviePoster = (ImageView)viewRow.findViewById(R.id.single_movie_poster);
            viewRow.setTag(moviePoster);
        }
        else{
            moviePoster = (ImageView) viewRow.getTag();
        }

        Picasso.with(cuurentCntxt).load("https://image.tmdb.org/t/p/w300"+currentMoviesList.get(position).getPosterImgPath())
//                .resize(200,200)
//                .centerCrop()
                .into(moviePoster);

        return viewRow;
    }
}

