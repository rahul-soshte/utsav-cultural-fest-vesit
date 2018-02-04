package com.hunterlab.hunter.utsav18.FbNews.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Jayesh Saita on 05-Sep-16.
 */
public class Cover {

    @SerializedName("cover_id")
    @Expose
    private String coverId;
    @SerializedName("offset_x")
    @Expose
    private Integer offsetX;
    @SerializedName("offset_y")
    @Expose
    private Integer offsetY;
    @SerializedName("source")
    @Expose
    private String source;
    @SerializedName("id")
    @Expose
    private String id;

    /**
     *
     * @return
     * The coverId
     */
    public String getCoverId() {
        return coverId;
    }

    /**
     *
     * @param coverId
     * The cover_id
     */
    public void setCoverId(String coverId) {
        this.coverId = coverId;
    }

    /**
     *
     * @return
     * The offsetX
     */
    public Integer getOffsetX() {
        return offsetX;
    }

    /**
     *
     * @param offsetX
     * The offset_x
     */
    public void setOffsetX(Integer offsetX) {
        this.offsetX = offsetX;
    }

    /**
     *
     * @return
     * The offsetY
     */
    public Integer getOffsetY() {
        return offsetY;
    }

    /**
     *
     * @param offsetY
     * The offset_y
     */
    public void setOffsetY(Integer offsetY) {
        this.offsetY = offsetY;
    }

    /**
     *
     * @return
     * The source
     */
    public String getSource() {
        return source;
    }

    /**
     *
     * @param source
     * The source
     */
    public void setSource(String source) {
        this.source = source;
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
