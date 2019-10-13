package de.beuth.master.ripeatlas2go;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.support.test.filters.MediumTest;
import android.support.test.filters.SmallTest;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.rule.ActivityTestRule;
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
import de.beuth.master.services.ArrayListAdapter;
import de.beuth.master.services.ListViewApiKeysAdapter;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.isDialog;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class ApiKeysActivityTest {

    private static final String API_KEYS = "apiKeys";
    final private String UUID = "5adcf6b3-x-x-x-x";

    private static Context context;
    private ArrayList<ApiKey> apiKeys;
    private ApiKeysActivity activity;

    private ListView listView;
    private Button buttonAdd;
    private Button buttonCreate;

    @Rule
    public ActivityTestRule<ApiKeysActivity> rule = new ActivityTestRule<>(ApiKeysActivity.class);

    @Before
    public void setUp() {
        activity = rule.getActivity();
        context = activity.getApplicationContext();
        mockApiKey();
        Intent intent = new Intent();
        rule.launchActivity(intent);
        activity = rule.getActivity();
        context = activity.getApplicationContext();
        listView = activity.findViewById(R.id.list_view_api_keys);
        buttonAdd = activity.findViewById(R.id.button_add_api_key);
        buttonCreate = activity.findViewById(R.id.button_add_api_key);
    }

    private void mockApiKey() {
        ArrayList<Grant> grants = new ArrayList<>();
        grants.add(new Grant("getMeasurements"));
        ApiKey apiKey = new ApiKey(UUID, new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()), true, true, new Date(System.currentTimeMillis()), "LABEL", grants);
        apiKeys = new ArrayList<>();
        apiKeys.add(apiKey);
        ArrayListAdapter.saveApiKeyArrayList(apiKeys, API_KEYS, context);
    }

    @AfterClass
    public static void clean(){
        ArrayListAdapter.saveApiKeyArrayList(null, API_KEYS, context);
    }

    @Test
    @SmallTest
    public void getApiKeyArrayList_isNull() {
        apiKeys = null;
        ArrayListAdapter.saveApiKeyArrayList(apiKeys, API_KEYS, context);
        apiKeys = ArrayListAdapter.getApiKeyArrayList(API_KEYS, context);
        assertNull(apiKeys);
    }

    @Test
    @SmallTest
    public void getApiKeyArrayList_notNull() {
        apiKeys = ArrayListAdapter.getApiKeyArrayList(API_KEYS, context);
        assertEquals(apiKeys.get(0).getUuid(), UUID);
    }

    @Test
    @SmallTest
    public void onCreate_ListView() {
        getApiKeyArrayList_notNull();
        assertNotNull(listView);
        assertThat(listView, instanceOf(ListView.class));
        ListAdapter adapter = listView.getAdapter();
        assertNotNull(adapter);
        assertThat(adapter, instanceOf(ListViewApiKeysAdapter.class));
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
    public void onActivityResult_isCorrect() {
        // Build a result to return when the activity is launched.
        Intent resultIntent = new Intent();
        resultIntent.putExtra("result", UUID);
        Instrumentation.ActivityResult result = new Instrumentation.ActivityResult(Activity.RESULT_OK, resultIntent);
        assertNotNull(result.getResultData());
        assertEquals(result.getResultData().getStringExtra("result"), UUID);

    }

    @Test
    @MediumTest
    public void addApiKey_isCorrect() {
        onView(withId(R.id.button_add_api_key)).perform(click());
        onView(withId(R.id.popupAddApiKeys)).check(matches(isDisplayed()));
        onView(withId(R.id.textInputEditTextUUID)).perform(typeText(UUID)).check(matches(withText(UUID)));
        int oldApiKeys = listView.getAdapter().getCount();
        onView(withText("CANCEL")).inRoot(isDialog()).check(matches(isDisplayed())).perform(click());
        assertEquals(listView.getAdapter().getCount(), oldApiKeys);
    }

    @Test
    @MediumTest
    public void createApiKey_isCorrect() {
        onView(withId(R.id.button_create_api_key)).perform(click());
        onView(withId(R.id.popupCreateApiKeys)).check(matches(isDisplayed()));
        assertNotNull(onView(withId(R.id.popupCreateApiKeys)));
        onView(withId(R.id.popup_body1)).check(matches(withText(R.string.intro_create_api_Key)));
        onView(withId(R.id.popup_body2)).check(matches(withText(R.string.info_creating_api_Key)));
    }

    @Test
    @MediumTest
    public void showApiKey_isCorrect() {
        onData(anything()).inAdapterView(withId(R.id.list_view_api_keys)).atPosition(0).perform(click());
        onView(withId(R.id.popupShowApiKey)).check(matches(isDisplayed()));
        onView(withId(R.id.popup_body1)).check(matches(isDisplayed()));
        onView(withId(R.id.popup_body2)).check(matches(isDisplayed()));
        onView(withId(R.id.popup_body3)).check(matches(isDisplayed()));
        onView(withId(R.id.popup_body4)).check(matches(isDisplayed()));
        onView(withId(R.id.popup_body5)).check(matches(isDisplayed()));
        onView(withId(R.id.popup_body6)).check(matches(isDisplayed()));
        onView(withId(R.id.popup_body7)).check(matches(isDisplayed()));
        onView(withId(R.id.popup_body8)).check(matches(isDisplayed()));
    }
}