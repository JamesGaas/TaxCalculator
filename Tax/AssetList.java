package Tax;

/**
 * The AssetList class represents a collection of Asset objects.
 * It provides methods to add assets and calculate their total value.
 */
public class AssetList {
    private Asset[] assets;
    private int size;

    /**
     * Constructs an AssetList with the specified capacity.
     *
     * @param capacity the maximum number of assets that can be stored in the list
     */
    public AssetList(int capacity) {
        assets = new Asset[capacity];
        size = 0;
    }

    /**
     * Adds an asset to the list.
     *
     * @param asset the asset to be added
     */
    public void addAsset(Asset asset) {
        if (size < assets.length) {
            assets[size++] = asset;
        }
    }

    /**
     * Overloaded method to add an asset with specified parameters.
     * It creates an asset based on the provided asset type and additional value.
     *
     * @param assetName the name of the asset
     * @param assetValue the value of the asset
     * @param assetType the type of the asset (e.g., "Real Estate", "Stock", "Bond", "Other")
     * @param additionalValue an additional value used for specific asset types
     */
    public void addAsset(String assetName, double assetValue, String assetType, double additionalValue) {
        if (size < assets.length) {
            Asset newAsset = createAsset(assetName, assetValue, assetType, additionalValue);
            if (newAsset != null) {
                assets[size++] = newAsset;
            }
        }
    }

    /**
     * Creates an asset based on the specified type and parameters.
     *
     * @param assetName the name of the asset
     * @param assetValue the value of the asset
     * @param assetType the type of the asset
     * @param additionalValue an additional value used for specific asset types
     * @return the created asset, or null if the asset type is invalid
     */
    private Asset createAsset(String assetName, double assetValue, String assetType, double additionalValue) {
        switch (assetType) {
            case "Real Estate":
                return new RealEstate(assetName, assetValue, additionalValue);
            case "Stock":
                return new Stock(assetName, assetValue, additionalValue);
            case "Bond":
                return new Bond(assetName, assetValue, additionalValue);
            case "Other":
                return new Other(assetName, assetValue, additionalValue);
            default:
                System.out.println("Invalid asset type: " + assetType);
                return null;
        }
    }

    /**
     * Calculates the total value of all assets in the list.
     *
     * @return the total value of all assets
     */
    public double calculateTotalValue() {
        double totalValue = 0;
        for (int i = 0; i < size; i++) {
            totalValue += assets[i].calculateValue();
        }
        return totalValue;
    }

    /**
     * Returns an array of all current assets in the list.
     *
     * @return an array of current assets
     */
    public Asset[] getAssets() {
        Asset[] currentAssets = new Asset[size];
        System.arraycopy(assets, 0, currentAssets, 0, size);
        return currentAssets;
    }
}
