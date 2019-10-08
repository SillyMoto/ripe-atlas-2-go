package de.beuth.master.ripeatlas2go;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;

import de.beuth.master.classes.ApiKey;
import de.beuth.master.classes.Grant;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ApiKeysTest {

    @Test
    public void gsonApiKey() {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
            String jsonString = "{\n" +
                    "            \"uuid\": \"1a451379-x-x-x-x\",\n" +
                    "            \"valid_from\": null,\n" +
                    "            \"valid_to\": null,\n" +
                    "            \"enabled\": true,\n" +
                    "            \"is_active\": true,\n" +
                    "            \"created_at\": \"2019-03-18T11:37:26\",\n" +
                    "            \"label\": \"ripe-atlas-probes\",\n" +
                    "            \"grants\": [\n" +
                    "                {\n" +
                    "                    \"permission\": \"probes.get_probe_details\",\n" +
                    "                    \"target\": {\n" +
                    "                        \"id\": \"s70593@beuth-hochschule.de\",\n" +
                    "                        \"type\": \"user\"\n" +
                    "                    }\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"permission\": \"probes.get_probe_results\",\n" +
                    "                    \"target\": {\n" +
                    "                        \"id\": \"s70593@beuth-hochschule.de\",\n" +
                    "                        \"type\": \"user\"\n" +
                    "                    }\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"permission\": \"probes.set_probe_details\",\n" +
                    "                    \"target\": {\n" +
                    "                        \"id\": \"s70593@beuth-hochschule.de\",\n" +
                    "                        \"type\": \"user\"\n" +
                    "                    }\n" +
                    "                }\n" +
                    "            ],\n" +
                    "            \"type\": \"key\"\n" +
                    "        }";
        ApiKey ak = gson.fromJson(jsonString, ApiKey.class);
        assertNotNull(ak);
        assertEquals(ak.getClass(), ApiKey.class);
        assertEquals("ripe-atlas-probes", ak.getLabel());
        assertEquals("1a451379-x-x-x-x", ak.getUuid());
    }

    @Test
    public void findApiKeyByLabel_isCorrect() {
        ArrayList<ApiKey> apiKeys = new ArrayList<>();
        ArrayList<Grant> grants = new ArrayList<>();
        grants.add(new Grant("grant"));
        apiKeys.add(new ApiKey("5adcf6b3-x-x-x-x", new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()), true, true, new Date(System.currentTimeMillis()), "LABEL", grants));
        String apiKey = null;
        for (ApiKey ak : apiKeys) {
            if (ak.getLabel().equals("LABEL")) {
                apiKey = ak.getUuid();
            }
        }
        assertEquals(apiKey, "5adcf6b3-x-x-x-x");
    }

}