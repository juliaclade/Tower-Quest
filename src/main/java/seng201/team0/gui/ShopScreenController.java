package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import seng201.team0.audio.Audio;
import seng201.team0.models.Inventory;
import seng201.team0.services.GameEnvironment;
import seng201.team0.models.Shop;
import seng201.team0.models.Tower;
import seng201.team0.models.Upgrade;
import seng201.team0.models.exceptions.InvalidSelectionException;
import seng201.team0.models.exceptions.InventoryFullException;
import seng201.team0.models.exceptions.BelowMinimumTowerException;
import seng201.team0.models.exceptions.NotEnoughMoneyException;
import seng201.team0.services.GameManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
/**
 * Screen that lets the user buy and sell towers and upgrades.
 * @author Julia Clade and Katherine Irving Seguel
 * @version 1.0, 11 Apr 2024
 */
@SuppressWarnings({"checkstyle:JavadocType", "checkstyle:JavadocStyle"})
public class ShopScreenController {

    /**
     * Stores the state of the game and launches the screens.
     */
    private final GameEnvironment gameEnvironment;

    /**
     * Stores button height.
     */
    private static final int BUTTON_HEIGHT = 150;

    /**
     * Stores button width.
     */
    private static final int BUTTON_WIDTH = 142;

    /**
     * Stores font size for buttons.
     */
    private static final int FONT_SIZE = 13;

    /**
     * Stores  Hx-box spacing.
     */
    private static final int HX_SPACING = 15;

    /**
     * Stores H-Box width.
     */
    private static final int UPGRADES_H_BOX_WIDTH = 102;

    /**
     * Stores the button for first tower in the shop in buy section.
     */
    @FXML
    private Button shopTower1Button;

    /**
     * Stores the button for second tower in the shop in buy section.
     */
    @FXML
    private Button shopTower2Button;

    /**
     * Stores the button for the third tower in the shop in buy section.
     */
    @FXML
    private Button shopTower3Button;

    /**
     * Stores the button for the fourth tower in the shop in buy section.
     */
    @FXML
    private Button shopTower4Button;

    /**
     * Stores the button for the fifth tower in the shop in buy section.
     */
    @FXML
    private Button shopTower5Button;

    /**
     * Stores the button for first upgrade in the shop in sell section.
     */
    @FXML
    private Button shopUpgrade1Button;

    /**
     * Stores the button for second upgrade in the shop in sell section.
     */
    @FXML
    private Button shopUpgrade2Button;

    /**
     * Stores the button for third upgrade in the shop in sell section.
     */
    @FXML
    private Button shopUpgrade3Button;

    /**
     * Stores the button for fourth upgrade in the shop in sell section.
     */
    @FXML
    private Button shopUpgrade4Button;

    /**
     * Stores the button for fifth upgrade in the shop in sell section.
     */
    @FXML
    private Button shopUpgrade5Button;

    /**
     * Displays error messages in the buy section in the
     * Shop.
     */
    @FXML
    private Label buyErrorMessageLabel;

    /**
     * Display the current/total round status of the game.
     */
    @FXML
    private Label curRoundTotalRounds;

    /**
     * Displays the player's money.
     */
    @FXML
    private Label playerMoneyLabel;

    /**
     * Stores the H-box used to display/contain upgrade buttons in sell section
     *  of Shop.
     */
    @FXML
    private HBox currentHxUpgrade;

    /**
     * Displays error messages in the sell section in the shop.
     */
    @FXML
    private Label sellErrorMessage;

    /**
     * Stores the H-box used to display/contain Tower buttons in sell section
     * of shop.
     */
    @FXML
    private HBox currentHxTowersSell;

    /**
     * Stores all the Towers that can be bought in the buy section.
     */
    private final List<Tower> buyTower;

    /**
     * Stores the all the upgrades that can be bought in the buy section.
     */
    private final List<Upgrade> buyUpgrade;

    /**
     * Stores the current shop.
     */
    private final Shop currentShop;

    /**
     * Stores the current round number.
     */
    private final int currentRound;

    /**
     * Stores the total round number.
     */
    private final int totalRounds;

    /**
     * Stores the player's money.
     */
    private int playerMoney;

    /**
     * Stores the index of the selected Tower in the buy
     * section of the shop.
     */
    private int selectedTowerIndex = -1;

    /**
     * Stores the index of the selected upgrade in the buy
     * section of the shop.
     */
    private int selectedUpgradeIndex = -1;

    /**
     * Stores the player's main towers.
     */
    private final List<Tower> mainTowers;

    /**
     * Stores the player's reserve towers.
     */
    private final List<Tower> reserveTowers;

    /**
     * Stores the player's upgrades.
     */
    private final List<Upgrade> upgrades;

    /**
     * Stores all the tower buttons in buy section.
     */
    private List<Button> towerButtons;

    /**
     * Stores all the upgrade buttons in buy section.
     */
    private List<Button> upgradeButtons;

    /**
     * Stores all the buttons that have been disabled in
     * the screen.
     */
    private final List<Button> disabledButtons;

    /**
     * Stores all the upgrade buttons in sell section.
     */
    private final List<Button> sellUpgradeButton = new ArrayList<>();

    /**
     * Stores the index of the current selected upgrade in the
     * sell section.
     */
    private int currentSelectedUpgradeSell = -1;

    /**
     * Stores the player's inventory.
     */
    private final Inventory inventory;

    /**
     * Stores all the upgrades that were sold in the sell
     * section.
     */
    private final List<Upgrade> removeUpgradesSold = new ArrayList<>();

    /**
     * Stores the towers that were sold in the sell
     * section.
     */
    private final List<Tower> removeTowersSold = new ArrayList<>();

    /**
     * Stores all the towers buttons that can be sold in
     * the sell section.
     */
    private final List<Button> sellTowerButton = new ArrayList<>();

    /**
     * Stores the current selected tower in the sell section.
     */
    private int currentSelectedTowerSell = -1;

    /**
     * Stores the list of the image paths for towers in buy section.
     */
    private final List<String> towerBuyImagePath = new ArrayList<>();

    /**
     * Stores the image paths for upgrades in the buy
     * section.
     */
    private final List<String> upgradeImagePath = new ArrayList<>();

    /**
     * Stores the image paths for upgrades in the sell section.
     */
    private final List<String> upgradeSellImagePath = new ArrayList<>();

    /**
     * Provides access to player name, money, game difficulty,
     * current shop, round and inventory.
     */
    private final GameManager gameManager;

    /**
     * Stores the image paths for towers in the sell section.
     */
    private final List<String> towerSellImagePath = new ArrayList<>();

    /**
     * Stores the button's style and alignment values before is clicked.
     */
    private static final String BUTTON_STYLE_BEFORE_CLICK = "-fx-alignment: "
            + "bottom-center;"
            + "-fx" + "-text-fill: #464646;"
            + "-fx-background-position: top;"
            + "-fx-background-repeat: no-repeat;";

    /**
     * Stores the button's style and alignment values after it's clicked.
     */
     private static final String GET_BUTTON_STYLE_AFTER_CLICK =
            "-fx-alignment: "
            + "bottom-center;"
            + "-fx-text-fill: #6a7e02;"
            + "-fx-background-color: #b3b3b3;"
            + "-fx-background-repeat: no-repeat;"
            + "-fx-background-position: top;";

    /**
     * Stores the button's highlight style when it's clicked.
     */
    private static final String BUTTON_CLICK_HIGHLIGHT = "-fx-effect:dropshadow"
            + "(three-pass-box,"
            + "rgba(255,255,255,255), 8, 0.5, 0, 0)";

    /**
     * Stores the sound path for when an item is bought or sold.
     */
    private static final String SOUND_PATH = "/sound/money.wav";

    /**
     * Initializes all the game data from the gameEnvironment.
     * @param gameEnvironment keeps track of the state of the game.
     */
    @SuppressWarnings("checkstyle:HiddenField")
    public ShopScreenController(final GameEnvironment gameEnvironment) {
        this.gameEnvironment = gameEnvironment;
        this.gameManager = gameEnvironment.getGameManager();
        this.inventory = gameManager.getInventory();
        currentRound = gameManager.getCurrentRound();
        totalRounds = gameManager.getNumberOfRounds();
        playerMoney = gameManager.getPlayersMoney();
        mainTowers = inventory.getMainTowers();
        reserveTowers = inventory.getReserveTowers();
        upgrades = inventory.getUpgrades();
        currentShop = gameManager.getCurrentShop();
        buyTower = currentShop.getTowersForSale();
        buyUpgrade = currentShop.getUpgradesForSale();
        disabledButtons = gameEnvironment.getDisabledButtons();
    }

    /**
     * Sets button's highlight styles and images, as well as displaying labels
     * for tower and upgrade stats.
     */
    @SuppressWarnings("checkstyle:WhitespaceAround")
    public void initialize() {
        towerButtons = new ArrayList<>(List.of(shopTower1Button,
                shopTower2Button, shopTower3Button, shopTower4Button,
                shopTower5Button));

        upgradeButtons = new ArrayList<>(List.of(shopUpgrade1Button,
                shopUpgrade2Button, shopUpgrade3Button, shopUpgrade4Button,
                shopUpgrade5Button));
        for (int i = 0; i < upgradeButtons.size(); i++) {
            Tower currentBuyTower = buyTower.get(i);
            String towerPath = Tower.towerImagePath(currentBuyTower);
            Upgrade currentBuyUpgrade = buyUpgrade.get(i);
            String upGradePath = Upgrade.upgradeImagePath(currentBuyUpgrade);
            upgradeImagePath.add(upGradePath);
            towerBuyImagePath.add(towerPath);
            towerButtons.get(i).setText((currentBuyTower.toString("buy")));
            towerButtons.get(i).setStyle(BUTTON_STYLE_BEFORE_CLICK + "-fx"
                    + "-background-image: url('" + towerPath + "')");
            upgradeButtons.get(i).setText((currentBuyUpgrade.toString(
                    "buy")));
            upgradeButtons.get(i).setStyle(
                    BUTTON_STYLE_BEFORE_CLICK
                            + "-fx-background-image: url('"
                            + upGradePath + "')");
        }
        setRoundLabel();
        setMoneyLabel();
        sellUpgradeInitializer();
        sellTowerInitializer();
        buyTowerInitializer();
        buyUpgradeInitializer();
    }

    /**
     * Controls the action events and appearance of upgrade buttons
     * once one of them is clicked in the buy section.
     * It sets the selected tower index when the button is clicked.
     */
    @SuppressWarnings("checkstyle:MethodName")
    private void buyUpgradeInitializer() {
        for (int i = 0; i < upgradeButtons.size(); i++) {
            int index = i;
            for (Button button : disabledButtons) {
                if (Objects.equals(upgradeButtons.get(i).getId(),
                        button.getId())) {
                    upgradeButtons.get(i).setDisable(true);
                }
            }
            upgradeButtons.get(i).setOnAction(event -> {
                upgradeButtons.forEach(button -> button.setStyle(
                        BUTTON_STYLE_BEFORE_CLICK
                                + "-fx-background-image: url('"
                                + upgradeImagePath.get(upgradeButtons.
                                indexOf(button))
                                + "')"));
                upgradeButtons.get(index).setStyle(
                        GET_BUTTON_STYLE_AFTER_CLICK
                                + "-fx-background-image: url('"
                                + upgradeImagePath.get(index)
                                + "');" + BUTTON_CLICK_HIGHLIGHT);

                towerButtons.forEach(button -> button.setStyle(
                        BUTTON_STYLE_BEFORE_CLICK
                        + "-fx-background-image: url('"
                        + towerBuyImagePath.get(towerButtons.indexOf(button))
                        + "')"));
                buyErrorMessageLabel.setText("");
                selectedUpgradeIndex = index;
                selectedTowerIndex = -1;
            });
        }
    }

    /**
     * Controls tower button's action events and appearance once
     * one of them is clicked in the buy section. It sets the selected
     * tower index when the button is clicked.
     */
    @SuppressWarnings("checkstyle:ParenPad")
    private void buyTowerInitializer() {
        for (int i = 0; i < towerButtons.size(); i++) {
            int index = i;
            for (Button button : disabledButtons) {
                if (Objects.equals(towerButtons.get(i).getId(),
                        button.getId())) {
                    towerButtons.get(i).setDisable(true);
                }
            }
            towerButtons.get(i).setOnAction(event -> {
                towerButtons.forEach(button -> button.setStyle(
                        BUTTON_STYLE_BEFORE_CLICK
                        + "-fx-background-image: url('"
                                + towerBuyImagePath.get(towerButtons.
                                indexOf(button)) + "')"));
                towerButtons.get(index).setStyle(
                        GET_BUTTON_STYLE_AFTER_CLICK
                        + "-fx-background-image:"
                        + " url('" + towerBuyImagePath.get(index)
                        + "');" + BUTTON_CLICK_HIGHLIGHT);
                upgradeButtons.forEach(button -> button.setStyle(
                        BUTTON_STYLE_BEFORE_CLICK
                                + "-fx-background-image: url('"
                                + upgradeImagePath.get(upgradeButtons.
                                indexOf(button)) + "')"));
                buyErrorMessageLabel.setText("");
                selectedTowerIndex = index;
                selectedUpgradeIndex = -1;
            });
        }
    }

    /**
     * Controls and initializes upgrade button's action events and
     * appearance once one of them is clicked in the sell section.
     * It sets the selected tower index once the button is clicked.
     */
    private void sellUpgradeInitializer() {
        currentHxUpgrade.setPrefWidth(upgrades.size());
        for (Upgrade ownUpgrades : upgrades) {
            Button button = new Button(ownUpgrades.toString("sell"));
            sellUpgradeButton.add(button);
            String sellUpgradePath = Upgrade.upgradeImagePath(ownUpgrades);
            upgradeSellImagePath.add(sellUpgradePath);

            button.setMinHeight(BUTTON_HEIGHT);
            button.setMinWidth(BUTTON_WIDTH);
            button.setFont(new Font("helvetica neue bold", FONT_SIZE));
            button.setStyle(
                    BUTTON_STYLE_BEFORE_CLICK
                            + "-fx-background-image: url"
                            + "('" + sellUpgradePath + "')");
            currentHxUpgrade.getChildren().add(button);

        }
        for (int i = 0; i < sellUpgradeButton.size(); i++) {
            int index = i;
           sellUpgradeButton.get(i).setOnAction(event -> {

                sellUpgradeButton.forEach(button -> button.setStyle(
                        BUTTON_STYLE_BEFORE_CLICK
                                + "-fx-background-image: url('"
                                + upgradeSellImagePath.get(
                                        sellUpgradeButton.indexOf(button))
                                + "')"));
                sellUpgradeButton.get(index).setStyle(
                        GET_BUTTON_STYLE_AFTER_CLICK
                        + "-fx-background-image: url('"
                        + upgradeSellImagePath.get(index) + "')");
               if (currentSelectedTowerSell != -1) {
                   currentSelectedTowerSell = -1;
                   sellTowerButton.forEach(button -> button.setStyle(
                           BUTTON_STYLE_BEFORE_CLICK
                           + "-fx-background-image: url('"
                                   + towerSellImagePath.get(
                                           sellTowerButton.indexOf(button))
                                   + "')"));

               }
               upgradeButtons.forEach(button -> button.setStyle(
                       BUTTON_STYLE_BEFORE_CLICK
                               + "-fx-background-image: url('"
                               + upgradeImagePath.get(
                                       upgradeButtons.indexOf(button))
                               + "')"));
               sellErrorMessage.setText("");

               currentSelectedUpgradeSell = index;
           });
        }
        currentHxUpgrade.setSpacing(HX_SPACING);
    }

    /**
     * Controls and initializes tower button's action events and appearance
     * once one of them is clicked in the sell section.
     * It sets the selected tower index once the button is clicked.
     */
    private void sellTowerInitializer() {
        List<Tower> allTowersOwned = new ArrayList<>();
        allTowersOwned.addAll(mainTowers);
        allTowersOwned.addAll(reserveTowers);
        currentHxTowersSell.setPrefWidth(allTowersOwned.size()
                * UPGRADES_H_BOX_WIDTH);
        for (Tower ownTowers : allTowersOwned) {
            Button button = new Button(ownTowers.toString("sell"));
            String sellTowerPath = Tower.towerImagePath(ownTowers);
            towerSellImagePath.add(sellTowerPath);
            sellTowerButton.add(button);
            button.setMinHeight(BUTTON_HEIGHT);
            button.setMinWidth(BUTTON_WIDTH);
            button.setFont(new Font("helvetica neue bold", FONT_SIZE));
            button.setStyle(BUTTON_STYLE_BEFORE_CLICK
                    + "-fx-background-image: url('" + sellTowerPath + "')");
            currentHxTowersSell.getChildren().add(button);
            }
        for (int i = 0; i < sellTowerButton.size(); i++) {
            int index = i;
            sellTowerButton.get(i).setOnAction(event -> {
                sellTowerButton.forEach(button -> button.setStyle(
                        BUTTON_STYLE_BEFORE_CLICK
                        + "-fx-background-image: url('"
                                + towerSellImagePath.get(
                                        sellTowerButton.indexOf(button))
                                + "')"));
                sellTowerButton.get(index).setStyle("");
                if (currentSelectedUpgradeSell != -1) {
                    currentSelectedUpgradeSell = -1;
                    sellUpgradeButton.forEach(button -> button.setStyle(
                            BUTTON_STYLE_BEFORE_CLICK
                                    + "-fx-background-image: url('"
                                    + upgradeSellImagePath.get(
                                            sellUpgradeButton.indexOf(button))
                                    + "')"));
                }
                towerButtons.forEach(button -> button.setStyle(
                        BUTTON_STYLE_BEFORE_CLICK
                        + "-fx-background-image: "
                        + "url('" + towerBuyImagePath.get(
                                towerButtons.indexOf(button)) + "')"));
                sellTowerButton.get(index).setStyle(
                        GET_BUTTON_STYLE_AFTER_CLICK
                                + "-fx-background-image: "
                                + "url('" + towerSellImagePath.get(index)
                                + "')");
                sellErrorMessage.setText("");
                currentSelectedTowerSell = index;
            });
        }
        currentHxTowersSell.setSpacing(HX_SPACING);
    }

    /**
     * Updates the round label.
     */
    private void setRoundLabel() {
        String curRound = Integer.toString(currentRound);
        String totalRound = Integer.toString(totalRounds);
        curRoundTotalRounds.setText(curRound + "/" + totalRound);
    }

    /**
     * Updates the money label.
     */
    private void setMoneyLabel() {
        String playersMoney = Integer.toString(playerMoney);
        playerMoneyLabel.setText(playersMoney);
    }

    /**
     * Checks for valid tower or upgrade selection, then
     * disables the corresponding button. If selection is not valid, an error
     * is caught and a message is displayed. It uses the sellUpgrade and
     * sellTower
     * methods from the Shop class. Once an item is sold, it is added to the
     * removeUpgradesSold or removeTowersSold lists, so they are removed later
     * on.
     */
    @FXML
    private void sell() {

        if (currentSelectedUpgradeSell != -1) {
            try {
            if (disabledButtons.contains(sellUpgradeButton.
                    get(currentSelectedUpgradeSell))) {
                throw new InvalidSelectionException();
            }
                playerMoney = currentShop.sellUpgrade(
                        currentShop.getUpgradeList().get(
                                currentSelectedUpgradeSell), playerMoney
                );
                removeUpgradesSold.add(currentShop.getUpgradeList().get(
                        currentSelectedUpgradeSell));
                Audio audio = new Audio();
                audio.playSound(SOUND_PATH);
               sellErrorMessage.setText("");
                playerMoneyLabel.setText("" + playerMoney);
                sellUpgradeButton.get(
                        currentSelectedUpgradeSell).setDisable(true);
                disabledButtons.add(sellUpgradeButton.get(
                        currentSelectedUpgradeSell));
            } catch (InvalidSelectionException e) {
                sellErrorMessage.setText(e.getMessage());
            }
        } else if (currentSelectedTowerSell != -1) {
            try {
                if (disabledButtons.contains(sellTowerButton.
                        get(currentSelectedTowerSell))) {
                    throw new InvalidSelectionException();
                }
                playerMoney = currentShop.sellTower(currentShop.
                        getTowersList().get(
                        currentSelectedTowerSell), playerMoney, removeTowersSold

                );
                Audio audio = new Audio();
                audio.playSound(SOUND_PATH);
                removeTowersSold.add(currentShop.getTowersList().
                        get(currentSelectedTowerSell));
            sellErrorMessage.setText("");
            playerMoneyLabel.setText("" + playerMoney);
            sellTowerButton.get(currentSelectedTowerSell).setDisable(true);
            disabledButtons.add(sellTowerButton.get(currentSelectedTowerSell));
            } catch (InvalidSelectionException | BelowMinimumTowerException e) {
                sellErrorMessage.setText(e.getMessage());
            }
        } else {
            sellErrorMessage.setText("Please select a Tower or Upgrade to sell"
            );
        }
    }

    /**
     * This function is triggered when the "buy" button label is clicked.
     * It checks for valid tower or upgrade selection, then disables the
     * corresponding button. It verifies if the player has enough money to
     * perform the purchase and enough space to buy a tower. If not, an error
     * is caught and a message is displayed. It uses the buyUpgrade and buyTower
     * methods from the Shop class. Once an item is bought, it is added to the
     * player's inventory. This function also re-initialize the sell section in
     * the screen so the new bought items appear and the sold ones are removed
     * from screen in that section.
     */
    @FXML
    private void buy() {
        towerButtons = new ArrayList<>(List.of(shopTower1Button,
                shopTower2Button, shopTower3Button, shopTower4Button,
                shopTower5Button));

        upgradeButtons = new ArrayList<>(List.of(shopUpgrade1Button,
                shopUpgrade2Button, shopUpgrade3Button, shopUpgrade4Button,
                shopUpgrade5Button));

        // it's a tower
        if (selectedTowerIndex != -1) {
            try {
                if (disabledButtons.contains(towerButtons.
                        get(selectedTowerIndex))) {
                    throw new InvalidSelectionException();
                }
                currentShop.removeTowerSold(removeTowersSold);
                removeTowersSold.clear();
                towerSellImagePath.clear();
                playerMoney =
                        currentShop.buyTower(currentShop.getTowersForSale().
                                get(selectedTowerIndex), playerMoney);
                Audio audio = new Audio();
                audio.playSound(SOUND_PATH);
                buyErrorMessageLabel.setText("");
                playerMoneyLabel.setText(Integer.toString(playerMoney));
                towerButtons.get(selectedTowerIndex).setDisable(true);
                disabledButtons.add(towerButtons.get(selectedTowerIndex));
                currentHxTowersSell.getChildren().clear();
                sellTowerButton.clear();
                sellTowerInitializer();


            } catch (InventoryFullException | NotEnoughMoneyException
                     | InvalidSelectionException e) {
                buyErrorMessageLabel.setText(e.getMessage());
            }
        } else if (selectedUpgradeIndex != -1) {
            try {
                if (disabledButtons.contains(upgradeButtons.
                        get(selectedUpgradeIndex))) {
                    throw new InvalidSelectionException();
                }
                playerMoney =
                    currentShop.buyUpgrades(currentShop.getUpgradesForSale().
                                get(selectedUpgradeIndex), playerMoney
                        );
                Audio audio = new Audio();
                audio.playSound(SOUND_PATH);
                buyErrorMessageLabel.setText("");
                playerMoneyLabel.setText("" + playerMoney);
                upgradeButtons.get(selectedUpgradeIndex).setDisable(true);
                disabledButtons.add(upgradeButtons.get(selectedUpgradeIndex));
                currentHxUpgrade.getChildren().clear();
                sellUpgradeButton.clear();
                currentShop.removeUpgradesSold(removeUpgradesSold);
                removeUpgradesSold.clear();
                upgradeSellImagePath.clear();
                sellUpgradeInitializer();

            } catch (NotEnoughMoneyException | InvalidSelectionException e) {
                buyErrorMessageLabel.setText(e.getMessage());
            }
        } else {
            buyErrorMessageLabel.setText("Please select a Tower or Upgrade");
        }
    }

    /**
     * This function is triggered when the "back" button label is clicked.
     * It opens back up the roundSetupScreen. It removes sold
     * items form the player's inventory, and it will update the GameEnvironment
     * with the new data collected.
     */
    @FXML
    private void back() {
        currentShop.removeUpgradesSold(removeUpgradesSold);
        currentShop.removeTowerSold(removeTowersSold);
        gameManager.setPlayersMoney(playerMoney);
        inventory.setMainTowers(mainTowers);
        inventory.setReserveTowers(reserveTowers);
        inventory.setUpgrades(upgrades);
        gameManager.setInventory(inventory);
        disabledButtons.forEach(button -> button.setDisable(true));
        gameEnvironment.setDisabledButtons(disabledButtons);
        gameEnvironment.setGameManager(gameManager);
        gameEnvironment.closeShopScreen();
        gameEnvironment.launchSetupRoundScreen();
    }
}
