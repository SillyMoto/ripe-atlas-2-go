package de.beuth.master.classes;

public class Ping extends Measurement {
    private int includeProbeID;
    private int packetIntervall;

    public Ping(int includeProbeID, int packetIntervall) {
        super();
        this.includeProbeID = includeProbeID;
        this.packetIntervall = packetIntervall;
    }

    public int getIncludeProbeID() {
        return includeProbeID;
    }

    public void setIncludeProbeID(int includeProbeID) {
        this.includeProbeID = includeProbeID;
    }

    public int getPacketIntervall() {
        return packetIntervall;
    }

    public void setPacketIntervall(int packetIntervall) {
        this.packetIntervall = packetIntervall;
    }
}
