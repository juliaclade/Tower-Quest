package seng201.team0.models.exceptions;

/**
 * Applies when the player hasn't made a valid selection.
 */
public class InvalidSelectionException extends Exception {

    /**
     * Applies when the player hasn't made a valid selection.
     */
    public InvalidSelectionException() {
        super("Please select a Tower or Upgrade");
    }
    public InvalidSelectionException(String e) {
        super(e);
    }

}
