package Tax;

import java.util.ArrayList;
import java.util.List;

public class ExpensesList {
    private List<Expenses> expensesList;

    /**
     * Constructor to initialize an empty list of expenses.
     */
    public ExpensesList() {
        expensesList = new ArrayList<>();
    }

    /**
     * Adds a new expense to the list.
     * @param expense The expense to add.
     */
    public void addExpense(Expenses expense) {
        expensesList.add(expense);
    }

    /**
     * Removes an expense from the list at the specified index.
     * @param index The index of the expense to remove.
     */
    public void removeExpense(int index) {
        if (index >= 0 && index < expensesList.size()) {
            expensesList.remove(index);
        }
    }

    /**
     * Retrieves the expense at the specified index.
     * @param index The index of the expense to retrieve.
     * @return The expense at the specified index, or null if the index is out of bounds.
     */
    public Expenses getExpense(int index) {
        if (index >= 0 && index < expensesList.size()) {
            return expensesList.get(index);
        }
        return null;
    }

    /**
     * Retrieves all expenses in the list.
     * @return The list of all expenses.
     */
    public List<Expenses> getAllExpenses() {
        return expensesList;
    }

    /**
     * Retrieves the size of the expenses list.
     * @return The size of the expenses list.
     */
    public int getSize() {
        return expensesList.size();
    }
}
