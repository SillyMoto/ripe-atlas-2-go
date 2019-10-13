/*
 * Copyright (C) 2019 SillyMoto authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.beuth.master.classes;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Date;

/**
 * <h1>API Key Class!</h1>
 * <p>
 * This class implements all fields of an API Key.
 *
 * GET-Request to get all your keys:
 * <b>https://atlas.ripe.net/api/v2/keys/</b>
 *
 * @author  Sarah Kommorovski
 * @version 1.0
 * @since   2019-09-30
 */
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
    @SerializedName("grants")
    private ArrayList<Grant> grants;

    public ApiKey(String uuid, Date validFrom, Date validTo, Boolean enabled, Boolean isActive, Date createdAt, String label, ArrayList<Grant> grants) {
        this.uuid = uuid;
        this.validFrom = validFrom;
        this.validTo = validTo;
        this.enabled = enabled;
        this.isActive = isActive;
        this.createdAt = createdAt;
        this.label = label;
        this.grants = grants;
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

    public ArrayList<Grant> getGrants() {
        return grants;
    }

    public void setGrants(ArrayList<Grant> grants) {
        this.grants = grants;
    }
}
