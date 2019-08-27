package de.beuth.master.ripeatlas2go;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

import de.beuth.master.classes.ApiKey;
import de.beuth.master.classes.Permission;
import de.beuth.master.services.ArrayListAdapter;
import de.beuth.master.services.CustomListener;
import de.beuth.master.services.ListViewApiKeysAdapter;
import de.beuth.master.services.WebConnect;

public class ApiKeysActivity extends AppCompatActivity {

    final String KEYS_URL = "/keys";
    final String API_KEYS_URL = "/?key=";
    final String API_KEYS = "apiKeys";
    Context context = this;
    PopupWindow popUp;
    /**
     * ApiKeys stored in ArrayList variable
     */
    private ArrayList<ApiKey> apiKeys;
    /**
     * Custom BaseAdapter for the ListView
     */
    ListViewApiKeysAdapter adapter;

    //qr code scanner object
    private IntentIntegrator qrScan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WebConnect.getInstance(this);
        setContentView(R.layout.activity_api_keys);

        // Define ListView and Adapter
        apiKeys = ArrayListAdapter.getApiKeyArrayList(API_KEYS, getApplicationContext());
        System.out.println(API_KEYS + apiKeys + getApplicationContext());
        //apiKeys = new ArrayList<>();
        final ListView listView = findViewById(R.id.list_view_api_keys);
        adapter = new ListViewApiKeysAdapter(this, apiKeys);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object item = listView.getItemAtPosition(position);
                showPopup(R.layout.popup_show_api_key, item);
            }
        });
        // Define Buttons and onClickMethods
        Button buttonAdd = findViewById(R.id.button_add_api_key);
        Button buttonCreate = findViewById(R.id.button_create_api_key);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopup(R.layout.popup_add_api_key, null);
            }
        });
        buttonCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopup(R.layout.popup_create_api_key, null);
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (popUp != null && popUp.isShowing()) {
            popUp.dismiss();
        } else {
            super.onBackPressed();
        }
    }

    private void showPopup(final int resource, Object item) {
        try {
            LayoutInflater inflater = LayoutInflater.from(context);
            View popupView = inflater.inflate(resource, null);
            final EditText inputText = popupView.findViewById(R.id.textInputEditTextUUID);

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
            // set prompts.xml to alertDialog builder
            alertDialogBuilder.setView(popupView);

            //intializing scan object
            qrScan = new IntentIntegrator(this);

            if (resource == R.layout.popup_show_api_key && item != null) {
                ApiKey apiKey = (ApiKey) item;
                TextView view = popupView.findViewById(R.id.popup_body1);
                view.setText(apiKey.getUuid());
                view = popupView.findViewById(R.id.popup_body2);
                if (apiKey.getCreatedAt() != null) {
                    view.setText(DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.MEDIUM).format(apiKey.getCreatedAt()));
                } else {
                    view.setText("-");
                }
                view = popupView.findViewById(R.id.popup_body3);
                view.setText(apiKey.getLabel());
                view = popupView.findViewById(R.id.popup_body4);
                view.setText(DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.MEDIUM).format(System.currentTimeMillis()));
                view = popupView.findViewById(R.id.popup_body5);
                StringBuilder permission = new StringBuilder();
                if (apiKey.getPermissions() != null) {
                    for (String s : apiKey.getPermissions()) {
                        permission.append(s).append(",");
                    }
                    view.setText(permission);
                } else {
                    view.setText("-");
                }
                view = popupView.findViewById(R.id.popup_body6);
                if (apiKey.getValidFrom() != null) {
                    view.setText(DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.MEDIUM).format(apiKey.getValidFrom()));
                } else {
                    view.setText("-");
                }
                view = popupView.findViewById(R.id.popup_body7);
                if (apiKey.getValidTo() != null) {
                    view.setText(DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.MEDIUM).format(apiKey.getValidTo()));
                } else {
                    view.setText("-");
                }
                view = popupView.findViewById(R.id.popup_body8);
                view.setText(apiKey.getEnabled().toString());
            }

            // set dialog message
            alertDialogBuilder.setCancelable(false).
                    setPositiveButton("OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    switch (resource) {
                                        case R.layout.popup_add_api_key:
                                            getApiKeys(inputText.getText().toString());
                                        case R.layout.popup_create_api_key:
                                            dialog.cancel();
                                        case R.layout.popup_show_api_key:
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
            if(resource == R.layout.popup_add_api_key){
                //set Scan Button
                alertDialogBuilder.setNeutralButton("Scan QR-Code",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                qrScan.initiateScan();
                            }
                        });
            }

            // create alertDialog
            AlertDialog alertDialog = alertDialogBuilder.create();
            // show it
            alertDialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Getting the scan results
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            //if qrcode has nothing in it
            if (result.getContents() == null) {
                Toast.makeText(this, "Result Not Found", Toast.LENGTH_LONG).show();
            } else {
                //if qr contains data
                Toast.makeText(this, result.getContents(), Toast.LENGTH_SHORT).show();
                getApiKeys(result.getContents());
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void getApiKeys(final String apiKey) {
        String suffixURL = KEYS_URL + API_KEYS_URL + "5adcf6b3-ef7a-4acd-ad6a-b5c38d892a43"; // apikey;
        WebConnect.getInstance().getRequestReturningString(suffixURL, new CustomListener<String>() {
            @Override
            public void getResult(String result) {
                if (!result.isEmpty()) {
                    Log.i("getRequest\tgetResult\t", result);
                    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
                    JSONObject jsonResult;
                    try {
                        jsonResult = new JSONObject(result);
                        JSONArray results = jsonResult.getJSONArray("results");
                        for (int i = 0; i < results.length(); i++) {
                            ApiKey ak = gson.fromJson(results.getJSONObject(i).toString(), ApiKey.class);
                            apiKeys.add(ak);
                        }
                        // SharedPreferences: Save results to ApiKeyList
                        ArrayListAdapter.saveApiKeyArrayList(apiKeys, API_KEYS, context);
                        Log.d(API_KEYS, ArrayListAdapter.getApiKeyArrayList(API_KEYS, context).toString());
                        adapter.notifyDataSetChanged();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private void createApiKeys(final String apiKey, String label, Date validFrom, Date validTo, Permission permission) {
        String suffixURL = KEYS_URL + API_KEYS_URL + "5adcf6b3-ef7a-4acd-ad6a-b5c38d892a43"; // apikey;
        WebConnect.getInstance().getRequestReturningString(suffixURL, new CustomListener<String>() {
            @Override
            public void getResult(String result) {
                if (!result.isEmpty()) {
                    Log.i("getRequest\tgetResult\t", result);
                    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
                    JSONObject jsonResult;
                    try {
                        jsonResult = new JSONObject(result);
                        JSONArray results = jsonResult.getJSONArray("results");
                        for (int i = 0; i < results.length(); i++) {
                            ApiKey ak = gson.fromJson(results.getJSONObject(i).toString(), ApiKey.class);
                            apiKeys.add(ak);
                        }
                        Log.d("ApiKeys Added Keys\t", apiKeys.toString());
                        //add ApiKeys to ApiKeyList
                        //createApiKeyList();
                        adapter.notifyDataSetChanged();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

}

