package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import seng201.team0.services.GameEnvironment;
import seng201.team0.services.GameManager;

/**
 * Screen for EndGameScreenContoller .Is the final screen of the game.
 * Displays whether the player has
 * won, their name, how many rounds were completed successfully and how many
 * rounds they chose in total. It displays their money earned and total points.
 * @author Julia Clade and Katherine Irving Seguel
 * @version 1.0, 11 Apr 2024
 */
@SuppressWarnings("checkstyle:JavadocType")
public class EndGameScreenController {

    /**
     * Stores Label that displays "game Over" message.
     */
    @FXML
    private Label gameOverLabel;

    /**
     * Stores Label that displays if the game is won or lost.
     */
    @FXML
    private Label wonLostLabel;

    /**
     * Stores Label that displays how many rounds were completed.
     */
    @FXML
    private Label completedRoundsLabel;

    /**
     * Stores Label that displays how much the player has earned.
     */
    @FXML
    private Label moneyEarnedLabel;

    /**
     * Stores Label that displays how many points were earned.
     */
    @FXML
    private Label pointsEarnedLabel;

    /**
     * Stores the player's name.
     */
    private final String playerName;

    /**
     * Stores the state of the game and launches screens.
     */
    private final GameEnvironment gameEnvironment;

    /**
     * Stores player name, money, game difficulty,
     * current shop, round and inventory.
     */
    private final GameManager gameManager;

    /**
     * Constructor for EndGameScreenController sets class
     * attributes.
     * @param gameEnvironment Stores the state of the game.
     */
    @SuppressWarnings("checkstyle:HiddenField")
    public EndGameScreenController(final GameEnvironment gameEnvironment) {
        this.gameEnvironment = gameEnvironment;
        this.playerName = gameEnvironment.getGameManager().getPlayersName();
        this.gameManager = gameEnvironment.getGameManager();

    }

    /**
     * Sets the style and labels of the screen.
     */
    public void initialize() {
        int currentRound = gameManager.getCurrentRound();
        int totalRounds = gameManager.getNumberOfRounds();
        int playerMoney = gameManager.getPlayersMoney();
        int points = gameManager.calculatePoints();

        if (gameEnvironment.getGameManager().isGameWon()) {
            gameOverLabel.setText("Congratulations " + playerName);
            wonLostLabel.setText("You have won!");
            completedRoundsLabel.setText("You have successfully completed "
                            + "all " + totalRounds + " rounds.");

        } else {
            gameOverLabel.setText("Game Over\n" + playerName);
            wonLostLabel.setStyle("-fx-text-fill: #b8e407;" + "-fx-effect: "
                + "dropshadow( gaussian , rgb(54,54,54) , 10 , 0 , 0 , 1 )");
            wonLostLabel.setText("You have lost!");
            completedRoundsLabel.setText("You have successfully completed "
                         + (currentRound - 1) + "/" + totalRounds + " rounds.");
        }
        moneyEarnedLabel.setText("You have earned       " + playerMoney);
        pointsEarnedLabel.setText(" You have earned " + points + " points.");
    }

    /**
     * Exits out of the game when the quit button is clicked.
     */
    @FXML
    public void quitClicked() {
        gameEnvironment.closeEndGameScreen();
    }
}
