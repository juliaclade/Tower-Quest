package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import seng201.team0.models.Inventory;
import seng201.team0.services.GameEnvironment;
import seng201.team0.services.GameManager;
import seng201.team0.models.Tower;
import seng201.team0.models.exceptions.BelowMinimumTowerException;

import java.util.ArrayList;
import java.util.List;

/**
 * Implements the gui for the game setup screen
 * in which the player
 * sets up their name, game difficulty, round numbers and starting towers.
 * @author Julia Clade and Katherine Irving Seguel
 * @version 1.0, 11 Apr 2024
 */
@SuppressWarnings("checkstyle:JavadocType")
public class GameSetupScreenController {

    /**
     * Stores the GameManger, it provides access to player name, money, game
     * difficulty,
     * current shop, round and inventory.
     */
    private final GameManager gameManager;

    /**
     * Stores the TextField where player needs to enter their name here.
     */
    @FXML
    private TextField playerName;

    /**
     * Stores the slider for round numbers.
     */
    @FXML
    private Slider roundNumberSlider;

    /**
     * Stores the slider for game difficulty.
     */
    @FXML
    private Slider gameDifficultySlider;

    /**
     * Stores button for tower 1.
     */
    @FXML
    private Button tower1Button;

    /**
     * Stores button for tower 2.
     */
    @FXML
    private Button tower2Button;

    /**
     * Stores button for tower 3.
     */
    @FXML
    private Button tower3Button;

    /**
     * Stores button for tower 4.
     */
    @FXML
    private Button tower4Button;

    /**
     * Stores button for tower 5.
     */
    @FXML
    private Button tower5Button;

    /**
     * Stores button for selection 1.
     */
    @FXML
    private Button selection1Button;

    /**
     * Stores button for selection 2.
     */
    @FXML
    private Button selection2Button;

    /**
     * Stores button for selection 3.
     */
    @FXML
    private Button selection3Button;

    /**
     * Stores label to display error message related to name input.
     */
    @FXML
    private Label errorMessageName;

    /**
     * Stores label that displays selected tower resource type.
     */
    @FXML
    private Label resourceType;

    /**
     * Stores label that displays selected tower resource amount.
     */
    @FXML
    private Label resourceAmount;

    /**
     * Stores label that displays selected tower reload speed.
     */
    @FXML
    private Label reloadSpeed;

    /**
     * Stores label that displays selected tower level XP.
     */
    @FXML
    private Label levelXP;

    /**
     * Stores label to displays error message when start button is click.
     */
    @FXML
    private Label errorMessageStart;

    /**
     * Stores the lists all the selected towers.
     */
    private final List<Tower> selectedTowers = new ArrayList<>();

    /**
     * Stores the selected tower index.
     */
    private int selectedTowerIndex = -1;

    /**
     * Stores the GameEnvironment.
     */
    private final GameEnvironment gameEnvironment;

    /**
     * Stores list of the image paths for towers.
     */
    private final List<String> towerImagePath = new ArrayList<>();

    /**
     * Stores the Inventory.
     */
    private final Inventory inventory;

    /**
     * Stores the allowed length of selected towers.
     */
    private static final int MAX_SELECTED_TOWERS = 3;

    /**
     * Stores the selected tower button's text and background color.
     */
    private static final String SELECTED_TOWER_BUTTON_STYLE = "-fx-background"
            + "-color: "
            + "#FFFF;"
            + "-fx-background-radius: 5; ";

    /**
     * Stores the clicked tower's text and background color.
     */
    private static final String CLICKED_TOWER_BUTTON_STYLE = "-fx-effect"
            + ":dropshadow"
            + "(three-pass-box," + "rgba(255,255,255,255), 8, 0.5, 0, 0);"
            + "-fx-text-fill:  #B9E500 ;" + "-fx-background-color: #b3b3b3; "
            + "-fx-background-radius: 5;";

    /**
     * Stores the tower button's text and background color.
     */
    private static final String TOWER_BUTTON_STYLE = " -fx-text-fill: #ffffff; "
            + "-fx" + "-background-color: #FFFF; "
                    + "-fx-background-radius: 5;";


    /**
     * Constructs a GameSetupScreenController with the
     * GameEnvironment variable.
     * @param gameEnvironment keeps track of the state of the game.
     */
    @SuppressWarnings("checkstyle:HiddenField")
    public GameSetupScreenController(final GameEnvironment gameEnvironment) {
        this.gameEnvironment = gameEnvironment;
        this.gameManager = gameEnvironment.getGameManager();
        this.inventory = gameManager.getInventory();
    }

    /**
     * Handles the action events for selected tower buttons by setting
     * highlight style and images and displaying labels of
     * tower stats. Swaps the towers in their respective lists on selection.
     */
    public void initialize() {
        List<Button> selectedTowerButtons = List.of(selection1Button,
                                                    selection2Button,
                                                    selection3Button);
        List<Button> towerButtons = List.of(tower1Button,
                                            tower2Button,
                                            tower3Button,
                                            tower4Button,
                                            tower5Button);
        for (int i = 0; i < towerButtons.size(); i++) {
            int finalI = i;
            Tower towerCurrent = gameManager.getStartingTowers().get(finalI);
            String path = Tower.towerImagePath(towerCurrent);
            towerImagePath.add(path);

            towerButtons.get(i).setStyle(TOWER_BUTTON_STYLE
                   + "-fx-background-image: url('" + path + "')");
            towerButtons.get(i).setOnAction(event -> {
            updateStats(gameManager.getStartingTowers().get(finalI));
            selectedTowerIndex = finalI;
            towerButtons.forEach(button -> {
                if (button == towerButtons.get(finalI)) {
                    button.setStyle(CLICKED_TOWER_BUTTON_STYLE
                                    + "-fx-background-image:"
                                    + "url('"
                                    + towerImagePath
                                    .get(towerButtons.indexOf(button)) + "') ");
                } else {
                    button.setStyle(
                            TOWER_BUTTON_STYLE
                                    + "-fx-background-image: "
                                    + "url('" + towerImagePath.
                                    get(towerButtons.indexOf(button)) + "')");
                    }
                });
            });
        }
        for (int k = 0; k < selectedTowerButtons.size(); k++) {
            int finalI = k;
            selectedTowerButtons.get(k).setOnAction(event -> {
                if (selectedTowerIndex == -1) {
                    selectedTowerButtons.get(finalI).
                            setStyle(selectedTowerButtons.
                                    get(finalI).getStyle());
                } else {
                    Tower selectedTower =
                            gameManager.getStartingTowers().
                                    get(selectedTowerIndex);
                    if (!selectedTowers.contains(selectedTower)) {
                        if (selectedTowers.size() < MAX_SELECTED_TOWERS) {
                            selectedTowerButtons.get(finalI).setStyle(
                                    SELECTED_TOWER_BUTTON_STYLE
                                            + "-fx-background-image: url('"
                                            + towerImagePath.
                                            get(selectedTowerIndex)
                                            + "')");
                            selectedTowers.add(selectedTower);
                        } else {
                            selectedTowerButtons.get(finalI).setStyle(
                                    SELECTED_TOWER_BUTTON_STYLE
                                        + "-fx-background-image: url('"
                                        + towerImagePath.get(selectedTowerIndex)
                                        + "')");
                            selectedTowers.set(finalI, selectedTower);
                        }
                    }
                }
            });
        }
    }

    /**
     * Updates the labels with the selected tower's stats.
     * @param tower selected tower
     */
    public void updateStats(final Tower tower) {
        reloadSpeed.setText("Reload Speed: " + tower.getReloadSpeed());
        resourceAmount.setText("Resource amount: " + tower.getResourceAmount());
        levelXP.setText("XP Level: " + tower.getLevelXP());
        resourceType.setText("Resource type: " + tower.getResourceType());
    }

    /**
     * Sets the chosen round number from the value on the slider.
     */
    @FXML
    private void setRoundNumber() {
        gameManager.setNumberOfRounds((int) roundNumberSlider.getValue());
    }

    /**
     * Sets the chosen game difficulty from the value on the
     * slider.
     */
    @FXML
    private void setGameDifficulty() {
        gameManager.setGameDifficulty((int) gameDifficultySlider.getValue());
    }

    /**
     * Stores all values chosen by player in gameManager, stores
     * gameManager in gameEnvironment and launches the SetupRoundScreen.
     * It also checks for valid player name input and tower selection and
     * won't launch the next screen if an error is detected.
     */
    @FXML
    private void onStartClicked() {
        String name = playerName.getText();
        try {
            inventory.setMainTowers(selectedTowers);
            gameManager.setInventory(inventory);
            setRoundNumber();
            setGameDifficulty();
            gameEnvironment.setGameManager(gameManager);
            if (gameManager.validatePlayerName(name)) {
                errorMessageName.setText("");
            }
            if (gameManager.validateSelectedTowerListSize(selectedTowers,
                    MAX_SELECTED_TOWERS)) {
                errorMessageStart.setText("");
            }
            gameManager.setPlayersName(name);

            gameEnvironment.closeGameSetupScreen();
            gameEnvironment.launchSetupRoundScreen();
        } catch (IllegalArgumentException e) {
            errorMessageName.setText(e.getMessage());
            errorMessageStart.setText("");
        } catch (BelowMinimumTowerException e) {
            errorMessageStart.setText("Please select 3 towers.");
        }
    }
}


