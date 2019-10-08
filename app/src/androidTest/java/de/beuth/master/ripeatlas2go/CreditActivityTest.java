package de.beuth.master.ripeatlas2go;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.test.filters.SmallTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.Button;
import android.widget.TextView;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.text.DateFormat;
import java.util.Calendar;

import de.beuth.master.classes.Credit;
import de.beuth.master.services.ArrayListAdapter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class CreditActivityTest {

    private static final String CREDIT = "credit";
    private Activity activity;
    private static Context context;
    private Credit credit;

    private Button buttonCheck;
    private Button buttonTransfer;

    @Rule
    public ActivityTestRule<CreditActivity> rule = new ActivityTestRule<>(CreditActivity.class);

    /**
     * Create a Credit in SharedPreferences
     * Launch the Activity again
     */
    @Before
    public void setUp() {
        activity = rule.getActivity();
        context = activity.getApplicationContext();
        mockCredit();
        Intent intent = new Intent();
        rule.launchActivity(intent);
        activity = rule.getActivity();
        context = activity.getApplicationContext();
        buttonCheck = activity.findViewById(R.id.button_check_credits);
        buttonTransfer = activity.findViewById(R.id.button_transfer_credits);
    }

    private void mockCredit(){
        credit = new Credit(24000, 222888, 0, 0, 0, Calendar.getInstance().getTime(), "", -24000, Calendar.getInstance().getTime(), Calendar.getInstance().getTime(),"", "", 802396);
        ArrayListAdapter.saveCredit(credit, CREDIT, context);
    }

    @AfterClass
    public static void clean(){
        ArrayListAdapter.saveCredit(null, CREDIT, context);
    }

    @Test
    @SmallTest
    public void onCreate_Buttons() {
        assertNotNull(buttonCheck);
        assertNotNull(buttonTransfer);
        assertTrue(buttonCheck.isClickable());
        assertTrue(buttonTransfer.isClickable());
        assertTrue(buttonCheck.hasOnClickListeners());
        assertTrue(buttonTransfer.hasOnClickListeners());
    }

    @Test
    @SmallTest
    public void onCreate_showCredit(){
        credit = ArrayListAdapter.getCredit(CREDIT, context);
        TextView view = activity.findViewById(R.id.credits_subtitle1_body1);
        int i = Integer.valueOf(view.getText().toString());
        assertEquals(i, credit.getCurrentBalance());
        view = activity.findViewById(R.id.credits_subtitle2_body1);
        i =  Integer.valueOf(view.getText().toString());
        assertEquals(i, credit.getEstimatedDailyIncome());
        view = activity.findViewById(R.id.credits_subtitle3_body1);
        i =  Integer.valueOf(view.getText().toString());
        assertEquals(i, credit.getEstimatedDailyExpenditure());
        view = activity.findViewById(R.id.credits_subtitle4_body1);
        i =  Integer.valueOf(view.getText().toString());
        assertEquals(i, credit.getEstimatedDaily_balance());
        view = activity.findViewById(R.id.credits_subtitle5_body1);
        i =  Integer.valueOf(view.getText().toString());
        assertEquals(i, credit.getEstimatedRunoutSeconds());
        view = activity.findViewById(R.id.credits_subtitle6_body1);
        assertEquals(view.getText().toString(), String.valueOf(DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.MEDIUM).format(credit.getCalculationTime())));
        view = activity.findViewById(R.id.transactions_subtitle1_body1);
        assertEquals(view.getText().toString(), String.valueOf(DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.MEDIUM).format(credit.getLastDateCredited())));
        view = activity.findViewById(R.id.transactions_subtitle2_body1);
        assertEquals(view.getText().toString(), String.valueOf(DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.MEDIUM).format(credit.getLastDateDebited())));
    }

}