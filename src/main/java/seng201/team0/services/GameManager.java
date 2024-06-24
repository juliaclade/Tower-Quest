package seng201.team0.services;

import seng201.team0.models.*;
import seng201.team0.models.exceptions.BelowMinimumTowerException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Keeps track of all game variables throughout the game.
 * @author Julia Clade and Katherine Irving Seguel
 */
public class GameManager {

    /**
     * Stores the starting money if "easy" is chosen.
     */
    static final int STARTING_MONEY_EASY = 100;

    /**
     * Stores the starting money if "hard" is chosen.
     */
    static final int STARTING_MONEY_HARD = 50;

    /**
     * Stores the max length of player name.
     */
    static final int MIN_LENGTH_PLAYER_NAME = 3;

    /**
     * Stores the max length of player name.
     */
    static final int MAX_LENGTH_PLAYER_NAME = 15;

    /**
     * Stores the max length of a tower list.
     */
    static final int MAX_LENGTH_TOWER_LIST = 5;

    /**
     * Stores a number to ensure those towers are assigned exact resource
     * values at starting tower initialization.
     */
    static final int FIRST_THREE_TOWERS = 3;

    /**
     * Stores how many round options are generated.
     */
    static final int NUM_ROUND_OPTIONS = 3;

    /**
     * Stores the upper bound for calculating the likelihood of a random
     * event happening.
     */
    static final int RANDOM_EVENT_UPPER_BOUND = 3;

    /**
     * Stores the player's name.
     */
    private String playerName;

    /**
     * Stores the current random event.
     */
    private RandomEvent randomEvent;

    /**
     * Stores if the game is won. It is true until a round is lost
     * and then set to false.
     */
    private boolean gameWon = true;


    /**
     * Stores random event message to later be displayed in the label.
     */
    private String randomEventMessage;

    /**
     * Stores the resource type values that are used to build
     * towers, carts and upgrades.
     */
    private static final String[] RESOURCE_TYPES = {"Wood", "Gold", "Coal"};

    /**
     * A list of starting towers to chose from before the first round.
     */
    private final List<Tower> startingTowers = new ArrayList<>();

    /**
     * Stores the selected round difficulty.
     */
    private Round selectedRound;

    /** Keeps track of the money earned. */
    private int playersMoney;

    /**
     * Stores total number of rounds chosen for the game.
     */
    private int numberOfRounds;

    /**
     * Stores the game difficulty that is chosen as a number.
     * 0 means Easy and 1 means hard. This affects the starting money.
     */
    private int gameDifficulty;

    /**
     * Keeps track of the current round. Incremented after every round and
     * used to create round, tower and upgrade objects.
     */
    private int currentRound = 1;

    /**
     * A list of round options for the player to chose for the upcoming
     * round.
     */
    private List<Round> roundOptions;

    /**
     * Keeps track of main towers.
     */
    private List<Tower> mainTowersList = new ArrayList<>();

    /**
     * Keeps track of reserve towers.
     */
    private List<Tower> reserveTowersList = new ArrayList<>();

    /**
     * Keeps track of the current shop and items for sale when moving between
     * SetupRoundScreen, ShopScreen and InventoryScreen.
     */
    private Shop currentShop;

    /**
     * Stores information about the current round played and the
     * game mechanics.
     */
    private RoundManager roundManager;

    /**
     * Keeps track the player's upgrades.
     */
    private List<Upgrade> upgradeList = new ArrayList<>();

    /**
     * Stores the player's tower and upgrade lists.
     */
    private Inventory inventory;

    /**
     * Initializes the list of starting towers and a new Inventory.
     */
    public GameManager() {
        for (int i = 0; i < MAX_LENGTH_TOWER_LIST; i++) {
            Tower towerToAdd = new Tower(currentRound);
            if (i < FIRST_THREE_TOWERS) {
                towerToAdd.setResourceType(RESOURCE_TYPES[i]);
            }
            startingTowers.add(towerToAdd);
        }
        inventory = new Inventory(mainTowersList, reserveTowersList,
                upgradeList);
    }

    /**
     * Validates if size of the selected towers list is correct
     * and returns a boolean.
     * @return boolean True if the size of the selected towers list is correct.
     * @throws BelowMinimumTowerException If there aren't enough towers
     * @param selectedTowers list of selected towers in the game setup screen.
     * @param maxSelectedTowers Number stored for necessary length of
     *                          selected towers list.
     */
    public boolean validateSelectedTowerListSize(final List selectedTowers,
             final int maxSelectedTowers) throws BelowMinimumTowerException {
        if (selectedTowers.size() == maxSelectedTowers) {
            return true;
        } else {
            throw new BelowMinimumTowerException();
        }
    }

    /**
     * Returns the inventory.
     * @return inventory stores the player's towers and upgrades.
     */
    public Inventory getInventory() {
        return inventory;
    }

    /**
     * Sets the inventory.
     * @param inventory stores the player's towers and upgrades.
     */
    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    /**
     * Creates a roundManager, plays a round and returns true if won and
     * false if lost.
     * @return boolean. True if the round is won and false if the round is lost.
     */
    public boolean isRoundWon() {
        roundManager = new RoundManager(inventory.getMainTowers(),
                selectedRound, currentRound);
        return roundManager.playRound();
    }

    /**
     * Returns the list of starting towers.
     * @return startingTowers Player can choose from these at the start of the
     * game.
     */
    public List<Tower> getStartingTowers() {
        return this.startingTowers;
    }

    /**
     * Sets the selectedRound to the round chosen by player.
     * @param round is an object of Round class.
     */
    public void setSelectedRound(final Round round) {
        selectedRound = round;
    }

    /**
     * Generates 3 different difficulty choices of rounds
     * for the upcoming round.
     */
    public void generateRoundOptions() {
        roundOptions = new ArrayList<>();
        for (int i = 0; i < NUM_ROUND_OPTIONS; i++) {
            roundOptions.add(new Round(currentRound + (i * 2)));
        }
    }

    /**
     * Returns the mainTowersList.
     * @return mainTowersList The player's main towers.
     */
    public List<Tower> getMainTowerList() {
        return mainTowersList;
    }

    /**
     * Returns the reserveTowersList.
     * @return reserveTowersList The player's reserve towers.
     */
    public List<Tower> getReserveTowersList() {
        return reserveTowersList;
    }

    /**
     * Returns the player's upgrade list.
     * @return upgradeList The player's upgrades.
     */
    public List<Upgrade> getUpgradeList() {
        return upgradeList;
    }

    /**
     * Sets the mainTowersList.
     * @param mainTowersList Stores the player's main towers.
     */
    @SuppressWarnings("checkstyle:HiddenField")
    public void setMainTowerList(final List<Tower> mainTowersList) {
        this.mainTowersList = mainTowersList;
    }

    /**
     * Sets the reserveTowersList.
     * @param reserveTowersList Stores the player's reserve towers.
     */
    @SuppressWarnings("checkstyle:HiddenField")
    public void setReserveTowersList(final List<Tower> reserveTowersList) {
        this.reserveTowersList = reserveTowersList;
    }

    /**
     * Sets the upgradeList.
     * @param upgradeList stores the player's upgrades.
     */
    @SuppressWarnings("checkstyle:HiddenField")
    public void setUpgradeList(final List<Upgrade> upgradeList) {
        this.upgradeList = upgradeList;
    }

    /**
     * Sets the number of rounds for the game.
     * @param numberOfRound Keeps track of the current round number.
     */
    @SuppressWarnings("checkstyle:HiddenField")
    public void setNumberOfRounds(final int numberOfRound) {
        this.numberOfRounds = numberOfRound;
    }

    /**
     * Sets the random event message.
     * @param randomEventMessage Message for the random event to display in a
     *                           label.
     */
    @SuppressWarnings("checkstyle:HiddenField")
    public void setRandomEventMessage(final String randomEventMessage) {
        this.randomEventMessage = randomEventMessage;
    }

    /**
     * Returns the randomEventMessage.
     * @return randomEventMessage Message for the random event to display in a
     *                           label.
     */
    public String getRandomEventMessage() {
        return randomEventMessage;
    }

    /**
     * Stores if game is won.
     * @return boolean. True if won and false if lost.
     */
    public boolean isGameWon() {
        return gameWon;
    }

    /**
     * Sets if the game is won.
     * @param won True if won and false if lost.
     */
    public void setGameWon(final boolean won) {
        this.gameWon = won;
    }

    /**
     * Returns the number of rounds for the game.
     * @return Int numberOfRound Total number of rounds in the game.
     */
    public int getNumberOfRounds() {
        return numberOfRounds;
    }

    /**
     * Sets the game difficulty and calls setPlayersMoney() to set
     * the starting money accordingly.
     * @param gameDifficulty  If 0, game is set to "Easy", if 1, game is set
     *                      to "Hard".
     *
     */
    @SuppressWarnings("checkstyle:HiddenField")
    public void setGameDifficulty(final int gameDifficulty) {
        this.gameDifficulty = gameDifficulty;
        setPlayersStartingMoney();
    }

    /**
     * Increments current round number.
     */
    public void increaseCurrentRound() {
        currentRound += 1;
    }

    /**
     * Returns the current round number.
     * @return Int currentRound.
     */
    public int getCurrentRound() {
        return currentRound;
    }

    /**
     * Sets the starting money for a game depending on the game
     * difficulty chosen by the player.
     */
    public void setPlayersStartingMoney() {
        if (gameDifficulty == 0) {
            playersMoney = STARTING_MONEY_EASY;
        }
        if (gameDifficulty == 1) {
            playersMoney = STARTING_MONEY_HARD;
        }
    }

    /**
     * Returns the player's money.
     * @return playersMoney Player's current balance.
     */
    public int getPlayersMoney() {
        return playersMoney;
    }

    /**
     * Assigns a new value to playersMoney variable.
     * @param newMoney  Holds the new money value.
     */
    public void setPlayersMoney(final int newMoney) {
        playersMoney = newMoney;
    }

    /**
     * Returns list of roundOptions.
     * @return roundOptions Stores the different round options the
     * player can choose from.
     */
    public List<Round> getRoundOptions() {
        return roundOptions;
    }

    /**
     * Returns the selectedRound.
     * @return Round selectedRound Choice of round difficulty.
     */
    public Round getSelectedRound() {
        return selectedRound;
    }

    /**
     * Sets the roundNumber.
     * @param roundNumber Current round number.
     */
    public void setCurrentRoundNumber(final int roundNumber) {
        currentRound = roundNumber;
    }

    /**
     * Returns the player's name.
     * @return playersName Player's name.
     */
    public String getPlayersName() {
        return playerName;
    }

    /**
     *  Sets the player's name.
     * @param playerName Player's name.
     */
    @SuppressWarnings("checkstyle:HiddenField")
    public void setPlayersName(final String playerName) {
        this.playerName = playerName;
    }

    /**
     * Generates a random number in range one to Random_Event_Upper_Bound and
     * returns true if the number equals one.
     * @return boolean True if a random event is going to happen, False if not.
     */
    @SuppressWarnings("checkstyle:HiddenField")
    public boolean isRandomEvent() {
        Random random = new Random();
        int randomNumber = random.nextInt(1, RANDOM_EVENT_UPPER_BOUND);
        return randomNumber == 1;
    }

    /**
     * Creates a new RandomEvent object.
     */
    public void createNewRandomEvent() {
        this.randomEvent = new RandomEvent(inventory.getMainTowers(),
                inventory.getReserveTowers(), currentRound, numberOfRounds);
    }

    /**
     * Returns the roundManager.
     * @return roundManager keeps track of current round stats.
     */
    public RoundManager getRoundManager() {
        return roundManager;
    }

    /**
     * Returns the randomEvent.
     * @return randomEvent Stores the current random event.
     */
    public RandomEvent getRandomEvent() {
        return randomEvent;
    }

    /**
     * Sets the randomEvent.
     * @param randomEvent randomEvent stores this round's RandomEvent.
     */
    @SuppressWarnings("checkstyle:HiddenField")
    public void setRandomEvent(final RandomEvent randomEvent) {
        this.randomEvent = randomEvent;
    }

    /** Validates playerName. A name is valid if it is
     * between 3 and 15 characters long and
     *  doesn't contain any special symbols.
     * @param playerName The name of the player as a String.
     * @return If name is valid, returns true; false if invalid.
     */
    @SuppressWarnings("checkstyle:HiddenField")
    public boolean validatePlayerName(final String playerName)
            throws IllegalArgumentException {
        if (playerName.length() > MAX_LENGTH_PLAYER_NAME
                || playerName.length() < MIN_LENGTH_PLAYER_NAME
                || !playerName.matches("[a-zA-Z0-9]+")) {
            throw new IllegalArgumentException("Invalid name. Names must be "
                    + "between 3-15 characters and no special characters.");
        }
        return true;
    }

    /**
     * Initializes a new shop and assigns it to currentShop.
     */
    public void createShop() {
        this.currentShop =  new Shop(currentRound, inventory.getMainTowers(),
                inventory.getReserveTowers(), inventory.getUpgrades());
    }

    /**
     * Returns the currentShop.
     * @return currentShop stores the items for sale.
     */
    public Shop getCurrentShop() {
        return currentShop;
    }

    /**Calculates points at the end of game by adding all money
     * and sales price of all items owned.
     * @return int sum total of points.
     */
    public int calculatePoints() {
        int sum = this.getPlayersMoney()
                + this.getRoundManager().calculateEarnings();
        for (Tower tower : this.getMainTowerList()) {
            sum += tower.getSellingCost();
        }
        for (Tower tower : this.getReserveTowersList()) {
            sum += tower.getSellingCost();
        }
        for (Upgrade upgrade : this.getUpgradeList()) {
            sum += upgrade.getSellingCost();
        }
        return sum;
    }
}
