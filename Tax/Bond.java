package Tax;

/**
 * The Bond class represents a financial asset of type bond.
 * It extends the Asset class and includes additional attributes specific to bonds.
 */
public class Bond extends Asset {
    private static double Average = 0.02; // Default average yield for bonds
    private double yieldToMaturity; // Yield to maturity of the bond

    /**
     * Constructs a Bond object with the specified name, value, and yield to maturity.
     *
     * @param assetName the name of the bond
     * @param assetValue the value of the bond
     * @param yieldToMaturity the yield to maturity of the bond
     */
    public Bond(String assetName, double assetValue, double yieldToMaturity) {
        super(assetName, assetValue);
        this.yieldToMaturity = yieldToMaturity / 100;
    }

    /**
     * Constructs a Bond object with the specified name and value.
     * The yield to maturity is set to the default average yield.
     *
     * @param assetName the name of the bond
     * @param assetValue the value of the bond
     */
    public Bond(String assetName, double assetValue) {
        super(assetName, assetValue);
        this.yieldToMaturity = Average;
    }

    /**
     * Returns the yield to maturity of the bond.
     *
     * @return the yield to maturity
     */
    public double getYieldToMaturity() {
        return yieldToMaturity;
    }

    /**
     * Returns the average yield for bonds.
     *
     * @return the average yield
     */
    public static double getAverage() {
        return Average * 100;
    }

    /**
     * Sets the yield to maturity of the bond.
     *
     * @param yieldToMaturity the yield to maturity to set
     */
    public void setYieldToMaturity(double yieldToMaturity) {
        this.yieldToMaturity = yieldToMaturity;
    }

    /**
     * Calculates the value of the bond based on its yield to maturity.
     *
     * @return the calculated value of the bond
     */
    @Override
    public double calculateValue() {
        return assetValue *= yieldToMaturity;
    }
}
