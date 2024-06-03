package Tax;

/**
 * The RealEstate class represents real estate assets in the application.
 */
public class RealEstate extends Asset {
    private static final double Average = 0.06;
    private double capitalAppreciationValue;

    /**
     * Constructs a RealEstate object with the specified asset name, asset value, and capital appreciation value.
     * 
     * @param assetName               The name of the real estate asset.
     * @param assetValue              The value of the real estate asset.
     * @param capitalAppreciationValue The capital appreciation value of the real estate asset.
     */
    public RealEstate(String assetName, double assetValue, double capitalAppreciationValue) {
        super(assetName, assetValue);
        this.capitalAppreciationValue = capitalAppreciationValue / 100;
    }

    /**
     * Constructs a RealEstate object with the specified asset name and asset value.
     * Uses the average capital appreciation value as the default value.
     * 
     * @param assetName  The name of the real estate asset.
     * @param assetValue The value of the real estate asset.
     */
    public RealEstate(String assetName, double assetValue) {
        super(assetName, assetValue);
        this.capitalAppreciationValue = Average;
    }

    /**
     * Gets the capital appreciation value of the real estate.
     * 
     * @return The capital appreciation value of the real estate.
     */
    public double getCapitalAppreciationValue() {
        return capitalAppreciationValue;
    }

    /**
     * Gets the average capital appreciation value.
     * 
     * @return The average capital appreciation value.
     */
    public static double getAverage() {
        return Average * 100;
    }

    /**
     * Sets the capital appreciation value of the real estate.
     * 
     * @param capitalAppreciationValue The new capital appreciation value to set.
     */
    public void setCapitalAppreciationValue(double capitalAppreciationValue) {
        this.capitalAppreciationValue = capitalAppreciationValue;
    }

    /**
     * Calculates the value of the real estate based on its capital appreciation value.
     * 
     * @return The calculated value of the real estate.
     */
    @Override
    public double calculateValue() {
        return assetValue *= capitalAppreciationValue;
    }
}
