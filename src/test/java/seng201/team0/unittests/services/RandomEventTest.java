package seng201.team0.unittests.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seng201.team0.models.RandomEvent;
import seng201.team0.models.Tower;
import seng201.team0.models.exceptions.BelowMinimumTowerException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


/**
 * Test Random Event implementation.
 */
public class RandomEventTest {

    /**
     * @Description  Value for random event.
     */
    private static final int RANDOM_EVENT_ONE = 1;

    /**
     * @Description  Value for random event.
     */
    private static final int RANDOM_EVENT_TWO = 2;

    /**
     * @Description  Value for random event.
     */
    private static final int RANDOM_EVENT_THREE = 3;

    /**
     * @Description  Value for random event.
     */
    private static final int RANDOM_EVENT_FOUR = 4;

    /**
     * @Description  Value for random event.
     */
    private static final int RANDOM_EVENT_FIVE = 5;

    /**
     * @Description  Value for random event.
     */
    private static final int RANDOM_EVENT_SIX = 6;

    /**
     * @Description  Value for random event.
     */
    private static final int RANDOM_EVENT_SEVEN = 7;

    @SuppressWarnings("checkstyle:JavadocVariable")
    private RandomEvent randomEventTest;
    @SuppressWarnings("checkstyle:JavadocVariable")
    private List<Tower> mainTowerList;
    @SuppressWarnings("checkstyle:JavadocVariable")
    private List<Tower> reserveTowerList;
    @SuppressWarnings("checkstyle:JavadocVariable")
    private int currentRound;
    @SuppressWarnings("checkstyle:JavadocVariable")
    private int totalRounds;

    /**
     * Constructor for RandomEventTest. It initializes a mainTowerList and
     * reserveTowerList.
     * It runs tests based on round number 1 and a total of 5 rounds.
     */
    public RandomEventTest() {

        this.mainTowerList = new ArrayList<>();
        this.reserveTowerList = new ArrayList<>();
        final int maxTowers = 5;
        for (int i = 0; i < maxTowers; i++) {
            mainTowerList.add(new Tower(1));
        }
        for (int i = 0; i < maxTowers; i++) {
            reserveTowerList.add(new Tower(1));
        }
        final int rounds = 5;
        this.currentRound = 1;
        this.totalRounds = rounds;
    }

    /**
     * This creates a mock random event object to run tests on.
     */
    @BeforeEach
    public void setupTest() {
        randomEventTest = new RandomEvent(mainTowerList, reserveTowerList,
                                          currentRound, totalRounds);
    }

    /**
     * Testing Random event where a tower is stolen.
     */
    @Test
    public void testInitializeCase1() {
        final int fullLists = 10;
        final int towerMissing = fullLists - 1;
        assertEquals(fullLists, mainTowerList.size()
                + reserveTowerList.size());
        try {
            String message = randomEventTest.activateRandomEvent(1);
            assertEquals(towerMissing, mainTowerList.size()
                    + reserveTowerList.size());

        } catch (BelowMinimumTowerException e) {
            System.out.println(e.getMessage());
        }
        this.mainTowerList = new ArrayList<>();
        mainTowerList.add(new Tower(1));
        this.reserveTowerList = new ArrayList<>();

        randomEventTest = new RandomEvent(mainTowerList, reserveTowerList,
                                          currentRound, totalRounds);
        assertEquals(1, mainTowerList.size());
        assertEquals(0, reserveTowerList.size());
        assertThrows(BelowMinimumTowerException.class, () -> {
            randomEventTest.activateRandomEvent(1);
        });
    }

    /**
     * Testing Random event where all towers have reload speed halved.
     */
    @Test
    public void testInitializeCase2() {
        ArrayList<Integer> currentReloadSpeeds = new ArrayList<>();
        for (Tower tower : mainTowerList) {
            currentReloadSpeeds.add(tower.getReloadSpeed());
        }
        for (Tower tower : reserveTowerList) {
            currentReloadSpeeds.add(tower.getReloadSpeed());
        }
        try {
            String message = randomEventTest.activateRandomEvent(2);
            ArrayList<Integer> newReloadSpeeds = new ArrayList<Integer>();
            for (Tower tower : mainTowerList) {
                newReloadSpeeds.add(tower.getReloadSpeed());
            }
            for (Tower tower : reserveTowerList) {
                newReloadSpeeds.add(tower.getReloadSpeed());
            }
            for (int i = 0; i < currentReloadSpeeds.size(); i++) {
                assertEquals((int) (currentReloadSpeeds.get(i) / 2),
                             newReloadSpeeds.get(i));
            }
        } catch (BelowMinimumTowerException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Testing Random event where round numbers are increased.
     */
    @Test
    public void testInitializeCase3() {
        currentRound = totalRounds - 1;
        randomEventTest = new RandomEvent(mainTowerList,
                                  reserveTowerList, currentRound, totalRounds);

        try {
            String message
                    = randomEventTest.activateRandomEvent(RANDOM_EVENT_THREE);
            assertEquals(currentRound - 1,
                         randomEventTest.getCurrentRoundNumber());
        } catch (BelowMinimumTowerException e) {
            System.out.println(e.getMessage());
        }
        currentRound = 1;
        randomEventTest = new RandomEvent(mainTowerList,
                                  reserveTowerList, currentRound, totalRounds);

        try {
            String message
                    = randomEventTest.activateRandomEvent(RANDOM_EVENT_THREE);
            assertEquals(2,
                         randomEventTest.getCurrentRoundNumber());
        } catch (BelowMinimumTowerException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Tests the Random Event where a tower is upgraded.
     */
    @Test
    public void testInitializeCase4() {
    // check that one tower has changed stats
        ArrayList<Integer> currentReloadSpeeds = new ArrayList<>();
        for (Tower tower : mainTowerList) {
            currentReloadSpeeds.add(tower.getReloadSpeed());
        }
        for (Tower tower : reserveTowerList) {
            currentReloadSpeeds.add(tower.getReloadSpeed());
        }
        try {
            randomEventTest.activateRandomEvent(RANDOM_EVENT_FOUR);
            ArrayList<Integer> newReloadSpeeds = new ArrayList<Integer>();
            for (Tower tower : randomEventTest.getMainTowerList()) {
                newReloadSpeeds.add(tower.getReloadSpeed());
            }
            for (Tower tower : randomEventTest.getReserveTowerList()) {
                newReloadSpeeds.add(tower.getReloadSpeed());
            }
            int differences = 0;
            for (int i = 0; i < newReloadSpeeds.size(); i++) {
                if (currentReloadSpeeds.get(i) < newReloadSpeeds.get(i)) {
                    differences += 1;
                }
            }
            //there will be one tower that has increased reload speed;
            assertEquals(1, differences);
        } catch (BelowMinimumTowerException e) {
                System.out.println(e.getMessage());
            }
        }

    /**
     * Tests the Random Event where a tower is levelled up.
     */

    @Test
    public void testInitializeCase5() {
// check that one tower has changed stats
        ArrayList<Integer> currentReloadSpeeds = new ArrayList<>();
        for (Tower tower : mainTowerList) {
            currentReloadSpeeds.add(tower.getReloadSpeed());
        }
        for (Tower tower : reserveTowerList) {
            currentReloadSpeeds.add(tower.getReloadSpeed());
        }
        try {
            randomEventTest.activateRandomEvent(RANDOM_EVENT_FIVE);
            List<Integer> newReloadSpeeds = new ArrayList<Integer>();
            for (Tower tower : randomEventTest.getMainTowerList()) {
                newReloadSpeeds.add(tower.getReloadSpeed());
            }
            for (Tower tower : randomEventTest.getReserveTowerList()) {
                newReloadSpeeds.add(tower.getReloadSpeed());
            }
            int differences = 0;
            for (int i = 0; i < newReloadSpeeds.size(); i++) {
                if (currentReloadSpeeds.get(i) < newReloadSpeeds.get(i)) {
                    differences += 1;
                }
            }
            //there will be one tower that has increased reload speed;
            assertEquals(1, differences);
        } catch (BelowMinimumTowerException e) {
            System.out.println(e.getMessage());
        }
    }


    /**
     * Tests the Random Event where all tower's loading speeds are increased.
     */
    @Test
    public void testInitializeCase6() {
        ArrayList<Integer> currentReloadSpeeds = new ArrayList<>();
        for (Tower tower : mainTowerList) {
            currentReloadSpeeds.add(tower.getReloadSpeed());
        }
        for (Tower tower : reserveTowerList) {
            currentReloadSpeeds.add(tower.getReloadSpeed());
        }
        try {
            randomEventTest.activateRandomEvent(RANDOM_EVENT_SIX);
            ArrayList<Integer> newReloadSpeeds = new ArrayList<Integer>();
            for (Tower tower : mainTowerList) {
                newReloadSpeeds.add(tower.getReloadSpeed());
            }
            for (Tower tower : reserveTowerList) {
                newReloadSpeeds.add(tower.getReloadSpeed());
            }
            final double multiplier = 1.5;
            for (int i = 0; i < currentReloadSpeeds.size(); i++) {
                assertEquals((int)  (currentReloadSpeeds.get(i) * multiplier),
                             newReloadSpeeds.get(i));
            }
        } catch (BelowMinimumTowerException e) {
            System.out.println(e.getMessage());
        }
    }

    /**`
     * Tests the Random Event where the current round number is decreased.
     */
    @Test
    public void testInitializeCase7() {
        randomEventTest
            = new RandomEvent(mainTowerList, reserveTowerList, 2,
                                          totalRounds);
        System.out.println(this.currentRound);
        try {
            randomEventTest.activateRandomEvent(RANDOM_EVENT_SEVEN);
            assertEquals(3, randomEventTest.getCurrentRoundNumber());
        } catch (BelowMinimumTowerException e) {
            System.out.println(e.getMessage());
        }
        randomEventTest = new RandomEvent(mainTowerList, reserveTowerList,
                                  4, totalRounds);
        try {
            randomEventTest.activateRandomEvent(RANDOM_EVENT_SEVEN);
            assertEquals(3, randomEventTest.getCurrentRoundNumber());
        } catch (BelowMinimumTowerException e) {
            System.out.println(e.getMessage());
        }
    }
    @Test
    public void testInitializeCase8() {
        this.mainTowerList.clear();
        this.mainTowerList.add(new Tower(2));
        this.reserveTowerList.clear();
        randomEventTest
                = new RandomEvent(mainTowerList, reserveTowerList, 2,
                totalRounds);
        assertThrows(BelowMinimumTowerException.class,
                () ->{randomEventTest.activateRandomEvent(RANDOM_EVENT_ONE);} );

    }
}

