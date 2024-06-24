package seng201.team0.unittests.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seng201.team0.models.Cart;
import seng201.team0.models.Round;
import seng201.team0.models.Tower;
import seng201.team0.services.RoundManager;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RoundManagerTest {

    private RoundManager roundManager;
    private List<Tower> mainTowers;
    private Round round;
    /**
     * @Description Used in calculations for player income after a round
     * is won if the cart is filled with coal.
     */
    private static final int EARNINGS_COAL_MULTIPLIER = 2;

    /**
     * @Description Used in calculations for player income after a round
     * is won if the cart is filled with gold.
     */
    private static final int EARNINGS_GOLD_MULTIPLIER = 3;

    @BeforeEach
    public void setUp() {

        mainTowers = new ArrayList<>();
        mainTowers.add(new Tower(1));
        mainTowers.add(new Tower(1));
        mainTowers.add(new Tower(1));
        round = new Round(1);

        roundManager = new RoundManager(mainTowers, round, 1);
    }

    @Test
    public void testRoundManagerInitialization() {
        assertNotNull(roundManager);
        int lengthOfTrack = round.getLengthOfTrack();
        assertEquals(3, roundManager.getMainTowers().size());

        List<Integer> towerPositions = roundManager.getTowerPositions();
        for (int position : towerPositions) {
            assertTrue(position >= 1 && position < lengthOfTrack);
        }
    }

    @Test
    public void testPlayRoundSuccessful() {
        boolean won = roundManager.playRound();
        if (won) {
            for (Cart cart : roundManager.getCartList()) {
                assertTrue(cart.checkIfFull());
            }
        } else {
            boolean cartEmpty = false;
            for (Cart cart : round.getCartList()) {
                if (!cart.checkIfFull()) {
                    cartEmpty = true;
                }
                assertTrue(cartEmpty);
            }
        }
    }

    @Test
    public void testPlayRoundUnsuccessful() {
        for (Tower tower : mainTowers) {
            tower.setResourceAmount(0);
        }
        boolean result = roundManager.playRound();
        assertFalse(result);
    }

    @Test
    public void testCalculateEarnings() {
        List<Cart> cartList = new ArrayList<Cart>();
        Cart cart1 = new Cart(1);
        cart1.setResourceType("Wood");
        cart1.setStorageSize(100);
        cart1.setCurrentLoad(100);
        cartList.add(cart1);
        cart1 = new Cart(1);
        cart1.setResourceType("Coal");
        cart1.setStorageSize(100);
        cart1.setCurrentLoad(100);
        cartList.add(cart1);
        cart1 = new Cart(1);
        cart1.setResourceType("Gold");
        cart1.setStorageSize(100);
        cart1.setCurrentLoad(100);
        cartList.add(cart1);

        assertEquals(3, cartList.size());
        roundManager.setCartList(cartList);
        int sum = 0;
        for (Object cart : cartList) {
            if (((Cart) cart).getResourceType().equals("Wood")) {
                sum += ((Cart) cart).getStorageSize()
                        + ((Cart) cart).getStorageSize();
            } else if (((Cart) cart).getResourceType().equals("Coal")) {
                sum += EARNINGS_COAL_MULTIPLIER
                        * ((Cart) cart).getStorageSize();
            } else {
                sum += EARNINGS_GOLD_MULTIPLIER
                        * ((Cart) cart).getStorageSize();
            }
        }
            assertEquals(2 * sum, roundManager.calculateEarnings());
        }
}
