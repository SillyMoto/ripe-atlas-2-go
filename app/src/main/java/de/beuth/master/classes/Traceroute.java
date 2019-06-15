package de.beuth.master.classes;

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
