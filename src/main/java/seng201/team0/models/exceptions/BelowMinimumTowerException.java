package seng201.team0.models.exceptions;

/**
 * Applies when a player tries to start a round
 * without any towers in the main towers list.
 */
public class BelowMinimumTowerException extends Exception {

    /**
     * Applies when a player tries to start a round
     * without any towers in the main towers list.
     */
    public BelowMinimumTowerException() {
        super("Your main tower list can't have less than one tower");
    }
    /**
     * Exception thrown when a player attempts to go to round set up with fewer
     * than the required number of towers.
     * @param message (String)
     */
    public BelowMinimumTowerException(final String message) {
         super(message);
    }
}
