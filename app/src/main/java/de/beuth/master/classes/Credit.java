package de.beuth.master.classes;

import java.util.Date;

public class Credit {
    private int estimatedDailyExpenditure;
    private int currentBalance;
    private int pastDayCreditsSpent;
    private int estimatedDailyIncome;
    private int pastDayMeasurementResults;
    private Date calculationTime;
    private String incomeItems;
    private int estimatedDaily_balance;
    private String transactions;
    private String expenseItems;
    private int estimatedRunoutSeconds;

    public Credit(int estimatedDailyExpenditure, int currentBalance, int pastDayCreditsSpent, int estimatedDailyIncome, int pastDayMeasurementResults, Date calculationTime, String incomeItems, int estimatedDaily_balance, String transactions, String expenseItems, int estimatedRunoutSeconds) {
        this.estimatedDailyExpenditure = estimatedDailyExpenditure;
        this.currentBalance = currentBalance;
        this.pastDayCreditsSpent = pastDayCreditsSpent;
        this.estimatedDailyIncome = estimatedDailyIncome;
        this.pastDayMeasurementResults = pastDayMeasurementResults;
        this.calculationTime = calculationTime;
        this.incomeItems = incomeItems;
        this.estimatedDaily_balance = estimatedDaily_balance;
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