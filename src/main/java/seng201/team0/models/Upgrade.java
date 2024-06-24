
package seng201.team0.models;

import java.util.Objects;
import java.util.Random;


/**
 * Stores images and their respective URLs  for Upgrades.
 * @see #Wood Resource type wood.
 * @see #Coal Resource type coal.
 * @see #Gold Resource type gold.
 */
enum UpgradeImageURL {

    /**
     *  Holds image path of the resource type wood.
     */
    Wood("images/woodUpgrade.png"),

    /**
     * Holds image path of the resource type gold.
     */
    Gold("images/goldUpgrade.png"),

    /**
     * Holds image path of the resource type coal.
     */
    Coal("images/coalUpgrade.png");

    /**
     * Stores the current image URL String.
     */
    private final String imageURL;

    /**
     * Assigns a URL to an image.
     * @param uRL String for the image's URL.
     */
    UpgradeImageURL(final String uRL) {
        imageURL = uRL;
    }

    /**
     * Returns the image's URL.
     * @return imageURL String for the image's URL.
     */
    public String getPath() {
        return imageURL;
    }
}

/**
 * Creates Upgrade objects which are a type of Item.
 *  Upgrade objects have lower stats than towers.
 * @author Julia Clade and Katherine Irving Seguel
 * @version 1.0, 11 Apr 2024
 */
@SuppressWarnings("checkstyle:JavadocType")
public class Upgrade extends Item {

    /**
     * Stores the lower bound for the upgrade's randomly created reload speed.
     */
    public static final int LOWER_BOUND_RELOAD_SPEED = 4;

    /**
     * Stores the upper bound for the upgrade's randomly created reload speed.
     */
    public static final int UPPER_BOUND_RELOAD_SPEED = 8;

    /**
     * Stores the lower bound for the upgrade's randomly created resource
     * amount.
     */
    public static final int LOWER_BOUND_RESOURCE_AMOUNT = 5;

    /**
     * Stores the upper bound for the upgrade's randomly created resource
     * amount.
     */
    public static final int UPPER_BOUND_RESOURCE_AMOUNT = 10;

    /**
     * Stores the lower bound for the upgrade's randomly created buying cost.
     */
    public static final int LOWER_BOUND_BUYING_COST = 4;

    /**
     * Stores the upper bound for the upgrade's randomly created buying cost.
     */
    public static final int UPPER_BOUND_BUYING_COST = 10;

    /**
     * Stores the multiplier for generating reload speed values.
     */
    public static final int RELOAD_SPEED_MULTIPLIER = 3;

    /**
     * Stores the percentage of sales price.
     */
    public static final double SALE_PRICE_PERCENTAGE = 0.8;


    /**
     * Uses the Item's method for construction of upgrades and then sets the
     * values to different values.
     * @param roundNumber Current round number.
     */
    public Upgrade(final int roundNumber) {
        super(roundNumber);
        Random random = new Random();
        this.setReloadSpeed(random.nextInt(LOWER_BOUND_RELOAD_SPEED
                * roundNumber, UPPER_BOUND_RELOAD_SPEED
                * roundNumber) - RELOAD_SPEED_MULTIPLIER
                *  roundNumber);
        this.setResourceAmount(random.nextInt(LOWER_BOUND_RESOURCE_AMOUNT
                        * roundNumber, UPPER_BOUND_RESOURCE_AMOUNT
                        * roundNumber));
        this.setBuyingCost(random.nextInt(LOWER_BOUND_BUYING_COST
                * roundNumber, UPPER_BOUND_BUYING_COST
                * roundNumber));
        this.setSellingCost((int) (this.getBuyingCost()
                * SALE_PRICE_PERCENTAGE));
        this.setLevelXP(0);
    }

    /**
     * Selects the correct image to display and returns its URL.
     * @param upgrade Upgrade we need to find an image for.
     * @return String Upgrade's image path.
     */
    public static String upgradeImagePath(final Upgrade upgrade) {
        String path = null;

        switch (upgrade.getResourceType()) {
            case "Wood":
                path = UpgradeImageURL.Wood.getPath();
                break;
            case "Gold":
                path = UpgradeImageURL.Gold.getPath();
                break;
            case "Coal":
                path = UpgradeImageURL.Coal.getPath();
                break;
            default:
                break;
        }
        return path;
    }

    /**
     * Returns the string format of the upgrade depending on the context.
     * If the context is "sell", the string will include the sell cost.
     * If the context is "buy", the string will include the buy cost.
     * If the context is "buy", the string won't add additional information.
     * @param context The context in which the upgrade string is needed
     *                ("sell" , "buy","apply upgrade").
     * @return The string format of the upgrade based on the context.
     */
    public String toString(String context) {
        context = context.toLowerCase();
        String add = null;
        if (Objects.equals(context, "buy")) {
            add = "Cost: $" + getBuyingCost();
        } else if (Objects.equals(context, "sell")) {
            add = "Sell Cost: $" + getSellingCost();
        } else if (Objects.equals(context, "apply upgrade")) {
            add = "";
        }
        return "Load: " + getResourceAmount() + "\n" + "Speed: "
                + getReloadSpeed() + "\n" + add;
    }

}
