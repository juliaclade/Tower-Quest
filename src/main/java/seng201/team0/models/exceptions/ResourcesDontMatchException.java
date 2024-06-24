package seng201.team0.models.exceptions;

/**
 * Applies when a player tries to apply an
 * upgrade and the resources don't match.
 */
public class ResourcesDontMatchException extends Exception {

    /**
     * Applies when a player tries to apply an
     * upgrade and the resources don't match.
     */
    public ResourcesDontMatchException() {
        super("Resources don't match.");
    }
}
