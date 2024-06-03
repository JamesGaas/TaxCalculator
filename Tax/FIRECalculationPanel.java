package Tax;

// Imports
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FIRECalculationPanel extends JPanel {

    // Variables
    private JTextField ageField;
    private JTextField existingNetWorthField;
    private JTextField annualIncomeField;
    private JTextField annualSalaryIncreaseField;
    private JTextField inflationField;
    private JTextField targetRetirementAgeField;
    private JLabel resultsLabel;
    private JTable table;
    private DefaultTableModel tableModel;
    private Person person;

    // Initialize the GUI
    public FIRECalculationPanel(Person person) {
        this.person = person;
        setLayout(null);

        JLabel ageLabel = new JLabel("Current Age:");
        ageLabel.setBounds(20, 20, 120, 25);
        add(ageLabel);

        ageField = new JTextField();
        ageField.setBounds(350, 20, 200, 25);
        add(ageField);

        JLabel netWorthLabel = new JLabel("Existing Net Worth:");
        netWorthLabel.setBounds(20, 50, 120, 25);
        add(netWorthLabel);

        existingNetWorthField = new JTextField();
        existingNetWorthField.setBounds(350, 50, 200, 25);
        add(existingNetWorthField);

        JLabel incomeLabel = new JLabel("Annual Net Income:");
        incomeLabel.setBounds(20, 80, 120, 25);
        add(incomeLabel);

        annualIncomeField = new JTextField();
        annualIncomeField.setBounds(350, 80, 200, 25);
        add(annualIncomeField);

        JLabel salaryIncreaseLabel = new JLabel("Annual Salary Increase (%):");
        salaryIncreaseLabel.setBounds(20, 110, 170, 25);
        add(salaryIncreaseLabel);

        annualSalaryIncreaseField = new JTextField();
        annualSalaryIncreaseField.setBounds(350, 110, 200, 25);
        add(annualSalaryIncreaseField);

        JLabel inflationLabel = new JLabel("Inflation Rate (%):");
        inflationLabel.setBounds(20, 140, 120, 25);
        add(inflationLabel);

        inflationField = new JTextField();
        inflationField.setBounds(350, 140, 200, 25);
        add(inflationField);

        JLabel retirementAgeLabel = new JLabel("Target Retirement Age:");
        retirementAgeLabel.setBounds(20, 170, 150, 25);
        add(retirementAgeLabel);

        targetRetirementAgeField = new JTextField();
        targetRetirementAgeField.setBounds(350, 170, 200, 25);
        add(targetRetirementAgeField);

        resultsLabel = new JLabel();
        resultsLabel.setBounds(20, 450, 400, 25);
        add(resultsLabel);

        JButton calculateButton = new JButton("Calculate F.I.R.E");
        calculateButton.setBounds(350, 200, 200, 25);
        add(calculateButton);
        calculateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                calculateFIRE();
            }
        });

        // Setup the table and its model
        String[] columnNames = {"Age", "Net Worth", "Annual Expenses"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 240, 550, 200);
        add(scrollPane);
    }

    private void calculateFIRE() {
        tableModel.setRowCount(0);
        try {
            int age = Integer.parseInt(ageField.getText());
            double netWorth = Double.parseDouble(existingNetWorthField.getText());
            double income = Double.parseDouble(annualIncomeField.getText());
            double salaryIncrease = Double.parseDouble(annualSalaryIncreaseField.getText());
            double inflation = Double.parseDouble(inflationField.getText());
            int retirementAge = Integer.parseInt(targetRetirementAgeField.getText());
    
            // Set the person's parameter
            person.setAge(age);
            person.setExistingNetWorth(netWorth);
            person.setAnnualIncome(income);
            person.setAnnualSalaryIncrease(salaryIncrease);
            person.setInflation(inflation);
            person.setTargetRetirementAge(retirementAge);
            double total_expenses = person.gettotal_expenses();
            double total_invest = person.gettotal_invest();
            double ave = AssetPanel.getAverageYield();

    
            while (age <= 121) {
                if (age > 120) {
                resultsLabel.setText("Congratulations!, It seems that your money will last until age 120!");
                   break;
                }
    
                // Calculate total yearly expenses
                double yearly_expenses = total_expenses * 12;
    
                // Apply inflation to monthly expenses
                total_expenses *= (1 + (inflation / 100));
                total_expenses = Math.round(total_expenses);
    
                // If not retired, calculate income and income increase
                if (age < retirementAge) {
                    netWorth += income;
                    income *= (1+(salaryIncrease / 100));
                    income = Math.round(income);
                } else {
                    income = 0;
                }

                netWorth += total_invest;
                total_invest *= (1+(ave / 100));
                    total_invest = Math.round(total_invest);
    
                // Deduct yearly expenses from net worth
                netWorth -= yearly_expenses;
                netWorth = Math.round(netWorth);

                // Check if net worth is negative
                if (netWorth < 0) {
                    resultsLabel.setText("Your money will last until the age of " + age + "!");
                    break;
                }
    
                // Add row to the table
                tableModel.addRow(new Object[]{age, netWorth, yearly_expenses});
                age++;
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers.");
        }
    }
}