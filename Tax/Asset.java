package Tax;

/**
 * Asset is an abstract class
 */
public abstract class Asset {
    
    /**
     * An array of strings representing the types of assets.
     */
    public static final String[] ASSET_TYPES = {"Real Estate", "Stock", "Bond", "Other"};
    
    /**
     * The name of the asset.
     */
    protected String assetName;
    
    /**
     * The value of the asset.
     */
    protected double assetValue;

    /**
     * Constructs an Asset with the specified name and value.
     *
     * @param assetName the name of the asset
     * @param assetValue the value of the asset
     */
    public Asset(String assetName, double assetValue) {
        this.assetName = assetName;
        this.assetValue = assetValue;
    }

    /**
     * Returns the name of the asset.
     *
     * @return the name of the asset
     */
    public String getAssetName() {
        return assetName;
    }

    /**
     * Returns the value of the asset.
     *
     * @return the value of the asset
     */
    public double getAssetValue() {
        return assetValue;
    }

    /**
     * Sets the name of the asset.
     *
     * @param assetName the new name of the asset
     */
    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    /**
     * Sets the value of the asset.
     *
     * @param assetValue the new value of the asset
     */ 
    public void setAssetValue(double assetValue) {
        this.assetValue = assetValue;
    }

    /**
     * Calculates and returns the value of the asset.
     * This method must be implemented by subclasses to provide specific calculations.
     *
     * @return the calculated value of the asset
     */
    public abstract double calculateValue();
}
