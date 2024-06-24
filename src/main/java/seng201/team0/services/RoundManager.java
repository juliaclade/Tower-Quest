package seng201.team0.services;

import seng201.team0.models.Cart;
import seng201.team0.models.Round;
import seng201.team0.models.Tower;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static java.lang.Math.min;

/**
 * This class takes a list of main towers and a round object. It
 * initializes a list of random tower positions in sorted order. These
 * parameters are then used to play a round. Carts take turns travelling along a
 * track. When they reach a tower, if compatible, the carts are loaded with the
 * resource. If all carts are filled, the round is won. At the end of a round,
 * earning can be calculated.
 * @author Julia Clade and Katherine Irving Seguel
 * @version 1.0, 15 Apr 2024
 */
public class RoundManager {

    /**
     * Stores the player's main towers.
     */
    private final List<Tower> mainTowers;

    /**
     * Used in calculations for player income after a round
     * is won if the cart is filled with coal.
     */
    private static final int EARNINGS_COAL_MULTIPLIER = 2;

    /**
     * Used in calculations for player income after a round
     * is won if the cart is filled with gold.
     */
    private static final int EARNINGS_GOLD_MULTIPLIER = 3;

    /**
     * Stores the current round number.
     */
    private final int roundNumber;

    /**
     * Stores the current round's carts.
     */
    private List<Cart> cartList;

    /**
     * A randomly generated, sorted list of integers with values
     * from 0 to track length. This is used to calculate the current resource
     * amount of a tower based on cart speed and reload time.
     */
    private final List<Integer> towerPositions;

    /**
     * Initializes towerPositions and sets class variables.
     * @param mainTowers A list of the player's main towers which are used in
     *                   the current round.
     * @param round      Round objects keep track of number of carts and length
     *                   of track.
     * @param roundNumber Keeps track of the current round in the game.
     */
    @SuppressWarnings("checkstyle:HiddenField")
    public RoundManager(final List<Tower> mainTowers, final Round round,
                        final int roundNumber) {
        this.roundNumber = roundNumber;
        Random random = new Random();
        this.mainTowers = mainTowers;
        this.cartList = round.getCartList();
        int lengthOfTrack = round.getLengthOfTrack();
        this.towerPositions = new ArrayList<>();
        for (Tower ignored : mainTowers) {
            this.towerPositions.add(random.nextInt(1, lengthOfTrack));
        }
        Collections.sort(this.towerPositions);
    }

    /**
     * Carts take turns travelling along a track. When they reach a
     * tower, if resource types are compatible, the carts are loaded with the
     * resource. If all carts are filled successfully before reaching the end of
     * the track, the round is won.
     * @return true if won, false if lost.
     */
    public boolean playRound() {
        for (Cart cart : cartList) {
            for (int i = 0; i < towerPositions.size(); i++) {
                int seconds;
                Tower tower = mainTowers.get(i);
                int position = towerPositions.get(i);
                seconds = (int) Math.ceil((double) position / cart.getSpeed());

                if (tower.getResourceType().equals(cart.getResourceType())) {
                    int currentResourceAmount =
                            seconds * tower.getReloadSpeed();
                    int toAdd = min(currentResourceAmount,
                            tower.getResourceAmount());
                    cart.setCurrentLoad(cart.getCurrentLoad() + toAdd);
                }
            }
            if (!cart.checkIfFull()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Calculates income based on resource type. Gold is more
     * valuable than coal and wood is the least valuable resource. Cart size is
     * used to get the final amount.
     * @return Money earned during a round.
     */
    public int calculateEarnings() {
        int sum = 0;
        for (Cart cart : cartList) {
            if (cart.getResourceType().equals("Wood")) {
                sum += cart.getStorageSize() + cart.getStorageSize();
            } else if (cart.getResourceType().equals("Coal")) {
                sum += EARNINGS_COAL_MULTIPLIER * cart.getStorageSize();
            } else {
                sum += EARNINGS_GOLD_MULTIPLIER * cart.getStorageSize();
            }
        }
        return (int) sum * 2 / roundNumber;
    }

    /**
     * Returns the player's main towers list.
     * @return mainTowers The player's main towers.
     */
    public List<Tower> getMainTowers() {
        return mainTowers;
    }

    /**
     * Returns the round's cart list.
     * @return cartList List of the round's carts.
     */
    public List<Cart> getCartList() {
        return cartList;
    }

    /**
     * Returns the list of tower positions.
     * @return towerPositions Stores the location of each tower along the track.
     */
    public List<Integer> getTowerPositions() {
        return towerPositions;
    }

    /**
     * Sets the cart list.
     * @param cartList a list of Carts.
     */
    @SuppressWarnings("checkstyle:HiddenField")
    public void setCartList(final List<Cart> cartList) {
        this.cartList = cartList;
    }
}


