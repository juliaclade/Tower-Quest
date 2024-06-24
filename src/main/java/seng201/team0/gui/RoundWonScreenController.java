package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import seng201.team0.audio.Audio;
import seng201.team0.models.*;
import seng201.team0.services.GameEnvironment;
import seng201.team0.services.GameManager;
import seng201.team0.models.exceptions.BelowMinimumTowerException;
import seng201.team0.services.RoundManager;


/**
 * Screen for the round won screen in which the player
 * can see how much money they have won.
 * @author Julia Clade and Katherine Irving Seguel
 * @version 1.0, 11 Apr 2024
 */
@SuppressWarnings("checkstyle:JavadocType")
public class RoundWonScreenController {

    /**
     * Stores the state of the game and launches the screens.
     */
    private final GameEnvironment gameEnvironment;

    /**
     * Provides access to player name, money, game
     * difficulty, current shop, round and inventory.
     */
    private final GameManager gameManager;

    /**
     * Displays the current/total rounds in the game.
     */
    @FXML
    private Label curRoundTotalRoundsLabel;

    /**
     * Displays the player's money.
     */
    @FXML
    private Label playerMoneyLabel;

    /**
     * Displays the round number.
     */
    @FXML
    private Label roundNumberLabel;

    /**
     * Displays the money earned in the round.
     */
    @FXML
    private Label earningsLabel;

    /**
     * Displays how many carts were filled.
     */
    @FXML
    private Label messageCartsFilledLabel;

    /**
     * Stores whether a random event is happening.
     */
    private boolean isRandomEventHappening = false;

    /**
     * Stores the random event 3 which increases round number.
     */
    public static final int RANDOM_EVENT_THREE = 3;

    /**
     * Stores the random Event 7 which decreases round number.
     */
    public static final int RANDOM_EVENT_SEVEN = 7;

    /**
     * Stores the RoundManager.
     */
    private final RoundManager currentRoundManager;

    /**
     * Stores the sound path of the random event sound.
     */
    private static final String RANDOM_EVENT_SOUND_PATH = "/sound/random.wav";

    /**
     *  Constructs a round won screen by initializing the current
     * state from the gameEnvironment variable and initializes a random event
     * if this happens.
     * @param gameEnvironment Stores the state of the game.
     */
    @SuppressWarnings("checkstyle:HiddenField")
    public RoundWonScreenController(final GameEnvironment gameEnvironment) {
        this.gameEnvironment = gameEnvironment;
        this.gameManager = gameEnvironment.getGameManager();
        this.currentRoundManager = gameManager.getRoundManager();
        if (gameManager.isRandomEvent()) {
            isRandomEventHappening = true;
            gameManager.createNewRandomEvent();
        }
    }

    /**
     * Sets the gui's labels.
     */
    public void initialize() {
        setRoundLabel();
        setMoneyLabel();
        Round selectedRound = gameManager.getSelectedRound();

        if (selectedRound.getNumberOfCarts() > 1) {
            messageCartsFilledLabel
                .setText(selectedRound.getNumberOfCarts()
                                 + " carts were filled successfully.");
        } else {
            messageCartsFilledLabel
                .setText(selectedRound.getNumberOfCarts()
                                 + " cart was filled successfully.");
        }

        roundNumberLabel.setText("Congratulations! \n You have won round "
                                 + (gameManager.getCurrentRound() - 1));
        earningsLabel.setText("You have earned      "
                          + currentRoundManager.calculateEarnings());
    }

    /**
     * Sets the round label.
     */
    private void setRoundLabel() {
        String curRound = Integer.toString(gameManager.getCurrentRound() - 1);
        String totalRound = Integer.toString(gameManager.getNumberOfRounds());
        curRoundTotalRoundsLabel.setText(curRound + "/" + totalRound);
    }

    /**
     * Sets the money label.
     */
    private void setMoneyLabel() {
        String playersMoney = Integer.toString(gameManager.getPlayersMoney());
        playerMoneyLabel.setText(playersMoney);
    }

    /**
     * Saves state of game, resets random event and initializes new
     * Random Event. Afterward screen is closed and new screen launched.
     * @param randomEvent An object of type Random Event.
     * @param message The message returned from initializing the random event.
     */
    public void randomEventActions(final RandomEvent randomEvent,
                                   final String message) {
        gameManager.setRandomEventMessage(message);
        Inventory inventory = gameManager.getInventory();
        inventory.setMainTowers(randomEvent.getMainTowerList());
        inventory.setReserveTowers(randomEvent.getReserveTowerList());
        gameManager.setInventory(inventory);
        if (randomEvent.getRandomNumber() == RANDOM_EVENT_THREE
                || randomEvent.getRandomNumber() == RANDOM_EVENT_SEVEN) {
            gameManager.setCurrentRoundNumber(
                    randomEvent.getCurrentRoundNumber());
        }
        gameManager.setRandomEvent(null);
        if (gameManager.isRandomEvent()) {
            gameManager.createNewRandomEvent();
        }
        gameManager
                .setPlayersMoney(gameManager
                        .getPlayersMoney() + gameManager
                        .getRoundManager().calculateEarnings());
        Audio audio = new Audio();
        audio.playSound(RANDOM_EVENT_SOUND_PATH);
        gameEnvironment.setGameManager(gameManager);
        gameEnvironment.closeRoundWonScreen();
        gameEnvironment.launchRandomScreen();
    }

    /**
     * This function is triggered when the "continue" button label is clicked.
     * Checks whether the random event results in loss of all
     * towers and if the player can't afford to buy anymore, then game is
     * lost. Otherwise, the screen launches to the RoundSetupScreen.
     */
    @SuppressWarnings("checkstyle:HiddenField")
    @FXML
    public void continueButtonClicked() {
        String message = null;
        if (isRandomEventHappening) {
            RandomEvent randomEvent = gameManager.getRandomEvent();
            try {
                int randomNumber = randomEvent.getRandomNumber();
                message = randomEvent
                        .activateRandomEvent(randomNumber);

                randomEventActions(randomEvent, message);

            } catch (BelowMinimumTowerException e) {
                Shop currentShop = gameManager.getCurrentShop();
                int money = gameManager.getPlayersMoney()
                        + currentRoundManager
                        .calculateEarnings();
                for (Tower tower : currentShop.getTowersForSale()) {
                    boolean roundWon = tower.getBuyingCost() <= money;
                    gameManager.setGameWon(roundWon);
                    randomEventActions(randomEvent, message);
                }
            }
        } else {
            gameManager.setPlayersMoney(
            gameManager.getPlayersMoney()
                    + currentRoundManager.calculateEarnings());
            gameEnvironment.setGameManager(gameManager);
            gameEnvironment.closeRoundWonScreen();
            gameEnvironment.launchSetupRoundScreen();
        }
    }
}
