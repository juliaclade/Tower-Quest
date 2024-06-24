package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import seng201.team0.audio.Audio;
import seng201.team0.models.Round;
import seng201.team0.models.exceptions.BelowMinimumTowerException;
import seng201.team0.services.GameEnvironment;
import seng201.team0.services.GameManager;

import java.util.List;

/**
 * Screen that lets the user select round options, go to the
 * inventory or shop and start a new round.
 * @author Julia Clade and Katherine Irving Seguel
 * @version 1.0, 11 Apr 2024
 */
@SuppressWarnings("checkstyle:JavadocType")
public class SetupRoundScreenController {

    /**
     * Stores the state of the game and launches the screens.
     */
    private final GameEnvironment gameEnvironment;

    /**
     * Display the current/total round status of the game.
     */
    @FXML
    private Label curRoundTotalRounds;

    /**
     * Display the player's money.
     */
    @FXML
    private Label playerMoneyLabel;

    /**
     * Button for the easy round option.
     */
    @FXML
    private Button round1Button;

    /**
     * Button for the  medium difficulty round option.
     */
    @FXML
    private Button round2Button;

    /**
     * Button for the hard difficulty round option.
     */
    @FXML
    private Button round3Button;

    /**
     * Displays length of track for the easy round.
     */
    @FXML
    private Label round1LenTrackLabel;

    /**
     * Displays length of track for the medium round.
     */
    @FXML
    private Label round1NumCartsLabel;

    /**
     * Displays length of track for the hard round.
     */
    @FXML
    private Label round2LenTrackLabel;

    /**
     * Displays number of carts for the easy round.
     */
    @FXML
    private Label round2NumCartsLabel;

    /**
     * Displays number of carts for the medium round.
     */
    @FXML
    private Label round3LenTrackLabel;

    /**
     * Displays number of carts for the hard round.
     */
    @FXML
    private Label round3NumCartsLabel;

    /**
     * Displays error messages.
     */
    @FXML
    private Label errorMessageLabel;

    /**
     * Stores the current round number.
     */
    private final int currentRound;

    /**
     * Stores the total round numbers.
     */
    private final int totalRounds;

    /**
     * Stores the current player's money.
     */
    private final int playerMoney;

    /**
     * Stores the 3 round options.
     */
    private final List<Round> roundOptions;

    /**
     * Stores the index of the selected round difficulty.
     */
    private int selectedRoundDifficulty = -1;

    /**
     * Stores the GameManger, it provides access to player name, money, game
     * difficulty,
     * current shop, round and inventory.
     */
    private final GameManager gameManager;

    /**
     * Stores the style for option buttons when selected.
     */
    private static final String BUTTON_STYLE = "-fx-background-image:"
            + "url(images/dark_rock.png);"
            + "-fx-effect:dropshadow(three-pass-box,"
            + "rgba(255,255,255,255), 8, 0.5, 0, 0)";

    /**
     * Constructs a SetupRoundScreenController object and assigns
     * values to class variables.
     * @param gameEnvironment Keeps track of state of game.
     */
    @SuppressWarnings("checkstyle:HiddenField")
    public SetupRoundScreenController(final GameEnvironment gameEnvironment)  {
            this.gameEnvironment = gameEnvironment;
            this.gameManager = gameEnvironment.getGameManager();
            roundOptions = gameManager.getRoundOptions();
            currentRound = gameManager.getCurrentRound();
            totalRounds = gameManager.getNumberOfRounds();
            playerMoney = gameManager.getPlayersMoney();
    }

    /**
     * Sets the round label.
     */
    private void setRoundLabel() {
        String curRound = Integer.toString(currentRound);
        String totalRound = Integer.toString(totalRounds);
        curRoundTotalRounds.setText(curRound + "/" + totalRound);
    }

    /**
     * Sets the money label.
     */
    private void setMoneyLabel() {
        String playersMoney = Integer.toString(playerMoney);
        playerMoneyLabel.setText(playersMoney);
    }

    /**
     * Sets labels for each round option displayed on the screen.
     * @param round Round object
     * @param i Index in list.
     */
    private void updateStats(final Round round, final int i) {
        if (i == 1) {
            round1NumCartsLabel.setText("Number of carts: "
                                                + round.getNumberOfCarts());
            round1LenTrackLabel.setText("Track length: "
                                                + round.getLengthOfTrack()
                                                + "m");
        } else if (i == 2) {
            round2NumCartsLabel.setText("Number of carts: "
                                                + round.getNumberOfCarts());
            round2LenTrackLabel.setText("Track length: "
                                                + round.getLengthOfTrack()
                                                + "m");
        } else {
            round3NumCartsLabel.setText("Number of carts: "
                                                + round.getNumberOfCarts());
            round3LenTrackLabel.setText("Track length: "
                                                + round.getLengthOfTrack()
                                                + "m");
        }
    }

    /**
     * Sets the gui's button style when not clicked and when clicked and sets
     * the labels.
     */
    public void initialize() {
        setRoundLabel();
        setMoneyLabel();
        List<Button> optionButtons = List.of(round1Button, round2Button,
                                             round3Button);
        for (int i = 0; i < optionButtons.size(); i++) {
            int finalI = i;
            updateStats(roundOptions.get(finalI), finalI + 1);
            optionButtons.get(i).setOnAction(event -> {
                selectedRoundDifficulty = finalI;
                optionButtons.forEach(button -> {
                    if (button == optionButtons.get(finalI)) {
                        button.setStyle(BUTTON_STYLE);
                    } else {
                        button.setStyle(
                                "-fx-background-image: url(images/rocks.png)");
                    }
                });
            });
        }
    }

    /**
     * Calls the Audio class to play a sound.
     * The sound played will depend on winning or
     * losing and whether it's the game's last round or not.
     * @param roundWon If the round was won.
     * @param isLastRound If it's the last round.
     */
    private void playSound(final boolean roundWon, final boolean isLastRound) {
            final String soundPath;
            String soundPath1;
            if (roundWon) {
                soundPath1 = "/sound/success1.wav";
            } else {
                soundPath1 = "/sound/violin-lose.wav";
            }
            if (isLastRound) {
                soundPath1 = "/sound/success-fanfare.wav";
            }
            soundPath = soundPath1;
            Audio audio = new Audio();
            audio.playSound(soundPath);
    }

    /**
     * This function is triggered when the "continue" button label is clicked.
     * Checks for valid round selection, then plays round and
     * decisions are made depending on round lost or won and if it's the
     * game's last round or not. Music is played and
     * the correct screen is launched. All states are saved in Game Manager
     * and Game Environment.
     */
    @FXML
    private void continueButtonPressed() {
        if (selectedRoundDifficulty != -1) {
            try {
                if (gameManager.getInventory().getMainTowers().isEmpty()) {
                    throw new BelowMinimumTowerException("You don't have any "
                             + "towers in your main tower list.");
                }
                gameManager.setSelectedRound(
                        roundOptions.get(selectedRoundDifficulty));
                boolean roundWon = gameManager.isRoundWon();
                boolean isLastRound =
                        currentRound == gameManager.getNumberOfRounds();

                if (isLastRound) {
                    playSound(roundWon, true);
                    if (roundWon) {
                        gameManager.setPlayersMoney(
                                gameManager.getPlayersMoney()
                                        + gameManager.getRoundManager()
                                        .calculateEarnings());
                    }
                    gameManager.increaseCurrentRound();
                    gameManager.setGameWon(roundWon);
                    gameEnvironment.setGameManager(gameManager);
                    gameEnvironment.closeSetupRoundScreen();
                    gameEnvironment.launchEndGameScreen();
                } else {
                    if (roundWon) {
                        playSound(true, false);
                        gameManager.increaseCurrentRound();
                        gameManager.createShop();
                        gameManager.generateRoundOptions();
                        gameEnvironment.setGameManager(gameManager);
                        gameEnvironment.clearDisabledButtons();
                        gameEnvironment.closeSetupRoundScreen();
                        gameEnvironment.launchRoundWonScreen();
                    } else {
                        playSound(false, false);
                        gameManager.setGameWon(false);
                        gameEnvironment.setGameManager(gameManager);
                        gameEnvironment.closeSetupRoundScreen();
                        gameEnvironment.launchEndGameScreen();
                    }
                }
            } catch (BelowMinimumTowerException e) {
                errorMessageLabel.setText(e.getMessage());
            }
        } else {
                    errorMessageLabel.setText("Please select one option");
                }
            }

    /**
     * Closes the current screen and launches the store screen.
     */
    @FXML
    private void goStore() {
        gameEnvironment.closeSetupRoundScreen();
        gameEnvironment.launchShopScreen();
    }

    /**
     * Closes the current screen and launches the inventory screen.
     */
    @FXML
    private void goInventory() {
        gameEnvironment.closeSetupRoundScreen();
        gameEnvironment.launchInventoryScreen();
    }
}