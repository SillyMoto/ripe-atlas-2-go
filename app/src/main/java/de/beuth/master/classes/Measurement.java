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
import java.util.ArrayList;
import java.util.Date;

/**
 * <h1>Measurement Class!</h1>
 * <p>
 * This class implements all fields of an measurement.
 *
 * GET-Request to get all your measurements:
 * <b>https://atlas.ripe.net/api/v2/measurements/my</b>
 *
 * @author  Sarah Kommorovski
 * @version 1.0
 * @since   2019-09-30
 */
public class Measurement implements Serializable {
    @SerializedName("af")
    private int af;
    @SerializedName("creation_time")
    private Date creationTime;
    @SerializedName("credits_per_result")
    private int creditsPerResult;
    @SerializedName("description")
    private String description;
    @SerializedName("estimated_results_per_day")
    private int estimatedResultsPerDay;
    @SerializedName("group")
    private String group;
    @SerializedName("group_id")
    private int groupID;
    @SerializedName("id")
    private int ID;
    @SerializedName("in_wifi_group")
    private Boolean inWifiGroup;
    @SerializedName("interval")
    private int interval;
    @SerializedName("is_all_scheduled")
    private Boolean isAllScheduled;
    @SerializedName("is_one_off")
    private Boolean isOneOff;
    @SerializedName("is_public")
    private Boolean isPublic;
    @SerializedName("packet_interval")
    private int packetInterval;
    @SerializedName("packets")
    private int packets;
    @SerializedName("participant_count")
    private int participantCount;
    @SerializedName("probes_requested")
    private int probesRequested;
    @SerializedName("probes_scheduled")
    private int probesScheduled;
    @SerializedName("resolve_on_probe")
    private Boolean resolveOnProbe;
    @SerializedName("resolved_ips")
    private ArrayList<String> resolvedIps;
    @SerializedName("result")
    private String result;
    @SerializedName("size")
    private int size;
    @SerializedName("spread")
    private int spread;
    @SerializedName("start_time")
    private Date startTime;
    @SerializedName("status")
    private Status status;
    @SerializedName("stop_time")
    private Date stopTime;
    @SerializedName("tags")
    private ArrayList<String> tags;
    @SerializedName("target")
    private String target;
    @SerializedName("target_asn")
    private int targetASN;
    @SerializedName("target_ip")
    private String targetIP;
    @SerializedName("target_prefix")
    private String targetPrefix;
    @SerializedName("type")
    private String type;

    Measurement() {
    }

    public Measurement(int af, Date creationTime, int creditsPerResult, String description, int estimatedResultsPerDay, String group, int groupID, int ID, Boolean inWifiGroup, int interval, Boolean isAllScheduled, Boolean isOneOff, Boolean isPublic, int packetInterval, int packets, int participantCount, int probesRequested, int probesScheduled, Boolean resolveOnProbe, ArrayList<String> resolvedIps, String result, int size, int spread, Date startTime, Status status, Date stopTime, ArrayList<String> tags, String target, int targetASN, String targetIP, String targetPrefix, String type) {
        this.af = af;
        this.creationTime = creationTime;
        this.creditsPerResult = creditsPerResult;
        this.description = description;
        this.estimatedResultsPerDay = estimatedResultsPerDay;
        this.group = group;
        this.groupID = groupID;
        this.ID = ID;
        this.inWifiGroup = inWifiGroup;
        this.interval = interval;
        this.isAllScheduled = isAllScheduled;
        this.isOneOff = isOneOff;
        this.isPublic = isPublic;
        this.packetInterval = packetInterval;
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
        this.type = type;
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

    public int getGroupID() {
        return groupID;
    }

    public void setGroupID(int groupID) {
        this.groupID = groupID;
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

    public int getPacketInterval() {
        return packetInterval;
    }

    public void setPacketInterval(int packetInterval) {
        this.packetInterval = packetInterval;
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

    public ArrayList<String> getResolvedIps() {
        return resolvedIps;
    }

    public void setResolvedIps(ArrayList<String> resolvedIps) {
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}