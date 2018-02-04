package com.hunterlab.hunter.utsav18.FbNews.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Jayesh Saita on 05-Sep-16.
 */
public class FbPic {

    @SerializedName("cover")
    @Expose
    private Cover cover;
    @SerializedName("picture")
    @Expose
    private Picture picture;
    @SerializedName("id")
    @Expose
    private String id;

    /**
     *
     * @return
     * The cover
     */
    public Cover getCover() {
        return cover;
    }

    /**
     *
     * @param cover
     * The cover
     */
    public void setCover(Cover cover) {
        this.cover = cover;
    }

    /**
     *
     * @return
     * The picture
     */
    public Picture getPicture() {
        return picture;
    }

    /**
     *
     * @param picture
     * The picture
     */
    public void setPicture(Picture picture) {
        this.picture = picture;
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

}
