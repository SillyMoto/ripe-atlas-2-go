package de.beuth.master.ripeatlas2go;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.MediumTest;
import android.support.test.filters.SmallTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.widget.TextView;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Date;

import de.beuth.master.classes.Measurement;
import de.beuth.master.classes.Status;
import de.beuth.master.ripeatlas2go.ui.measurement.SectionsPagerAdapter;

@MediumTest
@RunWith(AndroidJUnit4.class)
public class ShowMeasurementActivityTest {
    private static final String MSM = "measurement";
    private Activity activity;
    private Measurement msm;

    @Rule
    public ActivityTestRule<ShowMeasurementActivity> rule = new ActivityTestRule<ShowMeasurementActivity>(ShowMeasurementActivity.class) {
        @Override
        protected Intent getActivityIntent() {
            InstrumentationRegistry.getTargetContext();
            Intent intent = new Intent(Intent.ACTION_MAIN);
            ArrayList<String> ips = new ArrayList<>();
            ips.add("141.64.156.45");
            Status status = new Status(4, "stopped", new Date(1553270220));
            msm = new Measurement(4, new Date(1552918479), 3, "Ping measurement to wiki.ipv6lab.beuth-hochschule.de", 40, "https://atlas.ripe.net/api/v2/measurements/groups/20283701/?format=json", 20283701, 20283701, false, 21600, true, false, true, 1000, 3, 10, 10, 10, false, ips, "https://atlas.ripe.net/api/v2/measurements/20283701/results/?format=json", 48, 0, new Date(1552924822),
                    status, new Date(1553529622), null, "wiki.ipv6lab.beuth-hochschule.de", 680, "141.64.156.45", "141.64.0.0/15", "Ping");
            intent.putExtra(MSM, msm);
            return intent;

        }
    };

    @Before
    public void setUp() {
        activity = rule.getActivity();
    }

    @Test
    public void displayTitleTest() {
        TextView title = activity.findViewById(R.id.title_id);

        assertNotNull(title);
        assertEquals("ID " + msm.getID(), title.getText().toString());
    }


    @Test
    @SmallTest
    public void intentTest() {
        msm = (Measurement) activity.getIntent().getSerializableExtra(MSM);
        assertNotNull(msm);
        assertThat(msm, Matchers.instanceOf(Measurement.class));
        assertEquals(msm.getID(), 20283701);
    }

    @Test
    public void onCreateTest(){
        ViewPager viewPager = activity.findViewById(R.id.view_pager);
        assertNotNull(viewPager);
        assertThat(viewPager, Matchers.instanceOf(ViewPager.class));
        assertThat(viewPager.getAdapter(), Matchers.instanceOf(SectionsPagerAdapter.class));
        TabLayout tabs = activity.findViewById(R.id.tabs);
        assertEquals(tabs.getTabCount(), 2);
        SectionsPagerAdapter adapter = (SectionsPagerAdapter) viewPager.getAdapter();
    }

}
