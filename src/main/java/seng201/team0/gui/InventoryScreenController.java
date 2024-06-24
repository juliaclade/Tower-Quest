package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import seng201.team0.audio.Audio;
import seng201.team0.models.exceptions.InvalidSelectionException;
import seng201.team0.services.GameEnvironment;
import javafx.scene.control.Label;
import seng201.team0.models.Inventory;
import seng201.team0.models.Tower;
import seng201.team0.models.Upgrade;
import seng201.team0.models.exceptions.BelowMinimumTowerException;
import seng201.team0.models.exceptions.ResourcesDontMatchException;
import seng201.team0.services.GameManager;

import java.util.ArrayList;
import java.util.List;


/**
 * Screen for inventory .This class provides the gui integration for the
 * inventory in the game.
 * @author Julia Clade and Katherine Irving Seguel
 * @version 1.0, 11 Apr 2024
 */
@SuppressWarnings("checkstyle:JavadocType")
public class InventoryScreenController {

    /**
     * Stores the player's name.
     */
    private final String playerName;

    /**
     * Stores the inventory.
     */
    private final Inventory inventory;

    /**
     * Stores the state of the game and launches the screens.
     */
    private final GameEnvironment gameEnvironment;

    /**
     * Stores the label to display the current/total rounds of the game.
     */
    @FXML
    private Label curRoundTotalRounds;

    /**
     * Stores the label to display the player's money.
     */
    @FXML
    private Label playerMoneyLabel;

    /**
     * Stores the label to display players name and  main towers string.
     */
    @FXML
    private Label mainTowersLabel;

    /**
     * Stores the label to display players name and reserve towers string.
     */
    @FXML
    private Label reserveTowersLabel;

    /**
     * Stores the label to display players name and upgrades string.
     */
    @FXML
    private Label upgradeLabel;

    /**
     * Stores the button for main tower 1.
     */
    @FXML
    private Button mainTowerButton1;

    /**
     * Stores the button for main tower 2.
     */
    @FXML
    private Button mainTowerButton2;

    /**
     * Stores the button for main tower 3.
     */
    @FXML
    private Button mainTowerButton3;

    /**
     * Stores the button for main tower 4.
     */
    @FXML
    private Button mainTowerButton4;

    /**
     * Stores the button for main tower 5.
     */
    @FXML
    private Button mainTowerButton5;

    /**
     * Stores the list of the main tower buttons.
     */
    private List<Button> mainTowersButtons;

    /**
     * Stores the list of the reserve tower buttons.
     */
    private List<Button> reserveTowersButtons;

    /**
     * Stores the button for reserve tower 1.
     */
    @FXML
    private Button reserveTowerButton1;

    /**
     * Stores the button for reserve tower 2.
     */
    @FXML
    private Button reserveTowerButton2;

    /**
     * Stores the button for reserve tower 3.
     */
    @FXML
    private Button reserveTowerButton3;

    /**
     * Stores the button for reserve tower 4.
     */
    @FXML
    private Button reserveTowerButton4;

    /**
     * Stores the button for reserve tower 5.
     */
    @FXML
    private Button reserveTowerButton5;

    /**
     * Displays error messages.
     */
    @FXML
    private Label errorMessage;

    /**
     * Contains the Upgrade buttons in the Gui.
     */
    @FXML
    private HBox upgradesHX;

    /**
     * Stores the upgrade buttons.
     */
    private final List<Button> upgradeButton = new ArrayList<>();

    /**
     * Stores the index of the selected main tower.
     */
    private int selectedMainTowerIndex = -1;

    /**
     * Stores the index of  the selected reserve tower.
     */
    private int selectedReserveTowerIndex = -1;

    /**
     * Stores the index of the selected upgrade.
     */
    private int selectedUpgrade = -1;

    /**
     * Stores background color of a Tower and Upgrade before being
     * clicked.
     */
    private static final String BUTTON_BACK_GROUND_COLOUR = "-fx-background"
            + "-color: "
             + "#e3e3e3;";

    /**
     * Stores background color of a Tower or Upgrade after being clicked.
     */
    private static final String BUTTON_BACK_GROUND_COLOUR_CLICK =
            "-fx-background"
            + "-color: #b3b3b3;";

    /**
     * Stores the text alignment, color and image properties of a
     * button.
     */
    private static final String BUTTON_STYLE = "-fx-alignment: bottom-center;"
            + "-fx-text-fill: #464646;"
            + "-fx-background-position: top;"
            + "-fx-background-repeat: no-repeat;";

    /**
     * Stores the width for the upgrades H-Box.
     */
    private static final int UPGRADES_H_BOX_WIDTH = 102;

    /**
     * Stores the dimensions for the upgrade button.
     */
    private static final int UPGRADE_BUTTON_DIMENSIONS = 120;

    /**
     * Stores the font size for upgrade buttons.
     */
    private static final int FONT_SIZE = 13;

    /**
     * Provides access to player name, money, game
     * difficulty, current shop, round and inventory.
     */
    private final GameManager gameManager;

    /**
     * Stores the spacing for the upgrade buttons.
     */
    private static final int UPGRADE_BUTTON_SPACING = 15;

    /**
     * Stores the maximum length of the tower lists.
     */
    private static final int MAX_TOWERS_IN_LIST = 5;

    /**
     * Stores the sound path of sound used in swap action.
     */
    private static final String SWAP_SOUND_PATH = "/sound/swap.wav";

    /**
     * Stores the sound path of sound used in apply upgrade action.
     */
    private static final String APPLY_UPGRADE_SOUND_PATH = "/sound/potion.wav";

    /**
     * Constructor takes a gameEnvironment and extracts the
     * gameManager for easy access to all game and player data.
     * @param gameEnvironment Keeps track of the state of the game.
     */
    @SuppressWarnings("checkstyle:HiddenField")
    public InventoryScreenController(final GameEnvironment gameEnvironment) {
        this.gameEnvironment = gameEnvironment;
        this.gameManager = gameEnvironment.getGameManager();
        this.inventory = gameManager.getInventory();
        this.playerName = gameManager.getPlayersName();
    }

    /**
     * Sets the screen's labels and style.
     */
    public void initialize() {
        setMoneyLabel();
        setRoundLabel();
        mainTowersLabel.setText(playerName + "'s main towers");
        reserveTowersLabel.setText(playerName + "'s reserve towers");
        upgradeLabel.setText(playerName + "'s upgrades");
        initializeTowers();
        initializeUpgrades();
    }

    /**
     * Sets the round label.
     */
    private void setRoundLabel() {
        String curRound = Integer
            .toString(gameManager.getCurrentRound());
        String totalRound = Integer
            .toString(gameManager.getNumberOfRounds());
        curRoundTotalRounds.setText(curRound + "/" + totalRound);
    }

    /**
     * Updates the money label.
     */
    private void setMoneyLabel() {
        String playersMoney = Integer
                .toString(gameManager.getPlayersMoney());
        playerMoneyLabel.setText(playersMoney);
    }

    /**
     * Event handler for Tower buttons that sets style for
     * highlighting.
     */
    private void initializeTowers() {
        mainTowersButtons = new ArrayList<>(
                List.of(mainTowerButton1, mainTowerButton2,
                        mainTowerButton3, mainTowerButton4,
                        mainTowerButton5));
        reserveTowersButtons = new ArrayList<>(
                List.of(reserveTowerButton1, reserveTowerButton2,
                        reserveTowerButton3, reserveTowerButton4,
                        reserveTowerButton5));
        mainTowersButtons.forEach(button
                          -> button.setStyle(BUTTON_BACK_GROUND_COLOUR));
        reserveTowersButtons.forEach(button
                         -> button.setStyle(BUTTON_BACK_GROUND_COLOUR));


        for (int i = 0; i < inventory.getMainTowers().size(); i++) {
            Tower currentMainTower
                    = inventory.getMainTowers().get(i);
            String towerData = currentMainTower.toString("swap");
            mainTowersButtons.get(i).setText(towerData);
            String mainTowerPath = Tower.towerImagePath(currentMainTower);

            mainTowersButtons.get(i).setText((currentMainTower.toString(
                    "swap")));
            mainTowersButtons.get(i).setStyle(BUTTON_STYLE
                        + "-fx-background-image: url('" + mainTowerPath + "');"
                        + BUTTON_BACK_GROUND_COLOUR);
    }

        for (int i = 0; i < inventory.getReserveTowers()
                .size(); i++) {
            Tower currentReserveTower = inventory.getReserveTowers()
                    .get(i);
            String towerData = (currentReserveTower.toString("swap"));
            reserveTowersButtons.get(i).setText(towerData);
            String reserveTowerPath = Tower.towerImagePath(currentReserveTower);

            reserveTowersButtons.get(i)
                    .setText((currentReserveTower.toString("swap")));
            reserveTowersButtons.get(i).setStyle(
                            BUTTON_STYLE
                            + "-fx-background-image: url("
                            + "'" + reserveTowerPath + "');"
                            + BUTTON_BACK_GROUND_COLOUR);
        }

        for (int i = 0; i < mainTowersButtons.size(); i++) {
            int index = i;

            mainTowersButtons.get(i).setOnAction(event -> {
                if (selectedReserveTowerIndex != -1 && selectedUpgrade != -1) {
                    upgradeButton
                    .forEach(button -> button
                    .setStyle(button.getStyle() + ";"
                            + BUTTON_BACK_GROUND_COLOUR));
                    reserveTowersButtons
                    .forEach(button -> button
                    .setStyle(button.getStyle() + ";"
                            + BUTTON_BACK_GROUND_COLOUR));
                    selectedReserveTowerIndex = -1;
                    selectedUpgrade = -1;
                }
                mainTowersButtons
                    .forEach(button -> button
                    .setStyle(button.getStyle() + ";"
                            + BUTTON_BACK_GROUND_COLOUR));
                mainTowersButtons
                    .get(index).setStyle(mainTowersButtons
                     .get(index).getStyle() + ";"
                                + BUTTON_BACK_GROUND_COLOUR_CLICK);
                selectedMainTowerIndex = index;
            });
        }

        for (int i = 0; i < reserveTowersButtons.size(); i++) {
            int index = i;
            reserveTowersButtons.get(i).setOnAction(event -> {
                if (selectedUpgrade != -1 && selectedMainTowerIndex != -1) {
                    upgradeButton
                        .forEach(button -> button
                        .setStyle(button.getStyle() + ";"
                                + BUTTON_BACK_GROUND_COLOUR));
                    mainTowersButtons
                        .forEach(button -> button.setStyle(button
                        .getStyle() + ";"
                                + BUTTON_BACK_GROUND_COLOUR));
                    selectedMainTowerIndex = -1;
                    selectedUpgrade = -1;
                }
                reserveTowersButtons
                    .forEach(button -> button.setStyle(button
                    .getStyle() + ";" + BUTTON_BACK_GROUND_COLOUR));
                reserveTowersButtons.get(index)
                    .setStyle(reserveTowersButtons.get(index)
                    .getStyle() + ";" + BUTTON_BACK_GROUND_COLOUR_CLICK);
                selectedReserveTowerIndex = index;
            });
        }
    }

    /**
     * Event handler for Upgrade buttons that sets style for
     * highlighting.
     */
    private void initializeUpgrades() {
        upgradesHX.setPrefWidth(inventory.getUpgrades().size()
                                    * UPGRADES_H_BOX_WIDTH);
        for (Upgrade ownUpgrades : inventory.getUpgrades()) {
            Button button = new Button(ownUpgrades.toString("apply upgrade"));
            upgradeButton.add(button);
            button.setMinHeight(UPGRADE_BUTTON_DIMENSIONS);
            button.setMinWidth(UPGRADE_BUTTON_DIMENSIONS);
            button.setFont(new Font("helvetica neue bold", FONT_SIZE));
            String upgradePath = Upgrade.upgradeImagePath(ownUpgrades);

            button.setStyle(
                    BUTTON_STYLE
                    + "-fx-background-image: url('" + upgradePath + "');"
                    + BUTTON_BACK_GROUND_COLOUR);
            upgradesHX.getChildren().add(button);
        }

        for (int i = 0; i < inventory.getUpgrades().size(); i++) {
            int index = i;
            upgradeButton.get(i).setOnAction(event -> {
                if (selectedMainTowerIndex != -1
                        && selectedReserveTowerIndex != -1) {
                    selectedReserveTowerIndex = -1;
                    selectedMainTowerIndex = -1;
                    reserveTowersButtons.forEach(button -> button
                            .setStyle(button
                            .getStyle() + ";" + BUTTON_BACK_GROUND_COLOUR));
                    mainTowersButtons
                        .forEach(button -> button.setStyle(button.getStyle()
                                + BUTTON_BACK_GROUND_COLOUR));
                }
                upgradeButton.forEach(button -> button.setStyle(button
                        .getStyle() + ";" + BUTTON_BACK_GROUND_COLOUR));
                upgradeButton.get(index)
                        .setStyle(upgradeButton.get(index)
                          .getStyle() + ";" + BUTTON_BACK_GROUND_COLOUR_CLICK);
                selectedUpgrade = index;
            });
        }
        upgradesHX.setSpacing(UPGRADE_BUTTON_SPACING);
    }

    /**
     * Facilitates tower swapping between lists and catches errors when an
     * invalid selection is made or the player attempts to remove the last tower
     * in the main towers list.
     */
    @FXML
    private void swapTowers() {
        if (selectedMainTowerIndex == -1 || selectedReserveTowerIndex == -1) {
            errorMessage.setText(
                "Please select a main Tower and a reserve tower to swap");
        } else {
            try {
                inventory.swap(selectedMainTowerIndex,
                        selectedReserveTowerIndex);
                errorMessage.setText("");
                Audio audio = new Audio();
                audio.playSound(SWAP_SOUND_PATH);
            } catch (BelowMinimumTowerException | InvalidSelectionException e) {
                errorMessage.setText(e.getMessage());
            }

            for (int i = 0; i < MAX_TOWERS_IN_LIST; i++) {
                reserveTowersButtons.get(i).setText("");
                mainTowersButtons.get(i).setText("");
            }
            mainTowersButtons.forEach(button -> button.setStyle(""));
            reserveTowersButtons.forEach(button -> button.setStyle(""));
            selectedReserveTowerIndex = -1;
            selectedMainTowerIndex = -1;
            initializeTowers();
        }
    }

    /**
     * Applies selected upgrade to selected tower and catches errors if the
     * wrong selection is made.
     */
    @FXML
    private void applyUpgrade() {
        boolean noTowerSelected =
                (selectedMainTowerIndex == -1
                         || selectedReserveTowerIndex == -1);
        try {
            if ((noTowerSelected && selectedUpgrade == -1)) {
                errorMessage.setText("Please select a Tower and an Upgrade");
            } else {
                Upgrade upgrade = inventory
                        .getUpgrades().get(selectedUpgrade);
                if (selectedMainTowerIndex != -1) {
                    Tower mainTower = inventory
                            .getMainTowers().get(selectedMainTowerIndex);
                    inventory.applyUpgrade(upgrade, mainTower);
                } else {
                    Tower reserveTower = inventory
                        .getReserveTowers().get(selectedReserveTowerIndex);
                    inventory.applyUpgrade(upgrade, reserveTower);
                }
                Audio audio = new Audio();
                audio.playSound(APPLY_UPGRADE_SOUND_PATH);
                errorMessage.setText("");
                inventory.removeUpgrade(upgrade);
                upgradeButton.clear();
                upgradesHX.getChildren().clear();
                selectedMainTowerIndex = -1;
                selectedReserveTowerIndex = -1;
                selectedUpgrade = -1;
                mainTowersButtons.forEach(button -> button.setStyle(""));
                reserveTowersButtons.forEach(button -> button.setStyle(""));
                upgradeButton.forEach(button -> button.setStyle(""));
                initialize();
            }
        } catch (IndexOutOfBoundsException e) {
            errorMessage.setText("Please select a Tower and an Upgrade");
        } catch (ResourcesDontMatchException e) {
            errorMessage.setText(e.getMessage());
        }
    }

    /**
     *  Saves state of game and catches errors if there are no towers in the
     *  main towers list. A message is displayed , and the player is not allowed
     *  to go back to the roundSetupScreen.
     *  BelowMinimumTowerException Exception is thrown when main
     *  towers has no towers in it.
     */
    @FXML
    private void back() {
        try {
            if (!inventory.getMainTowers().isEmpty()) {
                gameManager
                .setMainTowerList(inventory.getMainTowers());
                gameManager
                .setReserveTowersList(inventory.getReserveTowers());
                gameManager
                .setUpgradeList(inventory.getUpgrades());

                gameEnvironment.setGameManager(gameManager);
        gameEnvironment.closeInventoryScreen();
        gameEnvironment.launchSetupRoundScreen();
            } else {
                throw new BelowMinimumTowerException("Your main tower "
                    + "list has "
                    + "to have"
                    + " one tower to continue");
            }
        } catch (BelowMinimumTowerException e) {
            errorMessage.setText(e.getMessage());
        }

    }
}
