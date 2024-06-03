package Tax;

import java.util.ArrayList;
import java.util.List;

/**
 * The Person class represents an individual in the application, including their financial details and expenses.
 */
class Person {
    private int age;
    private double existingNetWorth;
    private double annualIncome;
    private double annualSalaryIncrease;
    private double inflation;
    private int targetRetirementAge;
    private List<Expenses> expenses = new ArrayList<>();
    private double total_expenses = 0;
    private double total_invest = 0;

    /**
     * Gets the total expenses of the person.
     * 
     * @return The total expenses of the person.
     */
    public double gettotal_expenses() {
        return total_expenses;
    }

    /**
     * Sets the total expenses of the person.
     * 
     * @param total The total expenses to set.
     */
    public void settotal_expenses(double total) {
        total_expenses = total;
    }

    /**
     * Gets the total investments of the person.
     * 
     * @return The total investments of the person.
     */
    public double gettotal_invest() {
        return total_invest;
    }

    /**
     * Sets the total investments of the person.
     * 
     * @param total The total investments to set.
     */
    public void settotal_invest(double total) {
        total_invest = total;
    }

    /**
     * Gets the age of the person.
     * 
     * @return The age of the person.
     */
    public int getAge() {
        return age;
    }

    /**
     * Sets the age of the person.
     * 
     * @param age The age to set.
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Gets the existing net worth of the person.
     * 
     * @return The existing net worth of the person.
     */
    public double getExistingNetWorth() {
        return existingNetWorth;
    }

    /**
     * Sets the existing net worth of the person.
     * 
     * @param existingNetWorth The existing net worth to set.
     */
    public void setExistingNetWorth(double existingNetWorth) {
        this.existingNetWorth = existingNetWorth;
    }

    /**
     * Gets the annual income of the person.
     * 
     * @return The annual income of the person.
     */
    public double getAnnualIncome() {
        return annualIncome;
    }

    /**
     * Sets the annual income of the person.
     * 
     * @param annualIncome The annual income to set.
     */
    public void setAnnualIncome(double annualIncome) {
        this.annualIncome = annualIncome;
    }

    /**
     * Gets the annual salary increase of the person.
     * 
     * @return The annual salary increase of the person.
     */
    public double getAnnualSalaryIncrease() {
        return annualSalaryIncrease;
    }

    /**
     * Sets the annual salary increase of the person.
     * 
     * @param annualSalaryIncrease The annual salary increase to set.
     */
    public void setAnnualSalaryIncrease(double annualSalaryIncrease) {
        this.annualSalaryIncrease = annualSalaryIncrease;
    }

    /**
     * Gets the inflation rate for the person.
     * 
     * @return The inflation rate for the person.
     */
    public double getInflation() {
        return inflation;
    }

    /**
     * Sets the inflation rate for the person.
     * 
     * @param inflation The inflation rate to set.
     */
    public void setInflation(double inflation) {
        this.inflation = inflation;
    }

    /**
     * Gets the target retirement age of the person.
     * 
     * @return The target retirement age of the person.
     */
    public int getTargetRetirementAge() {
        return targetRetirementAge;
    }

    /**
     * Sets the target retirement age of the person.
     * 
     * @param targetRetirementAge The target retirement age to set.
     */
    public void setTargetRetirementAge(int targetRetirementAge) {
        this.targetRetirementAge = targetRetirementAge;
    }

    /**
     * Gets the list of expenses for the person.
     * 
     * @return The list of expenses for the person.
     */
    public List<Expenses> getExpenses() {
        return expenses;
    }

    /**
     * Adds an expense to the list of expenses for the person.
     * 
     * @param expense The expense to add.
     */
    public void addExpense(Expenses expense) {
        expenses.add(expense);
    }
}
