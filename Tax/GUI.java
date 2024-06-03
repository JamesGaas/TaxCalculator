package Tax;

import javax.swing.*;

/**
 * The GUI class represents the main graphical user interface of the F.I.R.E Management System.
 * It extends the JFrame class and contains methods to initialize and configure the GUI components.
 */
public class GUI extends JFrame {

    /**
     * Creates a new instance of the GUI.
     */
    public GUI() {
        setTitle("F.I.R.E Management System"); // Set the title of the frame
        setSize(600, 600); // Set the size of the frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Set the default close operation
        setLocationRelativeTo(null); // Center the frame on the screen
        initComponents(); // Initialize GUI components
    }

    Person person = new Person(); // Create a new instance of Person to store user data

    /**
     * Initializes the GUI components and adds them to the frame.
     */
    private void initComponents() {
        JTabbedPane tabbedPane = new JTabbedPane(); // Create a tabbed pane to organize different functionalities

        // Create tabs for different functionalities
        JPanel expensePanel = createExpensePanel();
        tabbedPane.addTab("Expense Tracking", expensePanel);

        JPanel assetPanel = createAssetPanel();
        tabbedPane.addTab("Asset Management", assetPanel);

        JPanel taxPanel = createTaxPanel();
        tabbedPane.addTab("Deductions Calculation", taxPanel);

        JPanel firePanel = createFIREPanel();
        tabbedPane.addTab("F.I.R.E Calculation", firePanel);

        getContentPane().add(tabbedPane); // Add the tabbed pane to the frame
    }

    /**
     * Creates and returns an instance of ExpensePanel.
     * 
     * @return The created ExpensePanel instance.
     */
    private JPanel createExpensePanel() {
        return new ExpensePanel(person); // Create an instance of ExpensePanel with the person object
    }

    /**
     * Creates and returns an instance of AssetPanel.
     * 
     * @return The created AssetPanel instance.
     */
    private JPanel createAssetPanel() {
        return new AssetPanel(person); // Create an instance of AssetPanel with the person object
    }

    /**
     * Creates and returns an instance of TaxPanel.
     * 
     * @return The created TaxPanel instance.
     */
    private JPanel createTaxPanel() {
        return new TaxPanel(); // Create an instance of TaxPanel
    }

    /**
     * Creates and returns an instance of FIRECalculationPanel.
     * 
     * @return The created FIRECalculationPanel instance.
     */
    private JPanel createFIREPanel() {
        return new FIRECalculationPanel(person); // Create an instance of FIRECalculationPanel with the person object
    }

    /**
     * Main method to start the application.
     * 
     * @param args The command-line arguments.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GUI gui = new GUI(); // Create an instance of the GUI
            gui.setVisible(true); // Make the GUI visible
        });
    }
}
