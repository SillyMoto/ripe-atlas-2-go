package de.beuth.master.classes;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

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
