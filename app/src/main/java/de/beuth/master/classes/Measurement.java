package de.beuth.master.classes;

import java.util.ArrayList;
import java.util.Date;

public class Measurement {
    private int af;
    private Date creationTime;
    private int creditsPerResult;
    private String description;
    private int estimatedResultsPerDay;
    private String group;
    private int groudID;
    private int ID;
    private Boolean inWifiGroup;
    private int interval;
    private Boolean isAllScheduled;
    private Boolean isOneOff;
    private Boolean isPublic;
    private int packets;
    private int participantCount;
    private int probesRequested;
    private int probesScheduled;
    private Boolean resolveOnProbe;
    private int resolvedIps;
    private String result;
    private int size;
    private int spread;
    private Date startTime;
    private Status status;
    private Date stopTime;
    private ArrayList<String> tags;
    private String target;
    private int targetASN;
    private String targetIP;
    private String targetPrefix;

    Measurement() {
    }

    public Measurement(int af, Date creationTime, int creditsPerResult, String description, int estimatedResultsPerDay, String group, int groudID, int ID, Boolean inWifiGroup, int interval, Boolean isAllScheduled, Boolean isOneOff, Boolean isPublic, int packets, int participantCount, int probesRequested, int probesScheduled, Boolean resolveOnProbe, int resolvedIps, String result, int size, int spread, Date startTime, Status status, Date stopTime, ArrayList<String> tags, String target, int targetASN, String targetIP, String targetPrefix) {
        this.af = af;
        this.creationTime = creationTime;
        this.creditsPerResult = creditsPerResult;
        this.description = description;
        this.estimatedResultsPerDay = estimatedResultsPerDay;
        this.group = group;
        this.groudID = groudID;
        this.ID = ID;
        this.inWifiGroup = inWifiGroup;
        this.interval = interval;
        this.isAllScheduled = isAllScheduled;
        this.isOneOff = isOneOff;
        this.isPublic = isPublic;
        this.packets = packets;
        this.participantCount = participantCount;
        this.probesRequested = probesRequested;
        this.probesScheduled = probesScheduled;
        this.resolveOnProbe = resolveOnProbe;
        this.resolvedIps = resolvedIps;
        this.result = result;
        this.size = size;
        this.spread = spread;
        this.startTime = startTime;
        this.status = status;
        this.stopTime = stopTime;
        this.tags = tags;
        this.target = target;
        this.targetASN = targetASN;
        this.targetIP = targetIP;
        this.targetPrefix = targetPrefix;
    }

    public int getAf() {
        return af;
    }

    public void setAf(int af) {
        this.af = af;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public int getCreditsPerResult() {
        return creditsPerResult;
    }

    public void setCreditsPerResult(int creditsPerResult) {
        this.creditsPerResult = creditsPerResult;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getEstimatedResultsPerDay() {
        return estimatedResultsPerDay;
    }

    public void setEstimatedResultsPerDay(int estimatedResultsPerDay) {
        this.estimatedResultsPerDay = estimatedResultsPerDay;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public int getGroudID() {
        return groudID;
    }

    public void setGroudID(int groudID) {
        this.groudID = groudID;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Boolean getInWifiGroup() {
        return inWifiGroup;
    }

    public void setInWifiGroup(Boolean inWifiGroup) {
        this.inWifiGroup = inWifiGroup;
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public Boolean getAllScheduled() {
        return isAllScheduled;
    }

    public void setAllScheduled(Boolean allScheduled) {
        isAllScheduled = allScheduled;
    }

    public Boolean getOneOff() {
        return isOneOff;
    }

    public void setOneOff(Boolean oneOff) {
        isOneOff = oneOff;
    }

    public Boolean getPublic() {
        return isPublic;
    }

    public void setPublic(Boolean aPublic) {
        isPublic = aPublic;
    }

    public int getPackets() {
        return packets;
    }

    public void setPackets(int packets) {
        this.packets = packets;
    }

    public int getParticipantCount() {
        return participantCount;
    }

    public void setParticipantCount(int participantCount) {
        this.participantCount = participantCount;
    }

    public int getProbesRequested() {
        return probesRequested;
    }

    public void setProbesRequested(int probesRequested) {
        this.probesRequested = probesRequested;
    }

    public int getProbesScheduled() {
        return probesScheduled;
    }

    public void setProbesScheduled(int probesScheduled) {
        this.probesScheduled = probesScheduled;
    }

    public Boolean getResolveOnProbe() {
        return resolveOnProbe;
    }

    public void setResolveOnProbe(Boolean resolveOnProbe) {
        this.resolveOnProbe = resolveOnProbe;
    }

    public int getResolvedIps() {
        return resolvedIps;
    }

    public void setResolvedIps(int resolvedIps) {
        this.resolvedIps = resolvedIps;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getSpread() {
        return spread;
    }

    public void setSpread(int spread) {
        this.spread = spread;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Date getStopTime() {
        return stopTime;
    }

    public void setStopTime(Date stopTime) {
        this.stopTime = stopTime;
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public int getTargetASN() {
        return targetASN;
    }

    public void setTargetASN(int targetASN) {
        this.targetASN = targetASN;
    }

    public String getTargetIP() {
        return targetIP;
    }

    public void setTargetIP(String targetIP) {
        this.targetIP = targetIP;
    }

    public String getTargetPrefix() {
        return targetPrefix;
    }

    public void setTargetPrefix(String targetPrefix) {
        this.targetPrefix = targetPrefix;
    }
}