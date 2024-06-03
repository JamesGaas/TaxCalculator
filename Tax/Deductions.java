package Tax;

/**
 * The Deductions class represents deductions made from a person's income for various contributions.
 * It calculates the yearly net income after deducting contributions such as SSS, PhilHealth, and Pag-IBIG.
 */
class Deductions {
    // Instance variables to store income and deductions
    private double monthlyIncome;
    private double sssContribution;
    private double month13Pay;
    private double philhealthContribution;
    private double pagIbigContribution;

    /**
     * Sets the monthly income of the individual.
     * @param monthlyIncome The monthly income of the individual.
     */
    public void setMonthlyIncome(double monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }

    /**
     * Sets the SSS (Social Security System) contribution.
     * @param sssContribution The SSS contribution.
     */
    public void setSSSContribution(double sssContribution) {
        this.sssContribution = sssContribution;
    }

    /**
     * Sets the 13th-month pay.
     * @param month13Pay The 13th-month pay.
     */
    public void setMonth13Pay(double month13Pay) {
        this.month13Pay = month13Pay;
    }

    /**
     * Sets the PhilHealth contribution.
     * @param philhealthContribution The PhilHealth contribution.
     */
    public void setPhilhealthContribution(double philhealthContribution) {
        this.philhealthContribution = philhealthContribution;
    }

    /**
     * Sets the Pag-IBIG (Home Development Mutual Fund) contribution.
     * @param pagIbigContribution The Pag-IBIG contribution.
     */
    public void setPagIbigContribution(double pagIbigContribution) {
        this.pagIbigContribution = pagIbigContribution;
    }

    /**
     * Calculates the yearly net income after deducting contributions.
     * @return The yearly net income after deductions.
     */
    public double calculateYearlyNetIncome() {
        // Calculate total deductions
        double totalContributions = sssContribution + philhealthContribution + pagIbigContribution;
        // Calculate net monthly income after deductions
        double netMonthlyIncome = monthlyIncome - totalContributions;
        // Calculate yearly net income including 13th-month pay
        double yearlyNetIncome = (netMonthlyIncome * 12) + month13Pay;
        return yearlyNetIncome;
    }
}
