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

import java.io.Serializable;

/**
 * <h1>Ping Measurement Class!</h1>
 * <p>
 * This class should be implemented for the specific fields for an ping measurement.
 * It's not implemented yet.
 *
 * GET-Request to get all ping measurements:
 * <b>https://atlas.ripe.net/api/v2/measurements/my/?type=ping</b>
 *
 * @author  Sarah Kommorovski
 * @version 1.0
 * @since   2019-09-30
 */
public class Ping extends Measurement implements Serializable {
    @SerializedName("include_probe_id")
    private int includeProbeID;
    @SerializedName("packet_interval")
    private int packetInterval;

    public Ping(int includeProbeID, int packetInterval) {
        super();
        this.includeProbeID = includeProbeID;
        this.packetInterval = packetInterval;
    }

    public int getIncludeProbeID() {
        return includeProbeID;
    }

    public void setIncludeProbeID(int includeProbeID) {
        this.includeProbeID = includeProbeID;
    }

    public int getPacketInterval() {
        return packetInterval;
    }

    public void setPacketInterval(int packetIntervall) {
        this.packetInterval = packetIntervall;
    }
}
