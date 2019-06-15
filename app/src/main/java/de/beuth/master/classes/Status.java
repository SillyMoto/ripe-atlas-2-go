package de.beuth.master.classes;

import java.util.Date;

public class Status {
    private int ID;
    private String name;
    private Date when;

    public Status(int ID, String name, Date when) {
        this.ID = ID;
        this.name = name;
        this.when = when;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getWhen() {
        return when;
    }

    public void setWhen(Date when) {
        this.when = when;
    }
}
