package com.hunterlab.hunter.utsav18.FbNews.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Jayesh Saita on 05-Sep-16.
 */
public class Data {

    @SerializedName("is_silhouette")
    @Expose
    private Boolean isSilhouette;
    @SerializedName("url")
    @Expose
    private String url;

    /**
     *
     * @return
     * The isSilhouette
     */
    public Boolean getIsSilhouette() {
        return isSilhouette;
    }

    /**
     *
     * @param isSilhouette
     * The is_silhouette
     */
    public void setIsSilhouette(Boolean isSilhouette) {
        this.isSilhouette = isSilhouette;
    }

    /**
     *
     * @return
     * The url
     */
    public String getUrl() {
        return url;
    }

    /**
     *
     * @param url
     * The url
     */
    public void setUrl(String url) {
        this.url = url;
    }

}
