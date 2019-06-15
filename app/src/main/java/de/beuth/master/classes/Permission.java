package de.beuth.master.classes;

public final class Permission {
    public static final String[] CREDITS = {"Get information about your credits", "Transfer credits to another user"};
    public static final String[] KEYS = {"List all of your API keys", "Update an existing API key", "Create a new API key", "Delete an API key", "Get info about an API key"};
    public static final String[] MEASUREMENTS = {"List your measurement", "Stop a running measurement", "Update an existing measurement", "Get results from a non-public measurement", "Schedule a new measurement"};
    public static final String[] PROBES = {"Get restricted information about a probe", "Get non-public measurement results", "Set probe parameters"};

    private Permission() {

    }
}
