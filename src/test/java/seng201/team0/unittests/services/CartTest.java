package seng201.team0.unittests.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seng201.team0.models.Cart;

import static org.junit.jupiter.api.Assertions.*;

/**
 * A class to test cart objects.
 */
public class CartTest {


    /**
     * A variable to mock a cart in the CartTester class.
     */
    private Cart cartTester;

    /**
     * This creates a mock cart object to run tests on.
     */
    @BeforeEach
    public void setupTest() {
        cartTester = new Cart(1);
    }

    @SuppressWarnings("checkstyle:MissingJavadocMethod")
    @Test
    public void testGetters() {
        final int speed = 4;
        cartTester.setSpeed(speed);
        assertEquals(speed, cartTester.getSpeed());
        cartTester.setResourceType("Wood");
        assertEquals("Wood", cartTester.getResourceType());
    }
    /**
     * This test checks if the 'checkIfFull' method works.
     */
    @Test
    public void testIfFull() {
        assertFalse(cartTester.checkIfFull());
        // fill up cart
        int size = cartTester.getStorageSize();
        cartTester.setCurrentLoad(size);
        assertTrue(cartTester.checkIfFull());
    }
}
