package seng201.team0.unittests.services;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import seng201.team0.models.Cart;
import seng201.team0.models.Round;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class RoundTest {

    /**
     * @Description Stores the lower bound for generating track length for a
     * round
     */
    private static final int LOWER_BOUND_TRACK_LENGTH = 140;

    /**
     * @Description Stores the upper bound for generating track length for a
     * round.
     */
    private static final int UPPER_BOUND_TRACK_LENGTH = 160;

    /**
     * @Description Stores the lower bound for generating cart numbers for a
     * round.
     */
    private static final int LOWER_BOUND_CART_NUMBER = 1;

    /**
     * @Description Stores the upper bound for generating cart numbers for a
     * round.
     */
    private static final int UPPER_BOUND_CART_NUMBER = 3;

    /**
     * @Description Stores the multiplier for generating track length for a
     * round.
     */
    private static final int MULTIPLIER_TRACK_LENGTH = 6;

    @SuppressWarnings("checkstyle:JavadocVariable")
    private Round round;

    @SuppressWarnings("checkstyle:DesignForExtension")
    @BeforeEach
    public void setUp() {

        round = new Round(1);
    }

    @SuppressWarnings("checkstyle:MissingJavadocMethod")
    @Test
    public void testNumberOfCartsInitialization() {

        assertTrue(round.getNumberOfCarts() >= LOWER_BOUND_CART_NUMBER
                       && round.getNumberOfCarts() <= UPPER_BOUND_CART_NUMBER);
    }

    @SuppressWarnings("checkstyle:MissingJavadocMethod")
    @Test
    public void testLengthOfTrackInitialization() {
        int roundNumber = 1;
        int lower =
            LOWER_BOUND_TRACK_LENGTH - (roundNumber * MULTIPLIER_TRACK_LENGTH);
        int upper =
            UPPER_BOUND_TRACK_LENGTH - (roundNumber * MULTIPLIER_TRACK_LENGTH);
        assertTrue(round.getLengthOfTrack()
           >= lower && round.getLengthOfTrack() <= upper);
    }

    @SuppressWarnings("checkstyle:MissingJavadocMethod")
    @Test
    public void testGetNumberOfCarts() {
        assertTrue(round.getNumberOfCarts() > 0);
    }

    @SuppressWarnings("checkstyle:MissingJavadocMethod")
    @Test
    public void testGetLengthOfTrack() {

        assertTrue(round.getLengthOfTrack() > 0);
    }

    @SuppressWarnings("checkstyle:MissingJavadocMethod")
    @Test
    public void testGetCartList() {
        assertNotNull(round.getCartList());
        assertEquals(round.getNumberOfCarts(), round.getCartList().size());
        for (Cart cart : round.getCartList()) {
            assertTrue(cart != null);
        }
    }
}
