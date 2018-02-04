package com.hunterlab.hunter.utsav18.FbNews.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jayesh Saita on 05-Sep-16.
 */
public class FbGraphResponse {

    @SerializedName("data")
    @Expose
    private List<Datum> data = new ArrayList<Datum>();
    @SerializedName("paging")
    @Expose
    private Paging paging;

    /**
     *
     * @return
     * The data
     */
    public List<Datum> getData() {
        return data;
    }

    /**
     *
     * @param data
     * The data
     */
    public void setData(List<Datum> data) {
        this.data = data;
    }

    /**
     *
     * @return
     * The paging
     */
    public Paging getPaging() {
        return paging;
    }

    /**
     *
     * @param paging
     * The paging
     */
    public void setPaging(Paging paging) {
        this.paging = paging;
    }

}
