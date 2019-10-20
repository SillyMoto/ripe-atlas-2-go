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

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.Date;

import de.beuth.master.classes.Measurement;
import de.beuth.master.ripeatlas2go.ui.measurement.SectionsPagerAdapter;
import de.beuth.master.services.CustomListener;
import de.beuth.master.services.WebConnect;

/**
 * <h1>ShowMeasurement Activity!</h1>
 * <p>
 * This activity contains two fragments:
 *
 * @author Sarah Kommorovski
 * @version 1.0
 * @see de.beuth.master.ripeatlas2go.ui.measurement.ShowMeasurementFrame1
 * @see de.beuth.master.ripeatlas2go.ui.measurement.ShowMeasurementFrame2
 * @since 2019-09-30
 */
public class ShowMeasurementActivity extends AppCompatActivity {

    final String MSM = "measurement";
    Measurement msm;
    SectionsPagerAdapter sectionsPagerAdapter;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WebConnect.getInstance(this);
        setContentView(R.layout.activity_show_measurement);
        sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        // Get MSM from Intent.getExtra()
        msm = (Measurement) getIntent().getSerializableExtra(MSM);
        TextView title = findViewById(R.id.title_id);
        String id = "ID " + msm.getID();
        title.setText(id);

        final ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        final TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        // Refresh Measurement on pull
        final SwipeRefreshLayout swipeRefreshLayout = findViewById(R.id.pull_to_refresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshMsm();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        // smooth swiping between tabs
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabs) {

            @Override
            public void onPageSelected(int position) {
                // disable SwipeRefreshLayout for the second frame
                if (position == 1) {
                    swipeRefreshLayout.setEnabled(false);
                } else swipeRefreshLayout.setEnabled(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                swipeRefreshLayout.setEnabled(state == ViewPager.SCROLL_STATE_IDLE);
            }
        });
    }

    /**
     * update the actual measurement
     */
    private void refreshMsm() {
        // start string at position "m", the first part of URL is implemented in WebConnect
        // and cut the results
        String suffixURL = msm.getResult().substring(msm.getResult().indexOf("/m"), msm.getResult().indexOf("/r"));

        WebConnect.getInstance().getRequestReturningString(suffixURL, new CustomListener<String, String>() {
            @Override
            public void getResult(String result) {
                if (result != null) {
                    Log.i("getRequest\tgetResult\t", result);
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
                        msm = gson.fromJson(jsonResult.toString(), Measurement.class);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    getIntent().putExtra(MSM, msm);
                    sectionsPagerAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void getError(String error) {
                if (error != null) {
                    Toast.makeText(getApplication(), error, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
    }

}