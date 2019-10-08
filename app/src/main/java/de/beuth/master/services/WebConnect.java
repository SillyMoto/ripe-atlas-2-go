package de.beuth.master.services;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONObject;

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

    /**
     * get instance, so you don't need to pass the context each time
     * @return WebConnect
     */
    public static synchronized WebConnect getInstance() {
        if (null == instance) {
            throw new IllegalStateException(WebConnect.class.getSimpleName() +
                    " is not initialized, call getInstance(...) first");
        }
        return instance;
    }

    public void postRequestReturningString(String suffixURL, JSONObject jsonObject, final CustomListener<String, String> listener) {
        String url = prefixURL + suffixURL;

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG + ": ", "postRequest Response : " + response.toString());
                        listener.getResult(response.toString());
                        listener.getError(null);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (null != error.networkResponse) {
                    Log.d(TAG + ": ", "Error Response code: " + error.networkResponse.statusCode);
                    String message = null;
                    if (error instanceof NetworkError) {
                        message = "Cannot connect to Internet! Please check your connection!";
                    } else if (error instanceof ServerError) {
                        message = "The server could not be found! Please try again after some time!";
                    } else if (error instanceof AuthFailureError) {
                        message = "You do not have permission to perform this action! Please check your API Key!";
                    } else if (error instanceof ParseError) {
                        message = "Parsing error! Please try again after some time!!";
                    } else if (error instanceof TimeoutError) {
                        message = "Connection TimeOut! Please check your internet connection.";
                    }
                    listener.getError(message);
                    //listener.getResult(null);
                }
            }
        });
        requestQueue.add(request);
    }

    public void getRequestReturningString(String suffixURL, final CustomListener<String, String> listener) {
        String url = prefixURL + suffixURL;

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG + ": ", "getRequest Response : " + response.toString());
                        listener.getResult(response.toString());
                        listener.getError(null);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (null != error.networkResponse) {
                    Log.d(TAG + ": ", "Error Response code: " + error.networkResponse.statusCode);
                    String message = null;
                    if (error instanceof NetworkError) {
                        message = "Cannot connect to Internet! Please check your connection!";
                    } else if (error instanceof ServerError) {
                        message = "The server could not be found! Please try again after some time!";
                    } else if (error instanceof AuthFailureError) {
                        message = "You do not have permission to perform this action! Please check your API Key!";
                    } else if (error instanceof ParseError) {
                        message = "Parsing error! Please try again after some time!!";
                    } else if (error instanceof TimeoutError) {
                        message = "Connection TimeOut! Please check your internet connection.";
                    }
                    listener.getError(message);
                    listener.getResult(null);
                }
            }
        });
        requestQueue.add(request);
    }

    public void getRequestReturningArray(String suffixURL, final CustomListener<JSONArray, String> listener) {
        String url = prefixURL + suffixURL;

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG + ": ", "getRequest Response : " + response.toString());
                        listener.getResult(response);
                        listener.getError(null);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (null != error.networkResponse) {
                    Log.d(TAG + ": ", "Error Response code: " + error.networkResponse.statusCode);
                    String message = null;
                    if (error instanceof NetworkError) {
                        message = "Cannot connect to Internet! Please check your connection!";
                    } else if (error instanceof ServerError) {
                        message = "The server could not be found! Please try again after some time!";
                    } else if (error instanceof AuthFailureError) {
                        message = "You do not have permission to perform this action! Please check your API Key!";
                    } else if (error instanceof ParseError) {
                        message = "Parsing error! Please try again after some time!!";
                    } else if (error instanceof TimeoutError) {
                        message = "Connection TimeOut! Please check your internet connection.";
                    }
                    Log.d(TAG + ": ", "Error Response code: " + error.networkResponse.statusCode);
                    listener.getError(message);
                    listener.getResult(null);
                }
            }
        });
        requestQueue.add(request);
    }
}

