package de.beuth.master.classes;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

public class Status implements Serializable {
    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("when")
    private Date when;

    public Status(int id, String name, Date when) {
        this.id = id;
        this.name = name;
        this.when = when;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
