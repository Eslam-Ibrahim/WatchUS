package Model;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by wweqqful on 9/17/16.
 */
public class MovieReviewEntity implements Serializable {

    // Constants
    private final String REV_URL = "url";
    private final String REV_AUTHOR = "author";
    private final String REV_ID = "id";
    private final String REV_Content = "content";

    // Attributes
    private String revUrl;
    private String revAuthor;
    private String revID;
    private String revContent;

    // Constructors
    public MovieReviewEntity(){

        revUrl ="";
        revAuthor ="";
        revID ="";
        revContent ="";
    }

    public MovieReviewEntity(String revUrl, String revAuthor, String revID, String revContent) {
        this.revUrl = revUrl;
        this.revAuthor = revAuthor;
        this.revID = revID;
        this.revContent = revContent;
    }

    public MovieReviewEntity(JSONObject dataJson) throws JSONException {

        revUrl = dataJson.getString(REV_URL);
        revAuthor = dataJson.getString(REV_AUTHOR);
        revID = dataJson.getString(REV_ID);
        revContent = dataJson.getString(REV_Content);
    }

    // Setters and Getters

    public String getRevUrl() {
        return revUrl;
    }

    public void setRevUrl(String revUrl) {
        this.revUrl = revUrl;
    }

    public String getRevAuthor() {
        return revAuthor;
    }

    public void setRevAuthor(String revAuthor) {
        this.revAuthor = revAuthor;
    }

    public String getRevID() {
        return revID;
    }

    public void setRevID(String revID) {
        this.revID = revID;
    }

    public String getRevContent() {
        return revContent;
    }

    public void setRevContent(String revContent) {
        this.revContent = revContent;
    }
}
