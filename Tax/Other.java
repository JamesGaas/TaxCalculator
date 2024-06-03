package Tax;

/**
 * The Other class represents an asset of type "Other" in the application.
 * It extends the Asset class and includes additional properties specific to "Other" assets.
 */
public class Other extends Asset {

    private static final double Average = 0.05; // Default average return rate
    private double returnP; // Return rate

    /**
     * Constructs an Other asset with the given name, value, and return rate.
     * 
     * @param assetName The name of the asset.
     * @param assetValue The value of the asset.
     * @param returnP The return rate of the asset (as a percentage).
     */
    public Other(String assetName, double assetValue, double returnP) {
        super(assetName, assetValue); // Call the constructor of the superclass (Asset)
        this.returnP = returnP / 100; // Initialize return rate (convert from percentage to decimal)
    }
    
    /**
     * Constructs an Other asset with the given name and value, using the default average return rate.
     * 
     * @param assetName The name of the asset.
     * @param assetValue The value of the asset.
     */
    public Other(String assetName, double assetValue) {
        super(assetName, assetValue); // Call the constructor of the superclass (Asset)
        this.returnP = Average; // Use the default average return rate
    }
    
    /**
     * Gets the average return rate for Other assets.
     * 
     * @return The average return rate as a percentage.
     */
    public static double getAverage() {
        return Average * 100; // Get the average return rate as a percentage
    }

    /**
     * Calculates the value of the Other asset based on its return rate.
     * 
     * @return The calculated value of the asset.
     */
    @Override
    public double calculateValue() {
        return assetValue * returnP; // Calculate the value of the asset based on the return rate
    }
}
