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

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import de.beuth.master.classes.Measurement;
import de.beuth.master.ripeatlas2go.ui.measurement.SectionsPagerAdapter;
import de.beuth.master.services.WebConnect;

/**
 * <h1>ShowMeasurement Activity!</h1>
 * <p>
 * This activity contains two fragments:
 * @see de.beuth.master.ripeatlas2go.ui.measurement.ShowMeasurementFrame1
 * @see de.beuth.master.ripeatlas2go.ui.measurement.ShowMeasurementFrame2
 *
 * @author  Sarah Kommorovski
 * @version 1.0
 * @since   2019-09-30
 */
public class ShowMeasurementActivity extends AppCompatActivity {

    final String MSM = "measurement";
    Measurement msm;
    SectionsPagerAdapter sectionsPagerAdapter;

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
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
    }

    @Override
    public void onBackPressed() {
        finish();
    }

}