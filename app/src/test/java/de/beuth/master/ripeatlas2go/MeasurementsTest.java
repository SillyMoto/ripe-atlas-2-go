package de.beuth.master.ripeatlas2go;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.lang.reflect.Type;
import java.util.Date;

import de.beuth.master.classes.Measurement;

import static org.junit.Assert.*;

public class MeasurementsTest {

    private Measurement msm;

    @Before
    public void setUp(){
        String jsonString = "{\n" +
                "            \"af\": 4,\n" +
                "            \"creation_time\": 1285891200,\n" +
                "            \"credits_per_result\": 3,\n" +
                "            \"description\": null,\n" +
                "            \"estimated_results_per_day\": 0,\n" +
                "            \"group\": null,\n" +
                "            \"group_id\": null,\n" +
                "            \"id\": 1001,\n" +
                "            \"in_wifi_group\": false,\n" +
                "            \"include_probe_id\": null,\n" +
                "            \"interval\": 240,\n" +
                "            \"is_all_scheduled\": true,\n" +
                "            \"is_oneoff\": false,\n" +
                "            \"is_public\": true,\n" +
                "            \"packet_interval\": null,\n" +
                "            \"packets\": 3,\n" +
                "            \"participant_count\": null,\n" +
                "            \"probes_requested\": null,\n" +
                "            \"probes_scheduled\": null,\n" +
                "            \"resolve_on_probe\": false,\n" +
                "            \"resolved_ips\": null,\n" +
                "            \"result\": \"https://atlas.ripe.net/api/v2/measurements/1001/results/\",\n" +
                "            \"size\": null,\n" +
                "            \"spread\": null,\n" +
                "            \"start_time\": 1285891200,\n" +
                "            \"status\": {\n" +
                "                \"name\": \"Ongoing\",\n" +
                "                \"id\": 2,\n" +
                "                \"when\": null\n" +
                "            },\n" +
                "            \"stop_time\": 1577836800,\n" +
                "            \"tags\": [],\n" +
                "            \"target\": \"k.root-servers.net\",\n" +
                "            \"target_asn\": null,\n" +
                "            \"target_ip\": \"193.0.14.129\",\n" +
                "            \"target_prefix\": null,\n" +
                "            \"type\": \"ping\"\n" +
                "        }";

        GsonBuilder builder = new GsonBuilder();

        // Register an adapter to manage the date types as long values
        // RIPE Atlas API returns UNIX timestamp --> * 1000
        builder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
            public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                return new Date(json.getAsJsonPrimitive().getAsLong() * 1000);
            }
        });

        Gson gson = builder.create();

        msm = gson.fromJson(jsonString, Measurement.class);
    }

    @Test
    public void gsonMsm(){
        assertNotNull(msm);
        assertEquals(Measurement.class, msm.getClass());
        assertEquals(1001, msm.getID());
        assertFalse(msm.isInWifiGroup());
        assertEquals("https://atlas.ripe.net/api/v2/measurements/1001/results/", msm.getResult());
        assertEquals("k.root-servers.net", msm.getTarget());
        assertEquals("193.0.14.129", msm.getTargetIP());
        assertEquals("ping", msm.getType());
    }

    @Test
    public void checkSubstring(){
        String substring =  msm.getResult().substring(msm.getResult().indexOf("/m"), msm.getResult().indexOf("/r"));
        assertEquals("/measurements/1001", substring);
    }
}
