package seng201.team0.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Creates a Round object that contains carts, track distance and a cart list.
 * @author Julia Clade and Katherine Irving Seguel
 * @version 1.0, 12 Apr 2024
 */
public class Round {

    /**
     * Stores the round's number of carts.
     */
    private final int numberOfCarts;

    /**
     * Stores the round's track-length.
     */
    private final int lengthOfTrack;

    /**
     * Stores the round's list of carts.
     */
    private final List<Cart> cartList;

    /**
     * Stores the lower bound for generating track length for a
     * round.
     */
    private static final int LOWER_BOUND_TRACK_LENGTH = 140;

    /**
     * Stores the upper bound for generating track length for a
     * round.
     */
    private static final int UPPER_BOUND_TRACK_LENGTH = 160;

    /**
     * Stores the lower bound for generating cart numbers for a
     * round.
     */
    private static final int LOWER_BOUND_CART_NUMBER = 1;

    /**
     * Stores the upper bound for generating cart numbers for a
     * round.
     */
    private static final int UPPER_BOUND_CART_NUMBER = 3;

    /**
     * Stores the multiplier for generating cart numbers for a
     * round.
     */
    private static final int MULTIPLIER_CART_NUMBER = 2;

    /**
     * Stores the multiplier for generating track length for a
     * round.
     */
    private static final int MULTIPLIER_TRACK_LENGTH = 6;


    /**
     * Random values are created for track length, number of carts and carts
     * are constructed and added to the cart list.
     * @param roundNumber (int)
     */
    public Round(final int roundNumber) {
        Random random = new Random();
        this.numberOfCarts = random.nextInt(LOWER_BOUND_CART_NUMBER,
                UPPER_BOUND_CART_NUMBER)
                + (roundNumber / MULTIPLIER_CART_NUMBER);
        this.lengthOfTrack = random.nextInt(LOWER_BOUND_TRACK_LENGTH,
                UPPER_BOUND_TRACK_LENGTH)
                - (roundNumber * MULTIPLIER_TRACK_LENGTH);
        this.cartList = new ArrayList<>();

        for (int i = 0; i < this.numberOfCarts; i++) {
            Cart newCart = new Cart(roundNumber);
            cartList.add(newCart);
        }
    }

    /**
     * Returns the number of carts in a round.
     * @return numberOfCarts Number of carts in the round.
     */
    public int getNumberOfCarts() {
        return this.numberOfCarts;
    }

    /**
     * Returns the round's track length.
     * @return lengthOfTrack Length of track of current round.
     */
    public int getLengthOfTrack() {
        return this.lengthOfTrack;
    }

    /**
     * Returns the list of carts.
     * @return List Round's list of carts.
     */
    public List<Cart> getCartList() {
        return this.cartList;
    }

}

