package seng201.team0.models;

import java.util.Random;

/**
 * This class creates Cart Objects.
 * @author Julia Clade and Katherine Irving Seguel
 * @version 1.0, 11 Apr 2024
 */
public class Cart {

    /**
     * Stores possible resource types for tower and upgrade creation.
     */
    private static final String[] RESOURCE_TYPES = {"Wood", "Gold", "Coal"};

    /**
     * Stores the lower bound for the randomly
     * created speed of a cart.
     */
    public static final int LOWER_BOUND_SPEED = 3;

    /**
     * Stores the upper bound for the randomly
     * created speed of a cart.
     */
    public static final int UPPER_BOUND_SPEED = 8;

    /**
     * Stores the lower bound for
     * the randomly created size of a cart.
     */
    public static final int LOWER_BOUND_SIZE = 15;

    /**
     * Stores the upper bound for the
     * randomly created size of a cart.
     */
    public static final int UPPER_BOUND_SIZE = 20;

    /**
     * Holds the current amount of resources in the cart.
     */
    private int currentLoad;

    /**
     * Stores the size of the cart, the maximum number of items cart can carry.
     */
    private int storageSize;

    /**
     * Stores the resource type cart is able to carry.
     */
    private String resourceType;

    /**
     * Stores the speed of the cart in meters per second.
     */
    private int speed;

    /**
     * Stores the cart's fill-status.
     */
    private boolean isFull;

    /**
     * A constructor method to create an empty cart with random
     * values.
     * @param currentRoundNumber (int)
     */
    public Cart(final int currentRoundNumber) {
        Random random = new Random();
        this.currentLoad = 0;
        this.storageSize = random.nextInt(LOWER_BOUND_SIZE
                        * currentRoundNumber,
                UPPER_BOUND_SIZE * currentRoundNumber);
        this.resourceType =
                RESOURCE_TYPES[random.nextInt(RESOURCE_TYPES.length)];
        this.speed = random.nextInt(LOWER_BOUND_SPEED
                        * currentRoundNumber,
                UPPER_BOUND_SPEED * currentRoundNumber);
        this.isFull = false;
    }

    /**
     * This function sets the load of the cart when it is filled
     * by a tower.
     * @param currentLoad (int) The current load status of the cart;
     */
    @SuppressWarnings("checkstyle:HiddenField")
    public void setCurrentLoad(final int currentLoad) {
        this.currentLoad += currentLoad;
    }

    /**
     * This function returns the current load status of the cart.
     * @return currentLoad (int)
     */
    public int getCurrentLoad() {
        return this.currentLoad;
    }

    /**
     * This function return how big the cart is; how many resources
     * it can hold.
     * @return storageSize (int)
     */
    public int getStorageSize() {
        return this.storageSize;
    }

    /**
     * Sets the cart's storage size.
     * @param size int
     */
    public void setStorageSize(final int size) {
        this.storageSize = size; }

    /**
     * This function returns the speed of the cart.
     * @return speed (int)
     */
    public int getSpeed() {
        return this.speed;
    }

    /**
     * This function returns the cart's resource type.
     * @return resourceType (String)
     */
    public String getResourceType() {
        return this.resourceType;
    }

    /**
     * This function returns a boolean that represent whether a
     * cart is full after being load by a tower.
     * @return isFull (boolean) False if cart is not full, True if the cart is
     * full
     */
    public boolean checkIfFull() {
        if (this.getCurrentLoad() >= this.storageSize) {
            isFull = true;
        }
        return isFull;
    }

    /**
     * Sets the resource type of the cart.
     * @param resource type of resource.
     */
    public void setResourceType(final String resource) {
        this.resourceType = resource;
    }

    /**
     * Sets the speed of the cart.
     * @param speedOfCart How fast the cart travels on the track.
     */
    public void setSpeed(final int speedOfCart) {
        this.speed = speedOfCart;
    }
}
