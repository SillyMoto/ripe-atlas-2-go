package de.beuth.master.ripeatlas2go;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.SmallTest;
import android.support.test.rule.ActivityTestRule;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import de.beuth.master.classes.Measurement;
import de.beuth.master.classes.Status;
import de.beuth.master.services.ArrayListAdapter;
import de.beuth.master.services.ListViewMsmsAdapter;

import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

@SmallTest
public class MeasurementActivityTest {
    private static final String MSM = "measurement";
    private Activity activity;
    private Context context;
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
        listView = activity.findViewById(R.id.list_view_measurements);
        buttonAdd = activity.findViewById(R.id.button_add_msm);
        buttonCreate = activity.findViewById(R.id.button_create_msm);
    }

    @Test
    public void getMsmList_isNull() {
        msms = null;
        ArrayListAdapter.saveMsmArrayList(msms, MSM, context);
        msms = ArrayListAdapter.getMsmArrayList(MSM, context);
        assertNull(msms);
    }

    @Test
    public void getMsmArrayList_notNull() {
        Measurement msm = new Measurement(4, new Date(1552918479), 3, "Ping measurement to wiki.ipv6lab.beuth-hochschule.de", 40, "https://atlas.ripe.net/api/v2/measurements/groups/20283701/?format=json", 20283701, 20283701, false, 21600, true, false, true, 1000, 3, 10, 10, 10, false, new ArrayList<String>(){{add("141.64.156.45");}}, "https://atlas.ripe.net/api/v2/measurements/20283701/results/?format=json", 48, 0, new Date(1552924822),
        new Status(4, "stopped", new Date(1553270220)), new Date(1553529622), null, "wiki.ipv6lab.beuth-hochschule.de", 680, "141.64.156.45", "141.64.0.0/15", "Ping");
        msms = new ArrayList<>();
        msms.add(msm);
        ArrayListAdapter.saveMsmArrayList(msms, MSM, context);
        msms = ArrayListAdapter.getMsmArrayList(MSM, context);
        assertEquals(msms.get(0).getID(), 20283701);
        assertEquals(msms.get(0).getDescription(), "Ping measurement to wiki.ipv6lab.beuth-hochschule.de");
        assertEquals(msms.get(0).getStatus().getId(), 4);
    }

    @Test
    public void onCreate_ListView() {
        getMsmArrayList_notNull();
        assertNotNull(listView);
        assertThat(listView, instanceOf(ListView.class));
        ListAdapter adapter = listView.getAdapter();
        assertThat(adapter, instanceOf(ListViewMsmsAdapter.class));
        assertEquals(adapter.getCount(), 103);
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
}
