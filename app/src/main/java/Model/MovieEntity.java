package Model;


import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by wweqqful on 8/12/16.
 */

public class MovieEntity {

    // Constants
    private final String POSTER_PATH = "poster_path";
    private final String OVERVIEW = "overview";
    private final String RELEASE_DATE = "release_date";
    private final String MID = "id";
    private final String TITLE = "title";
    private final String VOTE_AVG = "vote_average";
    private final String BACK_DROP_PATH = "backdrop_path";


    // Attributes
    private String title;
    private String posterImgPath;
    private String overview;
    private String releaseDate;
    private double voteAvg;
    private int ID;
    private String backDropPath;

    // Constructors

    public MovieEntity() {
        title = "";
        posterImgPath = "";
        overview = "";
        releaseDate = "";
        voteAvg = 0.0;
        ID = -1;
        backDropPath = "";
    }

    public MovieEntity(String title, String posterImgPath, String overview, String releaseDate, double voteAvg, int ID,
           String backDropPath) {
        this.title = title;
        this.posterImgPath = posterImgPath;
        this.overview = overview;
        this.releaseDate = releaseDate;
        this.voteAvg = voteAvg;
        this.ID = ID;
        this.backDropPath = backDropPath;
    }

    public MovieEntity(JSONObject dataJson) throws JSONException {
        title = dataJson.getString(TITLE);
        posterImgPath = dataJson.getString(POSTER_PATH);
        overview = dataJson.getString(OVERVIEW);
        releaseDate = dataJson.getString(RELEASE_DATE);
        voteAvg = dataJson.getDouble(VOTE_AVG);
        ID = dataJson.getInt(MID);
        backDropPath = dataJson.getString(BACK_DROP_PATH);
    }

    // Setters and Getters

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPosterImgPath() {
        return posterImgPath;
    }

    public void setPosterImgPath(String posterImgPath) {
        this.posterImgPath = posterImgPath;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public double getVoteAvg() {
        return voteAvg;
    }

    public void setVoteAvg(double voteAvg) {
        this.voteAvg = voteAvg;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getBackDropPath() {
        return backDropPath;
    }

    public void setBackDropPath(String backDropPath) {
        this.backDropPath = backDropPath;
    }
}
