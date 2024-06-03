package Tax;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The AssetPanel class represents a GUI panel for managing assets.
 * It provides functionality to add, update, and delete assets, as well as to display total value and average yield.
 */
public class AssetPanel extends JPanel {

    private DefaultTableModel TableModel;
    private JTable Table;
    private JTextField NameField;
    private JTextField ValueField;
    private JTextField YieldField;
    private JLabel totalValueLabel;
    private JLabel averageYieldLabel;
    private JComboBox<String> AssetType;
    private AssetList List;
    private boolean EditMode;
    private Person person;
    private static double averageYield;

    public static double getAverageYield() {
        return averageYield;
    }

    /**
     * Constructs an AssetPanel for the specified person.
     *
     * @param person the person for whom the assets are managed
     */
    public AssetPanel(Person person) {
        this.person = person;
        setLayout(null);
        List = new AssetList(100);
        initComponents();
        refreshAssetTable();
        setEditMode(false);
    }

    /**
     * Initializes the GUI components of the AssetPanel.
     */
    private void initComponents() {
        JLabel assetNameLabel = new JLabel("Asset Name:");
        assetNameLabel.setBounds(20, 20, 120, 25);
        add(assetNameLabel);

        NameField = new JTextField();
        NameField.setBounds(150, 20, 200, 25);
        add(NameField);

        JLabel assetValueLabel = new JLabel("Value:");
        assetValueLabel.setBounds(20, 50, 120, 25);
        add(assetValueLabel);

        ValueField = new JTextField();
        ValueField.setBounds(150, 50, 200, 25);
        add(ValueField);

        JLabel assetTypeLabel = new JLabel("Type:");
        assetTypeLabel.setBounds(20, 80, 120, 25);
        add(assetTypeLabel);

        totalValueLabel = new JLabel("Total Value: $0.00");
        totalValueLabel.setBounds(20, 400, 200, 25);
        add(totalValueLabel);

        averageYieldLabel = new JLabel("Average Yield: 0%");
        averageYieldLabel.setBounds(250, 400, 200, 25);
        add(averageYieldLabel);

        AssetType = new JComboBox<>(Asset.ASSET_TYPES);
        AssetType.setBounds(150, 80, 200, 25);
        AssetType.setEnabled(true);
        add(AssetType);

        JLabel additionalFieldLabel = new JLabel("Yield/CapApp(%):");
        additionalFieldLabel.setBounds(20, 110, 120, 25);
        add(additionalFieldLabel);

        YieldField = new JTextField();
        YieldField.setBounds(150, 110, 200, 25);
        add(YieldField);

        JButton newAssetButton = new JButton("New Asset");
        newAssetButton.setBounds(380, 20, 150, 25);
        add(newAssetButton);
        newAssetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addNewAsset();
            }
        });

        JButton updateAssetButton = new JButton("Update Asset");
        updateAssetButton.setBounds(380, 50, 150, 25);
        add(updateAssetButton);
        updateAssetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateAsset();
            }
        });

        JButton deleteAssetButton = new JButton("Delete Asset");
        deleteAssetButton.setBounds(380, 80, 150, 25);
        add(deleteAssetButton);
        deleteAssetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteAsset();
            }
        });

        JScrollPane assetScrollPane = new JScrollPane();
        assetScrollPane.setBounds(20, 180, 550, 200);
        add(assetScrollPane);

        Table = new JTable() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 3 && EditMode;
            }
        };
        assetScrollPane.setViewportView(Table);
        Table.getSelectionModel().addListSelectionListener(e -> {
            int selectedRow = Table.getSelectedRow();
            if (selectedRow != -1 && !EditMode) {
                NameField.setText((String) Table.getValueAt(selectedRow, 0));
                ValueField.setText(String.valueOf(Table.getValueAt(selectedRow, 1)));
                AssetType.setSelectedItem((String) Table.getValueAt(selectedRow, 2));
                YieldField.setText(String.valueOf(Table.getValueAt(selectedRow, 3)));
            }
        });
    }

    /**
     * Calculates and updates the total value and average yield of the assets.
     */
    private void calculateTotalValueAndAverageYield() {
        double totalValue = 0;
        double totalYield = 0;
        double interest = 0;
        int rowCount = TableModel.getRowCount();
        for (int i = 0; i < rowCount; i++) {
            double value = (double) TableModel.getValueAt(i, 1);
            totalValue += value;
    
            double yield = (double) TableModel.getValueAt(i, 3);
            totalYield += yield;

            interest += value * (yield / 100);
        }
       
        if (rowCount != 0) {
            averageYield = totalYield / rowCount;
            totalValueLabel.setText("Total Value: $" + String.format("%.2f", totalValue));
            averageYieldLabel.setText("Average Yield: " + String.format("%.2f", averageYield) + "%");
        } else {
            totalValueLabel.setText("Total Value: 0");
            averageYieldLabel.setText("Average Yield: 0");
        }
        person.settotal_invest(interest);
    }

    /**
     * Adds a new asset to the list and table.
     */
    private void addNewAsset() {
        String assetName = NameField.getText();
        String assetValueText = ValueField.getText();
        String assetType = (String) AssetType.getSelectedItem();
        String yieldText = YieldField.getText();
        
        if (assetName.isEmpty() || assetValueText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in asset name and value.");
            return;
        }
        
        double assetValue = Double.parseDouble(assetValueText);
        double defaultYield = getDefaultYield(assetType);
        double additionalValue;
        
        if (yieldText.isEmpty()) {
            additionalValue = defaultYield;
        } else {
            additionalValue = Double.parseDouble(yieldText);
        }
        
        TableModel.addRow(new Object[]{assetName, assetValue, assetType, additionalValue});
        List.addAsset(assetName, assetValue, assetType, additionalValue);
        
        clearAssetFields();
    }

    /**
     * Updates the selected asset in the list and table.
     */
    private void updateAsset() {
        int selectedRow = Table.getSelectedRow();
        if (selectedRow != -1) {
            String assetName = NameField.getText();
            String assetValueText = ValueField.getText();
            String assetType = (String) AssetType.getSelectedItem();
            String yieldText = YieldField.getText();
    
            if (assetName.isEmpty() || assetValueText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in asset name and value.");
                return;
            }
    
            double assetValue = Double.parseDouble(assetValueText);
            double additionalValue;
            double defaultYield = getDefaultYield(assetType);
            
            if (yieldText.isEmpty()) {
                additionalValue = defaultYield;
            } else {
                additionalValue = Double.parseDouble(yieldText);
            }
            TableModel.setValueAt(assetName, selectedRow, 0);
            TableModel.setValueAt(assetValue, selectedRow, 1);
            TableModel.setValueAt(assetType, selectedRow, 2);
            TableModel.setValueAt(additionalValue, selectedRow, 3);
    
            clearAssetFields();
        } else {
            JOptionPane.showMessageDialog(this, "Please select an asset to update.");
        }
    }

    /**
     * Gets the default yield for the specified asset type.
     *
     * @param assetType the type of the asset
     * @return the default yield for the asset type
     */
    private double getDefaultYield(String assetType) {
        double defaultYield = 0.0;
        
        switch (assetType) {
            case "Stock":
                defaultYield = Math.round(Stock.getAverage());
                break;
            case "Bond":
                defaultYield = Math.round(Bond.getAverage());
                break;
            case "Real Estate":
                defaultYield = Math.round(RealEstate.getAverage());
                break;
            case "Other":
                defaultYield = Math.round(Other.getAverage());
                break;
        }
        return defaultYield;
    }

    /**
     * Deletes the selected asset from the list and table.
     */
    private void deleteAsset() {
        int selectedRow = Table.getSelectedRow();
        if (selectedRow != -1) {
            TableModel.removeRow(selectedRow);
            clearAssetFields();
        } else {
            JOptionPane.showMessageDialog(this, "Please select an asset to delete.");
        }
    }

    /**
     * Refreshes the asset table to display current assets.
     */
    private void refreshAssetTable() {
        TableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return true;
            }
        };
        TableModel.setColumnIdentifiers(new Object[]{"Asset Name", "Value", "Type", "Yield/CapApp(%)"});
        TableModel.addTableModelListener(e -> calculateTotalValueAndAverageYield());
        Table.setModel(TableModel);
    }

    /**
     * Clears the asset input fields.
     */
    private void clearAssetFields() {
        NameField.setText("");
        ValueField.setText("");
        YieldField.setText("");
    }

    /**
     * Sets the edit mode for the table.
     *
     * @param assetEditMode the edit mode to set
     */
    private void setEditMode(boolean assetEditMode) {
        this.EditMode = assetEditMode;
        Table.repaint();
    }

    
}
