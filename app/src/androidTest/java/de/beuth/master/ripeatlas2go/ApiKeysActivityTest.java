package de.beuth.master.ripeatlas2go;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.annotation.UiThreadTest;
import android.support.test.filters.MediumTest;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.rule.ActivityTestRule;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Date;

import de.beuth.master.classes.ApiKey;
import de.beuth.master.services.ArrayListAdapter;
import de.beuth.master.services.ListViewApiKeysAdapter;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

@MediumTest
@RunWith(AndroidJUnit4.class)
public class ApiKeysActivityTest {

    final private String API_KEYS = "apiKeys";
    final private String UUID = "5adcf6b3-ef7a-4acd-ad6a-b5c38d892a43";

    private Context context;
    private ArrayList<ApiKey> apiKeys;
    private ApiKeysActivity activity;

    private ListView listView;
    private Button buttonAdd;
    private Button buttonCreate;

    @Rule
    public ActivityTestRule<ApiKeysActivity> rule = new ActivityTestRule<>(ApiKeysActivity.class);

    @Before
    public void setUp() {
        context = InstrumentationRegistry.getContext();
        activity = rule.getActivity();
        listView = activity.findViewById(R.id.list_view_api_keys);
        buttonAdd = activity.findViewById(R.id.button_add_api_key);
        buttonCreate = activity.findViewById(R.id.button_add_api_key);
    }

    @Test
    public void getApiKeyArrayList_isNull() {
        apiKeys = null;
        ArrayListAdapter.saveApiKeyArrayList(apiKeys, API_KEYS, context);
        apiKeys = ArrayListAdapter.getApiKeyArrayList(API_KEYS, context);
        assertNull(apiKeys);
    }

    @Test
    public void getApiKeyArrayList_notNull() {
        ApiKey apiKey = new ApiKey("5adcf6b3-ef7a-4acd-ad6a-b5c38d892a43", new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()), true, true, new Date(System.currentTimeMillis()), "LABEL", new ArrayList<String>());
        apiKeys = new ArrayList<>();
        apiKeys.add(apiKey);
        ArrayListAdapter.saveApiKeyArrayList(apiKeys, API_KEYS, context);
        apiKeys = ArrayListAdapter.getApiKeyArrayList(API_KEYS, context);
        assertEquals(apiKeys.get(0).getUuid(), "5adcf6b3-ef7a-4acd-ad6a-b5c38d892a43");
    }

    @Test
    public void onCreate_ListView() {
        getApiKeyArrayList_notNull();
        assertNotNull(listView);
        assertThat(listView, instanceOf(ListView.class));
        ListAdapter adapter = listView.getAdapter();
        assertThat(adapter, instanceOf(ListViewApiKeysAdapter.class));
        assertEquals(adapter.getCount(), 7);
    }

    @Test
    public void onCreate_Buttons() {
        assertNotNull(buttonAdd);
        assertNotNull(buttonCreate);
        assertTrue(buttonAdd.isClickable());
        assertTrue(buttonCreate.isClickable());
        assertTrue(buttonAdd.hasOnClickListeners());
        assertTrue(buttonCreate.hasOnClickListeners());
    }

    @UiThreadTest
    public void onActivityResult_isCorrect() {
        // Build a result to return when the activity is launched.
        Intent resultIntent = new Intent();
        resultIntent.putExtra("result", UUID);
        Instrumentation.ActivityResult result = new Instrumentation.ActivityResult(Activity.RESULT_OK, resultIntent);
        assertNotNull(result.getResultData());
        assertEquals(result.getResultData().getStringExtra("result"), UUID);

    }

    @UiThreadTest
    public void addApiKey_isCorrect() {
        EditText editText = activity.findViewById(R.id.textInputEditTextUUID);
        View popUpView = activity.findViewById(R.layout.popup_add_api_key);
        buttonAdd.performClick();
        editText.setText(UUID);
        assertEquals(editText.getText().toString(), UUID);
        assertNotNull(popUpView.getContext());
    }

    @UiThreadTest
    public void createApiKey_isCorrect() {
        View popUpView = activity.findViewById(R.layout.popup_create_api_key);
        buttonCreate.performClick();
        assertNotNull(popUpView.getContext());
        TextView textView = popUpView.findViewById(R.id.popup_body1);
        assertEquals(textView.getText().toString(), activity.getString(R.string.intro_create_api_Key));
        textView = popUpView.findViewById(R.id.popup_body2);
        assertEquals(textView.getText().toString(), activity.getString(R.string.info_creating_api_Key));
    }

    @UiThreadTest
    public void showApiKey_isCorrect() {
        View popUpView = activity.findViewById(R.layout.popup_show_api_key);
        RelativeLayout rl = activity.findViewById(R.id.relativeLayout1);
        listView.performItemClick(rl, 0, 0);
        buttonCreate.performClick();
        assertNotNull(popUpView.getContext());
        TextView textView = activity.findViewById(R.id.popup_body1);
        assertNotNull(textView.getText());
        assertThat(textView.toString().length(), greaterThan(0));
        textView = activity.findViewById(R.id.popup_body1);
        assertNotNull(textView.getText());
        assertThat(textView.toString().length(), greaterThan(0));
        textView = activity.findViewById(R.id.popup_body2);
        assertNotNull(textView.getText());
        assertThat(textView.toString().length(), greaterThan(0));
        textView = activity.findViewById(R.id.popup_body3);
        assertNotNull(textView.getText());
        assertThat(textView.toString().length(), greaterThan(0));
        textView = activity.findViewById(R.id.popup_body4);
        assertNotNull(textView.getText());
        assertThat(textView.toString().length(), greaterThan(0));
        textView = activity.findViewById(R.id.popup_body5);
        assertNotNull(textView.getText());
        assertThat(textView.toString().length(), greaterThan(0));
        textView = activity.findViewById(R.id.popup_body6);
        assertNotNull(textView.getText());
        assertThat(textView.toString().length(), greaterThan(0));
        textView = activity.findViewById(R.id.popup_body7);
        assertNotNull(textView.getText());
        assertThat(textView.toString().length(), greaterThan(0));
        textView = activity.findViewById(R.id.popup_body8);
        assertNotNull(textView.getText());
        assertThat(textView.toString().length(), greaterThan(0));
    }
}