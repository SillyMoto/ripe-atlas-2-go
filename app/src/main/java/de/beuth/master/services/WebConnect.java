package de.beuth.master.services;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class WebConnect {

    private static final String TAG = "NetworkManager";
    private static WebConnect instance = null;

    private static final String prefixURL = "https://atlas.ripe.net/api/v2";

    //for Volley API
    private RequestQueue requestQueue;

    private WebConnect(Context context) {
        requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        //other stuff if you need
    }

    public static synchronized WebConnect getInstance(Context context) {
        if (null == instance)
            instance = new WebConnect(context);
        return instance;
    }

    //this is so you don't need to pass context each time
    public static synchronized WebConnect getInstance() {
        if (null == instance) {
            throw new IllegalStateException(WebConnect.class.getSimpleName() +
                    " is not initialized, call getInstance(...) first");
        }
        return instance;
    }

    public void postRequestReturningString(String suffixURL, Object param1, final CustomListener<String> listener) {

        String url = prefixURL + suffixURL;

        Map<String, Object> jsonParams = new HashMap<>();
        jsonParams.put("param1", param1);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(jsonParams),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG + ": ", "postRequest Response : " + response.toString());
                        listener.getResult(response.toString());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (null != error.networkResponse) {
                    Log.d(TAG + ": ", "Error Response code: " + error.networkResponse.statusCode);
                    //listener.getResult(false); ?!?!?!?
                    listener.getResult(null);
                }
            }
        });

        requestQueue.add(request);
    }

    public void getRequestReturningString(String suffixURL, final CustomListener<String> listener) {
        String url = prefixURL + suffixURL; //"/keys/?key=" + "5adcf6b3-ef7a-4acd-ad6a-b5c38d892a43";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG + ": ", "getRequest Response : " + response.toString());
                        listener.getResult(response.toString());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (null != error.networkResponse) {
                    Log.d(TAG + ": ", "Error Response code: " + error.networkResponse.statusCode);
                    //listener.getResult(false); ?!?!?!?
                    listener.getResult(null);
                }
            }
        });

        requestQueue.add(request);
    }
}

