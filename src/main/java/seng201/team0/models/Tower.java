package seng201.team0.models;

import seng201.team0.models.exceptions.ResourcesDontMatchException;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Enum for Tower image URLs.
 */
enum TowerImageURL {

    /**
     * Holds image path of the resource type wood.
     */
    Wood("images/woodtower.png"),

    /**
     *  Holds image path of the resource type gold.
     */
    Gold("images/goldtower.png"),

    /**
     * Holds image path of the resource type coal.
     */
    Coal("images/coaltower.png");

    /**
     * Stores the current image URL String.
     */
    private final String imageURL;

    /**
     * Assigns a URL to an image.
     * @param uRL String for the image's URL.
     */
    TowerImageURL(final String uRL) {
        imageURL = uRL;
    }

    /**
     * Returns the image's URL.
     * @return imageURL String
     */
    public String getPath() {
        return imageURL;
    }
}

/**
 * Class creates towers and applies upgrades to towers.
 * @author Julia Clade and Katherine Irving Seguel
 * @version 1.0, 11 Apr 2024
 */
@SuppressWarnings("checkstyle:JavadocType")
public class Tower extends Item {

    /**
     * Stores the upgrades that have been applied to towers.
     */
    private final ArrayList<Upgrade> upgradeTracker;
    /**
     * XP increase percentage of selling price when the XP of tower is increase.
     */
    public static final double XP_SELL_PRICE_INCREASE = 1.2;

    /**
     * Calls constructor of parent class and initializes a
     *  new array upgradeTracker to store upgrades.
     * @param roundNumber Current round number
     */
    public Tower(final int roundNumber) {
        super(roundNumber);
        this.upgradeTracker = new ArrayList<>();
    }

    /**
     * Applies an upgrade to a tower and increments all relevant variables
     * according to the upgrade values.
     * @param upgrade Upgrade to be applied to a tower.
     * @throws  ResourcesDontMatchException If resources of upgrade and tower
     * don't match.
     */
    public void applyUpgrade(final Upgrade upgrade)
            throws ResourcesDontMatchException {
        if (Objects.equals(upgrade.getResourceType(), getResourceType())) {
            this.setResourceType(upgrade.getResourceType());
            int newSpeed = this.getReloadSpeed() + upgrade.getReloadSpeed();
            this.setReloadSpeed(newSpeed);
            this.upgradeTracker.add(upgrade);
            this.setResourceAmount(this.getResourceAmount()
                    + upgrade.getResourceAmount());
            int newSellingCost =
                    this.getSellingCost() + upgrade.getSellingCost();
            this.setSellingCost(newSellingCost);
            if (upgradeTracker.size() == 2) {
                levelUpXP();
                resetUpgradeTracker();
            }

        } else {
            throw new ResourcesDontMatchException();
        }
    }

    /**
     * Increments resource amount , reload speed and selling price after
     * Tower XP has
     * increased.
     * @param levelXP Stores the tower's XP level.
     */
    public void applyXPUpgrade(final int levelXP) {
        this.setReloadSpeed(this.getReloadSpeed() + levelXP);
        this.setResourceAmount(this.getResourceAmount() + levelXP);
        this.setSellingCost((int) (this.getSellingCost()
                * XP_SELL_PRICE_INCREASE));
    }

    /**
     * Increments Towers XP for every two upgrades that have been applied.
     * Calls apply XP upgrade to update other tower stats.
     */
    public void levelUpXP() {
        int currentXP = getLevelXP();
        setLevelXP(currentXP + 1);
        applyXPUpgrade(getLevelXP());

    }

    /**
     * Returns the string format of the tower depending on the context.
     * If the context is "sell", the string will include the sell cost.
     * If the context is "buy", the string will include the buy cost and
     * removes XP information.
     * If the context is "buy", the string won't add additional information.
     * @param context The context in which the tower string is needed
     *                ("sell" , "buy","swap").
     * @return The string format of the upgrade based on the context.
     */


    public String toString(String context) {
        context = context.toLowerCase();
        String xP = "XP: " + getLevelXP()
                + "\n";
        String add;
        if (Objects.equals(context, "buy")) {
            add = "Cost: $" + getBuyingCost();
            xP = "";
        } else if (Objects.equals(context, "sell")) {
            add = "Sell Cost: $" + getSellingCost();
        } else {
          add = "";
        }

        return xP + "Load: " + getResourceAmount() + "\n" + "Speed: "
                + getReloadSpeed() + "\n" + add;
    }


    /**
     * Returns the string of the path for the tower image.
     * @param tower Tower to find tan image for.
     * @return String Tower image path.
     */
    public static String towerImagePath(final Tower tower) {
        String path = null;

        switch (tower.getResourceType()) {
            case "Wood":
                path = TowerImageURL.Wood.getPath();
                break;
            case "Gold":
                path = TowerImageURL.Gold.getPath();
                break;
            case "Coal":
                path = TowerImageURL.Coal.getPath();
                break;
            default:
                break;
        }
        return path;
    }

    /**
     * Returns the upgrade tracker list.
     * @return upgradeTracker List that keeps track of how many upgrades have
     * been applied to a tower.
     */
    public ArrayList<Upgrade> getUpgradeTracker() {
        return upgradeTracker;
    }

    /**
     * Clears the upgradeTracker.
     */
    public void resetUpgradeTracker() {
        upgradeTracker.clear();
    }
}
