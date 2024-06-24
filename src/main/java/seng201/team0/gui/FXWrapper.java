package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import seng201.team0.services.GameEnvironment;

import java.io.IOException;

/**
 * A utility class that provides methods to manage JavaFX UI
 * screens within the gameEnvironment. This class handles screen transitions and
 * initializes screen controllers for different game states.
 * @author Julia Clade and Katherine Irving Seguel and seng202 teaching team
 * @version 1.0, 11 Apr 2024
 */
public class FXWrapper {

    /**
     * Default constructor for FXWrapper.
     */
    public FXWrapper() {
        super();
    }

    /**
     * Stores the pane object.
     */
    @FXML
    private Pane pane;

    /**
     * Stores the stage object.
     */
    private Stage stage;


    /**
     * Launches the GameEnvironment with the specified stage,
     * sets up event handlers for launching various screens and manages the
     * application's UI.
     *
     * @param stage The primary JavaFX stage used to display scenes and UI
     *              elements. This stage is required for initializing the
     *              gamEnvironment.
     */
    @SuppressWarnings("checkstyle:HiddenField")
    public void init(final Stage stage) {
        this.stage = stage;
        new GameEnvironment(this::launchGameSetupScreen,
                this::launchSetupRoundScreen,
                this::launchEndGameScreen,
                this::launchInventoryScreen,
                this::launchShopScreen,
                this::launchRandomScreen,
                this::clearPane,
                this::launchRoundWonScreen);
    }

    /**
     * Launches the game setup screen by loading the corresponding
     * FXML file and setting up the controller.
     *
     * @param gameEnvironment The GameEnvironment instance is used to manage
     *                        game screens and state.
     */
    public void launchGameSetupScreen(final GameEnvironment gameEnvironment) {
        try {
            FXMLLoader setupLoader = new FXMLLoader(getClass()
                            .getResource("/fxml/game_setup_screen.fxml"));
            // provide a custom Controller with parameters
            setupLoader.setControllerFactory(param ->
                             new GameSetupScreenController(gameEnvironment));
            Parent setupParent = setupLoader.load();
            pane.getChildren().add(setupParent);
            stage.setTitle("Tower Quest - Game Setup");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Clears the current pane. Gets called before loading the
     * next pane.
     */
    public void clearPane() {
        pane.getChildren().removeAll(pane.getChildren());
    }

    /**
     * Launches the round setup screen by loading the corresponding
     * FXML file and setting up the controller.
     *
     * @param gameEnvironment The GameEnvironment instance is used to manage
     *                        game screens and state.
     */
    public void launchSetupRoundScreen(final GameEnvironment gameEnvironment) {
        try {
            FXMLLoader mainScreenLoader = new FXMLLoader(getClass()
                         .getResource("/fxml/setup_round_screen.fxml"));
            mainScreenLoader.setControllerFactory(param ->
                              new SetupRoundScreenController(gameEnvironment));
            Parent setupParent = mainScreenLoader.load();
            pane.getChildren().add(setupParent);
            stage.setTitle("Setup Round - Chose Difficulty");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Launches the round setup screen by loading the corresponding
     * FXML file and setting up the controller.
     *
     * @param gameEnvironment The GameEnvironment instance is used to manage
     *                        game screens and state.
     */
    public void launchEndGameScreen(final GameEnvironment gameEnvironment) {
        try {
            FXMLLoader mainScreenLoader = new FXMLLoader(getClass()
                         .getResource("/fxml/end_game_screen.fxml"));
            mainScreenLoader.setControllerFactory(param ->
                              new EndGameScreenController(gameEnvironment));
            Parent setupParent = mainScreenLoader.load();
            pane.getChildren().add(setupParent);
            stage.setTitle("End Of Game");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Launches the inventory screen by loading the corresponding
     * FXML file and setting up the controller.
     *
     * @param gameEnvironment The GameEnvironment instance is used to manage
     *                        game screens and state.
     */
    public void launchInventoryScreen(final GameEnvironment gameEnvironment) {
        try {
            FXMLLoader mainScreenLoader = new FXMLLoader(getClass()
                            .getResource("/fxml/inventory_screen.fxml"));
            mainScreenLoader.setControllerFactory(param ->
                            new InventoryScreenController(gameEnvironment));
            Parent setupParent = mainScreenLoader.load();
            pane.getChildren().add(setupParent);
            stage.setTitle("Inventory");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Launches the shop screen by loading the corresponding
     * FXML file and setting up the controller.
     *
     * @param gameEnvironment The GameEnvironment instance is used to manage
     *                        game screens and state.
     */
    public void launchShopScreen(final GameEnvironment gameEnvironment) {
        try {
            FXMLLoader mainScreenLoader = new FXMLLoader(getClass()
                             .getResource("/fxml/shop_screen.fxml"));
            mainScreenLoader.setControllerFactory(param ->
                              new ShopScreenController(gameEnvironment));
            Parent setupParent = mainScreenLoader.load();
            pane.getChildren().add(setupParent);
            stage.setTitle("Shop");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Launches the round won screen by loading the corresponding
     * FXML file and setting up the controller.
     *
     * @param gameEnvironment The GameEnvironment instance is used to manage
     *                        game screens and state.
     */
    public void launchRoundWonScreen(final GameEnvironment gameEnvironment) {
        try {
            FXMLLoader mainScreenLoader = new FXMLLoader(getClass()
                         .getResource("/fxml/round_won_screen.fxml"));
            mainScreenLoader.setControllerFactory(param ->
                              new RoundWonScreenController(gameEnvironment));
            Parent setupParent = mainScreenLoader.load();
            pane.getChildren().add(setupParent);
            stage.setTitle("Round won");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Launches the random event screen by loading the
     * corresponding FXML file and setting up the controller.
     *
     * @param gameEnvironment The GameEnvironment instance is used to manage
     *                        game screens and state.
     */
    public void launchRandomScreen(final GameEnvironment gameEnvironment) {
        try {
            FXMLLoader mainScreenLoader =
                    new FXMLLoader(getClass()
                               .getResource("/fxml/random_screen.fxml"));
            mainScreenLoader.setControllerFactory(param ->
                              new RandomEventScreenController(gameEnvironment));
            Parent setupParent = mainScreenLoader.load();
            pane.getChildren().add(setupParent);
            stage.setTitle("Random Event");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
