package com.hunterlab.hunter.utsav18.FbNews.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Jayesh Saita on 05-Sep-16.
 */
public class Datum {

    @SerializedName("created_time")
    @Expose
    private String createdTime;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("full_picture")
    @Expose
    private String fullPicture;
    @SerializedName("link")
    @Expose
    private String link;


    /**
     *
     * @return
     * The createdTime
     */
    public String getCreatedTime() {
        return createdTime;
    }

    /**
     *
     * @param createdTime
     * The created_time
     */
    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    /**
     *
     * @return
     * The message
     */
    public String getMessage() {
        return message;
    }

    /**
     *
     * @param message
     * The message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     *
     * @return
     * The id
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The fullPicture
     */
    public String getFullPicture() {
        return fullPicture;
    }

    /**
     *
     * @param fullPicture
     * The full_picture
     */
    public void setFullPicture(String fullPicture) {
        this.fullPicture = fullPicture;
    }

    public String getLink() {
        return link;
    }

    /**
     *
     * @param link
     * The link
     */
    public void setLink(String link) {
        this.link = link;
    }


}
