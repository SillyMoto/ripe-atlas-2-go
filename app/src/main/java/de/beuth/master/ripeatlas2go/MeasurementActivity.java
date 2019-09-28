package de.beuth.master.ripeatlas2go;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;

import de.beuth.master.classes.ApiKey;
import de.beuth.master.classes.Measurement;
import de.beuth.master.services.ArrayListAdapter;
import de.beuth.master.services.CustomListener;
import de.beuth.master.services.ListViewMsmsAdapter;
import de.beuth.master.services.WebConnect;

public class MeasurementActivity extends AppCompatActivity {

    final String MSM_URL = "/measurements";
    final String MSM_URL_MY = "/my";
    final String MSM_URL_PING = "/?type=ping&";
    final String MSM_URL_TRACEROUTE = "/?type=traceroute&";
    final String MSM_URL_DNS = "/?type=dns&";
    final String MSM_URL_SSL = "/?type=ssl&";
    final String API_KEYS_URL = "key=";
    final String API_KEYS = "apiKeys";
    final String MSMS = "measurements";
    final String MSM = "measurement";

    Context context = this;
    /**
     * ApiKeys stored in ArrayList variable
     */
    private ArrayList<ApiKey> apiKeys;
    /**
     * Measurements stored in ArrayList variable
     */
    private ArrayList<Measurement> msms;
    /**
     * Custom BaseAdapter for the ListView
     */
    ListViewMsmsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WebConnect.getInstance(this);
        setContentView(R.layout.activity_measurement);

        // Define ApiKeys and Measurements
        apiKeys = ArrayListAdapter.getApiKeyArrayList(API_KEYS, getApplicationContext());
        System.out.println(API_KEYS + apiKeys);

        if (ArrayListAdapter.getMsmArrayList(MSMS, getApplicationContext()) != null) {
            msms = ArrayListAdapter.getMsmArrayList(MSMS, getApplicationContext());
        } else {
            msms = new ArrayList<>();
        }
        // Define ListView and Adapter
        final ListView listView = findViewById(R.id.list_view_measurements);
        adapter = new ListViewMsmsAdapter(this, msms);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object item = listView.getItemAtPosition(position);
                Intent intent = new Intent(MeasurementActivity.this, ShowMeasurementActivity.class);
                intent.putExtra(MSM, (Serializable) item);
                startActivity(intent);
            }
        });
        // Define Buttons and onClickMethods
        Button buttonAdd = findViewById(R.id.button_add_msm);
        Button buttonCreate = findViewById(R.id.button_create_msm);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopup(R.layout.popup_add_msm);
            }
        });
        buttonCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //showPopup(R.layout.popup_create_msm);
            }
        });
    }

    private void showPopup(final int resource) {
        try {
            LayoutInflater inflater = LayoutInflater.from(context);
            View popupView = inflater.inflate(resource, null);

            final Spinner spinnerMsm = popupView.findViewById(R.id.popup_spinner2);
            final Spinner spinnerKey = popupView.findViewById(R.id.popup_spinner1);
            final EditText editText = popupView.findViewById(R.id.popup_edit_text);


            // get apiKeys as a String Array for the spinner.
            String[] spinnerItems = new String[apiKeys.size()];
            for (int i = 0; i < apiKeys.size(); i++) {
                spinnerItems[i] = apiKeys.get(i).getLabel();
            }

            ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, spinnerItems);
            // set the spinners adapter to the previously created one.
            spinnerKey.setAdapter(spinnerAdapter);

            spinnerMsm.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if (position == 5) {
                        editText.setVisibility(View.VISIBLE);
                    } else {
                        editText.setVisibility(View.INVISIBLE);
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    editText.setVisibility(View.INVISIBLE);
                }
            });

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
            // set prompts.xml to alertDialog builder
            alertDialogBuilder.setView(popupView);


            // set dialog message
            alertDialogBuilder.setCancelable(false).
                    setPositiveButton("OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    switch (resource) {
                                        case R.layout.popup_add_msm:
                                            getMsm(spinnerMsm.getSelectedItemPosition(), spinnerKey.getSelectedItem().toString(), editText.getText().toString());
                                        case R.layout.popup_create_msm:
                                            dialog.cancel();
                                    }
                                }
                            })
                    .setNegativeButton("Cancel",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });

            // create alertDialog
            AlertDialog alertDialog = alertDialogBuilder.create();
            // show it
            alertDialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getMsm(final int msmType, final String apiKeyLabel, String id) {
        String suffixURL = MSM_URL;
        switch (msmType) {
            case 0:
                suffixURL += MSM_URL_MY + "/?";
                break;
            case 1:
                suffixURL += MSM_URL_MY + MSM_URL_PING;
                break;
            case 2:
                suffixURL += MSM_URL_MY + MSM_URL_TRACEROUTE;
                break;
            case 3:
                suffixURL += MSM_URL_MY + MSM_URL_DNS;
                break;
            case 4:
                suffixURL += MSM_URL_MY + MSM_URL_SSL;
                break;
            case 5:
                if (id != null && !id.isEmpty()) {
                    suffixURL += id;
                } else {
                    Toast.makeText(getApplicationContext(), "No ID inserted!", Toast.LENGTH_SHORT).show();
                    return;
                }
                break;

        }
        String apiKey = findApiKeyByLabel(apiKeyLabel);
        if (apiKey != null) {
            suffixURL += API_KEYS_URL + apiKey;
            WebConnect.getInstance().getRequestReturningString(suffixURL, new CustomListener<String, String>() {
                @Override
                public void getResult(String result) {
                    if (result != null) {
                        Log.i("getRequest\tgetResult\t", result);
                        //Gson gson = new GsonBuilder().setDateFormat(DateFormat.LONG).create(); //setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create(); //registerTypeAdapter(Date.class, new DateDeserializer()).create();
                        // Creates the json object which will manage the information received
                        GsonBuilder builder = new GsonBuilder();

                        // Register an adapter to manage the date types as long values
                        // RIPE Atlas API returns UNIX timestamp --> * 1000
                        builder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
                            public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                                return new Date(json.getAsJsonPrimitive().getAsLong() * 1000);
                            }
                        });

                        Gson gson = builder.create();

                        //JSONObject jsonResult;
                        try {
                            JSONObject jsonResult = new JSONObject(result);
                            if (msmType == 5) {
                                Measurement msm = gson.fromJson(jsonResult.toString(), Measurement.class);
                                msms.add(msm);
                            } else {
                                JSONArray results = jsonResult.getJSONArray("results");
                                for (int i = 0; i < results.length(); i++) {
                                    Measurement msm = gson.fromJson(results.getJSONObject(i).toString(), Measurement.class);
                                    msms.add(msm);
                                }
                            }
                            // SharedPreferences: Save results to MeasurementList
                            ArrayListAdapter.saveMsmArrayList(msms, MSMS, context);
                            Log.d(MSMS, ArrayListAdapter.getMsmArrayList(MSMS, context).toString());
                            adapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void getError(String error) {
                    if(error != null){
                        Toast.makeText(getApplicationContext(), error, Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } else {
            Toast.makeText(getApplicationContext(), "Add Measurement : An Api Key must selected!", Toast.LENGTH_SHORT)
                    .show();
        }

    }

    private String findApiKeyByLabel(String apiKeyLabel) {
        String apiKey = null;
        for (ApiKey ak : apiKeys) {
            if (ak.getLabel().equals(apiKeyLabel)) {
                apiKey = ak.getUuid();
                break;
            }
        }
        return apiKey;
    }
}
