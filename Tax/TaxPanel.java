package Tax;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The TaxPanel class represents a JPanel containing components for calculating tax deductions.
 * It allows users to input their monthly income and calculates various deductions such as SSS, PhilHealth, Pag-IBIG,
 * and net yearly income after tax.
 */
public class TaxPanel extends JPanel {

    private JTextField incomeField;
    private JLabel deductionsResultLabel;
    private JLabel SSSResultLabel;
    private JLabel PhilResultLabel;
    private JLabel PagResultLabel;
    private JLabel NetResultLabel;
    
    private double income;
    private double SSS;
    private double Phil;
    private double Pag;
    private double net;

    /**
     * Constructs a new TaxPanel with components initialized and laid out.
     */
    public TaxPanel() {
        setLayout(null);
        initComponents();
    }

     /**
     * Initializes and adds components to the panel.
     */
    private void initComponents() {
        JLabel incomeLabel = new JLabel("Monthly Income:");
        incomeLabel.setBounds(20, 20, 120, 25);
        add(incomeLabel);

        incomeField = new JTextField();
        incomeField.setBounds(150, 20, 200, 25);
        add(incomeField);

        JButton calculateTaxButton = new JButton("Calculate Tax");
        calculateTaxButton.setBounds(150, 50, 200, 25);
        add(calculateTaxButton);
        calculateTaxButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                calculateTax();
            }
        });

        deductionsResultLabel = new JLabel("Net Monthly Income: ");
        deductionsResultLabel.setBounds(20, 80, 200, 25);
        add(deductionsResultLabel);

        SSSResultLabel = new JLabel("SSS Contribution: ");
        SSSResultLabel.setBounds(20, 110, 200, 25);
        add(SSSResultLabel);

        PhilResultLabel = new JLabel("PhilHealth Contribution: ");
        PhilResultLabel.setBounds(20, 140, 200, 25);
        add(PhilResultLabel);

        PagResultLabel = new JLabel("PagIbig Contribution: ");
        PagResultLabel.setBounds(20, 170, 200, 25);
        add(PagResultLabel);

        NetResultLabel = new JLabel("Net Yearly Income: ");
        NetResultLabel.setBounds(20, 200, 200, 25);
        add(NetResultLabel);

    }

    /**
     * Calculates tax deductions
     */
    private void calculateTax() {
        try {
            income = Double.parseDouble(incomeField.getText());
            SSS = calculateSSS(income);
            Phil = calculatePhil(income);
            Pag = calculatePag(income);
            net = calculateTax(income, SSS, Phil, Pag);
            deductionsResultLabel.setText("Net Monthly Income: " + net);
            SSSResultLabel.setText("SSS Contribution: " + SSS);
            PhilResultLabel.setText("PhilHealth Contribution: " + Phil);
            PagResultLabel.setText("PagIbig Contribution: " + Pag);
            NetResultLabel.setText("Net Yearly Income: " + net*12);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers for income and deductions.");
        }
    }

    /**
     * Calculates the SSS contribution based on monthly income.
     * @param monthly The monthly income.
     * @return The calculated SSS contribution.
     */
    private double calculateSSS(double monthly) {
        double SSSCon;

        double[][] contributionTable = {
            {4249, 180},
            {4749, 202.5},
            {5249, 225},
            {5749, 247.5},
            {6249, 270},
            {6749, 292.5},
            {7249, 315},
            {7749, 337.5},
            {8249, 360},
            {8749, 382.5},
            {9249, 405},
            {9749, 427.5},
            {10249, 450},
            {10749, 472.5},
            {11249, 495},
            {11749, 517.5},
            {12249, 540},
            {12749, 562.5},
            {13249, 585},
            {13749, 607.5},
            {14249, 630},
            {14749, 652.5},
            {15249, 675},
            {15749, 697.5},
            {16249, 720},
            {16749, 742.5},
            {17249, 765},
            {17749, 787.5},
            {18249, 810},
            {18749, 832.5},
            {19249, 855},
            {19749, 877.5},
            {20249, 900},
            {20749, 922.5},
            {21249, 945},
            {21749, 967.5},
            {22249, 990},
            {22749, 1012.5},
            {23249, 1035},
            {23749, 1057.5},
            {24249, 1080},
            {24749, 1102.5},
            {25249, 1125},
            {25749, 1147.5},
            {26249, 1170},
            {26749, 1192.5},
            {27249, 1215},
            {27749, 1237.5},
            {28249, 1260},
            {28749, 1282.5},
            {29249, 1305},
            {29749, 1327.5},
            {29750, 1350}
        };

        SSSCon = 1350;  // Default to the highest contribution

        if (monthly < 4250) {
            SSSCon = 180;
        } else if (monthly <= 29750) {
            for (double[] row : contributionTable) {
                if (monthly <= row[0]) {
                    SSSCon = row[1];
                    break;
                }
            }
        }
        return SSSCon;
    }

    /**
     * Calculates the Pag-IBIG contribution based on monthly income.
     * @param monthly The monthly income.
     * @return The calculated Pag-IBIG contribution.
     */
    private double calculatePag(double monthly) {
        double PagCon;

    {
        if (monthly <= 1500) {
            PagCon = monthly * 0.01;
        } else {
            PagCon = monthly * 0.02; 
        }
    }

        if (PagCon > 100) {
            PagCon = 100;
        }

        return PagCon;
    }

    /**
     * Calculates the PhilHealth contribution based on monthly income.
     * @param monthly The monthly income.
     * @return The calculated PhilHealth contribution.
     */
    private double calculatePhil(double monthly) {

        final double PhilRate = 0.05;
        double Philcon;

        if (monthly <= 10000 && monthly > 0) {
            Philcon = 500;
        } 
        else if (monthly > 10000 && monthly < 99999) {
            Philcon = monthly * PhilRate;
            Philcon /= 2;
        } 
        else {
            Philcon = 5000;
        }
        return Philcon;
    }

    /**
     * Calculates the net income after tax deductions.
     * @param monthly The monthly income.
     * @param SSS The SSS contribution.
     * @param Phil The PhilHealth contribution.
     * @param Pag The Pag-IBIG contribution.
     * @return The net income after deductions.
     */
    private double calculateTax(double monthly, double SSS, double Phil, double Pag) {

        double total_contributions = SSS + Phil + Pag;

        monthly -= total_contributions;
        if (monthly >= 20833 && monthly <= 33332) {
            
            // get the excess of 20833 and multiply by the tax rate, copy this for all monthly income bracket
              
               double monthly_tax = monthly - 20833;
               monthly_tax *= (0.15);
               monthly = monthly - monthly_tax;
            }
            else if (monthly >= 33333 && monthly <= 66666) {
               double monthly_tax = monthly - 33333;
               monthly_tax *= (0.20);
               
            // additional 2500 reduction tax from the previous bracket tax
               monthly = monthly - monthly_tax - 2500;
            }
            else if (monthly >= 66667 && monthly <= 166666) {
               double monthly_tax = monthly - 66667;
               monthly_tax *= (0.25);
               monthly = monthly - monthly_tax - 10833;
            }
            else if (monthly >= 166667 && monthly <= 666666) {
               double monthly_tax = monthly - 166667;
               monthly_tax *= (0.30);
               monthly = monthly - monthly_tax - 40833;
            }
            else if (monthly >= 666667) {
               double monthly_tax = monthly - 666667;
               monthly_tax *= (0.35);
               monthly = monthly - monthly_tax - 200833;
            }
            
            monthly = Math.round(monthly);
           
         
            return monthly;
         }
    }
