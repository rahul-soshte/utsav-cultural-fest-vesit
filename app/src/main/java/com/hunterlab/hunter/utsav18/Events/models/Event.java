package com.hunterlab.hunter.utsav18.Events.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Jayesh Saita on 17-Jul-16.
 */
public class Event {

    @SerializedName("event_id")
    private String event_id;

    @SerializedName("category")
    private String category;

    @SerializedName("event_name")
    private String event_name;

    @SerializedName("cost")
    private String cost;

    @SerializedName("prize")
    private String prize;

    @SerializedName("teams_of")
    private String teams_of;

    @SerializedName("description")
    private String description;

    @SerializedName("date")
    private String date;

    @SerializedName("image")
    private String image;

    public String getImage(){
        return image;
    }

    public void setImage(String image){
        this.image = image;
    }

    public String getEvent_id() {
        return event_id;
    }

    public void setEvent_id(String event_id) {
        this.event_id = event_id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getPrize() {
        return prize;
    }

    public void setPrize(String prize) {
        this.prize = prize;
    }

    public String getTeams_of() {
        return teams_of;
    }

    public void setTeams_of(String teams_of) {
        this.teams_of = teams_of;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEvent_name() {
        return event_name;
    }

    public void setEvent_name(String event_name) {
        this.event_name = event_name;
    }
}
