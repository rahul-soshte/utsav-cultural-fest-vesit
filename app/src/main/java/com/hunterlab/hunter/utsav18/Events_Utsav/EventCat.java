package com.hunterlab.hunter.utsav18.Events_Utsav;

/**
 * Created by hunter on 12/10/17.
 */

public class EventCat {

    public String catname;
    public String id;

    public EventCat(String catname)
    {
        this.catname=catname;
    }

    public EventCat()
    {

    }

    public String getCatname() {
        return catname;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCatname(String catname) {
        this.catname = catname;
    }
}
