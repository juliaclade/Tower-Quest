package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import seng201.team0.services.GameEnvironment;
import seng201.team0.services.GameManager;

/**
 * Screen for random event. This screen
 * only appears when a random event has been triggered.
 * @author Julia Clade and Katherine Irving Seguel
 * @version 1.0, 11 Apr 2024
 */
@SuppressWarnings("checkstyle:JavadocType")
public class RandomEventScreenController {

    /**
     * Launches screens and keeps track of state of game.
     */
    private final GameEnvironment gameEnvironment;

    /**
     * Provides access to player name, money, game
     * difficulty, current shop, round and inventory.
     */
    private final GameManager gameManager;

    /**
     * Displays the information of the random event.
     */
    @FXML
    private Label randomEventLabel;

    /**
     * Displays the current/total round status of the game.
     */
    @FXML
    private Label curRoundTotalRoundsLabel;

    /**
     * Displays the player's money.
     */
    @FXML
    private Label playerMoneyLabel;

    /**
     * Constructs a RandomEventScreenController values are
     * assigned  to gameEnvironment and gameManager.
     * @param gameEnvironment Keeps track of state of game.
     */
    @SuppressWarnings("checkstyle:HiddenField")
    public RandomEventScreenController(final GameEnvironment gameEnvironment) {
        this.gameEnvironment = gameEnvironment;
        this.gameManager = gameEnvironment.getGameManager();
    }

    /**
     * Sets the gui's labels.
     */
    public void initialize() {
        setRoundLabel();
        setMoneyLabel();
        randomEventLabel.setText(gameManager.getRandomEventMessage());
    }

    /**
     * Sets the round label.
     */
    private void setRoundLabel() {
        String curRound = Integer.toString(gameManager.getCurrentRound());
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
 * Updates GameManager and GameEnvironment data and launches the
 * roundSetupScreen.
 */
 @FXML
 private void continueButtonClicked() {
        if (gameManager.isGameWon()) {
            gameManager.setPlayersMoney(gameManager.
                    getPlayersMoney());
            gameEnvironment.setGameManager(gameManager);
            gameEnvironment.closeRandomScreen();
            gameEnvironment.launchSetupRoundScreen();
        }  else {
            gameEnvironment.setGameManager(gameManager);
            gameEnvironment.closeRandomScreen();
            gameEnvironment.launchEndGameScreen();
        }
 }
}
