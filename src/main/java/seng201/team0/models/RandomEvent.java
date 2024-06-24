package seng201.team0.models;

import seng201.team0.models.exceptions.BelowMinimumTowerException;
import seng201.team0.models.exceptions.ResourcesDontMatchException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This class creates a random event in the game.
 * @author Julia Clade and Katherine Irving Seguel
 * @version 1.0, 16 Apr 2024
 */
@SuppressWarnings("checkstyle:JavadocType")
public class RandomEvent {

    /**
     * Stores all the player's towers.
     */
    private final List<Tower> allTowers = new ArrayList<>();

    /**
     * Stores the player's reserve towers.
     */
    private final List<Tower> reserveTowerList;

    /**
     * Stores the player's main towers.
     */
    private final List<Tower> mainTowerList;

    /**
     * Stores the game's current round number.
     */
    private int currentRound;

    /**
     * Stores the number to generate the random event.
     */
    private int randomNumber;

    /**
     * Stores the game's total rounds.
     */
    private final int totalRounds;

    /**
     * Lower bound for random event generation.
     */
    private static final int RANDOM_LOWER_BOUND = 1;

    /**
     * Upper bound for random event generation.
     */
    private static final int RANDOM_UPPER_BOUND = 8;

    /**
     * Value that is used to increase tower stats for the random event
     * IncreaseLoadSpeedAllTowers.
     */
    private static final double INCREASE_LOAD_SPEED = 1.5;

    /**
     * Value that is used to initialize an upgrade for the random event
     * UpgradeTower.
     */
    private static final int UPGRADE_TOWER_VALUE = 3;

    /**
     * Random Event number one.
     */
    private static final int RANDOM_EVENT_ONE = 1;

    /**
     * Random Event number two.
     */
    private static final int RANDOM_EVENT_TWO = 2;

    /**
     * Random Event number three.
     */
    private static final int RANDOM_EVENT_THREE = 3;

    /**
     * Random Event number four.
     */
    private static final int RANDOM_EVENT_FOUR = 4;

    /**
     * Random Event number five.
     */
    private static final int RANDOM_EVENT_FIVE = 5;

    /**
     * Random Event number six.
     */
    private static final int RANDOM_EVENT_SIX = 6;

    /**
     * Random Event number seven.
     */
    private static final int RANDOM_EVENT_SEVEN = 7;



    /**
     * This constructor initializes the attributes of class and generates the
     * random event number.
     * @param mainTowerList    Stores the main towers.
     * @param reserveTowerList Stores the reserve towers.
     * @param currentRound     Stores the current round number.
     * @param totalRounds      Stores the number of total rounds of the game;
     */
    @SuppressWarnings("checkstyle:HiddenField")
    public RandomEvent(final List<Tower> mainTowerList,
                       final List<Tower> reserveTowerList,
                       final int currentRound,
                       final int totalRounds) {
        this.reserveTowerList = reserveTowerList;
        this.mainTowerList = mainTowerList;
        this.allTowers.addAll(mainTowerList);
        this.allTowers.addAll(reserveTowerList);
        this.currentRound = currentRound;
        generateRandomNumber();
        this.totalRounds = totalRounds;
    }

    /**
     * Returns the main tower list.
     * @return List player's list of main towers.
     */
    public List<Tower> getMainTowerList() {
        return mainTowerList;
    }

    /**
     * Returns the reserve tower list.
     * @return List Player's list of reserve towers.
     */
    public List<Tower> getReserveTowerList() {
        return reserveTowerList;
    }

    /**
     * Returns the currentRound number.
     * @return int currentRound
     */
    public int getCurrentRoundNumber() {
        return currentRound;
    }

    /**
     * Generates a random int which is used to pick a random event.
     */
    public void generateRandomNumber() {
        Random random = new Random();
        randomNumber =
                random.nextInt(RANDOM_LOWER_BOUND, RANDOM_UPPER_BOUND);
    }

    /**
     * Returns the random number associated with the event.
     * @return int randomNumber
     */
    public int getRandomNumber() {
        return randomNumber;
    }

    /**
     * Assigns the randomNumber to an event and calls the event method.
     * @return String of Event
     * @throws BelowMinimumTowerException Exception is thrown when main
     * towers has no towers in it.
     * @param randomNumber This is a random number generated in range 1-7.
     */
    @SuppressWarnings({"checkstyle:HiddenField", "checkstyle:MagicNumber"})
    public String activateRandomEvent(final int randomNumber)
            throws BelowMinimumTowerException {
        return switch (randomNumber) {
            case RANDOM_EVENT_ONE -> stealTower();
            case RANDOM_EVENT_TWO -> reduceLoadSpeedAllTowers();
            case RANDOM_EVENT_THREE -> increaseCurrentRoundNumber();
            case RANDOM_EVENT_FOUR -> upgradeTower();
            case RANDOM_EVENT_FIVE -> levelUpTower();
            case RANDOM_EVENT_SIX -> increaseLoadSpeedAllTowers();
            case RANDOM_EVENT_SEVEN -> decreaseCurrentRoundNumber();
            default -> "Event out of bound.";
        };
    }

    /**
     * This function returns a random tower from the player's inventory.
     * @return towerSelected Randomly chosen tower.
     */
    public Tower pickRandomTower() {
        Random random = new Random();
        int currentAllTowersSize = allTowers.size();
        int randomIndexToRemove = random.nextInt(0, currentAllTowersSize);
        return allTowers.get(randomIndexToRemove);
    }

    /**
     * Steals one of the player's randomly selected towers. It checks if the
     * lists contain any towers
     * @throws BelowMinimumTowerException Exception is thrown when main
     * towers has no towers in it.
     * @return String Message of event to display on a label.
     */
    public String stealTower() throws BelowMinimumTowerException {
        Tower towerToRemove = pickRandomTower();
        if (mainTowerList.contains(towerToRemove) && mainTowerList.size() > 1) {
            mainTowerList.remove(towerToRemove);
        } else if (mainTowerList.contains(towerToRemove)
                && mainTowerList.size() == 1 && !reserveTowerList.isEmpty()) {
            mainTowerList.remove(towerToRemove);
            mainTowerList.add(reserveTowerList.remove(0));
        } else if (mainTowerList.contains(towerToRemove)
                && mainTowerList.size() == 1 && reserveTowerList.isEmpty()) {
            mainTowerList.remove(towerToRemove);
            throw new BelowMinimumTowerException();
        } else {
            reserveTowerList.remove(towerToRemove);
        }
        return "Oh No! \nOne of your towers has been stolen";
    }

    /**
     * A randomly selected tower gets upgraded.
     * @return String Message of event to display on a label.
     */
    public String upgradeTower() {
        Upgrade newUpgrade = new Upgrade(currentRound
                                                 + UPGRADE_TOWER_VALUE);
        Tower towerToUpgrade = pickRandomTower();
        newUpgrade.setResourceType(towerToUpgrade.getResourceType());
        try {
            towerToUpgrade.applyUpgrade(newUpgrade);
        } catch (ResourcesDontMatchException e) {
            System.out.println(e.getMessage());
        }
        return "Lucky you! \nOne of your towers has been upgraded";
    }

    /**
     * A randomly selected tower gets levelled up.
     * @return String Message of event to display on a label.
     */
    public String levelUpTower() {
        Tower towerToLevelUp = pickRandomTower();
        towerToLevelUp.levelUpXP();
        return "Lucky you! \nOne of your tower's XP has levelled up";
    }

    /**
     * Reduces reload speed of all the player's towers.
     * @return String Message of event to display on a label.
     */
    public String reduceLoadSpeedAllTowers() {
        for (Tower tower : allTowers) {
            int towerCurrentReloadSpeed = tower.getReloadSpeed();
            tower.setReloadSpeed(towerCurrentReloadSpeed / 2);
        }
        return "Oh No! \nAll of your towers have reduced loading speed";
    }

    /**
     * Increases the reload speed of all the player's towers.
     * @return String Message of event to display on a label.
     */
    public String increaseLoadSpeedAllTowers() {
        for (Tower tower : allTowers) {
            int towerCurrentReloadSpeed = tower.getReloadSpeed();
            tower.setReloadSpeed((int) (towerCurrentReloadSpeed
                                                * INCREASE_LOAD_SPEED));
        }
        return "Lucky you! \nAll of your towers have increased loading speed";
    }

    /**
     * Increases current round number unless game is in second to last round.
     * In that case decreaseCurrentRoundNumber() is called.
     * @return String Message of event to display on a label.
     */
    public String increaseCurrentRoundNumber() {
        if (currentRound == totalRounds - 1) {
            currentRound -= 1;
            randomNumber = RANDOM_EVENT_SEVEN;
            return "Lucky You! You went back one round!";
        } else {
            currentRound += 1;
            return "Oh no! You just skipped one round";
        }
    }

    /**
     * Decreases the current round number unless it's the first round. In
     * that case it calls increaseCurrentRoundNumber() instead.
     * @return String Message of event to display on a label.
     */
    public String decreaseCurrentRoundNumber() {
        if (currentRound == 2) {
            currentRound += 1;
            randomNumber = RANDOM_EVENT_THREE;
            return "Oh No! You just skipped a round";
        } else {
            currentRound -= 1;
            return "Lucky You!\nYou just went back one round";
        }
    }
}
