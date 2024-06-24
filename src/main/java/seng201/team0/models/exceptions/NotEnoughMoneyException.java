package seng201.team0.models.exceptions;

/**
 * Applies when the player is trying to
 * buy an item and doesn't have enough money.
 */
public class NotEnoughMoneyException extends Exception {

    /**
     * Applies when the player is trying to
     * buy an item and doesn't have enough money.
     */
    public NotEnoughMoneyException() {
        super("Sorry, you don't have enough money");
    }
}
