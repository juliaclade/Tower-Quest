package seng201.team0.models;

import java.util.Random;

/**
 * Parent class for towers and upgrades; contains some shared
 * attributes and methods between both classes.
 * @author Julia Clade and Katherine Irving Seguel
 * @version 1.0, 11 Apr 2024
 */
public class Item {

    /**
     * Stores possible types of resources to be used in tower and upgrade
     * construction.
     */
    private static final String[] RESOURCE_TYPES = {"Wood", "Gold", "Coal"};

    /**
     * Stores item's resource type.
     */
    private String resourceType;

    /**
     * Stores how many seconds it takes for the item to load its resource.
     */
    private int reloadSpeed;

    /**
     * Stores the item's buying price in the shop.
     */
    private int buyingCost;

    /**
     * Stores the item's sell-back price in the shop.
     */
    private int sellingCost;

    /**
     * Stores the item's XP value.
     */
    private int levelXP;

    /**
     * Stores the maximum resource amount a tower can load into a cart at a
     * time.
     */
    private int resourceAmount;

    /**
     * Stores the item's lower bound for the randomly created reload speed.
     */
    public static final int LOWER_BOUND_RELOAD_SPEED = 15;

    /**
     * Stores the item's upper bound for the randomly created reload speed.
     */
    public static final int UPPER_BOUND_RELOAD_SPEED = 20;

    /**
     * Stores the item's lower bound for the randomly created resource amount.
     */
    public static final int LOWER_BOUND_RESOURCE_AMOUNT = 50;

    /**
     * Stores the item's upper bound for the randomly created resource amount.
     */
    public static final int UPPER_BOUND_RESOURCE_AMOUNT = 60;

    /**
     * Stores the item's lower bound for the randomly created buying cost.
     */
    public static final int LOWER_BOUND_BUYING_COST = 30;

    /**
     * Stores the item's upper bound for the randomly created buying cost.
     */
    public static final int UPPER_BOUND_BUYING_COST = 50;

    /**
     * Stores the multiplier for generating reload speed values.
     */
    public static final double ITEM_CONSTRUCTOR_MULTIPLIER = 1.5;

    /**
     * Stores the sales price percentage.
     */
    public static final double SALE_PRICE_PERCENTAGE = 0.8;

    /**
     * Constructor initializes items with XP set to 0 and random values
     * based on upper and lower bounds.The current round number is used as a
     * multiplier to give better values in higher rounds.
     * @param roundNumber The current round number in the game.
     */
    public Item(final int roundNumber) {
        Random random = new Random();
        int multiplier = (int) (ITEM_CONSTRUCTOR_MULTIPLIER * roundNumber);
        this.resourceType =
                RESOURCE_TYPES[random.nextInt(RESOURCE_TYPES.length)];
        this.reloadSpeed = random.nextInt(LOWER_BOUND_RELOAD_SPEED
                * multiplier, (UPPER_BOUND_RELOAD_SPEED * multiplier) + 1);
        this.levelXP = 1;
        this.resourceAmount = random.nextInt(LOWER_BOUND_RESOURCE_AMOUNT
                * multiplier, (UPPER_BOUND_RESOURCE_AMOUNT * multiplier) + 1);
        this.buyingCost = random.nextInt(LOWER_BOUND_BUYING_COST
                * multiplier, (UPPER_BOUND_BUYING_COST * multiplier) + 1);
        this.sellingCost = (int) (buyingCost * SALE_PRICE_PERCENTAGE);
    }

    /**
     * Returns the item's buying cost.
     * @return buyingCost The item's buying cost.
     */
    public int getBuyingCost() {
        return this.buyingCost;
    }

    /**
     * Sets the buying and selling cost of an item in
     * the shop. Sales value is 0.8 times the original cost.
     * @param buyingCost The item's buying cost.
     */
    @SuppressWarnings("checkstyle:HiddenField")
    public void setBuyingCost(final int buyingCost) {
        this.buyingCost = buyingCost;
        this.sellingCost = (int) (buyingCost * SALE_PRICE_PERCENTAGE);
    }

    /**
     * Returns the item's resource type.
     * @return resourceType. The item's resource type.
     */
    public String getResourceType() {
        return this.resourceType;
    }

    /**
     * Sets the item's resourceType.
     * @param string (String)
     */
    public void setResourceType(final String string) {
        this.resourceType = string;
    }

    /**
     * Sets the item's selling cost .
     * @param sellingCost (int).
     */
    @SuppressWarnings("checkstyle:HiddenField")
    public void setSellingCost(final int sellingCost) {

        this.sellingCost = sellingCost;
    }

    /**
     * Returns the item's selling price.
     * @return sellingCost The sell-back price of the item in the shop.
     */
    public int getSellingCost() {
        return this.sellingCost;
    }

    /**
     * Sets the item's reloadSpeed.
     * @param reloadSpeed Seconds it takes for the item to
     * load its resource.
     */
    @SuppressWarnings("checkstyle:HiddenField")
    public void setReloadSpeed(final int reloadSpeed) {
        this.reloadSpeed = reloadSpeed;
    }

    /**
     * Returns the item's reload speed.
     * @return reloadSpeed Seconds it takes for the item to
     * load its resource.
     */
    public int getReloadSpeed() {
        return reloadSpeed;
    }

    /**
     * Returns the XP value for Towers.
     * @return levelXP
     */
    public int getLevelXP() {
        return levelXP;
    }

    /**
     * Set's the item's XP value.
     * @param levelXP Item's XP value.
     */
    @SuppressWarnings("checkstyle:HiddenField")
    public void setLevelXP(final int levelXP) {
        this.levelXP = levelXP;
    }

    /**
     * Returns item's resource amount.
     * @return resourceAmount Amount of resources that can be loaded into a
     * cart at a time.
     */
    public int getResourceAmount() {
        return resourceAmount;
    }

    /**
     * Sets the item's resource amount.
     * @param resourceAmount Amount of resources that can be loaded into a
     * cart at a time.
     */
    @SuppressWarnings("checkstyle:HiddenField")
    public void setResourceAmount(final int resourceAmount) {
        this.resourceAmount = resourceAmount;
    }
}
