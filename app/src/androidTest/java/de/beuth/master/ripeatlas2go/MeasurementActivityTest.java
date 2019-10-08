package de.beuth.master.ripeatlas2go;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.test.espresso.matcher.RootMatchers;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.MediumTest;
import android.support.test.filters.SmallTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Date;

import de.beuth.master.classes.ApiKey;
import de.beuth.master.classes.Grant;
import de.beuth.master.classes.Measurement;
import de.beuth.master.classes.Status;
import de.beuth.master.services.ArrayListAdapter;
import de.beuth.master.services.DateParser;
import de.beuth.master.services.ListViewMsmsAdapter;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.isDialog;
import static android.support.test.espresso.matcher.ViewMatchers.isChecked;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isNotChecked;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class MeasurementActivityTest {
    private static final String API_KEYS = "apiKeys";
    private static final String MSMS = "measurements";
    private Activity activity;
    private static Context context;
    private ListView listView;

    private Button buttonAdd;
    private Button buttonCreate;
    private ArrayList<Measurement> msms;

    @Rule
    public ActivityTestRule<MeasurementActivity> rule = new ActivityTestRule<>(MeasurementActivity.class);

    @Before
    public void setUp() {
        activity = rule.getActivity();
        context = activity.getApplicationContext();
        mockApiKey();
        mockMeasurement();
        Intent intent = new Intent();
        rule.launchActivity(intent);
        activity = rule.getActivity();
        context = activity.getApplicationContext();
        listView = activity.findViewById(R.id.list_view_measurements);
        buttonAdd = activity.findViewById(R.id.button_add_msm);
        buttonCreate = activity.findViewById(R.id.button_create_msm);
    }

    private void mockApiKey() {
        ArrayList<Grant> grants = new ArrayList<>();
        grants.add(new Grant("getMeasurements"));
        ApiKey apiKey = new ApiKey("5adcf6b3-x-x-x-x", new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()), true, true, new Date(System.currentTimeMillis()), "LABEL", grants);
        ArrayList<ApiKey> apiKeys = new ArrayList<>();
        apiKeys.add(apiKey);
        ArrayListAdapter.saveApiKeyArrayList(apiKeys, API_KEYS, context);
    }

    private void mockMeasurement(){
        Measurement msm = new Measurement(4, new Date(1552918479), 3, "Ping measurement to wiki.ipv6lab.beuth-hochschule.de", 40, "https://atlas.ripe.net/api/v2/measurements/groups/20283701/?format=json", 20283701, 20283701, false, 21600, true, false, true, 1000, 3, 10, 10, 10, false, new ArrayList<String>() {{
            add("141.64.156.45");
        }}, "https://atlas.ripe.net/api/v2/measurements/20283701/results/?format=json", 48, 0, new Date(1552924822),
                new Status(4, "stopped", new Date(1553270220)), new Date(1553529622), null, "wiki.ipv6lab.beuth-hochschule.de", 680, "141.64.156.45", "141.64.0.0/15", "Ping");
        msms = new ArrayList<>();
        msms.add(msm);
        ArrayListAdapter.saveMsmArrayList(msms, MSMS, context);
    }

    @AfterClass
    public static void clean(){
        ArrayListAdapter.saveApiKeyArrayList(null, API_KEYS, context);
        ArrayListAdapter.saveMsmArrayList(null, MSMS, context);
    }

    @Test
    @SmallTest
    public void getMsmList_isNull() {
        msms = null;
        ArrayListAdapter.saveMsmArrayList(msms, MSMS, context);
        msms = ArrayListAdapter.getMsmArrayList(MSMS, context);
        assertNull(msms);
    }

    @Test
    @SmallTest
    public void getMsmArrayList_notNull() {
        msms = ArrayListAdapter.getMsmArrayList(MSMS, context);
        assertEquals(msms.get(0).getID(), 20283701);
        assertEquals(msms.get(0).getDescription(), "Ping measurement to wiki.ipv6lab.beuth-hochschule.de");
        assertEquals(msms.get(0).getStatus().getId(), 4);
    }

    @Test
    @SmallTest
    public void onCreate_ListView() {
        getMsmArrayList_notNull();
        assertNotNull(listView);
        assertThat(listView, instanceOf(ListView.class));
        ListAdapter adapter = listView.getAdapter();
        assertNotNull(adapter);
        assertThat(adapter, instanceOf(ListViewMsmsAdapter.class));
    }

    @Test
    @SmallTest
    public void onCreate_Buttons() {
        assertNotNull(buttonAdd);
        assertNotNull(buttonCreate);
        assertTrue(buttonAdd.isClickable());
        assertTrue(buttonCreate.isClickable());
        assertTrue(buttonAdd.hasOnClickListeners());
        assertTrue(buttonCreate.hasOnClickListeners());
    }

    @Test
    @MediumTest
    public void addMsm_isCorrect() {
        onView(withId(R.id.button_add_msm)).perform(click());
        onView(withId(R.id.popup_add_msm)).check(matches(isDisplayed()));
        onView(withId(R.id.popup_spinner2)).perform(click());
        // onData(allOf(is(instanceOf(String.class)), is("Specific Measurement by ID")).inRoot(RootMatchers.isPlatformPopup()).perform(click());
        // Error: Waited for the root of the view hierarchy to have window focus and not be requesting layout for over 10 seconds.
        onData(anything()).inRoot(RootMatchers.isPlatformPopup()).atPosition(5).perform(click());
        onView(withId(R.id.popup_spinner2)).check(matches(withSpinnerText(containsString("Specific Measurement by ID"))));

        onView(withId(R.id.popup_edit_text)).perform(typeText("12356")).check(matches(withText("12356")));
        int oldMsms = listView.getAdapter().getCount();
        onView(withText("CANCEL")).inRoot(isDialog()).check(matches(isDisplayed())).perform(click());
        assertEquals(listView.getAdapter().getCount(), oldMsms);
    }

    @Test
    @MediumTest
    public void createMsm_isCorrect() {
        onView(withId(R.id.button_create_msm)).perform(click());
        onView(withId(R.id.popup_create_msm)).check(matches(isDisplayed()));
        onView(withId(R.id.textInputEditTextDescription)).perform(typeText("DescriptionTest")).check(matches(withText("DescriptionTest")));
        onView(withId(R.id.radioButtonIPv4)).perform(click()).check(matches(isChecked()));
        onView(withId(R.id.radioButtonIPv6)).check(matches(isNotChecked()));
        onView(withId(R.id.textInputEditTextTarget)).perform(typeText("Test")).check(matches(withText("Test")));
        onView(withId(R.id.switch_one_off)).perform(click()).check(matches(isChecked()));
        onView(withId(R.id.textInputEditTextStartTime)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
        onView(withId(R.id.textInputEditTextStopTime)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.INVISIBLE)));
    }

    @Test
    @MediumTest
    public void createMsm_RecurringMsm() {
        String startTime = "2019-10-06T10:00:00Z";
        String stopTime = "2019-10-06T12:00:00Z";
        onView(withId(R.id.button_create_msm)).perform(click());
        onView(withId(R.id.popup_create_msm)).check(matches(isDisplayed()));
        // Default Switch is unchecked
        onView(withId(R.id.switch_one_off)).check(matches(isNotChecked()));
        onView(withId(R.id.textInputEditTextStartTime)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
        onView(withId(R.id.textInputEditTextStopTime)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
        // Check DateParser.java
        assertTrue(DateParser.isValidFormat(startTime));
        assertTrue(DateParser.isValidFormat(stopTime));
        assertFalse(DateParser.isValidFormat("20191006T10:00:00Z"));
    }

    @Test
    @MediumTest
    public void createMsm_checkSpinnerType() {
        onView(withId(R.id.button_create_msm)).perform(click());
        onView(withId(R.id.popup_create_msm)).check(matches(isDisplayed()));
        // AREA
        onView(withId(R.id.popup_spinner_probes_type)).perform(click());
        onData(anything()).inRoot(RootMatchers.isPlatformPopup()).atPosition(0).perform(click());
        onView(withId(R.id.popup_spinner_probes_type)).check(matches(withSpinnerText(containsString("Area"))));
        onView(withId(R.id.popup_spinner_probes_type)).perform(click());
        onData(anything()).inRoot(RootMatchers.isPlatformPopup()).atPosition(1).perform(click());
        onView(withId(R.id.popup_spinner_probes_type)).check(matches(withSpinnerText(containsString("Country"))));
        assertTrue("US".matches("^[a-zA-Z]{2}$"));
        onView(withId(R.id.popup_spinner_probes_type)).perform(click());
        onData(anything()).inRoot(RootMatchers.isPlatformPopup()).atPosition(2).perform(click());
        onView(withId(R.id.popup_spinner_probes_type)).check(matches(withSpinnerText(containsString("Prefix"))));
        assertTrue("192.16.0.0/24".matches("^(?:[0-9]{1,3}\\.){3}[0-9]{1,3}\\/[0-9]{1,2}$"));
        onView(withId(R.id.popup_spinner_probes_type)).perform(click());
        onData(anything()).inRoot(RootMatchers.isPlatformPopup()).atPosition(3).perform(click());
        onView(withId(R.id.popup_spinner_probes_type)).check(matches(withSpinnerText(containsString("ASN"))));
        assertTrue("680".matches("^[0-9]*(,[0-9]*)*$"));
        onView(withId(R.id.popup_spinner_probes_type)).perform(click());
        onData(anything()).inRoot(RootMatchers.isPlatformPopup()).atPosition(4).perform(click());
        onView(withId(R.id.popup_spinner_probes_type)).check(matches(withSpinnerText(containsString("Probes"))));
        assertTrue("55,19,252".matches("^[0-9]*(,[0-9]*)*$"));
        onView(withId(R.id.popup_spinner_probes_type)).perform(click());
        onData(anything()).inRoot(RootMatchers.isPlatformPopup()).atPosition(5).perform(click());
        onView(withId(R.id.popup_spinner_probes_type)).check(matches(withSpinnerText(containsString("Measurements"))));
        assertTrue("1000002".matches("^[0-9]*(,[0-9]*)*$"));
    }

    @Test
    @MediumTest
    public void clickMsmAndOpenNewActivity_isCorrect() {
        onData(anything()).inAdapterView(withId(R.id.list_view_measurements)).atPosition(0).perform(click());
        onView(withId(R.id.show_msm_fragment_frame1)).check(matches(isDisplayed()));
    }
}
