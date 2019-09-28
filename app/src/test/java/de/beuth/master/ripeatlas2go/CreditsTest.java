package de.beuth.master.ripeatlas2go;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.junit.Test;

import de.beuth.master.classes.Credit;

import static org.junit.Assert.*;

public class CreditsTest {
    @Test
    public void gsonCredits(){
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
        String jsonString = "{\n" +
                "    \"current_balance\": 124748,\n" +
                "    \"credit_checked\": true,\n" +
                "    \"max_daily_credits\": 1000000,\n" +
                "    \"estimated_daily_income\": 0,\n" +
                "    \"estimated_daily_expenditure\": 0,\n" +
                "    \"estimated_daily_balance\": 0,\n" +
                "    \"calculation_time\": \"2019-09-27T18:08:43.166150\",\n" +
                "    \"estimated_runout_seconds\": null,\n" +
                "    \"past_day_measurement_results\": 0,\n" +
                "    \"past_day_credits_spent\": 0,\n" +
                "    \"last_date_debited\": \"2019-09-17T12:14:23\",\n" +
                "    \"last_date_credited\": \"2019-03-18T13:34:51\",\n" +
                "    \"income_items\": \"https://atlas.ripe.net/api/v2/credits/income-items/\",\n" +
                "    \"expense_items\": \"https://atlas.ripe.net/api/v2/credits/expense-items/\",\n" +
                "    \"transactions\": \"https://atlas.ripe.net/api/v2/credits/transactions/\"\n" +
                "}";
        Credit credit = gson.fromJson(jsonString, Credit.class);

        assertNotNull(credit);
        assertEquals(Credit.class, credit.getClass());
        assertEquals(124748, credit.getCurrentBalance());
        assertEquals(credit.getEstimatedDailyIncome(), 0);
        assertEquals("https://atlas.ripe.net/api/v2/credits/income-items/", credit.getIncomeItems());
    }
}
