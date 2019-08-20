package de.beuth.master.ripeatlas2go;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import de.beuth.master.classes.Measurement;
import de.beuth.master.ripeatlas2go.ui.measurement.SectionsPagerAdapter;
import de.beuth.master.services.CustomListener;
import de.beuth.master.services.WebConnect;

public class ActivityShowMeasurement extends AppCompatActivity {

    final String MSM = "measurement";
    Measurement msm;
    SectionsPagerAdapter sectionsPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WebConnect.getInstance(this);
        setContentView(R.layout.activity_show_measurement);
        // SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        // Get MSM from Intent.getExtra()
        msm = (Measurement) getIntent().getSerializableExtra(MSM);
        TextView title = findViewById(R.id.title_id);
        //String id = "ID: " + msm.getID();
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