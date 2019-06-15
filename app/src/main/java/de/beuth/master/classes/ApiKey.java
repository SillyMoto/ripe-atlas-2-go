package de.beuth.master.classes;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Date;

public class ApiKey {
    @SerializedName("uuid")
    private String uuid;
    @SerializedName("valid_from")
    private Date validFrom;
    @SerializedName("valid_to")
    private Date validTo;
    @SerializedName("enabled")
    private Boolean enabled;
    @SerializedName("is_active")
    private Boolean isActive;
    @SerializedName("created_at")
    private Date createdAt;
    @SerializedName("label")
    private String label;
    @SerializedName("permissions")
    private ArrayList<String> permissions;

    public ApiKey(String uuid, Date validFrom, Date validTo, Boolean enabled, Boolean isActive, Date createdAt, String label, ArrayList<String> permissions) {
        this.uuid = uuid;
        this.validFrom = validFrom;
        this.validTo = validTo;
        this.enabled = enabled;
        this.isActive = isActive;
        this.createdAt = createdAt;
        this.label = label;
        this.permissions = permissions;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Date getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(Date validFrom) {
        this.validFrom = validFrom;
    }

    public Date getValidTo() {
        return validTo;
    }

    public void setValidTo(Date validTo) {
        this.validTo = validTo;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public ArrayList<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(ArrayList<String> permissions) {
        this.permissions = permissions;
    }
}
