package com.hunterlab.hunter.utsav18.Events.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jayesh Saita on 17-Jul-16.
 */
public class EventsResponse {

    @SerializedName("data")
    List<Event> data = new ArrayList<Event>();

    public List<Event> getData() {
        return data;
    }

    public void setData(List<Event> response_json) {
        this.data = data;
    }
}
