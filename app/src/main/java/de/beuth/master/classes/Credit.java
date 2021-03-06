/*
 * Copyright (C) 2019 SillyMoto authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.beuth.master.classes;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * <h1>Credit Class!</h1>
 * <p>
 * This class implements all fields of Credits.
 *
 * GET-Request to get all information of your credits:
 * <b>https://atlas.ripe.net/api/v2/credits/</b>
 *
 * @author  Sarah Kommorovski
 * @version 1.0
 * @since   2019-09-30
 */
public class Credit {
    @SerializedName("estimated_daily_expenditure")
    private int estimatedDailyExpenditure;
    @SerializedName("current_balance")
    private int currentBalance;
    @SerializedName("past_day_credits_spent")
    private int pastDayCreditsSpent;
    @SerializedName("estimated_daily_income")
    private int estimatedDailyIncome;
    @SerializedName("past_day_measurement_results")
    private int pastDayMeasurementResults;
    @SerializedName("calculation_time")
    private Date calculationTime;
    @SerializedName("income_items")
    private String incomeItems;
    @SerializedName("estimated_daily_balance")
    private int estimatedDaily_balance;
    @SerializedName("last_date_credited")
    private Date lastDateCredited;
    @SerializedName("last_date_debited")
    private Date lastDateDebited;
    @SerializedName("transactions")
    private String transactions;
    @SerializedName("expense_items")
    private String expenseItems;
    @SerializedName("estimated_runout_seconds")
    private int estimatedRunoutSeconds;

    public Credit(int estimatedDailyExpenditure, int currentBalance, int pastDayCreditsSpent, int estimatedDailyIncome, int pastDayMeasurementResults, Date calculationTime, String incomeItems, int estimatedDaily_balance, Date lastDateCredited, Date lastDateDebited, String transactions, String expenseItems, int estimatedRunoutSeconds) {
        this.estimatedDailyExpenditure = estimatedDailyExpenditure;
        this.currentBalance = currentBalance;
        this.pastDayCreditsSpent = pastDayCreditsSpent;
        this.estimatedDailyIncome = estimatedDailyIncome;
        this.pastDayMeasurementResults = pastDayMeasurementResults;
        this.calculationTime = calculationTime;
        this.incomeItems = incomeItems;
        this.estimatedDaily_balance = estimatedDaily_balance;
        this.lastDateCredited = lastDateCredited;
        this.lastDateDebited = lastDateDebited;
        this.transactions = transactions;
        this.expenseItems = expenseItems;
        this.estimatedRunoutSeconds = estimatedRunoutSeconds;
    }

    public int getEstimatedDailyExpenditure() {
        return estimatedDailyExpenditure;
    }

    public void setEstimatedDailyExpenditure(int estimatedDailyExpenditure) {
        this.estimatedDailyExpenditure = estimatedDailyExpenditure;
    }

    public int getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(int currentBalance) {
        this.currentBalance = currentBalance;
    }

    public int getPastDayCreditsSpent() {
        return pastDayCreditsSpent;
    }

    public void setPastDayCreditsSpent(int pastDayCreditsSpent) {
        this.pastDayCreditsSpent = pastDayCreditsSpent;
    }

    public int getEstimatedDailyIncome() {
        return estimatedDailyIncome;
    }

    public void setEstimatedDailyIncome(int estimatedDailyIncome) {
        this.estimatedDailyIncome = estimatedDailyIncome;
    }

    public int getPastDayMeasurementResults() {
        return pastDayMeasurementResults;
    }

    public void setPastDayMeasurementResults(int pastDayMeasurementResults) {
        this.pastDayMeasurementResults = pastDayMeasurementResults;
    }

    public Date getCalculationTime() {
        return calculationTime;
    }

    public void setCalculationTime(Date calculationTime) {
        this.calculationTime = calculationTime;
    }

    public String getIncomeItems() {
        return incomeItems;
    }

    public void setIncomeItems(String incomeItems) {
        this.incomeItems = incomeItems;
    }

    public int getEstimatedDaily_balance() {
        return estimatedDaily_balance;
    }

    public void setEstimatedDaily_balance(int estimatedDaily_balance) {
        this.estimatedDaily_balance = estimatedDaily_balance;
    }

    public Date getLastDateCredited() {
        return lastDateCredited;
    }

    public void setLastDateCredited(Date lastDateCredited) {
        this.lastDateCredited = lastDateCredited;
    }

    public Date getLastDateDebited() {
        return lastDateDebited;
    }

    public void setLastDateDebited(Date lastDateDebited) {
        this.lastDateDebited = lastDateDebited;
    }

    public String getTransactions() {
        return transactions;
    }

    public void setTransactions(String transactions) {
        this.transactions = transactions;
    }

    public String getExpenseItems() {
        return expenseItems;
    }

    public void setExpenseItems(String expenseItems) {
        this.expenseItems = expenseItems;
    }

    public int getEstimatedRunoutSeconds() {
        return estimatedRunoutSeconds;
    }

    public void setEstimatedRunoutSeconds(int estimatedRunoutSeconds) {
        this.estimatedRunoutSeconds = estimatedRunoutSeconds;
    }
}