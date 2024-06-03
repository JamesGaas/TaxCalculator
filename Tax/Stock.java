package Tax;

/**
 * The Stock class represents stock assets in the application.
 */
public class Stock extends Asset {
    private static final double Average = 0.07;
    private double dividendYield;

    /**
     * Constructs a Stock object with the specified asset name, asset value, and dividend yield.
     * 
     * @param assetName     The name of the stock asset.
     * @param assetValue    The value of the stock asset.
     * @param dividendYield The dividend yield of the stock asset.
     */
    public Stock(String assetName, double assetValue, double dividendYield) {
        super(assetName, assetValue);
        this.dividendYield = dividendYield / 100;
    }

    /**
     * Constructs a Stock object with the specified asset name and asset value.
     * Uses the average dividend yield as the default value.
     * 
     * @param assetName  The name of the stock asset.
     * @param assetValue The value of the stock asset.
     */
    public Stock(String assetName, double assetValue) {
        super(assetName, assetValue);
        this.dividendYield = Average;
    }

    /**
     * Gets the dividend yield of the stock.
     * 
     * @return The dividend yield of the stock.
     */
    public double getDividendYield() {
        return dividendYield;
    }

    /**
     * Gets the average dividend yield.
     * 
     * @return The average dividend yield.
     */
    public static double getAverage() {
        return Average * 100;
    }

    /**
     * Sets the dividend yield of the stock.
     * 
     * @param dividendYield The new dividend yield to set.
     */
    public void setDividendYield(double dividendYield) {
        this.dividendYield = dividendYield;
    }

    /**
     * Calculates the value of the stock based on its dividend yield.
     * 
     * @return The calculated value of the stock.
     */
    @Override
    public double calculateValue() {
        return assetValue *= dividendYield;
    }
}
