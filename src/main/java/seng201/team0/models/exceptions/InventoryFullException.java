package seng201.team0.models.exceptions;


/**
 * Applies when a player is trying to purchase
 * too many items in the Shop.
 */
public class InventoryFullException extends Exception {

    /**
     * Applies when a player is trying to purchase
     * too many items in the Shop.
     */
    public InventoryFullException() {
        super("Sorry, you can't buy more towers. Your inventory is full.");
    }
}
