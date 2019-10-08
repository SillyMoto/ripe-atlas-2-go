package de.beuth.master.ripeatlas2go;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import de.beuth.master.classes.Measurement;
import de.beuth.master.ripeatlas2go.ui.measurement.SectionsPagerAdapter;
import de.beuth.master.services.WebConnect;

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