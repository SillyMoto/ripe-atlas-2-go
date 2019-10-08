package de.beuth.master.ripeatlas2go;

import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.annotation.UiThreadTest;
import android.support.test.filters.MediumTest;
import android.support.test.rule.ActivityTestRule;
import android.support.v4.app.Fragment;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;

import de.beuth.master.classes.Measurement;
import de.beuth.master.classes.Status;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

public class ShowMeasurementFramesTest {
    private static final String MSM = "measurement";
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
    public void setUp(){
        rule.getActivity().getSupportFragmentManager().beginTransaction();
    }

    @Test
    @MediumTest
    public void frame1Test() {
        onView(withId(R.id.show_msm_fragment_frame1)).check(matches((isDisplayed())));
        onView(withId(R.id.overview_subtitle1_body1)).check(matches(withText("Description: " + msm.getDescription())));
        onView(withId(R.id.overview_subtitle2_body3)).check(matches(withText("is wifi group: " + msm.getInWifiGroup())));
        onView(withId(R.id.status_subtitle1_body1)).check(matches(withText("Status: " + msm.getStatus().getName())));
    }

    @Test
    @MediumTest
    public void frame2Test(){
        onView(withText("Latest Results")).perform(click());
        onView(withId(R.id.line_chart1)).check(matches(isDisplayed()));
    }

    @Test
    @UiThreadTest
    public void destroyFragment() {
        Fragment f = rule.getActivity().getSupportFragmentManager().getFragments().get(0);
        // Must be called from main thread of fragment host
        rule.getActivity().getSupportFragmentManager().beginTransaction().remove(f).commit();
        rule.getActivity().getSupportFragmentManager().executePendingTransactions();
        assertFalse("Fragment frame is added", f.isAdded());
        assertNull("Fragment frame returned non-null from getView after removal", f.getView());
    }

}
