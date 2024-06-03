package Tax;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExpensePanel extends JPanel {

    // Fields
    private DefaultTableModel expenseTableModel;
    private JTable expenseTable;
    private JTextField expenseNameField;
    private JTextField expenseValueField;
    private boolean expenseEditMode;
    private JLabel totalExpensesLabel;
    private ExpensesList expensesList; // Using the ExpensesList class
    private Person person;

    /**
     * Constructs an ExpensePanel object.
     * @param person The person associated with the panel.
     */
    public ExpensePanel(Person person) {
        this.person = person;
        setLayout(null);
        initComponents();
        refreshExpenseTable();
        setEditMode(false);
        expensesList = new ExpensesList(); // Initialize the ExpensesList
    }

    /**
     * Initializes components of the ExpensePanel.
     */
    private void initComponents() {
        JLabel expenseNameLabel = new JLabel("Expense Name:");
        expenseNameLabel.setBounds(20, 20, 120, 25);
        add(expenseNameLabel);

        expenseNameField = new JTextField();
        expenseNameField.setBounds(150, 20, 200, 25);
        add(expenseNameField);

        JLabel expenseValueLabel = new JLabel("Value:");
        expenseValueLabel.setBounds(20, 50, 120, 25);
        add(expenseValueLabel);

        expenseValueField = new JTextField();
        expenseValueField.setBounds(150, 50, 200, 25);
        add(expenseValueField);

        JButton newExpenseButton = new JButton("New Expense");
        newExpenseButton.setBounds(380, 20, 150, 25);
        add(newExpenseButton);

        JButton updateExpenseButton = new JButton("Update Expense");
        updateExpenseButton.setBounds(380, 50, 150, 25);
        add(updateExpenseButton);

        JButton deleteExpenseButton = new JButton("Delete Expense");
        deleteExpenseButton.setBounds(380, 80, 150, 25);
        add(deleteExpenseButton);

        JScrollPane expenseScrollPane = new JScrollPane();
        expenseScrollPane.setBounds(20, 120, 550, 200);
        add(expenseScrollPane);

        expenseTable = new JTable() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return expenseEditMode;
            }
        };
        expenseScrollPane.setViewportView(expenseTable);
        expenseTable.getSelectionModel().addListSelectionListener(e -> {
            int selectedRow = expenseTable.getSelectedRow();
            if (selectedRow != -1 && !expenseEditMode) {
                expenseNameField.setText((String) expenseTable.getValueAt(selectedRow, 0));
                expenseValueField.setText(String.valueOf(expenseTable.getValueAt(selectedRow, 1)));
            }
        });

        // Label to display total expenses
        totalExpensesLabel = new JLabel("Total Expenses: $0.00");
        totalExpensesLabel.setBounds(300, 330, 200, 25);
        add(totalExpensesLabel);

        // Add ActionListener to newExpenseButton
        newExpenseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addNewExpense();
            }
        });

        // Add ActionListener to updateExpenseButton
        updateExpenseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateExpense();
            }
        });

        // Add ActionListener to deleteExpenseButton
        deleteExpenseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteExpense();
            }
        });
    }

    /**
     * Adds a new expense to the expenses list and updates the UI table.
     */
    private void addNewExpense() {
        String expenseName = expenseNameField.getText();
        double expenseValue = Double.parseDouble(expenseValueField.getText());
        Expenses newExpense = new Expenses(expenseName, expenseValue);
        expensesList.addExpense(newExpense); // Add expense to the ExpensesList
        addToTable(newExpense);
        
        updateTotalExpenses();
        clearExpenseFields();
    }

    /**
     * Updates an existing expense in the expenses list and the UI table.
     */
    private void updateExpense() {
        int selectedRow = expenseTable.getSelectedRow();
        if (selectedRow != -1) {
            String expenseName = expenseNameField.getText();
            double expenseValue = Double.parseDouble(expenseValueField.getText());
            Expenses selectedExpense = expensesList.getExpense(selectedRow); // Get the expense from ExpensesList
            if (selectedExpense != null) {
                selectedExpense.setName(expenseName);
                selectedExpense.setValue(expenseValue);
                expenseTableModel.setValueAt(expenseName, selectedRow, 0);
                expenseTableModel.setValueAt(expenseValue, selectedRow, 1);
                updateTotalExpenses();
                clearExpenseFields();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select an expense to update.");
        }
    }

    /**
     * Deletes the selected expense from the expenses list and the UI table.
     */
    private void deleteExpense() {
        int selectedRow = expenseTable.getSelectedRow();
        if (selectedRow != -1) {
            expensesList.removeExpense(selectedRow); // Remove expense from ExpensesList
            expenseTableModel.removeRow(selectedRow);
            updateTotalExpenses();
            clearExpenseFields();
        } else {
            JOptionPane.showMessageDialog(this, "Please select an expense to delete.");
        }
    }

    /**
     * Refreshes the UI table for displaying expenses.
     */
    private void refreshExpenseTable() {
        expenseTableModel = new DefaultTableModel();
        expenseTableModel.setColumnIdentifiers(new Object[]{"Expense Name", "Value"});
        expenseTable.setModel(expenseTableModel);
    }

    /**
     * Adds an expense to the UI table.
     * @param expense The expense to be added.
     */
    private void addToTable(Expenses expense) {
        expenseTableModel.addRow(new Object[]{expense.getName(), expense.getValue()});
    }

    /**
     * Clears the text fields for entering new expenses.
     */
    private void clearExpenseFields() {
        expenseNameField.setText("");
        expenseValueField.setText("");
    }

    /**
     * Sets the edit mode for the expense table.
     * @param expenseEditMode Whether to enable edit mode or not.
     */
    private void setEditMode(boolean expenseEditMode) {
        this.expenseEditMode = expenseEditMode;
        expenseTable.repaint();
    }

    /**
     * Updates the total expenses label based on the expenses list.
     */
    private void updateTotalExpenses() {
        double totalExpenses = 0.0;
        for (Expenses expense : expensesList.getAllExpenses()) {
            totalExpenses += expense.getValue();
        }
        person.settotal_expenses(totalExpenses);
        totalExpensesLabel.setText("Total Expenses: $" + String.format("%.2f", totalExpenses));
    }
}

