package Model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.mal.wweqqful.watchus.R;
import java.util.List;

/**
 * Created by wweqqful on 9/17/16.
 */
public class MovieReviewListAdapter extends ArrayAdapter<MovieReviewEntity> {


    // Attributes
    private final List<MovieReviewEntity> mData;
    private final Context mContext;

    //Constructors
    public MovieReviewListAdapter(Context context, int resource, List<MovieReviewEntity> objects) {
        super(context, resource, objects);
        mData = objects;
        mContext = context;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //Create view
        RelativeLayout root;
        ViewHolder vh;
        if(convertView==null) {
            LayoutInflater inflater = (LayoutInflater)
                    mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            root = (RelativeLayout) inflater.inflate(R.layout.single_movie_review_item, null);
            vh = new ViewHolder();
            vh.reviewAuthor = (TextView) root.findViewById(R.id.revAuthorTxt);
            vh.reviewContent = (TextView) root.findViewById(R.id.revContentTxt);
            vh.reviewUrl = (TextView) root.findViewById(R.id.revUrlTxt);
            root.setTag(vh);
        }else{
            root = (RelativeLayout) convertView;
            vh = (ViewHolder) root.getTag();
        }

        //Get Item at position
        final MovieReviewEntity item = mData.get(position);

        //Bind Data
        vh.reviewAuthor.setText(item.getRevAuthor());
        vh.reviewContent.setText(item.getRevContent());
        vh.reviewUrl.setText(item.getRevUrl());
        return root;
    }

    class ViewHolder {
        TextView reviewAuthor;
        TextView reviewUrl;
        TextView reviewContent;
    }
}

