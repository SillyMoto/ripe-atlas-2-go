package de.beuth.master.classes;

import com.google.gson.annotations.SerializedName;

public class Grant {
    @SerializedName("permission")
    private String permission;
    //@SerializedName("target")
    //private Target target;

    public Grant(String permission) {
        this.permission = permission;
        //this.target = target;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }
}
