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

/**
 * <h1>Traceroute Measurement Class!</h1>
 * <p>
 * This class should be implemented for the specific fields for an traceroute measurement.
 * It's not implemented yet.
 *
 * GET-Request to get all ping measurements:
 * <b>https://atlas.ripe.net/api/v2/measurements/my/?type=traceroute</b>
 *
 * @author  Sarah Kommorovski
 * @version 1.0
 * @since   2019-09-30
 */
public class Traceroute extends Measurement {
    private int destinationOptionSize;
    private Boolean dontFragment;
    private Boolean duplicateTimeout;
    private int firstHop;
    private int hopByHopOptionSize;
    private int maxHops;
    private int paris;
    private int port;
    private Protocol protocol;
    private int responseTimeout;
    private String trafficClass;

    public Traceroute(int destinationOptionSize, Boolean dontFragment, Boolean duplicateTimeout, int firstHop, int hopByHopOptionSize, int maxHops, int paris, int port, Protocol protocol, int responseTimeout, String trafficClass) {
        super();
        this.destinationOptionSize = destinationOptionSize;
        this.dontFragment = dontFragment;
        this.duplicateTimeout = duplicateTimeout;
        this.firstHop = firstHop;
        this.hopByHopOptionSize = hopByHopOptionSize;
        this.maxHops = maxHops;
        this.paris = paris;
        this.port = port;
        this.protocol = protocol;
        this.responseTimeout = responseTimeout;
        this.trafficClass = trafficClass;
    }

    public int getDestinationOptionSize() {
        return destinationOptionSize;
    }

    public void setDestinationOptionSize(int destinationOptionSize) {
        this.destinationOptionSize = destinationOptionSize;
    }

    public Boolean getDontFragment() {
        return dontFragment;
    }

    public void setDontFragment(Boolean dontFragment) {
        this.dontFragment = dontFragment;
    }

    public Boolean getDuplicateTimeout() {
        return duplicateTimeout;
    }

    public void setDuplicateTimeout(Boolean duplicateTimeout) {
        this.duplicateTimeout = duplicateTimeout;
    }

    public int getFirstHop() {
        return firstHop;
    }

    public void setFirstHop(int firstHop) {
        this.firstHop = firstHop;
    }

    public int getHopByHopOptionSize() {
        return hopByHopOptionSize;
    }

    public void setHopByHopOptionSize(int hopByHopOptionSize) {
        this.hopByHopOptionSize = hopByHopOptionSize;
    }

    public int getMaxHops() {
        return maxHops;
    }

    public void setMaxHops(int maxHops) {
        this.maxHops = maxHops;
    }

    public int getParis() {
        return paris;
    }

    public void setParis(int paris) {
        this.paris = paris;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public Protocol getProtocol() {
        return protocol;
    }

    public void setProtocol(Protocol protocol) {
        this.protocol = protocol;
    }

    public int getResponseTimeout() {
        return responseTimeout;
    }

    public void setResponseTimeout(int responseTimeout) {
        this.responseTimeout = responseTimeout;
    }

    public String getTrafficClass() {
        return trafficClass;
    }

    public void setTrafficClass(String trafficClass) {
        this.trafficClass = trafficClass;
    }
}
