package de.beuth.master.ripeatlas2go;

import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.annotation.UiThreadTest;
import android.support.test.filters.SmallTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.github.mikephil.charting.charts.LineChart;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Date;

import de.beuth.master.classes.Measurement;
import de.beuth.master.classes.Status;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

@RunWith(AndroidJUnit4.class)
public class ShowMeasurementFrame2Test {
    private static final String MSM = "measurement";
    private ShowMeasurementActivity activity;
    private FragmentTransaction transaction;
    private FragmentManager manager;
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
    public void setUp() throws Throwable {
        rule.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                activity = rule.getActivity();
                manager = activity.getSupportFragmentManager();
                transaction = manager.beginTransaction();
                transaction.commitNowAllowingStateLoss();
                manager.executePendingTransactions();
            }
        });
        getInstrumentation().waitForIdleSync();
    }

    @Test
    @SmallTest
    public void onAttachTest() throws Throwable {
        executePendingTransactions(manager);
        Fragment f = manager.getFragments().get(1);
        assertTrue("Fragment frame2 is not added", f.isAdded());
        assertNotNull("Fragment frame2 hasn't create View after adding", f.getView());
    }

    @Test
    @SmallTest
    public void onCreateTest() throws Throwable{
        executePendingTransactions(manager);
        Fragment f = manager.getFragments().get(1);
        assertNotNull(f.getActivity());
        LineChart chart = f.getActivity().findViewById(R.id.line_chart1);
        assertThat(chart, Matchers.<LineChart>instanceOf(LineChart.class));
    }

    @Test
    @UiThreadTest
    public void destroyFragment() throws Throwable {
        executePendingTransactions(manager);
        Fragment f = manager.getFragments().get(1);
        // Must be called from main thread of fragment host
        manager.beginTransaction().remove(f).commitNowAllowingStateLoss();
        executePendingTransactions(manager);
        assertFalse("Fragment frame2 is added", f.isAdded());
        assertNull("Fragment frame2 returned non-null from getView after removal", f.getView());
    }

    private void executePendingTransactions(final FragmentManager fm) throws Throwable {
        rule.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                fm.executePendingTransactions();
            }
        });
    }
}