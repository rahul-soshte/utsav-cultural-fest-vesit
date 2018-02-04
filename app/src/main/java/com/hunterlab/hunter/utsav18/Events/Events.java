package com.hunterlab.hunter.utsav18.Events;

/**
 * Created by Jayesh Saita on 13-Sep-16.
 */
public class Events {

    private String event_id;

    private String category;

    private String event_name;

    private String cost;

    private String prize;

    private String teams_of;

    private String description;

    private String date;

    private String image;

    public String getPrize() {
        return prize;
    }

    public void setPrize(String prize) {
        this.prize = prize;
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

    public String getEvent_name() {
        return event_name;
    }

    public void setEvent_name(String event_name) {
        this.event_name = event_name;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
