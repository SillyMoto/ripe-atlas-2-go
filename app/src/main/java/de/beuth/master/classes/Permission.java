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
 * <h1>Permission Class!</h1>
 * <p>
 * This class is not implemented yet.
 * These string arrays show all available permission.
 *
 * @author  Sarah Kommorovski
 * @version 1.0
 * @since   2019-09-30
 */
public final class Permission {
    public static final String[] CREDITS = {"Get information about your credits", "Transfer credits to another user"};
    public static final String[] KEYS = {"List all of your API keys", "Update an existing API key", "Create a new API key", "Delete an API key", "Get info about an API key"};
    public static final String[] MEASUREMENTS = {"List your measurement", "Stop a running measurement", "Update an existing measurement", "Get results from a non-public measurement", "Schedule a new measurement"};
    public static final String[] PROBES = {"Get restricted information about a probe", "Get non-public measurement results", "Set probe parameters"};

    private Permission() {

    }
}
