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
package de.beuth.master.ripeatlas2go;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import de.beuth.master.classes.ApiKey;
import de.beuth.master.classes.Credit;
import de.beuth.master.services.ArrayListAdapter;
import de.beuth.master.services.CustomListener;
import de.beuth.master.services.WebConnect;

/**
 * <h1>Credit Activity!</h1>
 * <p>
 * This activity list all information about your credits.
 * You can refresh these information with the button "check credits".
 * You can also transfer credits to another user.
 *
 * @author  Sarah Kommorovski
 * @version 1.0
 * @since   2019-09-30
 */
public class CreditActivity extends AppCompatActivity {

    final String TRANSFER_URL = "/transfer";
    final String CREDIT_URL = "/credits";
    final String API_KEYS_URL = "/?key=";
    final String API_KEYS = "apiKeys";
    final String CREDIT = "credit";

    Context context = this;

    /**
     * ApiKeys stored in ArrayList variable
     */
    private ArrayList<ApiKey> apiKeys;

    /**
     * actual Credit stored in Credit variable
     */
    private Credit credit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WebConnect.getInstance(this);
        setContentView(R.layout.activity_credit);

        // Define ApiKeys and Credits
        apiKeys = ArrayListAdapter.getApiKeyArrayList(API_KEYS, getApplicationContext());
        System.out.println(API_KEYS + apiKeys);

        if (ArrayListAdapter.getCredit(CREDIT, getApplicationContext()) != null) {
            credit = ArrayListAdapter.getCredit(CREDIT, getApplicationContext());
        } else {
            credit = new Credit(0,0 , 0,0,0, Calendar.getInstance().getTime(),"",0,null, null,"","", 0);
        }
        showCredit();

        // Define Buttons and onClickMethods
        Button buttonAdd = findViewById(R.id.button_check_credits);
        Button buttonCreate = findViewById(R.id.button_transfer_credits);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopup(R.layout.popup_check_credits);
            }
        });
        buttonCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopup(R.layout.popup_transfer_credits);
            }
        });
    }

    private void showPopup(final int resource) {
        try {
            LayoutInflater inflater = LayoutInflater.from(context);
            View popupView = inflater.inflate(resource, null);

            final Spinner spinner = popupView.findViewById(R.id.popup_spinner1);
            final TextInputEditText inputEditRecipient = popupView.findViewById(R.id.input_edit_text_recipient);
            final TextInputEditText inputEditAmount = popupView.findViewById(R.id.input_edit_text_amount);

            // get apiKeys as a String Array for the spinner.
            String[] spinnerItems;
            if (apiKeys != null) {
                spinnerItems = new String[apiKeys.size()];
                for (int i = 0; i < apiKeys.size(); i++) {
                    spinnerItems[i] = apiKeys.get(i).getLabel();
                }

                ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, spinnerItems);
                // set the spinners adapter to the previously created one.
                spinner.setAdapter(spinnerAdapter);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                // set prompts.xml to alertDialog builder
                alertDialogBuilder.setView(popupView);

                // set dialog message
                alertDialogBuilder.setCancelable(false).
                        setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        switch (resource) {
                                            case R.layout.popup_check_credits:
                                                getCredits(spinner.getSelectedItem().toString());
                                                break;
                                            case R.layout.popup_transfer_credits:
                                                if(inputEditRecipient.getText() != null && inputEditRecipient.getText().length() > 0 && inputEditAmount.getText() != null && inputEditAmount.getText().length() > 0){
                                                    String recipient = inputEditRecipient.getText().toString();
                                                    int amount = Integer.valueOf(inputEditAmount.getText().toString());
                                                    transferCredits(recipient, amount, spinner.getSelectedItem().toString());
                                                }else{
                                                    Toast.makeText(getApplicationContext(), "Transfer Credits: Amount or Recipient is missing!", Toast.LENGTH_SHORT)
                                                            .show();
                                                }
                                                break;
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
            } else {
                Toast.makeText(getApplicationContext(), "No Keys available!", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getCredits(final String apiKeyLabel) {
        String apiKey = findApiKeyByLabel(apiKeyLabel);
        if (apiKey != null) {
            String suffixURL = CREDIT_URL + API_KEYS_URL + apiKey;
            WebConnect.getInstance().getRequestReturningString(suffixURL, new CustomListener<String, String>() {
                @Override
                public void getResult(String result) {
                    if (result != null && !result.isEmpty()) {
                        Log.i("getRequest\tgetResult\t", result);
                        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
                        JSONObject jsonResult;
                        try {
                            jsonResult = new JSONObject(result);
                            credit = gson.fromJson(jsonResult.toString(), Credit.class);
                            // SharedPreferences: Save results to ApiKeyList
                            ArrayListAdapter.saveCredit(credit, CREDIT, context);
                            Log.d(CREDIT, ArrayListAdapter.getCredit(CREDIT, context).toString());
                            showCredit();
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
            Toast.makeText(getApplicationContext(), "Check Credits: An Api Key must selected!", Toast.LENGTH_SHORT)
                    .show();
        }
    }

    private void transferCredits(String recipient, int amount, String apiKeyLabel) {
        String apiKey = findApiKeyByLabel(apiKeyLabel);
        JSONObject json = new JSONObject();
        try {
            json.put("recipient", recipient);
            json.put("amount", amount);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (apiKey != null) {
            String suffixURL = CREDIT_URL + TRANSFER_URL + API_KEYS_URL + apiKey;
            WebConnect.getInstance().postRequestReturningString(suffixURL, json, new CustomListener<String, String>() {
                @Override
                public void getResult(String result) {
                    if (result != null && !result.isEmpty()) {
                        Log.i("getRequest\tgetResult\t", result);
                        Toast.makeText(getApplicationContext(), "Transfer Credits: Transfer successful!", Toast.LENGTH_SHORT)
                                .show();
                    } else{
                        Toast.makeText(getApplicationContext(), "Transfer Credits: Transfer failed!", Toast.LENGTH_SHORT)
                                .show();
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
            Toast.makeText(getApplicationContext(), "Transfer Credits: An Api Key must selected!", Toast.LENGTH_SHORT)
                    .show();
        }
    }

    private void showCredit() {
        TextView view = findViewById(R.id.credits_subtitle1_body1);
        view.setText(formatIntToString(credit.getCurrentBalance()));
        view = findViewById(R.id.credits_subtitle2_body1);
        view.setText(String.valueOf(credit.getEstimatedDailyIncome()));
        view = findViewById(R.id.credits_subtitle3_body1);
        view.setText(String.valueOf(credit.getEstimatedDailyExpenditure()));
        view = findViewById(R.id.credits_subtitle4_body1);
        view.setText(String.valueOf(credit.getEstimatedDaily_balance()));
        view = findViewById(R.id.credits_subtitle5_body1);
        view.setText(String.valueOf(credit.getEstimatedRunoutSeconds()));
        view = findViewById(R.id.credits_subtitle6_body1);
        view.setText(String.valueOf(DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.MEDIUM).format(credit.getCalculationTime())));
        if(credit.getLastDateCredited() != null){
            view = findViewById(R.id.transactions_subtitle1_body1);
            view.setText(String.valueOf(DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.MEDIUM).format(credit.getLastDateCredited())));
        }

        if(credit.getLastDateDebited() != null){
            view = findViewById(R.id.transactions_subtitle2_body1);
            view.setText(String.valueOf(DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.MEDIUM).format(credit.getLastDateDebited())));
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

    public static String formatIntToString(int i){
        DecimalFormat df = (DecimalFormat) (DecimalFormat.getInstance( Locale.GERMAN ));
        DecimalFormatSymbols symbols = df.getDecimalFormatSymbols();
        symbols.setGroupingSeparator( '.' );
        df.setDecimalFormatSymbols( symbols );
        //df.applyPattern("#,###");
        return df.format(i);
    }
}
