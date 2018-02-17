package com.hunterlab.hunter.utsav18.Event_Single;


/**
 * Created by hunter on 5/2/18.
 */

public class EventSingleDetails{
    public long entriesperclass;
    public String event_desc;
    public String name;
    public String teamsize;

    public String getName() {
        return name;
    }

    public long getEntriesperclass() {
        return entriesperclass;
    }

    public String getEvent_desc() {
        return event_desc;
    }

    public String getTeamsize() {
        return teamsize;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEntriesperclass(long entriesperclass) {
        this.entriesperclass = entriesperclass;
    }

    public void setEvent_desc(String event_desc) {
        this.event_desc = event_desc;
    }

    public void setTeamsize(String teamsize) {
        this.teamsize = teamsize;
    }
}
