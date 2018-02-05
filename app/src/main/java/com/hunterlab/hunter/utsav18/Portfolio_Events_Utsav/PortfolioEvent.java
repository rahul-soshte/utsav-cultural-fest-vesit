package com.hunterlab.hunter.utsav18.Portfolio_Events_Utsav;

/**
 * Created by hunter on 5/2/18.
 */

public class PortfolioEvent {
    public String name;
    public String id;

    public PortfolioEvent(String name)
    {
        this.name=name;
    }

    public PortfolioEvent()
    {

    }

    public String getName() {
        return name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
