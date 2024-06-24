package seng201.team0.services;

import javafx.scene.control.Button;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * This class stores the state of the game and has methods to
 * launch the various screens.
 */
public class GameEnvironment {

    /**
     * Variable of class GameManager that stores information
     * about the player, money, round,
     * difficulty and random events.
     */
    private GameManager gameManager;

    /**
     * A private field representing a consumer function for
     * launching the game setup screen.
     * This function accepts a GameEnvironment parameter.
     */
    private final Consumer<GameEnvironment> gameSetupScreenLauncher;

    /**
     * A private field representing a consumer function for
     * launching the inventory screen.
     * This function accepts a GameEnvironment parameter.
     */
    private final Consumer<GameEnvironment> inventoryScreenLauncher;

    /**
     * A private field representing a consumer function for
     * launching the  end game screen.
     * This function accepts a GameEnvironment parameter.
     */
    private final Consumer<GameEnvironment> endGameScreenLauncher;

    /**
     * A private field representing a consumer function for
     * launching the setup round screen.
     * This function accepts a GameEnvironment parameter.
     */
    private final Consumer<GameEnvironment> setupRoundScreenLauncher;

    /**
     * A private field representing a consumer function for
     * launching the shop screen.
     * This function accepts a GameEnvironment parameter.
     */
    private final Consumer<GameEnvironment> shopScreenLauncher;
    /**
     * A private field representing a consumer function for
     * launching the random event screen.
     * This function accepts a GameEnvironment parameter.
     */
    private final Consumer<GameEnvironment> randomScreenLauncher;

    /**
     *  A private field representing a Runnable used to clear the
     * screen before launching a different
     * screen.
     */
    private final Runnable clearScreen;

    /**
     *  A private field representing a consumer function for
     * launching the round won screen.
     * This function accepts a GameEnvironment parameter.
     */
    private final Consumer<GameEnvironment> roundWonScreenLauncher;

    /**
     *  A private field that stores the list of Buttons that have
     * been disabled in the shop.
     */
    private List<Button> disabledButtons = new ArrayList<>();

    /**
     * This is a Constructor, setting values to the
     * GameEnvironment variables and constructing a
     * starting list of towers. After initializing it launches the Game setup
     * screen;
     * @param gameSetupScreenLauncher  A private field representing a
     *                                 consumer function for launching the
     *                                 inventory screen. This function accepts a
     *                                 GameEnvironment parameter.
     * @param setupRoundScreenLauncher A private field representing a consumer
     *                                 function for launching the shop screen.
     *                                 This function accepts a GameEnvironment
     *                                 parameter.
     * @param endGameScreenLauncher    A private field representing a consumer
     *                                 function for launching the setup round
     *                                 screen. This function accepts a
     *                                 GameEnvironment parameter.
     * @param inventoryScreenLauncher  A private field representing a
     *                                 consumer function for launching the
     *                                 between rounds screen. This function
     *                                 accepts a GameEnvironment parameter.
     * @param shopScreenLauncher       A private field representing a
     *                                 consumer function for launching the
     *                                 shop screen. This function accepts a
     *                                 GameEnvironment parameter.
     * @param clearScreen              A private field representing a
     *                                 Runnable used to clear the screen
     *                                 before launching a different screen.
     * @param randomScreenLauncher     A private field representing a
     *                                 consumer function for launching the
     *                                 random event screen. This function
     *                                 accepts a GameEnvironment parameter.
     * @param roundWonScreenLauncher A private field representing a consumer
     *                               function for launching the roundWonScreen
     *                               screen. This function accepts a
     *                               GameEnvironment parameter.
     *
     */
    @SuppressWarnings({"checkstyle:HiddenField", "checkstyle:ParameterNumber"})
    public GameEnvironment(
            final Consumer<GameEnvironment> gameSetupScreenLauncher,
            final Consumer<GameEnvironment> setupRoundScreenLauncher,
            final Consumer<GameEnvironment> endGameScreenLauncher,
            final Consumer<GameEnvironment> inventoryScreenLauncher,
            final Consumer<GameEnvironment> shopScreenLauncher,
            final Consumer<GameEnvironment> randomScreenLauncher,
            final Runnable clearScreen,
            final Consumer<GameEnvironment> roundWonScreenLauncher) {
        this.gameManager = new GameManager();
        this.clearScreen = clearScreen;
        this.randomScreenLauncher = randomScreenLauncher;
        this.shopScreenLauncher = shopScreenLauncher;
        this.setupRoundScreenLauncher = setupRoundScreenLauncher;
        this.endGameScreenLauncher = endGameScreenLauncher;
        this.inventoryScreenLauncher = inventoryScreenLauncher;
        this.gameSetupScreenLauncher = gameSetupScreenLauncher;
        this.roundWonScreenLauncher = roundWonScreenLauncher;
        launchGameSetupScreen();
    }

    /**
     * Launches the game setup screen
     */
    public void launchGameSetupScreen() {
        gameSetupScreenLauncher.accept(this);
    }

    /**
     * Closes the game setup screen.
     */
    public void closeGameSetupScreen() {

        clearScreen.run();
        this.gameManager.generateRoundOptions();
        this.gameManager.createShop();
    }

    /**
     * Launches the round setup screen.
     */
    public void launchSetupRoundScreen() {
        setupRoundScreenLauncher.accept(this);
    }
    /**
     * Launches the random event screen.
     */
    public void launchRandomScreen() {
        randomScreenLauncher.accept(this);
    }

    /**
     * Closes the setup round screen.
     */
    public void closeSetupRoundScreen() {
        clearScreen.run();
    }

    /**
     * Launches the inventory screen.
     */
    public void launchInventoryScreen() {
        inventoryScreenLauncher.accept(this);
    }

    /**
     *
     * Closes the inventory screen.
     */
    public void closeInventoryScreen() {
        clearScreen.run();
    }

    /**
     * Launches the shop screen.
     */
    public void launchShopScreen() {
        shopScreenLauncher.accept(this);
    }

    /**
     * Closes the shop screen.
     */
    public void closeShopScreen() {
        clearScreen.run();
    }

    /**
     * Launches the round won screen.
     */
    public void launchRoundWonScreen() {
        roundWonScreenLauncher.accept(this);
    }

    /**
     * Closes the round won screen.
     */
    public void closeRoundWonScreen() {
        clearScreen.run();
    }

    /**
     * Closes the random even screen.
     */
    public void closeRandomScreen() {
        clearScreen.run();
    }

    /**
     *  Launches the end game screen.
     */
    public void launchEndGameScreen() {
        endGameScreenLauncher.accept(this);
    }

    /**
     *  Closes the end game screen.
     */
    public void closeEndGameScreen() {
        System.exit(0);
    }

    /**
     * Setter method for assigning disabledButtons to a list of
     * buttons.
     * @param disabledButtons List of disabled buttons.
     */
    @SuppressWarnings("checkstyle:HiddenField")
    public void setDisabledButtons(final List<Button> disabledButtons) {
        this.disabledButtons = disabledButtons;
    }

    /**
     * After a round is started this method is used to clear the
     * disabled button list.
     */
    public void clearDisabledButtons() {
        disabledButtons.clear();
    }

    /**
     * This method returns the List of disabled buttons. A
     * button is disabled after the player has
     * purchased that tower or upgrade in the shop and until the next round
     * is launched.
     * @return List of disabledButtons
     */
    public List<Button> getDisabledButtons() {
        return disabledButtons;
    }

    /**
     * A getter method for a gameManager object.
     * @return gameManager Keeps track of game variables
     */
    public GameManager getGameManager() {
        return gameManager;
    }

    /**
     *  A setter method for a gameManager object.
     * @param gameManager Stores the state of the game manager.
     */
    @SuppressWarnings("checkstyle:HiddenField")
    public void setGameManager(final GameManager gameManager) {
        this.gameManager = gameManager;
    }
}

